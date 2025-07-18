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
package views.business.context.modules.storefront.customizer.bodies.generic.title;


import org.allbinary.business.page.PageData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.StoreTransformer;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.template.customizer.bodies.title.TitleBodyValidation;
import org.allbinary.logic.visual.transform.template.util.TransformTemplateCustomizerUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class TitleBodyCustomizerView extends HttpStoreComponentView 
   implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   protected TitleBodyValidation titleBody;
   
   public TitleBodyCustomizerView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public void addDomNodeInterfaces() throws Exception
   {
      this.addDomNodeInterface((DomNodeInterface) this.titleBody);
      this.addDomNodeInterface((DomNodeInterface) this);
   }      
   
   private static final String NAME = "None";
   
   public Node toXmlNode(Document document) throws Exception
   {
         String pageName = 
        	 TransformTemplateCustomizerUtil.getInstance().getPageNameHack(
            this.getTransformInfoInterface().getName(),
            this.getWeblisketSession().getStoreName());
         
         if(StringValidationUtil.getInstance().isEmpty(pageName))
         {
        	 pageName = NAME;
         }
         
         return ModDomHelper.createNameValueNodes(document, PageData.getInstance().NAME, pageName);
   }
   
   public String view() throws Exception
   {
      try
      {
         String success = DomDocumentHelper.toString(this.getDoc());

         String result =
            new StoreTransformer(this.abeClientInformation, this.getTransformInfoInterface()).translate(success);
         return StringEscapeUtils.unescapeHtml3(result);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         throw e;
      }
   }
}
