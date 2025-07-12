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
package org.allbinary.business.installer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.allbinary.business.init.db.HistoryDbInitInfo;
import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.init.db.LogDbInitInfo;
import org.allbinary.business.init.db.StaticPagesDbInitInfo;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonStrings;

public class Initializer
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private HttpServletRequest request;

    private String userJdbcDriver;
    private String userName;
    private String userUserName;
    private String userPassword;
    private String userSchema;
    private String userServer;
    private String userPort;
    private String historyJdbcDriver;
    private String historyName;
    private String historyUserName;
    private String historyPassword;
    private String historySchema;
    private String historyServer;
    private String historyPort;
    private String logJdbcDriver;
    private String logName;
    private String logUserName;
    private String logPassword;
    private String logSchema;
    private String logServer;
    private String logPort;
    private String inventoryJdbcDriver;
    private String inventoryName;
    private String inventoryUserName;
    private String inventoryPassword;
    private String inventorySchema;
    private String inventoryServer;
    private String inventoryPort;
    private String staticPagesJdbcDriver;
    private String staticPagesName;
    private String staticPagesUserName;
    private String staticPagesPassword;
    private String staticPagesSchema;
    private String staticPagesServer;
    private String staticPagesPort;
    
    private static final int MAXDB = 30;
    private static final int MAX = 16;
    private static final int MIN = 4;
    private static final int MINPASSWORD = 0;

    public Initializer(Map map)
    {
        final StringMaker stringBuffer = new StringMaker();
        
        //    this.request = request;
        //this.getFormData(request.getParameterMap());
        HashMap hashMap = new HashMap();
        Set keys = map.keySet();

        final Object[] keyArray = keys.toArray();
        final int size = keyArray.length;
        for(int index = 0; index < size; index++)
        {
            String key = (String) keyArray[index];
            String[] values = (String[]) map.get(key);
            hashMap.put(new String(key), new String(values[0]));
         
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append("key: ");
            stringBuffer.append(key);
            stringBuffer.append(" Value: ");
            stringBuffer.append(values[0]);
            
            logUtil.put(stringBuffer.toString(), this, "getFormData()");
        }
        this.getFormData(hashMap);
    }

    public Initializer(HashMap initHashMap)
    {
        this.getFormData(initHashMap);
    }

    public void getFormData(HashMap hashMap)
    {
        try
        {
        	InitializerData initializerData = InitializerData.getInstance();

            userJdbcDriver = (String) hashMap.get(initializerData.CUSTOMERJDBCDRIVER);
            userName = (String) hashMap.get(initializerData.CUSTOMERNAME);
            userUserName = (String) hashMap.get(initializerData.CUSTOMERUSERNAME);
            userPassword = (String) hashMap.get(initializerData.CUSTOMERPASSWORD);

            userSchema = (String) hashMap.get(initializerData.CUSTOMERSCHEMA);
            userServer = (String) hashMap.get(initializerData.CUSTOMERSERVER);
            userPort = (String) hashMap.get(initializerData.CUSTOMERPORT);

            historyJdbcDriver = (String) hashMap.get(initializerData.HISTORYJDBCDRIVER);
            historyName = (String) hashMap.get(initializerData.HISTORYNAME);
            historyUserName = (String) hashMap.get(initializerData.HISTORYUSERNAME);
            historyPassword = (String) hashMap.get(initializerData.HISTORYPASSWORD);

            historySchema = (String) hashMap.get(initializerData.HISTORYSCHEMA);
            historyServer = (String) hashMap.get(initializerData.HISTORYSERVER);
            historyPort = (String) hashMap.get(initializerData.HISTORYPORT);

            logJdbcDriver = (String) hashMap.get(initializerData.LOGJDBCDRIVER);
            logName = (String) hashMap.get(initializerData.LOGNAME);
            logUserName = (String) hashMap.get(initializerData.LOGUSERNAME);
            logPassword = (String) hashMap.get(initializerData.LOGPASSWORD);

            logSchema = (String) hashMap.get(initializerData.LOGSCHEMA);
            logServer = (String) hashMap.get(initializerData.LOGSERVER);
            logPort = (String) hashMap.get(initializerData.LOGPORT);

            inventoryJdbcDriver = (String) hashMap.get(initializerData.INVENTORYJDBCDRIVER);
            inventoryName = (String) hashMap.get(initializerData.INVENTORYNAME);
            inventoryUserName = (String) hashMap.get(initializerData.INVENTORYUSERNAME);
            inventoryPassword = (String) hashMap.get(initializerData.INVENTORYPASSWORD);

            inventorySchema = (String) hashMap.get(initializerData.INVENTORYSCHEMA);
            inventoryServer = (String) hashMap.get(initializerData.INVENTORYSERVER);
            inventoryPort = (String) hashMap.get(initializerData.INVENTORYPORT);

            staticPagesJdbcDriver = (String) hashMap.get(initializerData.STATICPAGESJDBCDRIVER);
            staticPagesName = (String) hashMap.get(initializerData.STATICPAGESNAME);
            staticPagesUserName = (String) hashMap.get(initializerData.STATICPAGESUSERNAME);
            staticPagesPassword = (String) hashMap.get(initializerData.STATICPAGESPASSWORD);

            staticPagesSchema = (String) hashMap.get(initializerData.STATICPAGESSCHEMA);
            staticPagesServer = (String) hashMap.get(initializerData.STATICPAGESSERVER);
            staticPagesPort = (String) hashMap.get(initializerData.STATICPAGESPORT);

        } catch (Exception e)
        {
            logUtil.put("Unable to get form data", this, "getFormData()", e);
        }
    }

    private boolean isJdbcDriverValid(String jdbcDriverClassPathString)
    {
        try
        {
            Class.forName(jdbcDriverClassPathString).newInstance();
            return true;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.IS_VALID, e);
            }
            return false;
        }
    }

    public boolean isValid()
    {
        boolean isValid = true;

        if (!this.isJdbcDriverValid(this.userJdbcDriver))
        {
            isValid = false;
        }

        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (!stringValidationUtil.isValidRequired(userName, MIN, MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(userUserName, MIN, MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(userPassword, MINPASSWORD, MAX))
        {
            isValid = false;
        }

        if (!this.isJdbcDriverValid(this.historyJdbcDriver))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(historyName, MIN, MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(historyUserName, MIN, MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(historyPassword, MINPASSWORD, MAX))
        {
            isValid = false;
        }

        if (!this.isJdbcDriverValid(this.logJdbcDriver))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.logName, MIN, MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.logUserName, MIN, MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(this.logPassword, MINPASSWORD, MAX))
        {
            isValid = false;
        }

        if (!this.isJdbcDriverValid(this.inventoryJdbcDriver))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(inventoryName, MIN, MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(inventoryUserName, MIN, MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(inventoryPassword, MINPASSWORD, MAX))
        {
            isValid = false;
        }

        if (!this.isJdbcDriverValid(this.staticPagesJdbcDriver))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(staticPagesName, MIN, MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(staticPagesUserName, MIN, MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(staticPagesPassword, MINPASSWORD, MAX))
        {
            isValid = false;
        }

        return isValid;
    }

    private String getJdbcDriverValidationInfo(String jdbcDriver)
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append("The JDBC driver (");
        stringBuffer.append(jdbcDriver);
        stringBuffer.append(") you have provided is not valid.<br/>");

        return stringBuffer.toString();
    }

    private String getJdbcDriverSolutionInfo()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("The following describes the possible problems and solutions regarding the Jdbc Driver error(s):<p/>");
        stringBuffer.append("1. A JDBC driver you specified is not in your classpath.<br/>");

        stringBuffer.append("Solution 1: Move the JDBC driver into any directory specified in the existing classpath. <br/>");
        stringBuffer.append("Solution 2: Add the directory that contains the JDBC driver to the classpath.<br/>");
        stringBuffer.append("Solution 3: Add the JDBC driver to the WEB-INF/lib directory where you installed this webapp.<br/>");

        stringBuffer.append("2. The JDBC driver you specified does not exit.<br/>");

        stringBuffer.append("Solution: Get a JDBC driver. <br/>");

        stringBuffer.append("3. The JDBC driver you specified is not valid.<br/>");

        stringBuffer.append("Solution: Use a valid JDBC driver.<p/>");

        return stringBuffer.toString();
    }

    public String getInvalidInfo()
    {
        //boolean isValid = true;
        boolean isJdbcDriversValid = true;
        StringMaker stringBuffer = new StringMaker();

        if (!this.isJdbcDriverValid(this.userJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.userJdbcDriver));
        }

        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (!stringValidationUtil.isValidRequired(userName, MIN, MAXDB))
        {
            stringBuffer.append("User db name should be < " + MAXDB + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(userUserName, MIN, MAX))
        {
            stringBuffer.append("User db username should be < " + MAX + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(userPassword, MINPASSWORD, MAX))
        {
            stringBuffer.append("User DB password should be < " + MAX + " and > " + MINPASSWORD + " characters in length.<br />");
        }

        if (!this.isJdbcDriverValid(this.historyJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.historyJdbcDriver));
        }

        if (!stringValidationUtil.isValidRequired(historyName, MIN, MAXDB))
        {
            stringBuffer.append("History db should be < " + MAXDB + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(historyUserName, MIN, MAX))
        {
            stringBuffer.append("History db username should be < " + MAX + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(historyPassword, MINPASSWORD, MAX))
        {
            stringBuffer.append("History db password should be < " + MAX + " and > " + MINPASSWORD + " characters in length.<br />");
        }

        if (!this.isJdbcDriverValid(this.logJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.logJdbcDriver));
        }

        if (!stringValidationUtil.isValidRequired(this.logName, MIN, MAXDB))
        {
            stringBuffer.append("Log db should be < " + MAXDB + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(this.logUserName, MIN, MAX))
        {
            stringBuffer.append("Log db username should be < " + MAX + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(this.logPassword, MINPASSWORD, MAX))
        {
            stringBuffer.append("Log db password should be < " + MAX + " and > " + MINPASSWORD + " characters in length.<br />");
        }

        if (!this.isJdbcDriverValid(this.inventoryJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.inventoryJdbcDriver));
        }

        if (!stringValidationUtil.isValidRequired(inventoryName, MIN, MAXDB))
        {
            stringBuffer.append("Inventory db should be < " + MAXDB + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(inventoryUserName, MIN, MAX))
        {
            stringBuffer.append("Inventory db username should be < " + MAX + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(inventoryPassword, MINPASSWORD, MAX))
        {
            stringBuffer.append("Inventory DB password should be < " + MAX + " and > " + MINPASSWORD + " characters in length.<br />");
        }

        if (!this.isJdbcDriverValid(this.staticPagesJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.staticPagesJdbcDriver));
        }

        if (!stringValidationUtil.isValidRequired(staticPagesName, MIN, MAXDB))
        {
            stringBuffer.append("Static Pages db should be < " + MAXDB + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(staticPagesUserName, MIN, MAX))
        {
            stringBuffer.append("Static Pages db username should be < " + MAX + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(staticPagesPassword, MINPASSWORD, MAX))
        {
            stringBuffer.append("Static Pages DB password should be < " + MAX + " and > " + MINPASSWORD + " characters in length.<br />");
        }

        if (!isJdbcDriversValid)
        {
            stringBuffer.append(getJdbcDriverSolutionInfo());
        }

        return stringBuffer.toString();
    }

    /*
    private void set(DbInitInfoInterface dbInitInfoInterface, DbConnectionInfo)
    {
    dbInitInfoInterface.setName(userName);
    dbInitInfoInterface.setUserName(userUserName);
    dbInitInfoInterface.setPassword(userPassword);
    dbInitInfoInterface.setHasRead(true);
    dbInitInfoInterface.write();
    }
     */
    public void set() throws Exception
    {
        logUtil.put("Creating DB connection files", this, "set()");

        UserDbInitInfo userDbInitInfo = new UserDbInitInfo(false);
        userDbInitInfo.setJdbcDriver(this.userJdbcDriver);
        userDbInitInfo.setName(userName);
        userDbInitInfo.setUserName(userUserName);
        userDbInitInfo.setPassword(userPassword);
        userDbInitInfo.setSchema(userSchema);
        userDbInitInfo.setServer(userServer);
        userDbInitInfo.setPort(userPort);
        userDbInitInfo.setHasRead(true);
        userDbInitInfo.write();

        HistoryDbInitInfo historyDbInitInfo = new HistoryDbInitInfo(false);
        historyDbInitInfo.setJdbcDriver(this.historyJdbcDriver);
        historyDbInitInfo.setName(historyName);
        historyDbInitInfo.setUserName(historyUserName);
        historyDbInitInfo.setPassword(historyPassword);

        historyDbInitInfo.setSchema(historySchema);
        historyDbInitInfo.setServer(historyServer);
        historyDbInitInfo.setPort(historyPort);
        historyDbInitInfo.setHasRead(true);
        historyDbInitInfo.write();

        LogDbInitInfo logDbInitInfo = new LogDbInitInfo(false);
        logDbInitInfo.setJdbcDriver(this.logJdbcDriver);
        logDbInitInfo.setName(this.logName);
        logDbInitInfo.setUserName(this.logUserName);
        logDbInitInfo.setPassword(this.logPassword);

        logDbInitInfo.setSchema(this.logSchema);
        logDbInitInfo.setServer(this.logServer);
        logDbInitInfo.setPort(this.logPort);
        logDbInitInfo.setHasRead(true);
        logDbInitInfo.write();

        StaticPagesDbInitInfo staticPagesDbInitInfo = new StaticPagesDbInitInfo(false);
        staticPagesDbInitInfo.setJdbcDriver(this.staticPagesJdbcDriver);
        staticPagesDbInitInfo.setName(staticPagesName);
        staticPagesDbInitInfo.setUserName(staticPagesUserName);
        staticPagesDbInitInfo.setPassword(staticPagesPassword);

        staticPagesDbInitInfo.setSchema(staticPagesSchema);
        staticPagesDbInitInfo.setServer(staticPagesServer);
        staticPagesDbInitInfo.setPort(staticPagesPort);
        staticPagesDbInitInfo.setHasRead(true);
        staticPagesDbInitInfo.write();

        InventoryDbInitInfo inventoryDbInitInfo = new InventoryDbInitInfo(false);
        inventoryDbInitInfo.setJdbcDriver(this.inventoryJdbcDriver);
        inventoryDbInitInfo.setName(inventoryName);
        inventoryDbInitInfo.setUserName(inventoryUserName);
        inventoryDbInitInfo.setPassword(inventoryPassword);

        inventoryDbInitInfo.setSchema(inventorySchema);
        inventoryDbInitInfo.setServer(inventoryServer);
        inventoryDbInitInfo.setPort(inventoryPort);
        inventoryDbInitInfo.setHasRead(true);
        inventoryDbInitInfo.write();

        logUtil.put("Created DB connection files", this, "set()");
    }
}
