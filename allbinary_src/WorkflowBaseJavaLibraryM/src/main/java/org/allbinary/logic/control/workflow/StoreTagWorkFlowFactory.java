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
package org.allbinary.logic.control.workflow;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.business.DynamicObjectData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.string.CommonStrings;

public class StoreTagWorkFlowFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final StoreTagWorkFlowFactory instance = new StoreTagWorkFlowFactory();

    public static StoreTagWorkFlowFactory getInstance() {
        return instance;
    }

    private StoreTagWorkFlowFactory()
    {
    }

    public StoreWorkFlowInterface getInstance(final AbeClientInformationInterface abeClientInformation, final HashMap propertiesHashMap, final PageContext pageContext) throws Exception, LicensingException
    {
        try
        {
            String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY))
            {
                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append("Creating WorkFlow: ");
                stringBuffer.append(workFlowClassName);
                stringBuffer.append("->");
                stringBuffer.append(instance.getClass().getName());

                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(stringBuffer.toString(), this, commonStrings.GET_INSTANCE);
            }

            Object params[] = new Object[2];
            Class classes[] = new Class[2];

            //Add param types
            classes[0] = propertiesHashMap.getClass();
            classes[1] = AbeFactory.getInstance().getClass(abeClientInformation, "javax.servlet.jsp.PageContext");

            //Add arguments
            params[0] = (Object) propertiesHashMap;
            params[1] = (Object) pageContext;

            return (StoreWorkFlowInterface) AbeFactory.getInstance().getInstance(abeClientInformation, workFlowClassName, classes, params);
        } catch (LicensingException e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
            {
                String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append("Error Creating WorkFlow: ");
                stringBuffer.append(workFlowClassName);
                stringBuffer.append("->");
                stringBuffer.append(instance.getClass().getName());

                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(stringBuffer.toString(), this, commonStrings.GET_INSTANCE, e);
            }
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
            {
                String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append("Error Creating WorkFlow: ");
                stringBuffer.append(workFlowClassName);
                stringBuffer.append("->");
                stringBuffer.append(instance.getClass().getName());

                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(stringBuffer.toString(), this, commonStrings.GET_INSTANCE, e);
            }
            throw e;
        }
    }
}
