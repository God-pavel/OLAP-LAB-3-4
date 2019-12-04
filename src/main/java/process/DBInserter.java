package process;

import dao.impl.JDBCDimensionDao;
import dao.impl.JDBCFactDao;
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

    private JDBCFactDao jdbcFactDao;
    private JDBCDimensionDao jdbcDimensionDao;

    public DBInserter(DataMapper... args) {
        mappers = new ArrayList<>();
        facts = new ArrayList<>();
        mappers.addAll(Arrays.asList(args));
        jdbcFactDao = new JDBCFactDao();
        jdbcDimensionDao = new JDBCDimensionDao();

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

    private void fillDimensionTables() {
        for (Fact fact : facts) {
            jdbcDimensionDao.createYearDimension(fact.getDate().getYear());
            jdbcDimensionDao.createMonthDimension(fact.getDate().getMonth());
            jdbcDimensionDao.createDayDimension(fact.getDate().getDayOfMonth());
            jdbcDimensionDao.createCityDimension(fact.getCity());
            jdbcDimensionDao.createCountryDimension(fact.getCountry());
            jdbcDimensionDao.createTypeOfDeathDimension(fact.getTypeOfDeath());
            jdbcDimensionDao.createAirportNameDimension(fact.getAirportName());
            jdbcDimensionDao.createInjurySeverityDimension(fact.getInjurySeverity());
        }
    }

    private void insertFact(Fact fact) {
        jdbcFactDao.createFact(FactDBRow.builder()
                .yearID(jdbcDimensionDao.getYearIdByValue(fact.getDate().getYear()))
                .monthID(jdbcDimensionDao.getMonthIdByValue(fact.getDate().getMonth()))
                .dayID(jdbcDimensionDao.getDayIdByValue(fact.getDate().getDayOfMonth()))
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
