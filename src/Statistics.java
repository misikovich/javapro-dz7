import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private final Path path;
    private final List<String> statistics;

    public Statistics(Path path) {
        this.path = Paths.get(path.toString() + "_statistic.txt");
        statistics = new ArrayList<>();

    }

    public void addStatistics(String stat) {
        statistics.add(stat);
        writeToFile();
    }

    public String getStatistics() {
        return statistics.isEmpty() ? "" : String.join("\n", statistics);
    }

    private void writeToFile() {
        File statFile = path.toFile();
        try {
           statFile.createNewFile();
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(statFile))) {
                bufferedWriter.write(getStatistics());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Could not create statistics");
        }
    }
}
