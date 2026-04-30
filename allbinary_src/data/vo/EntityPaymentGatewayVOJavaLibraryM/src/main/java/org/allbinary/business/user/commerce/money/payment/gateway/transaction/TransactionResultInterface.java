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
package org.allbinary.business.user.commerce.money.payment.gateway.transaction;

import java.util.Vector;

public interface TransactionResultInterface
{
    String toString();
    Vector getValues();
    String getResult();
    String getPnRef();
    String getRespMsg();
    String getAvsAddr();
    String getAvsZip();    
    String getOrigResult();    
    String getStatus();    
    String getFraudCode();    
    String getFraudMsg();    
    String getErrCode();    
    String getScore();    
    String getReason1();
    String getReason2();
    String getReason3();
    String getException1();
    String getException2();
    String getException3();
    String getException4();
    String getException5();
    String getException6();
    String getException7();
}
