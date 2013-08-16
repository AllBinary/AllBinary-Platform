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
package allbinary.data.tables.user.quoterequest;

import allbinary.business.user.quoterequest.QuoteRequest;
import allbinary.data.tables.BasicTableInterface;

import java.util.HashMap;
import java.util.Vector;

public interface QuoteRequestEntityInterface extends BasicTableInterface
{      
	public QuoteRequest get(String userName, int id) throws Exception;
	public Vector getIds(String userName);
   //public QuoteRequest[] get(String userName);
   
   public void deleteWhere(String key,String value);
   
   public void insert(Vector values);

   public void update(String userName, HashMap updatedValues);

   //public String getTable(String itemId);
   //public String getForm(String id);
   
}
