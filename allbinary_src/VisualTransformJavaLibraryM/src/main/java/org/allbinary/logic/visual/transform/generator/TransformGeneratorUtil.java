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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.InputOutputTypeData;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.TransformFactory;
import org.allbinary.logic.visual.transform.TransformInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoDomNode;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.generator.StoreFileGenerator;

public class TransformGeneratorUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final TransformGeneratorUtil instance = new TransformGeneratorUtil();

    public static TransformGeneratorUtil getInstance() {
        return instance;
    }
    
    private TransformGeneratorUtil() {
    }

    public void generate(
        final AbeClientInformationInterface abeClientInformation,
        final TransformInfoInterface transformInfoInterface,
        final TransformInfoInterface ownerTransformInfoInterface) throws Exception {
        try {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW)) {
                logUtil.put("Generating View: " + transformInfoInterface.getName(),
                    this, "generate()");
            }

            TransformInterface componentInterface = TransformFactory.getInstance().getInstance(
                abeClientInformation, transformInfoInterface.getName(), ownerTransformInfoInterface);

            String result = componentInterface.view();

            TransformInfoHttpInterface httpTransformInfoInterface
                = (TransformInfoHttpInterface) componentInterface.getTransformInfoInterface();

            InputOutputTypeData inputOutputTypeData
                = InputOutputTypeData.getInstance();

            if (result.indexOf("<HTML>") >= 0) {
                httpTransformInfoInterface.getPropertiesHashMap().put(
                    inputOutputTypeData.NAME, inputOutputTypeData.DEFAULT);
            } else {
                httpTransformInfoInterface.getPropertiesHashMap().put(
                    inputOutputTypeData.NAME, inputOutputTypeData.DEFAULT_FRAGMENT);
            }

            new StoreFileGenerator(
                componentInterface.getTransformInfoInterface()).process(result);
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR)) {
                logUtil.put("Failed to generate a view",
                    this, "generate()", e);
            }
            throw e;
        }
    }

    public void generate(
        final AbeClientInformationInterface abeClientInformation,
        final TransformInfoDomNode transformInfoDomNode,
        final TransformInfoInterface ownerTransformInfoInterface) throws Exception {
        generate(abeClientInformation, transformInfoDomNode.getTransformInfoInterface(), ownerTransformInfoInterface);

    }
}
