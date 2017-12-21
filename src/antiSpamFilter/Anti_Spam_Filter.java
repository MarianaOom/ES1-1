package antiSpamFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import gui.Window;

/**
 * @author Group 26
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
	 * This method initiates the application window for the antiSpamFilter.
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
	 * This method locates the rules file and reads it. warns user if it can't
	 * find the file.
	 * 
	 * @param path
	 *            name the path for the rules file.
	 */
	public void prepareRules(String path) {
		rules = new ArrayList<Rules>();
		File fileRules = new File(path);
		try {
			Scanner scannerRules = new Scanner(fileRules);
			while (scannerRules.hasNextLine()) {
				String temp = scannerRules.nextLine();
				if (temp.contains(" ")) {
					Rules rule = new Rules(temp.split(" ")[0]);
					rule.setWeight(Double.parseDouble(temp.split(" ")[1]));
					rules.add(rule);
				} else
					rules.add(new Rules(temp));
			}
			scannerRules.close();
		} catch (Exception e) {
			System.out.println("Failed to locate RULES file!");
		}
	}

	/**
	 * This method locates the HAM file and reads it. warns user if it can't
	 * find the file.
	 * 
	 * @param path
	 *            name the path for the HAM file.
	 */
	public void readHam(String path) {
		File fileHam = new File(path);
		try {
			Scanner scannerHam = new Scanner(fileHam);
			while (scannerHam.hasNextLine())
				createMessage(scannerHam.nextLine(), 1);
		} catch (Exception e) {
			System.out.println("Failed to locate HAM file!");
		}
	}

	/**
	 * This method locates the SPAM file and reads it. warns user if it can't
	 * find the file.
	 * 
	 * @param path
	 *            path for the SPAM file.
	 */
	public void readSpam(String path) {
		File fileSpam = new File(path);
		try {
			Scanner scannerSpam = new Scanner(fileSpam);
			while (scannerSpam.hasNextLine()) {
				createMessage(scannerSpam.nextLine(), 0);
			}
		} catch (Exception e) {
			System.out.println("Failed to locate SPAM file!");
		}

	}

	/**
	 * This method turns each string read in the above functions into an Message
	 * object, be it Ham or Spam. Gets the rules that evaluate the given message
	 * and copies them into the Rules array field.
	 * 
	 * @param s
	 *            String type that splits the line in the file.
	 * @param i
	 *            Integer that defines if we are reading the HAM or SPAM file.
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
	 * This method evaluates the messages in the message list. For each message
	 * reads it's rules and calculates the weight of the message, if the message
	 * is from the SPAM file and the weight is less than five it adds one to the
	 * false negatives, if the message is from the HAM file and the weight is
	 * more than five it adds one to the false positives.
	 *
	 */
	public void evaluate() {
		FN = 0;
		FP = 0;
		double weight = 0.0;
		for (Message message : messages) {
			for (Rules rule : message.getRules())
				weight += rule.getWeight();
			if (weight >= 5 && message instanceof Ham)
				FP++;
			if (weight < 5 && message instanceof Spam)
				FN++;
			weight = 0.0;
		}
		// if (i == 0)
		// window.setManualResults(FP, FN);
		// else
		// window.setAutomaticResults(FP, FN);
	}

	/**
	 * This method launches the AntiSpamFilterAutomaticConfiguration object, so
	 * it may be possible to calculate the weights automatically.
	 * 
	 */
	public void launcAuto() {
		try {
			AntiSpamFilterAutomaticConfiguration anti = new AntiSpamFilterAutomaticConfiguration(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method reads referenceFronts's AntiSpamFilterProblem.NSGAII.rs file
	 * which contains the results previously calculated with the automatically
	 * generated weight array. 
	 * It calculates the false negatives and false positives relative to each of the results of the automatic generation
	 * It sends the result to the gui so it may be
	 * displayed to the user and generates latex's pdf and R' Boxplot.
	 * 
	 */
	public void automaticEvaluation() {
		try {
			String[] rar = new String[635];
			ArrayList<Integer> result = new ArrayList<Integer>();

			Scanner scanne = new Scanner(
					new File("experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.NSGAII.rs"));

			int j = 0;
			while (scanne.hasNextLine()) {
				rar[j] = scanne.nextLine();
				for (int i = 0; i < rules.size(); i++) {
					String[] temp = rar[j].split(" ");
					rules.get(i).setWeight(Double.parseDouble(temp[i]));
				}
				evaluate();
				result.add(FN);
				j++;
			}
			scanne.close();
			int min = result.get(0);
			int best = 0;
			for (int i = 0; i < result.size(); i++)
				if (result.get(i) < min) {
					min = result.get(i);
					best = i;
				}
			for (int i = 0; i < rules.size(); i++) {
				String[] temp = rar[best].split(" ");
				rules.get(i).setWeight(Double.parseDouble(temp[i]));
			}
			evaluate();
			window.setAutomaticResults(FP, FN);

			Process process = new ProcessBuilder("D:\\Programas\\R-3.4.3\\bin\\RScript.exe", "HV.Boxplot.R")
					.directory(new File("experimentBaseDirectory\\AntiSpamStudy\\R")).start();
			Process process2 = new ProcessBuilder(
					"D:\\Programas\\Nova pasta (2)\\miktex\\bin\\x64\\miktex-pdflatex.exe", "AntiSpamStudy.tex")
							.directory(new File("experimentBaseDirectory\\AntiSpamStudy\\latex")).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method saves the results of the evaluations. It is invoked in the
	 * class window when the button "save" is pressed
	 * 
	 */
	public void printResults() {
		try {
			FileWriter fi = new FileWriter("AntiSpamConfigurationForLeisureMailbox/rules.cf", false);
			BufferedWriter printer = new BufferedWriter(fi);
			for (Rules rule : rules) {
				String line = rule.getName() + " " + rule.getWeight();
				printer.write(line);
				printer.newLine();
			}
			printer.close();
			fi.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method sets the rules weight with the values given in the double
	 * array passed as an attribute. Then the method evaluate is called for the
	 * message to be evaluated.
	 * 
	 * @param x
	 *            A double vector that set's all the weights of the rules in the
	 *            automatic evaluation.
	 * @return The results of the false positives an false negatives in in a
	 *         vector with both results.
	 */
	public int[] evaluateAutomatic(double[] x) {
		FP = 0;
		FN = 0;
		for (int i = 0; i < rules.size(); i++)
			rules.get(i).setWeight(x[i]);
		evaluate();
		int[] result = new int[2];
		result[0] = FP;
		result[1] = FN;
		return result;
	}

	/**
	 * @return Returns the value of the FP variable which contains the amount of
	 *         false positives
	 */
	public int getFP() {
		return FP;
	}

	/**
	 * @return Returns the value of the FN variable which contains the amount of
	 *         false negaatives
	 */
	public int getFN() {
		return FN;
	}

	/**
	 * @return Returns the array list of messages
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}

}
