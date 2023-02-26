import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Book {
    private static final String directory = Paths.get(System.getProperty("user.dir"), "/src/books").toString();
    private String bookName;
    private final Path path;
    private final StringBuilder text;

    private final StringBuilder statistics;

    public Book(String p) {
        path = Paths.get(directory, p);
        text = new StringBuilder();
        statistics = new StringBuilder();
        bookName = p;

        try (BufferedReader bookReader =
                     new BufferedReader(new FileReader(path.toFile()))) {
            bookReader.lines().forEach(text::append);
        } catch (FileNotFoundException e) {
            System.out.printf("Book '%s' was not found\n", bookName);
            statistics.append("Book was not found\n");
        } catch (IOException e) {
            statistics.append(e.getMessage());
            e.printStackTrace();
        }
        finally {
            writeStatsToFile();
        }
    }

    public String getText() {
        return text.toString();
    }

    public String getBookName() {
        return bookName;
    }

    private String[] getTextAsArray() {
        if (text.isEmpty()) return new String[0];
        return text.toString().split("[ \n]");
    }

    public List<String> countPopularWords(int wordLengthThreshold) {
        Map<String, Long> wordCount = Arrays.stream(getTextAsArray())
                .filter(s -> s.length() > wordLengthThreshold)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<String> strings = wordCount.entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry<String, Long>::getValue).reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();

        statistics.append(strings).append("\n");
        writeStatsToFile();
        return strings;
    }

    public long countUniqueWords() {
        long uniqueCount = Arrays.stream(getTextAsArray())
                .map(String::toLowerCase)
                .distinct()
                .count();
        statistics.append(uniqueCount).append("\n");
        writeStatsToFile();
        return uniqueCount;
    }

    public void writeStatsToFile() {
        File statFile = Paths.get(directory, path.getFileName().toString() + "_statistic.txt").toFile();
        try {
            statFile.createNewFile();
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(statFile))) {
                bufferedWriter.write(statistics.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println(statFile.getAbsolutePath());
            e.printStackTrace();
        }

    }

    public void printStatistics() {
        System.out.printf("\n'%s' stats: \n", bookName);
        Arrays.stream(statistics.toString().split("\n"))
                .forEach(System.out::println);
    }
}
