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
package taghelpers.transform.info;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.jsp.PageContext;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import admin.taghelpers.ModifyTableInterface;
import allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import allbinary.logic.visual.transform.info.TransformInfoHttpStoreNoManipulation;

public class TransformInfoRequestHelper implements ModifyTableInterface
{   
   private TransformInfoHttpInterface transformInfoInterface;
   
   public TransformInfoRequestHelper(HashMap propertiesHashMap, PageContext pageContext) throws Exception
   {
       /*
        * //TWB - Updated for GAE - for some reason it is looking for a view when CRUDing a Transform
      this.transformInfoInterface  =
         new TransformInfoHttp(
            (HttpServletRequest) pageContext.getRequest(),
            propertiesHashMap, pageContext);
        */

      this.transformInfoInterface  =
         new TransformInfoHttpStoreNoManipulation(
            propertiesHashMap, pageContext);
   }

   public String update()
   {
      try
      {
         String success = "Updated Successfully";         
         HashMap hashMapData = this.transformInfoInterface.toHashMap();
         TransformInfoEntityBuilder.getInstance().update(hashMapData);

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update storefronts table";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update()",e));
         }
         return error;
      }
   }
   
   public String insert()
   {
      try
      {
         String success = "Added TransformInfo Successfully";         
         Vector values = this.transformInfoInterface.toVector();

         TransformInfoEntityBuilder.getInstance().insert(values);         

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"insert()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add storefronts table";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"insert()",e));
         }
         return error;
      }
   }
   
   public String delete()
   {
      try
      {
         String success = "Delete Successfully";
         TransformInfoEntityBuilder.getInstance().delete(
            (String) this.transformInfoInterface.getKey());

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to delete storefronts table";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"delete()",e));
         }
         return error;
      }
   }
}
