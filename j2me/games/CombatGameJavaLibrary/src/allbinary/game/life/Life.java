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
package allbinary.game.life;

public class Life implements LifeInterface
{
    private short lives;
    private short maxlives;
    
    public Life(short lives, short maxlives) throws Exception
    {
        this.maxlives = maxlives;

        if(lives > this.maxlives)
        {
            throw new Exception("To Many Lives");
        }
    
        this.lives = lives;
    }
    
    public short get()
    {
        return this.lives;
    }
    
    public void set(short lives)
    {
        this.lives = lives;
    }
    
    public void add(short lives)
    {
        if(!this.reachedLimit())
        {
            if(this.lives + lives <= this.getMaxlives())
            {
                this.lives += lives;
            }
            else
            {
                this.lives = this.getMaxlives();
            }
        }
    }

    public boolean isAlive()
    {
        if (this.lives <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public short getMaxlives()
    {
        return maxlives;
    }

    private boolean reachedLimit()
    {
        if (this.lives > this.maxlives || this.lives < 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void deaths(short lives)
    {
        if(!this.reachedLimit())
        {
            if(this.lives - lives >= 0)
            {
                this.lives -= lives;
            }
            else
            {
                this.lives = 0;
            }
        }
    }
}
