package org.java.javaCoder.simpleMessenger.resourses;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.java.javaCoder.simpleMessenger.model.Message;
import org.java.javaCoder.simpleMessenger.resources.beans.MessageFilterBean;
import org.java.javaCoder.simpleMessenger.service.MessageService;

@Path("messages")// "/" is taken care of by jersey
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value= {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {
	
	MessageService messageService=new MessageService();

	// to get all messages
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Message> getMessages(@QueryParam("year") int year,
//									@QueryParam("start") int start,
//									@QueryParam("size") int size) {
//		if(year>0) {
//			return messageService.getAllMessagesForYear(year);
//		}
//		if(start>=0 && size>0) {
//			return messageService.getAllMessagesPaginated(start, size);
//		}
//		return messageService.getAllMessages();
//	}
	// to get all messages
		@GET
		//@Produces(MediaType.APPLICATION_JSON)
		public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
			if(filterBean.getYear()>0) {
				return messageService.getAllMessagesForYear(filterBean.getYear());
			}
			if(filterBean.getStart()>=0 && filterBean.getSize()>0) {
				return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
			}
			return messageService.getAllMessages();
		}
	
	// to get a single message by id
//	@GET
//	@Path("{messageId}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Message getMessage(@PathParam("messageId") long messageId) {
//		
//		return messageService.getMessage(messageId);
//		
//	}
	@GET
	@Path("{messageId}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message=messageService.getMessage(messageId);		
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		
		return message;
		
	}
	//method to build comment url
	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId",message.getId())
				.build();
		return uri.toString();
	}
	// method to build profile url
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
		return uri;
		
	}

	//method to create self uri link
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
		return uri;
	}
	
	// to create a new message
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)	
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Message addMessage(Message message) {
//		return messageService.addMessage(message);
//	}
	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)	
//	@Consumes(MediaType.APPLICATION_JSON)
//	// this alternative POST method provides custom response code	
//	public Response addMessage(Message message) throws URISyntaxException {
//		Message newMessage=messageService.addMessage(message);
////		return Response.status(Status.CREATED)
////			.entity(newMessage)
////				.build();
//		return Response.created(new URI("/simpleMessenger/webapi/messages/"+ newMessage.getId()))
//				.entity(newMessage)
//				.build();
//	}
	@POST
	//@Produces(MediaType.APPLICATION_JSON)	
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage=messageService.addMessage(message);
		String newId=String.valueOf(newMessage.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	// to update a message
	@PUT
	@Path("{messageId}")
	//@Produces(MediaType.APPLICATION_JSON)	
	//@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
		
	}
	// to delete a message
	
	@DELETE
	@Path("{messageId}")
	//@Produces(MediaType.APPLICATION_JSON)	
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.deleteMessage(id);
		
	}
	
	//anchor method for comment subresource
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	
	
}
