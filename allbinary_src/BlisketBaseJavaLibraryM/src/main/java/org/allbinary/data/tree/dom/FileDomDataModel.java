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
package org.allbinary.data.tree.dom;

import java.util.HashMap;

import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.logic.io.file.AbFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class FileDomDataModel
   implements DomNodeInterface
{
   private AbFile file;

   public FileDomDataModel(AbFile file)
   {
      this.file = file;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      TableMappingInterface tableMappingInterface = 
         (TableMappingInterface) new FileTableMapping(this.file);
      HashMap hashMap = tableMappingInterface.toHashMap();

      //)createNameValueNodes(
      return ModDomHelper.createNodeWithValueNodes(document, "file", hashMap);
   }
}
