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
package org.allbinary.business.context.modules.storefront.statistics.users;

import org.allbinary.data.tables.TableMappingInterface;
//import org.allbinary.business.user.commerce.money.Money;

public interface StoreFrontUsersStatisticsInterface extends TableMappingInterface
{
   public Long getNumberOfUsers();
   public Long getNumberOfUsersByRole(String role);
}
