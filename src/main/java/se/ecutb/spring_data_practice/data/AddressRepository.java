package se.ecutb.spring_data_practice.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.spring_data_practice.entity.Address;
import se.ecutb.spring_data_practice.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, String> {
    List<Address> findByCityContainsIgnoreCase(String city);
    List<Address> findByStreetContainsIgnoreCase(String street);



}
