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
package allbinary.logic.visual.dhtml.html.head.meta;

public class HtmlMetaAttributeData
{
   private int id;
   private String name;
   
   public HtmlMetaAttributeData(int id, String value)
   {
      this.name = value;
      this.id = id;
   }

   public boolean equals(HtmlMetaAttributeData htmlMetaAttributeData)
   {
      if(htmlMetaAttributeData.id == this.id)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public String toString()
   {
      return this.name;
   }
}
