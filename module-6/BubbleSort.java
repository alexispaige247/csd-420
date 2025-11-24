// Alexis Mitchell
// November 23, 2025
// This program creates a bubble sort using both Comparable and Comparator interfaces

import java.util.Comparator;
import java.util.Arrays;
public class BubbleSort {
    
    // Bubble sort using Comparable
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean swapped;

        // Perform bubble sort
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    // Swap
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }

            // Optimization: stop early if no swaps occurred
            if (!swapped) break;
        }
    }

    // Bubble sort using Comparator
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean swapped;

        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    // Swap
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    // print array
    public static <E> void printArray(E[] array) {
        System.out.println(Arrays.toString(array));
    }

    // Test cases
    public static void main(String[] args) {

        System.out.println("Test 1 (Comparable)");
        Integer[] nums = {5, 3, 9, 1, 4};
        System.out.print("Before: ");
        printArray(nums);

        bubbleSort(nums);
        System.out.print("After : ");
        printArray(nums);


        System.out.println("\nTest 2 (Comparator)");
        Person[] people = {
            new Person("Daniel", 30),
            new Person("Jordan", 25),
            new Person("Taylor", 35)
        };

        System.out.print("Before: ");
        printArray(people);

          // Sort by AGE using comparator
        bubbleSort(people, Comparator.comparing(Person::getAge));
        System.out.print("After : ");
        printArray(people);
    }
}

// Person class for testing Comparator
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}