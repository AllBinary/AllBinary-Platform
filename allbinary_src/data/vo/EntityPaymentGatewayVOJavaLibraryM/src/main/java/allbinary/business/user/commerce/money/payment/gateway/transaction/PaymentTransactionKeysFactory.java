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
package allbinary.business.user.commerce.money.payment.gateway.transaction;

public class PaymentTransactionKeysFactory {

	private static final PaymentTransactionKeysFactory instance = new PaymentTransactionKeysFactory();

	public static PaymentTransactionKeysFactory getInstance() {
		return instance;
	}      

	   public final PaymentTransactionKeys TRXTYPE = new PaymentTransactionKeys("TRXTYPE");
	   public final PaymentTransactionKeys TENDER = new PaymentTransactionKeys("TENDER");
	   public final PaymentTransactionKeys ACCT = new PaymentTransactionKeys("ACCT");
	   public final PaymentTransactionKeys EXPDATE = new PaymentTransactionKeys("EXPDATE");
	   public final PaymentTransactionKeys AMT = new PaymentTransactionKeys("AMT");
	   public final PaymentTransactionKeys AUTHCODE = new PaymentTransactionKeys("AUTHCODE");
	   public final PaymentTransactionKeys MICR = new PaymentTransactionKeys("MICR");
	   public final PaymentTransactionKeys CHECKNUM = new PaymentTransactionKeys("CHECKNUM");   
	   public final PaymentTransactionKeys NAME = new PaymentTransactionKeys("NAME");
	   public final PaymentTransactionKeys STREET = new PaymentTransactionKeys("STREET");
	   public final PaymentTransactionKeys CITY = new PaymentTransactionKeys("CITY");
	   public final PaymentTransactionKeys STATE = new PaymentTransactionKeys("STATE");
	   public final PaymentTransactionKeys ZIP = new PaymentTransactionKeys("ZIP");
	   public final PaymentTransactionKeys DL = new PaymentTransactionKeys("DL");
	   public final PaymentTransactionKeys EMAIL = new PaymentTransactionKeys("EMAIL");
	   public final PaymentTransactionKeys COMMENT1 = new PaymentTransactionKeys("COMMENT1");
	   public final PaymentTransactionKeys COMMENT2 = new PaymentTransactionKeys("COMMENT2");
	   public final PaymentTransactionKeys ORIGID = new PaymentTransactionKeys("ORIGID");
	   public final PaymentTransactionKeys PONUM = new PaymentTransactionKeys("PONUM");
	   //public final PaymentTransactionKeys DESC = new PaymentTransactionKeys("DESC");
	   public final PaymentTransactionKeys DESC = new PaymentTransactionKeys("PaymentTransactionDESC");
	   public final PaymentTransactionKeys DESC1TO4 = new PaymentTransactionKeys("DESC1TO4");
	   public final PaymentTransactionKeys INVNUM = new PaymentTransactionKeys("INVNUM");
	   public final PaymentTransactionKeys SHIPTOZIP = new PaymentTransactionKeys("SHIPTOZIP");
	   public final PaymentTransactionKeys SWIPE = new PaymentTransactionKeys("SWIPE");
	   public final PaymentTransactionKeys TAXAMT = new PaymentTransactionKeys("TAXAMT");
	   public final PaymentTransactionKeys COMMCARD = new PaymentTransactionKeys("COMMCARD");
	   public final PaymentTransactionKeys DUTYAMT = new PaymentTransactionKeys("DUTYAMT");
	   public final PaymentTransactionKeys FRIEGHTAMT = new PaymentTransactionKeys("FRIEGHTAMT");
	   public final PaymentTransactionKeys ORDERDATE = new PaymentTransactionKeys("ORDERDATE");
	   public final PaymentTransactionKeys TAXEXEMPT = new PaymentTransactionKeys("TAXEXEMPT");
	   public final PaymentTransactionKeys COUNTRYCODE = new PaymentTransactionKeys("COUNTRYCODE");
	   public final PaymentTransactionKeys CUSTCODE = new PaymentTransactionKeys("CUSTCODE");
	   public final PaymentTransactionKeys CVV2 = new PaymentTransactionKeys("CVV2");
	   public final PaymentTransactionKeys ABA = new PaymentTransactionKeys("ABA");
	   public final PaymentTransactionKeys ACCTTYPE = new PaymentTransactionKeys("ACCTTYPE");
	   public final PaymentTransactionKeys DISCOUNT = new PaymentTransactionKeys("DISCOUNT");
	   public final PaymentTransactionKeys FIRSTNAME = new PaymentTransactionKeys("FIRSTNAME");
	   public final PaymentTransactionKeys LASTNAME = new PaymentTransactionKeys("LASNAME");   
	   public final PaymentTransactionKeys SHIPFROMZIP = new PaymentTransactionKeys("SHIPFROMZIP");
	   public final PaymentTransactionKeys PRENOTE = new PaymentTransactionKeys("PRENOTE");
	   public final PaymentTransactionKeys CHKTYPE = new PaymentTransactionKeys("CHKTYPE");
	   public final PaymentTransactionKeys DOB = new PaymentTransactionKeys("DOB");
	   public final PaymentTransactionKeys PHONENUM = new PaymentTransactionKeys("PHONENUM");
	   public final PaymentTransactionKeys SS = new PaymentTransactionKeys("SS");
	   public final PaymentTransactionKeys COMPANYNAME = new PaymentTransactionKeys("COMPANYNAME");
	   public final PaymentTransactionKeys COUNTRY = new PaymentTransactionKeys("COUNTRY");
	   public final PaymentTransactionKeys FREIGHTAMT = new PaymentTransactionKeys("FREIGHTAMT");
	   public final PaymentTransactionKeys SHIPTOCITY = new PaymentTransactionKeys("SHIPTOCITY");
	   public final PaymentTransactionKeys SHIPTOFIRSTNAME = new PaymentTransactionKeys("SHIPTOFIRSTNAME");
	   public final PaymentTransactionKeys SHIPTOLASTNAME = new PaymentTransactionKeys("SHIPTOLASTNAME");
	   public final PaymentTransactionKeys SHIPTOSTATE = new PaymentTransactionKeys("SHIPTOSTATE");
	   public final PaymentTransactionKeys SHIPTOSTREET = new PaymentTransactionKeys("SHIPTOSTREET");

