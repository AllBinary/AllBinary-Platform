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
package org.allbinary.data.tree.dom.document;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class DomDocumentHelper
{
   //System property specifying the TransformerFactory returned 
   //"javax.xml.transform.TransformerFactory"
   //"javax.xml.parsers.DocumentBuilderFactory"
   private DomDocumentHelper()
   {
   }
   
   public static Document create()
   {
      try
      {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

         DocumentBuilder builder = factory.newDocumentBuilder();
         
         Document document = builder.newDocument();
         
         return document;
      }
      catch (Exception e)
      {
         return null;
      }
   }

   public static Document create(InputStream inputStream) throws Exception
   {
      try
      {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

         DocumentBuilder builder = factory.newDocumentBuilder();

         //Document document = builder.parse(xmlFile.getFile());

         Document document = builder.parse(inputStream);

         return document;
      }
      catch (Exception e)
      {
         throw e;
      }
   }

   public static Document create(String xmlString) throws Exception
   {
      try
      {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
         DocumentBuilder builder = factory.newDocumentBuilder();
         
         Document document = builder.parse(
             new StringBufferInputStream(xmlString));
         
         return document;
      }
      catch (Exception e)
      {
         throw e;
      }
   }
   
   public static String toString(Document document) throws Exception
   {
      try
      {
         DOMSource domSource = new DOMSource(document);
         
         ByteArrayOutputStream byteArrayOutputStream
               = new ByteArrayOutputStream();
         
         StreamResult streamResult = new StreamResult(byteArrayOutputStream);
         
         TransformerFactory copyTransformerFactory
               = TransformerFactory.newInstance();
         
         Transformer copyTransformer
               = copyTransformerFactory.newTransformer();
         
         copyTransformer.transform(domSource,streamResult);
         
         return byteArrayOutputStream.toString();
      }
      catch (Exception e)
      {
         throw e;
      }
   }
   
}
