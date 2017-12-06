package antiSpamFilter;

import java.util.ArrayList;

public class Message {
	
	private ArrayList<Rules> rules= new ArrayList<Rules>();
	private String name;
	
	public Message(String name){
		this.name=name;
	}
	
	public ArrayList<Rules> getRules(){
		return rules;
	}
	
	public void addRules(Rules rules){
		this.rules.add(rules);
	}
	
	public String getName(){
		return name;
	}
}
