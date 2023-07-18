import java.util.Arrays;
import java.util.Scanner;
//These lines import necessary classes from the Java standard library for array manipulation and user input


public class SortingAndSearching {
    //This line defines the main class SortingAndSearching, which encapsulates the functionality of sorting and searching algorithms.
    public static void main(String[] args) {
        SortingAndSearchingApp app = new SortingAndSearchingApp();
        app.start();
    }
}

class SortingAndSearchingApp {
    private Scanner scanner;
    //This line declares the main method within the SortingAndSearching class.
// The method is the entry point of the program. It creates a Scanner object to read user input.
    public SortingAndSearchingApp() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
//This line displays a message and waits for the user to enter the size of the array.
// The entered value is stored in the size variable.

        int[] array = new int[size];
        //This line creates an integer array with a size equal to the value entered by the user.

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
//These lines display a message and prompt the user to enter the elements of the array one by one.
// The entered values are stored in the array variable using a loop.
        System.out.print("Enter the element to search: ");
        int target = scanner.nextInt();

        System.out.println("\nSorting Algorithms:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Quick Sort");
        System.out.print("Enter the sorting algorithm choice: ");
        int sortChoice = scanner.nextInt();

        System.out.println("\nSearching Algorithms:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");
        System.out.print("Enter the searching algorithm choice: ");
        int searchChoice = scanner.nextInt();

        System.out.println("\nOriginal Array: " + Arrays.toString(array));

        SortingAlgorithm sortingAlgorithm = null;
        switch (sortChoice) {
            case 1:
                sortingAlgorithm = new BubbleSort();
                break;
            case 2:
                sortingAlgorithm = new SelectionSort();
                break;
            case 3:
                sortingAlgorithm = new InsertionSort();
                break;
            case 4:
                sortingAlgorithm = new MergeSort();
                break;
            case 5:
                sortingAlgorithm = new QuickSort();
                break;
            default:
                System.out.println("Invalid sorting algorithm choice.");
                System.exit(0);
        }

        if (sortingAlgorithm != null) {
            sortingAlgorithm.sort(array);
            System.out.println("Sorted Array (" + sortingAlgorithm.getName() + "): " + Arrays.toString(array));
        }

        SearchingAlgorithm searchingAlgorithm = null;
        switch (searchChoice) {
            case 1:
                searchingAlgorithm = new LinearSearch();
                break;
            case 2:
                searchingAlgorithm = new BinarySearch();
                break;
            default:
                System.out.println("Invalid searching algorithm choice.");
                System.exit(0);
        }

        if (searchingAlgorithm != null) {
            int searchIndex = searchingAlgorithm.search(array, target);
            if (searchIndex == -1) {
                System.out.println("Element not found (" + searchingAlgorithm.getName() + ").");
            } else {
                System.out.println("Element found at index " + searchIndex + " (" + searchingAlgorithm.getName() + ").");
            }
        }

        scanner.close();
    }
}

abstract class SortingAlgorithm {
    public abstract void sort(int[] array);
    public abstract String getName();
}

class BubbleSort extends SortingAlgorithm {
    public void sort(int[] array) {
        long startTime = System.nanoTime();

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println(getName() + " Execution Time: " + executionTime + " nanoseconds");
    }

    public String getName() {
        return "Bubble Sort";
    }
}



class SelectionSort extends SortingAlgorithm {
    public void sort(int[] array) {
        long startTime = System.nanoTime();

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println(getName() + " Execution Time: " + executionTime + " nanoseconds");
    }

    public String getName() {
        return "Selection Sort";
    }
}

class InsertionSort extends SortingAlgorithm {
    public void sort(int[] array) {
        long startTime = System.nanoTime();

        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println(getName() + " Execution Time: " + executionTime + " nanoseconds");
    }

    public String getName() {
        return "Insertion Sort";
    }
}

class MergeSort extends SortingAlgorithm {
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }
    long startTime = System.nanoTime();

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println(getName() + " Execution Time: " + executionTime + " nanoseconds");
    }

    public String getName() {
        return "Merge Sort";
    }
}

class QuickSort extends SortingAlgorithm {
    public void sort(int[] array) {
        long startTime = System.nanoTime();

        quickSort(array, 0, array.length - 1);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println(getName() + " Execution Time: " + executionTime + " nanoseconds");
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public String getName() {
        return "Quick Sort";
    }
}


abstract class SearchingAlgorithm {
    public abstract int search(int[] array, int target);
    public abstract String getName();
}

class LinearSearch extends SearchingAlgorithm {
    public int search(int[] array, int target) {
        long startTime = System.nanoTime();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                System.out.println("Linear Search Execution Time: " + executionTime + " nanoseconds");
                return i;
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Linear Search Execution Time: " + executionTime + " nanoseconds");

        return -1;
    }

    public String getName() {
        return "Linear Search";
    }
}


class BinarySearch extends SearchingAlgorithm {
    public int search(int[] array, int target) {
        long startTime = System.nanoTime();

        int result = binarySearch(array, target);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println(getName() + " Execution Time: " + executionTime + " nanoseconds");

        return result;
    }

    private int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public String getName() {
        return "Binary Search";
    }
}
