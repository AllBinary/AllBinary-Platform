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
package org.allbinary.business.user.role;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

/**
 *
 * @author user
 */
public class StoreRoleFactory {

    private static final StoreRoleFactory instance = new StoreRoleFactory();

    /**
     * @return the instance
     */
    public static StoreRoleFactory getInstance()
    {
        return StoreRoleFactory.instance;
    }

    private final BasicArrayList roles = new BasicArrayListD();
    private final BasicArrayList subscriberRoles = new BasicArrayListD();
    private final BasicArrayList wholesaleRoles = new BasicArrayListD();

    private StoreRoleFactory()
    {
        final BasicUserRoleFactory basicUserRoleFactory =
            BasicUserRoleFactory.getInstance();

        this.roles.add(basicUserRoleFactory.CUSTOMER);
        this.roles.add(basicUserRoleFactory.SUBSCRIBERCUSTOMER);
        this.roles.add(basicUserRoleFactory.WHOLESALECUSTOMER);

        this.subscriberRoles.add(basicUserRoleFactory.SUBSCRIBERCUSTOMER);

        this.wholesaleRoles.add(basicUserRoleFactory.WHOLESALECUSTOMER);
    }

    /**
     * @return the roles
     */
    public BasicArrayList getRoles()
    {
        return this.roles;
    }

    /**
     * @return the subscriberRoles
     */
    public BasicArrayList getSubscriberRoles()
    {
        return this.subscriberRoles;
    }

    /**
     * @return the wholesaleRoles
     */
    public BasicArrayList getWholesaleRoles()
    {
        return this.wholesaleRoles;
    }

}
