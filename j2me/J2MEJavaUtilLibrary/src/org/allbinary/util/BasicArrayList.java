/*
* AllBinary Open License Version 1
* Copyright (c) 1996, 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
*/
package org.allbinary.util;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.CommonStrings;

//This is a very fast and simple resizable list.
public class BasicArrayList
{
    private static final String SIZE = ", Size: ";
    private final ArrayUtil arrayUtil = ArrayUtil.getInstance();

    private transient Object[] objectArray;
    private int currentIndex;

    public BasicArrayList(int size)
    {
        super();

        if (size < 0)
            throw new IllegalArgumentException("Init Size Exception: " + size);

        objectArray = new Object[size];
    }

    public BasicArrayList()
    {
        this(7);
    }

    protected Object[] getObjectArray()
    {
        return objectArray;
    }

    protected void setObjectArray(Object[] objectArray)
    {
        this.objectArray = objectArray;
    }

    public void add(int index, Object element)
    {
        if (index > currentIndex || index < 0) {
            StringBuilder stringBuffer = new StringBuilder();

            stringBuffer.append(CommonStrings.getInstance().INDEX_LABEL);
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

    public boolean add(Object object)
    {
        ensureCapacity(currentIndex + 1);
        objectArray[currentIndex++] = object;
        return true;
    }

    public Object remove(int index)
    {
        if (index >= currentIndex) {
            StringBuilder stringBuffer = new StringBuilder();

            stringBuffer.append(CommonStrings.getInstance().INDEX_LABEL);
            stringBuffer.append(index);
            stringBuffer.append(SIZE);
            stringBuffer.append(currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        Object oldValue = objectArray[index];

        int numMoved = currentIndex - index - 1;
        if (numMoved > 0)
            System.arraycopy(objectArray, index + 1, objectArray, index, numMoved);

        objectArray[--currentIndex] = null;

        return oldValue;
    }

    public boolean remove(Object object)
    {
        if (object == null) {
            for (int index = 0; index < currentIndex; index++) {
                if (objectArray[index] == null) {
                    int numMoved = currentIndex - index - 1;

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
                    int numMoved = currentIndex - index - 1;

                    if (numMoved > 0)
                        System.arraycopy(objectArray, index + 1, objectArray, index, numMoved);

                    objectArray[--currentIndex] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addAll2(BasicArrayList list)
    {
        ensureCapacity(currentIndex + list.currentIndex);

        int listSize = list.currentIndex;

        for (int index = 0; index < listSize; index++) {
            objectArray[currentIndex++] = list.get(index);
        }

        return true;
    }

    public boolean addAll(BasicArrayList list)
    {
        Object[] newObjectArray = list.toArray();
        int numSize = newObjectArray.length;
        ensureCapacity(currentIndex + numSize);
        System.arraycopy(newObjectArray, 0, objectArray, currentIndex, numSize);
        currentIndex += numSize;
        return numSize != 0;
    }

    public void ensureCapacity(int minSize)
    {
        int oldCapacity = objectArray.length;

        if (minSize > oldCapacity) {
            int newCapacity = ((oldCapacity * 3) >> 1) + 1;

            if (newCapacity < minSize)
                newCapacity = minSize;

            objectArray = arrayUtil.copyOf(objectArray, newCapacity);
        }
    }

    public void trimToSize()
    {
        int oldCapacity = objectArray.length;

        if (currentIndex < oldCapacity)
            objectArray = arrayUtil.copyOf(objectArray, currentIndex);
    }

    public int indexOf(Object object)
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

    public int lastIndexOf(Object object)
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

    public Object get(int index)
    {
        if (index >= currentIndex) {
            StringBuilder stringBuffer = new StringBuilder();

            stringBuffer.append(CommonStrings.getInstance().INDEX_LABEL);
            stringBuffer.append(index);
            stringBuffer.append(SIZE);
            stringBuffer.append(currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        return objectArray[index];
    }

    public Object set(int index, Object element)
    {
        if (index >= currentIndex) {
            StringBuilder stringBuffer = new StringBuilder();

            stringBuffer.append(CommonStrings.getInstance().INDEX_LABEL);
            stringBuffer.append(index);
            stringBuffer.append(SIZE);
            stringBuffer.append(currentIndex);

            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }

        Object oldValue = objectArray[index];
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

    public boolean contains(Object object)
    {
        return indexOf(object) >= 0;
    }

    public Object[] toArray()
    {
        return arrayUtil.copyOf(objectArray, currentIndex);
    }

    public Object[] toArray(Object[] objectArray)
    {
        if (objectArray.length < currentIndex)
            return arrayUtil.copyOf(this.objectArray, currentIndex, objectArray.getClass());

        System.arraycopy(this.objectArray, 0, objectArray, 0, currentIndex);

        if (objectArray.length > currentIndex)
            objectArray[currentIndex] = null;

        return objectArray;
    }

    public String toString()
    {
        final String COMMA_SEP = CommonSeps.getInstance().COMMA_SEP;
        StringBuilder stringBuffer = new StringBuilder();

        for (int index = 0; index < currentIndex; index++) {
            stringBuffer.append(get(index).toString());
            stringBuffer.append(COMMA_SEP);
        }
        return stringBuffer.toString();
    }
}