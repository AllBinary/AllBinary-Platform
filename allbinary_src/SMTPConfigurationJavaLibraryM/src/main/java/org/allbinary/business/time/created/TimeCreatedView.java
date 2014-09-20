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
package org.allbinary.business.time.created;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.EntryData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class TimeCreatedView implements DomNodeInterface
{
   private TimeCreated timeCreated;

   public TimeCreatedView(TimeCreated timeCreated)
   {
      this.setTimeCreated(timeCreated);
   }
   
   public TimeCreatedView(Node node)
   {
      this.setTimeCreated(new TimeCreated(DomNodeHelper.getTextNodeValue(node)));
   }

   public Node toXmlNode(Document document) throws Exception
   {
      return ModDomHelper.createTextNode(
         document, EntryData.getInstance().TIMECREATED, this.getTimeCreated().toString());
   }

    public TimeCreated getTimeCreated()
    {
        return timeCreated;
    }

    public void setTimeCreated(TimeCreated timeCreated)
    {
        this.timeCreated = timeCreated;
    }
}
