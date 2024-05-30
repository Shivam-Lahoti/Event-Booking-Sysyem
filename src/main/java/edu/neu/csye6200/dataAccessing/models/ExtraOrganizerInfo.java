package edu.neu.csye6200.dataAccessing.models;

import edu.neu.csye6200.dataAccessing.FileAccessUtility;
import edu.neu.csye6200.dataAccessing.abstraction.DataAccess;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ExtraOrganizerInfo extends DataAccess {
    private static final ExtraOrganizerInfo instance = new ExtraOrganizerInfo();
    private ExtraOrganizerInfo() {}
    public static ExtraOrganizerInfo getInstance() {return instance;}
    protected static String tableName = "Extra Organizer Info";
    protected static String[] headers = {"id", "user_id", "description"};

    public static String getTableName() {
        return tableName;
    }

    public static String[] getHeaders() {
        return headers;
    }

    public Dictionary<String, String> getRecordById(String id) {
        return getOneRecordByACertainColumn(tableName, headers, "id", id);
    }

    public Dictionary<String, String> getRecordByUserId(String userId) {
        return getOneRecordByACertainColumn(tableName, headers, "user_id", userId);
    }

    synchronized public boolean insertNewOrganizerInfo(String userId, String description) {
        if (checkIfExists(tableName, headers, "user_id", userId)) {
            return false;
        }

        return FileAccessUtility.getInstance().insertData(
            tableName,
            headers,
            new String[]{String.valueOf(generateNewId(tableName, headers)), userId, description}
        );
    }

    synchronized public boolean updateOrganizerInfoById(String id, String description) {
        return updateByOneKey_checkIfExist(
                tableName,
                headers,
                "id",
                id,
                new Hashtable<String, String>() {{put("description", description);}}
        );
    }

    synchronized public boolean updateOrganizerInfoByUserId(String userId, String description) {
        return updateByOneKey_checkIfExist(
                tableName,
                headers,
                "user_id",
                userId,
                new Hashtable<String, String>() {{put("description", description);}}
        );
    }

    synchronized public boolean deleteOrganizerInfoById(String id) {
        return deleteByOneKey_checkIfExist(tableName, headers, "id", id);
    }

}
