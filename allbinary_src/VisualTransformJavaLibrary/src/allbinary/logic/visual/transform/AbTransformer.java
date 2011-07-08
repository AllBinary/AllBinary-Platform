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
package allbinary.logic.visual.transform;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.XslHelper;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorFactory;
import allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorInterface;

public class AbTransformer implements BasicTransformerInterface
{
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.XMLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("xml: \n" + xmlDocumentStr, this, "translate(String xmlDocumentStr)"));
         }

         if(this.getURIResolver() == null)
         {
             throw new Exception("No URIResolver");
         }

         String result = XslHelper.translate(this.getURIResolver(),
            new StreamSource(this.getInputStream()),
            new StreamSource(new StringBufferInputStream(xmlDocumentStr)));

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.XSLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("translated xml: " + result, this, "translate(String xmlDocumentStr)"));
         }

         TransformInfoObjectConfigGeneratorInterface 
            transformInfoObjectConfigGeneratorInterface =
               TransformInfoObjectConfigGeneratorFactory.getInstance().getInstance(
                  this.getTransformInfoInterface());

         return transformInfoObjectConfigGeneratorInterface.process(result);
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.transformInfoInterface.log(), this, "translate(document)", e));
         }
         throw e;
      }
   }
}