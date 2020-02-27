package se.ecutb.spring_data_practice.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.ecutb.spring_data_practice.entity.Address;
import se.ecutb.spring_data_practice.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, String> {

    AppUser findByEmailContainsIgnoreCase(String email);
    List<AppUser> findByNameContainsIgnoreCase(String name);
    List<AppUser> findByEmailAndPassword(String email, String password);
    List<AppUser> findByNameContaining(String string);

    List<AppUser> findByAddressStreet(String street);
    List<AppUser> findByAddressCityContainsIgnoreCase(String city);



    //List<AppUser> findByStreetContainsIgnoreCase(String street);

}
