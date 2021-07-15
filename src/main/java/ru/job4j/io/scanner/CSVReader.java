package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    private static final String PROMPT = "Usage \"java -jar target/csvReader.jar\"\n"
                                        + "-path -delimiter -out -filter";

    private Path path;

    private File outFile;

    private String delimiter;

    private Set<String> filter;

    /**
     * Карта для подсчета длины колонок при выводе данных
     */
    private final Map<Integer, Integer> headlines = new HashMap<>();

    public CSVReader(String[] args) {
        check(args);
    }

    public void execute() {
        List<List<String>> csvData = readFromFile(path, delimiter);
        try {
            write(outFile, csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void check(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect number of parameters.\n" + PROMPT);
        }
        ArgsName argsName = ArgsName.of(args);
        path = getPath(argsName.get("path"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s%n", path.toFile().getAbsoluteFile()) + PROMPT
            );
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException(
                    String.format("Not file %s%n", path.toFile().getAbsoluteFile()) + PROMPT
            );
        }
        String out = argsName.get("out");
        outFile = out.equals("stdout") ? null : getPath(out).toFile();
        delimiter = argsName.get("delimiter");
        filter = new HashSet<>(Arrays.asList(
                argsName.get("filter").split(",")
        ));
    }

    private List<List<String>> readFromFile(Path path, String delimiter) {
        List<List<String>> result = new ArrayList<>();
        try (var scanner = new Scanner(path, StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) {
                Set<Integer> allowedColumns = new HashSet<>();
                List<String> line = new ArrayList<>();
                String[] lineHeadlines = scanner.nextLine().split(delimiter);
                for (int i = 0; i < lineHeadlines.length; i++) {
                    if (filter.contains(lineHeadlines[i])) {
                        allowedColumns.add(i);
                        line.add(lineHeadlines[i]);
                        headlines.put(line.size() - 1, lineHeadlines[i].length());
                    }
                }
                if (allowedColumns.isEmpty()) {
                    throw new IllegalArgumentException("Invalid file format. Column headings are missing.\n" + PROMPT);
                }
                result.add(line);
                while (scanner.hasNextLine()) {
                    String[] row = scanner.nextLine().split(delimiter);
                    line = new ArrayList<>();
                    for (int i = 0; i < row.length; i++) {
                        if (!allowedColumns.contains(i)) {
                            continue;
                        }
                        String token = row[i];
                        line.add(token);
                        int key = line.size() - 1;
                        int maxLen = headlines.get(key);
                        if (token.length() > maxLen) {
                            headlines.put(key, token.length());
                        }
                    }
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void write(File file, List<List<String>> csvData) throws IOException {
        Writer writer = (file == null)
                ? new OutputStreamWriter(System.out, StandardCharsets.UTF_8)
                : new FileWriter(file);
        int length = 1;
        for (Integer len : headlines.values()) {
            length += len + 3;
        }
        try (BufferedWriter out = new BufferedWriter(writer)) {
            drawHorizontalSeparator(out, length);
            drawLine(out, csvData.get(0));
            drawHorizontalSeparator(out, length);
            for (int i = 1; i < csvData.size(); i++) {
                drawLine(out, csvData.get(i));
            }
            drawHorizontalSeparator(out, length);
            if (file != null) {
                System.out.println("The result is written to the file path - " + file.getCanonicalPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawHorizontalSeparator(BufferedWriter out, int length) throws IOException {
        out.write("-".repeat(length));
        out.newLine();
    }

    private void drawLine(BufferedWriter out, List<String> line) throws IOException {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < line.size(); i++) {
            int columnLen = headlines.get(i);
            String str = line.get(i);
            sb.append(String.format(" %-" + columnLen + "s |", str));
        }
        out.write(sb.toString());
        out.newLine();
    }

    private static Path getPath(String path) {
        try {
            return Path.of(path);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid path \"" + path + "\".\n" + PROMPT, e);
        }
    }

    public static void main(String[] args) throws IOException {
        new CSVReader(args).execute();
    }
}
