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
package allbinary.logic.communication.http.request;

import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.http.file.upload.AbFileUploadFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;

public class MultipartRequestParams extends RequestParams
{
   private boolean special = false;

   public MultipartRequestParams(HttpServletRequest request) //throws Exception
   {
      super();
      this.processMultipartRequest(request);
   }
   
   public MultipartRequestParams(PageContext pageContext) //throws Exception
   {
      super();
      this.processMultipartRequest(
      (HttpServletRequest) pageContext.getRequest());
   }
   
   protected void setSpecial()
   {
      this.special = true;
   }

   private void processMultipartRequest(HttpServletRequest request) //throws Exception
   {
      try
      {
         HashMap specialRequest = new HashMap();
         List multipartRequestList = AbFileUploadFactory.getFileItemStreamList(request);
         //List multipartRequestList = AbFileUploadFactory.getFileItemList(request);

         if(multipartRequestList!=null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
            {
               LogUtil.put(LogFactory.getInstance("FileItem List Size: " + multipartRequestList.size(), this, "processMultipartRequest()"));
            }

            Iterator iter = multipartRequestList.iterator();
            while(iter.hasNext())
            {
               FileItem fileItem = (FileItem) iter.next();

               //Save normal input fields and ignore file since file size may may be too long
               String name = fileItem.getName();
               if(StringValidationUtil.getInstance().isEmpty(name))
               {
                  specialRequest.put(fileItem.getFieldName(), fileItem.getString());
               }
               else
               {
                  specialRequest.put(fileItem.getFieldName(), fileItem);
               }
            }

            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
            {
                LogUtil.put(LogFactory.getInstance("Special Request Data: " + specialRequest.toString(), this, "processMultipartRequest()"));
            }

            this.setSpecial();
            this.setMap(specialRequest);
         }
      }
      catch(InvalidContentTypeException e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
         {
            LogUtil.put(LogFactory.getInstance("Using Normal RequestParams", this, "processMultipartRequest()"));
         }

         super.setMap(request.getParameterMap());
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.HTTPREQUESTERROR))
         {
            LogUtil.put(LogFactory.getInstance("Should Not Occur", this, "processMultipartRequest()"));
         }

         super.setMap(request.getParameterMap());
      }
   }
   
   public HashMap toHashMap() throws Exception
   {
      if(this.special)
      {
         return (HashMap) this.getMap();
      }
      else
      {
         return super.toHashMap();
      }
   }
}
