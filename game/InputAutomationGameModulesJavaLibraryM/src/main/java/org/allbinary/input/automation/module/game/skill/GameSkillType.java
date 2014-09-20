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
package org.allbinary.input.automation.module.game.skill;

public class GameSkillType
{
    public static GameSkillType MANA = 
        new GameSkillType("Mana");

    public static GameSkillType REPLENISH = 
        new GameSkillType("Replenish");

    public static GameSkillType TIMED = 
        new GameSkillType("Timed");
    
    public static GameSkillType ATTACK = 
        new GameSkillType("Attack");

    //Add distance property to skill
    //public static GameSkillType RANGED = 
      //  new GameSkillType("Ranged");

    public static GameSkillType TRANSPORT = 
        new GameSkillType("Transport");
    
    private String name;
    
    private GameSkillType(String name)
    {
        this.setName(name);
    }

    public String getName()
    {
        return name;
    }

    protected void setName(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return "SpellGameSkillType: " + this.getName();
    }
}
