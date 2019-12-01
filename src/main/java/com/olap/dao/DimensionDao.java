package com.olap.dao;

public interface DimensionDao {

    void createDateDimension(String name);
    void createCountryDimension(String name);

}
