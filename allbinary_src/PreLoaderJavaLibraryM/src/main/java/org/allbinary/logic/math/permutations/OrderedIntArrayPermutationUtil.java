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

import org.allbinary.logic.util.visitor.Visitor;

public class OrderedIntArrayPermutationUtil {

    private static final OrderedIntArrayPermutationUtil instance
            = new OrderedIntArrayPermutationUtil();

    /**
     * @return the instance
     */
    public static OrderedIntArrayPermutationUtil getInstance() {
        return instance;
    }

    private final String TOTAL_ITERATIONS_TABLE = "Total Iterations: ";
    private final String FINISHED = "\nFINISHED";
    
    private OrderedIntArrayPermutationUtil() {
    }

    public void getNext(int[] data) {
        int length = data.length;
        int i = length - 1;

        while (data[i - 1] >= data[i]) {
            i = i - 1;
        }

        int j = length;

        while (data[j - 1] <= data[i - 1]) {
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

    public void swap(int[] data, int a, int b) {
        int temp = data[a];
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

    public void generate(int[] data, Visitor visitor) {
        long iterations = this.factorial(data.length);
        System.out.print(TOTAL_ITERATIONS_TABLE);
        System.out.println(iterations);
        for (long count = 0; count < iterations - 1; count++) {
            this.getNext(data);
            visitor.visit(this);
        }
        System.out.println(FINISHED);
    }

    public static void main(String args[]) {
        final int TOTAL = 3; //max for long 20;
        final int[] data = new int[TOTAL];

        for (int i = 0; i < TOTAL; i++) {
            data[i] = i;
        }

        final IntPermutationVisitor permutationVisitor = new IntPermutationVisitor(data);        
        final String ORIGINAL_DATA_LABEL = "Original Data: ";
        System.out.print(ORIGINAL_DATA_LABEL);
        permutationVisitor.print(data);
        OrderedIntArrayPermutationUtil.getInstance().generate(data, permutationVisitor);
    }
}