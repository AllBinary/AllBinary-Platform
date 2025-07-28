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

import java.util.Hashtable;

import javax.microedition.lcdui.TextField;
import org.allbinary.logic.string.StringUtil;

public class GameConfigurationTextInput
{
    private static Hashtable hashtable = new Hashtable();
    
    public static GameConfigurationTextInput SIMULATOR_IP = 
        new GameConfigurationTextInput("IP: ", "192.168.1.3");
    public static GameConfigurationTextInput SIMULATOR_PORT = 
        new GameConfigurationTextInput("Port: ", "8010");
    
    private String label = StringUtil.getInstance().EMPTY_STRING;
    private String text = StringUtil.getInstance().EMPTY_STRING;
    
    public GameConfigurationTextInput(String label, String text)
    {
        this.setText(text);
        this.setLabel(label);
        hashtable.put(this.getLabel(), this);
    }

    public static void update(TextField textField)
    {
        GameConfigurationTextInput gameConfigurationTextInput =
            (GameConfigurationTextInput)
            hashtable.get(textField.getLabel());
        
        gameConfigurationTextInput.setText(textField.getString());
    }
    
    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }

    public static Hashtable getHashtable()
    {
        return hashtable;
    }
}
