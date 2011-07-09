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
package allbinary.business.quoterequest;

public class QuoteRequestData
{
	private static final QuoteRequestData instance = new QuoteRequestData();
	
	   public static QuoteRequestData getInstance() {
			return instance;
		}
	
   private QuoteRequestData()
   {
   }
   
public final String ID = "QUOTEREQUEST_ID";
   public final String PROJECT_INFO = "QUOTEREQUEST_PROJECT_INFO";
   public final String CUSTOMER_COMMENTS = "QUOTEREQUEST_CUSTOMER_COMMENTS";
   public final String BUDGET = "QUOTEREQUEST_BUDGET";
   public final String TIMEFRAME = "QUOTEREQUEST_TIMEFRAME";
   public final String COMMENTS = "QUOTEREQUEST_COMMENTS";
}
