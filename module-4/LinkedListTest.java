// Alexis Mitchell
// November 9, 2025
// This program tests and compares the traversal times of a LinkedList
// using an Iterator versus using the get(index) method.

import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListTest {

    // Method to fill the LinkedList with a given number of integers
    public static LinkedList<Integer> createLinkedList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    // Method to test traversal time using Iterator
    public static long traverseWithIterator(LinkedList<Integer> list) {
        long startTime = System.nanoTime();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Method to test traversal time using get(index)
    public static long traverseWithGet(LinkedList<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            list.get(i); // This is very inefficient for LinkedLists
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        // Test with 50,000 integers
        System.out.println("Testing with 50,000 integers:");
        LinkedList<Integer> list50k = createLinkedList(50000);

        long iteratorTime50k = traverseWithIterator(list50k);
        long getTime50k = traverseWithGet(list50k);

        System.out.println("Iterator traversal time: " + iteratorTime50k / 1_000_000.0 + " ms");
        System.out.println("get(index) traversal time: " + getTime50k / 1_000_000.0 + " ms\n");

        // Test with 500,000 integers
        System.out.println("Testing with 500,000 integers:");
        LinkedList<Integer> list500k = createLinkedList(500000);

        long iteratorTime500k = traverseWithIterator(list500k);
        long getTime500k = traverseWithGet(list500k);

        System.out.println("Iterator traversal time: " + iteratorTime500k / 1_000_000.0 + " ms");
        System.out.println("get(index) traversal time: " + getTime500k / 1_000_000.0 + " ms\n");

        // Simple test check
        if (iteratorTime50k < getTime50k && iteratorTime500k < getTime500k) {
            System.out.println("Test passed: Iterator traversal is consistently faster than get(index) traversal.");
        } else {
            System.out.println("Test warning: Results may vary based on system performance.");
        }
    }
}
/*

Results Explained:

For LinkedLists, accessing elements using get(index) is very slow because LinkedList 
does not use direct indexing like an ArrayList. Each call to get(index) must start 
from the beginning (or end) and move node by node until it reaches the position.

When using an Iterator, traversal is much faster because the iterator already has 
a reference to the current node and can move directly to the next one.

In testing:
 - With 50,000 elements, the iterator typically finishes in milliseconds, while 
   get(index) might take several seconds.
 - With 500,000 elements,  the iterator remains efficient, but get(index) can take  longer.

Conclusion:
The Iterator approach is much more efficient (O(n)) compared to 
get(index) (O(n^2)) for LinkedLists,
*/