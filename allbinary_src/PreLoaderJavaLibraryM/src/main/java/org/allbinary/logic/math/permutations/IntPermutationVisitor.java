package org.allbinary.logic.math.permutations;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.util.visitor.Visitor;

public class IntPermutationVisitor extends Visitor {

    protected final int[] data;
    
    public IntPermutationVisitor(int[] data)
    {
        this.data = data;
    }
    
    private StringBuilder stringBuilder = new StringBuilder();

    public Object visit(Object object) {
        stringBuilder.delete(0, stringBuilder.length());
        this.print(data, stringBuilder);
        return null;
    }
    

    public void print(int[] data) {
        final StringBuilder stringBuilder = new StringBuilder();
        this.print(data, stringBuilder);
    }
    
    public void print(int[] data, StringBuilder stringBuilder) {
        int size = data.length;
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            stringBuilder.append(CommonSeps.getInstance().SPACE);
        }
        //stringBuilder.append(CommonSeps.getInstance().NEW_LINE);
        System.out.println(stringBuilder.toString());
    }
}
