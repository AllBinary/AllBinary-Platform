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
package org.allbinary.business.user;

import java.util.HashMap;

import org.allbinary.business.entry.EntryData;
import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.string.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class UserDomNode implements DomNodeInterface
{
   private TableMappingInterface dataMappingInterface;
   
   public UserDomNode(TableMappingInterface dataMappingInterface)
   {
      this.dataMappingInterface = dataMappingInterface;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
	  final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
	   
      HashMap hashMap = dataMappingInterface.toHashMap();
      hashMap.put(EntryData.getInstance().LASTMODIFIED, EMPTY_STRING);
      hashMap.put(EntryData.getInstance().ENCRYPTION, EMPTY_STRING);
      hashMap.put(UserData.PASSWORD, EMPTY_STRING);

      return ModDomHelper.createNameValueNodes(document, UserData.NAME, hashMap);
   }
   
   public Document toXmlDoc() throws Exception
   {
      return null;
   }
      
   public String view() throws Exception
   {
      return null;
   }
   
}
