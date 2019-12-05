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
        try {

            while ((nextLine = reader.readNext()) != null) {
                facts.add(Fact.builder()
                        .date(parseJournalistDate(nextLine[1]))
                        .country(nextLine[4])
                        .nameOfJournalist(nextLine[2])
                        .nationalityOfJournalist(nextLine[6])
                        .typeOfDeath(nextLine[13])
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            return null;
        }

    }

}