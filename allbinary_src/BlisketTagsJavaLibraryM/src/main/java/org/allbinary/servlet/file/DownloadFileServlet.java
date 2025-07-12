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
package org.allbinary.servlet.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.taghelpers.AuthenticationHelper;
import admin.taghelpers.AuthenticationHelperFactory;
import admin.taghelpers.AuthenticationHelperUtil;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.CloudStreamUtil;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.servlet.BlisketServletUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author user
 *
 */
public class DownloadFileServlet extends HttpServlet
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final String DOWNLOAD = "download";
    private final int DEFAULT_BUFFER_SIZE = 16384;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        InputStream inputStream = null;
        try
        {
            //response.setContentType("text/plain");
            //response.getOutputStream().write("OK!".getBytes());

        	BlisketServletUtil.getInstance().init(request);

            String requestPath = request.getRequestURI();

            if (requestPath == null)
            {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            int beginIndex = requestPath.indexOf(DOWNLOAD);

            String filePath = StringUtil.getInstance().EMPTY_STRING;

            if (beginIndex >= 0)
            {
                filePath = requestPath.substring(beginIndex + DOWNLOAD.length());
            } else
            {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

            AbFile file = new AbFile(URLGLOBALS.getWebappPath() + filePath);

            if (!file.exists())
            {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            //HashMap should have storename added
            HashMap hashMap = new HashMap();

            //Vector roleVector = new Vector();

            AuthenticationHelper authenticationHelper =
                (AuthenticationHelper) new AuthenticationHelperFactory().getInstance(
                hashMap, request);

            //if (authenticationHelper.isAuthenticationSessionValid(roleVector) == Boolean.TRUE)
            if (authenticationHelper.isAuthenticated())
            {
                if (AuthenticationHelperUtil.getInstance().isAuthorized(authenticationHelper, filePath))
                {
                    inputStream = CloudStreamUtil.getInstance().getFile(file);

                    String contentType = getServletContext().getMimeType(file.getName());

                    //application/x-www-form-urlencoded
                    if (contentType == null)
                    {
                        contentType = "application/octet-stream";
                    }

                    response.reset();
                    response.setBufferSize(DEFAULT_BUFFER_SIZE);
                    response.setContentType(contentType);
                    response.setHeader("Content-Length", String.valueOf(file.length()));

                    StringMaker stringBuffer = new StringMaker();

                    stringBuffer.append("attachment; filename=\"");
                    stringBuffer.append(file.getName());
                    stringBuffer.append("\"");

                    response.setHeader("Content-Disposition", stringBuffer.toString());

                    StreamUtil.getInstance().get(
                        inputStream, response.getOutputStream(), new byte[16348]);

                    //byte[] byteArray = StreamUtil.getInstance().getByteArray(inputStream);
                    //response.getOutputStream().write(byteArray);
                }
                else
                {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not Authorized");
                }

            } else
            {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please Login");
            }

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
                logUtil.put(this.commonStrings.EXCEPTION, this, "processRequest()", e);
            }
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } finally
        {
        	StreamUtil.getInstance().close(response.getOutputStream());
        	
            if (!StreamUtil.getInstance().close(inputStream))
            {
            	//Hmm
                //response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
        return "Serves Up Cloud Files";
    }
}
