package edu.neu.csye6200.dataAccessing.models;

import edu.neu.csye6200.dataAccessing.FileAccessUtility;
import edu.neu.csye6200.dataAccessing.abstraction.DataAccess;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class TicketTypes extends DataAccess {
    private static final TicketTypes instance = new TicketTypes();
    private TicketTypes() {}
    public static TicketTypes getInstance() {return instance;}

    protected static String tableName = "Ticket Type";
    protected static String[] headers = {"id", "event_id", "price", "name", "how_many_tickets_in_total"};

    public static String getTableName() {
        return tableName;
    }

    public static String[] getHeaders() {
        return headers;
    }

    synchronized public Dictionary<String, String> getRecordById(String id) {
        return getOneRecordByACertainColumn(tableName, headers, "id", id);
    }

    synchronized public List<Dictionary<String, String>> getRecordsByEventId(String event_id) {
        return getRecordsByACertainColumn(tableName, headers, "event_id", event_id);
    }
    synchronized public Dictionary<String, String> getRecordByEventId(String event_id) {
        return getOneRecordByACertainColumn(tableName, headers, "event_id", event_id);
    }

    synchronized public boolean insertTicketType(String event_id, double price, String name, int how_many_tickets_in_total) {
        return FileAccessUtility.getInstance().insertData(
                tableName,
                headers,
                new String[]{
                        String.valueOf(generateNewId(tableName, headers)), event_id, String.valueOf(price), name, String.valueOf(how_many_tickets_in_total)
                }
        );
    }

    synchronized public boolean updateTicketType(String id, Hashtable<String, String> updatedKeyValueString) {
        return updateByOneKey_checkIfExist(tableName, headers, "id", id, updatedKeyValueString);
    }

    synchronized public boolean deleteTicketType(String id) {
        return deleteByOneKey_checkIfExist(tableName, headers, "id", id);
    }
}
