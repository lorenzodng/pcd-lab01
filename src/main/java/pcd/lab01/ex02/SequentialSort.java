package pcd.lab01.ex02;

import java.util.*;

public class SequentialSort {

	static final int VECTOR_SIZE = 400_000_000;

	public static void main(String[] args) {

		System.out.println("Generating array...");
		int[] array = genArray(VECTOR_SIZE);

		System.out.println("Array generated.");
		System.out.println("Sorting (" + VECTOR_SIZE + " elements)...");

		long t0 = System.currentTimeMillis();
		Arrays.sort(array, 0, array.length);
		long t1 = System.currentTimeMillis();

		System.out.println("Done. Time elapsed: " + (t1 - t0) + " ms");
	}


	private static int[] genArray(int n) {
		Random gen = new Random(System.currentTimeMillis());
		int[] array = new int[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = gen.nextInt();
		}
		return array;
	}
}
