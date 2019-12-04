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
        try {
            while ((nextLine = reader.readNext()) != null) {
                System.out.println(nextLine[3]);
                facts.add(Fact.builder()
                        .date(LocalDate.parse(nextLine[3]))
                        .country(nextLine[4])
                        .airportName(nextLine[9])
                        .injurySeverity(nextLine[10])
                        .aircraftCategory(nextLine[12])
                        .model(nextLine[15])
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return facts;
    }

}
