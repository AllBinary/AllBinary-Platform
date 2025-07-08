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
package views.generic.address;


import org.w3c.dom.Document;
import org.w3c.dom.Node;


import org.allbinary.logic.io.LineReader;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.address.StreetAddressData;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.string.CommonStrings;


public class StatesView implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private final CommonStrings commonStrings = CommonStrings.getInstance();
    
   public StatesView()
   { 
   }
   
   public Node toXmlNode(Document document)
   {
      try
      {
         Node node = document.createElement(StreetAddressData.STATES);

         String statesFile 
            = URLGLOBALS.getMainPath() + 
              FREEBLISKET_PATH_GLOBALS.getInstance().LINEDATAPATH +
              "states.txt";      

         LineReader lineReader = new LineReader(statesFile);

         while(lineReader.hasNext())
         {
            String option = lineReader.next();

            node.appendChild(ModDomHelper.createNameValueNodes(
               document, StreetAddressData.STATE, option));
         }
         
         return node;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            logUtil.put(this.commonStrings.FAILURE,this,"toXmlNode",e);
         }
         //throw e;
         return null;
      }
   }
   
}
