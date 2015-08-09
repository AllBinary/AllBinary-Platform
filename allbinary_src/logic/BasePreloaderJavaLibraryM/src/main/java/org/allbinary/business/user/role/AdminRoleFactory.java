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

public class AdminRoleFactory
{

    private static final AdminRoleFactory instance = new AdminRoleFactory();

    public static AdminRoleFactory getInstance()
    {
        return instance;
    }
    
    private final Vector adminRoles = new Vector();
    private final Vector storeAdminRoles = new Vector();
    private final Vector storeManagementRoles = new Vector();
    private final Vector productManagementRoles = new Vector();
    private final Vector reviewManagementRoles = new Vector();
    private final Vector customerManagementRoles = new Vector();
    private final Vector workflowManagementRoles = new Vector();
    private final Vector orderManagementRoles = new Vector();
    private final Vector adjusterManagementRoles = new Vector();
    private final Vector shippingManagementRoles = new Vector();
    private final Vector webManagementRoles = new Vector();

    private AdminRoleFactory()
    {
        final BasicUserRoleFactory basicUserRoleFactory =
            BasicUserRoleFactory.getInstance();

        adminRoles.add(basicUserRoleFactory.ADMINISTRATOR);

        storeAdminRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        storeAdminRoles.add(basicUserRoleFactory.STOREMANAGER);

        storeManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        storeManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        storeManagementRoles.add(basicUserRoleFactory.CUSTOMERMANAGER);
        storeManagementRoles.add(basicUserRoleFactory.ADJUSTER);
        storeManagementRoles.add(basicUserRoleFactory.PRODUCTMANAGER);
        storeManagementRoles.add(basicUserRoleFactory.REVIEWER);
        storeManagementRoles.add(basicUserRoleFactory.SHIPPING);
        //storeManagementRoles.add(basicUserRoleFactory.ORDERMANAGER);

        productManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        productManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        productManagementRoles.add(basicUserRoleFactory.PRODUCTMANAGER);

        reviewManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        reviewManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        reviewManagementRoles.add(basicUserRoleFactory.REVIEWER);

        customerManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        customerManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        customerManagementRoles.add(basicUserRoleFactory.CUSTOMERMANAGER);

        workflowManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        workflowManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        workflowManagementRoles.add(basicUserRoleFactory.WORKFLOWEDITOR);

        orderManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        orderManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        //orderManagementRoles.add(basicUserRoleFactory.ORDERMANAGER);

        adjusterManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        adjusterManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        adjusterManagementRoles.add(basicUserRoleFactory.ADJUSTER);

        shippingManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        shippingManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        shippingManagementRoles.add(basicUserRoleFactory.SHIPPING);

        webManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        webManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        webManagementRoles.add(basicUserRoleFactory.WEBMANAGER);
        webManagementRoles.add(basicUserRoleFactory.WORKFLOWEDITOR);
//webManagementRoles.add(basicUserRoleFactory.WIZARDEDITOR);
        webManagementRoles.add(basicUserRoleFactory.VIEWEDITOR);

    }

    /**
     * @return the productManagementRoles
     */
    public Vector getProductManagementRoles()
    {
        return productManagementRoles;
    }

    /**
     * @return the adminRoles
     */
    public Vector getAdminRoles()
    {
        return adminRoles;
    }

    /**
     * @return the storeAdminRoles
     */
    public Vector getStoreAdminRoles()
    {
        return storeAdminRoles;
    }

    /**
     * @return the storeManagementRoles
     */
    public Vector getStoreManagementRoles()
    {
        return storeManagementRoles;
    }

    /**
     * @return the reviewManagementRoles
     */
    public Vector getReviewManagementRoles()
    {
        return reviewManagementRoles;
    }

    /**
     * @return the customerManagementRoles
     */
    public Vector getCustomerManagementRoles()
    {
        return customerManagementRoles;
    }

    /**
     * @return the workflowManagementRoles
     */
    public Vector getWorkflowManagementRoles()
    {
        return workflowManagementRoles;
    }

    /**
     * @return the webManagementRoles
     */
    public Vector getWebManagementRoles()
    {
        return webManagementRoles;
    }

    /**
     * @return the shippingManagementRoles
     */
    public Vector getShippingManagementRoles()
    {
        return shippingManagementRoles;
    }

    /**
     * @return the adjusterManagementRoles
     */
    public Vector getAdjusterManagementRoles()
    {
        return adjusterManagementRoles;
    }

    /**
     * @return the orderManagementRoles
     */
    public Vector getOrderManagementRoles()
    {
        return orderManagementRoles;
    }
}
