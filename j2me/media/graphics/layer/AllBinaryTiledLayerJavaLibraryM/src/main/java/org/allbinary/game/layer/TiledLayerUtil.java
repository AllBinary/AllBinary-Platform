/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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

package org.allbinary.game.layer;


/**
 *
 * @author user
 */
public class TiledLayerUtil {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private static final TiledLayerUtil instance = new TiledLayerUtil();

    public static TiledLayerUtil getInstance()
    {
        return instance;
    }
    
    /*
    public void keepOnMap(AllBinaryTiledLayer terrainTiledLayer,
        AllBinaryLayer layer)
    {
        int mapBorderBufferHeight = terrainTiledLayer.getCellHeight();
        int mapBorderBufferWidth = terrainTiledLayer.getCellWidth();
        keepOnMap(terrainTiledLayer, layer, mapBorderBufferHeight, mapBorderBufferWidth);
    }
    */

    /*
    public static void keepOnMap(AllBinaryTiledLayer terrainTiledLayer,
            AllBinaryLayer layer,
            int x, int y,
            int mapBorderBufferHeight, int mapBorderBufferWidth)
    {
        //int x = layer.getX();
        //int y = layer.getY();

        int newX = x;
        int newY = y;

        // logUtil.put("pre scrollTerrain X: " +
        // terrainTiledLayer.getX(), this, "keepOnMap");
        // logUtil.put("pre scrollTerrain Y: " +
        // terrainTiledLayer.getY(), this, "keepOnMap");

        // StringMaker stringBuffer = new StringMaker();

        int maxRight = terrainTiledLayer.getWidth() - mapBorderBufferWidth;
        if (x > maxRight)
        {
            // Reached the right border so don't go beyond the map
            newX = maxRight;
            // logUtil.put("Right: < " + maxRight, this,
            // "keepOnMap");
            // stringBuffer.append("Right: < ");
            // stringBuffer.append(maxRight);
        }

        if (x < mapBorderBufferWidth)
        {
            // Reached the left boundry so don't go beyond the map
            newX = mapBorderBufferWidth;
            // logUtil.put("Left: > " +
            // mapBorderBufferWidth, this,
            // "keepOnMap");
            // stringBuffer.append(" Left: > ");
            // stringBuffer.append(mapBorderBufferWidth);
        }

        int maxBottom = terrainTiledLayer.getHeight() - mapBorderBufferHeight;
        if (y > maxBottom)
        {
            // Reached the bottom so don't go beyond the map
            newY = maxBottom;
            // logUtil.put("Bottom: < " + maxBottom,
            // this,
            // "keepOnMap");
            // stringBuffer.append(" Bottom: < ");
            // stringBuffer.append(maxBottom);
        }

        if (y < mapBorderBufferHeight)
        {
            // Reached the top so don't go beyond the map
            newY = mapBorderBufferHeight;
            // logUtil.put("Top: > " +
            // mapBorderBufferHeight, this,
            // "keepOnMap");
            // stringBuffer.append(" Top: > ");
            // stringBuffer.append(mapBorderBufferHeight);
        }

        //if (x != newX || y != newY)
        //{
            // logUtil.put(stringBuffer.toString() +
            // " X: " + this.x + " Y: " + this.y, this, "keepOnMap");
            layer.setPosition(newX, newY);
        //}

        // logUtil.put("scrollTerrain X: " +
        // terrainTiledLayer.getX(),
        // this, "keepOnMap");
        // logUtil.put("scrollTerrain Y: " +
        // terrainTiledLayer.getY(),
        // this, "keepOnMap");
    }
    */

