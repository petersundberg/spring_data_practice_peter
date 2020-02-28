package se.ecutb.spring_data_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.spring_data_practice.data.AddressRepository;
import se.ecutb.spring_data_practice.data.AppUserRepository;
import se.ecutb.spring_data_practice.data.CarRepository;
import se.ecutb.spring_data_practice.data.StatusRepository;
import se.ecutb.spring_data_practice.entity.Address;
import se.ecutb.spring_data_practice.entity.AppUser;
import se.ecutb.spring_data_practice.entity.Car;
import se.ecutb.spring_data_practice.entity.Status;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class CommandLine implements CommandLineRunner {

    private StatusRepository statusRepository;
    private AddressRepository addressRepository;
    private AppUserRepository appUserRepository;
    private CarRepository carRepository;

    @Autowired
    public CommandLine(StatusRepository statusRepository, AddressRepository addressRepository, AppUserRepository appUserRepository, CarRepository carRepository) {
        this.statusRepository = statusRepository;
        this.addressRepository = addressRepository;
        this.appUserRepository = appUserRepository;
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        AppUser user1 = new AppUser("peter@gmail.com", "Peter", "9999", null);
        AppUser user2 = new AppUser("lars@gmail.com", "Lars", "abcd", null);
        AppUser user3 = new AppUser("lisa@gmail.com", "Lisa", "a1b7", null);
        AppUser user4 = new AppUser("anna@gmail.com", "Anna", "pw12", null);
        appUserRepository.save(user1);
        appUserRepository.save(user2);
        appUserRepository.save(user3);
        appUserRepository.save(user4);


        Address address1 = new Address("Hemvägen 1", "123 45", "Hemstad");
        Address address2 = new Address("Bortagatan 25", "789 45", "Bortberga");
        Address address3 = new Address("Hemvägen 1", "123 47", "Hemstad");
        Address address4 = new Address("Bortastället 125", "456 78", "BortiStan");

        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);
        addressRepository.save(address4);

//        user1.setAddress(address1);
//        user2.setAddress(address2);
//        user3.setAddress(address3);
//        user4.setAddress(address4);

        Car car1 = new Car("abc123", "VW", "Golf", LocalDate.parse("2016-01-01"));
        Car car2 = new Car("qwe125", "Audi", "A4", LocalDate.parse("2017-01-01"));
        Car car3 = new Car("ert345", "Mercedes", "SL", LocalDate.parse("2018-01-01"));
        Car car4 = new Car("dfg567", "BMW", "M3", LocalDate.parse("2019-01-01"));
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);

        List<Car> carList1 = new ArrayList<>();
        carList1.add(car1);
        carList1.add(car2);
//            List<Car> carList2 = new ArrayList<>();
//            carList2.add(car3);
//            carList2.add(car4);

        Status status1 = new Status("Körförbud");
        Status status2 = new Status("Går ej att styra");
        Status status3 = new Status("Bromsar ur funktion");
        Status status4 = new Status("Varningstriangel saknas");

        user1.setAddress(address1);
        user1.setOwnedCars(carList1);
        car1.addStatusCode(status1);
        car1.addStatusCode(status2);

        user2.setAddress(address2);
        user2.addCar(car3);
        car3.addStatusCode(status3);

        user3.setAddress(address3);
        user3.addCar(car4);
        car4.addStatusCode(status4);

        user4.setAddress(address4);

        appUserRepository.save(user1);
        appUserRepository.save(user2);
        appUserRepository.save(user3);
        appUserRepository.save(user4);

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
//        List<AppUser> nameResult = appUserRepository.findByNameContainsIgnoreCase("peter");
//        nameResult.forEach(System.out::println);


        //Hitta AppUser med specifik email OCH lösenord.
        List<AppUser> emailPasswordResult = appUserRepository.findByEmailAndPassword("lisa@gmail.com", "a1b7");
        emailPasswordResult.forEach(System.out::println);

                    //Hitta AppUsers som har namn som innehåller en String.
                    List<AppUser> nameContainResult = appUserRepository.findByNameContaining("rs");
                    nameContainResult.forEach(System.out::println);

        //Hitta AppUsers som bor på samma address.
        List<AppUser> addressResult = appUserRepository.findByAddressStreetContainsIgnoreCase("Hemvägen 1");
        addressResult.forEach(System.out::println);

        //Hitta AppUsers som bor i samma stad.
        List<AppUser> userCityResult = appUserRepository.findByAddressCityContainsIgnoreCase("hemStad");
        userCityResult.forEach(System.out::println);


        //CarRepository:
        //Hitta en bil med specifikt registreringsnummer.
        Car carRegNumberResult = carRepository.findByRegNumberContainsIgnoreCase("qwe125");
        System.out.println(carRegNumberResult);


        //Hitta alla bilar som har en viss statuskod.
        List<Car> carStatusResult = carRepository.findByStatusCodesStatusCodeContainsIgnoreCase("Varningstriangel saknas");
        carStatusResult.forEach(System.out::println);


        //Hitta alla bilar nyare än ett visst datum.
        List<Car> carOlderThan = carRepository.findByRegDateBefore(LocalDate.parse("2017-01-01"));
        carOlderThan.forEach(System.out::println);

        //Hitta alla bilar nyare än ett visst datum.
        List<Car> carNewerThan = carRepository.findByRegDateAfter(LocalDate.parse("2017-01-01"));
        carNewerThan.forEach(System.out::println);

        //Hitta alla bilar registrerade mellan två datum.
        List<Car> carBetweenDates = carRepository.findByRegDateBetween (LocalDate.parse("2016-12-25"), LocalDate.parse("2018-02-01"));
        carBetweenDates.forEach(System.out::println);


        System.out.println(user1.getOwnedCars().toString());


    }
}
