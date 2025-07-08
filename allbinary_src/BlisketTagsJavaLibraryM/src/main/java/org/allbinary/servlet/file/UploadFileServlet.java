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

import org.allbinary.servlet.BlisketServletUtil;
import org.apache.commons.fileupload.FileItem;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.AbFileOutputStream;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import admin.taghelpers.AuthenticationHelper;
import admin.taghelpers.AuthenticationHelperFactory;
import admin.taghelpers.AuthenticationHelperUtil;
import org.allbinary.logic.communication.http.file.upload.FileUploadData;
import org.allbinary.logic.communication.http.file.upload.HttpFileUploadUtil;
import org.allbinary.logic.communication.http.request.MultipartRequestParams;
import org.allbinary.logic.communication.http.request.HttpRequestUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author user
 *
 */
public class UploadFileServlet extends HttpServlet
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    //private static final String UPLOAD = "upload";
    //private final int DEFAULT_BUFFER_SIZE = 16384;
    protected HashMap requestHashMap;
    private String fileName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
    	boolean isError = false;
        InputStream inputStream = null;
        try
        {
            //response.setContentType("text/plain");
            //response.getOutputStream().write("OK!".getBytes());

            BlisketServletUtil.getInstance().init(request);

            this.requestHashMap = new MultipartRequestParams(request).toHashMap();

            String filePath = (String) this.requestHashMap.get(
                FileUploadData.getInstance().UPLOAD_TO_FILE_PATH);

            if (filePath == null)
            {
            	isError = true;
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
                    //Save File
                    this.saveFile(filePath);

                    response.setContentType("text/plain");
                    response.getOutputStream().write("OK!".getBytes());
                } else
                {
                	isError = true;
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not Authorized");
                }

            } else
            {
            	isError = true;
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please Login");
            }

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
            {
                logUtil.put(this.commonStrings.EXCEPTION, this, "processRequest()", e);
            }
            isError = true;
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } finally
        {
        	if(!isError)
        	{
        		StreamUtil.getInstance().close(response.getOutputStream());
        	}

            if (!StreamUtil.getInstance().close(inputStream))
            {
            	//Hmmm
            	//response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }

    }

    private void saveFile(String filePath)
        throws Exception
    {
        AbFileOutputStream fileOutputStream = null;
        try
        {
            FileItem fileItem = (FileItem) this.requestHashMap.get(
                FileUploadData.getInstance().FILE_DATA);

            if (fileItem != null && fileItem.getSize() > 1)
            {
                this.fileName = HttpRequestUtil.getInstance().generateFileName(
                    fileItem.getName());

                if (filePath.endsWith("/") || filePath.endsWith("\\"))
                {
                    filePath = filePath + this.fileName;
                }

                AbFile file = new AbFile(URLGLOBALS.getWebappPath() + filePath);

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("Uploaded File: ");
                    stringBuffer.append(this.fileName);
                    stringBuffer.append(" New File: ");
                    stringBuffer.append(file.getPath());

                    logUtil.put(stringBuffer.toString(), this, "saveFile()");
                }
                HttpFileUploadUtil.log(fileItem);

                //Now that file checks out write it to file
                byte[] byteArray = fileItem.get();

                if(file.isFile())
                {
                    file.delete();
                    file.createNewFile();
                }
                
                fileOutputStream = new AbFileOutputStream(file);
                fileOutputStream.write(byteArray);
            }
        } finally
        {
            if (fileOutputStream != null)
            {
                fileOutputStream.close();
            }
            //StreamUtil.getInstance().close();
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
        return "Upload Cloud Files";
    }
}
