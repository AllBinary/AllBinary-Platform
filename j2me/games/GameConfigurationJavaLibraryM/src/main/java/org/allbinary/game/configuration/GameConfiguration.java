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
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class GameConfiguration
{
    private String name = StringUtil.getInstance().EMPTY_STRING;
    private Integer defaultValue = SmallIntegerSingletonFactory.getInstance().getInstance(0);
    private Integer value;
    private Integer minValue = defaultValue;
    private Integer maxValue = defaultValue;

    private Boolean modifiable = BooleanFactory.getInstance().TRUE;

    public GameConfiguration(final String name, final Integer defaultValue,
            final Integer minValue, final Integer maxValue)
    {
        this.setName(name);
        this.setDefaultValue(defaultValue);
        this.value = this.getDefaultValue();
        this.setMinValue(minValue);
        this.setMaxValue(maxValue);

        GameConfigurationSingleton.getInstance().getHashtable().put(
                this.getName(), this);
    }

    public void setValue(final Integer value) throws Exception
    {
        if (this.value != value)
        {
            final Integer oldValue = this.value;
            this.value = value;
            GameFeatureEventHandler.getInstance().fireEvent(
                    new GameFeatureEvent(this, new StringMaker().append(this.name).append(" value: from: ").append(oldValue.intValue()).append(" to ").append(this.value.intValue()).toString()));
        }
    }

    public Integer getValue()
    {
        return value;
    }

    public void setMaxValue(final Integer maxValue)
    {
        this.maxValue = maxValue;
    }

    public Integer getMaxValue()
    {
        return maxValue;
    }

    public void setMinValue(final Integer minValue)
    {
        this.minValue = minValue;
    }

    public Integer getMinValue()
    {
        return minValue;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDefaultValue(final Integer defaultValue)
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
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("Name: ");
        stringBuffer.append(this.name);
        stringBuffer.append(" Min: ");
        stringBuffer.append(this.getMinValue().intValue());
        stringBuffer.append(" Max: ");
        stringBuffer.append(this.getMaxValue().intValue());
        stringBuffer.append(" Value: ");
        stringBuffer.append(this.getValue().intValue());
        stringBuffer.append(" Default: ");
        stringBuffer.append(this.getDefaultValue().intValue());
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
