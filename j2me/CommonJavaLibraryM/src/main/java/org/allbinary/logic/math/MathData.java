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
        return instance;
    }

    private MathData()
    {
    }

    public String EQUALS = CommonSeps.getInstance().EQUALS;
    public String PLUS = "+";
    public String MINUS = "-";
    public String DIVIDE = CommonSeps.getInstance().FORWARD_SLASH;
    public String MULTIPLY = CommonPhoneStrings.getInstance().STAR;
}
