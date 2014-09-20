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
package org.allbinary.logic.visual.transform.data;

import org.allbinary.logic.visual.transform.data.TransformDocumentInterface;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.control.search.SearchRequest;

import javax.servlet.jsp.PageContext;

public class TransformStoreDocumentFactory
{
   private TransformStoreDocumentFactory()
   {
   }
      
   public static TransformDocumentInterface getInstance(PageContext pageContext, 
      WeblisketSession weblisketSession) throws Exception
   {
      return (TransformDocumentInterface) new TransformStoreDocument(pageContext, weblisketSession);
   }

   public static TransformDocumentInterface getInstance(SearchRequest searchRequest) throws Exception
   {
      return (TransformDocumentInterface) new TransformStoreDocument(searchRequest);
   }   
}
