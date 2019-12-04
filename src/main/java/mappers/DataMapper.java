package mappers;

import au.com.bytecode.opencsv.CSVReader;
import entities.Fact;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class DataMapper {

    CSVReader reader;

    public DataMapper(String filepath) throws IOException {
        reader = new CSVReader(new FileReader(filepath), ',', '"', 1);
    }

    public abstract List<Fact> putFactsFromData(List<Fact> facts);
}
