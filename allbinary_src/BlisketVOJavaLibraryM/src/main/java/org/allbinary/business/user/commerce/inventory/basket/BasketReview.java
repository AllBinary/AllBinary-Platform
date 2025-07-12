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

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.allbinary.business.user.commerce.inventory.item.Item;
import org.allbinary.business.user.commerce.inventory.item.ItemView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

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
      final Object[] itemArray = items.toArray();
      final int size = itemArray.length;
      for (int index = 0; index < size; index++)       
      {
         Item item = (Item) itemArray[index];
         if(item.getId().compareTo(id) == 0)
         {
            items.remove(item);
         }
      }
   }
   
   public void adjustItem(String id, String num)
   {
      final Object[] itemArray = items.toArray();
      final int size = itemArray.length;
      for (int index = 0; index < size; index++)       
      {
         Item item = (Item) itemArray[index];
         if(item.getId().compareTo(id) == 0)
         {
            item.setNumber(num);
         }
      }      
   }
   
   public String getTotalWeight()
   {
      float weightFloat = 0;
       
      final Object[] itemArray = items.toArray();
      final int size = itemArray.length;
      for (int index = 0; index < size; index++)       
      {
         Item item = (Item) itemArray[index];
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
      final Object[] itemArray = items.toArray();
      final int size = itemArray.length;
      for (int index = 0; index < size; index++)       
      {
         Item item = (Item) itemArray[index];
         idSet.add(item.getId());
      }
      return (Set) idSet;
   }
      
   public Integer getNumberOf(String id)
   {
      final Object[] itemArray = items.toArray();
      final int size = itemArray.length;
      for (int index = 0; index < size; index++)       
      {
         Item item = (Item) itemArray[index];
         if(item.getId().compareTo(id) == 0) 
         {
            return new Integer(item.getNumber());
         }
      }      
      return new Integer(0);
   }
      
   public Node toXmlNode(Document document) throws Exception
   {
      Node node = document.createElement(BasketData.BASKET);
      
      final Object[] itemArray = items.toArray();
      final int size = itemArray.length;
      for (int index = 0; index < size; index++)       
      {
         Item item = (Item) itemArray[index];
         node.appendChild(new ItemView(item, new Vector()).toXmlNode(document));
      }
      return node;
   }   
}
