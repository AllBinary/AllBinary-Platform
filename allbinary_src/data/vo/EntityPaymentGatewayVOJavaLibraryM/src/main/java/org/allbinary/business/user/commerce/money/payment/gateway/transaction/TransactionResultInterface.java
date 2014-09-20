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
    public String toString();
    public Vector getValues();
    public String getResult();
    public String getPnRef();
    public String getRespMsg();
    public String getAvsAddr();
    public String getAvsZip();    
    public String getOrigResult();    
    public String getStatus();    
    public String getFraudCode();    
    public String getFraudMsg();    
    public String getErrCode();    
    public String getScore();    
    public String getReason1();
    public String getReason2();
    public String getReason3();
    public String getException1();
    public String getException2();
    public String getException3();
    public String getException4();
    public String getException5();
    public String getException6();
    public String getException7();
}
