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

import java.util.HashMap;
import java.util.Vector;
import org.allbinary.string.CommonSeps;

public class GameSkill
{
   private static HashMap hashMap = new HashMap();
   
   private GameSkillType gameSkillType;
   private String extra;
   private int time;
   
   private Vector vector;
    
    private final String GAME_SKILL_LABEL = "GameSkill: ";
    private final String EXTRA_LABEL = "Extra: ";
    private final String TIME_LABEL = "Time: ";
   
   public GameSkill(GameSkillType gameSkillType, String extra, int time)
   {
        this.setGameSkillType(gameSkillType);
        this.setExtra(extra);
        this.setTime(time);
        
        this.vector = new Vector();
        
        hashMap.put(new StringBuilder().append(this.getGameSkillType().getName()).append(CommonSeps.getInstance().SPACE).append(extra).toString(), this);
   }
   
    public GameSkillType getGameSkillType()
    {
        return gameSkillType;
    }

    public void setGameSkillType(GameSkillType gameSkillType)
    {
        this.gameSkillType = gameSkillType;
    }
    
    public String getExtra()
    {
        return extra;
    }

    public void setExtra(String extra)
    {
        this.extra = extra;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }
    
    public void addProperty(GameSkillPropertyInterface gameSkillPropertyInterface)
    {
       this.vector.add(gameSkillPropertyInterface);
    }
   
    public Vector getProperties()
    {
       return this.vector;
    }
    
    public static GameSkill getInstance(
        GameSkillType gameSkillType, String extra)
    {
        return (GameSkill) 
           hashMap.get(new StringBuilder().append(gameSkillType.getName()).append(CommonSeps.getInstance().SPACE).append(extra).toString());
    }
    
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(GAME_SKILL_LABEL);
        stringBuffer.append(this.getGameSkillType().toString());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(EXTRA_LABEL);
        stringBuffer.append(this.getExtra());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(TIME_LABEL);
        stringBuffer.append(this.getTime());
        
        return stringBuffer.toString();
    }
}
