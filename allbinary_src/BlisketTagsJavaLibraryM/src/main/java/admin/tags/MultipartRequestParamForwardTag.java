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
package admin.tags;

import java.util.HashMap;

import jakarta.servlet.jsp.JspTagException;
import jakarta.servlet.jsp.tagext.Tag;
import jakarta.servlet.jsp.tagext.TagSupport;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.system.security.licensing.LicensingException;
import admin.taghelpers.MultipartRequestParamForwardHelper;
import java.util.Vector;
import tags.HelperTag;

public class MultipartRequestParamForwardTag extends TagSupport 
{
    private String page;

    private String command;

    private Vector paramVector;

    /**
     * @param page the page to set
     */
    public void setPage(String page)
    {
        this.page = page;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command)
    {
        this.command = command;
    }

    /**
     * @param paramVector the paramVector to set
     */
    public void setParamVector(Vector paramVector)
    {
        this.paramVector = paramVector;
    }

    public int doStartTag() throws JspTagException
    {
        try
        {  
            HelperTag parentTag = (HelperTag) this.getParent();

            ParentInventoryTagHelper.getInstance().isValid(this, parentTag);

            HashMap hashMap = new HashMap();
            hashMap.put(AbTagData.PARENT, parentTag);
            
            MultipartRequestParamForwardHelper multipartRequestParamForwardHelper =
            	new MultipartRequestParamForwardHelper(parentTag, this.page, this.command, this.paramVector, this.pageContext);

            multipartRequestParamForwardHelper.forward();

            return Tag.SKIP_BODY;

        } catch (LicensingException e)
        {
            AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext, e);
            return Tag.SKIP_BODY;
        } catch (Exception e)
        {
            AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
            return Tag.SKIP_BODY;
        }
    }
}