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

import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;
import org.w3c.dom.Document;

public interface TransformInfoInterface extends TableMappingInterface
{
   public String log();

   public void override(HashMap hashMap) throws Exception;
   
   public String getName();

   public void setStoreName(String value);
   public String getStoreName();
   
   public String getObjectFile();
   public Object getObject();
   
   public AbPath getTemplateFilePath() throws Exception;
   public String getTemplateFile();
   public String getTemplate();
   
   public AbPath getDataFilePath() throws Exception;
   public String getDataFile();
   public Document getDataDocument() throws Exception;
  
   public void setName(String value);
   
   public void setObjectFile(String value);
   public void setObject(Object object);
   
   public void setObjectConfigInterface(TransformInfoObjectConfigInterface transformInfoObjectConfigInterface) throws Exception;
   public TransformInfoObjectConfigInterface getObjectConfigInterface() throws Exception;

   public void setTemplateFile(String value);
   public void setTemplate(String value);
   
   public void setDataFile(String value);
   public void setData(String value);

   //When the componentFactory generates a child component this method is 
   //called so it will not use a special viewinfoobjectgenerator
   public void setChild();
   public boolean isChild();
}
