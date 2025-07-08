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
package org.allbinary.business.user.modules;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.modules.configuration.UserConfigurationDomDocumentMapping;
import org.allbinary.business.user.modules.configuration.UserConfigurationInterface;
import org.allbinary.business.user.modules.configuration.UserConfigurationInterfaceFactory;
import org.allbinary.business.user.password.Password;
import org.allbinary.business.user.role.UserRole;
import org.allbinary.business.user.role.UserRoleB;
import org.allbinary.business.user.role.UserRoleData;
import org.allbinary.business.user.username.UserName;
import org.allbinary.logic.communication.http.request.RequestParams;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionData;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;
import org.allbinary.business.entry.EntryData;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonStrings;

public class User implements UserInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String userName;
   private String prefixName;
   private String firstName;
   private String lastName;
   private String middleName;
   private String suffixName;
   private String company;
   private String positionAtCompany;
   private String mainEmail;
   private String secondaryEmail;
   private String homePhone;
   private String cellPhone;
   private String workPhone;
   private String otherContact;
   private String electronicDevice;
   private String fax;
   private UserRole role;
   private String encryption;
   private String secret;
   private Password password;
   private String enable;
   
   private String permissions;
   
   private UserConfigurationInterface userConfigurationInterface;
   
/*   protected final String[] HEADING = {USERNAME,PREFIXNAME,FIRSTNAME,LASTNAME,
      MIDDLENAME,SUFFIXNAME,COMPANY,POSITIONATCOMPANY,MAINEMAIL,SECONDARYEMAIL,
      HOMEPHONE,CELLPHONE,WORKPHONE,OTHERCONTACT,ELECTRONICDEVICE,FAX,ROLE,
      ENCRYPTION,SECRET,PASSWORD,DEFAULTSHIPPINGADDRESS,DEFAULTBILLINGADDRESS};
 */
   
   public User() throws Exception
   {
      //Use Default for role - future Imp will allow adjusting config with request - then move this default code to when user is new in view (same for storefront)
      this.userConfigurationInterface = 
         UserConfigurationInterfaceFactory.getInstance();
      this.password = new Password(StringUtil.getInstance().EMPTY_STRING);
   }
   
   public User(final HttpServletRequest request) throws Exception
   {
      this.getFormData(new RequestParams(request).toHashMap());

      //Use Default for role - future Imp will allow adjusting config with request - then move this default code to when user is new in view (same for storefront)
      this.userConfigurationInterface =  
         UserConfigurationInterfaceFactory.getInstance(this.getRole());      
   }

   public User(final HashMap userHashMap) throws Exception
   {
      this.getFormData(userHashMap);
      
      //Use Blob data from database
      this.userConfigurationInterface =  
         UserConfigurationInterfaceFactory.getInstance(this.getRole());      
   }

   public void getFormData(final HashMap userHashMap) throws Exception
   {
      this.userName = new UserName(userHashMap).get();
      
      final StringUtil stringUtil = StringUtil.getInstance();
      
      String passwordString = stringUtil.getInstance((String) userHashMap.get(UserData.PASSWORD));
      
      final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
      
      if(stringValidationUtil.isEmpty(this.userName) && 
         stringValidationUtil.isEmpty(passwordString))
      {
         this.userName = stringUtil.getInstance((String) userHashMap.get(WeblisketSessionData.REMOVABLEUSERNAME));
         passwordString = stringUtil.getInstance((String) userHashMap.get(WeblisketSessionData.REMOVABLEPASSWORD));
      }

      String encryption = stringUtil.getInstance((String) userHashMap.get(EntryData.getInstance().ENCRYPTION));
      String secret = stringUtil.getInstance((String) userHashMap.get(UserData.SECRET));

      this.password = new Password(passwordString);
      
      this.prefixName = stringUtil.getInstance((String) userHashMap.get(UserData.PREFIXNAME));
      this.firstName = stringUtil.getInstance((String) userHashMap.get(UserData.FIRSTNAME));
      this.lastName = stringUtil.getInstance((String) userHashMap.get(UserData.LASTNAME));
      this.middleName = stringUtil.getInstance((String) userHashMap.get(UserData.MIDDLENAME));
      this.suffixName = stringUtil.getInstance((String) userHashMap.get(UserData.SUFFIXNAME));
      this.company = stringUtil.getInstance((String) userHashMap.get(UserData.COMPANY));
      this.positionAtCompany = stringUtil.getInstance((String) userHashMap.get(UserData.POSITIONATCOMPANY));
      this.mainEmail = stringUtil.getInstance((String) userHashMap.get(UserData.MAINEMAIL));
      this.secondaryEmail = stringUtil.getInstance((String) userHashMap.get(UserData.SECONDARYEMAIL));
      this.homePhone = stringUtil.getInstance((String) userHashMap.get(UserData.HOMEPHONE));
      this.cellPhone = stringUtil.getInstance((String) userHashMap.get(UserData.CELLPHONE));
      this.workPhone = stringUtil.getInstance((String) userHashMap.get(UserData.WORKPHONE));
      this.otherContact = stringUtil.getInstance((String) userHashMap.get(UserData.OTHERCONTACT));
      this.electronicDevice = stringUtil.getInstance((String) userHashMap.get(UserData.ELECTRONICDEVICE));
      this.fax = stringUtil.getInstance((String) userHashMap.get(UserData.FAX));
      this.role =
         UserRoleB.getRole((String) userHashMap.get(UserRoleData.NAME.toString()));
      
      this.permissions = stringUtil.getInstance((String) userHashMap.get(UserData.PERMISSIONS));
      
      //TWB Temporary for store manager permissions
      if(!stringValidationUtil.isEmpty(this.permissions) && 
         this.permissions.compareTo(StoreFrontData.getInstance().NAME)==0)
         this.permissions = stringUtil.getInstance((String) userHashMap.get(StoreFrontData.getInstance().NAME));
      else
         if(this.permissions==null)
         {
            this.permissions = "No Permissions";
         }

      this.enable = stringUtil.getInstance((String) userHashMap.get(EntryData.getInstance().ENABLE));
   }

   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;
         
         if(!UserName.getInstance().isValid(this.userName).booleanValue())
         {
            valid = Boolean.FALSE;
         }
         
         if(!this.password.isValid().booleanValue())
         {
            valid = Boolean.FALSE;
         }
         
         final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
         
         if(!stringValidationUtil.isValidRequired(firstName, 1, UserData.MAXLEN))
         {
            valid = Boolean.FALSE;
         }
         
         if(!stringValidationUtil.isValidRequired(lastName, 1, UserData.MAXLEN))
         {
            valid = Boolean.FALSE;
         }
         
         if(!stringValidationUtil.isValidRequired(this.mainEmail, 1, UserData.MAXLEN) ||
            this.mainEmail.indexOf("@")==-1)
         {
            valid = Boolean.FALSE;
         }
         
         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VALIDATIONERROR))
         {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put("Failed to validate form", this, commonStrings.IS_VALID, e);
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
          
         final StringBuffer stringBuffer = new StringBuffer();
         
         stringBuffer.append(UserName.getValidationInfo(this.userName));
         
         stringBuffer.append(this.password.getValidationInfo());
         
         if(!stringValidationUtil.isValidRequired(firstName, 1, UserData.MAXLEN))
         {
            stringBuffer.append("Please enter a valid First Name.<br />");
         }
         
         if(!stringValidationUtil.isValidRequired(lastName, 1, UserData.MAXLEN))
         {
            stringBuffer.append("Please enter a valid Last Name.<br />");
         }
         
         if(!stringValidationUtil.isValidRequired(this.mainEmail, 1, UserData.MAXLEN) ||
            this.mainEmail.indexOf("@")==-1)
         {
            stringBuffer.append("Please enter a valid email address.<br />");
         }
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put("Failed to generate validation error info", this, "validationInfo()", e);
         }
         return "Error Validating Form";
      }
   }
   
   public void setUserName(String value)
   {
      this.userName=value;
   }
   
   public void setPrefixName(String value)
   {
      this.prefixName=value;
   }
   
   public void setFirstName(String value)
   {
      this.firstName=value;
   }
   
   public void setLastName(String value)
   {
      this.lastName=value;
   }
   
   public void setMiddleName(String value)
   {
      this.middleName=value;
   }
   
   public void setSuffixName(String value)
   {
      this.suffixName=value;
   }
   
   public void setCompany(String value)
   {
      this.company=value;
   }
   
   public void setPositionAtCompany(String value)
   {
      this.positionAtCompany=value;
   }
   
   public void setMainEmail(String value)
   {
      this.mainEmail=value;
   }
   
   public void setSecondaryEmail(String value)
   {
      this.secondaryEmail=value;
   }
   
   public void setHomePhone(String value)
   {
      this.homePhone=value;
   }
   
   public void setCellPhone(String value)
   {
      this.cellPhone=value;
   }
   
   public void setWorkPhone(String value)
   {
      this.workPhone=value;
   }
   
   public void setOtherContact(String value)
   {
      this.otherContact=value;
   }
   
   public void setElectronicContact(String value)
   {
      this.electronicDevice=value;
   }
   
   public void setFax(String value)
   {
      this.fax=value;
   }
   
   public void setRole(UserRole role)
   {
      this.role = role;
   }
   
   public void setPermissions(String value)
   {
      this.permissions = value;
   }
   
   public void setEncryption(String value)
   {
      this.encryption=value;
   }
   
   public void setSecret(String value)
   {
      this.secret=value;
   }
   
   public void setPassword(String value)
   {
      this.password.set(value);
   }
   
   public void enable()
   {
      this.setEnable("Yes");
   }

   public void disable()
   {
      this.setEnable("No");
   }

   public void setEnable(String enable)
   {
      this.enable= enable;
   }
   
   public String getUserName()
   {
      return this.userName;
   }
   
   public String getPrefixName()
   {
      return this.prefixName;
   }
   
   public String getFirstName()
   {
      return this.firstName;
   }
   
   public String getLastName()
   {
      return this.lastName;
   }
   
   public String getMiddleName()
   {
      return this.middleName;
   }
   
   public String getSuffixName()
   {
      return this.suffixName;
   }
   
   public String getCompany()
   {
      return this.company;
   }
   
   public String getPositionAtCompany()
   {
      return this.positionAtCompany;
   }
   
   public String getMainEmail()
   {
      return this.mainEmail;
   }
   
   public String getSecondaryEmail()
   {
      return this.secondaryEmail;
   }
   
   public String getHomePhone()
   {
      return this.homePhone;
   }
   
   public String getCellPhone()
   {
      return this.cellPhone;
   }
   
   public String getWorkPhone()
   {
      return this.workPhone;
   }
   
   public String getOtherContact()
   {
      return this.otherContact;
   }
   
   public String getElectronicContact()
   {
      return this.electronicDevice;
   }
   
   public String getFax()
   {
      return this.fax;
   }
   
   public UserRole getRole()
   {
      return this.role;
   }
   
   public String getPermissions()
   {
      return this.permissions;
   }
   
   public String getEncryption()
   {
      return this.encryption;
   }
   
   public String getSecret()
   {
      return this.secret;
   }
   
   public String getPassword()
   {
      return this.password.get();
   }
   
   public Vector toVector() throws Exception
   {
      Vector values = new Vector();
      values.add(userName);
      values.add(prefixName);
      values.add(firstName);
      values.add(lastName);
      values.add(middleName);
      values.add(suffixName);
      values.add(company);
      values.add(positionAtCompany);
      values.add(mainEmail);
      values.add(secondaryEmail);
      values.add(homePhone);
      values.add(cellPhone);
      values.add(workPhone);
      values.add(otherContact);
      values.add(electronicDevice);
      values.add(fax);
      values.add(this.getRole().toString());

      UserConfigurationDomDocumentMapping userConfigurationDomDocumentMapping =
         new UserConfigurationDomDocumentMapping(this.getUserConfigurationInterface());
      values.add(userConfigurationDomDocumentMapping.toDomDocumentString());
      
      values.add(this.permissions);
      
      values.addAll(this.password.toVector(this.secret));
      
      values.add(this.enable);
      
      Calendar calendar=Calendar.getInstance();
      String time = new String(new Long(calendar.getTimeInMillis()).toString());
      values.add(time);
      values.add(time);
      
      return values;
   }
   
   public HashMap toHashMap() throws Exception
   {
      HashMap values = new HashMap();

      values.put(UserData.USERNAME,userName);
      values.put(UserData.PREFIXNAME,prefixName);
      values.put(UserData.FIRSTNAME,firstName);
      values.put(UserData.LASTNAME,lastName);
      values.put(UserData.MIDDLENAME,middleName);
      values.put(UserData.SUFFIXNAME,suffixName);
      values.put(UserData.COMPANY,company);
      values.put(UserData.POSITIONATCOMPANY,positionAtCompany);
      values.put(UserData.MAINEMAIL,mainEmail);
      values.put(UserData.SECONDARYEMAIL,secondaryEmail);
      values.put(UserData.HOMEPHONE,homePhone);
      values.put(UserData.CELLPHONE,cellPhone);
      values.put(UserData.WORKPHONE,workPhone);
      values.put(UserData.OTHERCONTACT,otherContact);
      values.put(UserData.ELECTRONICDEVICE,electronicDevice);
      values.put(UserData.FAX,fax);

      if(this.getRole() != null)
      {
         values.put(UserRoleData.NAME.toString(), this.getRole().toString());
      }

      UserConfigurationDomDocumentMapping userConfigurationDomDocumentMapping =
         new UserConfigurationDomDocumentMapping(this.getUserConfigurationInterface());
      values.put(UserData.CONFIGURATION, userConfigurationDomDocumentMapping.toDomDocumentString());

      values.put(UserData.PERMISSIONS,this.permissions);
      
      values.putAll(this.password.toHashMap(this.secret));
      
      values.put(EntryData.getInstance().ENABLE,this.enable);
      
      Calendar calendar=Calendar.getInstance();
      String time = new String(new Long(calendar.getTimeInMillis()).toString());
      values.put(EntryData.getInstance().LASTMODIFIED,time);

      return values;
   }
   
   public HashMap toPasswordHashMap()
   {
      return this.password.toHashMap(this.secret);
   }
   
   public void validateSession(WeblisketSessionInterface weblisketSession)
   {
      weblisketSession.setAuthenticated();
      weblisketSession.setRole(this.getRole());
      weblisketSession.setUserName(this.getUserName());
   }
   
   public void updateSession(WeblisketSessionInterface weblisketSession)
   {
   }
   
   public Boolean isSessionValid()
   {
      return Boolean.TRUE;
   }
   
   /*
   public void validateSession(
   WeblisketSession weblisketSession, RequestParams requestParams)
   {
      weblisketSession.setAuthenticated();
      weblisketSession.setRole(this.getRole());
      weblisketSession.setUserName(this.getUserName());
   }
    
   public void updateSession(
   WeblisketSession weblisketSession, RequestParams requestParams)
   {
   }
    
   public boolean hasPermission(RequestParams requestParams)
   {
      return true;
   }
    */
   
   public Object getKey() throws Exception
   {
      return (Object) this.userName;
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public UserConfigurationInterface getUserConfigurationInterface()
   {
      return userConfigurationInterface;
   }
   
   public void setUserConfigurationInterface(UserConfigurationInterface userConfigurationInterface)
   {
      this.userConfigurationInterface = userConfigurationInterface;
   }
}