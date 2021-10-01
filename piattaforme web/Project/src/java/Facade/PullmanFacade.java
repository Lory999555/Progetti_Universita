/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import entity.Cliente;
import entity.Pullman;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author loren
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PullmanFacade extends AbstractFacade<Pullman> {

    @PersistenceContext(unitName = "ProgettoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PullmanFacade() {
        super(Pullman.class);
    }

    public Cliente trovaCliente(String id) {
        Cliente c = em.find(Cliente.class, id);
        return c;
    }

    public List<Pullman> tuttiPullmans() {
        Query q = em.createNamedQuery("Pullman.findAll", Pullman.class);
        return q.getResultList();
    }

    public Pullman trovaPullman(int numeroPullman) {
        Pullman ritorno = em.find(Pullman.class, numeroPullman);
        return ritorno;
    }
}
