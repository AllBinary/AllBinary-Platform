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

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.http.request.session.WeblisketSession;

public class TransformHttpRequestDocumentFactory
{
   private TransformHttpRequestDocumentFactory()
   {
   }
   
   public static TransformDocumentInterface getInstance(
      PageContext pageContext, WeblisketSession weblisketSession) 
      throws Exception
   {
      return (TransformDocumentInterface) 
         new TransformHttpRequestDocument(pageContext, weblisketSession);
   }
}
