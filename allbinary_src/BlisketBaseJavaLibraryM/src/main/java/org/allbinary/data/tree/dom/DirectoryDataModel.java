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

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.visual.transform.TransformInterface;
import org.allbinary.logic.visual.transform.data.TransformDocumentFactory;
import org.allbinary.logic.visual.transform.data.TransformDocumentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DirectoryDataModel
   implements DomNodeInterface, TransformInterface
{
   private final BasicArrayList fileBasicArrayList;
   private TransformDocumentInterface transformDocumentInterface;

   public DirectoryDataModel(final AbFile file) throws Exception
   {
      this.fileBasicArrayList = Directory.getInstance().search(file);
      this.transformDocumentInterface = 
         (TransformDocumentInterface) TransformDocumentFactory.getInstance();
   }
   
   public int NO_TYPE = 0;
   
   public int getTypeId()
   {
	   return NO_TYPE;
   }
   
   public TransformInfoInterface getTransformInfoInterface() throws Exception
   {
      return null;
   }
   
   public void setTransformDocumentInterface(
      TransformDocumentInterface transformDocumentInterface)
   {
      this.transformDocumentInterface = transformDocumentInterface;
   }
   
   public TransformDocumentInterface getTransformDocumentInterface()
   {
      return this.transformDocumentInterface;
   }

   //Create the directory Node
   public Node toXmlNode(final Document document) throws Exception
   {
      final Node dirNode = document.createElement("dir");

      final int size = this.fileBasicArrayList.size();
      
      AbFile nextFile;
      Node fileNode;
      for(int index = 0; index < size; index++)
      {
         nextFile = (AbFile) this.fileBasicArrayList.get(index);
         fileNode = new FileDomDataModel(nextFile).toXmlNode(document);
         dirNode.appendChild(fileNode);
      }

      return dirNode;
   }

   public Document toXmlDoc() throws Exception
   {
      return this.getTransformDocumentInterface().getDoc();
   }
   
   //In this case the data model is the view as well
   public String view() throws Exception
   {
      Node node = this.toXmlNode(this.getTransformDocumentInterface().getDoc());
      this.getTransformDocumentInterface().getBaseNode().appendChild(node);
      return DomDocumentHelper.toString(this.getTransformDocumentInterface().getDoc());
   }
}
