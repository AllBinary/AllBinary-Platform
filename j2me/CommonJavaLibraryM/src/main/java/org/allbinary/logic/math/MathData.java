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
package org.allbinary.logic.math;

import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.string.CommonSeps;

public class MathData
{

    private static final MathData instance = new MathData();

    public static MathData getInstance()
    {
        return MathData.instance;
    }

    public final String EQUALS;
    public final String PLUS = "+";
    public final String GREATER_THAN = ">";
    public final String MINUS;
    public final String DIVIDE;
    public final String MULTIPLY;
    
    private MathData()
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        this.EQUALS = commonSeps.EQUALS;
        this.MINUS = commonSeps.DASH;
        this.DIVIDE = commonSeps.FORWARD_SLASH;
        this.MULTIPLY = CommonPhoneStrings.getInstance().STAR;
    }

}
