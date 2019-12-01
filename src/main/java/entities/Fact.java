package entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;


@Data
@Builder
@ToString
public class Fact {
    LocalDate date;
    String country;

    long killedByTerroristsAttack;
    long injuredByTerroristsAttack;

    String nameOfJournalist;
    String nationalityOfJournalist;
    String typeOfDeath;

    String airportName;
    String injurySeverity;
    String aircraftCategory;
}
