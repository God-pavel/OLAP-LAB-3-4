import entities.Fact;
import mappers.JournalistsMapper;
import mappers.PlaneCrashMapper;
import mappers.TerroristsMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        TerroristsMapper tm = new TerroristsMapper();
        JournalistsMapper jm = new JournalistsMapper();
        PlaneCrashMapper pm = new PlaneCrashMapper();
        List<Fact> facts = new ArrayList<>();
        System.out.println(pm.getFactsFromData(facts));
    }
}
