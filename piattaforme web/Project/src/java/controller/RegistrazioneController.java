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
import entity.Pullman;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author loren
 */
@Named(value = "registrazioneController")
@SessionScoped
public class RegistrazioneController implements Serializable {

    Cliente cliente;
    Pullman pullman;
    private String isAdmin;

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Pullman getPullman() {
        return pullman;
    }

    public void setPullman(Pullman pullman) {
        this.pullman = pullman;
    }

    @EJB
    private ClienteFacade ejbCliente;

    @EJB
    private AdminFacade ejbAdmin;

    public RegistrazioneController() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String prepareCreate() {
        cliente = new Cliente();
        List<String> messaggi = new LinkedList<>();
        cliente.setMessaggi(messaggi);
        isAdmin = "";
        return "registrazione";
    }

    public String salvaCliente() {
        try {
            if (isAdmin.equals("SI")) {
                if (ejbAdmin.find(cliente.getUsername()) == null && ejbCliente.find(cliente.getUsername()) == null) {
                    ejbAdmin.create(new Admin(cliente.getUsername(), cliente.getPassword()));
                    return "login";
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Username già presente", "Inserisci altro username");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return "registrazione";
                }
            } else {
                if (ejbAdmin.find(cliente.getUsername()) != null || ejbCliente.find(cliente.getUsername()) != null) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Username già presente", "Inserisci altro username");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return "registrazione";
                }
                Cliente c = ejbCliente.find1(cliente.getUsername());
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Username già presente " + c.getUsername(), "Inserisci altro username");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "registrazione";
            }
        } catch (Exception e) {
            ejbCliente.create(cliente);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente creato con successo", cliente.getUsername());
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login";
        }
    }
}
