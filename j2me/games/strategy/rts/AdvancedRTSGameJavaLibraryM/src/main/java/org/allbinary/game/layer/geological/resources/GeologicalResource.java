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
package org.allbinary.game.layer.geological.resources;

/**
 *
 * @author user
 */
public class GeologicalResource {

    private int total;
    private int initTotal;

    public GeologicalResource(int initTotal)
    {
        this.initTotal = initTotal;

        this.init();
    }

    public void init()
    {
        this.setTotal(this.initTotal);
    }

    public void add(int value)
    {
        if(this.getTotal() + value > Integer.MAX_VALUE)
        {
            this.setTotal(Integer.MAX_VALUE);
        }
        else
        {
            this.setTotal(this.getTotal() + value);
        }
    }

    public void remove(int value)
    {
        if(this.getTotal() - value < 0)
        {
            this.setTotal(0);
        }
        else
        {
            this.setTotal(this.getTotal() - value);
        }
    }

    /**
     * @return the total
     */
    public int getTotal()
    {
        return total;
    }

    /**
     * @param total the total to set
     */
    protected void setTotal(int total)
    {
        this.total = total;
    }
}
