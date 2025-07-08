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
package tags.transform.info;

import javax.servlet.jsp.JspTagException;

import org.allbinary.logic.io.OutputTypeData;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;


import admin.taghelpers.TagHelperFactoryInterface;

import org.allbinary.business.context.modules.storefront.StoreFrontData;

import org.allbinary.logic.visual.transform.info.TransformInfoData;

import org.allbinary.logic.communication.log.LogUtil;

import tags.ModifyTag;

import taghelpers.transform.info.TransformInfoRequestHelperFactory;

public class TransformInfoTag extends ModifyTag
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String name;
   private String storeName;

   //The viewFile must contain a String that names a
   //view object that implements the view interface
   private String objectFile;
   private String object;

   private String objectConfigFile;
   //is still a file path, but the file is loaded and 
   //inserted into database instead of file path   
   private String objectConfig;

   private String templateFile;
   //is still a file path, but the file is loaded and 
   //inserted into database instead of file path
   private String template;

   private String dataFile;
   //is still a file path, but the file is loaded and 
   //inserted into database instead of file path
   private String data;

   //The viewObects vector may contain actual objects or a Vector of Strings
   //that name view objects or are the objects that implement the view interface
   //private Vector viewObjects;
      
   //defines the type of output requested if one exists in the view object config
   //like jsp, asp, pdf, and so on
   private String type;

   public TransformInfoTag()
   {
      super(new TransformInfoRequestHelperFactory());
   }

   public TransformInfoTag(TagHelperFactoryInterface tagHelperFactoryInterface)
   {
      super(tagHelperFactoryInterface);
   }

   public void setName(String value)
   {
      this.name = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().NAME, this.name);
   }

   public void setStoreName(String value)
   {
      this.storeName = value;
      this.getPropertiesHashMap().put(StoreFrontData.getInstance().NAME,this.storeName);
   }

   public void setObjectFile(String value)
   {
      this.objectFile = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().OBJECTFILENAME, this.objectFile);
   }

   public void setObject(String value)
   {
      this.object = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().OBJECT, this.object);
   }

   public void setTemplateFile(String value)
   {
      this.templateFile = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().TEMPLATEFILENAME, this.templateFile);
   }

   public void setTemplate(String value)
   {
      this.template = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().TEMPLATE, this.template);
   }

   public void setDataFile(String value)
   {
      this.dataFile = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().DATAFILENAME, this.dataFile);
   }

   public void setData(String value)
   {
      this.data = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().DATA, this.data);
   }

   public void setObjectConfigFile(String value)
   {
      this.objectConfigFile = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().OBJECTCONFIGFILENAME, this.objectConfigFile);
   }

   public void setObjectConfig(String value)
   {
      this.objectConfig = value;
      this.getPropertiesHashMap().put(TransformInfoData.getInstance().OBJECTCONFIG, this.objectConfig);
   }

   public void setType(String value)
   {
      this.type = value;
      this.getPropertiesHashMap().put(OutputTypeData.getInstance().NAME, this.type);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         logUtil.put("TransformInfoTag type set: " + this.type,this,"setType");
      }
   }   

   public String getName()
   {
      return this.name;
   }

   public String getObjectFile()
   {
      return this.objectFile;
   }

   public int doStartTag() throws JspTagException
   {
      try
      {
         return super.doStartTag();
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
