public class SortingAlgorithms {
    /**
     * Sorts the given array using the selection sort algorithm.
     * This should modify the array in-place.
     *
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void selectionSort(T[] input, boolean reversed) {
        int n = input.length;
        for(int i=0; i< n-1; i++){
            int compIndex =i;
            for (int j = i + 1; j < n; j++) {
                if (reversed == false) {
                    if (input[j].compareTo(input[compIndex]) < 0) {
                        compIndex = j;
                    }
                }
                else{
                    if (input[j].compareTo(input[compIndex]) > 0){
                        compIndex = j;
                    }
                }
            }
            T temp = input[compIndex];
            input[compIndex] = input[i];
            input[i] = temp;
        }
    }

    /**
     * Sorts the given array using the insertion sort algorithm.
     * This should modify the array in-place.
     *
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void insertionSort(T[] input, boolean reversed) {
        for (int i = 1; i < input.length; i++){
            int j = i;
            if (reversed == false) {
                while (j > 0 && (input[j - 1].compareTo(input[j]) > 0)) {
                    T temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                    j = j - 1;
                }
            }else
                while(j > 0 && (input[j-1].compareTo(input[j]) < 0)){
                    T temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                    j = j - 1;
                }
        }
    }
    
    /**
     * Sorts the given array using the merge sort algorithm.
     * This should modify the array in-place.
     * 
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void mergeSort(T[] input, boolean reversed) {
        mergeSortHelper(input, reversed);
    }

    private static <T extends Comparable> void mergeSortHelper(T[] input,
                                                               boolean reversed){
        int n = input.length;
        if(n < 2) {
            return;
        }
        int midpoint = (n / 2);

        T[] firstHalf = arrayCopy(input, 0, midpoint);
        T[] secondHalf = arrayCopy(input, midpoint, n);

        mergeSortHelper(firstHalf, reversed);
        mergeSortHelper(secondHalf, reversed);

        merge(firstHalf, secondHalf, input, reversed);

    }


    private static <T extends  Comparable> void merge(T[] input, T[] firstHalf,
                                                      T[] secondHalf,
                                                      boolean reversed){
        int i = 0 , j = 0;

        while (i + j < input.length){
            if(j == secondHalf.length || (i < firstHalf.length) &&
                    firstHalf[i].compareTo(secondHalf[i]) <= 0){
                input[i + j] = firstHalf[i++];
            }
            else{
                input[i + j] = secondHalf[j++];
            }
        }
    }


    private static <T extends Comparable> T[] arrayCopy (T[] array,
                                                         int start,
                                                         int end){
        T[] copy = (T[])new Comparable[end-start];
        for(int i = 0; i < copy.length; i++){
            copy[i] = array[start];
            start++;
        }
        return copy;
    }

    
    /**
     * Sorts the given array using the quick sort algorithm.
     * This should modify the array in-place.
     * 
     * You should use the value at the middle of the input  array(i.e. floor(n/2)) 
     * as the pivot at each step.
     * 
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void quickSort(T[] input, boolean reversed) {
        quickSortHelper(input, 0, input.length - 1, reversed);

    }


    /**
     * @param input array to be sorted
     * @param left left pointer
     * @param right right pointer
     * @param reversed If false, the array should be sorted ascending.
     *                        Otherwise, it should be sorted descending.
     *
     */
    private static <T extends Comparable> void quickSortHelper(T[] input,
                                                             int left,
                                                               int right,
                                                               boolean reversed){
        if (left >= right){
            return;
        }
        T pivot = input[(left+right)/ 2];
        int partitionIndex = partition(input, left, right, pivot,reversed);

        quickSortHelper(input, left, partitionIndex - 1, reversed);
        quickSortHelper(input, partitionIndex, right, reversed);
    }


    /**
     * Partition method: checks each element and swaps it before the pivot if
     * it is smaller or larger based on boolean of reversed.
     * @param input array to be sorted
     * @param left left side of pivot
     * @param right right side of pivot
     * @param pivot median element of the array.
     * @param reversed If false, the array should be sorted ascending.
     *                      Otherwise, it should be sorted descending.
     *
     * @return partition point
     */
    private static <T extends Comparable> int partition(T[] input, int left,
                                                        int right, T pivot,
                                                        boolean reversed){
        while (left <= right) {
            if (reversed == false) {
                while (input[left].compareTo(pivot) < 0) {
                    left++;
                }
                while (input[right].compareTo(pivot) > 0) {
                    right--;
                }
            }
            else {
                while (input[left].compareTo(pivot) > 0) {
                    left++;
                }
                while (input[right].compareTo(pivot) < 0) {
                    right--;
                }
            }

            if (left <= right) {
                T temp = input[left];
                input[left] = input[right];
                input[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }
}
