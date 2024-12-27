package org.allbinary.util;

import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

//This is a very fast and simple resizable list.
public class BasicArrayList
{
    private static final String SIZE = ", Size: ";
    private final ArrayUtil arrayUtil = ArrayUtil.getInstance();

    public transient Object[] objectArray;
    private int currentIndex = 0;

    public BasicArrayList(final int size)
    {
        super();

        //currentIndex = 0;

        if (size < 0) {
            throw new IllegalArgumentException(new StringMaker().append("Init Size Exception: ").append(size).toString());
        }

        objectArray = new Object[size];
    }

    public BasicArrayList(final Object[] objectArray) {
        this.objectArray = objectArray;
    }
    
    public BasicArrayList()
    {
        this(7);
        
        //currentIndex = 0;
    }

    protected Object[] getObjectArray()
    {
        return objectArray;
    }

    protected void setObjectArray(final Object[] objectArray)
    {
        this.objectArray = objectArray;
    }

    public void add(final int index, final Object element)
    {
        if (index > currentIndex || index < 0) {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
            stringBuffer.append(index);
            stringBuffer.append(SIZE);
            stringBuffer.append(currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        ensureCapacity(currentIndex + 1);
        System.arraycopy(objectArray, index, objectArray, index + 1, currentIndex - index);
        objectArray[index] = element;
        currentIndex++;
    }

    public boolean add(final Object object)
    {
        ensureCapacity(currentIndex + 1);
        objectArray[currentIndex++] = object;
        return true;
    }

    public Object remove(final int index)
    {
        if (index >= currentIndex) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
            stringBuffer.append(index);
            stringBuffer.append(SIZE);
            stringBuffer.append(currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        Object oldValue = objectArray[index];

        final int numMoved = currentIndex - index - 1;
        if (numMoved > 0)
            System.arraycopy(objectArray, index + 1, objectArray, index, numMoved);

        objectArray[--currentIndex] = null;

        return oldValue;
    }

    public boolean remove(final Object object)
    {
        if (object == null) {
            for (int index = 0; index < currentIndex; index++) {
                if (objectArray[index] == null) {
                    final int numMoved = currentIndex - index - 1;

                    if (numMoved > 0)
                        System.arraycopy(objectArray, index + 1, objectArray, index, numMoved);

                    objectArray[--currentIndex] = null;
                    return true;
                }
            }
        }
        else
        {
            for (int index = 0; index < currentIndex; index++) {
                if (object.equals(objectArray[index])) {
                    final int numMoved = currentIndex - index - 1;

                    if (numMoved > 0)
                        System.arraycopy(objectArray, index + 1, objectArray, index, numMoved);

                    objectArray[--currentIndex] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addAll2(final BasicArrayList list)
    {
        ensureCapacity(currentIndex + list.currentIndex);

        int listSize = list.currentIndex;

        for (int index = 0; index < listSize; index++) {
            objectArray[currentIndex++] = list.objectArray[index];
        }

        return true;
    }

    public boolean addAll(final BasicArrayList list)
    {
        final Object[] newObjectArray = list.toArray();
        return this.addAll(newObjectArray);
    }

    public boolean addAll(final Object[] newObjectArray)
    {
        int numSize = newObjectArray.length;
        ensureCapacity(currentIndex + numSize);
        System.arraycopy(newObjectArray, 0, objectArray, currentIndex, numSize);
        currentIndex += numSize;
        return numSize != 0;
    }
    
    public void ensureCapacity(final int minSize)
    {
        final int oldCapacity = objectArray.length;

        if (minSize > oldCapacity) {
            int newCapacity = ((oldCapacity * 3) >> 1) + 1;

            if (newCapacity < minSize)
                newCapacity = minSize;

            objectArray = arrayUtil.copyOf(objectArray, newCapacity);
        }
    }

    public void trimToSize()
    {
        final int oldCapacity = objectArray.length;

        if (currentIndex < oldCapacity)
            objectArray = arrayUtil.copyOf(objectArray, currentIndex);
    }

    public int indexOf(final Object object)
    {
        if (object == null) {
            for (int i = 0; i < currentIndex; i++) {
                if (objectArray[i] == null)
                    return i;
            }
        }
        else
        {
            for (int i = 0; i < currentIndex; i++) {
                if (object.equals(objectArray[i]))
                    return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(final Object object)
    {
        if (object == null) {
            for (int i = currentIndex - 1; i >= 0; i--) {
                if (objectArray[i] == null)
                    return i;
            }
        }
        else
        {
            for (int i = currentIndex - 1; i >= 0; i--) {
                if (object.equals(objectArray[i]))
                    return i;
            }
        }
        return -1;
    }

    public Object get(final int index)
    {
        if (index >= currentIndex) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
            stringBuffer.append(index);
            stringBuffer.append(SIZE);
            stringBuffer.append(currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        return objectArray[index];
    }

    public Object set(final int index, final Object element)
    {
        if (index >= currentIndex) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
            stringBuffer.append(index);
            stringBuffer.append(SIZE);
            stringBuffer.append(currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        final Object oldValue = objectArray[index];
        objectArray[index] = element;
        return oldValue;
    }

    public void clear()
    {
        for (int i = 0; i < currentIndex; i++) 
        {objectArray[i] = null;}

        currentIndex = 0;
    }

    public int size()
    {
        return currentIndex;
    }

    public boolean isEmpty()
    {
        return currentIndex == 0;
    }

    public boolean contains(final Object object)
    {
        return indexOf(object) >= 0;
    }

    public Object[] toArray()
    {
        return arrayUtil.copyOf(objectArray, currentIndex);
    }

    public Object[] toArray(final Object[] objectArray)
    {
        if (objectArray.length < currentIndex)
            return arrayUtil.copyOf(this.objectArray, currentIndex, objectArray.getClass());

        System.arraycopy(this.objectArray, 0, objectArray, 0, currentIndex);

        if (objectArray.length > currentIndex)
            objectArray[currentIndex] = null;

        return objectArray;
    }

    public Object clone()
    {
    	final BasicArrayList list = new BasicArrayList();
    	
    	final int size = this.size();
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
        StringMaker stringBuffer = new StringMaker();

        for (int index = 0; index < currentIndex; index++) {
            stringBuffer.append(objectArray[index].toString());
            stringBuffer.append(COMMA_SEP);
        }
        return stringBuffer.toString();
    }
}