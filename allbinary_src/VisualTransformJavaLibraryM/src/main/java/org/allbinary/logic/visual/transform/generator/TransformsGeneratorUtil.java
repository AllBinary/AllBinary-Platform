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
package org.allbinary.logic.visual.transform.generator;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoDomNode;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;

import java.util.Iterator;
import java.util.Vector;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class TransformsGeneratorUtil
{
	private static final TransformsGeneratorUtil instance = new TransformsGeneratorUtil();

    private TransformsGeneratorUtil()
    {
    }

    public static String generateComponentsFromObjectConfig(
        final AbeClientInformationInterface abeClientInformation, 
        final TransformInfoInterface transformInfoInterface, String group)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Started Group: " + group, 
                instance, "generateComponentsFromObjectConfig(2)"));
        }

        TransformInfoObjectConfigInterface transformInfoObjectConfigInterface =
            transformInfoInterface.getObjectConfigInterface();

        String result = TransformsGeneratorUtil.generateComponentsFromObjectConfig(
            abeClientInformation, transformInfoObjectConfigInterface, transformInfoInterface, group);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("End Group: " + group,
                instance, "generateComponentsFromObjectConfig(2)"));
        }
        return result;
    }

    //Transforms the Template TransformInfoInterface using each component/TransformInfoInterface from the TransformInfoObjectConfigInterface
    //I.E. the Template replaces it's body component tag with the template form each TransformInfoInterface from the TransformInfoObjectConfigInterface
    public static String generateComponentsFromObjectConfig(
        final AbeClientInformationInterface abeClientInformation,
        final TransformInfoObjectConfigInterface transformInfoObjectConfigInterface,
        final TransformInfoInterface transformInfoInterface, String group)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Started Group: " + group,
                instance, "generateComponentsFromObjectConfig()"));
        }

        Vector transformInfoObjectConfigComponentVector =
            transformInfoObjectConfigInterface.getTransformsGroup(group);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Processing ");
            stringBuffer.append(transformInfoObjectConfigComponentVector.size());
            stringBuffer.append(" Components Group: ");
            stringBuffer.append(group);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "generateComponentsFromObjectConfig()"));
        }

        Iterator iter = transformInfoObjectConfigComponentVector.iterator();

        if (transformInfoObjectConfigComponentVector.size() < 1)
        {
            throw new Exception("No Pages Generated For Template.");
        }

        while (iter.hasNext())
        {
            TransformInfoDomNode transformInfoObjectConfigComponent =
                (TransformInfoDomNode) iter.next();

            TransformGeneratorUtil.generate(abeClientInformation, transformInfoObjectConfigComponent, transformInfoInterface);
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Done Group: " + group, instance, "generateComponentsFromObjectConfig()"));
        }

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<!-- Generated all template views successfully for View: ");
        stringBuffer.append(transformInfoInterface.getName());
        stringBuffer.append("-->");
        
        return stringBuffer.toString();
    }
}
