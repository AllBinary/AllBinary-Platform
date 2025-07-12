/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 * 
 */
package org.allbinary.logic.math.permutations;

import java.util.Arrays;

import org.allbinary.logic.util.visitor.Visitor;

public class OrderedObjectArrayPermutationUtil {

    private static final OrderedObjectArrayPermutationUtil instance
            = new OrderedObjectArrayPermutationUtil();

    /**
     * @return the instance
     */
    public static OrderedObjectArrayPermutationUtil getInstance() {
        return instance;
    }

    private final String TOTAL_ITERATIONS_TABLE = "Total Iterations: ";
    private final String FINISHED = "\nFINISHED";
    
    private OrderedObjectArrayPermutationUtil() {
    }

    public void getNext(Object[] data) {
        int length = data.length;
        int i = length - 1;

        while (data[i - 1].hashCode() >= data[i].hashCode()) {
            i = i - 1;
        }

        int j = length;

        while (data[j - 1].hashCode() <= data[i - 1].hashCode()) {
            j = j - 1;
        }

        this.swap(data, i - 1, j - 1);

        i++;
        j = length;

        while (i < j) {
            this.swap(data, i - 1, j - 1);
            i++;
            j--;
        }
    }

    public void swap(Object[] data, int a, int b) {
        Object temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public long factorial(int dataLength) {
        long temp = 1;
        if (dataLength > 1) {
            for (int i = 1; i <= dataLength; i++) {
                temp *= i;
            }
        }
        return temp;
    }

    public void generate(Object[] data, Visitor visitor) {
        long iterations = this.factorial(data.length);
        System.out.print(TOTAL_ITERATIONS_TABLE);
        System.out.println(iterations);
        for (long count = 0; count < iterations - 1; count++) {
            this.getNext(data);
            //System.out.println(count);
            visitor.visit(this);
        }
        System.out.println(FINISHED);
    }

    public static void main(String args[]) {
        final int TOTAL = 7; //max for long 20;
        final Object[] data = new Object[TOTAL];

        for (int i = 0; i < TOTAL; i++) {
            data[i] = new ComparableObject();
        }

        Arrays.sort(data);

        final ObjectPermutationVisitor permutationVisitor = new ObjectPermutationVisitor(data);        
        final String ORIGINAL_DATA_LABEL = "Original Data: ";
        System.out.print(ORIGINAL_DATA_LABEL);
        permutationVisitor.visit(data);
        OrderedObjectArrayPermutationUtil.getInstance().generate(data, permutationVisitor);
    }
}