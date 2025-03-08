package pcd.lab01.ex01.solution;

import java.util.Random;
import org.fusesource.jansi.Ansi.Color;
import pcd.lab01.ex01.*;

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
		countDownThread.start();
		try {
			countDownThread.join();
		} catch (Exception ex) {}


		Random gen = new Random(1000);
		
		String[] words = sentence.split(" ");
		for (String w: words) {
			new WordFallingAgent(w, startRow, startColumn, endRow, gen.nextInt(40)).start();
			startColumn += w.length() + 1;
		}
		
	}

}
