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
package org.allbinary.media.image;

public class ImageUnifierProperties
{
    private Integer rows;
    private Integer columns;
    private ImageUnifierCell imageUnifierCell;
    
    public ImageUnifierProperties()
    {
    }

    public int getWidth()
    {
        return this.getColumns().intValue() * this.getImageUnifierCell().getWidth().intValue();
    }

    public int getHeight()
    {
        return this.getRows().intValue() * this.getImageUnifierCell().getHeight().intValue();
    }
    
    public Integer getRows()
    {
        return this.rows;
    }

    public void setRows(Integer rows)
    {
        this.rows = rows;
    }

    public Integer getColumns()
    {
        return this.columns;
    }

    public void setColumns(Integer columns)
    {
        this.columns = columns;
    }

    public ImageUnifierCell getImageUnifierCell()
    {
        return this.imageUnifierCell;
    }

    public void setImageUnifierCell(ImageUnifierCell imageUnifierCell)
    {
        this.imageUnifierCell = imageUnifierCell;
    }
    
}
