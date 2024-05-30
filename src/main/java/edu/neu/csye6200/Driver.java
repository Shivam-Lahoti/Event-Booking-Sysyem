package edu.neu.csye6200;

import edu.neu.csye6200.dataAccessing.FileAccessUtility;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 * @author Ruchika Sharma
 * 
 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");

         //Add your code in between these two print statements
		Init.demo();

		/*
		FileAccessUtility.instance.insertData("Test", new String[]{"a", "b", "c"}, new String[]{"1", "2", "3"});
		FileAccessUtility.instance.insertData("Test", new String[]{"a", "b", "c"}, new String[]{"2", "2", "3"});
		FileAccessUtility.instance.insertData("Test", new String[]{"a", "b", "c"}, new String[]{"3", "2", "3"});
		FileAccessUtility.instance.insertData("Test", new String[]{"a", "b", "c"}, new String[]{"4", "2", "3"});
		*/

		 
		System.out.println("\n\n============Main Execution End===================");
	}

}
