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
package org.allbinary.data.tree.dom;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;

import org.w3c.dom.Document;

//import org.allbinary.data.tree.dom.document.DomDocumentHelper;

public class XslHelper
{
   
   private XslHelper()
   {
   }
   
   public static OutputStream translate(StreamSource xsltStreamSource,
                            StreamSource xmlStreamSource,
                            StreamResult streamResult) throws Exception
   {
      try
      {
          //TWB - GAE needed xalan.jar and serializer.jar
         TransformerFactory tFactory = TransformerFactory.newInstance(
             //"org.apache.xalan.processor.TransformerFactoryImpl",
             //Thread.currentThread().getContextClassLoader()
             );
         
         Transformer transformer =
            tFactory.newTransformer(xsltStreamSource);
         
         transformer.transform(
            xmlStreamSource,
            streamResult);
                  
         return streamResult.getOutputStream();
      }
      catch(Exception e)
      {
         throw e;
      }
   }

   public static OutputStream translate(URIResolver resolver, 
                            StreamSource xsltStreamSource,
                            StreamSource xmlStreamSource,
                            StreamResult streamResult) throws Exception
   {
      try
      {
          //TWB - GAE needed xalan.jar and serializer.jar
         TransformerFactory tFactory = TransformerFactory.newInstance(
             //"org.apache.xalan.processor.TransformerFactoryImpl",
             //Thread.currentThread().getContextClassLoader()
             );
         
         tFactory.setURIResolver(resolver);
         
         Transformer transformer =
            tFactory.newTransformer(xsltStreamSource);

         //transformer.setURIResolver(resolver);
         
         transformer.transform(xmlStreamSource, streamResult);
                  
         return streamResult.getOutputStream();
      }
      catch(Exception e)
      {
         throw e;
      }
   }
   
   public static String translate(StreamSource xsltStreamSource,
                                  Document xmlDocument) throws Exception
   {
      try
      {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         return XslHelper.translate(xsltStreamSource,
            new StreamSource(new StringBufferInputStream(DomDocumentHelper.toString(xmlDocument))),
            new StreamResult(outputStream)).toString();         
      }
      catch(Exception e)
      {
         throw e;
      }
   }   
   

   public static String translate(StreamSource xsltStreamSource,
                                  StreamSource xmlStreamSource) 
                                  throws Exception
   {
      try
      {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         return XslHelper.translate(xsltStreamSource,
            xmlStreamSource,
            new StreamResult(outputStream)).toString();
      }
      catch(Exception e)
      {
         throw e;
      }
   }   

   public static String translate(
      URIResolver resolver, 
      StreamSource xsltStreamSource,
      StreamSource xmlStreamSource)
      throws Exception
   {
      try
      {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

         return XslHelper.translate(
             resolver, xsltStreamSource, xmlStreamSource,
             new StreamResult(outputStream)).toString();
      }
      catch(Exception e)
      {
         throw e;
      }
   }   
   
   public static void export(
      File outputFile, String xsltFilePath, Document xmlDocument) 
      throws Exception
   {
      try
      {
         outputFile.createNewFile();
                  
         XslHelper.translate(
            new StreamSource(xsltFilePath),
            new StreamSource(new StringBufferInputStream(DomDocumentHelper.toString(xmlDocument))),            
            new StreamResult(outputFile));     
      }
      catch(Exception e)
      {
         throw e;
      }
   }
}
