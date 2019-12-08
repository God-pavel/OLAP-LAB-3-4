package mappers;

import entities.Fact;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PlaneCrashMapper extends DataMapper {

    public PlaneCrashMapper() throws IOException {
        super("./src/main/resources/AviationData.csv");
    }

    @Override
    public List<Fact> putFactsFromData(List<Fact> facts) {
        String[] nextLine;
        System.out.println("-------------------Planes--------------------");
        try {
            while ((nextLine = reader.readNext()) != null) {

//                if (facts.size() < 300) {
                    Fact fact =Fact.builder()
                            .date(LocalDate.parse(nextLine[3]))
                            .country(nextLine[4])
                            .airportName(nextLine[9])
                            .injurySeverity(nextLine[10])
                            .aircraftCategory(nextLine[12])
                            .model(nextLine[15])
                            .build();
                    facts.add(fact);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return facts;
    }

}
