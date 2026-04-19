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

        this.adminRoles.add(basicUserRoleFactory.ADMINISTRATOR);

        this.storeAdminRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.storeAdminRoles.add(basicUserRoleFactory.STOREMANAGER);

        this.storeManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.storeManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        this.storeManagementRoles.add(basicUserRoleFactory.CUSTOMERMANAGER);
        this.storeManagementRoles.add(basicUserRoleFactory.ADJUSTER);
        this.storeManagementRoles.add(basicUserRoleFactory.PRODUCTMANAGER);
        this.storeManagementRoles.add(basicUserRoleFactory.REVIEWER);
        this.storeManagementRoles.add(basicUserRoleFactory.SHIPPING);
        //storeManagementRoles.add(basicUserRoleFactory.ORDERMANAGER);

        this.productManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.productManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        this.productManagementRoles.add(basicUserRoleFactory.PRODUCTMANAGER);

        this.reviewManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.reviewManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        this.reviewManagementRoles.add(basicUserRoleFactory.REVIEWER);

        this.customerManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.customerManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        this.customerManagementRoles.add(basicUserRoleFactory.CUSTOMERMANAGER);

        this.workflowManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.workflowManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        this.workflowManagementRoles.add(basicUserRoleFactory.WORKFLOWEDITOR);

        this.orderManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.orderManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        //orderManagementRoles.add(basicUserRoleFactory.ORDERMANAGER);

        this.adjusterManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.adjusterManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        this.adjusterManagementRoles.add(basicUserRoleFactory.ADJUSTER);

        this.shippingManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.shippingManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        this.shippingManagementRoles.add(basicUserRoleFactory.SHIPPING);

        this.webManagementRoles.add(basicUserRoleFactory.ADMINISTRATOR);
        this.webManagementRoles.add(basicUserRoleFactory.STOREMANAGER);
        this.webManagementRoles.add(basicUserRoleFactory.WEBMANAGER);
        this.webManagementRoles.add(basicUserRoleFactory.WORKFLOWEDITOR);
//webManagementRoles.add(basicUserRoleFactory.WIZARDEDITOR);
        this.webManagementRoles.add(basicUserRoleFactory.VIEWEDITOR);

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
