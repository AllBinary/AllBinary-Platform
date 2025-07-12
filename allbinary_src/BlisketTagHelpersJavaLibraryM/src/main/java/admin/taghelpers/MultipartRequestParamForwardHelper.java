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

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.jsp.PageContext;

import org.allbinary.globals.GLOBALS2;
import org.allbinary.logic.communication.http.request.RequestMapInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import tags.HelperTag;

public class MultipartRequestParamForwardHelper
{
    protected final LogUtil logUtil = LogUtil.getInstance();


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

                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append(page);
                stringBuffer.append(params);

                this.pageContext.forward(stringBuffer.toString());
            }

        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "forward()", e);
            }
        }
    }

    private String getParams()
    {
        final String AMP = CommonSeps.getInstance().AMP;
        final String QUESTION = CommonSeps.getInstance().QUESTION;
        final String EQUALS = CommonSeps.getInstance().EQUALS;

        final HashMap hashMap =
            this.requestMapInterface.getRequestHashMap();

        StringMaker stringBuffer = new StringMaker();

        int size = this.paramVector.size();

        for (int index = 0; index < size; index++)
        {
            //TWB - Update using specified values from tag?
            String key = (String) this.paramVector.get(index);
            String value = (String) hashMap.get(key);

            if (index != 0)
            {
                stringBuffer.append(AMP);
            } else
            {
                stringBuffer.append(QUESTION);
            }

            stringBuffer.append(key);
            stringBuffer.append(EQUALS);
            stringBuffer.append(value);
        }

        return stringBuffer.toString();
    }

    //Forward if no command is needed or if command is correct
    private boolean shouldForward()
    {
        final HashMap hashMap = this.requestMapInterface.getRequestHashMap();

        String requestCommand = (String) hashMap.get(GLOBALS2.ADMINCOMMAND);

        if (StringValidationUtil.getInstance().isEmpty(command)
            || (!StringValidationUtil.getInstance().isEmpty(requestCommand) &&
            command.compareTo(requestCommand) == 0))
        {
            return true;
        }
        return false;
    }
}
