/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Facade.ClienteFacade;
import Facade.OrdineFacade;
import Facade.PullmanFacade;
import entity.Cliente;
import entity.Pullman;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.OptimisticLockException;

/**
 *
 * @author loren
 */
@Named(value = "cancellaPullmanController")
@SessionScoped
public class CancellaPullmanController implements Serializable {

    @EJB
    private PullmanFacade ejbPullman;

    @EJB
    private ClienteFacade ejbCliente;
    
    @EJB
    private OrdineFacade ejbOrdine;

    private List<Pullman> pullmans;

    private Pullman pullman;

    private int numeroPullman;

    public List<Pullman> getPullmans() {
        return pullmans;
    }

    public void setPullmans(List<Pullman> pullmans) {
        this.pullmans = pullmans;
    }

    public Pullman getPullman() {
        return pullman;
    }

    public void setPullman(Pullman pullman) {
        this.pullman = pullman;
    }

    public int getNumeroPullman() {
        return numeroPullman;
    }

    public void setNumeroPullman(int numeroPullman) {
        this.numeroPullman = numeroPullman;
    }
    public CancellaPullmanController() {
    }

    public String prepareCreate() {
        pullmans = ejbPullman.tuttiPullmans();
        return "cancellaPullman";
    }

    public String cancellaPullmans() throws Exception {
        try {
            pullman = ejbPullman.trovaPullman(numeroPullman);
        } catch (Exception e1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Pullman non trovato", "Non può cancellare pullman inesistente");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "cancellaPullman";
        }
        try {
            List<Cliente> client = ejbCliente.findPerPullman(numeroPullman);
            for (Cliente c : client) {
                c.getOrdini().remove(pullman);
                Calendar cc = new GregorianCalendar();
                if (!pullman.getDataPullman().before(cc.getTime())) {
                    c.setCreditoCliente(c.getCreditoCliente() + pullman.getCosto());
                }
                String messaggio = ("Pullman " + pullman.getNumeroPullman() + " del " + pullman.getGiorno() + "/" + (pullman.getMese()+1) + "/" + pullman.getAnno() + " cancellato");
                c.getMessaggi().add(messaggio);
            }
            ejbOrdine.edit(client,pullman);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pullman cancellato", "Pullman " + pullman.getNumeroPullman() + " cancellato");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "cancellaPullman";
        } catch (OptimisticLockException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Riprova", "Aggiorna la pagina");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "cancellaPullman";
        }
        catch (Exception e2) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nessun cliente ha prenotato il pullman", "Avvisato nessun cliente");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ejbPullman.remove(pullman);
            return "cancellaPullman";
        }
       
    }

    public String cancellaPullmans(int numeroPullman) throws Exception {
        try {
            pullman = ejbPullman.trovaPullman(numeroPullman);
        } catch (Exception e1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Pullman non trovato", "Non può cancellare pullman inesistente");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "cancellaPullman";
        }
        try {
            List<Cliente> client = ejbCliente.findPerPullman(numeroPullman);
            for (Cliente c : client) {
                c.getOrdini().remove(pullman);
                Calendar cc = new GregorianCalendar();
                if (!pullman.getDataPullman().before(cc.getTime())) {
                    c.setCreditoCliente(c.getCreditoCliente() + pullman.getCosto());
                }
                String messaggio = ("Pullman " + pullman.getNumeroPullman() + " del " + pullman.getGiorno() + "/" + (pullman.getMese()+1) + "/" + pullman.getAnno() + " cancellato");
                c.getMessaggi().add(messaggio);
            }
            ejbOrdine.edit(client,pullman);
            pullmans.remove(pullman);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pullman cancellato", "Pullman " + pullman.getNumeroPullman() + " cancellato");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "cancellaPullman";
        } catch (OptimisticLockException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Riprova", "Aggiorna la pagina");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "cancellaPullman";
        } catch (Exception e2) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nessun cliente ha prenotato il pullman", "Avvisato nessun cliente");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ejbPullman.remove(pullman);
            return "cancellaPullman";
        }
    }
}
