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
package org.allbinary.graphics.j2me;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author user
 */
public class StatusFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final StatusFactory instance = new StatusFactory();

    /**
     * @return the instance
     */
    public static StatusFactory getInstance()
    {
        return instance;
    }

    private final String SETSTATUS = "setStatus";
    private final String STATUS_LABEL = "Status: ";

    public void setStatusNoLog(String status)
    {
        MyFrame.statusJLabel.setText(status);
    }

    public void setStatus(String status)
    {
        setStatusNoLog(status);
        logUtil.put(STATUS_LABEL + status, this, SETSTATUS);
    }

}
