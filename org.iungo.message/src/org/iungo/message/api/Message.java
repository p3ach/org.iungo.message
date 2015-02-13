package org.iungo.message.api;

import java.io.Serializable;

import org.iungo.id.api.ID;

public interface Message extends Comparable<Message>, Serializable {

	static final Integer PRIORITY_STEP = 1024;

	static final Integer LOW_PRIORITY = PRIORITY_STEP * 1; 

	static final Integer NORMAL_PRIORITY = PRIORITY_STEP * 3; 

	static final Integer HIGH_PRIORITY = PRIORITY_STEP * 5; 
	
	Long getTimestamp();
	
	ID getID();
	
	ID getFrom();
	
	ID getTo();
	
	Integer getPriority();
	
	ID getKey();
}
