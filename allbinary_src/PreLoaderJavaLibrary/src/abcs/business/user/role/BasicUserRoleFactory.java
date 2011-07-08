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

public class BasicUserRoleFactory
{
    private static final BasicUserRoleFactory instance = new BasicUserRoleFactory();

    public static BasicUserRoleFactory getInstance()
    {
        return instance;
    }
    //60000 = 1 min
    //600000 = 10 min
    //900000 = 15 min
    //3600000 = 60 min
    private final long INACTIVITYTIMEOUT = 360000000;
    private final long CUSTOMERTIMEOUT = 360000000;
    //360000000
    //Long.MAX_VALUE
    private final long CUSTOMERMAXSESSIONTIME = 360000000;
    private final long MAXSESSIONTIME = 360000000;
    public final BasicUserRole INSTALLER =
        new BasicUserRole("Installer", 16, MAXSESSIONTIME, INACTIVITYTIMEOUT);
    //Administrators
    //new AdministratorUserFactory()
    public final BasicUserRole ADMINISTRATOR =
        new BasicUserRole("Administrator", 0, MAXSESSIONTIME, INACTIVITYTIMEOUT);
    //Users
    public final BasicUserRole CUSTOMER =
        new BasicUserRole(
        "CustomerUser",
        "Customer User",
        1, CUSTOMERTIMEOUT, CUSTOMERMAXSESSIONTIME);
    public final BasicUserRole SUBSCRIBERCUSTOMER =
        new BasicUserRole("SubscriberUser", "Subscriber User", 2,
        CUSTOMERTIMEOUT, CUSTOMERMAXSESSIONTIME);
    public final BasicUserRole WHOLESALECUSTOMER =
        new BasicUserRole("WholesaleUser", "Wholesale User", 3,
        CUSTOMERTIMEOUT, CUSTOMERMAXSESSIONTIME);
    //Store Users
    //Store Admin
    public final BasicUserRole STOREMANAGER =
        new BasicUserRole("StoreManager", "Store Manager", 4,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
    //Managers
    public final BasicUserRole PRODUCTMANAGER =
        new BasicUserRole("ProductManager", "Product Manager", 5,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
    public final BasicUserRole CUSTOMERMANAGER =
        new BasicUserRole("UserManager", "User Manager", 6,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
    public final BasicUserRole PAYMENTMANAGER =
        new BasicUserRole("PaymentManager", "Payment Manager", 7,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
    public final BasicUserRole ORDERMANAGER =
        new BasicUserRole("OrderManager", "Order Manager", 8,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
    public final BasicUserRole WEBMANAGER =
        new BasicUserRole("WebManager", "Web Manager", 9,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
    //Order Manager Assistants
    public final BasicUserRole REVIEWER =
        new BasicUserRole("Reviewer", 10, MAXSESSIONTIME, INACTIVITYTIMEOUT);
    public final BasicUserRole SHIPPING =
        new BasicUserRole("Shipping", 11, MAXSESSIONTIME, INACTIVITYTIMEOUT);
    public final BasicUserRole ADJUSTER =
        new BasicUserRole("Adjuster", 12, MAXSESSIONTIME, INACTIVITYTIMEOUT);
    //Web Manager Assistants
    public final BasicUserRole WORKFLOWEDITOR =
        new BasicUserRole("WorkFlowEditor", "WorkFlow Editor", 13,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
    public final BasicUserRole WIZARD =
        new BasicUserRole("WizardUser", "Wizard User", 14,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
    //public final BasicUserRole WIZARDEDITOR =
    //new BasicUserRole ("Wizard Editor User", MAXSESSIONTIME, INACTIVITYTIMEOUT);
    public final BasicUserRole VIEWEDITOR =
        new BasicUserRole("ViewEditor", "View Editor", 15,
        MAXSESSIONTIME, INACTIVITYTIMEOUT);
}
