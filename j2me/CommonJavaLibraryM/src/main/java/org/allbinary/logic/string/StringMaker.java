package org.allbinary.logic.string;

public class StringMaker 
{
    //private final StringUtil stringUtil = StringUtil.getInstance();
    
    private char[] charArray;
    private int currentLength;

    public StringMaker()
    {
        charArray = new char[20];
    }
    
    public StringMaker append(final String string)
    {
        int stringLength = string.length();
        ensureCapacity(currentLength + stringLength);
        string.getChars(0, stringLength, charArray, currentLength);
        currentLength += stringLength;
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
    
    public StringMaker append(final char c)
    {
        ensureCapacity(currentLength + 1);
        charArray[currentLength++] = c;
        return this;
    }

    public StringMaker append(final byte b)
    {
      //change to PrimitiveLongUtil
        this.append(Integer.toString(b));
        return this;
    }

    public StringMaker append(final int i)
    {
      //change to PrimitiveLongUtil
        this.append(Integer.toString(i));
        return this;
    }
    
    public StringMaker append(final long l)
    {
      //change to PrimitiveLongUtil
        this.append(Long.toString(l));
        return this;
    }

    public StringMaker append(float f)
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
    
    public StringMaker append(final boolean bool)
    {
        if (bool)
        {
            ensureCapacity(currentLength + 4);
            charArray[currentLength++] = 't';
            charArray[currentLength++] = 'r';
            charArray[currentLength++] = 'u';
            charArray[currentLength++] = 'e';
        } else {
            ensureCapacity(currentLength + 5);
            charArray[currentLength++] = 'f';
            charArray[currentLength++] = 'a';
            charArray[currentLength++] = 'l';
            charArray[currentLength++] = 's';
            charArray[currentLength++] = 'e';
        }
        return this;
    }

    public void ensureCapacity(final int minSize)
    {
        final int oldCapacity = charArray.length;

        if (minSize > oldCapacity) 
        {
            int newCapacity = oldCapacity * 3;

            if (newCapacity < minSize)
                newCapacity = minSize;

            final char[] copy = new char[newCapacity];

            final int min = min(charArray.length, newCapacity);

            System.arraycopy(charArray, 0, copy, 0, min);

            charArray = copy;
        }
    }

    //mathUtil.min(charArray.length, newCapacity)
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
        return new String(charArray, 0, currentLength);
    }
}
