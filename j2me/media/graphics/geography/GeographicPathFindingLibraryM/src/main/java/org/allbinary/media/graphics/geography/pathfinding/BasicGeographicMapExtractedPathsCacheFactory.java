package org.allbinary.media.graphics.geography.pathfinding;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.util.cache.BaseBasicArrayListCache;
import org.allbinary.media.graphics.geography.pathfinding.BasicGeographicMapExtractedPathCacheFactory;

public class BasicGeographicMapExtractedPathsCacheFactory
    extends BaseBasicArrayListCache
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final BasicGeographicMapExtractedPathsCacheFactory instance =
        new BasicGeographicMapExtractedPathsCacheFactory();

    /**
     * @return the instance
     */
    public static BasicGeographicMapExtractedPathsCacheFactory getInstance()
    {
        return instance;
    }

    private int maxConcurrentPaths;
    private int maxPaths;

    private BasicGeographicMapExtractedPathsCacheFactory()
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

    public BasicArrayList getPaths()
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
        //PreLogUtil.put("paths: " + BasicArrayListUtil.log(list), this, "release");

        for(int index = list.size() - 1; index >= 0; index--)
        {
            BasicGeographicMapExtractedPathCacheFactory.getInstance().release(
                (BasicArrayList) list.get(index));
        }
        
        list.clear();
        this.getList().add(list);

        //logUtil.put("List: " + this.log(), this, "clear");
    }
}
