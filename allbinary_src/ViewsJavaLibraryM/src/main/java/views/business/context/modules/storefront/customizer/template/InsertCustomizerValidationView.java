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
package views.business.context.modules.storefront.customizer.template;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import views.business.context.modules.storefront.HttpStoreComponentView;
import views.business.context.modules.storefront.customizer.template.objectConfig.InsertTemplateCustomizerTransformInfoObjectConfig;
import views.business.context.modules.storefront.customizer.template.objectConfig.NoTemplateTransformInfoObjectConfig;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import allbinary.logic.communication.http.request.RequestParams;
import allbinary.logic.control.validate.ValidationComponentInterface;
import allbinary.logic.visual.transform.TransformFactory;
import allbinary.logic.visual.transform.TransformInterface;
import allbinary.logic.visual.transform.info.GeneratorTransformInfoData;
import allbinary.logic.visual.transform.info.TransformInfo;
import allbinary.logic.visual.transform.info.TransformInfoData;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.info.TransformInfosData;

//Sets Template Type
public class InsertCustomizerValidationView extends HttpStoreComponentView
    implements ValidationComponentInterface
{
    private String viewName;
    //private Integer current;
    //private Integer total;

    public InsertCustomizerValidationView(TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        super(transformInfoInterface);

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "Constructor()"));
        }

        HashMap requestHashMap =
            new RequestParams(this.getPageContext()).toHashMap();

        this.viewName = (String) requestHashMap.get(TransformInfoData.getInstance().NAME);

        //this.current = Integer.valueOf((String) requestHashMap.get("CURRENT"));
        //this.total = Integer.valueOf((String) requestHashMap.get("TOTAL"));
    }

    public Boolean isValid()
    {
        try
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Started Validation", this, "isValid()"));
            }

            Boolean isValid = Boolean.TRUE;

            if (StringValidationUtil.getInstance().isEmpty(this.viewName))
            {
                isValid = Boolean.FALSE;
            } else
            {
                //Use original objectconfig because root template is not known yet
                NoTemplateTransformInfoObjectConfig objectConfig =
                    new NoTemplateTransformInfoObjectConfig(
                    this.getTransformInfoInterface(),
                    this.getTransformInfoInterface().getObjectConfigInterface().toXmlDoc());

                //Iterate throught components specified in objectConfig
                Vector componentVector = objectConfig.getGroupTransforms();
                Iterator iter = componentVector.iterator();

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("Pointing ");
                    stringBuffer.append(componentVector.size());
                    stringBuffer.append(" Components");

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isValid()"));
                }

                while (iter.hasNext())
                {
                    TransformInfo transformInfo = (TransformInfo) iter.next();

                    String transformInfoName = transformInfo.getName();

                    if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
                    {
                        LogUtil.put(LogFactory.getInstance("Setting Template Component with ViewName: "
                            + transformInfoName, this, "isValid()"));
                    }

                    TransformInterface componentInterface =
                        TransformFactory.getInstance(
                        transformInfoName, this.getTransformInfoInterface());

                    InsertTemplateCustomizerTransformInfoObjectConfig templateViewObjectConfig =
                        new InsertTemplateCustomizerTransformInfoObjectConfig(
                        componentInterface.getTransformInfoInterface(),
                        componentInterface.getTransformInfoInterface().getObjectConfigInterface().toXmlDoc());

                    String storeName = this.getTransformInfoInterface().getStoreName();
                    int endIndex = this.viewName.indexOf(storeName) + storeName.length();

                    String storePrepend =
                        this.viewName.substring(0, endIndex);

                    String viewNamePostfix =
                        this.viewName.substring(endIndex, this.viewName.length());

                    StringBuffer newViewNameStringBuffer = new StringBuffer(storePrepend);

                    CommonSeps commonSeps = CommonSeps.getInstance();

                    TransformInfosData transformInfosData = TransformInfosData.getInstance();
                    
                    //update viewName
                    if (transformInfoName.indexOf(transformInfosData.SMALL_PREVIEW) >= 0)
                    {
                        newViewNameStringBuffer.append(commonSeps.SPACE);
                        newViewNameStringBuffer.append(transformInfosData.SMALL_PREVIEW);

                    } else if (transformInfoName.indexOf(transformInfosData.PREVIEW) >= 0)
                    {
                        newViewNameStringBuffer.append(commonSeps.SPACE);
                        newViewNameStringBuffer.append(transformInfosData.PREVIEW);
                    }

                    newViewNameStringBuffer.append(commonSeps.SPACE);
                    newViewNameStringBuffer.append(viewNamePostfix);

                    if (transformInfoName.indexOf(GeneratorTransformInfoData.NAME) >= 0)
                    {
                        newViewNameStringBuffer.append(commonSeps.SPACE);
                        newViewNameStringBuffer.append(GeneratorTransformInfoData.NAME);
                    }

                    final String newViewName = newViewNameStringBuffer.toString();

                    if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                        abcs.logic.communication.log.config.type.LogConfigType.VIEW))
                    {
                        StringBuffer stringBuffer = new StringBuffer();

                        stringBuffer.append("Template Component: ");
                        stringBuffer.append(transformInfoName);
                        stringBuffer.append("\n Now Pointing To View Name: ");
                        stringBuffer.append(newViewName);

                        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isValid()"));
                    }

                    templateViewObjectConfig.set(newViewName);

                    if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                        abcs.logic.communication.log.config.type.LogConfigType.VIEW))
                    {
                        StringBuffer stringBuffer = new StringBuffer();

                        stringBuffer.append("Template Component: ");
                        stringBuffer.append(transformInfoName);
                        stringBuffer.append("\n with ObjectConfig: ");
                        stringBuffer.append(templateViewObjectConfig.toString());

                        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isValid()"));
                    }

                    componentInterface.getTransformInfoInterface().setObjectConfigInterface(
                        templateViewObjectConfig);

                    HashMap updatedTransformInfoHashMap =
                        componentInterface.getTransformInfoInterface().toHashMap();
                    TransformInfoEntityBuilder.getInstance().update(updatedTransformInfoHashMap);
                }
            }
            return isValid;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed to validate", this, "isValid()", e));
            }
            return Boolean.FALSE;
        }
    }

    public String validationInfo()
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Error: Template Name Is Empty.");

            return stringBuffer.toString();
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed to generate validation error info", this, "validationInfo()", e));
            }
            return "Error Getting Validation Info";
        }
    }

    public Document toValidationInfoDoc()
    {
        return null;
    }

    public Node toValidationInfoNode(Document document)
    {
        return null;
    }

    public String view() throws Exception
    {
        return views.ValidationOnlyTempUtil.view(this);
    }
}
