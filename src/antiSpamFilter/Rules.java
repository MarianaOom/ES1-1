package antiSpamFilter;

public class Rules {
	
	private String name;
	private double weight=0;
	
	public Rules(String n){
		name=n;
	}
	public double getWeight(){
		return weight;
	}
	
	public void setWeight(double d){
		this.weight=d;
	}
	
	public String getName(){
		return name;
	}
}
