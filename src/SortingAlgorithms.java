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

        //Iterate over unsorted subarray
        for(int i=0; i< n-1; i++){
            int compIndex =i;
            for (int j = i + 1; j < n; j++) {
                //Find smaller element
                if (reversed == false) {
                    if (input[j].compareTo(input[compIndex]) < 0) {
                        compIndex = j;
                    }
                }
                else{
                    //Find bigger element
                    if (input[j].compareTo(input[compIndex]) > 0){
                        compIndex = j;
                    }
                }
            }
            //Swap current element with bigger or smaller element
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
            //Locate hole position for element to be inserted
            if (reversed == false) {
                while (j > 0 && (input[j - 1].compareTo(input[j]) > 0)) {
                    //swap elements
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
        mergeSortHelper(input, 0, input.length - 1, reversed);
    }




    /**
     * Helper function for merge sort which calls itself recursively to
     * divide the array
     * @param input array to be sorted
     * @param leftStart left index
     * @param rightEnd right index
     * @param reversed If false, the array should be sorted ascending.
     *                   Otherwise, it should be sorted descending.
     * @param <T>
     */

    private static <T extends Comparable> void mergeSortHelper(T[] input,
                                                               int leftStart,
                                                               int rightEnd,
                                                               boolean reversed){
        if (leftStart < rightEnd) {
            int midpoint = (leftStart + rightEnd) / 2;
            mergeSortHelper(input, leftStart, midpoint, reversed);
            mergeSortHelper(input, (midpoint + 1), rightEnd, reversed);

            merge(input, leftStart, midpoint, rightEnd, reversed);
        }
    }



    /**
     * Merges the 2 subarrays which are sorted ascending if reversed == false
     * and descending if reversed == true.
     * @param input array to be sorted
     * @param leftStart left index
     * @param midPoint mid index
     * @param rightEnd right index
     * @param reversed If false, the array should be sorted ascending.
     *                   Otherwise, it should be sorted descending.
     */

    private static <T extends  Comparable> void merge(T[] input,
                                                      int leftStart,
                                                      int midPoint,
                                                      int rightEnd,
                                                      boolean reversed){
        int right = midPoint + 1;

        //Base case of recursion call reached
        if(input[midPoint].compareTo(input[right]) <= 0 && !reversed == true){
            return;
        }
        else if (input[midPoint].compareTo(input[right]) >= 0 && reversed == true){
            return;
        }

        //
        while (leftStart <= midPoint && right <= rightEnd){
            //If element is in correct position, continue.
            if(input[leftStart].compareTo(input[right]) <= 0 && !reversed == true){
                leftStart++;
            }
            else if (input[leftStart].compareTo(input[right]) >= 0 && reversed == true){
                leftStart++;
            }
            else{
                T element = input[right];
                int index = right;

                while (index != leftStart){
                    input[index] = input[index - 1];
                    index--;
                }
                input[leftStart] = element;

                //Update pointers to positions of array
                leftStart++;
                midPoint++;
                right++;
            }
        }
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
                //Scan until reaching value that is equal or larger than pivot.
                while (input[left].compareTo(pivot) < 0) {
                    left++;
                }
                //scan until reaching value equal or smaller than pivot
                while (input[right].compareTo(pivot) > 0) {
                    right--;
                }
            }
            else {
                //Scan until reaching value that is equal or smaller than pivot.
                while (input[left].compareTo(pivot) > 0) {
                    left++;
                }
                //Scan until reaching value that is equal or larger than pivot.
                while (input[right].compareTo(pivot) < 0) {
                    right--;
                }
            }


            if (left <= right) { //indices did not cross over
                //swap values.
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
