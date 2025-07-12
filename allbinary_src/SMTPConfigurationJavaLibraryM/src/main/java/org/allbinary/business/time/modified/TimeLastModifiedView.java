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
package org.allbinary.business.time.modified;

import org.allbinary.business.entry.EntryData;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class TimeLastModifiedView implements DomNodeInterface
{
   private TimeLastModified timeLastModified;

   public TimeLastModifiedView(TimeLastModified timeLastModified)
   {
      this.setTimeLastModified(timeLastModified);
   }
   
   public TimeLastModifiedView(Node node)
   {
      this.setTimeLastModified(new TimeLastModified(DomNodeHelper.getTextNodeValue(node)));
   }

   public Node toXmlNode(Document document) throws Exception
   {
      return ModDomHelper.createTextNode(
         document, EntryData.getInstance().TIMECREATED, this.getTimeLastModified().toString());
   }

    public TimeLastModified getTimeLastModified()
    {
        return timeLastModified;
    }

    public void setTimeLastModified(TimeLastModified timeLastModified)
    {
        this.timeLastModified = timeLastModified;
    }
}
