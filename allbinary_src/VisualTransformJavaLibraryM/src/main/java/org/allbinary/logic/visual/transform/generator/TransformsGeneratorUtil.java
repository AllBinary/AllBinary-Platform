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


import java.util.Vector;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoDomNode;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class TransformsGeneratorUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TransformsGeneratorUtil instance = new TransformsGeneratorUtil();

    public static TransformsGeneratorUtil getInstance() {
        return instance;
    }
        
    private TransformsGeneratorUtil()
    {
    }

    public String generateComponentsFromObjectConfig(
        final AbeClientInformationInterface abeClientInformation, 
        final TransformInfoInterface transformInfoInterface, String group)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("Started Group: " + group, this, "generateComponentsFromObjectConfig(2)");
        }

        TransformInfoObjectConfigInterface transformInfoObjectConfigInterface =
            transformInfoInterface.getObjectConfigInterface();

        String result = this.generateComponentsFromObjectConfig(
            abeClientInformation, transformInfoObjectConfigInterface, transformInfoInterface, group);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("End Group: " + group,
                this, "generateComponentsFromObjectConfig(2)");
        }
        return result;
    }

    //Transforms the Template TransformInfoInterface using each component/TransformInfoInterface from the TransformInfoObjectConfigInterface
    //I.E. the Template replaces it's body component tag with the template form each TransformInfoInterface from the TransformInfoObjectConfigInterface
    public String generateComponentsFromObjectConfig(
        final AbeClientInformationInterface abeClientInformation,
        final TransformInfoObjectConfigInterface transformInfoObjectConfigInterface,
        final TransformInfoInterface transformInfoInterface, String group)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("Started Group: " + group,this, "generateComponentsFromObjectConfig()");
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

            logUtil.put(stringBuffer.toString(), this, "generateComponentsFromObjectConfig()");
        }

        if (transformInfoObjectConfigComponentVector.size() < 1)
        {
            throw new Exception("No Pages Generated For Template.");
        }

        final int size = transformInfoObjectConfigComponentVector.size();
        for (int index = 0; index < size; index++)
        {
            TransformInfoDomNode transformInfoObjectConfigComponent = (TransformInfoDomNode) transformInfoObjectConfigComponentVector.get(index);

            TransformGeneratorUtil.getInstance().generate(abeClientInformation, transformInfoObjectConfigComponent, transformInfoInterface);
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("Done Group: " + group, this, "generateComponentsFromObjectConfig()");
        }

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<!-- Generated all template views successfully for View: ");
        stringBuffer.append(transformInfoInterface.getName());
        stringBuffer.append("-->");
        
        return stringBuffer.toString();
    }
}
