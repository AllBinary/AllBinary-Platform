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
package views.business.context.modules.storefront.customizer;


import java.util.Vector;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.generator.TransformGeneratorUtil;
import org.allbinary.logic.visual.transform.info.TransformInfo;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class StoreCustomizerComponentUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final StoreCustomizerComponentUtil instance = new StoreCustomizerComponentUtil();
    
    public static StoreCustomizerComponentUtil getInstance() {
        return instance;
    }

    private StoreCustomizerComponentUtil()
    {
    }

    //generateModifiedViews
    public String generate(final AbeClientInformationInterface abeClientInformation, TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("Generating Modified Views For: " + transformInfoInterface.getName(), this, "generateModifiedViews()");
        }

        final CustomizerUtil customizerUtil = CustomizerUtil.getInstance();

        Vector allCustomizedViews =
            customizerUtil.getTransformInfoObjectConfigGroupComponentNodes(
            transformInfoInterface);

        final int size = allCustomizedViews.size();
        for(int index = 0; index < size; index++)
        {
            TransformInfo nextTransformInfoInterface =
                (TransformInfo) allCustomizedViews.get(index);

            Vector allViewsToBeModified =
                customizerUtil.getTransformInfoObjectConfigComponentNodesToCustomize(
                transformInfoInterface, nextTransformInfoInterface);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                logUtil.put(
                    "View or component that was modified by a Customizer: "
                    + nextTransformInfoInterface.getName(),
                    this, "generateModifiedViews()");
            }

            generate(abeClientInformation, transformInfoInterface, allViewsToBeModified);
        }
        return StringUtil.getInstance().EMPTY_STRING;
    }

    private final void generate(
        final AbeClientInformationInterface abeClientInformation,
        TransformInfoInterface transformInfoInterface, Vector allViewsToBeModifiedVector)
        throws Exception
    {
        final CustomizerUtil customizerUtil = CustomizerUtil.getInstance();
        final int size = allViewsToBeModifiedVector.size();
        for(int index = 0; index < size; index++)
        {
            TransformInfo nextTransformInfoInterface =
                (TransformInfo) allViewsToBeModifiedVector.get(index);

            TransformInfoInterface specifiedTransformInfoInterface =
                customizerUtil.getTransformInfoInterfaceToCustomize(
                transformInfoInterface,
                nextTransformInfoInterface);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                logUtil.put("Retrieved: " + specifiedTransformInfoInterface.getName(), this, "generateModifiedViews()");
            }

            //generate views with the new data
            TransformGeneratorUtil.getInstance().generate(abeClientInformation, nextTransformInfoInterface, specifiedTransformInfoInterface);
        }
    }
    /*
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("Generate Result: " +
    result,this,"generateModifiedViews()");
    }
     **/

    /* should be same as above
    private void generateModifiedViews() throws Exception
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("Generating Modified Views For: " +
    this.getTransformInfoInterface().getName(),this,"generateModifiedViews()");
    }

    Vector allCustomizedViews =
    CustomizerUtil.getInstance().getTransformInfoObjectConfigComponentNodes(
    this.getTransformInfoInterface());

    iter = allCustomizedViews;
    while(iter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponentNode =
    (TransformInfoObjectConfigComponentNode) iter.next();

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("View or component that was modified by a Customizer: " +
    transformInfoObjectConfigComponentNode.getName(),this,"generateModifiedViews()");
    }

    Vector allViewsToBeModified =
    CustomizerUtil.getInstance().getTransformInfoObjectConfigComponentNodesToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponentNode);

    componentIter = allViewsToBeModified;
    while(componentIter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponent
    = (TransformInfoObjectConfigComponentNode) componentIter.next();

    TransformInfoInterface specifiedTransformInfoInterface =
    CustomizerUtil.getInstance().getTransformInfoInterfaceToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponent);

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("Retrieved: " +
    specifiedTransformInfoInterface.getName(),this,"generateModifiedViews()");
    }

    //generate views with the new data
    //String result =
    ComponentGeneratorUtil.generate(
    transformInfoObjectConfigComponent, specifiedTransformInfoInterface);
    }
    }
    }
     *
     *
    private void generateModifiedViews() throws Exception
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("Generating Modified Views For: " +
    this.getTransformInfoInterface().getName(),this,"generateModifiedViews()");
    }

    Vector allCustomizedViews =
    CustomizerUtil.getInstance().getTransformInfoObjectConfigComponentNodes(
    this.getTransformInfoInterface());

    iter = allCustomizedViews;
    while(iter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponentNode =
    (TransformInfoObjectConfigComponentNode) iter.next();

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("View or component that was modified by a Customizer: " +
    transformInfoObjectConfigComponentNode.getName(),this,"generateModifiedViews()");
    }

    Vector allViewsToBeModified =
    CustomizerUtil.getInstance().getTransformInfoObjectConfigComponentNodesToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponentNode);

    componentIter = allViewsToBeModified;
    while(componentIter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponent
    = (TransformInfoObjectConfigComponentNode) componentIter.next();

    TransformInfoInterface specifiedTransformInfoInterface =
    CustomizerUtil.getInstance().getTransformInfoInterfaceToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponent);

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("Retrieved: " +
    specifiedTransformInfoInterface.getName(),
    this, "generateModifiedViews()");
    }

    //generate views with the new data
    //String result =
    ComponentGeneratorUtil.generate(
    transformInfoObjectConfigComponent, specifiedTransformInfoInterface);

    }

    }
    }

    private void generateModifiedViews() throws Exception
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("Generating Modified Views For: " +
    this.getTransformInfoInterface().getName(),this,"generateModifiedViews()");
    }

    Vector allCustomizedViews =
    CustomizerUtil.getInstance().getTransformInfoObjectConfigComponentNodes(
    this.getTransformInfoInterface());

    iter = allCustomizedViews;
    while(iter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponentNode =
    (TransformInfoObjectConfigComponentNode) iter.next();

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("View or component that was modified by a Customizer: " +
    transformInfoObjectConfigComponentNode.getName(),this,"generateModifiedViews()");
    }

    Vector allViewsToBeModified =
    CustomizerUtil.getInstance().getTransformInfoObjectConfigComponentNodesToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponentNode);

    componentIter = allViewsToBeModified;
    while(componentIter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponent
    = (TransformInfoObjectConfigComponentNode) componentIter.next();

    TransformInfoInterface specifiedTransformInfoInterface =
    CustomizerUtil.getInstance().getTransformInfoInterfaceToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponent);

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
    {
    logUtil.put("Retrieved: " +
    specifiedTransformInfoInterface.getName(),this,"generateModifiedViews()");
    }

    //generate views with the new data
    //String result =
    ComponentGeneratorUtil.generate(
    transformInfoObjectConfigComponent, specifiedTransformInfoInterface);

    }

    }
    }

     */
}
