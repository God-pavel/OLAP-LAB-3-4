package com.olap.dao.impl;

import com.olap.dao.FactDao;
import com.olap.db.ConnectionPoolHandler;
import com.olap.entities.Fact;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;

public class JDBCFactDao implements FactDao {

    private final String INSERT_INTO_FACTS = "insert into facts(\n" +
            "date, \n" +
            "country, \n" +
            "killedByTerroristsAttack, \n" +
            "injuredByTerroristsAttack, \n" +
            "nameOfJournalist, \n" +
            "nationalityOfJournalist, \n" +
            "typeOfDeath, \n" +
            "airportName, \n" +
            "injurySeverity, \n" +
            "aircraftCategory) \n" +
            "values(?, ?, ? ,?, ?, ?, ?, ?, ?, ?)";

    public void createFact(Fact fact) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_FACTS)) {

            insertParameters(preparedStatement, fact);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAllFacts(List<Fact> facts) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_FACTS)) {

            for (Fact fact : facts) {
                insertParameters(preparedStatement, fact);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertParameters(PreparedStatement preparedStatement, Fact fact) throws SQLException{
        preparedStatement.setDate(1, (Date)Date.from(fact.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        preparedStatement.setString(2, fact.getCountry());
        preparedStatement.setLong(3, fact.getKilledByTerroristsAttack());
        preparedStatement.setLong(4, fact.getInjuredByTerroristsAttack());
        preparedStatement.setString(5, fact.getNameOfJournalist());
        preparedStatement.setString(6, fact.getNationalityOfJournalist());
        preparedStatement.setString(7, fact.getTypeOfDeath());
        preparedStatement.setString(8, fact.getAirportName());
        preparedStatement.setString(9, fact.getInjurySeverity());
        preparedStatement.setString(10, fact.getAircraftCategory());
    }
}
