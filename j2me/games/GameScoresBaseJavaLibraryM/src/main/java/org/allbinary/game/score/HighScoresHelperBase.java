/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class HighScoresHelperBase implements HighScoresHelperBaseInterface {
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected HighScores[] highScoresArrayP = 
        LastFetchHighScoresFactory.getInstance().highScoresArray;

    @Override
    @JsMethod
    public void setHighScoresArray(final HighScores[] highScoresArrayP)
    {
        if (highScoresArrayP != null) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.putF(new StringMaker().append(commonStrings.START).appendint(highScoresArrayP.length).toString(), this, "setHighScoresArray");
        } else {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.putF(commonStrings.START, this, "setHighScoresArray");
        }

        this.highScoresArrayP = highScoresArrayP;
    }
    
    @JsMethod
    public HighScores getNextHighScores()
    {
        return NullHighScoresSingletonFactory.getInstance();
    }
    
    @JsMethod
    public boolean isAnyHighScores() {
        throw new RuntimeException();
    }

    @Override
    @JsMethod
    public void setSelectedHighScores(final HighScores selectedHighScores)
    {
    }

    @Override
    @JsMethod
    public HighScores getSelectedHighScores()
    {
        return NullHighScoresSingletonFactory.getInstance();
    }
    
    @Override
    @JsMethod
    public HighScores[] getHighScoresArray()
    {
        return this.highScoresArrayP;
    }

    @JsMethod
    public void selectHighScores() {
        
    }

}
