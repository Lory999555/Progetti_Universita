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
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author loren
 */
@Named(value = "messaggiController")
@SessionScoped
public class MessaggiController implements Serializable {

    private List<String> messaggi;

    @EJB
    private ClienteFacade ejbCliente;

    @Inject
    private LoginController ejbLogin;

    public List<String> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<String> messaggi) {
        this.messaggi = messaggi;
    }

    public MessaggiController() {
    }

    public String prepareCreate() {
        try {
            Cliente c = ejbCliente.find1(ejbLogin.getUsername());
            messaggi = c.getMessaggi();
            return "messaggi";
        } catch (Exception e) {
            return "loginOspite";
        }
    }

    public String cancellaMessaggi() {
        try {
            Cliente c = ejbCliente.find1(ejbLogin.getUsername());
            c.getMessaggi().removeAll(c.getMessaggi());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancellazione avvenuta", "Cancellati tutti i messaggi");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ejbLogin.setNumMessaggi(0);
            ejbCliente.edit(c);
            return "schermataIniziale";
        } catch (Exception e) {
            return "schermataIniziale";
        }
    }
}
