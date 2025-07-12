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
package tags;

import javax.servlet.jsp.JspTagException;

import admin.taghelpers.TagHelperFactoryInterface;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.LicensingException;
import taghelpers.ViewHelperFactory;
import tags.transform.info.TransformInfoTag;

public class TransformTag extends TransformInfoTag
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //The View HelperObject must contain a
    //view object that implements the view interface

    public TransformTag()
    {
        super(new ViewHelperFactory());
        //this.helperObject = null;
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
        {
            logUtil.put(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
        }
    }

    public TransformTag(TagHelperFactoryInterface tagHelperFactoryInterface)
    {
        super(tagHelperFactoryInterface);
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
        {
            logUtil.put(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
        }
    }

    protected String view() throws Exception
    {
        try
        {
            String result = (String) this.getHelper().getClass().getMethod(
                "view", null).invoke(this.getHelper(), null);
            return result;
        } catch (Exception e)
        {

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "view()", e);
            }
            throw e;
        }
    }

    public int doStartTag() throws JspTagException
    {
        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
                logUtil.put(
                    "Creating with: \n" + this.getPropertiesHashMap().toString(), this, "doStartTag");
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append("ViewTag Start For: ");
                stringBuffer.append(this.getName());
                stringBuffer.append("\nViewFile: ");
                stringBuffer.append(this.getObjectFile());

                logUtil.put(stringBuffer.toString(), this, "doStartTag");
            }

            this.setHelper();

            pageContext.getOut().print(this.view());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
                logUtil.put("Tag End", this, "doStartTag");
            }

            return SKIP_BODY;
        } catch (LicensingException e)
        {
            AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext, e);
            return SKIP_BODY;
        } catch (Exception e)
        {
            AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
            return SKIP_BODY;
        }
    }
}
