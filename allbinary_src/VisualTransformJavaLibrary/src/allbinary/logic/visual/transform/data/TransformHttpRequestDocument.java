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
package allbinary.logic.visual.transform.data;

 import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogFactory;
 import abcs.logic.communication.log.LogUtil;
 import allbinary.logic.communication.http.AcceptableResponseGenerator;
 import allbinary.logic.communication.http.request.session.WeblisketSession;
 import allbinary.logic.control.search.SearchRequest;
 import org.w3c.dom.Document;
 import org.w3c.dom.Node;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.jsp.PageContext;

public class TransformHttpRequestDocument 
   implements TransformDocumentInterface
{
   private Node baseNode;
   private Document document;
   
   //language_country = en_US
   private final static String language = "en";
   private final static String local = "US";

   public TransformHttpRequestDocument(
      PageContext pageContext, WeblisketSession weblisketSession) 
      throws Exception
   {
      try
      {
         String contentType =
         AcceptableResponseGenerator.get(
         (HttpServletRequest) pageContext.getRequest());
         
         this.document = DomDocumentHelper.create();
         
         Node contentTypeNode = document.createElement(contentType);         
         
         Node languageTypeNode = document.createElement(language);
         
         Node localTypeNode = document.createElement(local);
         this.baseNode = localTypeNode;
         
         languageTypeNode.appendChild(localTypeNode);
         
         contentTypeNode.appendChild(languageTypeNode);
         
         document.appendChild(contentTypeNode);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.log(), this, "Constructor()"));
         }
      }
      catch(Exception e)
      {
         String error = "Failed to create view document";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "Constructor()", e));
         }
         throw e;
      }
   }

   public TransformHttpRequestDocument(SearchRequest searchRequest) throws Exception
   {
      try
      {         
         String contentType = searchRequest.getContentType();
         
         this.document = DomDocumentHelper.create();
         
         Node contentTypeNode = document.createElement(contentType);         
         
         Node languageTypeNode = document.createElement(language);
         
         Node localTypeNode = document.createElement(local);
         this.baseNode = localTypeNode;
         
         languageTypeNode.appendChild(localTypeNode);
         
         contentTypeNode.appendChild(languageTypeNode);
         
         document.appendChild(contentTypeNode);
      }
      catch(Exception e)
      {
         String error = "Failed to create view document";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "Constructor()", e));
         }
         throw e;
      }
   }
   
   public Node getBaseNode()
   {
      return this.baseNode;
   }
   
   public Document getDoc()
   {
      return this.document;
   }

   public String log() throws Exception
   {
      StringBuffer stringBuffer = new StringBuffer();
      if(this.baseNode != null)
      {
         stringBuffer.append("BaseNode: ");
         stringBuffer.append(this.baseNode.getNodeName());
      }
      else
      {
         stringBuffer.append("Log-Error: BaseNode is Null");
      }
      stringBuffer.append("\nDocument: ");
      stringBuffer.append(DomDocumentHelper.toString(document));
      return stringBuffer.toString();
   }
}
