package org.allbinary.logic.math.permutations;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.visitor.Visitor;
import org.allbinary.string.CommonSeps;

public class IntPermutationVisitor extends Visitor {

    protected final int[] data;
    
    public IntPermutationVisitor(final int[] data)
    {
        this.data = data;
    }
    
    private StringMaker stringBuilder = new StringMaker();

    public Object visit(final Object object) {
        this.stringBuilder.delete(0, stringBuilder.length());
        this.print(this.data, stringBuilder);
        return null;
    }
    

    public void print(final int[] data) {
        final StringMaker stringBuilder = new StringMaker();
        this.print(data, stringBuilder);
    }
    
    public void print(final int[] data, final StringMaker stringBuilder) {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        final int size = data.length;
        for (int i = 0; i < size; i++) {
            stringBuilder.appendint(data[i]);
            stringBuilder.append(commonSeps.SPACE);
        }
        //stringBuilder.append(CommonSeps.getInstance().NEW_LINE);
        System.out.println(stringBuilder.toString());
    }
}
