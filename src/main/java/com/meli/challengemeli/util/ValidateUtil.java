package com.meli.challengemeli.util;

import java.util.List;
import java.util.Optional;

public class ValidateUtil {
	
	private ValidateUtil() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static String validateMessage(List<String> message, List<List<String>> messages) {	
			
		StringBuilder messageFinal = new StringBuilder();	
		var empty = message.stream().filter(item -> item.equals("")).count();
		
		if(empty<=1) {
			message.remove("");
			Optional<List<String>> min = messages.stream().min((size1, size2) -> Integer.compare(size1.size(), size2.size()));
			
			if(min.isPresent() && message.size() == min.get().size()) {
				for(String item: message) {
					messageFinal.append(item).append(' ');
				}	
			}
		}			
		
		return 	messageFinal.toString();
	}		
}

