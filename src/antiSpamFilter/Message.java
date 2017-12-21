package antiSpamFilter;

import java.util.ArrayList;

/**
 * @author Group 26
 *
 */
public class Message {
	
	private ArrayList<Rules> rules= new ArrayList<Rules>();
	private String name;
	
	public Message(String name){
		this.name=name;
	}
	
	/**
	 * This method gets the list of rules of the message.
	 * @return Rules list.
	 */
	public ArrayList<Rules> getRules(){
		return rules;
	}
	
	/**
	 * This method adds the rules to the list of rules in a message.
	 * @param rules The rules to the message.
	 */
	public void addRules(Rules rules){
		this.rules.add(rules);
	}
	
	/**
	 * This method gets the name of the message.
	 * @return the message name.
	 */
	public String getName(){
		return name;
	}
}
