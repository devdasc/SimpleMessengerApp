package org.java.javaCoder.simpleMessenger.database;

import java.util.HashMap;
import java.util.Map;

import org.java.javaCoder.simpleMessenger.model.Message;
import org.java.javaCoder.simpleMessenger.model.Profile;

public class DatabaseClass {
	
	private static Map<Long, Message> messages=new HashMap<>();
	private static Map<String, Profile> profiles=new HashMap<>();
	
	public static Map<Long,Message> getMessages(){
		return messages;
	}
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}

}
