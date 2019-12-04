package mappers;

import entities.Fact;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TerroristsMapper extends DataMapper {

    public TerroristsMapper() throws IOException {
        super("./src/main/resources/attacks_data.csv");
    }

    @Override
    public List<Fact> putFactsFromData(List<Fact> facts) {
        String[] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                facts.add(Fact.builder()
                        .date(LocalDate.parse(nextLine[1]))
                        .country(nextLine[2])
                        .city(nextLine[3])
                        .killedByTerroristsAttack(Long.parseLong(nextLine[4]))
                        .injuredByTerroristsAttack(Long.parseLong(nextLine[5]))
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return facts;
    }

}
