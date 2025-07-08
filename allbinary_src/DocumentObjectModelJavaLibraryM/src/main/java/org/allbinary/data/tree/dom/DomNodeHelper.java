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

//import javax.xml.soap.Text;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.logic.string.StringUtil;

public class DomNodeHelper
{
   //private static final String TEMPNODE = "TEMP_NODE";
   //private static String tempNodeStr = "<TEMP_NODE></TEMP_NODE>";
   //null;

   private DomNodeHelper()
   {
   }

   /*
   public static final Node createTempNode(Document document)
   {
      return document.createElement(TEMPNODE);
   }

   public static String getKey()
   {
      return tempNodeStr;
   }
*/
   
   public static Node getFirstChildElement(Node parentNode)
   {

   Node node = parentNode.getFirstChild();

   if (node != null && node.getNodeType() != Node.ELEMENT_NODE)
   {
	   NodeList nodeList = parentNode.getChildNodes();
	   
       for (int index = 0; index < nodeList.getLength(); index++)
       {
           node = nodeList.item(index);

           if (node.getNodeType() == 1)
           {
               break;
           }
           //logUtil.put("NodeType: " + node.getNodeType(), this, "getDoc()");
       }
   }
   return node;
   }
   
   public static String getTextNodeValue(Node node)
   {
      Node nodeTextNode = node.getFirstChild();
      if(nodeTextNode!=null)
      {
         return nodeTextNode.getNodeValue();
      }
      else 
      {
        return StringUtil.getInstance().EMPTY_STRING;
      }
   }

   public static String getTextNodeValue(String nodeName, NodeList nodeList) 
      throws Exception
   {
      Node node = DomSearchHelper.getNode(nodeName, nodeList);
      return DomNodeHelper.getTextNodeValue(node);
   }

   public static String getTextNodesValue(Node node)
   {
      StringBuffer stringBuffer = new StringBuffer();
      NodeList nodeList = node.getChildNodes();

      for(int index = 0; index < nodeList.getLength(); index++)
      {
         Node nodeTextNode = nodeList.item(index);
         
         //instanceof org.w3c.dom.Text
         if(nodeTextNode.getNodeType() == Node.TEXT_NODE)
         {
            if(nodeTextNode!=null)
            {
               stringBuffer.append(nodeTextNode.getNodeValue());
            }
         }
      }

      return stringBuffer.toString();
   }

}
