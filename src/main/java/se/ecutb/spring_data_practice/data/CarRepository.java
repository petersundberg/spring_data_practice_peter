package se.ecutb.spring_data_practice.data;

import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.CrudRepository;
import se.ecutb.spring_data_practice.entity.AppUser;
import se.ecutb.spring_data_practice.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends CrudRepository<Car, String> {

    //Hitta en bil med specifikt registreringsnummer.
    Car findByRegNumberContainsIgnoreCase(String regNumber);

//----------------------------

    //Hitta alla bilar som har en viss status kod.
    List<Car> findByStatusCodesStatusCodeContainsIgnoreCase(String statusCode);

//----------------------------

    //Hitta alla bilar äldre än ett visst datum.
    List<Car> findByRegDateBefore(LocalDate regDate);


    //Hitta alla bilar nyare än ett visst datum.
    List<Car> findByRegDateAfter(LocalDate regDate);


    //Hitta alla bilar registrerade mellan två datum.
    List<Car> findByRegDateBetween(LocalDate regDate1, LocalDate regDate2);




}
