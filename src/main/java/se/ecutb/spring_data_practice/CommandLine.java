package se.ecutb.spring_data_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.spring_data_practice.data.AddressRepository;
import se.ecutb.spring_data_practice.data.AppUserRepository;
import se.ecutb.spring_data_practice.data.StatusRepository;
import se.ecutb.spring_data_practice.entity.Address;
import se.ecutb.spring_data_practice.entity.AppUser;


import java.util.List;
import java.util.Optional;

@Repository
public class CommandLine implements CommandLineRunner {

    private StatusRepository statusRepository;
    private AddressRepository addressRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public CommandLine(StatusRepository statusRepository, AddressRepository addressRepository, AppUserRepository appUserRepository) {
        this.statusRepository = statusRepository;
        this.addressRepository = addressRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        AppUser user1 = new AppUser("peter@gmail.com", "Peter", "9999", null);
        AppUser user2 = new AppUser("lars@gmail.com", "Lars", "abcd", null);
        AppUser user3 = new AppUser("lisa@gmail.com", "Lisa", "a1b7", null);
        appUserRepository.save(user1);
        appUserRepository.save(user2);
        appUserRepository.save(user3);

        Address address1 = new Address("Hemvägen 1", "123 45", "Hemstad");
        Address address2 = new Address("Bortagatan 25", "789 45", "Bortberga");
        Address address3 = new Address("Hemvägen 1", "123 47", "Hemstad");
        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);

        user1.setAddress(address1);
        user2.setAddress(address2);
        user3.setAddress(address3);

//---------------------------------------------------

        //Hitta AppUser i specifik stad.
        List<Address> cityResult = addressRepository.findByCityContainsIgnoreCase("hemstad");
        cityResult.forEach(System.out::println);

        //Hitta AppUser på specifik adress.
        List<Address> streetResult = addressRepository.findByStreetContainsIgnoreCase("orta");
        streetResult.forEach(System.out::println);

        //Hitta AppUser med specifik email.
        AppUser appUser = appUserRepository.findByEmailContainsIgnoreCase("peter@gmail.com");
        System.out.println(appUser);

                    //Hitta AppUser med specifikt namn.
                    //List<AppUser> nameResult = appUserRepository.findByNameContainsIgnoreCase("peter");
                    //nameResult.forEach(System.out::println);

        //Hitta AppUser med specifik email OCH lösenord.
        List<AppUser> emailPasswordResult = appUserRepository.findByEmailAndPassword("lisa@gmail.com", "a1b7");
        emailPasswordResult.forEach(System.out::println);

                    //Hitta AppUsers som har namn som innehåller en String.
                    List<AppUser> nameContainResult = appUserRepository.findByNameContaining("rs");
                    nameContainResult.forEach(System.out::println);

        //Hitta AppUsers som bor på samma address.
        List<AppUser> addressResult = appUserRepository.findByAddressStreet("Hemvägen 1");
        addressResult.forEach(System.out::println);

        //Hitta AppUsers som bor i samma stad.
        List<AppUser> userCityResult = appUserRepository.findByAddressCityContainsIgnoreCase("hemStad");
        userCityResult.forEach(System.out::println);

    }
}
