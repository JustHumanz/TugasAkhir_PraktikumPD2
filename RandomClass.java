/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Responsi2;

/**
 *
 * @author Asus
 */
import java.util.*;

class House implements Comparable<House> {

    String index;
    int size;

    public House(String i, int s) {
        index = i;
        size = s;
    }

    @Override
    public int compareTo(House o) {
        int comparedSize = o.size;
        if (this.size > comparedSize) {
            return 1;
        } else if (this.size == comparedSize) {
            return 0;
        } else {
            return -1;
        }
    }

    public String toString() {
        return index;
    }
} // membuat objek karakter

public class RandomClass {

    public static void main(String[] args) {

        LinkedList<House> charaTry = new LinkedList<House>();
        charaTry.add(new House("medium", 200));
        System.out.println(charaTry.element().size);
        charaTry.add(new House("small", 100));
        charaTry.add(new House("big", 300));
        System.out.println(charaTry);
       

        // sort in ascending order
        Collections.sort(charaTry);
        System.out.println(charaTry);

        // sort in descending order
        Collections.sort(charaTry, Collections.reverseOrder());
        System.out.println(charaTry);
    } //contoh dasar sorting value object

    public static void printList(List l) {
        for (Object o : l) {
            System.out.println(o);
        }
    } // print LinkedList
}
