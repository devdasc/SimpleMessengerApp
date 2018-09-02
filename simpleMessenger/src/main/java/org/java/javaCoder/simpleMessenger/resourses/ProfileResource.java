package org.java.javaCoder.simpleMessenger.resourses;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.java.javaCoder.simpleMessenger.model.Profile;
import org.java.javaCoder.simpleMessenger.service.ProfileService;

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	private ProfileService profileService=new ProfileService();
	
	//to get all profiles
	@GET
	public  List<Profile> getProfiles(){
		return profileService.getAllProfiles();
	}
	//to get a single profile
	@GET
	@Path("{profileName}")
	public Profile addProfile(@PathParam("profileName") String profileName) {
		
		return profileService.getProfile(profileName);
	}
	//to create a new profile
	@POST
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
		
	}
	// to update a profile
	@PUT
	@Path("{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	//to delete a profile
	@DELETE
	@Path("{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
	}
	

}
