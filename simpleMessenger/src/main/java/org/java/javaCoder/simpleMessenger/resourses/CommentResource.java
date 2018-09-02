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

import org.java.javaCoder.simpleMessenger.model.Comment;
import org.java.javaCoder.simpleMessenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService=new CommentService();
	
	//to get all comments of a message
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId){
		return commentService.getAllComments(messageId);
	}
	//to get a comment of a message
	@GET
	@Path("{/commentId}")
	public Comment getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
		
	}
	
	//to create a comment for a message
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		
		return commentService.addComment(messageId, comment);
	}
	//to edit a comment
	@PUT
	@Path("{/commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	//to delete a comment
	@DELETE
	@Path("{/commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		
		commentService.removeComment(messageId, commentId);
	}

	
}
