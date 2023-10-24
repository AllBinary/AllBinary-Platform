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

import org.allbinary.logic.control.workflow.WorkFlowInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.business.DynamicObjectData;

import jakarta.servlet.jsp.PageContext;
import java.util.HashMap;

public class TagWorkFlowFactory
{

    private static final TagWorkFlowFactory instance = new TagWorkFlowFactory();

    private TagWorkFlowFactory()
    {
    }

    public static WorkFlowInterface getInstance(HashMap propertiesHashMap, PageContext pageContext) throws Exception, LicensingException
    {
        try
        {
            String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

            Object params[] = new Object[2];
            Class classes[] = new Class[2];

            //Add param types
            classes[0] = propertiesHashMap.getClass();
            classes[1] = AbeFactory.getClass("javax.servlet.jsp.PageContext");

            //Add arguments
            params[0] = (Object) propertiesHashMap;
            params[1] = (Object) pageContext;

            return (WorkFlowInterface) AbeFactory.getInstance(workFlowClassName, classes, params);
        } catch (LicensingException e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
            {
                String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Failed To Get Instance: ");
                stringBuffer.append(workFlowClassName);
                stringBuffer.append("->");
                stringBuffer.append(instance.getClass().getName());

                LogUtil.put(LogFactory.getInstance(
                    stringBuffer.toString(), instance, "getInstance(HashMap, PageContext)", e));
            }
            throw e;
        } catch (Exception e)
        {
            String error = "Failed To Get Instance";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
            {
                String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Failed To Get Instance: ");
                stringBuffer.append(workFlowClassName);
                stringBuffer.append("->");
                stringBuffer.append(instance.getClass().getName());

                LogUtil.put(LogFactory.getInstance(
                    stringBuffer.toString(), instance, "getInstance(HashMap, PageContext)", e));
            }
            throw e;
        }
    }
}
