package mappers;

import entities.Fact;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class JournalistsMapper extends DataMapper {

    public JournalistsMapper() throws IOException {
        super("./src/main/resources/cpj.csv");
    }

    @Override
    public List<Fact> putFactsFromData(List<Fact> facts) {
        String[] nextLine;
        System.out.println("-------------------Journalist--------------------");
        try {
            while ((nextLine = reader.readNext()) != null) {

//                if (facts.size() < 200) {
                    Fact fact = Fact.builder()
                            .date(parseJournalistDate(nextLine[1]))
                            .country(nextLine[4])
                            .nameOfJournalist(nextLine[2])
                            .nationalityOfJournalist(nextLine[6])
                            .typeOfDeath(nextLine[13])
                            .build();
                    facts.add(fact);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return facts;
    }

    private LocalDate parseJournalistDate(String stringDate) {
        try {
            String[] partsOfDate = stringDate.split(",");
            String[] monthAndDay = partsOfDate[0].split(" ");
            if (monthAndDay.length == 3) {
                return LocalDate.of(Integer.parseInt(partsOfDate[1].split(" ")[1]), Month.valueOf(monthAndDay[0].toUpperCase()), Integer.parseInt(monthAndDay[2]));
            } else {
                return LocalDate.of(Integer.parseInt(partsOfDate[1].split(" ")[1]), Month.valueOf(monthAndDay[0].toUpperCase()), Integer.parseInt(monthAndDay[1].split(" ")[0]));
            }
        } catch (Exception e) {
            return null;
        }

    }

}