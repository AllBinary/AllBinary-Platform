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
package allbinary.graphics.j2me.workarea.canvas;

import allbinary.graphics.GPoint;

/**
 *
 * @author user
 */
public class Grid
{
    public GPoint grid;
    public boolean isGridOn = true;
    public boolean isGridPossible = false;
    private int zoom = 3;
    public boolean isChanged = true;

    public Grid()
    {
    }

    public Grid(Grid grid)
    {
        this.grid = grid.grid;
        this.showGrid(grid.isGridOn);
        this.isGridPossible = grid.isGridPossible;
        this.setZoom(grid.getZoom());
    }

    public boolean isGridOn()
    {
        return this.isGridOn;
    }

    public boolean isGridPossible()
    {
        return this.isGridPossible;
    }

    public GPoint getGrid()
    {
        return this.grid;
    }

    public int getZoom()
    {
        return this.zoom;
    }

    public void showGrid(boolean value)
    {
        this.isGridOn = value;
    }

    public void setZoom(int zoom)
    {
        this.zoom = zoom;
        if (this.zoom < 1)
        {
            zoom = 1;
        }
        this.isChanged = true;
    }
}
