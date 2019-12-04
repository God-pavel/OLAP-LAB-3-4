package dao;

import java.time.Month;

public interface DimensionDao {

    void createYearDimension(int year);

    void createMonthDimension(Month month);

    void createCityDimension(String city);

    void createCountryDimension(String country);

    void createDayDimension(int dayOfMonth);

    void createTypeOfDeathDimension(String typeOfDeath);

    void createAirportNameDimension(String airportName);

    void createInjurySeverityDimension(String injurySeverity);

    boolean checkIfExists(String dimensionName, String value);

    long getYearIdByValue(int year);

    long getMonthIdByValue(Month month);

    long getDayIdByValue(int dayOfMonth);

    long getCountryIdByValue(String country);

    long getCityIdByValue(String city);

    long getTypeOfDeathIdByValue(String typeOfDeath);

    long getAirportNameIdByValue(String airportName);

    long getInjurySeverityIdByValue(String injurySeverity);
}
