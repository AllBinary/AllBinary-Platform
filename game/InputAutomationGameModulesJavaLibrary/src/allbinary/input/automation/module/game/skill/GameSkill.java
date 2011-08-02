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
package allbinary.input.automation.module.game.skill;

import java.util.HashMap;
import java.util.Vector;

public class GameSkill
{
   private static HashMap hashMap = new HashMap();
   
   private GameSkillType gameSkillType;
   private String extra;
   private int time;
   
   private Vector vector;
   
   public GameSkill(GameSkillType gameSkillType, String extra, int time)
   {
        this.setGameSkillType(gameSkillType);
        this.setExtra(extra);
        this.setTime(time);
        
        this.vector = new Vector();
        
        hashMap.put(this.getGameSkillType().getName() + " " + extra, this);
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
           hashMap.get(gameSkillType.getName() + " " + extra);
    }
    
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("GameSkill: ");
        stringBuffer.append(this.getGameSkillType().toString());
        stringBuffer.append(" Extra: ");
        stringBuffer.append(this.getExtra());
        stringBuffer.append(" Time: ");
        stringBuffer.append(this.getTime());
        
        return stringBuffer.toString();
    }
}
