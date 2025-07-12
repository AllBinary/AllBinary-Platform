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

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.io.path.AbFilePath;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class ContextUriResolver implements URIResolver
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private BasicUriResolver basicURIResolver;
   
   public ContextUriResolver(BasicUriResolver basicURIResolver)
   {
      this.basicURIResolver = basicURIResolver;
   }
   
   public Source resolve(String href, String base) throws TransformerException
   {
      try
      {
    	  final StringMaker stringBuffer = new StringMaker();
    	  
    	  stringBuffer.append(URLGLOBALS.getMainPath());
    	  stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
    	  stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().INSTALLPATH);
    	  stringBuffer.append(AbPathData.getInstance().SEPARATOR);
    	  stringBuffer.append(href);

         final AbPath fileAbPath = new AbFilePath(stringBuffer.toString());
         
         if(  org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
              org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XMLLOGGING))
         {
        	 stringBuffer.delete(0, stringBuffer.length());
        	 
        	 stringBuffer.append("attempt to use xsl:import: href=");
        	 stringBuffer.append(href);
        	 stringBuffer.append("\nBase= ");
        	 stringBuffer.append(base);
        	 stringBuffer.append("\nNew path= ");
        	 stringBuffer.append(fileAbPath.toString());
        	 stringBuffer.append("\nNote: ");
        	 stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
        	 stringBuffer.append(" is a urlglobal");
        	 stringBuffer.append("\nRequired Extension: ");
        	 stringBuffer.append(this.basicURIResolver.getExtension());
             
            logUtil.put(stringBuffer.toString(), this, "resolve");
         }
         
         return new StreamSource(new CryptFileReader(
            TransformInfoTemplateData.getInstance().UNCRYPTED_EXTENSION,
            TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION
            ).getInputStream(fileAbPath));
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
         return URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().INSTALLPATH + "/{import url}";
      }
      catch(Exception e)
      {
         //Log Error
         return "ContextUriResolver - Does not work without webapp path should be changed";
      }
   }
}
