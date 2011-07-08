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

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abcs.globals.AppUrlGlobals;
import abcs.globals.URLGLOBALS;
import abcs.logic.basic.io.CloudStreamUtil;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

/**
 *
 * @author user
 */
public class ImageServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
    	InputStream inputStream = null;
        try
        {
            //response.setContentType("text/plain");
            //response.getOutputStream().write("OK!".getBytes());

        	BlisketServletUtil.getInstance().init(request);

            String requestURI = request.getRequestURI();
            AbFile file = new AbFile(URLGLOBALS.getWebappPath() + requestURI);

            inputStream = CloudStreamUtil.getInstance().getFileLocal(file);
            byte[] byteArray = StreamUtil.getInstance().getByteArray(inputStream);

            response.setContentType("image/jpeg;charset=utf-8");

            response.getOutputStream().write(byteArray);

        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
            {
                LogUtil.put(LogFactory.getInstance("Exception", this, "processRequest()", e));
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
