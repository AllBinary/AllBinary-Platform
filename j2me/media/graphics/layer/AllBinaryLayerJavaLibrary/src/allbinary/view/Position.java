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
package allbinary.view;

class Position
{
    private final int x;
    private final int y;
    private final int z;

    protected Position()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    
    protected Position(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    protected Position(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    protected int getPositionX()
    {
       return this.x;
    }

    protected int getPositionY()
    {
       return this.y;
    }

    protected int getPositionZ()
    {
       return this.z;
    }
    
    public int getX()
    {
       return this.x;
    }

    public int getY()
    {
       return this.y;
    }

    public int getZ()
    {
       return this.z;
    }
    
    /*
    public void setX(int x)
    {
       this.x = x;
    }

    public void setY(int y)
    {
       this.y = y;
    }
    */
}
