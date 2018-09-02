package org.java.javaCoder.simpleMessenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.java.javaCoder.simpleMessenger.database.DatabaseClass;
import org.java.javaCoder.simpleMessenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles=DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("devdas", new Profile(1L,"devdas","devdas","chatterjee"));
	}
	
	public List<Profile>getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String ProfileName) {
		return profiles.get(ProfileName);
	}
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	public Profile updateProfile(Profile profile) {
		if(profile.getId()<=0) {
			return null;
		}else {
			profiles.put(profile.getProfileName(), profile);
			return profile;
		}
	}
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
	

}
