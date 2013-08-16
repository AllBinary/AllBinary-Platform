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

public class CreditCardTypeFactory {

	private static final CreditCardTypeFactory instance = new CreditCardTypeFactory();
	
	public static CreditCardTypeFactory getInstance() {
		return instance;
	}
	
	   public final CreditCardType VISA = new CreditCardType("Visa");
	   public final CreditCardType MASTERCARD = new CreditCardType("MasterCard");
	   public final CreditCardType DISCOVER = new CreditCardType("Discover");
	   public final CreditCardType AMERICANEXPRESS = new CreditCardType("AMX");
}
