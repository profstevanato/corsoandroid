package com.example.genji.am028_view;

import java.util.Random;

/**
 * Created by genji on 2/25/16.
 */
public class RandomUtils {
    private static Random r = new Random();

    public static int randomInt(int range) {
        return(r.nextInt(range));
    }

    /** Returns a random index of an array. */

    public static int randomIndex(Object[] array) {
        return(randomInt(array.length));
    }

    /** Returns a random element from an array. Uses generics, so no typecast is
     *  required for the return value.
     */

    public static <T> T randomElement(T[] array) {
        return(array[randomIndex(array)]);
    }

    /** Returns a random float between 0 (inclusive) and n (exclusive). */

    public static float randomFloat(int n) {
        return((float)Math.random()*n);
    }
}
