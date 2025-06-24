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

import org.allbinary.data.tree.dom.DomData;
import java.util.HashMap;

import java.util.Set;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.java.object.clazz.ClassUtil;

public class ModDomHelper
{
   
   private ModDomHelper()
   {
   }

   public static Node createTextNode(Document document, String name, String text) throws Exception
   {
      Node newValueNode = document.createElement(name);
      Node newValueTextNode = document.createTextNode(text);
      newValueNode.appendChild(newValueTextNode);
      return newValueNode;
   }

   public static Node createValueNode(Document document, String text) throws Exception
   {
      return ModDomHelper.createTextNode(document, DomData.VALUE, text);
   }

   public static Node createNameNode(Document document, String text) throws Exception
   {
      return ModDomHelper.createTextNode(document, DomData.NAME, text);
   }

   public static Node createIndexNode(Document document, int index) throws Exception
   {
      return ModDomHelper.createTextNode(
         document, DomData.INDEX, new Integer(index).toString());
   }

   public static Node createNameValueNodes(Document document, String elementName, String nameText, String valueText) throws Exception
   {
      Node newNode = document.createElement(elementName);

      newNode.appendChild(ModDomHelper.createNameNode(document, nameText));
      newNode.appendChild(ModDomHelper.createValueNode(document, valueText));

      return newNode;
   }

   public static Node createNameValueNodes(Document document, String elementNameAndNameText, String valueText) throws Exception
   {
      Node newNode = ModDomHelper.createNameValueNodes(
         document, elementNameAndNameText, elementNameAndNameText, valueText);

      return newNode;
   }

   public static Node createNameValueIndexNodes(Document document, String elementNameAndNameText, int index, String valueText) throws Exception
   {
      Node newNode = ModDomHelper.createNameValueNodes(document, elementNameAndNameText, valueText);

      newNode.appendChild(ModDomHelper.createIndexNode(document, index));

      return newNode;
   }

   public static Node createNameValueNodes(Document document, String rootNode, HashMap hashMap) throws Exception
   {
      Node node = document.createElement(rootNode);
      return ModDomHelper.createNameValueNodes(document, node, hashMap);
   }

   //Can convert any class implementing DataMappingInterface into Dom Node
   //Don't use - use withvaluenodes - it excludes name nodes and makes value nodes a text node
   public static Node createNameValueNodes(Document document, Node node, HashMap hashMap) throws Exception
   {
       StringUtil stringUtil = StringUtil.getInstance();

      Set keySet = hashMap.keySet();
      Object[] keyArray = keySet.toArray();
      int size = keyArray.length;
      for (int i = 0; i < size; i++)
      {
         Object objectKey = keyArray[i];
         String name = (String) objectKey;
         Object object = (Object) hashMap.get(objectKey);
         
         //LogUtil.put(LogFactory.getInstance("Adding: " + name + "=" + value ,this,"toXmlNode(document)");
         
         if(object == null)
         {
            String value = stringUtil.EMPTY_STRING;
            node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));
         }
         else
         if(object instanceof String)
         {
            String value = stringUtil.getInstance((String) object);
            node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));
         }
         else
         //Recursively add Child HashMaps
         if(object instanceof HashMap)
         {
             node.appendChild(ModDomHelper.createNameValueNodes(document, name, hashMap));
         }

         /*
         else
         if(object instanceof HashMap)
         {
            HashMap value = (HashMap) object;
            node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));
         }
          */
         else
         {
            throw new Exception("HashMap value is the wrong instance and is: " + ClassUtil.viewAll(object,"\n"));
         }
      }
      return node;
   }
   
   public static Node createNameValueNodes(
   Document document, String rootNodeName, 
   String duplicateNodeName, Vector valueVector) throws Exception
   {
       StringUtil stringUtil = StringUtil.getInstance();
       
      Node node = document.createElement(rootNodeName);      
      int size = valueVector.size();
      for (int i = 0; i < size; i++)
      {
         String value = stringUtil.getInstance((String) valueVector.get(i));
         //LogUtil.put(LogFactory.getInstance("Adding: " + name + "=" + value ,this,"");
         
         node.appendChild(
         ModDomHelper.createNameValueNodes(
         document, duplicateNodeName, value));
      }
      return node;
   }

   public static Node createNameValueIndexNodes(
   Document document, String rootNodeName, 
   String duplicateNodeName, Vector valueVector) throws Exception
   {
       StringUtil stringUtil = StringUtil.getInstance();
       
      Node node = document.createElement(rootNodeName);

      int size = valueVector.size();
      for (int index = 0; index < size; index++)
      {
         String value = stringUtil.getInstance((String) valueVector.get(index));
         //LogUtil.put(LogFactory.getInstance("Adding: " + name + "=" + value ,this,"");

         node.appendChild(
         ModDomHelper.createNameValueIndexNodes(
            document, duplicateNodeName, index, value));
      }
      return node;
   }

   public static Node createNodeWithValueNodes(Document document, String rootNode, HashMap hashMap) throws Exception
   {
      Node node = document.createElement(rootNode);
      return ModDomHelper.createNodeWithValueNodes(document, node, hashMap);
   }

   public static Node createNodeWithValueNodes(Document document, Node node, HashMap hashMap) throws Exception
   {
       StringUtil stringUtil = StringUtil.getInstance();
       
      Set keySet = hashMap.keySet();
      Object[] keyArray = keySet.toArray();
      int size = keyArray.length;
      for (int i = 0; i < size; i++)
      {
         String name = (String) keyArray[i];
         Object object = (Object) hashMap.get(name);
         
         //LogUtil.put(LogFactory.getInstance("Adding: " + name + "=" + value ,this,"toXmlNode(document)");

         String value = stringUtil.getInstance((String) object);
         node.appendChild(ModDomHelper.createTextNode(document, name, value));
         
         /*
         if(object == null)
         {
            String value = StringUtil.getInstance();
            node.appendChild(ModDomHelper.createTextNode(document, name, value));
         }
         else
         if(object instanceof String)
         {
            String value = StringUtil.getInstance((String) object);
            node.appendChild(ModDomHelper.createTextNode(document, name, value));
         }
         else
         */
         
         /*
         else
         if(object instanceof HashMap)
         {
            HashMap value = (HashMap) object;
            node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));
         }
          */
         
         //Recursively add Child HashMaps
         /*
         if(object instanceof HashMap)
         {
             node.appendChild(
                 ModDomHelper.createNodeWithValueNodes(document, name, hashMap));
         }
         */
         
         /*
         else
         {
            throw new Exception("HashMap value is the wrong instance and is: " + ClassUtil.viewAll(object, "\n"));
         }
         */
      }
      return node;
   }
}
