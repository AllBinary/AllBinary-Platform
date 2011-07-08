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
package abcs.business.user.role;

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
        return instance;
    }

    private final Vector roles = new Vector();
    private final Vector subscriberRoles = new Vector();
    private final Vector wholesaleRoles = new Vector();

    private StoreRoleFactory()
    {
        final BasicUserRoleFactory basicUserRoleFactory =
            BasicUserRoleFactory.getInstance();

        roles.add(basicUserRoleFactory.CUSTOMER);
        roles.add(basicUserRoleFactory.SUBSCRIBERCUSTOMER);
        roles.add(basicUserRoleFactory.WHOLESALECUSTOMER);

        subscriberRoles.add(basicUserRoleFactory.SUBSCRIBERCUSTOMER);

        wholesaleRoles.add(basicUserRoleFactory.WHOLESALECUSTOMER);
    }

    /**
     * @return the roles
     */
    public Vector getRoles()
    {
        return roles;
    }

    /**
     * @return the subscriberRoles
     */
    public Vector getSubscriberRoles()
    {
        return subscriberRoles;
    }

    /**
     * @return the wholesaleRoles
     */
    public Vector getWholesaleRoles()
    {
        return wholesaleRoles;
    }

}
