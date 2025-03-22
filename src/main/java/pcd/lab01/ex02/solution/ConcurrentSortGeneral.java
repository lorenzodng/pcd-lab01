package pcd.lab01.ex02.solution;

import java.util.*;

public class ConcurrentSortGeneral {

	static final int VECTOR_SIZE = 400_000_000;

	public static void main(String[] args) {

		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Generating array...");
		int[] array = genArray(VECTOR_SIZE); //genero un array

		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Array generated.");
		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Spawning workers to do sorting (" + VECTOR_SIZE + " elements)...");

		int nAgents = Runtime.getRuntime().availableProcessors() + 1; //definisco il numero di futuri thread come il numero di processori disponibili sul mio dispositivo + 1 (valore di tolleranza)
		int jobSize = array.length/nAgents; //distribuisco equamente l'array tra i thread
		int from = 0;
		int to = jobSize - 1;

		long t0 = System.currentTimeMillis(); //prendo il tempo corrente in millisecondi
		List<SortingWorker> workers = new ArrayList<>(); //creo una lista di thread
		for (int i = 0; i < nAgents - 1; i++) { //creo tanti thread quanti definiti in precedenza con "nAgents"
			SortingWorker w = new SortingWorker("worker-"+(i+1), array, from, to);
			w.start(); //mando ciascun thread in esecuzione
			workers.add(w); //aggiungo ciascun thread alla lista "workers"
			from = to + 1; //aggiorno parte iniziale dell'array a cui il thread successivo deve essere associato
			to = to + jobSize; //aggiorno la parte finale dell'array a cui il thread successivo deve essere associato
		}
		SortingWorker w = new SortingWorker("worker-"+nAgents, array, from, array.length - 1);
		w.start();
		workers.add(w);

		MergingWorkerGeneral m = new MergingWorkerGeneral("merger", array, workers); //creo un thread per unire le parti ordinate
		m.start(); //mando in esecuzione il thread
		long t1 = System.currentTimeMillis();

		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Done. Time elapsed: " + (t1 - t0) + " ms");

	}

	//metodo per creare un array di numeri casuali
	private static int[] genArray(int n) {
		Random gen = new Random(System.currentTimeMillis());
		int array[] = new int[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = gen.nextInt();
		}
		return array;
	}
}
