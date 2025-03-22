package pcd.lab01.ex01;

import org.fusesource.jansi.Ansi.Color;
//estende la classe thread, quindi ogni istanza di questa classe è un thread
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

	//metodo eseguito automaticamente quando l'istanza esegue il metodo "start()"
	public void run() {
		Screen sc = Screen.getInstance();
		while (startRow < endRow) { //finchè non si raggiunge una determinata linea di posizione...
			sc.writeStringAt(startRow, startColumn, Color.YELLOW, word); //scrivo la parola in una determinata posizione sullo schermo
			try {
				Thread.sleep(delay); //lascio in attesa il thread rappresentativo della parola
			} catch (Exception ex) {}
			sc.writeStringAt(startRow, startColumn, Color.WHITE, makeBlank(word.length())); //successivamente, elimino la parola
			startRow++; //procedo verso il basso
		}

	}

	//metodo per cancellare una parola sullo schermo
	private String makeBlank(int len) {
		StringBuffer sb = new StringBuffer(""); //creo uno string buffer vuoto
		for (int i = 0; i < len; i++) { //per ogni carattere della parola...
			sb.append(" "); //inserisco uno spazio vuoto
		}
		return sb.toString();
	}
}
