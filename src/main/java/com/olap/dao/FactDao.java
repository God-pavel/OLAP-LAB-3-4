package com.olap.dao;

import com.olap.entities.Fact;

import java.util.List;

public interface FactDao {
    void createFact(Fact fact);
    void createAllFacts(List<Fact> facts);
}
