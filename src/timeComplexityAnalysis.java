import java.util.*;
public class timeComplexityAnalysis<T> {
    /**
     * Generates an unsorted list of numbers of size n.
     * @param n size of the array
     */
    public static <T extends Comparable> void unsortedTest(int n){
        Integer[] unsortedList = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++){
            unsortedList[i] = random.nextInt();
        }
        //Time function with input:
        timeFunction(unsortedList);
    }

    /**
     * Generates a sorted ascending list of numbers of size n.
     * @param n size of the array
     * @param <T>
     */
    public static <T extends Comparable> void sortedAscending(int n){
        Integer[] ascendingList = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++){
            ascendingList[i] = random.nextInt();
        }
        Arrays.sort(ascendingList);
        //Time function with input:
        timeFunction(ascendingList);
    }

    /**
     * Generates a sorted descending list of numbers of size n.
     * @param n size of the array
     */
    public static <T extends Comparable> void sortedDescending(int n){
        Integer[] descendingList = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++){
            descendingList[i] = random.nextInt();
        }
        Arrays.sort(descendingList, Collections.reverseOrder());

        //Time function with input:
        timeFunction(descendingList);
    }

    /**
     * Timer for selectionSort algorithm
     * @param input array of size n
     */
    private static <T extends Comparable> void selectionSortTime(T[] input) {
        double startTime = System.nanoTime();
        SortingAlgorithms.selectionSort(input, false);
        double endTime = System.nanoTime();
        System.out.println("Selection sort:" + (endTime - startTime) / 1000000);
    }

    /**
     * Timer for insertionSort algorithm
     * @param input array of size n
     */
    private static <T extends Comparable> void insertionSortTime(T[] input){
        double startTime = System.nanoTime();
        SortingAlgorithms.insertionSort(input, false);
        double endTime = System.nanoTime();
        System.out.println("Insertion sort:" + (endTime - startTime) / 1000000);
    }

    /**
     * Timer for mergeSort algorithm
     * @param input array of size n
     */
    private static <T extends Comparable> void mergeSortTime(T[] input){
        double startTime = System.nanoTime();
        SortingAlgorithms.mergeSort(input, false);
        double endTime = System.nanoTime();
        System.out.println("Merge sort:" + (endTime - startTime) / 1000000);
    }

    /**
     * Timer for quickSort algorithm
     * @param input array of size n
     */
    private static <T extends Comparable> void quickSortTime(T[] input){
        double startTime = System.nanoTime();
        SortingAlgorithms.quickSort(input, false);
        double endTime = System.nanoTime();
        System.out.println("Quick sort:" + (endTime - startTime) / 1000000);
    }

    /**
     * Helper function to print results.
     * @param n size of the array to be tested.
     */
    private static void results(int n){
        System.out.println("\n" + "n = " + n);
        System.out.println("--unsorted array--");
        unsortedTest(n);
        System.out.println("--ascending order--");
        sortedAscending(n);
        System.out.println("--descending order--");
        sortedDescending(n);

    }

    /**
     * Helper function to time all sorting algorithm with the same input size
     * n.
     * @param input array with input size n
     */
    private static <T extends Comparable> void timeFunction(T[] input){
        selectionSortTime(input);
        insertionSortTime(input);
        mergeSortTime(input);
        quickSortTime(input);
    }


    public static void main(String[] args){
        results(5);
        results(10);
        results(50);
        results(100);
        results(500);
        results(1000);
        results(10000);
    }
}
