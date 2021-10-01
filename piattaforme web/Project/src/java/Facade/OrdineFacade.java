/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import entity.Cliente;
import entity.Pullman;
import static java.util.Collections.list;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;



/**
 *
 * @author loren
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrdineFacade{
    @EJB
    private PullmanFacade ejbPullman;

    @EJB
    private ClienteFacade ejbCliente;
    
    public void edit (Cliente c , Pullman p){
        ejbCliente.edit(c);
        ejbPullman.edit(p);
    }
    public void edit (List<Cliente> c , Pullman p){
        for ( Cliente c1 : c){
            ejbCliente.edit(c1);
        }
        ejbPullman.remove(p);
    }
}
