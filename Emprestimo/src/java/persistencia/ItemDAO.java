/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Item;

/**
 *
 * @author 2info2021
 */
public class ItemDAO {

    EntityManager em;

    public ItemDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Item i) {
        emTransaction();
        if (i.getId_item()== 0) {
            em.persist(i);
        }else{
            em.merge(i);
        }
        em.getTransaction().commit();
    }

    public List<Item> pesquisa() {
        Query q = em.createQuery("select i from Item i order by i.id_item");
        List<Item> listaItem = q.getResultList();
        return listaItem;
    }
    
     public void emTransaction(){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
    }

    // localiza
    // buscar o primeiro registro da tabela e setar no bean
    public Item localiza(int id_item) {
        Item i = em.find(Item.class, id_item);
        return i;
    }

    public void exclui(Item i) {
        em.getTransaction().begin();
        em.remove(i);
        em.getTransaction().commit();
    }
}



