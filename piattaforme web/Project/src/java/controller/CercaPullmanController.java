/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Facade.PullmanFacade;
import entity.Pullman;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author loren
 */
@Named(value = "cercaPullmanController")
@SessionScoped
public class CercaPullmanController implements Serializable {

    String partenza, arrivo;
    Date data;
    List<Pullman> pullmans;

    @EJB
    private PullmanFacade ejbPullman;

    public List<Pullman> getPullmans() {
        return pullmans;
    }

    public void setPullmans(List<Pullman> pullmans) {
        this.pullmans = pullmans;
    }

    public CercaPullmanController() {
    }

    public String prepareCreate() {
        partenza = "";
        arrivo = "";
        pullmans = new LinkedList<>();
        return "cercaPullman";
    }

    public String tuttiPullmans() {
        pullmans = ejbPullman.tuttiPullmans();
        return "tuttiPullman";
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getArrivo() {
        return arrivo;
    }

    public void setArrivo(String arrivo) {
        this.arrivo = arrivo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
