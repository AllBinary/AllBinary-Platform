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
package org.allbinary.logic.visual.transform.info.objectConfig;

import java.util.Vector;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.io.InputOutputTypeData;
import org.allbinary.logic.io.OutputTypeData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoDomNode;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfosData;
import org.allbinary.logic.visual.transform.template.XslData;
import org.allbinary.string.CommonStrings;

public class TransformInfoObjectConfig 
   implements TransformInfoObjectConfigInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final TransformInfoInterface ownerTransformInfoInterface;
   /**
    * <OBJECTCONFIG_NAME OBJECTCONFIG_NAME="GenericStoreCompoundTransformInfoConfig" >
    * <OBJECTCONFIG_TEMPLATE_NAME TRANSFORM_INFO_NAME="a Contact" >
    * </OBJECTCONFIG_TEMPLATE_NAME>
    * <TRANSFORM_COMPONENTS_NAME>
    * <COMPONENT_NAME TRANSFORM_INFO_NAME="a Contact Body" >
    * </COMPONENT_NAME>
    * </TRANSFORM_COMPONENTS_NAME>
    * </OBJECTCONFIG_NAME>
    */

   private Document document;

   private String outputTypeName;
   
   /*
   private String inputOutputTypeName;
   private String inputOutputTypeFile;

   private String importUriPath;
   */
   
   public TransformInfoObjectConfig(TransformInfoInterface transformInfoInterface)
       throws Exception
   {
      this.ownerTransformInfoInterface = transformInfoInterface;

      this.createDocument();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append("TransformInfo: ");
    	  
    	  if(this.ownerTransformInfoInterface != null)
    	  {
    		  stringBuffer.append(this.ownerTransformInfoInterface.getName());
    	  }
    	  else
    	  {
    		  stringBuffer.append("No Owner!?#@");
    	  }

    	  stringBuffer.append("\nConstructed with document: ");
    	  stringBuffer.append(this.toString());
    	  
          logUtil.put(stringBuffer.toString(), this, "Constructor(TransformInfoInterface)");
      }
   }

   public TransformInfoObjectConfig(TransformInfoInterface transformInfoInterface, Document document)
       throws Exception
   {
      this.ownerTransformInfoInterface = transformInfoInterface;

      this.document = document;

      Node node = DomSearchHelper.getNodeNoThrow(
         OutputTypeData.getInstance().NAME, this.getRootNode().getChildNodes());
      if(node != null)
      {
         this.setOutputTypeName(DomNodeHelper.getTextNodeValue(node));
      }

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append("TransformInfo: ");
    	  
    	  if(this.ownerTransformInfoInterface != null)
    	  {
    		  stringBuffer.append(this.ownerTransformInfoInterface.getName());
    	  }
    	  else
    	  {
    		  stringBuffer.append("No Owner!?#@");
    	  }

    	  stringBuffer.append("\nConstructed with document: ");
    	  stringBuffer.append(this.toString());
    	  
         logUtil.put(stringBuffer.toString(), this, "Constructor(TransformInfoInterface, Document)");
      }
   }

   public TransformInfoObjectConfig(
       TransformInfoInterface transformInfoInterface,
       String name, String type)
       throws Exception
   {
      this.ownerTransformInfoInterface = transformInfoInterface;

      this.createDocument();

      Attr configNameAttr = this.document.createAttribute(
    		  TransformInfoObjectConfigData.getInstance().NAME);

      configNameAttr.setValue(name);

      Node objectConfigNode = this.document.getElementsByTagName(
         TransformInfoObjectConfigData.getInstance().NAME).item(0);

      objectConfigNode.appendChild(configNameAttr);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append("TransformInfo: ");
    	  
    	  if(this.ownerTransformInfoInterface != null)
    	  {
    		  stringBuffer.append(this.ownerTransformInfoInterface.getName());
    	  }
    	  else
    	  {
    		  stringBuffer.append("No Owner!?#@");
    	  }

    	  stringBuffer.append("\nConstructed with document: ");
    	  stringBuffer.append(this.toString());
    	  
         logUtil.put(stringBuffer.toString(), this, "Constructor(TransformInfoInterface, name, type)");
      }
   }

   private void createDocument()
   {
      this.document = DomDocumentHelper.create();

      Node objectConfigNode = document.createElement(
    		  TransformInfoObjectConfigData.getInstance().NAME);

      this.document.appendChild(objectConfigNode);
   }

   protected TransformInfoInterface getTransformInfoInterface()
   {
      return this.ownerTransformInfoInterface;
   }

   public Document toXmlDoc() throws Exception
   {
      return this.document;
   }

   protected void setDocument(Document document)
   {
      this.document = document;
   }
   
   //Looks to see if view is in this TransformInfoObjectConfig
   //Usually used for updating a sites templates
   public boolean containsView(TransformInfoInterface transformInfoInterface)
   {
      Node objectConfigNode = this.document.getElementsByTagName(
    		  TransformInfoObjectConfigData.getInstance().NAME).item(0);

      NodeList viewNodeList = objectConfigNode.getChildNodes();
      int numberOfViews = viewNodeList.getLength();

      for(int index = 0; index < numberOfViews; index++)
      {
         Node viewNode = viewNodeList.item(index);
         NamedNodeMap viewAttributes = viewNode.getAttributes();
         Attr attrNode = (Attr) viewAttributes.getNamedItem(TransformInfoData.getInstance().NAME);
         if(transformInfoInterface.getName().compareTo(attrNode.getValue())==0)
         {
            return true;
         }
      }
      return false;
   }

   private NamedNodeMap getTemplateAttributes() throws Exception
   {
      Node componentNode = this.document.getElementsByTagName(
    		  TransformInfoObjectConfigData.getInstance().NAME).item(0);
      return componentNode.getAttributes();
   }
   
   public String getName() throws Exception
   {
      Attr attrNode = (Attr) this.getTemplateAttributes().getNamedItem(
    		  TransformInfoObjectConfigData.getInstance().NAME);
      return attrNode.getValue();
   }
   
   public void setName(String name) throws Exception
   {
      Attr attrNode = (Attr) this.getTemplateAttributes().getNamedItem(
    		  TransformInfoObjectConfigData.getInstance().NAME);
      
      attrNode.setValue(name);
   }

   private Vector getNodeVector(String nodeName)
   throws Exception
   {
	      NodeList componentsNodeList =
	          this.document.getElementsByTagName(nodeName);

	       if(componentsNodeList != null &&
	          componentsNodeList.getLength() > 0)
	       {
	          Vector viewNodeVector = DomSearchHelper.getAllNodes(
	          TransformInfoData.getInstance().NAME, componentsNodeList.item(0).getChildNodes());

	          int numberOfViews = viewNodeVector.size();

	          if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
	          {
	              StringBuffer stringBuffer = new StringBuffer();

	              stringBuffer.append("Number Of ");
	              stringBuffer.append(nodeName );
	              stringBuffer.append(" Nodes: ");
	              stringBuffer.append(numberOfViews);

	             logUtil.put(stringBuffer.toString(), this, "getNodeVector(nodename)");
	             //logUtil.put("Document: " +
	             //DomHelper.toString(this.document), this, "getComponents()");
	          }
	          return viewNodeVector;
	       }
	       return new Vector();
    }

   //Best to not use this
   private Vector getTransformDomNodes(String nodeName) 
   throws Exception
   {
      Vector viewVector = new Vector();

      Vector viewNodeVector = this.getNodeVector(nodeName);
      
       final int size = viewNodeVector.size();
       for (int index = 0; index < size; index++)
       {
          Node viewNode = (Node) viewNodeVector.get(index);
          viewVector.add(new TransformInfoDomNode(viewNode));
          //.getTransformInfoInterface()
       }

      return viewVector;
   }
   
   public Vector getTransforms(String nodeName) 
   throws Exception
   {
      Vector viewVector = new Vector();

      Vector viewNodeVector = this.getNodeVector(nodeName);
      
       final int size = viewNodeVector.size();
       for (int index = 0; index < size; index++)
       {
          Node viewNode = (Node) viewNodeVector.get(index);
          viewVector.add(new TransformInfoDomNode(viewNode).getTransformInfoInterface());
       }

      return viewVector;
   }

   public Vector getTransformsGroup(String group) throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("Started: " + group, this, "getTransformsGroup()");
      }

      Vector viewVector = new Vector();

      final String GROUP = TransformInfosData.getInstance().GROUP;
      
      NodeList componentsNodeList = this.document.getElementsByTagName(GROUP);

      if(componentsNodeList != null &&
    	         componentsNodeList.getLength() > 0)
      {
         Node componentsNode = componentsNodeList.item(0);

         int length = componentsNodeList.getLength();
         for(int index = 0; index < length; index++)
         {
            Node node = componentsNodeList.item(index);
            NamedNodeMap attributes = node.getAttributes();
            Attr attrNode = (Attr) attributes.getNamedItem(GROUP);
            String value = attrNode.getValue();

            if(value.compareTo(group) == 0)
            {
               componentsNode = node;
               break;
            }
         }

         Vector viewNodeVector = DomSearchHelper.getAllNodes(
            TransformInfoData.getInstance().NAME, componentsNode.getChildNodes());

         int numberOfViews = viewNodeVector.size();

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("Number Of Nodes: " + numberOfViews, this, "getTransformsGroup()");
            //logUtil.put("Document: " +
            //DomHelper.toString(this.document), this, "getTransformsGroup()");
         }

         final int size = viewNodeVector.size();
         for (int index = 0; index < size; index++)
         {
            Node viewNode = (Node) viewNodeVector.get(index);
            viewVector.add(new TransformInfoDomNode(viewNode));
         }
      }
      else
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
        	 if(componentsNodeList == null)
        	 {
        		 logUtil.put("Number Of Nodes: NULL", this, "getTransformsGroup()"); 
        	 }
        	 else
        	 {
        		 logUtil.put("Number Of Nodes: 0", this, "getTransformsGroup()");
        	 }
         }
      }

      return viewVector;
   }

   public Vector getTransformDomNodes() throws Exception
   {
      return this.getTransformDomNodes(TransformInfosData.getInstance().GROUP);
   }
   
   public Vector getTransforms() throws Exception
   {
      return this.getTransforms(TransformInfosData.getInstance().NAME);
   }

   public Vector getGroupTransforms() throws Exception
   {
      return this.getTransforms(TransformInfosData.getInstance().GROUP);
   }
   
   public Vector getParentTransforms() throws Exception
   {
      return this.getTransforms(TransformInfoData.getInstance().PARENT);
   }

   private Node getRootNode()
   {
      return this.document.getElementsByTagName(
    		  TransformInfoObjectConfigData.getInstance().NAME).item(0);
   }

   public void setOutputTypeName(String outputTypeName)
   {
      this.outputTypeName = outputTypeName;
   }

   public String getOutputTypeName() throws Exception
   {
      return this.outputTypeName;
   }

   public String getInputOutputTypeName() throws Exception
   {
      Node node = DomSearchHelper.getNode(
         InputOutputTypeData.getInstance().NAME, this.getRootNode().getChildNodes());
      return DomNodeHelper.getTextNodeValue(node);
   }

   public String getInputOutputTypeFile() throws Exception
   {
      Node node = DomSearchHelper.getNode(
         InputOutputTypeData.getInstance().FILE, this.getRootNode().getChildNodes());
      return DomNodeHelper.getTextNodeValue(node);
   }
   
   public String getImportUriPath() throws Exception
   {
      Node node = DomSearchHelper.getNode(
         XslData.getInstance().ROOT_IMPORT_URI, this.getRootNode().getChildNodes());
      return DomNodeHelper.getTextNodeValue(node);
   }

   /*
   public void setImportUriPath(String importUriPath)
   {
      this.importUriPath = importUriPath;
   }   
   */

   public String toString()
   {
      try
      {
         if(this.document != null)
         {
            return DomDocumentHelper.toString(this.document);
         }
         else
         {
            return null;
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("DOM Document error", this, commonStrings.TOSTRING);
         }
         return null;
      }
   }
   
   /*
   public TransformInfoObjectConfigGenerator getGeneratorNode() throws Exception
   {
      Node templateNode =
      this.document.getElementsByTagName(TemplateData.NAME).item(0);
   }
    */
   
   /*
   public void update(TransformInfoInterface transformInfoInterface, String newMapping) throws Exception
   {
      this.update(transformInfoInterface, transformInfoInterface.getName(), newMapping);
   }
    */
   
   /*
   public void update(TransformInfoObjectConfigComponentNode transformInfoObjectConfigComponent, String newTransformInfoName) throws Exception
   {
      Node objectConfigNode =
      this.document.getElementsByTagName(TransformInfoObjectConfigData.NAME).item(0);
    
      NodeList viewNodeList = objectConfigNode.getChildNodes();
      int numberOfViews = viewNodeList.getLength();
    
      for(int index = 0; index < numberOfViews; index++)
      {
         Node viewNode = viewNodeList.item(index);
         NamedNodeMap viewAttributes = viewNode.getAttributes();
         Attr attrNode = (Attr) viewAttributes.getNamedItem(TransformInfoData.NAME);
         Attr mappedNameAttrNode =
         (Attr) viewAttributes.getNamedItem(TransformInfoObjectConfigComponentNodeData.MAPPEDNAME);
         if(transformInfoObjectConfigComponent.getName().compareTo(attrNode.getValue())==0)
         {
            attrNode.setValue(newTransformInfoName);
            return;
         }
      }
      throw new Exception("View Does Not Exist in TransformInfoObjectConfig");
   }
    */
   
   /*
   public void update(TransformInfoInterface transformInfoInterface, String newTransformInfoName, String newMapping) throws Exception
   {
      Node objectConfigNode =
      this.document.getElementsByTagName(TransformInfoObjectConfigData.NAME).item(0);
    
      NodeList viewNodeList = objectConfigNode.getChildNodes();
      int numberOfViews = viewNodeList.getLength();
    
      for(int index = 0; index < numberOfViews; index++)
      {
         Node viewNode = viewNodeList.item(index);
         NamedNodeMap viewAttributes = viewNode.getAttributes();
         Attr attrNode = (Attr) viewAttributes.getNamedItem(TransformInfoData.NAME);
         Attr mappedNameAttrNode =
         (Attr) viewAttributes.getNamedItem(TransformInfoObjectConfigComponentNodeData.MAPPEDNAME);
         if(transformInfoInterface.getName().compareTo(attrNode.getValue())==0)
         {
            attrNode.setValue(newTransformInfoName);
            mappedNameAttrNode.setValue(newMapping);
            return;
         }
      }
      throw new Exception("View Does Not Exist in TransformInfoObjectConfig");
   }
    
   public void delete(TransformInfoInterface transformInfoInterface, String newMapping) throws Exception
   {
      Node objectConfigNode =
      this.document.getElementsByTagName(TransformInfoObjectConfigData.NAME).item(0);
    
      NodeList viewNodeList = objectConfigNode.getChildNodes();
      int numberOfViews = viewNodeList.getLength();
    
      for(int index = 0; index < numberOfViews; index++)
      {
         Node viewNode = viewNodeList.item(index);
         NamedNodeMap viewAttributes = viewNode.getAttributes();
         Attr attrNode = (Attr) viewAttributes.getNamedItem(TransformInfoData.NAME);
         Attr mappedNameAttrNode =
         (Attr) viewAttributes.getNamedItem(TransformInfoObjectConfigComponentNodeData.MAPPEDNAME);
         if(transformInfoInterface.getName().compareTo(attrNode.getValue())==0)
         {
            objectConfigNode.removeChild(viewNode);
         }
      }
      throw new Exception("Component Does Not Exist in TransformInfoObjectConfig");
   }
    
   public void insert(TransformInfoInterface transformInfoInterface, String newMapping) throws Exception
   {
      Node objectConfigNode =
      this.document.getElementsByTagName(TransformInfoObjectConfigData.NAME).item(0);
    
      NodeList viewNodeList = objectConfigNode.getChildNodes();
      int numberOfViews = viewNodeList.getLength();
    
      for(int index = 0; index < numberOfViews; index++)
      {
         Node viewNode = viewNodeList.item(index);
         NamedNodeMap viewAttributes = viewNode.getAttributes();
         Attr attrNode = (Attr) viewAttributes.getNamedItem(TransformInfoData.NAME);
         Attr mappedNameAttrNode =
         (Attr) viewAttributes.getNamedItem(TransformInfoObjectConfigComponentNodeData.MAPPEDNAME);
         if(transformInfoInterface.getName().compareTo(attrNode.getValue())==0)
         {
            throw new Exception("Component Already Exist in TransformInfoObjectConfig");
         }
      }
      objectConfigNode.appendChild(
      new TransformInfoObjectConfigComponentNode(transformInfoInterface).toXmlNode(this.document));
   }
    */
}
