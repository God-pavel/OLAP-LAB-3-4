package com.olap.dao.impl;

import com.olap.dao.DimensionDao;
import com.olap.db.ConnectionPoolHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDimensionDao implements DimensionDao {

    private final String INSERT_INTO = "insert into \n";
    private final String VALUES = "(name) values (?)";

    @Override
    public void createDateDimension(String name) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                     "date_dimension" + VALUES)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCountryDimension(String name) {
        try (Connection connection = ConnectionPoolHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO +
                     "country_dimension" + VALUES)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
