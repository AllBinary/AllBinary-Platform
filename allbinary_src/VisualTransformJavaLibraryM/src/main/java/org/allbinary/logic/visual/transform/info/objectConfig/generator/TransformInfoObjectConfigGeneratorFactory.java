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
package org.allbinary.logic.visual.transform.info.objectConfig.generator;

import org.allbinary.logic.io.InputOutputTypeData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.string.CommonStrings;

public class TransformInfoObjectConfigGeneratorFactory implements
    TransformInfoObjectConfigGeneratorFactoryInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TransformInfoObjectConfigGeneratorFactory instance =
        new TransformInfoObjectConfigGeneratorFactory();

    /**
     * @return the instance
     */
    public static TransformInfoObjectConfigGeneratorFactory getInstance()
    {
        return instance;
    }

    private TransformInfoObjectConfigGeneratorFactory()
    {
    }

    public TransformInfoObjectConfigGenerator getInstance(
        TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        String type = (String) transformInfoInterface.getObjectConfigInterface().getOutputTypeName();

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put("Generating Instance for type: " + type,this, commonStrings.GET_INSTANCE);
        }

        if (type == null || type.compareTo(InputOutputTypeData.getInstance().RESPONSE) == 0
            || transformInfoInterface.isChild())
        {
            //type == null for normal tag views

            //requested - data to be put in response by tag property

            //Is a child view of another view i.e. only parents
            //without parents can use the generator
            return new BasicGenerator();
        } else if (type.compareTo(InputOutputTypeData.getInstance().FILE) == 0)
        {
            //, propertiesHashMap, pageContext
            return new StoreFileGenerator(transformInfoInterface);
        } else
        {
            throw new Exception("No Such TransformInfoObjectConfigGenerator Type");
        }
    }
}
