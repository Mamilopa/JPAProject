package world;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import world.domain.City;
import static world.domain.City_.name;
import world.domain.Country;

public class JpaDemo {

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("WorldPU");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
//        findCity("Los Angeles");
//        findCountry("Sweden");
        menu();
    }

    public static void menu() {
        boolean loop = true;
        do {
            try {
                System.out.println("Which country would you like to find?");
                String country = sc.nextLine();
                findCountry(country);
                System.out.println("Would you like to find another country? Y/N");
                String response = sc.nextLine();
                if (response.equalsIgnoreCase("y")) {
                } else {
                    loop = false;
                }
            } catch (NoResultException e) {
                System.out.println("Country not found, please try again");
            }
        } while (loop);
        
        if (loop == false) {
            System.out.println("Bye");
                 System.exit(0);
        }
   

    }

    public static void findCity(String name) {

        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery("SELECT c from City c where c.name = :name");
        query.setParameter("name", name);

        List<City> cities = (List<City>) query.getResultList();

        System.out.printf("Found %d matches for %s%n", cities.size(), name);
        for (City c : cities) {
            System.out.println(c);
        }
        em.close();
    }

    public static void findCountry(String name) {

        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT m from Country m where m.name = :name");
        query.setParameter("name", name);

        Country m = (Country) query.getSingleResult();

        System.out.println(m);

        em.close();

    }

}
