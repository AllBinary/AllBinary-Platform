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

import org.allbinary.string.CommonSeps;

public class HttpData
{
    private static final HttpData instance = new HttpData();

    /**
     * @return the instance
     */
    public static HttpData getInstance()
    {
        return instance;
    }

    private HttpData()
    {
    }

    public final String EQUALS = CommonSeps.getInstance().EQUALS;
    public final String PARAM_SEP = CommonSeps.getInstance().AMP;
    public final String URL_PARAM_SEP = CommonSeps.getInstance().QUESTION;
    public final String URL_LABEL = "Url: ";
}
