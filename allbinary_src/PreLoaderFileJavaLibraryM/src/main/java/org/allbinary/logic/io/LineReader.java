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
package org.allbinary.logic.io;

import java.io.DataInputStream;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogUtil;

public class LineReader {

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private String fileName;
    private String string;
    private int index = 0;
    private AbFileInputStream idFile;
    private DataInputStream idData;

    public LineReader(final String fileName) {
        try {
            byte bytes[] = new byte[100000];
            this.fileName = fileName;
            idFile = new AbFileInputStream(fileName);
            idFile.read(bytes);
            string = new String(bytes);
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance("File: " + fileName, this, "LineReader", e));
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().IDLOGGING))
            {

            }
        }
    }

    public boolean hasNext() {
        try {
            final int nextIndex = string.indexOf("\n", index);
            if (nextIndex == -1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "hasNext", e));
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().IDLOGGING))
            {

            }
            return false;
        }
    }

    public String next() throws Exception {
        try {
            final int nextIndex = string.indexOf("\n", index);

            if (nextIndex == -1) {
                throw new Exception("next() should have been called first");
            }

            final String temp = string.substring(index, nextIndex);
            index = nextIndex + 1;
            return temp;
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "next", e));
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().IDLOGGING))
            {

            }
            throw e;
        }
    }
}
