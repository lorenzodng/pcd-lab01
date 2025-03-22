package pcd.lab01.ex01;

import org.fusesource.jansi.Ansi.Color;
//estende la classe thread, quindi ogni istanza di questa classe è un thread
public class CountDownAgent extends Thread {

	private int countDown;
	
	public CountDownAgent(int countDown) {
		this.countDown = countDown;
	}

	//metodo eseguito automaticamente quando l'istanza esegue il metodo "start()"
	public void run() {
		Screen sc = Screen.getInstance();
		while (countDown > 0) { //finchè non si è raggiunto lo 0, faccio il countdown
			sc.writeStringAt(1, 1, Color.WHITE, "In " + countDown + " seconds the sentence below will fall..."); //scrivo sempre nella stessa posizione dello schermo il countdown
			try {
				Thread.sleep(1000); //...ed ogni secondo aggiorno il countdown
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			countDown--;
		}
	}
}
