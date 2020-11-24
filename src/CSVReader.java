import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    List<List<String>> records;
    CSVReader(String filepath) {
        this.records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println("Read csv file failed");
        }
    }
}
