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

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.AbFileInputStream;
import org.allbinary.logic.io.DataOutputStreamFactory;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.w3c.dom.Document;

public class DomDocumentFileHelper
{
   //System property specifying the TransformerFactory returned 
   //"javax.xml.transform.TransformerFactory"
   //"javax.xml.parsers.DocumentBuilderFactory"
   private DomDocumentFileHelper()
   {
   }

   /*
   public static Document create(AbFile xmlFile) throws Exception
   {
      return DomDocumentFileHelper.create(xmlFile);
   }
   */
   
   public static Document create(AbFile xmlFile) throws Exception
   {
      try
      {
         //DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

         //DocumentBuilder builder = factory.newDocumentBuilder();

         //Document document = builder.parse(xmlFile.getFile());
         
         //return document;
          return DomDocumentHelper.create(new AbFileInputStream(xmlFile));
      }
      catch (Exception e)
      {
         throw e;
      }
   }
   
   public static void save(AbFile file, Document document) throws Exception
   {
      AbDataOutputStream dataOutputStream = null;
      try
      {
         TransformerFactory copyTransformerFactory =
               TransformerFactory.newInstance();
         
         Transformer copyTransformer =
               copyTransformerFactory.newTransformer();
         
         DOMSource domSource = new DOMSource(document);
         
         if(file.isFile())
         {
        	 file.delete();
         }
         file.createNewFile();

         dataOutputStream =
             DataOutputStreamFactory.getInstance().getInstance(file);

         StreamResult streamResult = 
             new StreamResult(dataOutputStream);
             //new StreamResult(file.getFile());

         copyTransformer.transform(domSource,streamResult);

         dataOutputStream.flush();
      }
      catch(Exception e)
      {
         throw e;
      }
      finally
      {
          StreamUtil.getInstance().close(dataOutputStream);
      }
   }
   
}
