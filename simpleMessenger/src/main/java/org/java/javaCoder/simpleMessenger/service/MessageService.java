package org.java.javaCoder.simpleMessenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.java.javaCoder.simpleMessenger.database.DatabaseClass;
import org.java.javaCoder.simpleMessenger.exception.DataNotFoundException;
import org.java.javaCoder.simpleMessenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages=DatabaseClass.getMessages();
	
	public MessageService() {
		
		messages.put(1L, new Message(1, "Hello World","Java Coder"));
		messages.put(2L, new Message(2, "Hello JAX-RS","Java Learner"));
	}
	
	public List<Message> getAllMessages(){
//		Message m1=new Message(1L,"Hello World","JavaCoder");
//		Message m2=new Message(2L,"Hello World2","JavaCoder2");
//		List<Message> messageList=new ArrayList<>();
//		messageList.add(m1);
//		messageList.add(m2);
//		return messageList;
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear=new ArrayList<>();
		Calendar cal=Calendar.getInstance();
		for(Message message: messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	 public List<Message> getAllMessagesPaginated(int start, int size){
		 ArrayList<Message>list=new ArrayList<Message>(messages.values());
		 if(start+size>list.size()) return new ArrayList<Message>();
		 return list.subList(start, start+size);
	 }
	
	public Message getMessage(long id) {
		//return messages.get(id);
		Message message=messages.get(id);
		if(message==null) {
			throw new DataNotFoundException("Message with id "+ id+" not found" );
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId()<=0) {
			return null;
		}else {
			messages.put(message.getId(), message);
			return message;
		}
	}
	
	public Message deleteMessage(long id) {
		
		return messages.remove(id);
	}

}
