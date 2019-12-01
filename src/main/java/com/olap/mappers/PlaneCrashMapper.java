package com.olap.mappers;

import au.com.bytecode.opencsv.CSVReader;
import com.olap.entities.Fact;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PlaneCrashMapper{

    private CSVReader reader;

    public PlaneCrashMapper() throws IOException {
        reader = new CSVReader(new FileReader("./src/main/resources/AviationData.csv"), ',', '"', 1);
    }

    public List<Fact> getFactsFromData(List<Fact> facts) throws IOException {
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[3]);
            facts.add(Fact.builder()
                    .date(LocalDate.parse(nextLine[3]))
                    .country(nextLine[4])
                    .airportName(nextLine[9])
                    .injurySeverity(nextLine[10])
                    .aircraftCategory(nextLine[12])
                    .build());
        }
        return facts;
    }

}
