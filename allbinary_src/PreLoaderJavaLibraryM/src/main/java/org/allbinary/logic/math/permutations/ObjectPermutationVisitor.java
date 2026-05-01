package org.allbinary.logic.math.permutations;

import org.allbinary.logic.TsUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.visitor.Visitor;
import org.allbinary.string.CommonSeps;

public class ObjectPermutationVisitor extends Visitor {

    protected final Object[] data;
    
    public ObjectPermutationVisitor(final Object[] data)
    {
        this.data = data;
    }
    
    private StringMaker stringBuilder = new StringMaker();

    public Object visit(final Object object) {
        this.stringBuilder.delete(0, stringBuilder.length());
        this.print(this.data, stringBuilder);
        return null;
    }
    

    public void print(final Object[] data) {
        final StringMaker stringBuilder = new StringMaker();
        this.print(data, stringBuilder);
    }
    
    public void print(final Object[] data, final StringMaker stringBuilder) {
        final TsUtil tsUtil = TsUtil.getInstance();
        final int size = data.length;
        for (int i = 0; i < size; i++) {
            stringBuilder.appendint(tsUtil.hashCode(data[i]));
            stringBuilder.append(CommonSeps.getInstance().SPACE);
        }
        //stringBuilder.append(CommonSeps.getInstance().NEW_LINE);
        System.out.println(stringBuilder.toString());
    }
}
