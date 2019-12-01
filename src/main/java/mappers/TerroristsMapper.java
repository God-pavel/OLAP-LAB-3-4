package mappers;

import au.com.bytecode.opencsv.CSVReader;
import entities.Fact;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TerroristsMapper {

    private CSVReader reader;

    public TerroristsMapper() throws IOException {
        reader = new CSVReader(new FileReader("./src/main/resources/attacks_data.csv"), ',', '"', 1);
    }

    public List<Fact> getFactsFromData(List<Fact> facts) throws IOException {
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            facts.add(Fact.builder()
                    .date(LocalDate.parse(nextLine[1]))
                    .country(nextLine[2])
                    .killedByTerroristsAttack(Long.parseLong(nextLine[4]))
                    .injuredByTerroristsAttack(Long.parseLong(nextLine[5]))
                    .build());
        }
        return facts;
    }

}
