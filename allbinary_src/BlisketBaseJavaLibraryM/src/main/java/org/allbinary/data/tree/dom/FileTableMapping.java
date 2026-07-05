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
import org.allbinary.logic.io.path.AbPathData;
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

      filePropertyVector.add(this.file.getAbsolutePath());
      filePropertyVector.add(this.file.getCanonicalPath());
      filePropertyVector.add(this.file.getName());
      filePropertyVector.add(this.file.getParent());
      filePropertyVector.add(this.file.getPath());

      return filePropertyVector;
   }

   //Used for updating viewinfo in database   
   public HashMap toHashMap() throws Exception
   {
      final HashMap filePropertyHashMap = new HashMap();

      final AbPathData pathData = AbPathData.getInstance();
      final PathUtil pathUtil = PathUtil.getInstance();
      final String rootFileName = pathData.getNameFromPath(pathUtil.getWithoutExtension(this.file.getPath()));
      final FileData fileData = FileData.getInstance();
      
      filePropertyHashMap.put(fileData.ROOT_NAME, rootFileName);
      filePropertyHashMap.put(fileData.ISFILE, Boolean.valueOf(this.file.isFile()).toString());
      filePropertyHashMap.put(fileData.ISDIRECTORY, Boolean.valueOf(this.file.isDirectory()).toString());
      filePropertyHashMap.put(fileData.ISHIDDEN, Boolean.valueOf(this.file.isHidden()).toString());
      filePropertyHashMap.put(fileData.ISABSOLUTE, Boolean.valueOf(this.file.isAbsolute()).toString());
      filePropertyHashMap.put(fileData.ABSOLUTE_PATH, file.getAbsolutePath());
      filePropertyHashMap.put(fileData.CANONICAL_PATH, file.getCanonicalPath());
      filePropertyHashMap.put(fileData.NAME, file.getName());
      filePropertyHashMap.put(fileData.PARENT, file.getParent());
      filePropertyHashMap.put(fileData.PATH, file.getPath());

      return filePropertyHashMap;
   }
}
