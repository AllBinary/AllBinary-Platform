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
package org.allbinary.business.user.commerce.inventory.basket;

import org.allbinary.business.user.commerce.inventory.basket.BasketData;
import org.allbinary.business.user.commerce.inventory.item.Item;
import org.allbinary.business.user.commerce.inventory.item.ItemView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class BasketReview
{
   Vector items = new Vector();
   
   public BasketReview()
   {
   }
   
   public Boolean isValid()
   {
      //should run through all items and check validity
      return Boolean.TRUE;
   }
   
   public void addItem(Item item)
   {      
      items.add(item);
   }
   
   public Vector getItems()
   {
      return items;
   }
   
   public void removeItem(String id)
   {
      Iterator iter = items.iterator();
      while(iter.hasNext())
      {
         Item item = (Item) iter.next();
         if(item.getId().compareTo(id) == 0)
         {
            items.remove(item);
         }
      }
   }
   
   public void adjustItem(String id, String num)
   {
      Iterator iter = items.iterator();
      while(iter.hasNext())
      {
         Item item = (Item) iter.next();
         if(item.getId().compareTo(id) == 0)
         {
            item.setNumber(num);
         }
      }      
   }
   
   public String getTotalWeight()
   {
      float weightFloat = 0;
      Iterator iter = items.iterator();
      while(iter.hasNext())
      {
         Item item = (Item) iter.next();
         weightFloat += new Float(item.getWeight()).floatValue();
      }
      return new Float(weightFloat).toString();
   }
   
   public Integer getNumberOfItems()
   {
      return new Integer(items.size());
   }
   
   public Set getIds()
   {
      HashSet idSet = new HashSet();
      Iterator iter = items.iterator();
      while(iter.hasNext())
      {
         Item item = (Item) iter.next();
         idSet.add(item.getId());
      }
      return (Set) idSet;
   }
      
   public Integer getNumberOf(String id)
   {
      Iterator iter = items.iterator();
      while(iter.hasNext())
      {
         Item item = (Item) iter.next();
         if(item.getId().compareTo(id) == 0) 
         {
            return new Integer(item.getNumber());
         }
      }      
      return new Integer(0);
   }
      
   public Node toXmlNode(Document document) throws Exception
   {
      Iterator iter = this.items.iterator();
      
      Node node = document.createElement(BasketData.BASKET);
      
      while(iter.hasNext())
      {
         Item item = (Item) iter.next();         
         node.appendChild(new ItemView(item, new Vector()).toXmlNode(document));
      }
      return node;
   }   
}
