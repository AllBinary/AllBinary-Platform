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
package org.allbinary.logic.communication.http.request.session;

import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.role.UserRole;

/**
 *
 * @author user
 */
public interface WeblisketSessionInterface {

    void clear();

    Integer getAttempts();

    String getAuthentication();

    //BasketInterface getBasket() throws Exception;

    long getCreationTime();

    String getId();

    long getLastAccessedTime();

    OrderInterface getOrder() throws Exception;

    String getPassword();

    String getPaymentMethod();

    UserRole getRole() throws Exception;

    String getStoreName();

    String getTimeout();

    String getUserName();

    String getWebAppPath();

    void removeBasket();

    void setAttempts(Integer value);

    void setAuthenticated();

    void setAuthenticated(boolean value);

    void setPassword(String password);

    void setPaymentMethod(String value);

    void setRole(UserRole aRole);

    void setStoreName(String value);

    void setTimeout(String value);

    void setUserName(String userName);

}
