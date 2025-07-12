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

import org.allbinary.business.user.modules.UserFactory;
import org.allbinary.business.user.modules.admin.AdminUserFactory;
import org.allbinary.business.user.modules.admin.store.StoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.customer.CustomerStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.order.OrderStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.order.adjust.AdjustOrderStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.order.review.ReviewOrderStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.order.ship.ShipOrderStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.payment.PaymentStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.product.ProductStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.web.WebStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.web.view.editor.ViewEditorWebStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.web.wizard.WizardWebStoreAdminUserFactory;
import org.allbinary.business.user.modules.admin.store.web.workflow.editor.WorkflowEditorWebStoreAdminUserFactory;
import org.allbinary.business.user.modules.customer.subscriber.SubscriberUserFactory;
import org.allbinary.business.user.modules.customer.wholesale.WholesaleUserFactory;

/**
 *
 * @author user
 */
public class UserRoleFactory {
	
	private static final UserRoleFactory instance = new UserRoleFactory();
	
	public static UserRoleFactory getInstance() {
		return instance;
	}
	
	private final BasicUserRoleFactory basicUserRoleFactory = 
		BasicUserRoleFactory.getInstance();
	
   public final UserRole INSTALLER =
      new UserRoleB(basicUserRoleFactory.INSTALLER, null);

   //Administrators
   //new AdministratorUserFactory()
   public final UserRole ADMINISTRATOR =
      new UserRoleB(basicUserRoleFactory.ADMINISTRATOR, new AdminUserFactory());

   //Users
   public final UserRole CUSTOMER =
      new UserRoleB(basicUserRoleFactory.CUSTOMER, new UserFactory());
   public final UserRole SUBSCRIBERCUSTOMER =
      new UserRoleB(basicUserRoleFactory.SUBSCRIBERCUSTOMER, new SubscriberUserFactory());
   public final UserRole WHOLESALECUSTOMER =
      new UserRoleB(basicUserRoleFactory.WHOLESALECUSTOMER, new WholesaleUserFactory());

   //Store Users

   //Store Admin
   public final UserRole STOREMANAGER =
      new UserRoleB(basicUserRoleFactory.STOREMANAGER, new StoreAdminUserFactory());

   //Managers
   public final UserRole PRODUCTMANAGER =
      new UserRoleB(basicUserRoleFactory.PRODUCTMANAGER, new ProductStoreAdminUserFactory());
   public final UserRole CUSTOMERMANAGER =
      new UserRoleB(basicUserRoleFactory.CUSTOMERMANAGER, new CustomerStoreAdminUserFactory());
   public final UserRole PAYMENTMANAGER =
      new UserRoleB(basicUserRoleFactory.PAYMENTMANAGER, new PaymentStoreAdminUserFactory());
   public final UserRole ORDERMANAGER =
      new UserRoleB(basicUserRoleFactory.ORDERMANAGER, new OrderStoreAdminUserFactory());
   public final UserRole WEBMANAGER =
      new UserRoleB(basicUserRoleFactory.WEBMANAGER, new WebStoreAdminUserFactory());
   
   //Order Manager Assistants
   public final UserRole REVIEWER =
      new UserRoleB(basicUserRoleFactory.REVIEWER, new ReviewOrderStoreAdminUserFactory());
   public final UserRole SHIPPING =
      new UserRoleB(basicUserRoleFactory.SHIPPING, new ShipOrderStoreAdminUserFactory());
   public final UserRole ADJUSTER =
      new UserRoleB(basicUserRoleFactory.ADJUSTER, new AdjustOrderStoreAdminUserFactory());

   //Web Manager Assistants
   public final UserRole WORKFLOWEDITOR =
      new UserRoleB(basicUserRoleFactory.WORKFLOWEDITOR, new WorkflowEditorWebStoreAdminUserFactory());
   public final UserRole WIZARD =
      new UserRoleB(basicUserRoleFactory.WIZARD, new WizardWebStoreAdminUserFactory());
   //public final UserRole WIZARDEDITOR =
   //new UserRole (BasicUserRole, new WizardEditorWebStoreAdminUserFactory());
   public final UserRole VIEWEDITOR =
      new UserRoleB(basicUserRoleFactory.VIEWEDITOR, new ViewEditorWebStoreAdminUserFactory());
}
