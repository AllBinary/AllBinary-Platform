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

import abcs.logic.communication.log.LogUtil;

import allbinary.business.context.modules.storefront.StoreFrontData;

import abcs.logic.communication.http.request.AbResponseHandler;
import abcs.logic.communication.log.LogFactory;
import javax.servlet.jsp.JspTagException;

public class StoreValidationTransformTag extends ValidationTransformTag
{
    private String storeName;

    public StoreValidationTransformTag()
    {
        super();
    }

    public void setStoreName(String value)
    {
        this.storeName = value;
        this.getPropertiesHashMap().put(StoreFrontData.getInstance().NAME, this.storeName);
    }

    public int doStartTag() throws JspTagException
    {
        try
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
            {
                LogUtil.put(LogFactory.getInstance("Tag Start", this, "doStartTag"));
            }

            return super.doStartTag();
        } catch (Exception e)
        {
            AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
            return SKIP_BODY;
        }
    }
}
