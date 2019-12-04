package entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;


@Data
@Builder
@ToString
public class Fact {

    //Dimensions
    LocalDate date;
    String country; //3 dimensions
    String city;
    String typeOfDeath;
    String airportName;
    String injurySeverity;

    //Fact fields
    long killedByTerroristsAttack;
    long injuredByTerroristsAttack;

    String nameOfJournalist;
    String nationalityOfJournalist;

    String aircraftCategory;
    String model;
}
