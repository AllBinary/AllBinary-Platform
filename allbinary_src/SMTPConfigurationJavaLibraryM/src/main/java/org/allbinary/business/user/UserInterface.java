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
package org.allbinary.business.user;

import java.util.HashMap;

import org.allbinary.business.user.modules.configuration.UserConfigurationInterface;
import org.allbinary.business.user.role.UserRole;
import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionInterface;
import org.allbinary.logic.control.validate.ValidationInterface;

public interface UserInterface
    extends TableMappingInterface, ValidationInterface
{
   void setUserName(String value);
   
   void setPrefixName(String value);
   
   void setFirstName(String value);
   
   void setLastName(String value);
   
   void setMiddleName(String value);
   
   void setSuffixName(String value);
   
   void setCompany(String value);
   
   void setPositionAtCompany(String value);
   
   void setMainEmail(String value);
   
   void setSecondaryEmail(String value);
   
   void setHomePhone(String value);
   
   void setCellPhone(String value);
   
   void setWorkPhone(String value);
   
   void setOtherContact(String value);
   
   void setElectronicContact(String value);
   
   void setFax(String value);
   
   void setRole(UserRole aRole);
   
   void setEncryption(String value);
   
   void setSecret(String value);
   
   void setPassword(String value);
   
   void setPermissions(String value);
   
   void enable();
   
   void setEnable(String enable);
   
   String getUserName();
   
   String getPrefixName();
   
   String getFirstName();
   
   String getLastName();
   
   String getMiddleName();
   
   String getSuffixName();
   
   String getCompany();
   
   String getPositionAtCompany();
   
   String getMainEmail();
   
   String getSecondaryEmail();
   
   String getHomePhone();
   
   String getCellPhone();
   
   String getWorkPhone();
   
   String getOtherContact();
   
   String getElectronicContact();
   
   String getFax();
   
   UserRole getRole();
   
   String getEncryption();
   
   String getSecret();
   
   String getPassword();
   
   String getPermissions();
   
   UserConfigurationInterface getUserConfigurationInterface();
   void setUserConfigurationInterface(UserConfigurationInterface userConfigurationInterface);

   HashMap toPasswordHashMap();
   //public boolean hasPermission(RequestParams requestParams);
   
   //public void validateSession(
     // WeblisketSession weblisketSession, RequestParams requestParams);
   
   //public void updateSession(WeblisketSession weblisketSession, 
     // RequestParams requestParams);

   void validateSession(WeblisketSessionInterface weblisketSession);
   
   void updateSession(WeblisketSessionInterface weblisketSession);
   
   Boolean isSessionValid();
   
}
