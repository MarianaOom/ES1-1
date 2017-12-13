package antiSpamFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import gui.Window;

public class Filtro_Anti_Spam {

	private Window window;
	private String rules_path;
	private String ham_path;
	private String spam_path;
	private ArrayList<Rules> rules = new ArrayList<Rules>();
	private int FP = 0;
	private int FN = 0;
	private ArrayList<Message> messages = new ArrayList<Message>();

	public static void main(String[] args) {
		new Filtro_Anti_Spam();
	}

	public Filtro_Anti_Spam() {
		window = new Window(this);
		window.setVisible(true);
	}

	public void prepareRules() {
		File fileRules = new File(rules_path);
		try {
			Scanner scannerRules = new Scanner(fileRules);
			while (scannerRules.hasNextLine())
				rules.add(new Rules(scannerRules.nextLine()));
		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	public void readMessages() {
		messages = new ArrayList<Message>();
		File fileHam = new File(ham_path);
		File fileSpam = new File(spam_path);
		try {
			Scanner scannerHam = new Scanner(fileHam);
			Scanner scannerSpam = new Scanner(fileSpam);
			while (scannerSpam.hasNextLine())
				createMessage(scannerSpam.nextLine(), 0);
			while (scannerHam.hasNextLine())
				createMessage(scannerHam.nextLine(), 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int spam = 0;
		int ham = 0;
		for (Message rule : messages) {
			if (rule instanceof Ham)
				ham++;
			else
				spam++;
		}

	}

	public void createMessage(String s, int i) {
		String[] line = s.split("\\t");
		Message m;
		if (i == 1)
			m = new Ham(line[0]);
		else
			m = new Spam(line[0]);
		for (int j = 0; j < line.length; j++) 
			for (Rules rule : rules)
				if (rule.getName().contains(line[j].trim()))
					m.addRules(rule);
		messages.add(m);
	}
	
	
	public void evalute(int type){
		FP = 0;
		FN = 0;
		double weigh =0.0;
		for(Message message : messages){
			for(Rules rule : message.getRules())
				weigh+= rule.getWeight();
			if(weigh > 5 && message instanceof Ham)
				FN++;
			if(weigh < 5 && message instanceof Spam)
				FP++;
			weigh=0.0;
		}
		System.out.println("Falsos Positivos - " +FP);
		System.out.println("Falsos Negativos - " +FN);
		if(type == 1 )
			window.setManualResults(FP,FN);
		else //Falta o neto fazer
			window.setAutomaticResults(FP,FN);
	}
	
	public void printResults(){
		File[] r = (new File("Rules")).listFiles();
		String lastName = r[r.length -1].getName();
		int number = Integer.parseInt(lastName.split("s")[1].split(".c")[0]);
		number++;
		try {
			File f = new File("Rules/rules" +number +".cf");
			FileWriter fi = new FileWriter("Rules/rules" +number +".cf");
			BufferedWriter printer = new BufferedWriter(fi);
			for(Rules rule : rules){
				String line = rule.getName() +"\t" +rule.getWeight();
				printer.write(line);
				printer.newLine();
			}
			printer.write("FP:\t" +FP);
			printer.newLine();
			printer.write("FN:\t" +FN);
			printer.close();
			fi.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	public Window getWindow() {
		return window;
	}
	
	public ArrayList<Rules> getRules() {
		return rules;
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setRules_path(String rules_path) {
		this.rules_path = rules_path;
	}

	public void setHam_path(String ham_path) {
		this.ham_path = ham_path;
	}

	public void setSpam_path(String spam_path) {
		this.spam_path = spam_path;
	}

	
}
