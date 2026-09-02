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

public class AdminRoleFactory
{

    private static final AdminRoleFactory instance = new AdminRoleFactory();

    public static AdminRoleFactory getInstance()
    {
        return AdminRoleFactory.instance;
    }
    
    private final BasicArrayList adminRoles = new BasicArrayListD();
    private final BasicArrayList storeAdminRoles = new BasicArrayListD();
    private final BasicArrayList storeManagementRoles = new BasicArrayListD();
    private final BasicArrayList productManagementRoles = new BasicArrayListD();
    private final BasicArrayList reviewManagementRoles = new BasicArrayListD();
    private final BasicArrayList customerManagementRoles = new BasicArrayListD();
    private final BasicArrayList workflowManagementRoles = new BasicArrayListD();
    private final BasicArrayList orderManagementRoles = new BasicArrayListD();
    private final BasicArrayList adjusterManagementRoles = new BasicArrayListD();
    private final BasicArrayList shippingManagementRoles = new BasicArrayListD();
    private final BasicArrayList webManagementRoles = new BasicArrayListD();

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
    public BasicArrayList getProductManagementRoles()
    {
        return this.productManagementRoles;
    }

    /**
     * @return the adminRoles
     */
    public BasicArrayList getAdminRoles()
    {
        return this.adminRoles;
    }

    /**
     * @return the storeAdminRoles
     */
    public BasicArrayList getStoreAdminRoles()
    {
        return this.storeAdminRoles;
    }

    /**
     * @return the storeManagementRoles
     */
    public BasicArrayList getStoreManagementRoles()
    {
        return this.storeManagementRoles;
    }

    /**
     * @return the reviewManagementRoles
     */
    public BasicArrayList getReviewManagementRoles()
    {
        return this.reviewManagementRoles;
    }

    /**
     * @return the customerManagementRoles
     */
    public BasicArrayList getCustomerManagementRoles()
    {
        return this.customerManagementRoles;
    }

    /**
     * @return the workflowManagementRoles
     */
    public BasicArrayList getWorkflowManagementRoles()
    {
        return this.workflowManagementRoles;
    }

    /**
     * @return the webManagementRoles
     */
    public BasicArrayList getWebManagementRoles()
    {
        return this.webManagementRoles;
    }

    /**
     * @return the shippingManagementRoles
     */
    public BasicArrayList getShippingManagementRoles()
    {
        return this.shippingManagementRoles;
    }

    /**
     * @return the adjusterManagementRoles
     */
    public BasicArrayList getAdjusterManagementRoles()
    {
        return this.adjusterManagementRoles;
    }

    /**
     * @return the orderManagementRoles
     */
    public BasicArrayList getOrderManagementRoles()
    {
        return this.orderManagementRoles;
    }
}
