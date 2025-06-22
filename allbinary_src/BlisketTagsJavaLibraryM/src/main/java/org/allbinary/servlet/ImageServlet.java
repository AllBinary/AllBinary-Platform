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
package org.allbinary.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.CloudStreamUtil;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author user
 */
public class ImageServlet extends HttpServlet
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
        throws ServletException, IOException
    {
    	InputStream inputStream = null;
        try
        {
            //response.setContentType("text/plain");
            //response.getOutputStream().write("OK!".getBytes());

        	BlisketServletUtil.getInstance().init(request);

            final String requestURI = request.getRequestURI();
            final AbFile file = new AbFile(URLGLOBALS.getWebappPath() + requestURI);

            inputStream = CloudStreamUtil.getInstance().getFileLocal(file);
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(8000);
            final byte[] byteArray = StreamUtil.getInstance().getByteArray(inputStream, outputStream, new byte[16384]);

            response.setContentType("image/jpeg;charset=utf-8");

            response.getOutputStream().write(byteArray);

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "processRequest()", e));
            }
        }
        finally
        {
        	StreamUtil.getInstance().close(response.getOutputStream());
        	
            if (!StreamUtil.getInstance().close(inputStream))
            {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest(request, response);
    }

    public String getServletInfo()
    {
        return "Serves Up Image";
    }
}
