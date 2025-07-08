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
package org.allbinary.data.tree.dom;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.path.AbFilePath;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;

import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class BasicUriResolver implements URIResolver
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final String IMPORT_URL = "/{import url}";
    private final String ATTEMPT = "attempt to use xsl:import: href=";
    private final String BASE = "\nBase= ";
    private final String NEW_PATH = "\nNew path= ";
    private final String NOTE = "\nNote: ";
    private final String URL_GLOBAL = " is a urlglobal";
    private final String REQUIRED_EXTENSION = "\nRequired Extension: ";
    private final String RESOLVE = "resolve";
    
    private String extension;

    //private TransformInfoInterface parentTransformInfoInterface;
    //TransformInfoInterface parentTransformInfoInterface,
    public BasicUriResolver(String extension)
    {
        //this.parentTransformInfoInterface = parentTransformInfoInterface;
        this.extension = extension;
    }

    public String getExtension()
    {
        return this.extension;
    }

    public Source resolve(String href, String base) throws TransformerException
    {
        try
        {
            final StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(URLGLOBALS.getMainPath());
            stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
            stringBuffer.append(href);

            final AbPath abPath = (AbPath) new AbFilePath(stringBuffer.toString());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XMLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(ATTEMPT);
                stringBuffer.append(href);
                stringBuffer.append(BASE);
                stringBuffer.append(base);
                stringBuffer.append(NEW_PATH);
                stringBuffer.append(abPath.toString());
                stringBuffer.append(NOTE);
                stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
                stringBuffer.append(URL_GLOBAL);
                stringBuffer.append(REQUIRED_EXTENSION);
                stringBuffer.append(extension);

                logUtil.put(stringBuffer.toString(), this, RESOLVE);
            }

            return new StreamSource(new CryptFileReader(
                    TransformInfoTemplateData.getInstance().UNCRYPTED_EXTENSION,
                    TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION).getInputStream(abPath));
        } catch (TransformerException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new TransformerException(e);
        }
    }

    public String toString()
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(URLGLOBALS.getMainPath());
            stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
            stringBuffer.append(IMPORT_URL);

            return stringBuffer.toString();
        } catch (Exception e)
        {
            //Log Error
            return "BasicUriResolver - Does not work without webapp path should be changed";
        }
    }
}
