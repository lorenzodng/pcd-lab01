package pcd.lab01.ex01.solution;

import org.fusesource.jansi.Ansi.Color;
import pcd.lab01.ex01.*;

public class WordFallingAgent extends Thread {
	
	private String word;
	private int startRow, startColumn, endRow;
	private long delay;
	
	public WordFallingAgent(String word, int startRow, int startColumn, int endRow, long delay) {
		this.word = word;
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.endRow = endRow;
		this.delay = delay;
	}
	
	public void run() {
		Screen sc = Screen.getInstance();
		while (startRow < endRow) {
			sc.writeStringAt(startRow, startColumn, Color.YELLOW, word);
			try {
				Thread.sleep(delay);
			} catch (Exception ex) {}
			sc.writeStringAt(startRow, startColumn, Color.WHITE, makeBlank(word.length()));
			startRow++;
			
		}
	}

	private String makeBlank(int len) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < len; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
}
