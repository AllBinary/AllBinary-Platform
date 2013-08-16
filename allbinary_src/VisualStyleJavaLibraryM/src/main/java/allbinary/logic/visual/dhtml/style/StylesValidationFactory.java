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
package allbinary.logic.visual.dhtml.style;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.http.request.NameSpaceRequestParamData;
import allbinary.logic.visual.dhtml.style.css.CssElementData;
import allbinary.logic.visual.dhtml.style.css.CssElementsValidationFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Vector;

public class StylesValidationFactory
{
	private static final StylesValidationFactory instance = new StylesValidationFactory();
	
   private StylesValidationFactory()
   {
   }

   public static Vector getInstance(Document document) throws Exception
   {
      //Vector styles = new Vector();

      //Get all styles nodes
      NodeList nodeList = document.getElementsByTagName(StylesData.getInstance().NAME);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Style Present: " + DomDocumentHelper.toString(document),
            instance, "getInstance()"));
      }
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Styles Present: " + nodeList.getLength(),
            instance, "getInstance()"));
      }
      
      for(int index = 0; index < nodeList.getLength(); index++)
      {
         Node stylesNode = nodeList.item(index);
         //Get all style nodes
         Vector styleNodeList = 
            DomSearchHelper.getAllNodes(
               StyleData.getInstance().NAME,stylesNode.getChildNodes());

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Number Of Style Nodes Present: " +
               styleNodeList.size(), 
               instance, "getInstance()"));
         }
         
         for(int styleNodesIndex = 0; styleNodesIndex < styleNodeList.size(); styleNodesIndex++)
         {
            //Nodes with StyleData.NAME
            Node styleNode = (Node) styleNodeList.get(styleNodesIndex);

            //Nodes with CssElementData.NAME
            Vector cssElementStyleNodeList = 
               DomSearchHelper.getAllNodes(
                  CssElementData.getInstance().NAME,styleNode.getChildNodes());

            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
               abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("Number Of Element Nodes Present: " +
                  cssElementStyleNodeList.size(), 
                  instance, "getInstance()"));
            }
            
            return CssElementsValidationFactory.getInstance(cssElementStyleNodeList);
         }
      }
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("No Style Present", instance, "getInstance()"));
      }
      
      return new Vector();
   }

   public static Vector getInstance(HashMap hashMap) throws Exception
   {
      //Vector styles = new Vector();
      
      Document stylesDocument = 
         (Document) hashMap.get(NameSpaceRequestParamData.DOCUMENT);

      return StylesValidationFactory.getInstance(stylesDocument);
   }
}
