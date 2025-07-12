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
package org.allbinary.logic.communication.http.request;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.http.file.upload.AbFileUploadFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;

public class MultipartRequestParams extends RequestParams
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
         List multipartRequestList = AbFileUploadFactory.getInstance().getFileItemStreamList(request);
         //List multipartRequestList = AbFileUploadFactory.getFileItemList(request);

         if(multipartRequestList!=null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put("FileItem List Size: " + multipartRequestList.size(), this, "processMultipartRequest()");
            }

             final Object[] fileItemArray = multipartRequestList.toArray();
             final int size = fileItemArray.length;
             for (int index = 0; index < size; index++)
             {
               FileItem fileItem = (FileItem) fileItemArray[index];

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

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
                logUtil.put("Special Request Data: " + specialRequest.toString(), this, "processMultipartRequest()");
            }

            this.setSpecial();
            this.setMap(specialRequest);
         }
      }
      catch(InvalidContentTypeException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
         {
            logUtil.put("Using Normal RequestParams", this, "processMultipartRequest()");
         }

         super.setMap(request.getParameterMap());
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUESTERROR))
         {
            logUtil.put("Should Not Occur", this, "processMultipartRequest()");
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
