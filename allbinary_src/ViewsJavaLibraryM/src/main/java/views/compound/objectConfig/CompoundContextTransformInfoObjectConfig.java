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
package views.compound.objectConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Vector;

import org.allbinary.data.tree.dom.document.DocumentToNode;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.TransformFactory;
import org.allbinary.logic.visual.transform.TransformInterface;
import org.allbinary.logic.visual.transform.info.RootTransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoDomNode;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.GenericStoreTransformInfoObjectConfig;
import org.allbinary.string.CommonSeps;
import org.w3c.dom.Document;

public class CompoundContextTransformInfoObjectConfig
    extends GenericStoreTransformInfoObjectConfig
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public CompoundContextTransformInfoObjectConfig(
       final AbeClientInformationInterface abeClientInformation,
       TransformInfoInterface transformInfoInterface)
       throws Exception
   {
      super(abeClientInformation,  transformInfoInterface);
      //this.setDocument(this.generate(this.getDocument()));
   }

   public CompoundContextTransformInfoObjectConfig(
       final AbeClientInformationInterface abeClientInformation,
       TransformInfoInterface transformInfoInterface, Document document)
       throws Exception
   {
      super(abeClientInformation, transformInfoInterface, document);
      this.setDocument(this.generate(this.toXmlDoc()));
   }

   public CompoundContextTransformInfoObjectConfig(
       final AbeClientInformationInterface abeClientInformation,
       TransformInfoInterface transformInfoInterface, String name, String type)
       throws Exception
   {
      super(abeClientInformation, transformInfoInterface, name, type);
      this.setDocument(this.generate(this.toXmlDoc()));
   }

   //returns the result of a compound view
   public String get() throws Exception
   {
      String storeName = this.getTransformInfoInterface().getStoreName();
      
      //Template will force compound template to use an override template like preview
      //This does not fix situations where a preview page would be generated 
      //by itself unless it is using a objectconfig that specifies preview as the parent
      TransformInfoHttpInterface httpTransformInfoInterface = 
           (TransformInfoHttpInterface) this.getTransformInfoInterface();

      String templateNameOverride = StringUtil.getInstance().getInstance((String)
            httpTransformInfoInterface.getPropertiesHashMap().get(
               TransformInfoData.getInstance().PARTIAL));

      StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append(storeName);
      stringBuffer.append(templateNameOverride);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(RootTransformInfoData.NAME);
      
      //get parent template 
      TransformInterface rootComponentInterface = TransformFactory.getInstance().getInstance(
          abeClientInformation, stringBuffer.toString(), this.getTransformInfoInterface());

      Vector viewVector = this.getTransformDomNodes();

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put(
             "Root View Called with: " + viewVector.size(),
             this, "get(transformInfoInterface)");
      }

      String rootView = rootComponentInterface.view();

      final String startXMLHeader = "<xsl:text disable-output-escaping=\"yes\" ><![CDATA[";
      final String endXMLHeader = "]]></xsl:text>";

      //probably should be optimized
      final int size = viewVector.size();
      for(int index = 0; index < size; index++)
      {
         TransformInfoDomNode objectConfigTransformInfoDomNode = 
            (TransformInfoDomNode) viewVector.get(index);
         
         String templateKey = objectConfigTransformInfoDomNode.getReplaceKey();

         TransformInterface componentInterface = TransformFactory.getInstance().getInstance(
                abeClientInformation, objectConfigTransformInfoDomNode.getTransformInfoInterface().getName(), this.getTransformInfoInterface());

         String replacementViewString = componentInterface.view();
         
         replacementViewString =
        	 DocumentToNode.convertDocumentToNodeString(replacementViewString);

         stringBuffer.delete(0, stringBuffer.length());
         
         stringBuffer.append(startXMLHeader);
         stringBuffer.append(replacementViewString);
         stringBuffer.append(endXMLHeader);
         
         String templateValue = stringBuffer.toString();

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
        	 stringBuffer.delete(0, stringBuffer.length());
             
             stringBuffer.append("Replacing: ");
             stringBuffer.append(templateKey);
             stringBuffer.append(" with ");
             stringBuffer.append(templateValue);
        	 
            logUtil.put(stringBuffer.toString(), this,"get()");
         }
         
         Replace replace = new Replace(templateKey, templateValue);

         rootView = replace.all(rootView);
      }
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("End: Result of a compound view: " + rootView, this,"get()");
      }

      return rootView;
   }

   public InputStream createInputStream() throws Exception
   {
      byte[] completeTemplateViewBytes = this.get().getBytes();
      
      ByteArrayInputStream bais = 
         new ByteArrayInputStream(completeTemplateViewBytes);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("Template: " + new String(completeTemplateViewBytes).toString(),this,"createInputStream()");
      }
      
      return bais;
   }
}
