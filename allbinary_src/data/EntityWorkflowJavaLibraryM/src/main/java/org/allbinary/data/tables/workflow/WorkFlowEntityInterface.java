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

import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.logic.control.workflow.WorkFlowInterface;

import java.util.HashMap;
import java.util.Vector;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface WorkFlowEntityInterface 
   extends BasicDataTableInterface
{
   public Vector get(String storeName);

   public WorkFlowInterface get(String name, String storeName) throws Exception, LicensingException;

   public void delete(String name, String storeName);
   
   public void insert(Vector values);

   public void update(HashMap updatedValues);
}
