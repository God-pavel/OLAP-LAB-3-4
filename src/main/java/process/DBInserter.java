package process;

import dao.impl.JDBCDimensionDao;
import dao.impl.JDBCFactDao;
import entities.Fact;
import entities.FactDBRow;
import mappers.DataMapper;

import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBInserter {

    private List<DataMapper> mappers;
    private List<Fact> facts;

    private JDBCFactDao jdbcFactDao;
    private JDBCDimensionDao jdbcDimensionDao;

    public DBInserter(DataMapper... args) {
        mappers = new ArrayList<>();
        facts = new ArrayList<>();
        mappers.addAll(Arrays.asList(args));
        jdbcFactDao = new JDBCFactDao();
        jdbcDimensionDao = new JDBCDimensionDao();

    }

    public void execute() {
        System.out.println("Start parsing csv files ...");
        mappers.forEach(mapper -> mapper.putFactsFromData(facts));
        fillDimensionTables();
        System.out.println("Start filling  facts table ...");
        facts.forEach(this::insertFact);
        System.out.println("DB inserting was completed!");
    }

    private void fillDimensionTables() {
        for (Fact fact : facts) {
            if (fact.getDate() != null) {
                jdbcDimensionDao.createYearDimension(fact.getDate().getYear());
                jdbcDimensionDao.createMonthDimension(fact.getDate().getMonth());
                jdbcDimensionDao.createDayDimension(fact.getDate().getDayOfMonth());
            }
            if (fact.getCity() != null) {
                jdbcDimensionDao.createCityDimension(fact.getCity());
            }
            if (fact.getCountry() != null) {
                jdbcDimensionDao.createCountryDimension(fact.getCountry());
            }
            if (fact.getTypeOfDeath() != null) {
                jdbcDimensionDao.createTypeOfDeathDimension(fact.getTypeOfDeath());
            }
            if (fact.getAirportName() != null) {
                jdbcDimensionDao.createAirportNameDimension(fact.getAirportName());
            }
            if (fact.getInjurySeverity() != null) {
                jdbcDimensionDao.createInjurySeverityDimension(fact.getInjurySeverity());
            }
        }

    }

    private void insertFact(Fact fact) {
        System.out.println(fact);
        int year = 0;
        int day = 0;
        Month month = null;
        if(fact.getDate()!=null) {
            year = fact.getDate().getYear();
            month = fact.getDate().getMonth();
            day = fact.getDate().getDayOfMonth();
        }
        jdbcFactDao.createFact(FactDBRow.builder()
                .yearID(jdbcDimensionDao.getYearIdByValue(year))
                .monthID(jdbcDimensionDao.getMonthIdByValue(month))
                .dayID(jdbcDimensionDao.getDayIdByValue(day))
                .countryID(jdbcDimensionDao.getCountryIdByValue(fact.getCountry()))
                .cityID(jdbcDimensionDao.getCityIdByValue(fact.getCity()))
                .typeOfDeathID(jdbcDimensionDao.getTypeOfDeathIdByValue(fact.getTypeOfDeath()))
                .airportNameID(jdbcDimensionDao.getAirportNameIdByValue(fact.getAirportName()))
                .injurySeverityID(jdbcDimensionDao.getInjurySeverityIdByValue(fact.getInjurySeverity()))
                .killedByTerroristsAttack(fact.getKilledByTerroristsAttack())
                .injuredByTerroristsAttack(fact.getInjuredByTerroristsAttack())
                .nameOfJournalist(fact.getNameOfJournalist())
                .nationalityOfJournalist(fact.getNationalityOfJournalist())
                .aircraftCategory(fact.getAircraftCategory())
                .model(fact.getModel())
                .build());

    }

}
