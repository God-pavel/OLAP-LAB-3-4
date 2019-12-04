package entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@Builder
@ToString
public class FactDBRow {

    //Dimensions
    long yearID;
    long monthID;
    long dayID;
    long countryID;
    long cityID;
    long typeOfDeathID;
    long airportNameID;
    long injurySeverityID;

    //Fact fields
    long killedByTerroristsAttack;
    long injuredByTerroristsAttack;

    String nameOfJournalist;
    String nationalityOfJournalist;

    String aircraftCategory;
    String model;
}
