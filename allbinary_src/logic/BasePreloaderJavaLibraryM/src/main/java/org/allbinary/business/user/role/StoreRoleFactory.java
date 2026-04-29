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

import java.util.Vector;

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

    private final Vector<Object> roles = new Vector<Object>();
    private final Vector<Object> subscriberRoles = new Vector<Object>();
    private final Vector<Object> wholesaleRoles = new Vector<Object>();

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
    public Vector<Object> getRoles()
    {
        return this.roles;
    }

    /**
     * @return the subscriberRoles
     */
    public Vector<Object> getSubscriberRoles()
    {
        return this.subscriberRoles;
    }

    /**
     * @return the wholesaleRoles
     */
    public Vector<Object> getWholesaleRoles()
    {
        return this.wholesaleRoles;
    }

}
