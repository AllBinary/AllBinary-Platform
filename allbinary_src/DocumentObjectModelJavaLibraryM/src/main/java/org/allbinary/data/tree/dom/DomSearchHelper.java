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

import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomSearchHelper
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private DomSearchHelper()
   {
   }

   //Finds first node in the nodelist with the same tagname
   public static Node getNodeNoThrow(String nodeName, NodeList nodeList)
   {
      //PreLogUtil.put("NodeName: " + nodeName, "DomHelper", "searchNodeList");
      final int numberOfNodes = nodeList.getLength();         
      for(int index = 0; index < numberOfNodes; index++)
      {
         final Node node = nodeList.item(index);
         
         //PreLogUtil.put("next NodeName: " + node.getNodeName(), "DomHelper", "searchNodeList");
         
         if(node.getNodeName().compareTo(nodeName)==0)
         {
            return node;
         }
      }
      return null;
   }
   
   public static Node getNode(String nodeName, NodeList nodeList) throws Exception
   {
      Node node = DomSearchHelper.getNodeNoThrow(nodeName, nodeList);
      if(node != null)
      {
         return node;
      }
      else
      {
         throw new Exception(nodeName + " Node Not Found in search");
      }
   }

   public static NodeList getChildNodeList(
      String nodeName, NodeList nodeList) throws Exception
   {
      Node node = DomSearchHelper.getNode(nodeName, nodeList);
      return node.getChildNodes();
   }

   //This may be dangerious - since nodes in vector may be lost if it is the last reference
   public static Vector getAllNodes(String nodeName, NodeList nodeList) throws Exception
   {
      Vector vector = DomSearchHelper.getAllNodesNoThrow(nodeName, nodeList);
      if(vector == null)
      {
         throw new Exception(nodeName + " Node Not Found in search");
      }
      else
      {
         return vector;
      }
   }
   
   //This may be dangerious - since nodes in vector may be lost if it is the last reference
   //Finds all nodes in the nodelist with the same tagname
   public static Vector getAllNodesNoThrow(String nodeName, NodeList nodeList)
   {
      Vector nodeVector = new Vector();
      
      int numberOfNodes = nodeList.getLength();         
      for(int index = 0; index < numberOfNodes; index++)
      {               
         Node node = nodeList.item(index);
         /*         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XMLLOGGING))
         {         
            PreLogUtil.put("NodeName: " + node.getNodeName(),"DomHelper","searchNodeList");
         }
         */
         if(node.getNodeName().compareTo(nodeName)==0)
         {
            nodeVector.add(node);
         }
      }
      return nodeVector;
   }

}
