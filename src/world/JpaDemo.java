package world;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import world.domain.City;
import world.domain.Country;

public class JpaDemo {

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("WorldPU");
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args) {

Country javae= new Country("JavaLandMissy", "South America", 1L);

javae.setRegion("Eastern");
javae.setCode("342");

City espressoMissy = new City ("EspressoMissy");
espressoMissy.setDistrict("Beans");
espressoMissy.setPopulation(20000L);
espressoMissy.setCountry(javae);


EntityManager em= factory.createEntityManager();
em.getTransaction().begin();
em.persist(javae);
em.getTransaction().commit();


//findCity("Los Angeles");
//        while(true){
//            System.out.println("What country would you like to find out more about?");
//        String country =sc.nextLine();
//        findCountry(country);
//        }
//        //    findCountry("Sweden");
        // menu();
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
            System.out.println("Bye, thank you for using the program");
            System.exit(0);
        }

    }

    public static void findCity(String name) {

        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery("SELECT c from City c where c.name = :name");
        query.setParameter("name", name);

        List<City> cities = (List<City>) query.getResultList();

        System.out.printf("Found %d matches for %s%n", cities.size(), name);
        cities.forEach((c) -> {
            System.out.println(c.getName() + " ," + c.getPopulation() + " ," + c.getCountry().getName());
        });
        em.close();
    }

    public static void findCountry(String name) {

        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT m from Country m where m.name = :name");
        query.setParameter("name", name);

        try {
            Country m = (Country) query.getSingleResult();

            System.out.println(m.getName()+"Capital city: "+m.getCapital().getName());
            System.out.println("The population in "+m.getCapital().getName()+" is "+m.getCapital().getPopulation());
            System.out.println(m.getName()+" is in "+m.getRegion());
            System.out.println("The population in "+m.getName()+" is "+ m.getPopulation());
            System.out.println("Cities in "+ m.getName()+ " are:");
           
            
            
            m.getCities().forEach((temp) -> {
                System.out.println(temp.getName());
            });
        }catch (Exception e){
            System.out.println("No such country");
        }
            em.close();

        }
    }
 //private List<Country> countries;
