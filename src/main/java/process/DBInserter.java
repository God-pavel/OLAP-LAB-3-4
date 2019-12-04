package process;

import entities.Fact;
import entities.FactDBRow;
import mappers.DataMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBInserter {

    private List<DataMapper> mappers;
    private List<Fact> facts;

    public DBInserter(DataMapper... args) {
        mappers = new ArrayList<>();
        facts = new ArrayList<>();
        mappers.addAll(Arrays.asList(args));
    }

    public void execute() throws IOException {
        System.out.println("Start parsing csv files ...");
        mappers.forEach(mapper -> mapper.putFactsFromData(facts));
        System.out.println("Start filling dimensions tables ...");
        fillDimensionTables();
        System.out.println("Start filling  facts table ...");
        facts.forEach(this::insertFact);
        System.out.println("DB inserting was completed!");
    }

    //Значения в таблицах должны быть уникальными
    private void fillDimensionTables() {
        for (Fact fact : facts) {
            YearRepository.save(fact.getDate().getYear());
            MonthRepository.save(fact.getDate().getMonth());
            DayRepository.save(fact.getDate().getDayOfMonth());
            CityRepository.save(fact.getDate().getDayOfMonth());
            TypeOfDeathRepository.save(fact.getTypeOfDeath());
            AiportNameRepository.save(fact.getAirportName());
            InjurySeverityRepository.svae(fact.getInjurySeverity());
        }
    }

    private void insertFact(Fact fact) {
        FactRepository.save(FactDBRow.builder()
                .yearID(YearRepository.getID(fact.getDate().getYear()))
                .monthID(MonthRepository.getID(fact.getDate().getMonth()))
                .dayID(DayRepository.getID(fact.getDate().getDayOfMonth()))
                .countryID(CountryRepository.getID(fact.getCountry()))
                .cityID(CityRepository.getID(fact.getCity()))
                .typeOfDeathID(TypeOfDeathRepository.getID(fact.getTypeOfDeath()))
                .airportNameID(AirportNameRepository.getID(fact.getAirportName()))
                .injurySeverityID(InjurySeverityRepository.getID(fact.getInjurySeverity()))
                .killedByTerroristsAttack(fact.getKilledByTerroristsAttack())
                .injuredByTerroristsAttack(fact.getInjuredByTerroristsAttack())
                .nameOfJournalist(fact.getNameOfJournalist())
                .nationalityOfJournalist(fact.getNationalityOfJournalist())
                .aircraftCategory(fact.getAircraftCategory())
                .model(fact.getModel())
                .build()
        );
    }

}
