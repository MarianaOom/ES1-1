package antiSpamFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import gui.Window;

/**
 * @author Mariana Oom
 *
 */

/**
 * @author Mariana Oom
 *
 */
/**
 * @author Mariana Oom
 *
 */
public class Anti_Spam_Filter {

	private Window window;
	private ArrayList<Rules> rules = new ArrayList<Rules>();
	private int FP = 0;
	private int FN = 0;
	private ArrayList<Message> messages = new ArrayList<Message>();

	public static void main(String[] args) {
		new Anti_Spam_Filter();
	}

	/**
	 * 
	 */
	public Anti_Spam_Filter() {
		window = new Window(this);
		window.setVisible(true);
	}

	public ArrayList<Rules> getRules() {
		return rules;
	}

	/**
	 * @param path
	 */
	public void prepareRules(String path) {
		File fileRules = new File(path);
		try {
			Scanner scannerRules = new Scanner(fileRules);
			System.out.println(" Rules - " + scannerRules.hasNextLine());
			while (scannerRules.hasNextLine())
				rules.add(new Rules(scannerRules.nextLine()));
		} catch (Exception e) {
			System.out.println("Failed to locate file!");
		}
	}
	
	/**
	 * @param path
	 */
	public void readHam(String path) {
		File fileHam = new File(path);
		try {
			Scanner scannerHam = new Scanner(fileHam);
			while (scannerHam.hasNextLine())
				createMessage(scannerHam.nextLine(), 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @param path
	 */
	public void readSpam(String path) {
		File fileSpam = new File(path);
		try {
			Scanner scannerSpam = new Scanner(fileSpam);
			while (scannerSpam.hasNextLine()) {
				createMessage(scannerSpam.nextLine(), 0);
			}
		} catch (Exception e) {
		}

	}

	/**
	 * @param s
	 * @param i
	 */
	public void createMessage(String s, int i) {
		String[] line = s.split("\\t");
		Message m;
		if (i == 1)
			m = new Ham(line[0]);
		else
			m = new Spam(line[0]);
		for (int k = 1; k < line.length; k++)
			for (Rules rule : rules) {
				if (rule.getName().contains(line[k].trim()))
					m.addRules(rule);
			}
		messages.add(m);
	}

	/**
	 * 
	 */
	public void evaluate() {
		FN = 0;
		double weight = 0.0;
		for (Message message : messages) {
			for (Rules rule : message.getRules())
				weight += rule.getWeight();
			if (weight > 5 && message instanceof Ham)
				FN++;
			if (weight < 5 && message instanceof Spam)
				FP++;
			weight = 0.0;
		}
		System.out.println("Falsos Positiovos - " + FP);
		System.out.println("Falsos Negativos  - " + FN);
		window.setManualResults(FP, FN);

	}

	/**
	 * 
	 */
	public void automaticEvaluation() {
		try {
			new AntiSpamFilterAutomaticConfiguration(this);
			Scanner scann = new Scanner(
					new File("experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.NSGAII.rs"));
			String[] result = scann.nextLine().split(" ");
			for (int i = 0; i < result.length; i++)
				rules.get(i).setWeight(Double.parseDouble(result[i]));
			for (Rules ru : rules)
				System.out.println(ru.getWeight());
			window.setAutomaticResults(Integer.parseInt(result[result.length - 2].split(": ")[1]),
					Integer.parseInt(result[result.length - 1].split(": ")[1]));
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	/**
	 * @param type
	 */
	public void printResults(int type) {
		File[] r = (new File("Rules")).listFiles();
		String lastName = r[r.length - 1].getName();
		int number = Integer.parseInt(lastName.split("s")[1].split(".c")[0]);
		number++;
		try {
			File f;
			if (type == 0)
				f = new File("Rules/rules" + number + ".cf");
			else
				f = new File("Rules/Automatic_rules" + number + ".cf");
			FileWriter fi = new FileWriter("Rules/rules" + number + ".cf");
			BufferedWriter printer = new BufferedWriter(fi);
			for (Rules rule : rules) {
				String line = rule.getName() + "\t" + rule.getWeight();
				printer.write(line);
				printer.newLine();
			}
			printer.write("FP:\t" + FP);
			printer.newLine();
			printer.write("FN:\t" + FN);
			printer.close();
			fi.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param x
	 * @return  
	 */
	public int[] evaluateAutomatic(double[] x) {
		FP = 0;
		FN = 0;
		for (int i = 0; i < x.length; i++)
			rules.get(i).setWeight(x[i]); 
		double weight = 0.0;
		for (Message message : messages) {
			for (Rules rule : message.getRules())
				weight += rule.getWeight();
			if (weight > 5 && message instanceof Ham)
				FN++;
			if (weight < 5 && message instanceof Spam)
				FP++;
			weight = 0.0;
		}
		int[] result = new int[2];
		result[0] = FP;
		result[1] = FN;
		return result;
	}

}
