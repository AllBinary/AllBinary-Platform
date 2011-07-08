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
package allbinary.business.category;

import abcs.data.tree.dom.document.DomDocumentHelper;
import allbinary.logic.visual.transform.TransformInterface;
import allbinary.logic.visual.transform.data.TransformDocumentInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;

public class CategoryComponent extends CategoryView 
   implements TransformInterface
{
   public CategoryComponent(CategoryInterface categoryInterface)
   {
      super(categoryInterface);
   }

   public int NO_TYPE = 0;
   
   public int getTypeId()
   {
	   return NO_TYPE;
   }
   
   public TransformDocumentInterface getTransformDocumentInterface()
   {
      return null;
   }
   
   public TransformInfoInterface getTransformInfoInterface() throws Exception
   {
      return null;
   }
   
   public void setTransformDocumentInterface(
      TransformDocumentInterface viewDocumentInterface)
   {
   }
   
   public Document toXmlDoc() throws Exception
   {
      Document document = DomDocumentHelper.create();
      
      document.appendChild(
         new CategoryView(this.getCategoryInterface()).toXmlNode(document));
      
      return document;
   }
   
   public String view() throws Exception
   {
      return null;
   }
   
}
