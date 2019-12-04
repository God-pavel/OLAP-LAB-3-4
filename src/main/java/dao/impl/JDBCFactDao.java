package dao.impl;

import dao.FactDao;
import db.ConnectionPoolHandler;
import entities.Fact;
import entities.FactDBRow;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;

public class JDBCFactDao implements FactDao {

//    yearID(jdbcDimensionDao.getYearIdByValue(fact.getDate().getYear()))
//            .monthID(jdbcDimensionDao.getMonthIdByValue(fact.getDate().getMonth()))
//            .dayID(jdbcDimensionDao.getDayIdByValue(fact.getDate().getDayOfMonth()))
//            .countryID(jdbcDimensionDao.getCountryIdByValue(fact.getCountry()))
//            .cityID(jdbcDimensionDao.getCityIdByValue(fact.getCity()))
//            .typeOfDeathID(jdbcDimensionDao.getTypeOfDeathIdByValue(fact.getTypeOfDeath()))
//            .airportNameID(jdbcDimensionDao.getAirportNameIdByValue(fact.getAirportName()))
//            .injurySeverityID(jdbcDimensionDao.getInjurySeverityIdByValue(fact.getInjurySeverity()))
//            .killedByTerroristsAttack(fact.getKilledByTerroristsAttack())
//            .injuredByTerroristsAttack(fact.getInjuredByTerroristsAttack())
//            .nameOfJournalist(fact.getNameOfJournalist())
//            .nationalityOfJournalist(fact.getNationalityOfJournalist())
//            .aircraftCategory(fact.getAircraftCategory())
//            .model(fact.getModel())

    private final String INSERT_INTO_FACTS = "insert into facts(\n" +
            "yearId, \n" +
            "monthId, \n" +
            "dayId, \n" +
            "countryId, \n" +
            "cityId, \n" +
            "typeOfDeathId, \n" +
            "airportNameId, \n" +
            "injurySeverityId, \n" +
            "killedByTerroristsAttack, \n" +
            "injuredByTerroristsAttack, \n" +
            "nameOfJournalist, \n" +
            "nationalityOfJournalist, \n" +
            "aircraftCategory, \n" +
            "model) \n" +
            "values(?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void createFact(FactDBRow fact) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_FACTS)) {

            insertParameters(preparedStatement, fact);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAllFacts(List<FactDBRow> facts) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_FACTS)) {

            for (FactDBRow fact : facts) {
                insertParameters(preparedStatement, fact);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertParameters(PreparedStatement preparedStatement, FactDBRow fact) throws SQLException{
        preparedStatement.setLong(1, fact.getYearID());
        preparedStatement.setLong(2, fact.getMonthID());
        preparedStatement.setLong(3, fact.getDayID());
        preparedStatement.setLong(4, fact.getCountryID());
        preparedStatement.setLong(5, fact.getCityID());
        preparedStatement.setLong(6, fact.getTypeOfDeathID());
        preparedStatement.setLong(7, fact.getAirportNameID());
        preparedStatement.setLong(8, fact.getInjurySeverityID());
        preparedStatement.setLong(9, fact.getKilledByTerroristsAttack());
        preparedStatement.setLong(10, fact.getInjuredByTerroristsAttack());
        preparedStatement.setString(11, fact.getNameOfJournalist());
        preparedStatement.setString(12, fact.getNationalityOfJournalist());
        preparedStatement.setString(13, fact.getAircraftCategory());
        preparedStatement.setString(14, fact.getModel());

    }
}
