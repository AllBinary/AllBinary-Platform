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

package org.allbinary.data.tree.dom;

import java.io.ByteArrayOutputStream;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.path.AbPath;

/**
 *
 * @author User
 */
public class AbXslHelper {

    private static final AbXslHelper instance = new AbXslHelper();

    /**
     * @return the instance
     */
    public static AbXslHelper getInstance() {
        return instance;
    }

    public String translate(final AbFile xslFile, final AbFile xmlFile)
            throws Exception
    {
        return this.translate(xslFile.getAbsolutePath(), xmlFile.getAbsolutePath());
    }

    public String translate(final AbPath xslPath, final AbPath xmlPath)
            throws Exception
    {
        return this.translate(xslPath.getPath(), xmlPath.getPath());
    }
    
    public String translate(final String xsltFilePath, final String xmlFilePath)
            throws Exception
    {
        try
        {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            return XslHelper.getInstance().translate(new StreamSource(xsltFilePath),
                    new StreamSource(xmlFilePath),
                    new StreamResult(outputStream)).toString();
        } catch (Exception e)
        {
            throw e;
        }
    }
    
}