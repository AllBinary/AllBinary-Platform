package org.allbinary.logic.string;

public class StringMaker 
{
    //private final StringUtil stringUtil = StringUtil.getInstance();
    
    private char[] charArray;
    private int currentLength;

    public StringMaker()
    {
        this.charArray = new char[20];
    }
    
    public StringMaker append(final String string)
    {
        final int stringLength = string.length();
        this.ensureCapacity(this.currentLength + stringLength);
        string.getChars(0, stringLength, this.charArray, this.currentLength);
        this.currentLength += stringLength;
        return this;
    }

//    public StringMaker append(final Object object)
//    {
//        if(object != null) {
//            this.append(object.toString());
//        } else {
//            this.append(stringUtil.NULL_STRING);
//        }
//        return this;
//    }
    
    public StringMaker appendchar(final char c)
    {
        this.ensureCapacity(this.currentLength + 1);
        this.charArray[this.currentLength++] = c;
        return this;
    }

//    public StringMaker append(final byte b)
//    {
//      //change to PrimitiveLongUtil
//        this.append(Integer.toString(b));
//        return this;
//    }
//
//    public StringMaker append(final short b)
//    {
//      //change to PrimitiveLongUtil
//        this.append(Integer.toString(b));
//        return this;
//    }
    
    public StringMaker appendint(final int i)
    {
      //change to PrimitiveLongUtil
        this.append(Integer.toString(i));
        return this;
    }
    
    public StringMaker appendlong(final long l)
    {
      //change to PrimitiveLongUtil
        this.append(Long.toString(l));
        return this;
    }

    public StringMaker appendfloat(float f)
    {
      //change to PrimitiveLongUtil
        this.append(Float.toString(f));
        return this;
    }
    
//J2ME does not have double
//    public StringMaker append(double d)
//    {
//      //change to PrimitiveLongUtil
//        this.append(Double.toString(d));
//        return this;
//    }
    
    public StringMaker appendboolean(final boolean bool)
    {
        if (bool)
        {
            this.ensureCapacity(this.currentLength + 4);
            this.charArray[this.currentLength++] = 't';
            this.charArray[this.currentLength++] = 'r';
            this.charArray[this.currentLength++] = 'u';
            this.charArray[this.currentLength++] = 'e';
        } else {
            this.ensureCapacity(this.currentLength + 5);
            this.charArray[this.currentLength++] = 'f';
            this.charArray[this.currentLength++] = 'a';
            this.charArray[this.currentLength++] = 'l';
            this.charArray[this.currentLength++] = 's';
            this.charArray[this.currentLength++] = 'e';
        }
        return this;
    }

    public void ensureCapacity(final int minSize)
    {
        final int oldCapacity = this.charArray.length;

        if (minSize > oldCapacity) 
        {
            int newCapacity = oldCapacity * 3;

            if (newCapacity < minSize)
                newCapacity = minSize;

            final char[] copy = new char[newCapacity];

            final int min = this.min(this.charArray.length, newCapacity);

            System.arraycopy(this.charArray, 0, copy, 0, min);

            this.charArray = copy;
        }
    }

    //mathUtil.min(this.charArray.length, newCapacity)
    public int min(int value, int value2) {
        return (value <= value2) ? value : value2;
    }

    public StringMaker delete(final int start, final int end)
    { 
        this.currentLength -= (end - start);
        return this;
    }
    
    public int length()
    {
        return this.currentLength;
    }
    
    public String toString()
    {
        return new String(this.charArray, 0, this.currentLength);
    }
}
