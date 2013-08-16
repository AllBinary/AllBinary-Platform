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
package allbinary.logic.control.workflow;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.loader.AbeFactory;
import abcs.logic.system.security.licensing.LicensingException;
import allbinary.business.DynamicObjectData;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;

public class StoreTagWorkFlowFactory
{

    private static final StoreTagWorkFlowFactory instance = new StoreTagWorkFlowFactory();

    private StoreTagWorkFlowFactory()
    {
    }

    public static StoreWorkFlowInterface getInstance(HashMap propertiesHashMap, PageContext pageContext) throws Exception, LicensingException
    {
        try
        {
            String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Creating WorkFlow: ");
                stringBuffer.append(workFlowClassName);
                stringBuffer.append("->");
                stringBuffer.append(instance.getClass().getName());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "getInstance()"));
            }

            Object params[] = new Object[2];
            Class classes[] = new Class[2];

            //Add param types
            classes[0] = propertiesHashMap.getClass();
            classes[1] = AbeFactory.getClass("javax.servlet.jsp.PageContext");

            //Add arguments
            params[0] = (Object) propertiesHashMap;
            params[1] = (Object) pageContext;

            return (StoreWorkFlowInterface) AbeFactory.getInstance(workFlowClassName, classes, params);
        } catch (LicensingException e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
            {
                String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Error Creating WorkFlow: ");
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
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
            {
                String workFlowClassName = (String) propertiesHashMap.get(DynamicObjectData.NAME);

                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Error Creating WorkFlow: ");
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
