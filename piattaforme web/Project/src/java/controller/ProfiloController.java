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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
@Named(value = "profiloController")
@SessionScoped
public class ProfiloController implements Serializable {

    @Inject
    private LoginController ejbLogin;

    @EJB
    private PullmanFacade ejbPullman;

    @EJB
    private ClienteFacade ejbCliente;
    
    @EJB
    private OrdineFacade ejbOrdine;

    private List<Pullman> ordini;

    private Cliente c;

    private Pullman pullman, pullmanBiglietto;

    public Pullman getPullmanBiglietto() {
        return pullmanBiglietto;
    }

    public void setPullmanBiglietto(Pullman pullmanBiglietto) {
        this.pullmanBiglietto = pullmanBiglietto;
    }

    public List<Pullman> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Pullman> ordini) {
        this.ordini = ordini;
    }

    public ProfiloController() {
    }

    public String prepareCreate() {
        try {
            c = ejbCliente.find1(ejbLogin.getUsername());
            ordini = c.getOrdini();
            return "profilo";
        } catch (Exception e) {
            return "login";
        }
    }

    public String displayValue() {
        try {
            c = ejbCliente.find1(ejbLogin.getUsername());
            if (c == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            if (c == null) {
                return "none";
            }
        }
        return "block";
    }

    public String displayNoValue() {
        try {
            c = ejbCliente.find1(ejbLogin.getUsername());
            if (c == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            if (c == null) {
                return "block";
            }
        }
        return "none";
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
            Calendar cc = new GregorianCalendar();
            if (!pullman.getDataPullman().before(cc.getTime())) {
                Cliente c = ejbCliente.find1(ejbLogin.getUsername());
                c.getOrdini().remove(pullman);
                pullman.getClienti().remove(c);
                c.setCreditoCliente(c.getCreditoCliente() + pullman.getCosto());
                pullman.setPostiDisponibili(pullman.getPostiDisponibili() + 1);
                ejbOrdine.edit(c, pullman);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pullman cancellato", "Pullman " + pullman.getNumeroPullman() + " cancellato");
                FacesContext.getCurrentInstance().addMessage(null, message);
                prepareCreate();
                return "profilo";
            }
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
        return "";
    }


    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }
}
