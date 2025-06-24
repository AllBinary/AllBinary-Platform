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
package org.allbinary.logic.control.search;

import org.allbinary.logic.control.search.SearchData;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.data.tree.dom.ToDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import java.util.Set;

public class SearchParams
{
   private HashMap columnsAndSearchValues;
   
   private String order;
   private String sortBy;
   private String listLength;
   private String startPage;
   private String endPage;
         
   public SearchParams(HttpServletRequest request)
   {
      this.columnsAndSearchValues = new HashMap();
      
      this.setLength(request.getParameter(SearchData.LENGTH));
      this.setOrder(request.getParameter(SearchData.ORDER));
      String page = request.getParameter(SearchData.PAGE);
      this.setStartPage(page);
      this.setEndPage(page);
      this.setSortBy(request.getParameter(SearchData.SORTBY));
      //this.setType(request.getParameter(BasicItemData.TYPE));
      
      int index = 0;
      String columnName = request.getParameter(SearchData.COLUMNNAME + "[0]");
      String columnValue = request.getParameter(SearchData.COLUMNVALUE + "[0]");
      
      while(columnName != null)
      {
         this.add(columnName,columnValue);
         index++;
         columnName = request.getParameter(SearchData.COLUMNNAME + "[" + index + "]");
         columnValue = request.getParameter(SearchData.COLUMNVALUE + "[" + index + "]");         
      }
      
   }
   
   public void add(String column, String value)
   {
      this.columnsAndSearchValues.put(column, value);
   }
      
   public void setOrder(String value)
   {
      this.order = value;
   }
   
   public void setSortBy(String value)
   {
      this.sortBy = value;
   }
   
   public void setLength(String value)
   {
      this.listLength = value;
   }
   
   public void setStartPage(String value)
   {
      this.startPage = value;
   }
   
   public void setEndPage(String value)
   {
      this.endPage = value;
   }
      
   /*
   public void setType(String value)
   {
      this.type = value;
   }
   */
   
   public HashMap get()
   {
      return this.columnsAndSearchValues;
   }   
   
   public String getOrder()
   {
      return this.order;
   }
   
   public String getSortBy()
   {
      return this.sortBy;
   }
   
   public String getLength()
   {
      return this.listLength;
   }
   
   public Integer getLengthInt()
   {
      if(this.listLength!=null)
      {
         return new Integer(this.listLength);
      }
      else
         return new Integer(0);
   }
   
   public String getStartPage()
   {
      return this.startPage;
   }
   
   public String getEndPage()
   {
      return this.endPage;
   }
   
   public Integer getStartPageInt()
   {
      if(this.startPage!=null)
      {
         return new Integer(this.startPage);
      }
      else
         return new Integer(0);
   }
   
   public Integer getEndPageInt()
   {
      if(this.endPage!=null)
      {
         return new Integer(this.endPage);
      }
      else
         return new Integer(0);
   }
      
   /*
   public String getType()
   {
      return this.type;
   }*/
   
   public Node getParamsNode(Document document) throws Exception
   {      
      Node paramsNode = 
         ModDomHelper.createNameValueNodes(document, SearchData.PARAMS, SearchData.PARAMS);
      
      paramsNode.appendChild(this.getFieldsNode(document));
      paramsNode.appendChild(this.getOrderNode(document));
      paramsNode.appendChild(this.getSortByNode(document));
      paramsNode.appendChild(this.getLengthNode(document));
                        
      //searchParams.getCategoryNode(document);
      //searchParams.getTypeNode(document);
      
      return paramsNode;
   }

   private Node getFieldsNode(Document document) throws Exception
   {
      Node fieldsNode = 
         ModDomHelper.createNameValueNodes(document, SearchData.FIELDS, 
            new Integer(columnsAndSearchValues.size()).toString());

      Set set = this.columnsAndSearchValues.keySet();
      
      final Object[] searchValueArray = set.toArray();
      final int size = searchValueArray.length;
      for (int index = 0; index < size; index++)
      {
         String key = (String) searchValueArray[index];
         String searchValue = (String) this.columnsAndSearchValues.get(key);

         fieldsNode.appendChild(ModDomHelper.createNameValueNodes(document, SearchData.FIELD, key, ToDomHelper.convertNull(searchValue)));
      }
      return fieldsNode;
   }

   private Node getOrderNode(Document document) throws Exception
   {
      return ModDomHelper.createNameValueNodes(document, SearchData.ORDER, ToDomHelper.convertNull(this.getOrder()));
   }

   private Node getSortByNode(Document document) throws Exception
   {
      return ModDomHelper.createNameValueNodes(document, SearchData.SORTBY, ToDomHelper.convertNull(this.getSortBy()));
   }

   private Node getLengthNode(Document document) throws Exception
   {
      return ModDomHelper.createNameValueNodes(document, SearchData.LENGTH, ToDomHelper.convertNull(this.getLength()));
   }
      
   /*
   private Node getTypeNode(Document document)
   {
      return ModDomHelper.createNameValueNodes(document, SearchData.TYPE, this.getType());
   }
   */
}
