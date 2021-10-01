/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author loren
 */
@Named(value = "schermataInizialeController")
@SessionScoped
public class SchermataInizialeController implements Serializable {

    private List<String> immagini;
    private String password;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<String> immagini) {
        this.immagini = immagini;
    }

    @PostConstruct
    public void scorriImmagini() {
        immagini = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            immagini.add("Hotel" + i + ".jpg");
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "home";
    }

    public SchermataInizialeController() {
    }
}
