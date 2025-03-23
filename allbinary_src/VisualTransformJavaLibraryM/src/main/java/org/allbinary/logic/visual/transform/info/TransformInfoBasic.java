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
package org.allbinary.logic.visual.transform.info;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.jsp.PageContext;

import org.w3c.dom.Document;

import org.allbinary.logic.io.path.AbPath;

import org.allbinary.business.context.AbContext;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.string.CommonStrings;

import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;

//Hack class should not exist
public class TransformInfoBasic extends AbContext implements TransformInfoHttpInterface
{
   private StoreFrontInterface storeFrontInterface;
   
   public TransformInfoBasic(StoreFrontInterface storeFrontInterface, 
      HashMap propertiesHashMap, PageContext pageContext)
   {
      super(propertiesHashMap, pageContext);
      
      this.storeFrontInterface = storeFrontInterface;
   }

   public String getStoreName()
   {
      return this.storeFrontInterface.getName();
   }

   public String log()
   {
      return CommonStrings.getInstance().NOT_IMPLEMENTED;
   }
   
   public void override(HashMap hashMap)
   {
   }
   
   public String getName()
   {
      return null;
   }

   public String getObjectFile()
   {
      return null;
   }

   public Object getObject()
   {
      return null;
   }

   public TransformInfoObjectConfigInterface getObjectConfigInterface()
   {
      return null;
   }

   public void setObjectConfigInterface(TransformInfoObjectConfigInterface transformInfoObjectConfigInterface)
   {
   }

   public AbPath getTemplateFilePath()
   {
      return null;
   }
   
   public String getTemplateFile()
   {
      return null;
   }

   public String getTemplate()
   {
      return null;
   }

   public AbPath getDataFilePath()
   {
      return null;
   }
   
   public String getDataFile()
   {
      return null;
   }
   
   public Document getDataDocument() throws Exception
   {
      throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
   }

   public void setName(String value)
   {
   }
   
   public void setStoreName(String value)
   {
   }
      
   public void setObjectFile(String value)
   {
   }
   
   public void setObject(Object object)
   {
   }
   
   public void setObjectConfigFile(String value)
   {
   }

   public void setTemplateFile(String value)
   {
   }

   public void setTemplate(String value)
   {
   }
   
   public void setDataFile(String value)
   {
   }

   public void setData(String value)
   {
   }

   public void setChild()
   {
   }
   
   public boolean isChild()
   {
      return true;
   }
   
   public String getImportUriPath()
   {
      return null;
   }
   
   public void setImportUriPath(String importUriPath)
   {
   }   

   public Object getKey() throws Exception
   {
      return null;
   }

   public Vector toVector() throws Exception
   {
      return null;
   }

   public HashMap toHashMap() throws Exception
   {
      return null;
   }
}