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
package org.allbinary.game.rts.technology;

import org.allbinary.game.layer.RTSInterface;

/**
 *
 * @author user
 */
public class SpeedTechnology
implements RTSInterface
{
    private int level;
    private int maxLevel;

    public SpeedTechnology(int maxLevel)
    {
        this.maxLevel = maxLevel;
    }

    @Override
    public boolean isCompleted()
    {
        return false;
    }

    @Override
    public int getPercentComplete()
    {
        return 0;
    }

    @Override
    public int getLevel()
    {
        return level;
    }

    @Override
    public int getMaxLevel()
    {
        return this.maxLevel;
    }

    @Override
    public int getCost()
    {
        return 0;
    }

    @Override
    public void downgrade()
    {

    }

    @Override
    public int getDowngradeCost()
    {
        return 0;
    }

    @Override
    public boolean isDowngradeable()
    {
        return false;
    }

    @Override
    public int getUpgradeCost()
    {
        return (this.getLevel() * this.getLevel()) * 1000;
    }
    
    @Override
    public boolean isUpgradeable()
    {
        if(this.getLevel() < this.getMaxLevel())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void upgrade()
    {
        this.level++;

        UnitTechnologySingletonFactory unitTechnologySingletonFactory =
            UnitTechnologySingletonFactory.getInstance();

        unitTechnologySingletonFactory.setSpeed(
            unitTechnologySingletonFactory.getSpeed() + (this.getLevel() * 100));
    }
    
    @Override
    public boolean isSelfUpgradeable()
    {
        return false;
    }
}
