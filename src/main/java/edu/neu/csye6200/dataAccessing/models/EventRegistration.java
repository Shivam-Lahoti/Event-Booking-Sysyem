package edu.neu.csye6200.dataAccessing.models;

import edu.neu.csye6200.dataAccessing.FileAccessUtility;
import edu.neu.csye6200.dataAccessing.abstraction.DataAccess;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class EventRegistration extends DataAccess {
    private static final EventRegistration instance = new EventRegistration();
    private EventRegistration() {}
    public static EventRegistration getInstance() {return instance;}

    protected static String tableName = "Event Registration";
    protected static String[] headers = {"id", "user_id", "ticket_type_id", "isItAvailable", "description", "purchase_date"};

    public static String getTableName() {
        return tableName;
    }

    public static String[] getHeaders() {
        return headers;
    }
    synchronized public Dictionary<String, String> getRecordById(String id) {
        return getOneRecordByACertainColumn(tableName, headers, "id", id);
    }

    synchronized public List<Dictionary<String, String>> getRecordsBy_user_id(String user_id) {
        return getRecordsByACertainColumn(tableName, headers, "user_id", user_id);
    }

    synchronized public List<Dictionary<String, String>> getRecordsBy_ticket_type_id(String ticket_type_id) {
        return getRecordsByACertainColumn(tableName, headers, "ticket_type_id", ticket_type_id);
    }

    synchronized public boolean insertEventRegistrationRecord(String user_id, String ticket_type_id, boolean isItAvailable, String description, String purchase_date) {
        return FileAccessUtility.getInstance().insertData(
                tableName,
                headers,
                new String[]{
                        String.valueOf(generateNewId(tableName, headers)), user_id, ticket_type_id, String.valueOf(isItAvailable), description, purchase_date
                }
        );
    }

    synchronized public boolean updateEventRegistrationRecord(String id, Hashtable<String, String> updatedKeyValueString) {
        return updateByOneKey_checkIfExist(tableName, headers, "id", id, updatedKeyValueString);
    }

    synchronized public boolean deleteEventRegistrationRecord(String id) {
        return deleteByOneKey_checkIfExist(tableName, headers, "id", id);
    }

}
