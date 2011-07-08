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
package allbinary.business.user.commerce.money.payment.types;

public class TransactionTypeFactory {
	
	private static final TransactionTypeFactory instance = new TransactionTypeFactory();

	public static TransactionTypeFactory getInstance() {
		return instance;
	}

	   public final TransactionType SALE = new TransactionType("Sale");
	   public final TransactionType CREDIT = new TransactionType("Credit");   
	   public final TransactionType AUTHORIZATION = new TransactionType("Authorization");
	   public final TransactionType DELAYEDCAPTURE =new TransactionType("Delayed Capture");
	   public final TransactionType VOID = new TransactionType("Void");   
	   public final TransactionType INQUIRY = new TransactionType("Inquiry");      
}
