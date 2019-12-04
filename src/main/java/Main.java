import mappers.JournalistsMapper;
import mappers.PlaneCrashMapper;
import mappers.TerroristsMapper;
import process.DBInserter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        DBInserter dbInserter = new DBInserter(new TerroristsMapper(), new JournalistsMapper(), new PlaneCrashMapper());
        dbInserter.execute();
    }
}
