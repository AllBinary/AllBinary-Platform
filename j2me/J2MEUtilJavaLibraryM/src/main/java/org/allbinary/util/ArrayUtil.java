package org.allbinary.util;

import org.allbinary.logic.math.MathUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;

public class ArrayUtil
{
    private static final ArrayUtil instance = new ArrayUtil();

    public static ArrayUtil getInstance()
    {
        return ArrayUtil.instance;
    }

    private final MathUtil mathUtil = MathUtil.getInstance();
    
    public Object[] copyOf(final Object[] original, final int newLength)
    {
        return this.copyOfType(original, newLength, original.getClass());
    }

    public Object[] copyOfType(final Object[] original, final int newLength, final Class newType)
    {
        final Object[] copy = new Object[newLength];
        System.arraycopy(original, 0, copy, 0, this.mathUtil.min(original.length, newLength));
        return copy;
    }

    public byte[] copyOfbyte(final byte[] original, final int newLength) {
        final byte[] copy = new byte[newLength];
        System.arraycopy(original, 0, copy, 0,this.mathUtil.min(original.length, newLength));
        return copy;
    }

    public short[] copyOfshort(short[] original, int newLength) {
        final short[] copy = new short[newLength];
        System.arraycopy(original, 0, copy, 0,this.mathUtil.min(original.length, newLength));
        return copy;
    }

    public int[] copyOfint(final int[] original, final int newLength) {
        final int[] copy = new int[newLength];
        System.arraycopy(original, 0, copy, 0,this.mathUtil.min(original.length, newLength));
        return copy;
    }

    public long[] copyOflong(final long[] original, final int newLength) {
        final long[] copy = new long[newLength];
        System.arraycopy(original, 0, copy, 0,this.mathUtil.min(original.length, newLength));
        return copy;
    }

    public char[] copyOfchar(final char[] original, final int newLength) {
        final char[] copy = new char[newLength];
        System.arraycopy(original, 0, copy, 0,this.mathUtil.min(original.length, newLength));
        return copy;
    }

    public float[] copyOffloat(final float[] original, final int newLength) {
        final float[] copy = new float[newLength];
        System.arraycopy(original, 0, copy, 0,this.mathUtil.min(original.length, newLength));
        return copy;
    }

    public double[] copyOfdouble(final double[] original, final int newLength) {
        final double[] copy = new double[newLength];
        System.arraycopy(original, 0, copy, 0,this.mathUtil.min(original.length, newLength));
        return copy;
    }

    public boolean[] copyOfboolean(final boolean[] original, final int newLength) {
        final boolean[] copy = new boolean[newLength];
        System.arraycopy(original, 0, copy, 0,this.mathUtil.min(original.length, newLength));
        return copy;
    }
    
    public void reverse(final int[] intArray) {
        final int length = intArray.length - 1;
        int temp;
        final int size = length / 2;
        for (int index = 0; index <= size; index++) {
            temp = intArray[index];
            intArray[index] = intArray[length - index];
            intArray[length - index] = temp;
        }
    }	

    public void flip(final int[] intArray, final int width, final int height) {
        final int length = height - 1;
        int temp;
        int index;
        int rowIndex;
        for (int y = 0; y <= length / 2; y++) {
            for (int x = 0; x < width; x++) {
                rowIndex = ((length - y) * width);
                index = x + (y * width);
                temp = intArray[index];
                intArray[index] = intArray[rowIndex + x];
                intArray[rowIndex + x] = temp;
            }
        }
    }	
    
    public String toString(final int[][] twoDimensionalIntArray)
    {
        final StringMaker stringBuffer = new StringMaker();

        final CommonSeps commonSeps = CommonSeps.getInstance();
                
        final int endIndex = twoDimensionalIntArray.length;
        final int endIndex2 = twoDimensionalIntArray[0].length;
        for (int index = 0; index < endIndex; index++)
        {
            stringBuffer.append(commonSeps.BRACE_OPEN);
            for (int index2 = 0; index2 < endIndex2; index2++)
            {
                stringBuffer.appendint(twoDimensionalIntArray[index][index2]);
                stringBuffer.append(commonSeps.COMMA_SEP);
            }
            stringBuffer.append(commonSeps.BRACE_CLOSE);
            stringBuffer.append(commonSeps.NEW_LINE);
        }

        return stringBuffer.toString();
    }
    
    public String toStringFromObjectArray(final Object[] objectArray)
    {
        final StringMaker stringBuffer = new StringMaker();

        final CommonSeps commonSeps = CommonSeps.getInstance();

        for (int index = 0; index < objectArray.length; index++)
        {
                stringBuffer.append(StringUtil.getInstance().toString(objectArray[index]));
                stringBuffer.append(commonSeps.COMMA_SEP);
        }

        return stringBuffer.toString();
    }
    
    public String toStringFromByteArray(final byte[] byteArray)
    {
        final StringMaker stringBuffer = new StringMaker();

        final CommonSeps commonSeps = CommonSeps.getInstance();

        for (int index = 0; index < byteArray.length; index++)
        {
                stringBuffer.appendbyte(byteArray[index]);
                stringBuffer.append(commonSeps.COMMA_SEP);
        }

        return stringBuffer.toString();
    }
    
}
