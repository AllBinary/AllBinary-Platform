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
package org.allbinary.logic.visual.dhtml.style.color;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonPhoneStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ColorsView implements DomNodeInterface
{
    private final CommonPhoneStrings commonPhoneStrings = CommonPhoneStrings.getInstance();
    
   private final int HEXRADIX = 16;
   private final int MAX = 255;
   final int delta = 20;
   
   public ColorsView()
   {
   }
   
   public Node getColorNode(Document document, 
      int redIndex, int greenIndex, int blueIndex)
      throws Exception
   {
                     StringMaker hexColorStringBuffer = new StringMaker();

               if(redIndex < this.HEXRADIX)
               {
                  hexColorStringBuffer.append(this.commonPhoneStrings.ZERO);
               }
               
               hexColorStringBuffer.append(
                  Integer.toString(redIndex, HEXRADIX));
               
               if(greenIndex < HEXRADIX)
               {
                  hexColorStringBuffer.append(this.commonPhoneStrings.ZERO);
               }
               
               hexColorStringBuffer.append(
                  Integer.toString(greenIndex, HEXRADIX));
               
               if(blueIndex < HEXRADIX)
               {
                  hexColorStringBuffer.append(this.commonPhoneStrings.ZERO);
               }
               
               hexColorStringBuffer.append(
                  Integer.toString(blueIndex, HEXRADIX));
               
               Node colorNode = ModDomHelper.createNameValueNodes(
                  document, ColorData.getInstance().NAME, hexColorStringBuffer.toString());

               return colorNode;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      Node node = ModDomHelper.createNameValueNodes(
         document, ColorsData.getInstance().NAME, "RGB Colors In Hex");
      
      Node blackNode = ModDomHelper.createNameValueNodes(
         document, ColorData.getInstance().NAME, "000000");
      node.appendChild(blackNode);
      
      //Gray Scale
      for(int index = 0; index < this.MAX; index+=this.delta)
      {
         StringMaker hexColorStringBuffer = new StringMaker();
         
         if(index < this.HEXRADIX)
         {
            hexColorStringBuffer.append(this.commonPhoneStrings.ZERO);
         }
         
         hexColorStringBuffer.append(
            Integer.toString(index, HEXRADIX));
         
         if(index < HEXRADIX)
         {
            hexColorStringBuffer.append(this.commonPhoneStrings.ZERO);
         }
         
         hexColorStringBuffer.append(
            Integer.toString(index, HEXRADIX));
         
         if(index < HEXRADIX)
         {
            hexColorStringBuffer.append(this.commonPhoneStrings.ZERO);
         }
         
         hexColorStringBuffer.append(
            Integer.toString(index, HEXRADIX));
         
         Node colorNode = ModDomHelper.createNameValueNodes(
            document, ColorData.getInstance().NAME, hexColorStringBuffer.toString());
         
         node.appendChild(colorNode);
      }
      
      Node whiteNode = ModDomHelper.createNameValueNodes(
         document, ColorData.getInstance().NAME, "FFFFFF");
      node.appendChild(whiteNode);

      //Blue
      for(int blueIndex = this.MAX/2; blueIndex < this.MAX; blueIndex+=this.delta)
      {
         for(int greenIndex = 0; greenIndex < this.MAX/2; greenIndex+=this.delta)
         {
            for(int redIndex = 0; redIndex < this.MAX/2; redIndex+=this.delta)
            {
               node.appendChild(this.getColorNode(document, 
                  redIndex, greenIndex, blueIndex));
            }
         }
      }
      
      //Red
      for(int redIndex = this.MAX/2; redIndex < this.MAX; redIndex+=this.delta)
      {
         for(int greenIndex = 0; greenIndex < this.MAX/2; greenIndex+=this.delta)
         {
            for(int blueIndex = 0; blueIndex < this.MAX/2; blueIndex+=this.delta)
            {
               node.appendChild(this.getColorNode(document, 
                  redIndex, greenIndex, blueIndex));
            }
         }
      }
      
      //Green
      for(int greenIndex = this.MAX/2; greenIndex < this.MAX; greenIndex+=this.delta)
      {
         for(int redIndex = 0; redIndex < this.MAX/2; redIndex+=this.delta)
         {
            for(int blueIndex = 0; blueIndex < this.MAX/2; blueIndex+=this.delta)
            {
               node.appendChild(this.getColorNode(document, 
                  redIndex, greenIndex, blueIndex));
            }
         }
      }
      
      //All
      for(int greenIndex = 0; greenIndex < this.MAX; greenIndex+=2*this.delta)
      {
         for(int redIndex = 0; redIndex < this.MAX; redIndex+=2*this.delta)
         {
            for(int blueIndex = 0; blueIndex < this.MAX; blueIndex+=2*this.delta)
            {
               node.appendChild(this.getColorNode(document, 
                  redIndex, greenIndex, blueIndex));
            }
         }
      }

      return node;
   }
}