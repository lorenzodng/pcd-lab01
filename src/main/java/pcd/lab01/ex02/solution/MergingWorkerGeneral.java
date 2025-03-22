package pcd.lab01.ex02.solution;

import java.util.List;

public class MergingWorkerGeneral extends Thread {
	
	private int[] array;
	private List<SortingWorker> workers;
	
	public MergingWorkerGeneral(String name, int[] array, List<SortingWorker> workers){
		super(name);
		this.array = array;
		this.workers = workers;
	}
	
	public void run() {
		try {
			for (SortingWorker w: workers) { //per ogni thread della lista "workers"
				w.join(); //aspetto che ognuno abbia terminato l'ordinamento
			}
			System.out.println("[ " + System.currentTimeMillis() +  " ][ " + this.getName() + " ] " + "subparts sorted, going to merge...");
			long t0 = System.currentTimeMillis();
			int nParts = workers.size();
			array = merge(array, nParts); //ordino le diverse parti dell'array (già ordinate a loro volta), ottenendo un array completamente ordinato
			long t1 = System.currentTimeMillis();
			System.out.println("[ " + System.currentTimeMillis() +  " ][ " + this.getName() + " ] " + "completed -- " + (t1 - t0) + " ms for merging.");
		} catch(InterruptedException ex) {
			System.out.println("interrupted.");
		}
	}

	//metodo standard per ordinare più parti ordinate di un array
	private int[] merge(int[] v, int nParts) {
		int[] vnew = new int[v.length];

		int partSize = v.length/nParts;
		int from = 0; 

		int[] indexes = new int[nParts];
		int[] max = new int[nParts];
		for (int i = 0; i < indexes.length - 1; i++) {
			indexes[i] = from;
			max[i] = from + partSize;
			from = max[i];
		}
		indexes[indexes.length - 1] = from;
		max[indexes.length - 1] = v.length;

		int i3 = 0;
		boolean allFinished = false;
		while (!allFinished) {
			
			int min = Integer.MAX_VALUE;
			int index = -1;
			for (int i = 0; i < indexes.length; i++) {
				if (indexes[i] < max[i]) {
					if (v[indexes[i]] < min) {
						index = i;
						min = v[indexes[i]];
					}
				}
			}
			
			if (index != -1) {
				vnew[i3] = v[indexes[index]];
				indexes[index]++;
				i3++;
			} else {
				allFinished = true;
			}
		}
		return vnew;
	}

}
