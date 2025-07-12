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

import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.control.search.SearchRequest;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class TransformInfoHttpSearch extends TransformInfoHttp
{
   public TransformInfoHttpSearch(SearchRequest searchRequest) throws Exception
   {
      super(searchRequest.getPropertiesHashMap(), searchRequest.getPageContext());

      if(searchRequest.getStoreFront().getName()!=null)
      {
         this.setStoreName(searchRequest.getStoreFront().getName());
      }
      else
      {
          this.setStoreName(StringUtil.getInstance().EMPTY_STRING);
      }

      String dataFileName = searchRequest.getXslFile();

      this.setTemplateFile(dataFileName);
   }

   private String getPath() throws Exception
   {
       StringMaker stringBuffer = new StringMaker();

       stringBuffer.append(URLGLOBALS.getMainPath());
       stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
       stringBuffer.append(this.getStoreName());
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       return stringBuffer.toString();
   }

   public AbPath getTemplateFilePath() throws Exception
   {
      return new AbPath(this.getPath(), this.getTemplateFile());
   }
   
   public AbPath getObjectConfigFilePath() throws Exception
   {
      return new AbPath(this.getPath(), this.getObjectConfigFile());
   }
   
   public AbPath getDataFilePath() throws Exception
   {
      return new AbPath(this.getPath(), this.getDataFile());
   }
}