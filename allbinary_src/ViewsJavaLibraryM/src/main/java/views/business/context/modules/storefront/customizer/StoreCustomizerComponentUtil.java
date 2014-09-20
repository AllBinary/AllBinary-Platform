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

import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.generator.TransformGeneratorUtil;
import org.allbinary.logic.visual.transform.info.TransformInfo;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class StoreCustomizerComponentUtil
{
    private static final StoreCustomizerComponentUtil instance = new StoreCustomizerComponentUtil();

    private StoreCustomizerComponentUtil()
    {
    }

    //generateModifiedViews
    public static String generate(TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Generating Modified Views For: "
                + transformInfoInterface.getName(), instance, "generateModifiedViews()"));
        }

        Vector allCustomizedViews =
            CustomizerUtil.getTransformInfoObjectConfigGroupComponentNodes(
            transformInfoInterface);

        Iterator iter = allCustomizedViews.iterator();
        while (iter.hasNext())
        {
            TransformInfo nextTransformInfoInterface =
                (TransformInfo) iter.next();

            Vector allViewsToBeModified =
                CustomizerUtil.getTransformInfoObjectConfigComponentNodesToCustomize(
                transformInfoInterface, nextTransformInfoInterface);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance(
                    "View or component that was modified by a Customizer: "
                    + nextTransformInfoInterface.getName(),
                    instance, "generateModifiedViews()"));
            }

            generate(transformInfoInterface, allViewsToBeModified);
        }
        return StringUtil.getInstance().EMPTY_STRING;
    }

    private static final void generate(
        TransformInfoInterface transformInfoInterface, Vector allViewsToBeModified)
        throws Exception
    {
        Iterator iterator = allViewsToBeModified.iterator();
        while (iterator.hasNext())
        {
            TransformInfo nextTransformInfoInterface =
                (TransformInfo) iterator.next();

            TransformInfoInterface specifiedTransformInfoInterface =
                CustomizerUtil.getTransformInfoInterfaceToCustomize(
                transformInfoInterface,
                nextTransformInfoInterface);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Retrieved: "
                    + specifiedTransformInfoInterface.getName(), instance, "generateModifiedViews()"));
            }

            //generate views with the new data
            TransformGeneratorUtil.generate(
                nextTransformInfoInterface, specifiedTransformInfoInterface);
        }
    }
    /*
    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("Generate Result: " +
    result,this,"generateModifiedViews()"));
    }
     **/

    /* should be same as above
    private void generateModifiedViews() throws Exception
    {
    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("Generating Modified Views For: " +
    this.getTransformInfoInterface().getName(),this,"generateModifiedViews()"));
    }

    Vector allCustomizedViews =
    CustomizerUtil.getTransformInfoObjectConfigComponentNodes(
    this.getTransformInfoInterface());

    Iterator iter = allCustomizedViews.iterator();
    while(iter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponentNode =
    (TransformInfoObjectConfigComponentNode) iter.next();

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("View or component that was modified by a Customizer: " +
    transformInfoObjectConfigComponentNode.getName(),this,"generateModifiedViews()"));
    }

    Vector allViewsToBeModified =
    CustomizerUtil.getTransformInfoObjectConfigComponentNodesToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponentNode);

    Iterator componentIter = allViewsToBeModified.iterator();
    while(componentIter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponent
    = (TransformInfoObjectConfigComponentNode) componentIter.next();

    TransformInfoInterface specifiedTransformInfoInterface =
    CustomizerUtil.getTransformInfoInterfaceToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponent);

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("Retrieved: " +
    specifiedTransformInfoInterface.getName(),this,"generateModifiedViews()"));
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
    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("Generating Modified Views For: " +
    this.getTransformInfoInterface().getName(),this,"generateModifiedViews()"));
    }

    Vector allCustomizedViews =
    CustomizerUtil.getTransformInfoObjectConfigComponentNodes(
    this.getTransformInfoInterface());

    Iterator iter = allCustomizedViews.iterator();
    while(iter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponentNode =
    (TransformInfoObjectConfigComponentNode) iter.next();

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("View or component that was modified by a Customizer: " +
    transformInfoObjectConfigComponentNode.getName(),this,"generateModifiedViews()"));
    }

    Vector allViewsToBeModified =
    CustomizerUtil.getTransformInfoObjectConfigComponentNodesToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponentNode);

    Iterator componentIter = allViewsToBeModified.iterator();
    while(componentIter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponent
    = (TransformInfoObjectConfigComponentNode) componentIter.next();

    TransformInfoInterface specifiedTransformInfoInterface =
    CustomizerUtil.getTransformInfoInterfaceToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponent);

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("Retrieved: " +
    specifiedTransformInfoInterface.getName(),
    this, "generateModifiedViews()"));
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
    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("Generating Modified Views For: " +
    this.getTransformInfoInterface().getName(),this,"generateModifiedViews()"));
    }

    Vector allCustomizedViews =
    CustomizerUtil.getTransformInfoObjectConfigComponentNodes(
    this.getTransformInfoInterface());

    Iterator iter = allCustomizedViews.iterator();
    while(iter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponentNode =
    (TransformInfoObjectConfigComponentNode) iter.next();

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("View or component that was modified by a Customizer: " +
    transformInfoObjectConfigComponentNode.getName(),this,"generateModifiedViews()"));
    }

    Vector allViewsToBeModified =
    CustomizerUtil.getTransformInfoObjectConfigComponentNodesToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponentNode);

    Iterator componentIter = allViewsToBeModified.iterator();
    while(componentIter.hasNext())
    {
    TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponent
    = (TransformInfoObjectConfigComponentNode) componentIter.next();

    TransformInfoInterface specifiedTransformInfoInterface =
    CustomizerUtil.getTransformInfoInterfaceToCustomize(
    this.getTransformInfoInterface(),
    transformInfoObjectConfigComponent);

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
    {
    LogUtil.put(LogFactory.getInstance("Retrieved: " +
    specifiedTransformInfoInterface.getName(),this,"generateModifiedViews()"));
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
