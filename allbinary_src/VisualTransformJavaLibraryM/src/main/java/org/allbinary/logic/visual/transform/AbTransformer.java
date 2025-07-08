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
package org.allbinary.logic.visual.transform;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.XslHelper;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorFactory;
import org.allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGenerator;

public class AbTransformer implements BasicTransformerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private TransformInfoInterface transformInfoInterface;
   private InputStream inputStream;
   private URIResolver uriResolver;

   //Constructor for template/xsl imports for an existing transform to get the imports source stream
   public AbTransformer(TransformInfoInterface transformInfoInterface) throws Exception
   {
       this.transformInfoInterface = transformInfoInterface;
   }
   
   public TransformInfoInterface getTransformInfoInterface()
   {
      return this.transformInfoInterface;
   }

   public void setInputStream(InputStream inputStream)
   {
      this.inputStream = inputStream;
   }

   public void setURIResolver(URIResolver uriResolver)
   {
      this.uriResolver = uriResolver;
   }

   public InputStream getInputStream()
   {
      return this.inputStream;
   }
   
   public URIResolver getURIResolver()
   {
      return this.uriResolver;
   }
   
   public StreamSource getStreamSource()
   {
      return new StreamSource(this.getInputStream());
   }

   public String translate(String xmlDocumentStr) throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XMLLOGGING))
         {
            logUtil.put("xml: \n" + xmlDocumentStr, this, "translate(String xmlDocumentStr)");
         }

         if(this.getURIResolver() == null)
         {
             throw new Exception("No URIResolver");
         }

         String result = XslHelper.getInstance().translate(this.getURIResolver(),
            new StreamSource(this.getInputStream()),
            new StreamSource(new StringBufferInputStream(xmlDocumentStr)));

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGING))
         {
            logUtil.put("translated xml: " + result, this, "translate(String xmlDocumentStr)");
         }

         TransformInfoObjectConfigGenerator 
            transformInfoObjectConfigGeneratorInterface =
               TransformInfoObjectConfigGeneratorFactory.getInstance().getInstance(
                  this.getTransformInfoInterface());

         return transformInfoObjectConfigGeneratorInterface.process(result);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put(this.transformInfoInterface.log(), this, "translate(document)", e);
         }
         throw e;
      }
   }
}