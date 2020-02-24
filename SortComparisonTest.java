import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author Kazuhiro Tobita(18308725)
 * @version HT 2020
 * 
 * 【Questions】
 * (a) Which of the sorting algorithms does the order of input have an impact on? Why?
 *      Order of input significantly impacts insertion、quick and selection sort.
 *      For selection sort, in order to insert or order elements there are the more swaps.
 *      For quick sort the execution time is certainly effected when input is not completely random as it takes more iterations to find an element less / greater than the pivot.
 * 
 * (b) Which algorithm has the biggest difference between the best and worst performance, based on the type of input, for the input of size 1000? Why?
 *      Merge sort iterative has the biggest difference in best and worse performance. (the best:0MS, the worst:23MS)
 *      The longer execution time is when it is executing with 1000 random. 1000 few unique contains a lot of duplicates of numbers.
 *      These sorting algorithms work best on smaller data sets, or those which are already, or very nearly ordered.
 * 
 * (c) Which algorithm has the best/worst scalability, i.e., the difference in performance time based on the input size? Please consider only input files with random order for this answer.
 *      Best scalability  - Quick and merge sorts were the best scalability from my data. The biggest difference in performance time for the input files is 1MS.
 *	    Worst scalability - Insertion sort from the tests was the worst scalability, with a difference of 23MS in performance time.
 * 
 * (d) Did you observe any difference between iterative and recursive implementations of merge sort?
 *      For my tests, merge recursive runs much quicker than merge iterative in most cases, 
 *      therefore, recursive seems more efficient as it featured almost uniform scaling.
 * 
 * (e) Which algorithm is the fastest for each of the 7 input files?
 *      According my test below, merge recursive is comparably faster than the others, but these data are considerably inaccurate representations.
 
 *      
 *【ALL TIMES IN MILLISECONDS】（Time in MS）
 *							Insert		Quick		Merge Recursive		Merge Iterative		Selection
 *	10 random				1			0			0					0					0
 *	100 random				0			0			0					0					1
 *	1000 random				23			1			1					0					9
 *	1000 few unique			1			0			0					1					3
 *	1000 nearly ordered		2			1			0					0					2
 *	1000 reverse order		6			1			0					0					2
 *	1000 sorted				0			1			0					0					2
 */
 

@RunWith(JUnit4.class)
public class SortComparisonTest {
	public static final String N10 = "numbers10.txt";
	public static final String N100 = "numbers100.txt";
	public static final String N1000 = "numbers1000.txt";
	public static final String N1000D = "numbers1000Duplicates.txt";
	public static final String N1000NO = "numbersNearlyOrdered1000.txt";
	public static final String N1000R = "numbersReverse1000.txt";
	public static final String N1000S = "numbersSorted1000.txt";		
	
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new SortComparison();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */

	@Test
	public void testEmpty() {
		double[] array1 = {};
		double[] array2 = {};

		assertEquals("Checking empty array : selectionSort", Arrays.toString(array1), Arrays.toString(SortComparison.selectionSort(array2)));

		array2 = new double[] {};
		assertEquals("Checking empty array : mergeSortIterative", Arrays.toString(array1), Arrays.toString(SortComparison.mergeSortIterative(array2)));

		array2 = new double[] {};
		assertEquals("Checking empty array tree : mergeSortRecursive", Arrays.toString(array1), Arrays.toString(SortComparison.mergeSortRecursive(array2)));

		array2 = new double[] {};
		assertEquals("Checking empty array tree : quickSort", Arrays.toString(array1), Arrays.toString(SortComparison.quickSort(array2)));

		array2 = new double[] {};
		assertEquals("Checking empty array tree : insertionSort", Arrays.toString(array1), Arrays.toString(SortComparison.insertionSort(array2)));
	}

