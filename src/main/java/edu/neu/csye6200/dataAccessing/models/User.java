package edu.neu.csye6200.dataAccessing.models;

import edu.neu.csye6200.Utilities.PasswordUtility;
import edu.neu.csye6200.dataAccessing.FileAccessUtility;
import edu.neu.csye6200.dataAccessing.abstraction.DataAccess;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class User extends DataAccess {
    private static final User instance = new User();
    private User() {}
    public static User getInstance() {return instance;}

    protected static String tableName = "User";
    protected static String[] headers = {"id", "username", "display_name", "password_encrypted", "email_address", "isOrganizer"};

    public static String getTableName() {
        return tableName;
    }

    public static String[] getHeaders() {
        return headers;
    }

    public Dictionary<String, String> getRecordById(String id) {
        return getOneRecordByACertainColumn(tableName, headers, "id", id);
    }

    public Dictionary<String, String> getRecordByUsername(String username) {
        return getOneRecordByACertainColumn(tableName, headers, "username", username);
    }

    synchronized public boolean insertUser(String username, String display_name, String password_original, String email_address, boolean isOrganizer) {
        String idString = String.valueOf(super.generateNewId(tableName, headers));
        String password_encrypted = PasswordUtility.encrypt(password_original);
        String isOrganizerString = String.valueOf(isOrganizer);

        if (checkIfExists(tableName, headers, "username", username)) {
            return false;
        }

        return FileAccessUtility.getInstance().insertData(
                tableName,
                headers,
                new String[]{idString, username, display_name, password_encrypted, email_address, isOrganizerString}
        );
    }


    synchronized public boolean updateUser(String id, Hashtable<String, String> updatedKeyValueString) {
        return updateByOneKey_checkIfExist(tableName, headers, "id", id, updatedKeyValueString);
    }

    synchronized public boolean deleteUser(String id) {
        return deleteByOneKey_checkIfExist(tableName, headers, "id", id);
    }


}
