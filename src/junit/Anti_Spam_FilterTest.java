package junit;

import static org.junit.Assert.*;
import org.junit.Test;

import antiSpamFilter.Anti_Spam_Filter;
import antiSpamFilter.Ham;
import antiSpamFilter.Message;
import antiSpamFilter.Rules;
import antiSpamFilter.Spam;

public class Anti_Spam_FilterTest {

	Anti_Spam_Filter filter = new Anti_Spam_Filter();
	Rules rule = new Rules("teste");
	Message message = new Message("teste");

	@Test
	public void main() {
		filter = new Anti_Spam_Filter();
		assertNotNull(filter);
	}

	/** Tests whether rule's setWeight() function correctly sets the input's value;
	 * 
	 */
	@Test
	public void testSetweight() {
		rule.setWeight(5.0);
		assertTrue(rule.getWeight()==5.0);
	}

	/** Tests whether rule's getName() function correctly gets the name of the rule;
	 * 
	 */
	@Test
	public void testGetName() {
		assertEquals(rule.getName(), "teste");
	}

	/** Tests whether rule's getWeight() function correctly gets the weight of the rule;
	 * 
	 */
	@Test
	public void testGetweight() {
		rule.getWeight();
	}

	/** Tests whether Message's getRules() function correctly gets the rule array of the message;
	 * 
	 */
	@Test
	public void testGetRulesMessage() {
		message.getRules();
	}

	/** Tests whether Message's addRules() function correctly adds the rule to the rule's array of the message;
	 * 
	 */
	@Test
	public void testAddRules() {
		message.addRules(rule);
		assertEquals(message.getRules().size(), 1);
	}

	/** Tests whether Message's getName() function correctly gets the rule array of the message;
	 * 
	 */
	@Test
	public void testeGetMessage() {

		assertEquals(message.getName(),"teste");
	}

	/** Tests whether Anti_Spam_Filter's getRules() function correctly gets the rule array list of the object;
	 * 
	 */
	@Test
	public void testGetRules() {
		filter.getRules();
	}

	/** Tests whether Anti_Spam_Filter's prepareRules() function correctly reads and creates the rule objects;
	 * 
	 */
	@Test
	public void testPrepareRules() {
		filter.prepareRules("Scrum/rules0.cf");
		filter.prepareRules("rulesTeste.cf");
		assertEquals(filter.getRules().size(), 335);
	}

	/**  Tests whether Anti_Spam_Filter's readHam() function correctly reads and creates the Ham objects;
	 * 
	 */
	@Test
	public void testReadHam() {
		filter.readHam("Rules/ham.log");
		filter.readHam("ham.log");
		int ham = 0;
		for (Message message : filter.getMessages())
			if (message instanceof Ham)
				ham++;
		assertEquals(ham, 695);

	}

	/** Tests whether Anti_Spam_Filter's readSpam() function correctly reads and creates the Spam objects;
	 * 
	 */
	@Test
	public void testReadSpam() {
		filter.readSpam("Rules/spam.log");
		filter.readSpam("spam.log");
		int spam = 0;
		for (Message message : filter.getMessages()) 
			if (message instanceof Spam)
				spam++;
		assertEquals(spam, 239);
	}

	/** Tests whether Anti_Spam_Filter's CreateMessage() function correctly reads and creates the message objects;
	 * 
	 */
	@Test
	public void testCreateMessage() {
		filter.createMessage("teste", 0);
		assertEquals(filter.getMessages().get(filter.getMessages().size() - 1).getName(), "teste");
		assertTrue(filter.getMessages().get(filter.getMessages().size() - 1) instanceof Spam);
		filter.createMessage("teste", 1);
		assertEquals(filter.getMessages().get(filter.getMessages().size() - 1).getName(), "teste");
		assertTrue(filter.getMessages().get(filter.getMessages().size() - 1) instanceof Ham);

	}

	/** Tests whether Anti_Spam_Filter's evaluate() function calculates false positives and false negatives;
	 * 
	 */
	@Test
	public void testEvaluate() {
		double[] zero = new double[635];
		for (int i = 0; i < zero.length; i++) {
			zero[i] = 0;
		}
		filter.prepareRules("rulesZero.cf");
		filter.readSpam("spam.log");
		filter.readHam("ham.log");
		filter.evaluateAutomatic(zero);
		filter.evaluate();
		assertEquals(filter.getFN(), 239);
		assertEquals(filter.getFP(), 0);
		for (int i = 0; i < zero.length; i++) {
			zero[i] = 5;
		}
		filter.prepareRules("rulesZero.cf");
		filter.readSpam("spam.log");
		filter.readHam("ham.log");
		filter.evaluateAutomatic(zero);
		filter.evaluate();
		assertEquals(filter.getFN(), 239);
		assertEquals(filter.getFP(), 694);
	}

	/** Tests whether Anti_Spam_Filter's evaluate() function calculates false positives and false negatives;
	 * 
	 */
	@Test
	public void testAutomaticEvaluation() {
		double[] zero = new double[635];
		for (int i = 0; i < zero.length; i++) {
			zero[i] = 5;
		}
		filter.prepareRules("rulesTeste.cf");
		filter.evaluateAutomatic(zero);
		filter.printResults();
		filter.automaticEvaluation();
		System.out.println(filter.getFN() +" - " +filter.getFP());
		assertEquals(filter.getFN(), 0);
		assertEquals(filter.getFP(), 0);
	}

	/** Tests whether Anti_Spam_Filter's printResults() function correctly prints the results;
	 * 
	 */
	@Test
	public void testPrintResults() {
		double[] zero = new double[635];
		for (int i = 0; i < zero.length; i++) {
			zero[i] = 0;
		}
		filter.prepareRules("rulesTeste.cf");
		filter.evaluateAutomatic(zero);
		filter.printResults();
		assertEquals(filter.getFN(), 0);
		assertEquals(filter.getFP(), 0);
	}

	/** Tests whether Anti_Spam_Filter's evaluateAutomatic() function correctly prepares the array list of rules according with the
	 *  automatically generated weights so it may be calculated.
	 * 
	 */
	@Test
	public void testEvaluateAutomatic() {
		double[] zero = new double[635];
		for (int i = 0; i < zero.length; i++) {
			zero[i] = 5;
		}
		filter.prepareRules("rulesTeste.cf");
		filter.evaluateAutomatic(zero);
		assertEquals(filter.getFN(), 0);
		assertEquals(filter.getFP(), 0);
		//filter.launcAuto();

	}

	/** Tests whether Object fuction equals() function correctly identifies equal objects;
	 * 
	 */
	@Test
	public void testEquals() {
		Message m = new Message("Teste");
		Message m1 = new Message("Teste");
		assertTrue(m.getName().equals(m1.getName()) && m.getRules().equals(m1.getRules()));
	}

	/** Tests whether Object fuction toString() function correctly turns an object to a String;
	 * 
	 */
	@Test
	public void testToString() {
		int a = 5;
		assertEquals("" + a, "5");
	}

}
