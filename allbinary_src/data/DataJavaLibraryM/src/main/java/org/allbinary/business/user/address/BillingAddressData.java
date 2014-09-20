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
package org.allbinary.business.user.address;

public class BillingAddressData
{
   private BillingAddressData()
   {}
   public static final int MAX = 255;
   public static final int MIN = 0;
   
   //public static final String FORM = "BILLINGADDRESS_FORM";
   
   public static final String MULTIPLE = "BILLINGADDRESSES";
   
   public static final String STATES = "BILLINGADDRESS_STATES";

   public static final String SETTOSHIPPINGADDRESS="Set To Shipping Address";

   public static final String SELECT = "Select Billing Address";      
/*   
   public static final String ADD = "Add Billing Address";
   public static final String EDIT = "Edit Billing Address";
   public static final String UPDATE = "Update Billing Address";
   public static final String REMOVE = "Remove Billing Address";
   public static final String VIEW = "View Billing Address";
   public static final String CHANGE = "Change Billing Address";
   public static final String MAKENEW = "Add New Billing Address";
   
   public static final String ADDNAME = "ADDBILLINGADDRESS";
   public static final String SELECTNAME = "SELECTBILLINGADDRESS";
   public static final String REMOVENAME = "REMOVEBILLINGADDRESS";
   public static final String MAKENEWNAME = "MAKENEWBILLINGADDRESS";
 */
   
   public static final String SETTOSHIPPINGADDRESSNAME = "SETTOSHIPPINGADDRESS";
   
   public static final String BILLINGADDRESSES = "BILLINGADDRESSES";
   public static final String BILLINGADDRESS = "BILLINGADDRESS";
   
   public static final String ID = "BILLINGADDRESS_ID";
   public static final String ADDRESS = "BILLINGADDRESS_ADDRESS";
   public static final String NAME = "BILLINGADDRESS_NAME";
   public static final String STREET = "BILLINGADDRESS_STREET";
   public static final String CITY = "BILLINGADDRESS_CITY";
   public static final String STATE = "BILLINGADDRESS_STATE";
   public static final String CODE = "BILLINGADDRESS_CODE";
   public static final String COUNTRY = "BILLINGADDRESS_COUNTRY";
}
