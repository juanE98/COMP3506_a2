import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

public class arrayTestTime<T> {


    public static <T extends Comparable> void unsortedTest(int n){
        Integer[] unsortedList = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++){
            unsortedList[i] = random.nextInt();
        }
        //Time function with input:
        selectionSortTime(unsortedList);
        insertionSortTime(unsortedList);
        mergeSortTime(unsortedList);
        quickSortTime(unsortedList);

    }

    public static <T extends Comparable> void sortedAscending(int n){
        Integer[] ascendingList = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++){
            ascendingList[i] = random.nextInt();
        }
        Arrays.sort(ascendingList);
        //Time function with input:
        selectionSortTime(ascendingList);
        insertionSortTime(ascendingList);
        mergeSortTime(ascendingList);
        quickSortTime(ascendingList);

    }

    public static <T extends Comparable> void sortedDescending(int n){
        Integer[] descendingList = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++){
            descendingList[i] = random.nextInt();
        }
        Arrays.sort(descendingList, Collections.reverseOrder());

        //Time function with input:
        selectionSortTime(descendingList);
        insertionSortTime(descendingList);
        mergeSortTime(descendingList);
        quickSortTime(descendingList);

    }

    private static <T extends Comparable> void selectionSortTime(T[] input) {
        long startTime = System.nanoTime();
        SortingAlgorithms.selectionSort(input, false);
        long endTime = System.nanoTime();
        System.out.println("Selection sort:" + (endTime - startTime));
    }

    private static <T extends Comparable> void insertionSortTime(T[] input){
        long startTime = System.nanoTime();
        SortingAlgorithms.insertionSort(input, false);
        long endTime = System.nanoTime();
        System.out.println("Insertion sort:" + (endTime - startTime));
    }

    private static <T extends Comparable> void mergeSortTime(T[] input){
        long startTime = System.nanoTime();
        SortingAlgorithms.mergeSort(input, false);
        long endTime = System.nanoTime();
        System.out.println("Merge sort:" + (endTime - startTime));
    }

    private static <T extends Comparable> void quickSortTime(T[] input){
        long startTime = System.nanoTime();
        SortingAlgorithms.quickSort(input, false);
        long endTime = System.nanoTime();
        System.out.println("Quick sort:" + (endTime - startTime));
    }


    public static void main(String[] args){

        System.out.println("n = 5");
        System.out.println("--unsorted array--");
        unsortedTest(5);
        System.out.println("--ascending order--");
        sortedAscending(5);
        System.out.print("--descending order--");
        sortedDescending(5);

        System.out.println("\n n = 10");
        System.out.println("--unsorted array--");
        unsortedTest(10);
        System.out.println("--ascending order--");
        sortedAscending(10);
        System.out.print("--descending order--");
        sortedDescending(10);





    }
}
