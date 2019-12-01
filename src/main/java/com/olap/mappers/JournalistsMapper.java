package com.olap.mappers;

import au.com.bytecode.opencsv.CSVReader;
import com.olap.entities.Fact;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class JournalistsMapper {

    private CSVReader reader;

    public JournalistsMapper() throws IOException {
        reader = new CSVReader(new FileReader("./src/main/resources/cpj.csv"), ',', '"', 1);
    }

    public List<Fact> getFactsFromData(List<Fact> facts) throws IOException {
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            facts.add(Fact.builder()
                    .date(parseJournalistDate(nextLine[1]))
                    .country(nextLine[4])
                    .nameOfJournalist(nextLine[2])
                    .nationalityOfJournalist(nextLine[6])
                    .typeOfDeath(nextLine[13])
                    .build());
        }
        return facts;
    }

    private LocalDate parseJournalistDate(String stringDate) {
        try {
            String[] partsOfDate = stringDate.split(",");
            System.out.println(partsOfDate[0]);
            String[] monthAndDay = partsOfDate[0].split(" ");
            if (monthAndDay.length == 3) {
                System.out.println(monthAndDay[2]);
                return LocalDate.of(Integer.parseInt(partsOfDate[1].split(" ")[1]), Month.valueOf(monthAndDay[0].toUpperCase()), Integer.parseInt(monthAndDay[2]));
            } else {
                System.out.println(monthAndDay[1]);
                return LocalDate.of(Integer.parseInt(partsOfDate[1].split(" ")[1]), Month.valueOf(monthAndDay[0].toUpperCase()), Integer.parseInt(monthAndDay[1].split(" ")[0]));
            }
        }catch (Exception e){
            return null;
        }

    }

}