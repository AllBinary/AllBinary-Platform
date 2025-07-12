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

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class TransformInfoDomNodeView 
   extends TransformInfoDomNode 
   implements DomNodeInterface
{   
   public TransformInfoDomNodeView(
      TransformInfoInterface transformInfoInterface, String mapping)
   {
      super(transformInfoInterface, mapping);
   }

   public TransformInfoDomNodeView(
      TransformInfoInterface transformInfoInterface)
   {
      super(transformInfoInterface);
   }

   public TransformInfoDomNodeView(String name, String mappedName) throws Exception
   {
      super(name, mappedName);
   }

   public TransformInfoDomNodeView(String name) throws Exception
   {
      super(name, null);
   }

   public Node toXmlNode(Document document) throws Exception
   {
	  TransformInfoData transformInfoData = 
		 TransformInfoData.getInstance();
	   
      Node subViewNode = document.createElement(transformInfoData.NAME);

      Attr viewNameAttr = document.createAttribute(transformInfoData.NAME);
      viewNameAttr.setValue(this.getTransformInfoInterface().getName());
      //subViewNode.appendChild(viewNameAttr);
      NamedNodeMap componentNodeAttributes = subViewNode.getAttributes();
      componentNodeAttributes.setNamedItem(viewNameAttr);
      
      if(this.getMappedName() != null)
      {
         Attr mappedNameAttr = document.createAttribute(transformInfoData.MAPPED);
         mappedNameAttr.setValue(this.getMappedName());
         componentNodeAttributes.setNamedItem(mappedNameAttr);
      }

      return subViewNode;
   }
}
