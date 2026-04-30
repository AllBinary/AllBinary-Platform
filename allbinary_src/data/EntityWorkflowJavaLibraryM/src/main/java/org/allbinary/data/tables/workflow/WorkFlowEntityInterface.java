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
package org.allbinary.data.tables.workflow;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.data.tables.BasicDataTableInterface;
import org.allbinary.logic.control.workflow.WorkFlowInterface;
import org.allbinary.logic.system.security.licensing.LicensingException;

public interface WorkFlowEntityInterface 
   extends BasicDataTableInterface
{
   Vector get(String storeName);

   WorkFlowInterface get(String name, String storeName) throws Exception, LicensingException;

   void delete(String name, String storeName);
   
   void insert(Vector values);

   void update(HashMap updatedValues);
}
