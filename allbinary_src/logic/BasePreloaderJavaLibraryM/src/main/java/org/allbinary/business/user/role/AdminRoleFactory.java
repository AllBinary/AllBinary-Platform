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
    
    private final Vector<Object> adminRoles = new Vector<Object>();
    private final Vector<Object> storeAdminRoles = new Vector<Object>();
    private final Vector<Object> storeManagementRoles = new Vector<Object>();
    private final Vector<Object> productManagementRoles = new Vector<Object>();
    private final Vector<Object> reviewManagementRoles = new Vector<Object>();
    private final Vector<Object> customerManagementRoles = new Vector<Object>();
    private final Vector<Object> workflowManagementRoles = new Vector<Object>();
    private final Vector<Object> orderManagementRoles = new Vector<Object>();
    private final Vector<Object> adjusterManagementRoles = new Vector<Object>();
    private final Vector<Object> shippingManagementRoles = new Vector<Object>();
    private final Vector<Object> webManagementRoles = new Vector<Object>();

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
    public Vector<Object> getProductManagementRoles()
    {
        return productManagementRoles;
    }

    /**
     * @return the adminRoles
     */
    public Vector<Object> getAdminRoles()
    {
        return adminRoles;
    }

    /**
     * @return the storeAdminRoles
     */
    public Vector<Object> getStoreAdminRoles()
    {
        return storeAdminRoles;
    }

    /**
     * @return the storeManagementRoles
     */
    public Vector<Object> getStoreManagementRoles()
    {
        return storeManagementRoles;
    }

    /**
     * @return the reviewManagementRoles
     */
    public Vector<Object> getReviewManagementRoles()
    {
        return reviewManagementRoles;
    }

    /**
     * @return the customerManagementRoles
     */
    public Vector<Object> getCustomerManagementRoles()
    {
        return customerManagementRoles;
    }

    /**
     * @return the workflowManagementRoles
     */
    public Vector<Object> getWorkflowManagementRoles()
    {
        return workflowManagementRoles;
    }

    /**
     * @return the webManagementRoles
     */
    public Vector<Object> getWebManagementRoles()
    {
        return webManagementRoles;
    }

    /**
     * @return the shippingManagementRoles
     */
    public Vector<Object> getShippingManagementRoles()
    {
        return shippingManagementRoles;
    }

    /**
     * @return the adjusterManagementRoles
     */
    public Vector<Object> getAdjusterManagementRoles()
    {
        return adjusterManagementRoles;
    }

    /**
     * @return the orderManagementRoles
     */
    public Vector<Object> getOrderManagementRoles()
    {
        return orderManagementRoles;
    }
}
