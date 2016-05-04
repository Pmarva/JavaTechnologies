package SimplePage;

import SimplePage.entity.Item;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by marva on 11.04.16.
 */
public class EntityManager {
    static EntityManagerFactory emfactory;
    static javax.persistence.EntityManager em;

    public static void init() {
        emfactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = emfactory.createEntityManager();
    }

    public static void newItem(String name) {
        em.getTransaction().begin();
        Item item = new Item();
        item.setName(name);
        em.persist(item);
        em.getTransaction().commit();
    }

    public static void deleteItem(long id) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Item i WHERE i.id =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public static String getList() {
        List<Item> items = em.createQuery("SELECT i FROM Item i ORDER BY i.id", Item.class).getResultList();

        String html="";

        for (Item i:items) {
            html+="<li>";
            html+=i.getName();
            html+=" <a href=\"test?action=del&data="+i.getId()+"\">x</a>";
            html+="</li>";
        }
        return html;
    }

    public static void showItems() {
        List<Item> items = em.createQuery("SELECT i FROM Item i ORDER BY i.id", Item.class).getResultList();
        for(Item i:items) {
            System.out.printf("%d   %s\n", i.getId(), i.getName());
        }
    }
}
