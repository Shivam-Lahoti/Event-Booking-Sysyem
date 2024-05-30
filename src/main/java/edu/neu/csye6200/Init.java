package edu.neu.csye6200;

import edu.neu.csye6200.GUI.GUI;
import edu.neu.csye6200.GUI.Test;
import edu.neu.csye6200.Utilities.Datetime;
import edu.neu.csye6200.dataAccessing.FileAccessUtility;
import edu.neu.csye6200.dataAccessing.models.*;

import java.time.LocalDateTime;
import java.util.Hashtable;

public class Init {
    private static void addTestData() {
        // Users
        User.getInstance().insertUser("aaa", "A A", "aaa", "", false);
        User.getInstance().insertUser("bbb", "B B", "bbb", "", true);
        User.getInstance().insertUser("ccc", "C C", "ccc", "", true);

        User.getInstance().updateUser("2", new Hashtable<String, String>(){{put("display_name", "Bob");}});
        User.getInstance().deleteUser("3");

        ExtraOrganizerInfo.getInstance().insertNewOrganizerInfo("2", "I am Bob! I am an organizer!");

        ExtraOrganizerInfo.getInstance().updateOrganizerInfoById(
                ExtraOrganizerInfo.getInstance().getRecordByUserId(
                        User.getInstance().getRecordByUsername("bbb").get("id")
                ).get("id"),
                "I am really bob!"
        );


        EventSchedule.getInstance().insertEventSchedule(
                ExtraOrganizerInfo.getInstance().getRecordByUserId(
                        User.getInstance().getRecordByUsername("bbb").get("id")
                ).get("user_id"),
                "Bob Party",
                Datetime.localDateTime_toString(LocalDateTime.of(2023, 12, 21, 12, 30)),
                Datetime.localDateTime_toString(LocalDateTime.of(2023, 12, 21, 13, 30)),
                Datetime.localDateTime_toString(LocalDateTime.of(2023, 12, 20, 12, 30)),
                Datetime.localDateTime_toString(LocalDateTime.of(2023, 12, 18, 12, 30)),
                "This is event description",
                "Your Place"
        );


        EventSchedule.getInstance().updateEventSchedule(
                EventSchedule.getInstance().getRecordsByOrganizerId(
                        ExtraOrganizerInfo.getInstance().getRecordByUserId(
                                User.getInstance().getRecordByUsername("bbb").get("id")
                        ).get("user_id")
                ).get(0).get("id"),
                new Hashtable<String, String>() {{put("event_title", "Endless Night");}}
        );



        TicketTypes.getInstance().insertTicketType("1", 60, "Cheap Ticket", 50);
        TicketTypes.getInstance().insertTicketType("1", 600, "VIP Ticket", 20);
        TicketTypes.getInstance().insertTicketType("1", 6000, "Disappear Ticket", 2);

        TicketTypes.getInstance().deleteTicketType("3");
        System.out.println(TicketTypes.getInstance().getRecordsByEventId("1"));


        EventRegistration.getInstance().insertEventRegistrationRecord(
                "1",
                "1",
                true,
                "",
                Datetime.localDateTime_toString(LocalDateTime.now())
        );

        System.out.println(EventRegistration.getInstance().getRecordsBy_user_id("1"));
        System.out.println(
                Datetime.string_toLocalDateTime(EventRegistration.getInstance().getRecordsBy_user_id("1").get(0).get("purchase_date"))
        );
        EventRegistration.getInstance().deleteEventRegistrationRecord("1");

    }

    public static void demo() {
        // Check if all csv files are fine
        if_this_file_is_incorrect_then_recreate_it(User.getTableName(), User.getHeaders());
        if_this_file_is_incorrect_then_recreate_it(EventRegistration.getTableName(), EventRegistration.getHeaders());
        if_this_file_is_incorrect_then_recreate_it(ExtraOrganizerInfo.getTableName(), ExtraOrganizerInfo.getHeaders());
        if_this_file_is_incorrect_then_recreate_it(TicketTypes.getTableName(), TicketTypes.getHeaders());
        if_this_file_is_incorrect_then_recreate_it(EventSchedule.getTableName(), EventSchedule.getHeaders());

//        addTestData();

        //new GUI();
        new GUI();
    }


    private static void if_this_file_is_incorrect_then_recreate_it(String tableName, String[] headers) {
        if (FileAccessUtility.getInstance().checkIfFileCorrect(tableName, headers)) {
            ;
        } else {
            FileAccessUtility.getInstance().generateCSVFileBasedOnHeaders(tableName, headers);
        }
    }

}
