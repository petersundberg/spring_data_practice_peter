package se.ecutb.spring_data_practice.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.spring_data_practice.entity.AppUser;
import se.ecutb.spring_data_practice.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends CrudRepository<Car, String> {

    Car findByRegNumberContainsIgnoreCase(String regNumber);

    //List<Car> findByCarStatusCodeContainsIgnoreCase(String statusCode);


    //Hitta alla bilar äldre än ett visst datum.
    List<Car> findByRegDateBefore(LocalDate regDate);


    //Hitta alla bilar nyare än ett visst datum.
    List<Car> findByRegDateAfter(LocalDate regDate);


    //Hitta alla bilar registrerade mellan två datum.




}
