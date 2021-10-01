/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Facade.PullmanFacade;
import entity.Cliente;
import entity.Pullman;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author loren
 */
@Named(value = "creaPullmanController")
@SessionScoped
public class CreaPullmanController implements Serializable {

    Pullman v;
    List<Cliente> c;
    Cliente cliente;

    private String passwordAmministratore;

    public String getPasswordAmministratore() {
        return passwordAmministratore;
    }

    public void setPasswordAmministratore(String passwordAmministratore) {
        this.passwordAmministratore = passwordAmministratore;
    }

    public List<Cliente> getC() {
        return c;
    }

    public void setC(List<Cliente> c) {
        this.c = c;
    }

    @EJB
    private PullmanFacade ejbPullman;

    public Pullman getV() {
        return v;
    }

    public void setV(Pullman v) {
        this.v = v;
    }

    public CreaPullmanController() {
    }

    public String prepareCreate() {
        v = new Pullman();
        c = new LinkedList<>();
        return "creaPullman";
    }

    public String salvaPullman() throws Exception {
        Calendar c1 = new GregorianCalendar();
        Calendar cal = new GregorianCalendar();
        cal.setTime(v.getDataPullman());
        if (cal.getTime().before(c1.getTime())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Data passata", "Selezionare una data futura");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "creaPullman";
        }
        if (Integer.parseInt(v.getOrarioPartenza()) >= Integer.parseInt(v.getOrarioArrivo())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Orari impossibili", "Selezionare due orari diversi");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "creaPullman";
        }
        if (v.getPartenza().equals(v.getArrivo())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Partenza e arrivo coincidenti", "Selezionare due città differenti");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "creaPullman";
        }if (v.getPostiDisponibili() <= 0 ){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posti Disponibili impossibili", "Inserire un valore valido");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "creaPullman";
        }else {
            v.setGiorno(cal.get(Calendar.DAY_OF_MONTH));
            v.setMese(cal.get(Calendar.MONTH));
            v.setAnno(cal.get(Calendar.YEAR));
            ejbPullman.create(v);
            return "schermataAdmin";
        }
    }
}
