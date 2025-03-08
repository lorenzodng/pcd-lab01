package pcd.lab01.ex02.solution;

public class MergingWorkerTwoParts extends Thread {
	
	private int[] array;
	private SortingWorker w1,w2;
	
	public MergingWorkerTwoParts(String name, int[] array, SortingWorker w1, SortingWorker w2){
		super(name);
		this.array = array;
		this.w1 = w1;
		this.w2 = w2;		
	}
	
	public void run() {
		try {
			w1.join();
			w2.join();
			System.out.println("subparts sorted, going to merge...");
			long t0 = System.currentTimeMillis();
			int[] mergedArray = merge(array);
			for(int i = 0; i < mergedArray.length; i++) {
				array[i] = mergedArray[i];
			}
			long t1 = System.currentTimeMillis();
			System.out.println("completed -- " + (t1 - t0) + " ms for merging.");
		} catch(InterruptedException ex) {
			System.out.println("interrupted.");
		}
	}
	
	private int[] merge(int[] v) {
		int[] vnew = new int[v.length];
		int i1 = 0;
		int max1 = v.length/2;
		int i2 = max1;
		int max2 = v.length;
		int i3 = 0;
		while ((i1 < max1) && (i2 < max2)) {
			if (v[i1] < v[i2]) {
				vnew[i3] = v[i1];
				i1++;
			} else {
				vnew[i3] = v[i2];
				i2++;
			}
			i3++;
		}
		if (i1 < max1) {
			while (i1 < max1) {
				vnew[i3] = v[i1];
				i1++;
				i3++;
			}
		} else {
			while ((i2 < max2)) {
				vnew[i3] = v[i2];
				i2++;
				i3++;
			}
		}
		return vnew;
	}

}
