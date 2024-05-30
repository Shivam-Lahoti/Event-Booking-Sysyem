package edu.neu.csye6200.Services;

import edu.neu.csye6200.Utilities.PasswordUtility;
import edu.neu.csye6200.dataAccessing.models.ExtraOrganizerInfo;
import edu.neu.csye6200.dataAccessing.models.User;

import java.util.Dictionary;
import java.util.Hashtable;

public class AccountService {
    public static boolean loginVerification(String username, String password) {
        Dictionary<String, String> theUser = User.getInstance().getRecordByUsername(username);
        if (theUser != null) {
//            System.out.println("theUser: " + theUser);
//            System.out.println("The original password is: " + password);
//            System.out.println("The encrypted password is: " + PasswordUtility.encrypt(password));
//            System.out.println("The stored password is: " + theUser.get("password_encrypted"));
            return PasswordUtility.encrypt(password).equals(theUser.get("password_encrypted"));
        } else {
            return false;
        }
    }

    synchronized public static boolean registerNewUser(String username, String display_name, String password_original, String email_address, boolean isOrganizer) {
//        boolean userCreated = User.getInstance().insertUser(username, display_name, password_original, email_address, isOrganizer);
//        boolean organizerInfoCreated = false;
//        if (userCreated) {
//            if (isOrganizer) {
//                organizerInfoCreated = ExtraOrganizerInfo.getInstance().insertNewOrganizerInfo(
//                        User.getInstance().getRecordByUsername(username).get("id"),
//                        ""
//                );
//            }
//        }
//        return userCreated && organizerInfoCreated;
//
//
        // If the user is not an organizer, then we don't need to create the organizer info
        // If the user is an organizer, then we need to create the organizer info

        boolean userCreated = User.getInstance().insertUser(username, display_name, password_original, email_address, isOrganizer);
        boolean organizerInfoCreated = false;
        if (userCreated) {
            if (isOrganizer) {
                organizerInfoCreated = ExtraOrganizerInfo.getInstance().insertNewOrganizerInfo(
                        User.getInstance().getRecordByUsername(username).get("id"),
                        ""
                );
            }
        }
        return (userCreated && !isOrganizer) || (userCreated && isOrganizer && organizerInfoCreated);

    }


    public static boolean isAnOrganizer(Hashtable<String, String> universalHashtable) {
        return Boolean.parseBoolean(User.getInstance().getRecordById(universalHashtable.get("user_id")).get("isOrganizer"));
    }


}
