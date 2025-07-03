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
package org.allbinary.logic.visual.transform.info;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.logic.visual.transform.TransformInterface;
import org.allbinary.string.CommonStrings;

public class TransformInfoObjectFactory
{
    private static final TransformInfoObjectFactory instance = new TransformInfoObjectFactory();
    
    public static TransformInfoObjectFactory getInstance() {
        return instance;
    }
	
    private TransformInfoObjectFactory()
    {
    }

    public TransformInterface getInstance(
        final AbeClientInformationInterface abeClientInformation,
        final TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY))
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance("Creating View: " + transformInfoInterface.getName(), this, commonStrings.GET_INSTANCE));
            }

            //validate view data before creating view object
            Object params[] = new Object[1];
            Class classes[] = new Class[1];

            //Add param types
            //TWB - Moved to obf on everything that is no bolted to dynamic loading
            classes[0] = TransformInfoInterface.class;
                //AbeFactory.getInstance().getClass(
                //"org.allbinary.logic.visual.transform.info.TransformInfoInterface");

            //Add arguments
            params[0] = (Object) transformInfoInterface;

            final TransformInterface object = (TransformInterface) AbeFactory.getInstance().getInstance(
                abeClientInformation, transformInfoInterface.getObjectFile(), classes, params);

            if (object == null)
            {
                if (transformInfoInterface != null)
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("\nNo Such View Name: ");
                    stringBuffer.append(transformInfoInterface.getName());
                    stringBuffer.append("\nNo Such View Object: ");
                    stringBuffer.append(transformInfoInterface.getObjectFile());

                    throw new Exception(stringBuffer.toString());
                } else
                {
                    throw new Exception("No Such View Object since transformInfo is null");
                }
            }

            return object;
        } catch (LicensingException e)
        {
            String error = "Failed To Get Instance: ";

            if (transformInfoInterface != null)
            {
                error = error + transformInfoInterface.getName();
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(error, this, commonStrings.GET_INSTANCE, e));
            }
            throw e;
        } catch (Exception e)
        {
            String error = "Failed To Get Instance: ";

            if (transformInfoInterface != null)
            {
                error = error + transformInfoInterface.getName();
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(error, this, commonStrings.GET_INSTANCE, e));
            }
            throw e;
        }
    }
    /*
    public static ComponentInterface getInstance(TransformInfoInterface transformInfoInterface, HashMap propertiesHashMap, PageContext pageContext) throws Exception
    {
    try
    {
    //validate view data before creating view object
    Object params[] = new Object[3];
    Class classes[] = new Class[3];
    
    //Add param types
    classes[0] = AbeFactory.getInstance().getClass("org.allbinary.logic.visual.transform.info.TransformInfoInterface");
    classes[1] = propertiesHashMap.getClass();
    classes[2] = AbeFactory.getInstance().getClass("javax.servlet.jsp.PageContext");
    
    //Add arguments
    params[0] = (Object) transformInfoInterface;
    params[1] = (Object) propertiesHashMap;
    params[2] = (Object) pageContext;
    
    return (ComponentInterface) AbeFactory.getInstance().getInstance(
    transformInfoInterface.getObjectFile(), classes, params);
    }
    catch(LicensingException e)
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
    {
    LogUtil.put(commonStrings.EXCEPTION, propertiesHashMap.get(TransformInfoData.NAME) + "->ViewFactory",
    commonStrings.GET_INSTANCE,e);
    }
    throw e;
    }
    catch(Exception e)
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
    {
    LogUtil.put(commonStrings.EXCEPTION, propertiesHashMap.get(TransformInfoData.NAME) + "->TransformInfoFactory",
    commonStrings.GET_INSTANCE,e);
    }
    throw e;
    }
    }
     */
}
