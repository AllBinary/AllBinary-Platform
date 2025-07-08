package org.allbinary.media.graphics.geography.pathfinding;

import org.allbinary.logic.util.cache.BaseBasicArrayListCache;
import org.allbinary.util.BasicArrayList;

public class BasicGeographicMapExtractedPathCacheFactory
    extends BaseBasicArrayListCache
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final  BasicGeographicMapExtractedPathCacheFactory instance =
        new  BasicGeographicMapExtractedPathCacheFactory();

    /**
     * @return the instance
     */
    public static  BasicGeographicMapExtractedPathCacheFactory getInstance()
    {
        return instance;
    }
    
    private int maxConcurrentPaths;
    private int maxPaths;

    private BasicGeographicMapExtractedPathCacheFactory()
    {
    }

    public void init(
        int maxConcurrentPaths, int maxPaths)
    {
        //TWB - Size Optimization
        //maxConcurrentPaths = 0;
        //maxPaths = 0;

        if (maxConcurrentPaths != this.maxConcurrentPaths)
        {
            for (int index = maxConcurrentPaths; index >= this.maxConcurrentPaths; index--)
            {
                this.getList().add(new BasicArrayList(maxPaths));
            }
            this.maxConcurrentPaths = maxConcurrentPaths;
        }
        this.maxPaths = maxPaths;
    }

    public BasicArrayList getPath()
    {
        if (this.getList().size() > 0)
        {
            return (BasicArrayList) this.getList().remove(0);
        }
        else
        {
            return new BasicArrayList(this.maxPaths);
        }
        //return new BasicArrayList(this.maxPaths);
    }

    public void release(BasicArrayList list)
    {
        list.clear();
        this.getList().add(list);

        //logUtil.put("List: " + this.log(), this, "clear");
    }
}
