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
package org.allbinary.logic.communication.http.request;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.data.tree.dom.DomData;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

//This will convert tree/namespace requests into a hashmap
//containing string values and other hashmaps of the like
public class NameSpaceRequestParams extends RequestParams
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public NameSpaceRequestParams()
   {
      super();
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("Constructing empty", this, this.commonStrings.CONSTRUCTOR);
      }
   }
   
   public NameSpaceRequestParams(HttpServletRequest httpServletRequest)
   {
      super(httpServletRequest);
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("Constructing from HttpServletRequest", this, this.commonStrings.CONSTRUCTOR);
      }
   }
   
   public NameSpaceRequestParams(PageContext pageContext)
   {
      super(pageContext);
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("Constructing from PageContext", this, this.commonStrings.CONSTRUCTOR);
      }
   }
   
   //make new node
   private Node createPackageNode(
   Document document, String packageName, HashMap packagePropertiesHashMap)
   throws Exception
   {
      final Node node = document.createElement(packageName);
      
      //add root node properties
      final Set propertyKeySet = packagePropertiesHashMap.keySet();
      
      final Object[] propertyKeySetArray = propertyKeySet.toArray();
      final int size = propertyKeySetArray.length;
      for (int index = 0; index < size; index++)
      {
         String propertyName = (String) propertyKeySetArray[index];
         String propertyValue =
         (String) packagePropertiesHashMap.get(propertyName);
         
         Node propertyNode =
            ModDomHelper.createTextNode(document, propertyName, propertyValue);

         node.appendChild(propertyNode);
      }
      
      return node;
   }

   private Node createPackageMultiNode(
   Document document,
   NameSpaceRequestParam nameSpaceRequestParam,
   String packageName, HashMap packagePropertiesHashMap)
   throws Exception
   {
      int beginIndex = packageName.indexOf('[');
      
      String indexValue =
      packageName.substring(beginIndex + 1,
      packageName.length() - 1);
      
      String multiElementPackage =
      packageName.substring(0, beginIndex);
      
      String selectedValue = nameSpaceRequestParam.getValue();
      
      String propertyValue = (String) packagePropertiesHashMap.get(DomData.VALUE);
      
      if(propertyValue.compareTo(NameSpaceRequestParamData.VALUE) != 0)
      {
         selectedValue = propertyValue;
      }
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("\nAppending Multinode Element: " +
         multiElementPackage + "[" + indexValue + "] " +
         selectedValue, this, "createPackageMultiNode");
      }
      
      Node nextNode =
         ModDomHelper.createNameValueNodes(
            document, multiElementPackage, selectedValue);
      
      //Add Index Data
      Node indexNode =
         ModDomHelper.createTextNode(document, DomData.INDEX, indexValue);

      nextNode.appendChild(indexNode);
      return nextNode;
   }
   
   private Node getRootNode(
   String rootElementPackage,
   HashMap packagePropertiesHashMap,
   Document document)
   throws Exception
   {
      Node rootNode = DomSearchHelper.getNodeNoThrow(
      rootElementPackage, document.getChildNodes());
      
      if(rootNode == null)
      {
         //Node Not Already Appended - This means that all children must be added
         //getName()
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
         {
            logUtil.put("\nAppending Document Root: " +
            "\nElementName: " + rootElementPackage +
            "\nProperties: " + packagePropertiesHashMap.toString(),
            this, "getRootNode");
         }
         
         //make new root node
         Node node = this.createPackageNode(
         document, rootElementPackage, packagePropertiesHashMap);
         
         document.appendChild(node);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
         {
            logUtil.put("\nAppended Document Created: " +
            DomDocumentHelper.toString(document),
            this, "getRootNode");
         }
         
         return node;
      }
      else
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
         {
            logUtil.put("\nUsing Existing Root Node", this, "getRootNode");
         }
         return rootNode;
      }
   }

   private Node addNewProperties(
   Document document,
   Node node,
   HashMap nextPackagePropertiesHashMap)
   throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put(new StringMaker().append("\nAdding Any New Properties: ")
             .append(nextPackagePropertiesHashMap.toString()).append("\nto node: ").append(node.getNodeName()).toString(),
             this, "addNewProperties");
      }

      final Set propertyNameSet = nextPackagePropertiesHashMap.keySet();

      final Object[] propertyNameSetArray = propertyNameSet.toArray();
      final int size = propertyNameSetArray.length;
      for (int index = 0; index < size; index++)
      {
         String nextPropertyName = (String) propertyNameSetArray[index];
         String propertyValue = StringUtil.getInstance().getInstance((String)
         nextPackagePropertiesHashMap.get(nextPropertyName));

         //Node name was already checked in previous search
         //Obtain DomData.VALUE and compare to new value
         Node valueNode =
         DomSearchHelper.getNodeNoThrow(nextPropertyName, node.getChildNodes());

         if(valueNode!=null)
         {
            String existingElementNodeTextNodeValue =
            DomNodeHelper.getTextNodeValue(valueNode);

            if( org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put(
                       "\nIf property: \"" + nextPropertyName + " is the same as:\n" +
               "Node: " + node.getNodeName() + " then: " + propertyValue + "==" + 
               existingElementNodeTextNodeValue, this, "addNewProperties");
            }

            //Node does not have the same properties
            if(propertyValue.compareTo(existingElementNodeTextNodeValue) != 0)
            {
               if(  org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
               {
                  logUtil.put("At least one Property from NameSpaceRequest Package was different from Node", this, "addNewProperties");
               }
               //found a property that does not match
               throw new Exception("Property Modifications Should Not Occur");
            }
         }
         else
         {
            //found a new property and now add it
            if( org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put(
                       "Adding a new Property: " + nextPropertyName + "=" + propertyValue,
                       this, "addNewProperties");
            }

            Node newPropertyNode =
               ModDomHelper.createTextNode(
                  document, nextPropertyName, propertyValue);

            node.appendChild(newPropertyNode);
         }
      }
      return node;
   }

   private boolean isElementValueTextNodeEqual(
   HashMap nextPackagePropertiesHashMap,
   Node node)
   throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put(
                 "\nComparing the value property in: " +
         nextPackagePropertiesHashMap.toString() +
         "\nto node child leaves of node: " + node.getNodeName(),
         this, "isElementValueTextNodeEqual");
      }
      
      String propertyValue = (String) nextPackagePropertiesHashMap.get(DomData.VALUE);
      
      //Obtain DomData.VALUE and compare to new value
      Node valueNode = DomSearchHelper.getNodeNoThrow(DomData.VALUE, node.getChildNodes());
      
      if(valueNode!=null)
      {
         String existingElementNodeTextNodeValue =
         DomNodeHelper.getTextNodeValue(valueNode);
         
         //Node does not have the same properties
         //found a property that does not match
         if(propertyValue.compareTo(existingElementNodeTextNodeValue) == 0)
         {
            if( org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put("Value Node is the same", this, "isElementValueTextNodeEqual");
            }
            return true;
         }
      }
      else
      {
         return true;
      }
      return false;
   }
   
   private int isElementValueTextNodeUnique(
   HashMap nextPackagePropertiesHashMap,
   Vector elementNodeVector)
   throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("\nComparing Properties of: " + elementNodeVector.size() + " Nodes", this, "isElementValueTextNodeUnique");
      }
      
      //see if a value node contains unique values
      final int size = elementNodeVector.size();
      for (int index = 0; index < size; index++)
      {
         Node existingElementNode = (Node) elementNodeVector.get(index);

         if(this.isElementValueTextNodeEqual(nextPackagePropertiesHashMap, existingElementNode))
         {
            return index;
         }
      }
      return -1;
   }

   
   private Document addChildren(
   Document document, Node rootNode,
   NameSpaceRequestParam nameSpaceRequestParam) throws Exception
   {
      Node node = rootNode;
      
      //vector contains hashmaps with one dom element
      final Vector packageVector = nameSpaceRequestParam.getPackages();
      
      //skip root
      //Add Children
      final int size = packageVector.size();
      for (int index = 1; index < size; index++)
      {
         String nextPackageName = (String) packageVector.get(index);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
         {
            logUtil.put(new StringMaker().append("\nTrying to Add Child to Node: ").append(node.getNodeName()).append(
            "\nwith new PackageName: ").append(nextPackageName).toString(), this, "addChildren");
         }
         
         HashMap nextPackagePropertiesHashMap =
         nameSpaceRequestParam.getPackageProperties(index);
         
         Vector elementNodeVector =
         DomSearchHelper.getAllNodesNoThrow(
         nextPackageName, node.getChildNodes());
         
         //Document already has atleast 1 node with the same package name
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
         {
            logUtil.put("\nDocument Contains " + elementNodeVector.size() +
            " Node(s) With Same Name", this, "addChildren");
         }
         
         //see what kind of node exists
         //Example:
         //1. Unique Nodes Only like - CSS Element & CSS Property - currently only checks value property
         //2. CSS Property Value can duplicate existing nodes
         //The default is unique mode
         //Special for value property
         int isElementValueTextNodeUniqueIndex =
            this.isElementValueTextNodeUnique(nextPackagePropertiesHashMap, elementNodeVector);

         /*
         if(isElementValueTextNodeUnique)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put("Adding Package with different value node", this, "add()");
            }
         }
         */

         //Create new node if node with the same name does not exist unless an array multinode
         if((elementNodeVector.size() == 0 || 
            isElementValueTextNodeUniqueIndex == -1) &&
            !nextPackageName.endsWith(CommonSeps.getInstance().BRACKET_CLOSE))
         {
            Node nextNode = this.createPackageNode(
               document, nextPackageName, nextPackagePropertiesHashMap);
            
            node.appendChild(nextNode);
            
            //Crazy data carry through
            node = nextNode;
            
            if( org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put("\nAppended Package: " + node.getNodeName(),
               this, "addChildren");
               logUtil.put("\nAppended Document Created: " + DomDocumentHelper.toString(document),
               this, "addChildren");
            }
         }
         else
            if(nextPackageName.endsWith(CommonSeps.getInstance().BRACKET_CLOSE))
            {
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
               {
                  logUtil.put("Package is MultiNode: just append", this, "addChildren");
               }
               
               Node nextNode = this.createPackageMultiNode(
               document, nameSpaceRequestParam, nextPackageName, nextPackagePropertiesHashMap);
               
               node.appendChild(nextNode);
               node = nextNode;
               
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
               {
                  logUtil.put("\nAppended Multinode Element: " +
                  DomDocumentHelper.toString(document),
                  this, "addChildren");
               }
            }
            else
            {
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
               {
                  logUtil.put("Value Node already exists: Now compare (properties to leaf nodes)", this, "addChildren");
               }

               Node nodeNameDuplicateNode = (Node)
                  elementNodeVector.get(isElementValueTextNodeUniqueIndex);
               
               node = this.addNewProperties(
                  document, nodeNameDuplicateNode, nextPackagePropertiesHashMap);
               
               //Not Unique so move on to duplicate
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
               {
                  logUtil.put("\nDuplicate Node Found: Moving to next child ^", this, "addChildre");
               }
               //elementNodeVector.get(0);
               //node = nodeNameDuplicateNode;
            }
      }
      return document;
   }
   
   private Document addNameSpace(String key, String value, Document document) throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put(new StringMaker().append("NameSpace key: ").append(key).append(" Value: ").append(value).toString(), this, "addNameSpace");
      }
      
      final NameSpaceRequestParam nameSpaceRequestParam = new NameSpaceRequestParam(key, value);
      
      //vector contains hashmaps with one dom element
      final Vector packageVector = nameSpaceRequestParam.getPackages();

      final int size = packageVector.size();
      for (int index = 0; index < size; index++)
      {
         String packageName = (String) packageVector.get(index);
         HashMap packagePropertiesHashMap =
         nameSpaceRequestParam.getPackageProperties(packageVector.indexOf(packageName));
         
         Node rootNode =
         this.getRootNode(packageName, packagePropertiesHashMap, document);
         
         //document
         if(index < size - 1)
         {
            return this.addChildren(document,(Node) rootNode, nameSpaceRequestParam);
         }
      }
      return document;
   }
   
   public HashMap toHashMap() throws Exception
   {
      Document document = DomDocumentHelper.create();
      HashMap hashMap = new HashMap();
      Set keys = this.getMap().keySet();
      Object[] keyArray = keys.toArray();
      int keySize = keyArray.length;
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
      org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("NameSpace Request Size: " + keys.size(), this, "toHashMap");
      }
      
      for (int i = 0; i < keySize; i++)
      {
         String key = (String) keyArray[i];
         
         Object object = this.getMap().get(key);
         String className = (String) object.getClass().getName();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
         {
            logUtil.put("Request Param Class: " + className, this,"toHashMap");
         }
         
         if(this.getMap().get(key) instanceof String)
         {
            String value = (String) this.getMap().get(key);
            
            if(key.indexOf(NameSpaceRequestParamData.NAME) < 0)
            {
               if(  org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
               {
                  logUtil.put("Not NameSpace key: " + key + " Value: " + value,this,"toHashMap()");
               }
               
               hashMap.put(new String(key), new String(value));
            }
            else
            {
               document =
               this.addNameSpace(new String(key), new String(value), document);
            }
         }
         else
            if(this.getMap().get(key) instanceof String[])
            {
               String[] values = (String[]) this.getMap().get(key);
               
               if(key.indexOf(NameSpaceRequestParamData.NAME) < 0)
               {
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
                  {
                     logUtil.put("Not NameSpace key: " + key + " Value: " + values[0],this,"toHashMap()");
                  }
                  
                  hashMap.put(new String(key), new String(values[0]));
               }
               else
               {
                  document =
                  this.addNameSpace(new String(key), new String(values[0]), document);
               }
            }
      }
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("NameSpaceRequestParams: " + this.getMap().toString() +
         "\ntoHashMap(): " + hashMap.toString() +
         "\nDocument Created: " + DomDocumentHelper.toString(document),
         this, "toHashMap()");
      }
      
      hashMap.put(NameSpaceRequestParamData.DOCUMENT, document);
      
      return hashMap;
   }
}

   /*
   private boolean isNodeNull(Node node)
   {
      if(node == null)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   private boolean isNegative(int index)
   {
      if(index == -1)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
      
   //Compares the nextPackage properties with an existing node
   //returns true if node is duplicate
   private boolean isPropertiesSame(
   HashMap nextPackagePropertiesHashMap,
   Node node)
   throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("\nComparing the properties in: " +
         nextPackagePropertiesHashMap.toString() +
         "\nto node child leaves of node: " + node.getNodeName(),
         this, "isPropertiesSame()");
      }
      
      Set propertyNameSet = nextPackagePropertiesHashMap.keySet();
      iter = propertyNameSet
      while(iter.hasNext())
      {
         String nextPropertyName = (String) iter.next();
         String propertyValue = StringUtil.getInstance((String)
         nextPackagePropertiesHashMap.get(nextPropertyName);
         
         //Node name was already checked in previous search
         //Obtain DomData.VALUE and compare to new value
         Node valueNode =
         DomSearchHelper.getNodeNoThrow(nextPropertyName, node.getChildNodes());
         
         if(valueNode!=null)
         {
            String existingElementNodeTextNodeValue =
            DomNodeHelper.getTextNodeValue(valueNode);
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put("\nIf property: \"" + nextPropertyName + " is the same as:\n" +
               "Node: " + node.getNodeName() + " then: " + propertyValue + "==" + existingElementNodeTextNodeValue, this, "isPropertiesSame()");
            }
            
            //Node does not have the same properties
            if(propertyValue.compareTo(existingElementNodeTextNodeValue) != 0)
            {
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
               {
                  logUtil.put("At least one Property from NameSpaceRequest Package was different from Node", this, "isPropertiesSame()");
               }
               //found a property that does not match
               return false;
            }
         }
         else
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put("A new property from NameSpaceRequest Package existed so it is not the same.", this, "isPropertiesSame()");
            }
            return false;
         }
      }
      
      //Already exists
      return true;
   }
   
   private boolean isAPropertyNewOrAreAllPropertiesTheSame(
   HashMap nextPackagePropertiesHashMap,
   Node node)
   throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("\nComparing the properties in: " +
         nextPackagePropertiesHashMap.toString() +
         "\nto node child leaves of node: " + node.getNodeName(),
         this, "isPropertiesSame()");
      }
      
      Set propertyNameSet = nextPackagePropertiesHashMap.keySet();
      iter = propertyNameSet;
      while(iter.hasNext())
      {
         String nextPropertyName = (String) iter.next();
         String propertyValue = StringUtil.getInstance((String)
         nextPackagePropertiesHashMap.get(nextPropertyName);
         
         //Node name was already checked in previous search
         //Obtain DomData.VALUE and compare to new value
         Node valueNode =
         DomSearchHelper.getNodeNoThrow(nextPropertyName, node.getChildNodes());
         
         if(valueNode!=null)
         {
            String existingElementNodeTextNodeValue =
            DomNodeHelper.getTextNodeValue(valueNode);
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
            {
               logUtil.put("\nIf property: \"" + nextPropertyName + " is the same as:\n" +
               "Node: " + node.getNodeName() + " then: " + propertyValue + "==" + existingElementNodeTextNodeValue, this, "isPropertiesSame()");
            }
            
            //Node does not have the same properties
            if(propertyValue.compareTo(existingElementNodeTextNodeValue) != 0)
            {
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
               {
                  logUtil.put("At least one Property from NameSpaceRequest Package was different from Node", this, "isPropertiesSame()");
               }
               //found a property that does not match
               return false;
            }
         }
         else
         {
            //found a new property
            return true;
         }
      }
      
      //Already exists
      return true;
   }
   
   //Returns null-1 if node is not found
   private int getNodeWithDuplicatePropertiesFromVector(
   HashMap nextPackagePropertiesHashMap,
   Vector elementNodeVector)
   throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
      {
         logUtil.put("\nComparing Properties of: " + elementNodeVector.size() + " Nodes", this, "getNodeWithDuplicatePropertiesFromVector()");
      }
      
      int index = 0;
      //see if a value node contains unique values
      iter = elementNodeVector;
      while(iter.hasNext())
      {
         Node existingElementNode = (Node) iter.next();
         if(this.isAPropertyNewOrAreAllPropertiesTheSame(nextPackagePropertiesHashMap, existingElementNode))
         {
            return index;
         }
         index++;
      }
      return -1;
   }
    **/
