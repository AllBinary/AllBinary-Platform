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
package views.admin.customizers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import views.business.context.modules.storefront.HttpStoreComponentView;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.globals.GLOBALS;
import org.allbinary.logic.control.sort.StringComparator;
import org.allbinary.logic.visual.transform.info.CustomizerTransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.template.customizer.TransformTemplateCustomizerData;
import org.allbinary.logic.visual.transform.template.customizer.TransformTemplateCustomizersData;
import org.allbinary.logic.visual.transform.template.customizer.bodies.BodyData;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;
import org.allbinary.logic.visual.transform.template.util.TransformTemplateCustomizerUtil;

public class CustomizersView extends HttpStoreComponentView implements DomNodeInterface
{
   protected Vector customizersVector;
   
   public CustomizersView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      //String storeName = ;
      this.customizersVector = 
    	  TransformInfoEntityBuilder.getInstance().getNames(this.getWeblisketSession().getStoreName());
   }
      
   public Node toXmlNode(Document document)
   {
      try
      {
         Node node = document.createElement(TransformTemplateCustomizersData.NAME);
         
         Iterator iter = customizersVector.iterator();
         
         Vector unsortedCustomizerViewVector = new Vector();
         
         while(iter.hasNext())
         {
            String viewName = (String) iter.next();

            if(viewName.indexOf(CustomizerTransformInfoData.NAME) > 0 && 
               viewName.indexOf(GLOBALS.EDIT) > 0 && 
               viewName.indexOf(BodyData.getInstance().VIEWNAMEKEY) > 0 &&
               viewName.indexOf(TitleData.getInstance().VIEWNAMEKEY) > 0)
               //TransformTemplateCustomizerData.EDITNAMEKEY
            {
               unsortedCustomizerViewVector.add(viewName);
            }
         }

         Object objectArray[] = (Object[]) unsortedCustomizerViewVector.toArray();
         
         //String customizerViews[] = (String[]) objectArray;
         Arrays.sort(objectArray, new StringComparator());
         for(int index = 0; index < objectArray.length; index++)
         {
            String viewName = (String) objectArray[index];

            Node viewNameNode = 
               ModDomHelper.createNameValueNodes(
                  document,TransformTemplateCustomizerData.NAME,viewName,
                  TransformTemplateCustomizerUtil.getInstance().getPageNameHack(
                     viewName,
                     this.getWeblisketSession().getStoreName()));
             //+ " " + TransformInfoObjectConfigData.BODY

            node.appendChild(viewNameNode);
         }
         
         return node;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "toXmlNode", e));
         }
         return null;
      }
   }

   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view Mini Basket";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
         }
         throw e;
      }
   }   
}
