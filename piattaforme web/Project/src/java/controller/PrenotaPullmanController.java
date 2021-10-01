package controller;

import Facade.ClienteFacade;
import Facade.OrdineFacade;
import Facade.PullmanFacade;
import entity.Cliente;
import entity.Pullman;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;

/**
 *
 * @author loren
 */
@Named(value = "prenotaPullmanController")
@SessionScoped
public class PrenotaPullmanController implements Serializable {

    @EJB
    private PullmanFacade ejbPullman;

    @EJB
    private ClienteFacade ejbCliente;
    
    @EJB
    private OrdineFacade ejbOrdine;

    @Inject
    private CercaPullmanController ejbCVC;

    @Inject
    private LoginController ejbLogin;

    @Inject
    private ProfiloController ejbProfilo;

    private Pullman pullman;
    private boolean isVisible;

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    private int numeroPullman;

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumeroPullman() {
        return numeroPullman;
    }

    public void setNumeroPullman(int numeroPullman) {
        this.numeroPullman = numeroPullman;
    }

    public Pullman getPullman() {
        return pullman;
    }

    public void setPullman(Pullman pullman) {
        this.pullman = pullman;
    }

    public List<Pullman> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Pullman> ordini) {
        this.ordini = ordini;
    }

    private List<Pullman> ordini, pullmans;

    public PrenotaPullmanController() {
    }

    public String prepareCreate() {
        Calendar calendar = Calendar.getInstance();
        if (ejbCVC.getData().before(calendar.getTime())) {
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Data passata", "Impossibile prenotare pullman per una data passata");
            FacesContext.getCurrentInstance().addMessage(null, message1);
            return "cercaPullman";
        }
        if (ejbCVC.getArrivo().equals(ejbCVC.getPartenza())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Arrivo e partenza uguali", "Selezionare due città diverse");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "cercaPullman";
        }
        pullman = new Pullman();
        cliente = new Cliente();
        numeroPullman = 0;
        ordini = cercaPullmans(ejbCVC.getPartenza(), ejbCVC.getArrivo(), ejbCVC.getData());
        pullmans = new LinkedList<>();
        return "prenotaPullman";
    }

    public List<Pullman> getPullmans() {
        return pullmans;
    }

    public void setPullmans(List<Pullman> pullmans) {
        this.pullmans = pullmans;
    }

    public List<Pullman> cercaPullmans(String partenza, String arrivo, Date dataPullman) {
        List<Pullman> trovati = new LinkedList<>();
        for (Pullman v : ejbPullman.tuttiPullmans()) {
            if (v.getPartenza().equals(partenza) && v.getArrivo().equals(arrivo) && v.getDataPullman().equals(dataPullman)) {
                trovati.add(v);
            }
        }
        return trovati;
    }

    public String prenotaPullman() {
        try {
            pullman = ejbPullman.trovaPullman(numeroPullman);
            String idRicerca = ejbLogin.getUsername();
            cliente = ejbCliente.find1(idRicerca);

            if (pullman.getCosto() < cliente.getCreditoCliente() && pullman.getPostiDisponibili() > 0 && !cliente.getOrdini().contains(pullman)) {
                pullman.setPostiDisponibili(pullman.getPostiDisponibili() - 1);
                cliente.setCreditoCliente(cliente.getCreditoCliente() - pullman.getCosto());
                cliente.getOrdini().add(pullman);
                pullman.getClienti().add(cliente);
                ejbOrdine.edit(cliente, pullman);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pullman prenotato", "Nuovo credito " + cliente.getCreditoCliente());
                FacesContext.getCurrentInstance().addMessage(null, message);
                isVisible = true;
                prepareCreate();
                return "profilo";
            }
            if (cliente.getOrdini().contains(pullman)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Pullman già prenotato", "Non può acquistare più di un biglietto");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "prenotaPullman";
            }
            if (pullman.getCosto() > cliente.getCreditoCliente()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Credito insufficiente, ricarica", pullman.getCosto() - cliente.getCreditoCliente() + "£ mancanti");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            if (pullman.getPostiDisponibili() <= 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posti non disponibili", "Nessun posto");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "cercaPullman";
            }
        } catch (OptimisticLockException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Riprova", "Aggiorna la pagina");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "prenotaPullman";
        }
        return "";
    }

    public String prenotaPullman(int numPullman) {
        try {
            pullman = ejbPullman.trovaPullman(numPullman);
            String idRicerca = ejbLogin.getUsername();
            cliente = ejbCliente.find1(idRicerca);

            if (pullman.getCosto() <= cliente.getCreditoCliente() && pullman.getPostiDisponibili() > 0 && !cliente.getOrdini().contains(pullman)) {
                pullman.setPostiDisponibili(pullman.getPostiDisponibili() - 1);
                cliente.setCreditoCliente(cliente.getCreditoCliente() - pullman.getCosto());
                cliente.getOrdini().add(pullman);
                pullman.getClienti().add(cliente);
                ejbOrdine.edit(cliente, pullman);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pullman prenotato", "Nuovo credito " + cliente.getCreditoCliente());
                FacesContext.getCurrentInstance().addMessage(null, message);
                isVisible = true;
                ejbProfilo.prepareCreate();
                return "profilo";
            }
            if (cliente.getOrdini().contains(pullman)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Pullman già prenotato", "Non può acquistare più di un biglietto");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "prenotaPullman";
            }
            if (pullman.getCosto() > cliente.getCreditoCliente()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Credito insufficiente, ricarica", pullman.getCosto() - cliente.getCreditoCliente() + "€ mancanti");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            if (pullman.getPostiDisponibili() <= 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posti non disponibili", "Nessun posto");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "cercaPullman";
            }
        } catch (OptimisticLockException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Riprova", "Aggiorna la pagina");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "prenotaPullman";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Riprova", "Effettuare il login");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "prenotaPullman";
        }
        return "";
    }

    public String visibile() {
        if (isVisible) {
            return "block";
        }
        return "none";
    }
}
