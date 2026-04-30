package org.allbinary.util;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;

//This is a very fast and simple resizable list.
public class BasicArrayList
{
    private static final String SIZE = ", Size: ";
    private final ArrayUtil arrayUtil = ArrayUtil.getInstance();

    public transient Object[] objectArray;
    private int currentIndex = 0;

    public BasicArrayList(final Object[] objectArray) {
        this.objectArray = objectArray;
    }
    
//    protected Object[] getObjectArray()
//    {
//        return objectArray;
//    }
//
//    protected void setObjectArray(final Object[] objectArray)
//    {
//        this.objectArray = objectArray;
//    }

    public void addAt(final int index, final Object element)
    {
        if (index > this.currentIndex || index < 0) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
            stringBuffer.appendint(index);
            stringBuffer.append(BasicArrayList.SIZE);
            stringBuffer.appendint(this.currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        this.ensureCapacity(this.currentIndex + 1);
        System.arraycopy(this.objectArray, index, this.objectArray, index + 1, this.currentIndex - index);
        this.objectArray[index] = element;
        this.currentIndex++;
    }

    public boolean add(final Object object)
    {
        this.ensureCapacity(this.currentIndex + 1);
        this.objectArray[this.currentIndex++] = object;
        return true;
    }

    public Object removeAt(final int index)
    {
        if (index >= this.currentIndex) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
            stringBuffer.appendint(index);
            stringBuffer.append(BasicArrayList.SIZE);
            stringBuffer.appendint(this.currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        Object oldValue = this.objectArray[index];

        final int numMoved = this.currentIndex - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.objectArray, index + 1, this.objectArray, index, numMoved);
        }

        this.objectArray[--this.currentIndex] = null;

        return oldValue;
    }

    public boolean remove(final Object object)
    {
        if (object == null) {
            for (int index = 0; index < this.currentIndex; index++) {
                if (this.objectArray[index] == null) {
                    final int numMoved = this.currentIndex - index - 1;

                    if (numMoved > 0) {
                        System.arraycopy(this.objectArray, index + 1, this.objectArray, index, numMoved);
                    }

                    this.objectArray[--this.currentIndex] = null;
                    return true;
                }
            }
        }
        else
        {
            for (int index = 0; index < this.currentIndex; index++) {
                //Handle cases like File where the equals does not check for the same object
                if (object == this.objectArray[index] || object.equals(this.objectArray[index])) {
                    final int numMoved = this.currentIndex - index - 1;

                    if (numMoved > 0) {
                        System.arraycopy(this.objectArray, index + 1, this.objectArray, index, numMoved);
                    }

                    this.objectArray[--this.currentIndex] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeAll2(final BasicArrayList list) {
    
        boolean result = true;
        
        final int size = list.size();
        for(int index = 0; index < size; index++) {
            if(this.remove(list.get(index))) {
            } else {
                result = false;
            }
        }

        return result;
    }
    
    public boolean addAll2(final BasicArrayList list)
    {
        this.ensureCapacity(this.currentIndex + list.currentIndex);

        int listSize = list.currentIndex;

        for (int index = 0; index < listSize; index++) {
            this.objectArray[this.currentIndex++] = list.objectArray[index];
        }

        return true;
    }

    public boolean addAllList(final BasicArrayList list)
    {
        final Object[] newObjectArray = list.toArray();
        return this.addAll(newObjectArray);
    }

    public boolean addAll(final Object[] newObjectArray)
    {
        final int numSize = newObjectArray.length;
        this.ensureCapacity(this.currentIndex + numSize);
        System.arraycopy(newObjectArray, 0, this.objectArray, this.currentIndex, numSize);
        this.currentIndex += numSize;
        return numSize != 0;
    }
    
    public void ensureCapacity(final int minSize)
    {
        final int oldCapacity = this.objectArray.length;

        if (minSize > oldCapacity) {
            int newCapacity = ((oldCapacity * 3) >> 1) + 1;

            if (newCapacity < minSize) {
                newCapacity = minSize;
            }

            this.objectArray = this.arrayUtil.copyOf(this.objectArray, newCapacity);
        }
    }

    public void trimToSize()
    {
        final int oldCapacity = this.objectArray.length;

        if (this.currentIndex < oldCapacity) {
            this.objectArray = this.arrayUtil.copyOf(this.objectArray, this.currentIndex);
        }
    }

    public int indexOf(final Object object)
    {
        if (object == null) {
            for (int i = 0; i < this.currentIndex; i++) {
                if (this.objectArray[i] == null) {
                    return i;
                }
            }
        }
        else
        {
            for (int i = 0; i < this.currentIndex; i++) {
                if (object.equals(this.objectArray[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(final Object object)
    {
        if (object == null) {
            for (int i = this.currentIndex - 1; i >= 0; i--) {
                if (this.objectArray[i] == null) {
                    return i;
                }
            }
        }
        else
        {
            for (int i = this.currentIndex - 1; i >= 0; i--) {
                if (object.equals(this.objectArray[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Object get(final int index)
    {
        if (index >= this.currentIndex) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
            stringBuffer.appendint(index);
            stringBuffer.append(BasicArrayList.SIZE);
            stringBuffer.appendint(this.currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        return this.objectArray[index];
    }

    public Object set(final int index, final Object element)
    {
        if (index >= this.currentIndex) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
            stringBuffer.appendint(index);
            stringBuffer.append(BasicArrayList.SIZE);
            stringBuffer.appendint(this.currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        final Object oldValue = this.objectArray[index];
        this.objectArray[index] = element;
        return oldValue;
    }

    public void clear()
    {
        for (int i = 0; i < this.currentIndex; i++) 
        {this.objectArray[i] = null;}

        this.currentIndex = 0;
    }

    public int size()
    {
        return this.currentIndex;
    }

    public boolean isEmpty()
    {
        return this.currentIndex == 0;
    }

    public boolean contains(final Object object)
    {
        return this.indexOf(object) >= 0;
    }

    public Object[] toArray()
    {
        return this.arrayUtil.copyOf(this.objectArray, this.currentIndex);
    }

    public Object[] toArrayType(final Object[] objectArray)
    {
        if (objectArray.length < this.currentIndex) {
            return this.arrayUtil.copyOfType(this.objectArray, this.currentIndex, objectArray.getClass());
        }

        System.arraycopy(this.objectArray, 0, objectArray, 0, this.currentIndex);

        if (objectArray.length > this.currentIndex) {
            objectArray[this.currentIndex] = null;
        }

        return objectArray;
    }

    public Object clone()
    {
    	
    	final int size = this.size();
        final BasicArrayList list = new BasicArrayListS(size);
        Object object;
    	for (int index = 0; index < size; index++)
    	{
    	    object = this.objectArray[index];
    	    list.add(object);
    	}
    	
    	return list;
    }

    public String toString()
    {
        final String COMMA_SEP = CommonSeps.getInstance().COMMA_SEP;
        final StringMaker stringBuffer = new StringMaker();

        for (int index = 0; index < this.currentIndex; index++) {
            stringBuffer.append(this.objectArray[index].toString());
            stringBuffer.append(COMMA_SEP);
        }
        return stringBuffer.toString();
    }
}