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
   public void setUserName(String value);
   
   public void setPrefixName(String value);
   
   public void setFirstName(String value);
   
   public void setLastName(String value);
   
   public void setMiddleName(String value);
   
   public void setSuffixName(String value);
   
   public void setCompany(String value);
   
   public void setPositionAtCompany(String value);
   
   public void setMainEmail(String value);
   
   public void setSecondaryEmail(String value);
   
   public void setHomePhone(String value);
   
   public void setCellPhone(String value);
   
   public void setWorkPhone(String value);
   
   public void setOtherContact(String value);
   
   public void setElectronicContact(String value);
   
   public void setFax(String value);
   
   public void setRole(UserRole aRole);
   
   public void setEncryption(String value);
   
   public void setSecret(String value);
   
   public void setPassword(String value);
   
   public void setPermissions(String value);
   
   public void enable();
   
   public void setEnable(String enable);
   
   public String getUserName();
   
   public String getPrefixName();
   
   public String getFirstName();
   
   public String getLastName();
   
   public String getMiddleName();
   
   public String getSuffixName();
   
   public String getCompany();
   
   public String getPositionAtCompany();
   
   public String getMainEmail();
   
   public String getSecondaryEmail();
   
   public String getHomePhone();
   
   public String getCellPhone();
   
   public String getWorkPhone();
   
   public String getOtherContact();
   
   public String getElectronicContact();
   
   public String getFax();
   
   public UserRole getRole();
   
   public String getEncryption();
   
   public String getSecret();
   
   public String getPassword();
   
   public String getPermissions();
   
   public UserConfigurationInterface getUserConfigurationInterface();
   public void setUserConfigurationInterface(UserConfigurationInterface userConfigurationInterface);

   public HashMap toPasswordHashMap();
   //public boolean hasPermission(RequestParams requestParams);
   
   //public void validateSession(
     // WeblisketSession weblisketSession, RequestParams requestParams);
   
   //public void updateSession(WeblisketSession weblisketSession, 
     // RequestParams requestParams);

   public void validateSession(WeblisketSessionInterface weblisketSession);
   
   public void updateSession(WeblisketSessionInterface weblisketSession);
   
   public Boolean isSessionValid();
   
}
