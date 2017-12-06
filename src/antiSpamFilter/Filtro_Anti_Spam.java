package antiSpamFilter;

import java.io.File;
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
		else
			window.setAutomaticResults(FP,FN);
	}
	
	
}
