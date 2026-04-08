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
        return HttpData.instance;
    }

    public final String EQUALS;
    public final String PARAM_SEP;
    public final String URL_PARAM_SEP;
    public final String URL_LABEL = "Url: ";
    
    private HttpData()
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        this.EQUALS = commonSeps.EQUALS;
        this.PARAM_SEP = commonSeps.AMPERSAND;
        this.URL_PARAM_SEP = commonSeps.QUESTION;
    }

}
