/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Facade.AdminFacade;
import Facade.ClienteFacade;
import entity.Admin;
import entity.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author loren
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String username, password;
    private int numMessaggi;

    public int getNumMessaggi() {
        return numMessaggi;
    }

    public void setNumMessaggi(int numMessaggi) {
        this.numMessaggi = numMessaggi;
    }

    @EJB
    private ClienteFacade ejbCliente;

    @EJB
    private AdminFacade ejbAdmin;

    public LoginController() {
    }

    public String loginCliente() {
        try {
            Admin admin = ejbAdmin.find(username);
            if (!(password.equals(admin.getPassword()))) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore Password", "Password sbagliata");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Accesso come admin", "Password ok");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "schermataAdmin";
            }
        } catch (Exception e) {
            try {
                Cliente nuovo = ejbCliente.find1(username);
                if (!(password.equals(nuovo.getPassword()))) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore Password", "Password sbagliata");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return "login";
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login effettuato con successo", "");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    numMessaggi = nuovo.getMessaggi().size();
                    return "schermataIniziale";
                }
            } catch (Exception e1) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Utente non trovato", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
            }
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
