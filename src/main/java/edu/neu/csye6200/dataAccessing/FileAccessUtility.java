package edu.neu.csye6200.dataAccessing;

import edu.neu.csye6200.dataAccessing.abstraction.FileAccessAPI;
import edu.neu.csye6200.dataAccessing.exception.CSVFileCorruptedException;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.file.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;


public class FileAccessUtility implements FileAccessAPI {
    private final static String filePath = "./src/main/resources/";

    private static final FileAccessUtility instance = new FileAccessUtility();

    public static final FileAccessUtility getInstance() {
        return instance;
    }

    private FileAccessUtility() {}


    @Override
    public String generateFileName(String tableName) {
        return filePath + tableName + ".csv";
    }

    @Override
    public boolean generateCSVFileBasedOnHeaders(String tableName, String[] headers) {
        //create a CSV printer
        CSVPrinter printer = null;
        try {
            printer = new CSVPrinter(new FileWriter(generateFileName(tableName)), CSVFormat.DEFAULT);
            //create header row
            printer.printRecord(headers);

            printer.flush();
            printer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    synchronized public boolean checkIfFileCorrect(String tableName, String[] headers) {
        File f = new File(generateFileName(tableName));
        if(f.exists() && !f.isDirectory()) {
            try {
                CSVParser csvParser = null;
                csvParser = new CSVParser(new FileReader(generateFileName(tableName)), CSVFormat.DEFAULT);

                CSVRecord theRecord = csvParser.getRecords().get(0);

                for (int whichColumn = 0; whichColumn < headers.length; whichColumn++) {
                    if (theRecord.get(whichColumn).equals(headers[whichColumn])) {
                        ;
                    } else {
                        csvParser.close();
                        return false;
                    }
                }


                csvParser.close();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            return false;
        }
    }


    @Override
    synchronized public List<Dictionary<String, String>> readDataAndFilter(String tableName, String[] headers, Predicate<Dictionary<String, String>> predicate) {
        List<Dictionary<String, String>> records = new Vector<>();

        try {
            CSVParser csvParser = new CSVParser(new FileReader(generateFileName(tableName)), CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : csvParser) {
                //System.out.println(record.get(0) + "," + record.get(1) + "," + record.get(2) + "," + record.get(3));
                Dictionary<String, String> theDictionary = new Hashtable<>();

                for (int whichColumn = 0; whichColumn < headers.length; whichColumn++) {
                    theDictionary.put(headers[whichColumn], record.get(whichColumn));
                }

                if (predicate.test(theDictionary)) {
                    records.add(theDictionary);
                }
            }
            csvParser.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return records;
    }

    synchronized public boolean updateData(String tableName, String[] headers, Predicate<Dictionary<String, String>> predicate, Hashtable<String, String> updatedKeyValueString) {
        try {
            // Read the CSV file
            CSVParser csvParser = new CSVParser(new FileReader(generateFileName(tableName)), CSVFormat.DEFAULT.withHeader());

            // Create a new CSV file writer
            CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(generateFileName(tableName + "_temp")), CSVFormat.DEFAULT);
            csvPrinter.printRecord(headers);

            // Iterate over the records and update the specified cell
            for (CSVRecord csvRecord : csvParser) {
                // Get the data
                Dictionary<String, String> theDictionary = new Hashtable<>();
                for (int whichColumn = 0; whichColumn < headers.length; whichColumn++) {
                    theDictionary.put(headers[whichColumn], csvRecord.get(whichColumn));
                }


                if (predicate.test(theDictionary)) {
                    // Update the data
                    for (String eachKey: updatedKeyValueString.keySet()) {
                        theDictionary.put(eachKey, updatedKeyValueString.get(eachKey));
                    }


                    // Store the data
                    String [] array = new String[headers.length];
                    for (int i = 0; i < array.length; i++) {
                        array[i] = theDictionary.get(headers[i]);
                    }
                    csvPrinter.printRecord(array);
                } else {
                    csvPrinter.printRecord(csvRecord);
                }
            }

            // Close the CSV parser and printer
            csvPrinter.flush();
            csvParser.close();
            csvPrinter.close();

            // Replace the original file with the modified file
            Files.move(Paths.get(generateFileName(tableName + "_temp")), Paths.get(generateFileName(tableName)), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("CSV file updated successfully.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    synchronized public boolean insertData(String tableName, String[] headers, String[] values) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(generateFileName(tableName), true));

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            csvPrinter.printRecord(values);
            csvPrinter.flush();
            csvPrinter.close();
            System.out.println(values.toString());
            System.out.println("CSV record inserted successfully.");
            return  true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    synchronized public boolean deleteData(String tableName, String[] headers, Predicate<Dictionary<String, String>> predicate) {
        try {
            // Read the CSV file
            CSVParser csvParser = new CSVParser(new FileReader(generateFileName(tableName)), CSVFormat.DEFAULT.withHeader());

            // Create a new CSV file writer
            CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(generateFileName(tableName + "_temp")), CSVFormat.DEFAULT);
            csvPrinter.printRecord(headers);

            // Iterate over the records and update the specified cell
            for (CSVRecord csvRecord : csvParser) {
                // Get the data
                Dictionary<String, String> theDictionary = new Hashtable<>();
                for (int whichColumn = 0; whichColumn < headers.length; whichColumn++) {
                    theDictionary.put(headers[whichColumn], csvRecord.get(whichColumn));
                }


                if (predicate.test(theDictionary)) {
                    ;
                } else {
                    csvPrinter.printRecord(csvRecord);
                }
            }

            // Close the CSV parser and printer
            csvPrinter.flush();
            csvParser.close();
            csvPrinter.close();

            // Replace the original file with the modified file
            Files.move(Paths.get(generateFileName(tableName + "_temp")), Paths.get(generateFileName(tableName)), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("CSV file updated successfully.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
