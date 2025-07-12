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
import java.util.Vector;

import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileData;
import org.allbinary.logic.io.path.PathUtil;

public class FileTableMapping
   implements TableMappingInterface
{
   private AbFile file;
   
   public FileTableMapping(AbFile file)
   {
      this.file = file;
   }
   
   public Object getKey() throws Exception
   {
      return (Object) this.file.getPath();
   }
   
   //Used for inserting into database   
   public Vector toVector() throws Exception
   {
      final Vector filePropertyVector = new Vector();

      filePropertyVector.add(file.getAbsolutePath());
      filePropertyVector.add(file.getCanonicalPath());
      filePropertyVector.add(file.getName());
      filePropertyVector.add(file.getParent());
      filePropertyVector.add(file.getPath());

      return filePropertyVector;
   }

   //Used for updating viewinfo in database   
   public HashMap toHashMap() throws Exception
   {
      final HashMap filePropertyHashMap = new HashMap();

      final PathUtil pathUtil = PathUtil.getInstance();
      final String rootFileName = pathUtil.getNameFromPath(pathUtil.getWithoutExtension(file.getPath()));
      final FileData fileData = FileData.getInstance();
      
      filePropertyHashMap.put(fileData.ROOT_NAME, rootFileName);
      filePropertyHashMap.put(fileData.ISFILE, Boolean.valueOf(file.isFile()).toString());
      filePropertyHashMap.put(fileData.ISDIRECTORY, Boolean.valueOf(file.isDirectory()).toString());
      filePropertyHashMap.put(fileData.ISHIDDEN, Boolean.valueOf(file.isHidden()).toString());
      filePropertyHashMap.put(fileData.ISABSOLUTE, Boolean.valueOf(file.isAbsolute()).toString());
      filePropertyHashMap.put(fileData.ABSOLUTE_PATH, file.getAbsolutePath());
      filePropertyHashMap.put(fileData.CANONICAL_PATH, file.getCanonicalPath());
      filePropertyHashMap.put(fileData.NAME, file.getName());
      filePropertyHashMap.put(fileData.PARENT, file.getParent());
      filePropertyHashMap.put(fileData.PATH, file.getPath());

      return filePropertyHashMap;
   }
}
