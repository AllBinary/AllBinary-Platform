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


import abcs.logic.basic.io.LineReader;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import allbinary.business.user.address.StreetAddressData;

import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;


public class StatesView implements DomNodeInterface
{   
   public StatesView()
   { 
   }
   
   public Node toXmlNode(Document document)
   {
      try
      {
         Node node = document.createElement(StreetAddressData.STATES);

         String statesFile 
            = abcs.globals.URLGLOBALS.getMainPath() + 
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"toXmlNode",e));
         }
         //throw e;
         return null;
      }
   }
   
}
