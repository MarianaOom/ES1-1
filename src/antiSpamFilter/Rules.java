package antiSpamFilter;

/**
 * @author Group 26
 *
 */
public class Rules {
	
	private String name;
	private double weight=0;
	
	public Rules(String n){
		name=n;
	}
	/**
	 * This method gets the weight of a rule.
	 * @return rules weight
	 */
	public double getWeight(){
		return weight;
	}
	
	/**
	 * This method sets the rules weight.
	 * @param d The weight given to the rule.
	 */
	public void setWeight(double d){
		this.weight=d;
	}
	
	/**
	 * this method gets the rule name.
	 * @return rule name.
	 */
	public String getName(){
		return name;
	}
}
