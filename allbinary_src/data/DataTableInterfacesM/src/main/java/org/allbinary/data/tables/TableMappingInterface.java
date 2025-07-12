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
package org.allbinary.data.tables;

import java.util.HashMap;
import java.util.Vector;

public interface TableMappingInterface
{
   public Object getKey() throws Exception;
   
   //Used for inserting into database   
   public Vector toVector() throws Exception;
   //Used for updating viewinfo in database   
   public HashMap toHashMap() throws Exception;
}
