package pcd.lab01.ex02.solution;

import java.util.Arrays;

public class SortingWorker extends Thread {
	
	private int[] array;
	private int from, to;
	
	public SortingWorker(String name, int[] array, int from, int to){
		super(name); //per associare al thread un nome passato come parametro
		this.array = array;
		this.from = from;
		this.to = to;
		
	}
	
	public void run() {
		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + this.getName() + " ] " + "started - sorting from " + from + " " + to);
		Arrays.sort(array, from, to + 1); //ordino l'array
		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + this.getName() + " ] " + "completed");
	}
}
