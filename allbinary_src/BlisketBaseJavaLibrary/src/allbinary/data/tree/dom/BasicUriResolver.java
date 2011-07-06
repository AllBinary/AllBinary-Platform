/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/29/02
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.data.tree.dom;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbFilePath;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;

import allbinary.logic.control.crypt.file.CryptFileReader;
import allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class BasicUriResolver implements URIResolver
{
   private String extension;
   
   //private TransformInfoInterface parentTransformInfoInterface;
   
   //TransformInfoInterface parentTransformInfoInterface,
   public BasicUriResolver(String extension)
   {
      //this.parentTransformInfoInterface = parentTransformInfoInterface;
      this.extension = extension;
   }
   
   public String getExtension()
   {
      return this.extension;
   }
   
   public Source resolve(String href, String base) throws TransformerException
   {
      try
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append(URLGLOBALS.getMainPath());
    	  stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
    	  stringBuffer.append(href);
    	  
         AbPath abPath = (AbPath) new AbFilePath(stringBuffer.toString());

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.XMLLOGGING))
         {
        	 stringBuffer.delete(0, stringBuffer.length());
        	 
        	 stringBuffer.append("attempt to use xsl:import: href=");
        	 stringBuffer.append(href);
        	 stringBuffer.append("\nBase= ");
        	 stringBuffer.append(base);
        	 stringBuffer.append("\nNew path= ");
        	 stringBuffer.append(abPath.toString());
        	 stringBuffer.append("\nNote: ");
        	 stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
        	 stringBuffer.append(" is a urlglobal");
        	 stringBuffer.append("\nRequired Extension: ");
        	 stringBuffer.append(extension);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "resolve"));
         }

         return new StreamSource(new CryptFileReader(
            TransformInfoTemplateData.getInstance().UNCRYPTED_EXTENSION,
            TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION).getInputStream(abPath));
      }
      catch(TransformerException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         throw new TransformerException(e);
      }
   }
   
   public String toString()
   {
      try
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append(URLGLOBALS.getMainPath());
    	  stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
    	  stringBuffer.append("/{import url}");
    	  
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         //Log Error
         return "BasicUriResolver - Does not work without webapp path should be changed";
      }
   }
}
