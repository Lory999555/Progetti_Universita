/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import entity.Cliente;
import entity.Pullman;
import java.util.LinkedList;
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
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "ProgettoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Cliente find1(String id) {
        try {
            Cliente c = em.find(Cliente.class, id);
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cliente> findPerPullman(int numeroPullman) {
        Query q = em.createNamedQuery("Cliente.findAll", Cliente.class);
        List<Cliente> allResults = q.getResultList();
        List<Cliente> results = new LinkedList<>();
        for (Cliente c : allResults) {
            for (Pullman v : c.getOrdini()) {
                if (v.getNumeroPullman().equals(numeroPullman)) {
                    results.add(c);
                }
            }
        }
        return results;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
}
