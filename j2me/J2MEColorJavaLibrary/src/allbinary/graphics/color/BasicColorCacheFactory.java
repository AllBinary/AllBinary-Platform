/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Sep 9, 2007, 1:52:32 AM
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.graphics.color;

import java.util.Hashtable;

public class BasicColorCacheFactory
{
    private static final BasicColorCacheFactory instance = new BasicColorCacheFactory();

    public static BasicColorCacheFactory getInstance()
    {
        return instance;
    }

    private Hashtable hashtable = new Hashtable();

    private BasicColorCacheFactory()
    {
    }

    public void add(BasicColor basicDefaultColor)
    {
        // hashtable.put(Integer.valueOf(value), this);
        hashtable.put(new Integer(basicDefaultColor.intValue()),
                basicDefaultColor);
    }

    public synchronized BasicColor getInstance(Integer integer)
    {
        BasicColor basicColor = (BasicColor) hashtable.get(integer);

        if (basicColor == null)
        {
            basicColor = new BasicColor(integer.intValue());
            hashtable.put(integer, basicColor);
        }

        return basicColor;
    }
}