	@Test
	public void testSort() {
		double[] array1 = { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		double[] array2 = { -1000, 1, 1, 5, 5, 6, 8, 12, 105, 800 };

		assertEquals("Checking normal array : insertionSort", Arrays.toString(array2), Arrays.toString(SortComparison.insertionSort(array1)));

		array1 = new double[] { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		assertEquals("Checking normal array : selectionSort", Arrays.toString(array2), Arrays.toString(SortComparison.selectionSort(array1)));

		array1 = new double[] { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		assertEquals("Checking normal array : mergeSortIterative", Arrays.toString(array2), Arrays.toString(SortComparison.mergeSortIterative(array1)));

		array1 = new double[] { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		assertEquals("Checking normal array : mergeSortRecursive", Arrays.toString(array2), Arrays.toString(SortComparison.mergeSortRecursive(array1)));

		array1 = new double[] { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		assertEquals("Checking normal array : quickSort", Arrays.toString(array2), Arrays.toString(SortComparison.quickSort(array1)));
	}

	@Test
	public void testNull() {
		assertEquals("Checking null array : insertionSort", null, SortComparison.insertionSort(null));
		assertEquals("Checking null array : selection", null, SortComparison.selectionSort(null));
		assertEquals("Checking null array : quickSort", null, SortComparison.quickSort(null));
		assertEquals("Checking null array : mergeSort", null, SortComparison.mergeSortIterative(null));
		assertEquals("Checking null array : mergeSort", null, SortComparison.mergeSortRecursive(null));
	}

	@Test
	public void testSorted() {

		double[] array1 = { -1000, 1, 1, 5, 5, 6, 8, 12, 105, 800 };
		double[] array2 = { -1000, 1, 1, 5, 5, 6, 8, 12, 105, 800 };

		assertEquals("Checking sorted array : insertionSort", Arrays.toString(array2), Arrays.toString(SortComparison.insertionSort(array1)));

		array1 = new double[] { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		assertEquals("Checking sorted array : selectionSort", Arrays.toString(array2), Arrays.toString(SortComparison.selectionSort(array1)));

		array1 = new double[] { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		assertEquals("Checking sorted array : mergeSortIterative", Arrays.toString(array2), Arrays.toString(SortComparison.mergeSortIterative(array1)));

		array1 = new double[] { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		assertEquals("Checking sorted array : mergeSortRecursive", Arrays.toString(array2), Arrays.toString(SortComparison.mergeSortRecursive(array1)));

		array1 = new double[] { 6, 5, 8, 800, -1000, 5, 1, 1, 12, 105 };
		assertEquals("Checking sorted array : quickSort", Arrays.toString(array2), Arrays.toString(SortComparison.quickSort(array1)));
	}
	
// ----------------------------------------------------------
    
    
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment. 
     */

	public static void main(String[] args) {
		try {
			double[] a = new double[10];
			int index = 0;
			BufferedReader br = new BufferedReader(new FileReader(N10));
			String theNum = br.readLine();
			while (theNum!= null) {
				double num = Double.parseDouble(theNum);
				a[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			
			
			// test for 10 random numbers.
			
			double[] testArray = a.clone();
			long startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			long endTime = System.nanoTime();
			double runTime = (endTime-startTime) / 1000000.0;
			System.out.println("                      Insert          Quick         MergeRecursive        MergeIterative          Selection");
			System.out.print("10 random           " + runTime + "ms");
			
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime) / 1000000.0;
			System.out.print("     "+runTime+"ms");
			
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");
			
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");
			
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");
			
			// test for 100 random numbers.
			br = new BufferedReader(new FileReader(N100));
			a = new double[100];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null) {				double num = Double.parseDouble(theNum);
				a[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("100 random          "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
         	testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
	    	runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");

						
			// test for 1000 random numbers
						
			br = new BufferedReader(new FileReader(N1000));
			a = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null) {
				double num = Double.parseDouble(theNum);
				a[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
     		endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 random         "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
	    	runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");


			// test for 1000 few unique numbers
						
			br = new BufferedReader(new FileReader(N1000D));
			a = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null) {
				double num = Double.parseDouble(theNum);
				a[index] = num;
     			index++;
     			theNum = br.readLine(); 	
			}
			br.close();
			testArray = a.clone();
		    startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 few unique     "+runTime+"ms");

	    	testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

	     	testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
	    	runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");

						
			// test for 1000 nearly ordered numbers
						
			br = new BufferedReader(new FileReader(N1000NO));
			a = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null)
			{
				double num = Double.parseDouble(theNum);
				a[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 nearly ordered "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
	    	System.out.print("            "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
		    endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");

						
			// test for 1000 reverse numbers
						
			br = new BufferedReader(new FileReader(N1000R));
			a = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null) {
				double num = Double.parseDouble(theNum);
				a[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
		    testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 reverse order  "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
	    	endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

		    testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");

	    	testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");
						
					
			// test for 1000 sorted numbers
						
			br = new BufferedReader(new FileReader(N1000S));
			a = new double[1000];
			index = 0;
			theNum = br.readLine();
			while(theNum!= null) {
				double num = Double.parseDouble(theNum);
				a[index] = num;
				index++;
				theNum = br.readLine(); 	
			}
			br.close();
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.insertionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("1000 sorted         "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.quickSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("     "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortRecursive(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("         "+runTime+"ms");

			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.mergeSortIterative(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.print("            "+runTime+"ms");
			
			testArray = a.clone();
			startTime = System.nanoTime();
			testArray = SortComparison.selectionSort(testArray);
			endTime = System.nanoTime();
			runTime = (endTime-startTime)/1000000.0;
			System.out.println("           "+runTime+"ms");
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}