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
package org.allbinary.business.category;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class CategoryUtil
{
   private CategoryUtil()
   {
   }

   /*
   public static synchronized String getPath(String categoryPath)
   {
      int beginIndex = categoryPath.lastIndexOf(FileData.SEPARATOR);
      if(beginIndex < 0) return null;

      String path = categoryPath.substring(0, beginIndex);
      
      return path;
   }
   */
   
   public static synchronized int getPathLevel(AbPath categoryPath)
   {
      int count = StringUtils.countMatches(
    		  categoryPath.toString(), 
    		  AbPathData.getInstance().SEPARATOR);

      return count;
   }
   
   public static synchronized String getNameFromNode(Node node)
   {
      NamedNodeMap attributes = node.getAttributes();
      Attr attrNode = (Attr) attributes.getNamedItem(CategoryData.getInstance().LABEL);
      if(attrNode!=null)
      {
         return attrNode.getValue();
      }
      else
      {
         //This is for flash request
         Node labelNode = 
            DomSearchHelper.getNodeNoThrow(CategoryData.getInstance().LABEL, node.getChildNodes());

         if(labelNode!=null)
         {
            return DomNodeHelper.getTextNodeValue(labelNode);
         }
      }
      return StringUtil.getInstance().EMPTY_STRING;
   }
}