	   public final PaymentTransactionKeys SPECIAL1 = new PaymentTransactionKeys("SPECIAL1");
	   public final PaymentTransactionKeys SPECIAL2 = new PaymentTransactionKeys("SPECIAL2");
	   public final PaymentTransactionKeys SPECIAL3 = new PaymentTransactionKeys("SPECIAL3");     
	   
	   //not in db    
	   public final PaymentTransactionKeys PARTNER = new PaymentTransactionKeys("PARTNER");
	   public final PaymentTransactionKeys VENDOR = new PaymentTransactionKeys("VENDOR");      
	   public final PaymentTransactionKeys USER = new PaymentTransactionKeys("USER");
	   public final PaymentTransactionKeys PWD = new PaymentTransactionKeys("PWD");
	   
	   //duplicate
	   public final PaymentTransactionKeys CHKNUM = new PaymentTransactionKeys("CHKNUM");
	   
	   //response
	   //AUTHCODE in trans and resp
	   public final PaymentTransactionKeys AVSADDR = new PaymentTransactionKeys("AVSADDR");
	   public final PaymentTransactionKeys AVSZIP = new PaymentTransactionKeys("AVSZIP");
	   public final PaymentTransactionKeys PNREF = new PaymentTransactionKeys("PNREF");
	   public final PaymentTransactionKeys RESPMSG = new PaymentTransactionKeys("RESPMSG");
	   public final PaymentTransactionKeys RESULT = new PaymentTransactionKeys("RESULT");
	      
	   public final PaymentTransactionKeys ORIGRESULT = new PaymentTransactionKeys("ORIGRESULT");
	   public final PaymentTransactionKeys STATUS = new PaymentTransactionKeys("STATUS");
	   public final PaymentTransactionKeys FRAUDCODE = new PaymentTransactionKeys("FRAUDCODE");
	   public final PaymentTransactionKeys FRAUDMSG = new PaymentTransactionKeys("FRAUDMSG");
	   public final PaymentTransactionKeys ERRCODE = new PaymentTransactionKeys("ERRCODE");
	   public final PaymentTransactionKeys SCORE = new PaymentTransactionKeys("SCORE");
	   
	   public final PaymentTransactionKeys REASON1 = new PaymentTransactionKeys("REASON1");
	   public final PaymentTransactionKeys REASON2 = new PaymentTransactionKeys("REASON2");
	   public final PaymentTransactionKeys REASON3 = new PaymentTransactionKeys("REASON3");
	   
	   public final PaymentTransactionKeys EXCEPTION1 = new PaymentTransactionKeys("EXCEPTION1");   
	   public final PaymentTransactionKeys EXCEPTION2 = new PaymentTransactionKeys("EXCEPTION2");   
	   public final PaymentTransactionKeys EXCEPTION3 = new PaymentTransactionKeys("EXCEPTION3");   
	   public final PaymentTransactionKeys EXCEPTION4 = new PaymentTransactionKeys("EXCEPTION4");   
	   public final PaymentTransactionKeys EXCEPTION5 = new PaymentTransactionKeys("EXCEPTION5");   
	   public final PaymentTransactionKeys EXCEPTION6 = new PaymentTransactionKeys("EXCEPTION6");   
	   public final PaymentTransactionKeys EXCEPTION7 = new PaymentTransactionKeys("EXCEPTION7");
}
