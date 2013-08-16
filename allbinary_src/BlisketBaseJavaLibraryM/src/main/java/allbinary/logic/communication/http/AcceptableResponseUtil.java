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
package allbinary.logic.communication.http;

import java.util.Vector;

public class AcceptableResponseUtil
{
    private static final AcceptableResponseUtil instance = new AcceptableResponseUtil();

    public static AcceptableResponseUtil getInstance()
    {
        return instance;
    }

    static
    {
        AcceptableResponseFactory.getInstance().HTML.hashCode();
    }
    public final Vector candidateVector = new Vector();
    public final Vector tagNameVector = new Vector();

    public String get(int index)
    {
        return (String) candidateVector.get(index);
    }

    public String getTagName(int index)
    {
        return (String) tagNameVector.get(index);
    }

    public int size()
    {
        return candidateVector.size();
    }
}
