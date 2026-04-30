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
   String log();

   void override(HashMap hashMap) throws Exception;
   
   String getName();

   void setStoreName(String value);
   String getStoreName();
   
   String getObjectFile();
   Object getObject();
   
   AbPath getTemplateFilePath() throws Exception;
   String getTemplateFile();
   String getTemplate();
   
   AbPath getDataFilePath() throws Exception;
   String getDataFile();
   Document getDataDocument() throws Exception;
  
   void setName(String value);
   
   void setObjectFile(String value);
   void setObject(Object object);
   
   void setObjectConfigInterface(TransformInfoObjectConfigInterface transformInfoObjectConfigInterface) throws Exception;
   TransformInfoObjectConfigInterface getObjectConfigInterface() throws Exception;

   void setTemplateFile(String value);
   void setTemplate(String value);
   
   void setDataFile(String value);
   void setData(String value);

   //When the componentFactory generates a child component this method is 
   //called so it will not use a special viewinfoobjectgenerator
   void setChild();
   boolean isChild();
}
