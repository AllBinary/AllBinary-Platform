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
package org.allbinary.logic.communication.http;

import java.util.Vector;
import org.allbinary.logic.TsUtil;

public class AcceptableResponseUtil
{
    private static final AcceptableResponseUtil instance = new AcceptableResponseUtil();

    public static AcceptableResponseUtil getInstance()
    {
        return AcceptableResponseUtil.instance;
    }

    static
    {
        TsUtil.getInstance().hashCode(AcceptableResponseFactory.getInstance().HTML);
    }
    public final Vector candidateVector = new Vector();
    public final Vector tagNameVector = new Vector();

    public String get(int index)
    {
        return (String) this.candidateVector.get(index);
    }

    public String getTagName(int index)
    {
        return (String) this.tagNameVector.get(index);
    }

    public int size()
    {
        return this.candidateVector.size();
    }
}
