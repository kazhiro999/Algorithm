

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Kazuhiro Tobita(18308725)
 *  @version HT 2020
 */

 class SortComparison {
    
    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
	 
    static double [] insertionSort (double a[]) {
    	//todo: implement the sort
    	if(a == null) return null;
    	
    	double temp;
    	for(int i = 1; i < a.length; i++) {
    		for(int j = i; j > 0; j--) {
    			if(a[j] < a[j-1]) {
    				temp = a[j];
    				a[j] = a[j-1];
    				a[j-1] = temp;
    			}
    		}
    	}
    	return a;
    } //end insertionsort
    
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    
    static double [] selectionSort (double a[]) {
    	if(a == null) return null;
    	
    	int n = a.length;
    	//one by one move boundary of unsorted subarray
        for(int i = 0; i < n - 1; i++) {
        	//find the minimum element in unsorted array
        	int min_idx = i;
        	
            for(int j = i + 1; j < n; j++) 
                if(a[j] < a[min_idx]) 
                	min_idx = j;
                
                //swap the found minimum element with the first element
            double temp = a[min_idx];
            a[min_idx] = a[i];
            a[i] = temp; 
        }
        return a;
    } //end selectionsort
    
    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    
    public static double[] quickSort(double a[]) {
    	if(a == null) return null;
    	
    	recursiveQuick(a, 0, a.length - 1);
        return a;
	}
    
    private static void recursiveQuick(double[] a, int lo, int hi) {
    	if(hi <= lo) return;
    	int pivotPos = partition(a, lo, hi);
    	recursiveQuick(a, lo, pivotPos-1);
    	recursiveQuick(a, pivotPos+1, hi);
    }

	private static int partition(double[] a, int lo, int hi) {
		double pivot = a[hi];
		int i = lo - 1;
		//put elements on the appropriate side of the pivot
		for(int j = lo; j < hi; j++) {
			if (a[j] <= pivot) {
                i++;
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
		}
		//place the pivot in the correct spot
        double temp = a[i + 1];
        a[i + 1] = a[hi];
        a[hi] = temp;
        return i + 1;
	}

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
	
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */  
      
    private static void mSortMerge(double[] a, double[] aux, int lo, int mid, int hi) {
    	// copy to aux[]
    	for(int k = lo; k<=hi; k++) aux[k] = a[k];
    	
    	// merge back to a[]
    	int i = lo, j = mid + 1;
    	for (int k = lo; k<=hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (aux[j] < aux[i])      a[k] = aux[j++]; //merge from a[] to aux[]
            else                           a[k] = aux[i++];
        }
    }
    
    public static double[] mergeSortIterative(double[] a) {
    	if(a == null) return null;
    	

    	double[] aux = a.clone();
    	for(int size = 1; size < a.length; size = 2 * size) {
            for(int lo = 0; lo < a.length - size; lo += 2*size) {
                mSortMerge(a, aux, lo, lo+size -1, Math.min(lo+size+size-1, a.length-1));
            }
        } 
        return a;
    }
    
    private static void split(double[] a, double[] aux,  int lo, int hi) {
    	if (hi <= lo) {
            return;
        } 
    	int mid = lo + (hi - lo) / 2;
    	split(a, aux, lo, mid);
    	split(a, aux, mid+1, hi);
    	
    	mSortMerge(a, aux, lo, mid, hi); //switch role of aux[] and a[]
        
    }
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.does better when the inputs are nearly ordered.
     * @return after the method returns, the array must be in ascending sorted order.
     * 
     **/
    
    public static double[] mergeSortRecursive(double[] a) {
    	if(a == null) return null;
    	
    	double[] aux = a.clone();
    	split(a, aux, 0, a.length-1);
    	return a;
    }
    
 } //end class