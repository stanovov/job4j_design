package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException();
                }
                String status = parts[0];
                String period = parts[1];
                if ("400".equals(status) || "500".equals(status)) {
                    if (sb.length() == 0) {
                        sb.append(period).append(";");
                    }
                } else if ("200".equals(status) || "300".equals(status)) {
                    if (sb.length() != 0) {
                        sb.append(period);
                        out.println(sb.toString());
                        sb = new StringBuilder();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}