package ch.robinglauser.bfhexercise.exercises;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatisticsConverter {
    public static void main(String[] args) {

        convertTwo();
    }


    public static void convertTwo(){

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withQuote(null).withDelimiter(';');
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\robin\\IdeaProjects\\SwissPyramid\\output2.csv"));
            CSVPrinter printer = new CSVPrinter(writer, csvFileFormat);


            List<String> headerRow = new ArrayList<>();
            headerRow.add("Variante");
            headerRow.add("Simulationsjahr");
            headerRow.add("mw");
            headerRow.add("Median");
            headerRow.add("Bev");
            for (int i = 0; i < 100; i++) {
                headerRow.add("Bev_" + i + "_" + (i + 1));
            }

            printer.printRecord(headerRow);


            File csvData = new File("C:\\Users\\robin\\IdeaProjects\\SwissPyramid\\data2.csv");
            CSVParser parser = null;

            parser = CSVParser.parse(csvData, Charset.defaultCharset(), csvFileFormat);


            String year = "2010";
            String gender = "Mann";
            List<String> row = new ArrayList<>();
            row.add("0");
            row.add(year);
            row.add("m");
            row.add("0");
            row.add("0");
            Boolean change = false;
            for (CSVRecord csvRecord : parser) {
                System.out.println(csvRecord.toString());
                if (!year.equals( csvRecord.get(0).replace("\"", ""))) {
                    System.out.println("New Year");
                    year = csvRecord.get(0).replace("\"", "");
                    printer.printRecord(row);
                    change = true;
                }
                if (!gender.equals(csvRecord.get(1).replace("\"", ""))) {
                    System.out.println("New Gender");
                    gender = csvRecord.get(1).replace("\"", "");
                    printer.printRecord(row);
                    change = true;
                }
                if (change){
                    change = false;
                    row = new ArrayList<>();
                    row.add("0");
                    row.add(csvRecord.get(0).replace("\"", ""));
                    if (csvRecord.get(1).replace("\"", "").equals("Mann")){
                        row.add("m");
                    }
                    else {
                        row.add("w");
                    }
                    row.add("0");
                    row.add("0");
                }
                for (int i = 3; i < csvRecord.size(); i++) {
                    row.add(String.valueOf(Integer.parseInt(csvRecord.get(i)) / 1000));
                }

            }
            printer.printRecord(row);
            printer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void convertOne(){

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withQuote(null).withDelimiter(';');
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\robin\\IdeaProjects\\SwissPyramid\\output.csv"));
            CSVPrinter printer = new CSVPrinter(writer, csvFileFormat);


            List<String> headerRow = new ArrayList<>();
            headerRow.add("Variante");
            headerRow.add("Simulationsjahr");
            headerRow.add("mw");
            headerRow.add("Median");
            headerRow.add("Bev");
            for (int i = 0; i < 100; i++) {
                headerRow.add("Bev_" + i + "_" + (i + 1));
            }

            printer.printRecord(headerRow);


            File csvData = new File("C:\\Users\\robin\\IdeaProjects\\SwissPyramid\\data.csv");
            CSVParser parser = null;

            parser = CSVParser.parse(csvData, Charset.defaultCharset(), csvFileFormat);


            String year = "1900";
            String gender = "m ";
            List<String> row = new ArrayList<>();
            row.add("0");
            row.add(year);
            row.add(gender);
            row.add("0");
            row.add("0");
            Boolean change = false;
            for (CSVRecord csvRecord : parser) {
                if (!year.equals( csvRecord.get(0).replace("\"", ""))) {
                    System.out.println("New Year");
                    year = csvRecord.get(0).replace("\"", "");
                    printer.printRecord(row);
                    change = true;
                }
                if (!gender.equals(csvRecord.get(1).replace("\"", ""))) {
                    System.out.println("New Gender");
                    gender = csvRecord.get(1).replace("\"", "");
                    printer.printRecord(row);
                    change = true;
                }
                if (change){
                    change = false;
                    row = new ArrayList<>();
                    row.add("0");
                    row.add(csvRecord.get(0).replace("\"", ""));
                    if (csvRecord.get(1).replace("\"", "").equals("Mann")){
                        row.add("m");
                    }
                    else {
                        row.add("w");
                    }
                    row.add("0");
                    row.add("0");
                }

                row.add(String.valueOf(Integer.parseInt(csvRecord.get(3)) / 1000));
            }
            printer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
