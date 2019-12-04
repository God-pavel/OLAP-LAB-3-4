package dao;

import entities.FactDBRow;

import java.util.List;

public interface FactDao {
    void createFact(FactDBRow fact);
    void createAllFacts(List<FactDBRow> facts);
}
