/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Facade.ClienteFacade;
import entity.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author loren
 */
@Named(value = "loginOspiteController")
@SessionScoped
public class LoginOspiteController implements Serializable {

    String username, password;

    @Inject
    private LoginController ejbLogin;

    @EJB
    private ClienteFacade ejbCliente;

    public LoginOspiteController() {
    }

    public String loginCliente() {
        try {
            Cliente nuovo = ejbCliente.find1(ejbLogin.getUsername());
            if (!(ejbLogin.getPassword().equals(nuovo.getPassword()))) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore Password", "Password sbagliata");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "loginOspite";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Password ok", "Password ok");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "prenotaPullman";
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Utente non trovato", "Utente no");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "loginOspite";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
