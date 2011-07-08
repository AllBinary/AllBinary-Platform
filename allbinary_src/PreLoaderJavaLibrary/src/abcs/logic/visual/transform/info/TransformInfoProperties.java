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
package abcs.logic.visual.transform.info;

public class TransformInfoProperties 
{
   private String name;
   private String label;
   private String description;

   private String objectFileName;
   private String objectConfigFileName;
   private String templateFileName;
      
   public TransformInfoProperties(String name, String label, String description,
      String objectFileName, String objectConfigFileName, 
      String templateFileName) throws Exception
   {
      this.name = name;
      this.label = label;
      this.description = description;
      this.objectFileName = objectFileName;
      this.templateFileName = templateFileName;
      this.objectConfigFileName = objectConfigFileName;
   }
      
   public void setName(String value)
   {
      this.name = value;
   }

   public void setDescription(String value)
   {
      this.description = value;
   }

   public void setViewFile(String value)
   {
      this.objectFileName = value;
   }
   
   public void setTemplateFile(String value)
   {
      this.templateFileName = value;
   }
   
   public void setObjectConfigFile(String value)
   {
      this.objectConfigFileName = value;
   }

   public String getName()
   {
      return this.name;
   }

   public String getLabel()
   {
      return this.label;
   }
   
   public String getDescription()
   {
      return this.description;
   }

   public String getViewFile()
   {
      return this.objectFileName;
   }

   public String getTemplateFile()
   {
      return this.templateFileName;
   }

   public String getObjectConfigFile()
   {
      return this.objectConfigFileName;
   }
}