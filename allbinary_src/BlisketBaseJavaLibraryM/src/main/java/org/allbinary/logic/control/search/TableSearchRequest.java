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
package org.allbinary.logic.control.search;

import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class TableSearchRequest
{   
   private String xslFile;
   private String pageName;
   private String contentType;
   private String keywords;
   private SearchParams searchParams;
      
   public TableSearchRequest(String keywords, SearchParams searchParams, String xslFile, String contentType)
   {
      this.keywords = keywords;
      this.searchParams = searchParams;      
      this.xslFile = xslFile;
      this.pageName = pageName;
      this.contentType = contentType;
   }
   
   public void setKeywords(String value)
   {
      this.keywords = value;
   }
   
   public void setParams(SearchParams searchParams)
   {
      this.searchParams = searchParams;
   }
   
   public void setXslFile(String value)
   {
      this.xslFile = value;
   }

   public void setFileBaseName(String value)
   {
      this.pageName = value;
   }
   
   public void setContentType(String value)
   {
      this.contentType = value;
   }
   
   public String getKeywords()
   {
      return this.keywords;
   }
   
   public SearchParams getParams()
   {
      return this.searchParams;
   }
   
   public String getXslFile() throws Exception
   {      
      String path = URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH + this.xslFile;
      return path;
   }

   public String getFileBaseName()
   {
      return this.pageName;
   }   

   public String getContentType()
   {
      return this.contentType;
   }
   
   public Node getFileBaseNameNode(Document document) throws Exception
   {
      return ModDomHelper.createNameValueNodes(document, SearchData.FILENAME, this.getFileBaseName());
   }
   
}
