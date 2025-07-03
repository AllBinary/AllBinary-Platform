/*
*Copyright (c) 2002 All Binary
*All Rights Reserved. 
*Don't Duplicate or Distributed.
*Trade Secret Information 
*For Internal Use Only 
*Confidential
*Unpublished 
*
*Created By: Travis Berthelot 
*Date: 11/29/02
*
*
*Modified By         When       ?
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
