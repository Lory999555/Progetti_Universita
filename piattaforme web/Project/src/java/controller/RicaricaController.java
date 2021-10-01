/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Facade.ClienteFacade;
import entity.Cliente;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author loren
 */
@Named(value = "ricaricaController")
@SessionScoped
public class RicaricaController implements Serializable {

    public RicaricaController() {
    }

    int credito, ricarica;

    public int getRicarica() {
        return ricarica;
    }

    public void setRicarica(int ricarica) {
        this.ricarica = ricarica;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    @Inject
    private LoginController ejbLogin;

    @EJB
    private ClienteFacade ejbCliente;

    public String prepareCreate() {
        credito = 0;
        ricarica = 0;
        return "ricarica";
    }

    public String ricaricare() throws Exception {
        try {
            Cliente c = ejbCliente.find1(ejbLogin.getUsername());
            c.setCreditoCliente(c.getCreditoCliente() + ricarica);
            ejbCliente.edit(c);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ricarica avvenuuta con successo", "Nuovo credito: " + c.getCreditoCliente());
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "prenotaPullman";
        } catch (Exception e) {
            return "loginOspite";
        }
    }
}
