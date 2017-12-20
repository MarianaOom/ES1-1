package antiSpamFilter;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class Anti_Spam_FilterTest {
	
	Anti_Spam_Filter filter = new Anti_Spam_Filter();
	Rules rule = new Rules("teste");
	Message message = new Message("teste");
	
	@Test
	public void testSetweight(){
		rule.setWeight(5);
	}
	
	@Test 
	public void testGetName(){
		rule.getName(); 
	}
	
	@Test
	public void testGetweight(){
		rule.getWeight();
	}
	
	@Test
	public void testGetRulesMessage(){
		message.getRules();
	}
	
	@Test 
	public void testAddRules(){
		message.addRules(rule);
	}
	
	@Test 
	public void testeGetMessage(){
		message.getName();
	}

	@Test
	public void testMain() {
		filter.main(new String[0]);
	}


	@Test
	public void testGetRules() {
		filter.getRules();
	}

	@Test
	public void testPrepareRules() {
		filter.prepareRules("rules0.cf");
		filter.prepareRules("Rules/rules0.cf");
	}

	@Test
	public void testReadHam() { 
		filter.readHam("Rules/ham.log");
		filter.readHam("ham.log");

	}
	
	@Test
	public void testReadSpam() {
		filter.readSpam("Rules/spam.log");
		filter.readSpam("spam.log"); 
		assertTrue(true);
	}

	@Test
	public void testCreateMessage() {
		filter.createMessage("teste", 0);
		filter.createMessage("teste", 1);
	}
 
	@Test
	public void testEvaluate() {
		filter.evaluate();
	}

//	@Test
//	public void testLaunchAuto() {
//		filter.launcAuto();
//	}

	@Test
	public void testAutomaticEvaluation() {
		//filter.launcAuto(); 
		filter.automaticEvaluation();  
	}
   
	@Test
	public void testPrintResults() {
		filter.printResults(0);
		filter.printResults(1); 
	}

	@Test
	public void testEvaluateAutomatic() {
		double[] x = new double[335];
		for (int i = 0; i < x.length; i++) {
			x[i]=Math.random();
		}
		filter.evaluateAutomatic(x); 
	}

	@Test
	public void testEquals() {
		Object a = "Teste"; 
		a.equals("Teste");
	}

	@Test
	public void testToString() {
		Object a = 5;
		a.toString();
	}

}
