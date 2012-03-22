/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.graphics;

public class PointFactory
{
    private static final PointFactory instance = new PointFactory();

    public static PointFactory getInstance()
    {
        return instance;
    }
    
    public GPoint ZERO_ZERO = this.getInstance(0, 0, 0);
            //SmallIntegerSingletonFactory.getInstance(0), SmallIntegerSingletonFactory.getInstance(0));

    //private static int total = 0;

    //private static final Hashtable hashtable = new Hashtable();

    public void init()
    {
        /*
         * for(int index = 0; index < POINT_ARRAY.length; index++) { for(int
         * index2 = 0; index2 < POINT_ARRAY[0].length; index2++) {
         * POINT_ARRAY[index][index2] = new GPoint(index, index2); } }
         */
    }

    private PointFactory()
    {
    }

    //TWB - to be depricated
    public GPoint getInstance(int x, int y)
    {
        return new GPoint(x, y, 0); 
    }
    
    //TWB - Major misuse of point factory
    //Game Level points should use the cellPosition factory instead or simply arrays
    //MotionRecognizer should use a new point cache that is releasable and sets the x, y for each get from cache
    //The rest are mainly static or part of each game level as well
    public GPoint getInstance(int x, int y, int z) //throws Exception
    {
        return new GPoint(x, y, z); 
        //PointFactory.getInstance(
                //SmallIntegerSingletonFactory.getInstance(x), SmallIntegerSingletonFactory.getInstance(y));
        
        /*
        Integer xInteger = SmallIntegerSingletonFactory.getInstance(x);
        Integer yInteger = SmallIntegerSingletonFactory.getInstance(y);

        //|| x > 1025 || y > 1025
        if (total > 1025)
        {
            return getInstance(xInteger, yInteger);
        }
        else
        {
            Hashtable yHashtable = (Hashtable) PointFactory.hashtable
                    .get(xInteger);

            if (yHashtable == null)
            {
                yHashtable = new Hashtable();
                PointFactory.hashtable.put(xInteger, yHashtable);
            }

            GPoint point = (GPoint) yHashtable.get(yInteger);

            if (point != null)
            {
                return point;
            }
            else
            {
                point = getInstance(xInteger, yInteger);

                hashtable.put(yInteger, point);
                total++;

                return point;
            }
        }
        */
    }
}
