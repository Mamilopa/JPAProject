
package world;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import world.domain.City;

public class JpaDemo {
static EntityManagerFactory factory= Persistence.createEntityManagerFactory("WorldPU");
    public static void main(String[] args) {
        
        
        findCity("Los Angeles");
    }
    
    
    public static void findCity(String name){
    
   
    EntityManager em= factory.createEntityManager();
    
    Query query = em.createQuery("SELECT c from City c where c.name = :name");
    query.setParameter("name", name);
    
    List<City>cities =(List<City>) query.getResultList();
    
        System.out.printf("Found %d matches for %s%n", cities.size(), name);
        for (City c : cities){
            System.out.println(c);            
        }
        em.close();
}
    public static void findCountry(){
        
        EntityManager em= factory.createEntityManager();
        Query query = em.createQuery("SELECT m from Country m where m.name = :name");
        
        
    }
    
}
