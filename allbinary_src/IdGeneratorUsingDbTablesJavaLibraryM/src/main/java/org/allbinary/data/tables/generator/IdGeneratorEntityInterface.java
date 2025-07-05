/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
package org.allbinary.data.tables.generator;

import java.util.HashMap;
import java.util.Vector;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface IdGeneratorEntityInterface extends BasicDataTableInterface
{
   public Long get(String name) throws Exception;

   public void deleteWhere(String key, String value);
   
   public void insert(Vector values);

   public void update(HashMap updatedValues);
}
