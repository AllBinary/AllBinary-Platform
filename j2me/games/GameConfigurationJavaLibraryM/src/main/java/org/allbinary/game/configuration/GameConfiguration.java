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
package org.allbinary.game.configuration;

import org.allbinary.game.configuration.event.GameFeatureEvent;
import org.allbinary.game.configuration.event.GameFeatureEventHandler;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.string.StringMaker;

public class GameConfiguration
{
    private String name;
    private Integer defaultValue;
    private Integer value;
    private Integer minValue;
    private Integer maxValue;

    private Boolean modifiable = BooleanFactory.getInstance().TRUE;

    public GameConfiguration(String name, Integer defaultValue,
            Integer minValue, Integer maxValue)
    {
        this.setName(name);
        this.setDefaultValue(defaultValue);
        this.value = this.getDefaultValue();
        this.setMinValue(minValue);
        this.setMaxValue(maxValue);

        GameConfigurationSingleton.getInstance().getHashtable().put(
                this.getName(), this);
    }

    public void setValue(Integer value) throws Exception
    {
        if (this.value != value)
        {
            Integer oldValue = this.value;
            this.value = value;
            GameFeatureEventHandler.getInstance().fireEvent(
                    new GameFeatureEvent(this, new StringMaker().append(this.name).append(" value: from: ").append(oldValue).append(" to ").append(this.value).toString()));
        }
    }

    public Integer getValue()
    {
        return value;
    }

    public void setMaxValue(Integer maxValue)
    {
        this.maxValue = maxValue;
    }

    public Integer getMaxValue()
    {
        return maxValue;
    }

    public void setMinValue(Integer minValue)
    {
        this.minValue = minValue;
    }

    public Integer getMinValue()
    {
        return minValue;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDefaultValue(Integer defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    protected Integer getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefault() throws Exception
    {
        this.setValue(this.getDefaultValue());
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("Name: ");
        stringBuffer.append(this.name);
        stringBuffer.append(" Min: ");
        stringBuffer.append(this.getMinValue());
        stringBuffer.append(" Max: ");
        stringBuffer.append(this.getMaxValue());
        stringBuffer.append(" Value: ");
        stringBuffer.append(this.getValue());
        stringBuffer.append(" Default: ");
        stringBuffer.append(this.getDefaultValue());
        stringBuffer.append(" Modifiable: ");
        stringBuffer.append(this.isModifiable());

        return stringBuffer.toString();
    }

    public void setModifiable(Boolean modifiable)
    {
        this.modifiable = modifiable;
    }

    public Boolean isModifiable()
    {
        return modifiable;
    }

}
