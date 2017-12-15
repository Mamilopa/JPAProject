package world.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class City implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Long id;
   
    private String district;
    private String name;
    private Long population;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="countrycode")
   private Country country;
//    @OneToMany(mappedBy = "city")
//    private List<Country> countries;
//    

    public City(String name) {
        this.name = name;
    }
    

    public City() {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

   

   
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", district=" + district + ", name=" + name + ", population=" + population + ", country=" + country + '}';
    }

   
    

  
}
