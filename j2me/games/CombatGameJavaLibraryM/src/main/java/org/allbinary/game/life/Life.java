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
package org.allbinary.game.life;

public class Life implements LifeInterface
{
    public static final Life NO_LIFE = new Life();
    
    private short lives;
    private short startLives;
    private short maxlives;

    public Life()
    {
        this.lives = 0;
        this.startLives = 0;
        this.maxlives = 0;
    }
    
    public Life(short lives, short maxlives) throws Exception
    {
        this.maxlives = maxlives;

        if(lives > this.maxlives)
        {
            throw new Exception("To Many Lives");
        }
    
        this.lives = lives;
        this.startLives = lives;
    }

    @Override    
    public short get()
    {
        return this.lives;
    }
    
    @Override
    public void set(short lives)
    {
        this.lives = lives;
    }

    @Override
    public void reset()
    {
        this.lives = this.startLives;
        //this.startLives = lives;
    }
    
    @Override
    public void add(short lives)
    {
        if(!this.reachedLimit())
        {
            if(this.lives + lives <= this.getMaxlives())
            {
                this.lives = (short) (this.lives + lives);
            }
            else
            {
                this.lives = this.getMaxlives();
            }
        }
    }

    @Override
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
    
    @Override
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
    
    @Override
    public void deaths(short lives)
    {
        if(!this.reachedLimit())
        {
            if(this.lives - lives >= 0)
            {
                this.lives = (short) (this.lives - lives);
            }
            else
            {
                this.lives = 0;
            }
        }
    }

    /**
     * @return the startLives
     */
    public short getStartLives() {
        return startLives;
    }

    /**
     * @param startLives the startLives to set
     */
    public void setStartLives(short startLives) {
        this.startLives = startLives;
    }
}
