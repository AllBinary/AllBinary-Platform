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
package admin.taghelpers;

import abcs.logic.basic.string.StringValidationUtil;
import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import tags.HelperTag;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.globals.GLOBALS;
import allbinary.logic.communication.http.request.RequestMapInterface;
import java.util.Vector;

public class MultipartRequestParamForwardHelper
{

    private final PageContext pageContext;
    private final RequestMapInterface requestMapInterface;
    private final String page;
    private final String command;
    private final Vector paramVector;

    public MultipartRequestParamForwardHelper(
        HelperTag parentHelperTag,
        String page,
        String command,
        Vector paramVector,
        PageContext pageContext)
        throws Exception
    {
        this.pageContext = pageContext;
        this.page = page;
        this.command = command;
        this.paramVector = paramVector;

        this.requestMapInterface =
            InventoryItemViewParentTagHelper.getInstance().getRequestMapInterface(
            parentHelperTag);
    }

    public void forward() throws Exception
    {
        try
        {
            if (this.shouldForward())
            {
                String params = this.getParams();

                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append(page);
                stringBuffer.append(params);

                this.pageContext.forward(stringBuffer.toString());
            }

        } catch (Exception e)
        {
            String error = "Failed to forward";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "forward()", e));
            }
            //return error;
        }
    }

    private static final String PARAM_SEP = "&";
    private static final String PARAM_ASSIGN = "=";
    private static final String PARAM_START = "?";

    private String getParams()
    {
        final HashMap hashMap =
            this.requestMapInterface.getRequestHashMap();

        StringBuffer stringBuffer = new StringBuffer();

        int size = this.paramVector.size();

        for (int index = 0; index < size; index++)
        {
            //TWB - Update using specified values from tag?
            String key = (String) this.paramVector.get(index);
            String value = (String) hashMap.get(key);

            if (index != 0)
            {
                stringBuffer.append(PARAM_SEP);
            } else
            {
                stringBuffer.append(PARAM_START);
            }

            stringBuffer.append(key);
            stringBuffer.append(PARAM_ASSIGN);
            stringBuffer.append(value);
        }

        return stringBuffer.toString();
    }

    //Forward if no command is needed or if command is correct
    private boolean shouldForward()
    {
        final HashMap hashMap = this.requestMapInterface.getRequestHashMap();

        String requestCommand = (String) hashMap.get(GLOBALS.ADMINCOMMAND);

        if (StringValidationUtil.getInstance().isEmpty(command)
            || (!StringValidationUtil.getInstance().isEmpty(requestCommand) &&
            command.compareTo(requestCommand) == 0))
        {
            return true;
        }
        return false;
    }
}