    public int keepOnMapX(
            AllBinaryTiledLayer terrainTiledLayer,
            int x, int width, int mapBorderBufferWidth)
    {
        int newX = x;

        // logUtil.put("pre scrollTerrain X: " +
        // terrainTiledLayer.getX(), this, "keepOnMap");

        // StringMaker stringBuffer = new StringMaker();

        int maxRight = terrainTiledLayer.getWidth() - width - mapBorderBufferWidth;
        if (x + width > maxRight)
        {
            // Reached the right border so don't go beyond the map
            newX = maxRight;
            // logUtil.put("Right: < " + maxRight, this,
            // "keepOnMap");
            // stringBuffer.append("Right: < ");
            // stringBuffer.append(maxRight);
        }

        if (x < mapBorderBufferWidth)
        {
            // Reached the left boundry so don't go beyond the map
            newX = mapBorderBufferWidth;
            // logUtil.put("Left: > " +
            // mapBorderBufferWidth, this,
            // "keepOnMap");
            // stringBuffer.append(" Left: > ");
            // stringBuffer.append(mapBorderBufferWidth);
        }

        return newX;
    }

    public int keepOnMapY(AllBinaryTiledLayer terrainTiledLayer,
            int y, int height, int mapBorderBufferHeight)
    {
        int newY = y;

        // logUtil.put("pre scrollTerrain X: " +
        // terrainTiledLayer.getX(), this, "keepOnMap");
        // logUtil.put("pre scrollTerrain Y: " +
        // terrainTiledLayer.getY(), this, "keepOnMap");

        // StringMaker stringBuffer = new StringMaker();

        int maxBottom = terrainTiledLayer.getHeight() - height - mapBorderBufferHeight;
        if (y + height > maxBottom)
        {
            // Reached the bottom so don't go beyond the map
            newY = maxBottom;
            // logUtil.put("Bottom: < " + maxBottom,
            // this,
            // "keepOnMap");
            // stringBuffer.append(" Bottom: < ");
            // stringBuffer.append(maxBottom);
        }

        if (y < mapBorderBufferHeight)
        {
            // Reached the top so don't go beyond the map
            newY = mapBorderBufferHeight;
            // logUtil.put("Top: > " +
            // mapBorderBufferHeight, this,
            // "keepOnMap");
            // stringBuffer.append(" Top: > ");
            // stringBuffer.append(mapBorderBufferHeight);
        }

        return newY;
    }

    public int keepOnMapX(
            AllBinaryTiledLayer terrainTiledLayer,
            int x, int width)
    {
        int newX = x;

        // logUtil.put("pre scrollTerrain X: " +
        // terrainTiledLayer.getX(), this, "keepOnMap");

        // StringMaker stringBuffer = new StringMaker();

        int maxRight = terrainTiledLayer.getWidth() - width;
        if (x >= maxRight)
        {
            // Reached the right border so don't go beyond the map
            newX = maxRight;
            // logUtil.put("Right: < " + maxRight, this,
            // "keepOnMap");
            // stringBuffer.append("Right: < ");
            // stringBuffer.append(maxRight);
        }

        if (x < 0)
        {
            // Reached the left boundry so don't go beyond the map
            newX = 0;
            // logUtil.put("Left: > " +
            // mapBorderBufferWidth, this,
            // "keepOnMap");
            // stringBuffer.append(" Left: > ");
            // stringBuffer.append(mapBorderBufferWidth);
        }

        return newX;
    }

    public int keepOnMapY(AllBinaryTiledLayer terrainTiledLayer,
            int y, int height)
    {
        int newY = y;

        // logUtil.put("pre scrollTerrain X: " +
        // terrainTiledLayer.getX(), this, "keepOnMap");
        // logUtil.put("pre scrollTerrain Y: " +
        // terrainTiledLayer.getY(), this, "keepOnMap");

        // StringMaker stringBuffer = new StringMaker();

        int maxBottom = terrainTiledLayer.getHeight() - height;
        if (y >= maxBottom)
        {
            // Reached the bottom so don't go beyond the map
            newY = maxBottom;
            // logUtil.put("Bottom: < " + maxBottom,
            // this,
            // "keepOnMap");
            // stringBuffer.append(" Bottom: < ");
            // stringBuffer.append(maxBottom);
        }

        if (y < 0)
        {
            // Reached the top so don't go beyond the map
            newY = 0;
            // logUtil.put("Top: > " +
            // mapBorderBufferHeight, this,
            // "keepOnMap");
            // stringBuffer.append(" Top: > ");
            // stringBuffer.append(mapBorderBufferHeight);
        }

        return newY;
    }
    
}
