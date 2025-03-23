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
package org.allbinary.game.combat.weapon;

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.math.BasicDecimal;

/**
 *
 * @author user
 */
public class WeaponProperties extends SimpleWeaponProperties
{
    private long reloadTime;
    private long targetingTime;
    private BasicDecimal speed;
    
    private static boolean messageSent = false;
    
    public WeaponProperties(
            long reloadTime, long targetingTime, long speed, int damage, short dissipation)
    {
    	if(speed < 10240 && speed != 0 && !messageSent)
    	{
    	    final String MESSAGE = "Danger Danger Danger: Speed probably to slow if using 1 degree calculations as velocity for a single axis could be below 1024: ";

    	    PreLogUtil.put(new StringMaker().append(MESSAGE).append(speed).toString(), this, CommonStrings.getInstance().CONSTRUCTOR);
    	    //throw new Exception(MESSAGE + speed);
    	    
    	    messageSent = true;
    	}
    	
        this.setReloadTime(reloadTime);
        this.setTargetingTime(targetingTime);
        this.setDamage(damage);
        this.setDissipation(dissipation);
        this.setSpeed(new BasicDecimal(speed));

        if(dissipation != 0)
        {
            this.setRange((int) ((((this.speed.getUnscaled() * damage) / (dissipation * this.speed.getScaledFactorValue()))) * 9) / 10);
            
            /*
            StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append("Range: ");
            stringBuffer.append(this.range);
            stringBuffer.append(" Speed: ");
            stringBuffer.append(this.getSpeed().getScaled());
            stringBuffer.append(" Damage: ");
            stringBuffer.append(damage);
            stringBuffer.append(" Dissipation: ");
            stringBuffer.append(dissipation);

            PreLogUtil.put(stringBuffer.toString(), this, CommonStrings.getInstance().CONSTRUCTOR);
            */
        }
    }

    public WeaponProperties(int speed, int damage, short dissipation)
    {
        this(-1, -1, speed, damage, dissipation);
    }
    
    private void setReloadTime(long reloadTime)
    {
        this.reloadTime = reloadTime;
    }
    
    public long getReloadTime()
    {
        return reloadTime;
    }
    
    private void setTargetingTime(long targetingTime)
    {
        this.targetingTime = targetingTime;
    }
    
    public long getTargetingTime()
    {
        return targetingTime;
    }
    
    /**
     * @param range the range to set
     */
    /*
    private void setRange(int range)
    {
        this.range = range;
    }
    */

    /**
     * @return the speed
     */
    public BasicDecimal getSpeed()
    {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(BasicDecimal speed)
    {
        this.speed = speed;
    }

    public int getDamage(int range)
    {
        return this.getDamage() - ((this.getDissipation() * range) / this.speed.getScaled());
    }

    private static final String DAMAGE = "Damage: ";
    private static final String RANGE = "Range: ";
    private static final String RELOAD = "Reload: ";
    
    public String[] toStringArray()
    {
        int index = 0;

        String[] stringArray = new String[3];
        StringMaker stringBuffer = new StringMaker();
        
        stringArray[index++] = 
            stringBuffer.append(DAMAGE).append(this.getDamage()).toString();

        stringBuffer.delete(0, stringBuffer.length());
        stringArray[index++] = 
            stringBuffer.append(RANGE).append(this.getRange()).toString();
        //stringArray[index++] = "Speed: " + this.getSpeed();

        stringBuffer.delete(0, stringBuffer.length());
        stringArray[index++] =
            stringBuffer.append(RELOAD).append(this.getReloadTime()).toString();
        
        return stringArray;
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
         
        stringBuffer.append(DAMAGE).append(this.getDamage());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(RANGE).append(this.getRange());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(RELOAD).append(this.getReloadTime());

        return stringBuffer.toString();
    }
    
    /*
    long   space;
    long   mass;
    long   structure;      
    long   average_life_expectancy;        
    long   time_in_service_date;   
    long   energy_consumption;
    long   recharge_time;
    long   reliability;
    long   current_reliability;
    
    long[]   radiation_emissions = new long[50]; 
    long   motion_emissions;
    long   power;
    
    long targeting_capabilities; 
    int init_arc_horizontal; 
    int end_arc_horizontal;
    int init_arc_verticle;
    int end_arc_verticle;
    
    long   current_speed;  
    long   current_acceleration;   
    long   speed;  
    long   manuverbility;
    long   acceleration;           
    long   motion_range;
    long   radiation_range;
    
    long   range_cost;
    long   max_focus;
    long   min_focus;
    long   energy_release; //((speed*acceleration)/current focus)-push-pull
                            //if beam is charged in battle it emits the radiation_released instead
    long   radiation_released[50]; //((speed*acceleration*current focus)-push-pull)*radiation
    long   radiation_detection[50];
    
    long   vibration_rate; //Push pull rate
    long   push_or_pull;   
    long   duration;
    long   armor_peircing;
    long   motion_detection;
    long   projectile_range;       
    */
}
