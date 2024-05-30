package edu.neu.csye6200.dataAccessing.abstraction;

import edu.neu.csye6200.dataAccessing.exception.CSVFileCorruptedException;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

// About reading and writing files themselves
public interface FileAccessAPI {
    /*
        These are utilities
     */
    String generateFileName(String tableName); // To convert table name into the file name, including the path

    /*
        These are for initialization
     */
    boolean generateCSVFileBasedOnHeaders(String tableName, String[] headers);
    boolean checkIfFileCorrect(String tableName, String[] headers);

    /*
        These are basic operations
     */
    public List<Dictionary<String, String>> readDataAndFilter(String tableName, String[] headers, Predicate<Dictionary<String, String>> predicate);  // To read the table, get a list of data, and filter them, return a list of dictionaries each representing a record
    public boolean updateData(String tableName, String[] headers, Predicate<Dictionary<String, String>> predicate, Hashtable<String, String> updatedKeyValueString);
    public boolean insertData(String tableName, String[] headers, String[] values);
    public boolean deleteData(String tableName, String[] headers, Predicate<Dictionary<String, String>> predicate);
}