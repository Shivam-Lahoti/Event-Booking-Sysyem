package edu.neu.csye6200.dataAccessing.abstraction;

import edu.neu.csye6200.dataAccessing.FileAccessUtility;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

abstract public class DataAccess {
    protected List<Dictionary<String, String>> getAllRecords(String tableName, String[] headers) {
        List<Dictionary<String, String>> theList = FileAccessUtility.getInstance().readDataAndFilter(
                tableName,
                headers,
                (Dictionary<String, String> theDictionary) -> true
        );
        if (theList.size() > 0) {
            return theList;
        } else {
            return null;
        }
    }
    protected List<Dictionary<String, String>> getRecordsByACertainColumn(String tableName, String[] headers, String columnName, String value) {
        List<Dictionary<String, String>> theList = FileAccessUtility.getInstance().readDataAndFilter(
                tableName,
                headers,
                (Dictionary<String, String> theDictionary) -> theDictionary.get(columnName).equals(value)
        );
        if (theList.size() > 0) {
            return theList;
        } else {
            return null;
        }
    }
    protected List<Dictionary<String, String>> getRecordsbyTablename(String tableName, String[] headers) {
    	Predicate<Dictionary<String, String>> alwaysTruePredicate = d -> true;
        List<Dictionary<String, String>> theList = FileAccessUtility.getInstance().readDataAndFilter(
                tableName,
                headers,
                alwaysTruePredicate
        );
        if (theList.size() > 0) {
            return theList;
        } else {
            return null;
        }
    }
    protected Dictionary<String, String> getOneRecordByACertainColumn(String tableName, String[] headers, String columnName, String value) {
        List<Dictionary<String, String>> theList = getRecordsByACertainColumn(tableName, headers, columnName, value);

        if (theList != null) {
            return theList.get(0);
        } else {
            return null;
        }
    }

    synchronized public boolean checkIfExists(String tableName, String[] headers, String columnName, String value) {
        List<Dictionary<String, String>> theList = FileAccessUtility.getInstance().readDataAndFilter(
                tableName,
                headers,
                (Dictionary<String, String> a) -> a.get(columnName).equals(value)
        );


        if (theList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    synchronized protected int generateNewId(String tableName, String[] headers) {
        int currentMaxId = 0;
        List<Dictionary<String, String>> theList = FileAccessUtility.getInstance().readDataAndFilter(
                tableName,
                headers,
                (Dictionary<String, String> a) -> true
        );

        for (Dictionary<String, String> eachValue: theList) {
            int theIdNumber = Integer.parseInt(eachValue.get("id"));
            if (currentMaxId < theIdNumber) {
                currentMaxId = theIdNumber;
            }
        }

        currentMaxId++;
        return currentMaxId;
    }

    synchronized protected boolean updateByOneKey(String tableName, String[] headers, String byColumnName, String byValue, Hashtable<String, String> updatedKeyValueString) {// To update one record of data, with one key (like based on the id to trace)
        return FileAccessUtility.getInstance().updateData(
                tableName,
                headers,
                (Dictionary<String, String> a) -> a.get(byColumnName).equals(byValue),
                updatedKeyValueString
        );
    }

    synchronized protected boolean updateByOneKey_checkIfExist(String tableName, String[] headers, String byColumnName, String byValue, Hashtable<String, String> updatedKeyValueString) {
        if (checkIfExists(tableName, headers, byColumnName, byValue)) {
            return updateByOneKey(tableName, headers, byColumnName, byValue, updatedKeyValueString);
        }
        return false;
    }

    synchronized protected boolean deleteByOneKey(String tableName, String[] headers, String byColumnName, String byValue) {
        return FileAccessUtility.getInstance().deleteData(
            tableName,
            headers,
            (Dictionary<String, String> a) -> a.get(byColumnName).equals(byValue)
        );
    }

    synchronized protected boolean deleteByOneKey_checkIfExist(String tableName, String[] headers, String byColumnName, String byValue) {
        if (checkIfExists(tableName, headers, byColumnName, byValue)) {
            return deleteByOneKey(tableName, headers, byColumnName, byValue);
        }
        return false;
    }
}
