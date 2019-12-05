package dao.impl;

import dao.DimensionDao;
import db.ConnectionPoolHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;

public class JDBCDimensionDao implements DimensionDao {

    private final String INSERT_INTO = "insert into \n";
    private final String VALUES = "(val) values (?)";
    private final String SELECT = "select * \n";
    private final String FROM = " from \n";
    private final String WHERE = " where val=";
    private final String INJURY_SEVERITY_DIM = "injury_severity_dimension";
    private final String AIRPORT_NAME_DIM = "airport_name_dimension";
    private final String TYPE_OF_DEATH_DIM = "type_of_death_dimension";
    private final String DAY_DIM = "day_dimension";
    private final String CITY_DIM = "city_dimension";
    private final String MONTH_DIM = "month_dimension";
    private final String COUNTRY_DIM = "country_dimension";
    private final String YEAR_DIM = "year_dimension";
    private final String ID_QUERY = "select id from ";
    private final String WHERE_VALUE = " where val=";


    public void createYearDimension(int year) {
        if(!checkIfExists(YEAR_DIM, Integer.toString(year))) {
            try (Connection connection = ConnectionPoolHandler.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                         YEAR_DIM + VALUES)) {
                preparedStatement.setString(1, Integer.toString(year));
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createMonthDimension(Month month) {
        if(!checkIfExists(MONTH_DIM, month.toString())) {
            try (Connection connection = ConnectionPoolHandler.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                         MONTH_DIM + VALUES)) {
                preparedStatement.setString(1, month.toString());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createCityDimension(String city) {
        System.out.println(city);
        if(!checkIfExists(CITY_DIM, city)) {
            try (Connection connection = ConnectionPoolHandler.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                         CITY_DIM + VALUES)) {
                preparedStatement.setString(1, city);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createCountryDimension(String country) {
        if(!checkIfExists(COUNTRY_DIM, country)) {
            try (Connection connection = ConnectionPoolHandler.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                         COUNTRY_DIM + VALUES)) {
                preparedStatement.setString(1, country);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createDayDimension(int dayOfMonth) {
        if(!checkIfExists(DAY_DIM, Integer.toString(dayOfMonth))) {
            try (Connection connection = ConnectionPoolHandler.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                         DAY_DIM + VALUES)) {
                preparedStatement.setString(1, Integer.toString(dayOfMonth));
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createTypeOfDeathDimension(String typeOfDeath) {
        if(!checkIfExists(TYPE_OF_DEATH_DIM, typeOfDeath)) {
            try (Connection connection = ConnectionPoolHandler.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                         TYPE_OF_DEATH_DIM + VALUES)) {
                preparedStatement.setString(1, typeOfDeath);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createAirportNameDimension(String airportName) {
        if(!checkIfExists(AIRPORT_NAME_DIM, airportName)) {
            try (Connection connection = ConnectionPoolHandler.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                         AIRPORT_NAME_DIM + VALUES)) {
                preparedStatement.setString(1, airportName);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createInjurySeverityDimension(String injurySeverity) {
        if(!checkIfExists(INJURY_SEVERITY_DIM, injurySeverity)) {
            try (Connection connection = ConnectionPoolHandler.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                         INJURY_SEVERITY_DIM + VALUES)) {
                preparedStatement.setString(1, injurySeverity);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean checkIfExists(String dimensionName, String value) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT
                     + FROM + dimensionName + WHERE + "\'" + value + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public long getYearIdByValue(int year) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY
                     + YEAR_DIM + WHERE_VALUE + "\'" + year + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long getMonthIdByValue(Month month) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY
                     + MONTH_DIM + WHERE_VALUE + "\'" + month + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long getDayIdByValue(int dayOfMonth) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY
                     + DAY_DIM + WHERE_VALUE + "\'" + dayOfMonth + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long getCountryIdByValue(String country) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY
                     +  COUNTRY_DIM + WHERE_VALUE + "\'" + country + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long getCityIdByValue(String city) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY
                     + CITY_DIM + WHERE_VALUE + "\'" + city + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long getTypeOfDeathIdByValue(String typeOfDeath) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY
                     + TYPE_OF_DEATH_DIM + WHERE_VALUE + "\'" + typeOfDeath + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long getAirportNameIdByValue(String airportName) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY
                     + AIRPORT_NAME_DIM + WHERE_VALUE + "\'" + airportName + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long getInjurySeverityIdByValue(String injurySeverity) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY
                     + INJURY_SEVERITY_DIM + WHERE_VALUE + "\'" + injurySeverity + "\'")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
