package pcd.lab01.ex01;

import java.util.Random;
import org.fusesource.jansi.Ansi.Color;

public class TestFallingWords {

	static final int COUNT_DOWN_IN_SECS = 5;
	
	public static void main(String[] args) {
		Screen sc = Screen.getInstance();
		sc.clear();

		String sentence = "This is a long falling sentence from the top of the screen to the bottom";

		int startRow = 3;
		int startColumn = 10;
		int endRow = 20;
		
		sc.writeStringAt(startRow, startColumn, Color.YELLOW, sentence);

		Thread countDownThread = new CountDownAgent(COUNT_DOWN_IN_SECS);
		countDownThread.start(); //faccio partire il countdown
		try {
			countDownThread.join(); //mi assicuro che il thread del countdown abbia terminato prima di andare avanti
		} catch (Exception ex) {}

		Random gen = new Random(1000); //genero un numero casuale per bloccare i thread rappresentativi di ogni parola
		
		String[] words = sentence.split(" "); //prendo la stringa e la suddivido in tutte le parole con cui è composta
		for (String w: words) { //per ogni parola...
			Thread wordFallingThread= new WordFallingAgent(w, startRow, startColumn, endRow, gen.nextInt(40)); //...creo un thread
			wordFallingThread.start(); //... e faccio partire lo spostamento della parola sullo schermo
			startColumn += w.length() + 1; //sposto più a destra ogni parola successiva  rispetto alla precedente
		}
	}

}
