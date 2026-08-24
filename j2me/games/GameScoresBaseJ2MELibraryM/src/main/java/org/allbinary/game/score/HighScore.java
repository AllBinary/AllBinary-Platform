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
package org.allbinary.game.score;

import jsinterop.annotations.JsType;

//import com.sun.org.apache.bcel.internal.util.ByteSequence;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.allbinary.game.GameInfo;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class HighScore
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final int id;
    private String name;
    private final GameInfo gameInfo;
    private final long score;

    private final String scoreString;
    
    @JsConstructor
    public HighScore(int id, String name, GameInfo gameInfo, long score)
    {
        this.id = id;
        this.name = name;
        this.gameInfo = gameInfo;
        this.score = score;
        this.scoreString = Long.toString(this.score);
        
        //this.logUtil.putF(this.toString(), this, this.commonStrings.CONSTRUCTOR);
    }

    @JsMethod
    public int getId()
    {
        return this.id;
    }

    @JsMethod
    public String getName()
    {
        return this.name;
    }

    @JsMethod
    public long getScore()
    {
        return this.score;
    }

    @JsMethod
    public byte[] getAsBytes() throws Exception
    {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);
        outputStream.writeUTF(this.getName());
        outputStream.writeLong(this.getScore());
        return byteArrayOutputStream.toByteArray();
    }

    @JsMethod
    public GameInfo getGameInfo()
    {
        return this.gameInfo;
    }

    @JsMethod
    public String getScoreString()
    {
        return this.scoreString;
    }

    @JsMethod
    public void setName(String name)
    {
        this.name = name;
    }
    
    @JsMethod
    public String toString() {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        return new StringMaker().append(this.name).append(commonSeps.COLON).appendlong(this.score).append(commonSeps.FORWARD_SLASH).append(this.scoreString).toString();
    }

}
