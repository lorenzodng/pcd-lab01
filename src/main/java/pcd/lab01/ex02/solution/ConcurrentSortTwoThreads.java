package pcd.lab01.ex02.solution;

import java.util.*;

public class ConcurrentSortTwoThreads {

	static final int VECTOR_SIZE = 400_000_000;
	
	public static void main(String[] args) {

		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Generating array...");
		int[] array = genArray(VECTOR_SIZE); //genero un array

		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Array generated.");
		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Spawning workers to do sorting (" + VECTOR_SIZE + " elements)...");
	
		int middle = array.length/2;
		SortingWorker w1 = new SortingWorker("worker-1", array, 0, middle); //creo il primo thread responsabile della prima metà dell'array
		SortingWorker w2 = new SortingWorker("worker-2", array, middle+1, array.length - 1); //creo il secondo thrad responsabile della seconda metà dell'array

		long t0 = System.currentTimeMillis(); //prendo il tempo corrente in millisecondi
		w1.start(); //mando in esecuzione il primo thread
		w2.start(); //mando in esecuzione il secondo thread

		MergingWorkerTwoParts m = new MergingWorkerTwoParts("merger", array, w1, w2); //creo il thread responsabile dell'ordinamento finale dell'array
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
