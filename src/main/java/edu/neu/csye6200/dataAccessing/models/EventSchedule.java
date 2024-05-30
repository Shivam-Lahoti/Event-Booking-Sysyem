package edu.neu.csye6200.dataAccessing.models;

import edu.neu.csye6200.dataAccessing.FileAccessUtility;
import edu.neu.csye6200.dataAccessing.abstraction.DataAccess;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

public class EventSchedule extends DataAccess {
    private static final EventSchedule instance = new EventSchedule();
    private EventSchedule() {}
    public static EventSchedule getInstance() {return instance;}
    protected static String tableName = "Event Schedules";
    protected static String[] headers = {"id", "organizer_id", "event_title", "time_to_start", "time_to_end", "deadline", "creation_time", "description", "location"};

    public static String getTableName() {
        return tableName;
    }

    public static String[] getHeaders() {
        return headers;
    }

    synchronized public Dictionary<String, String> getRecordById(String id) {
        return getOneRecordByACertainColumn(tableName, headers, "id", id);
    }

    synchronized public List<Dictionary<String, String>> getRecordsByOrganizerId(String organizer_id) {
        return getRecordsByACertainColumn(tableName,headers,"organizer_id", organizer_id);
    }

    synchronized public List<Dictionary<String, String>> getAllevents() {
    	Predicate<Dictionary<String, String>> alwaysTruePredicate = d -> true;
        return  getRecordsbyTablename(tableName,headers);
    }
  


    synchronized public List<Dictionary<String, String>> getRecords() {
        return getAllRecords(tableName,headers);
    }


    synchronized public String insertEventSchedule(String organizer_id, String event_title, String time_to_start, String time_to_end, String deadline, String creation_time, String description, String location) {
        String newId = String.valueOf(generateNewId(tableName, headers));
        if (FileAccessUtility.getInstance().insertData(
                tableName,
                headers,
                new String[]{
                        newId, organizer_id, event_title, time_to_end, time_to_end, deadline, creation_time, description, location
                }
        )){
            return newId;
        } else {
            return null;
        }
    }

    synchronized public boolean updateEventSchedule(String id, Hashtable<String, String> updatedKeyValueString) {
        return updateByOneKey_checkIfExist(tableName, headers, "id", id, updatedKeyValueString);
    }

    synchronized public boolean deleteEventSchedule(String id) {
        return deleteByOneKey_checkIfExist(tableName, headers, "id", id);
    }
}
