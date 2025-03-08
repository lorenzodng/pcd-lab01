package pcd.lab01.ex02.solution;

import java.util.*;

public class ConcurrentSortGeneral {

	static final int VECTOR_SIZE = 400_000_000;
	static final boolean isDebugging = false;

	public static void main(String[] args) {

		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Generating array...");
		int[] array = genArray(VECTOR_SIZE);

		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Array generated.");
		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Spawning workers to do sorting (" + VECTOR_SIZE + " elements)...");

		int nAgents = Runtime.getRuntime().availableProcessors() + 1;
		int jobSize = array.length/nAgents;
		int from = 0;
		int to = jobSize - 1;

		long t0 = System.currentTimeMillis();
		List<SortingWorker> workers = new ArrayList<>();
		for (int i = 0; i < nAgents - 1; i++) {
			SortingWorker w = new SortingWorker("worker-"+(i+1), array, from, to);
			w.start();
			workers.add(w);
			from = to + 1;
			to = to + jobSize;
		}
		SortingWorker w = new SortingWorker("worker-"+nAgents, array, from, array.length - 1);
		w.start();
		workers.add(w);

		MergingWorkerGeneral m = new MergingWorkerGeneral("merger", array, workers);
		m.start();
		long t1 = System.currentTimeMillis();

		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + Thread.currentThread().getName() + " ] " + "Done. Time elapsed: " + (t1 - t0) + " ms");

	}


	private static int[] genArray(int n) {
		Random gen = new Random(System.currentTimeMillis());
		int array[] = new int[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = gen.nextInt();
		}
		return array;
	}
}
