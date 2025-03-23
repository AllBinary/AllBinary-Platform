package org.allbinary.logic.math.permutations;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.util.visitor.Visitor;

public class ObjectPermutationVisitor extends Visitor {

    protected final Object[] data;
    
    public ObjectPermutationVisitor(Object[] data)
    {
        this.data = data;
    }
    
    private StringBuilder stringBuilder = new StringBuilder();

    public Object visit(Object object) {
        stringBuilder.delete(0, stringBuilder.length());
        this.print(data, stringBuilder);
        return null;
    }
    

    public void print(Object[] data) {
        final StringBuilder stringBuilder = new StringBuilder();
        this.print(data, stringBuilder);
    }
    
    public void print(Object[] data, StringBuilder stringBuilder) {
        int size = data.length;
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i].hashCode());
            stringBuilder.append(CommonSeps.getInstance().SPACE);
        }
        //stringBuilder.append(CommonSeps.getInstance().NEW_LINE);
        System.out.println(stringBuilder.toString());
    }
}
