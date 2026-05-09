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
            
            this.logUtil.putF(stringBuffer.toString(), this, "getFormData()");
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

            this.userJdbcDriver = (String) hashMap.get(initializerData.CUSTOMERJDBCDRIVER);
            this.userName = (String) hashMap.get(initializerData.CUSTOMERNAME);
            this.userUserName = (String) hashMap.get(initializerData.CUSTOMERUSERNAME);
            this.userPassword = (String) hashMap.get(initializerData.CUSTOMERPASSWORD);

            this.userSchema = (String) hashMap.get(initializerData.CUSTOMERSCHEMA);
            this.userServer = (String) hashMap.get(initializerData.CUSTOMERSERVER);
            this.userPort = (String) hashMap.get(initializerData.CUSTOMERPORT);

            this.historyJdbcDriver = (String) hashMap.get(initializerData.HISTORYJDBCDRIVER);
            this.historyName = (String) hashMap.get(initializerData.HISTORYNAME);
            this.historyUserName = (String) hashMap.get(initializerData.HISTORYUSERNAME);
            this.historyPassword = (String) hashMap.get(initializerData.HISTORYPASSWORD);

            this.historySchema = (String) hashMap.get(initializerData.HISTORYSCHEMA);
            this.historyServer = (String) hashMap.get(initializerData.HISTORYSERVER);
            this.historyPort = (String) hashMap.get(initializerData.HISTORYPORT);

            this.logJdbcDriver = (String) hashMap.get(initializerData.LOGJDBCDRIVER);
            this.logName = (String) hashMap.get(initializerData.LOGNAME);
            this.logUserName = (String) hashMap.get(initializerData.LOGUSERNAME);
            this.logPassword = (String) hashMap.get(initializerData.LOGPASSWORD);

            this.logSchema = (String) hashMap.get(initializerData.LOGSCHEMA);
            this.logServer = (String) hashMap.get(initializerData.LOGSERVER);
            this.logPort = (String) hashMap.get(initializerData.LOGPORT);

            this.inventoryJdbcDriver = (String) hashMap.get(initializerData.INVENTORYJDBCDRIVER);
            this.inventoryName = (String) hashMap.get(initializerData.INVENTORYNAME);
            this.inventoryUserName = (String) hashMap.get(initializerData.INVENTORYUSERNAME);
            this.inventoryPassword = (String) hashMap.get(initializerData.INVENTORYPASSWORD);

            this.inventorySchema = (String) hashMap.get(initializerData.INVENTORYSCHEMA);
            this.inventoryServer = (String) hashMap.get(initializerData.INVENTORYSERVER);
            this.inventoryPort = (String) hashMap.get(initializerData.INVENTORYPORT);

            this.staticPagesJdbcDriver = (String) hashMap.get(initializerData.STATICPAGESJDBCDRIVER);
            this.staticPagesName = (String) hashMap.get(initializerData.STATICPAGESNAME);
            this.staticPagesUserName = (String) hashMap.get(initializerData.STATICPAGESUSERNAME);
            this.staticPagesPassword = (String) hashMap.get(initializerData.STATICPAGESPASSWORD);

            this.staticPagesSchema = (String) hashMap.get(initializerData.STATICPAGESSCHEMA);
            this.staticPagesServer = (String) hashMap.get(initializerData.STATICPAGESSERVER);
            this.staticPagesPort = (String) hashMap.get(initializerData.STATICPAGESPORT);

        } catch (Exception e)
        {
            this.logUtil.put("Unable to get form data", this, "getFormData()", e);
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
                this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.IS_VALID, e);
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
        
        if (!stringValidationUtil.isValidRequired(this.userName, Initializer.MIN, Initializer.MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.userUserName, Initializer.MIN, Initializer.MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(this.userPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            isValid = false;
        }

        if (!this.isJdbcDriverValid(this.historyJdbcDriver))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.historyName, Initializer.MIN, Initializer.MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.historyUserName, Initializer.MIN, Initializer.MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(this.historyPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            isValid = false;
        }

        if (!this.isJdbcDriverValid(this.logJdbcDriver))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.logName, Initializer.MIN, Initializer.MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.logUserName, Initializer.MIN, Initializer.MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(this.logPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            isValid = false;
        }

        if (!this.isJdbcDriverValid(this.inventoryJdbcDriver))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.inventoryName, Initializer.MIN, Initializer.MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.inventoryUserName, Initializer.MIN, Initializer.MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(this.inventoryPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            isValid = false;
        }

        if (!this.isJdbcDriverValid(this.staticPagesJdbcDriver))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.staticPagesName, Initializer.MIN, Initializer.MAXDB))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidRequired(this.staticPagesUserName, Initializer.MIN, Initializer.MAX))
        {
            isValid = false;
        }

        if (!stringValidationUtil.isValidNotRequired(this.staticPagesPassword, Initializer.MINPASSWORD, Initializer.MAX))
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
        
        if (!stringValidationUtil.isValidRequired(this.userName, Initializer.MIN, Initializer.MAXDB))
        {
            stringBuffer.append("User db name should be < " + Initializer.MAXDB + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(this.userUserName, Initializer.MIN, Initializer.MAX))
        {
            stringBuffer.append("User db username should be < " + Initializer.MAX + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(this.userPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            stringBuffer.append("User DB password should be < " + Initializer.MAX + " and > " + Initializer.MINPASSWORD + " characters in length.<br />");
        }

        if (!this.isJdbcDriverValid(this.historyJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.historyJdbcDriver));
        }

        if (!stringValidationUtil.isValidRequired(this.historyName, Initializer.MIN, Initializer.MAXDB))
        {
            stringBuffer.append("History db should be < " + Initializer.MAXDB + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(this.historyUserName, Initializer.MIN, Initializer.MAX))
        {
            stringBuffer.append("History db username should be < " + Initializer.MAX + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(this.historyPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            stringBuffer.append("History db password should be < " + Initializer.MAX + " and > " + Initializer.MINPASSWORD + " characters in length.<br />");
        }

        if (!this.isJdbcDriverValid(this.logJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.logJdbcDriver));
        }

        if (!stringValidationUtil.isValidRequired(this.logName, Initializer.MIN, Initializer.MAXDB))
        {
            stringBuffer.append("Log db should be < " + Initializer.MAXDB + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(this.logUserName, Initializer.MIN, Initializer.MAX))
        {
            stringBuffer.append("Log db username should be < " + Initializer.MAX + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(this.logPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            stringBuffer.append("Log db password should be < " + Initializer.MAX + " and > " + Initializer.MINPASSWORD + " characters in length.<br />");
        }

        if (!this.isJdbcDriverValid(this.inventoryJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.inventoryJdbcDriver));
        }

        if (!stringValidationUtil.isValidRequired(this.inventoryName, Initializer.MIN, Initializer.MAXDB))
        {
            stringBuffer.append("Inventory db should be < " + Initializer.MAXDB + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(this.inventoryUserName, Initializer.MIN, Initializer.MAX))
        {
            stringBuffer.append("Inventory db username should be < " + Initializer.MAX + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(this.inventoryPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            stringBuffer.append("Inventory DB password should be < " + Initializer.MAX + " and > " + Initializer.MINPASSWORD + " characters in length.<br />");
        }

        if (!this.isJdbcDriverValid(this.staticPagesJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.staticPagesJdbcDriver));
        }

        if (!stringValidationUtil.isValidRequired(this.staticPagesName, Initializer.MIN, Initializer.MAXDB))
        {
            stringBuffer.append("Static Pages db should be < " + Initializer.MAXDB + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidRequired(this.staticPagesUserName, Initializer.MIN, Initializer.MAX))
        {
            stringBuffer.append("Static Pages db username should be < " + Initializer.MAX + " and > " + Initializer.MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(this.staticPagesPassword, Initializer.MINPASSWORD, Initializer.MAX))
        {
            stringBuffer.append("Static Pages DB password should be < " + Initializer.MAX + " and > " + Initializer.MINPASSWORD + " characters in length.<br />");
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
    dbInitInfoInterface.setName(this.userName);
    dbInitInfoInterface.setUserName(this.userUserName);
    dbInitInfoInterface.setPassword(this.userPassword);
    dbInitInfoInterface.setHasRead(true);
    dbInitInfoInterface.write();
    }
     */
    public void set() throws Exception
    {
        this.logUtil.putF("Creating DB connection files", this, "set()");

        UserDbInitInfo userDbInitInfo = new UserDbInitInfo(false);
        userDbInitInfo.setJdbcDriver(this.userJdbcDriver);
        userDbInitInfo.setName(this.userName);
        userDbInitInfo.setUserName(this.userUserName);
        userDbInitInfo.setPassword(this.userPassword);
        userDbInitInfo.setSchema(this.userSchema);
        userDbInitInfo.setServer(this.userServer);
        userDbInitInfo.setPort(this.userPort);
        userDbInitInfo.setHasRead(true);
        userDbInitInfo.write();

        HistoryDbInitInfo historyDbInitInfo = new HistoryDbInitInfo(false);
        historyDbInitInfo.setJdbcDriver(this.historyJdbcDriver);
        historyDbInitInfo.setName(this.historyName);
        historyDbInitInfo.setUserName(this.historyUserName);
        historyDbInitInfo.setPassword(this.historyPassword);

        historyDbInitInfo.setSchema(this.historySchema);
        historyDbInitInfo.setServer(this.historyServer);
        historyDbInitInfo.setPort(this.historyPort);
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
        staticPagesDbInitInfo.setName(this.staticPagesName);
        staticPagesDbInitInfo.setUserName(this.staticPagesUserName);
        staticPagesDbInitInfo.setPassword(this.staticPagesPassword);

        staticPagesDbInitInfo.setSchema(this.staticPagesSchema);
        staticPagesDbInitInfo.setServer(this.staticPagesServer);
        staticPagesDbInitInfo.setPort(this.staticPagesPort);
        staticPagesDbInitInfo.setHasRead(true);
        staticPagesDbInitInfo.write();

        InventoryDbInitInfo inventoryDbInitInfo = new InventoryDbInitInfo(false);
        inventoryDbInitInfo.setJdbcDriver(this.inventoryJdbcDriver);
        inventoryDbInitInfo.setName(this.inventoryName);
        inventoryDbInitInfo.setUserName(this.inventoryUserName);
        inventoryDbInitInfo.setPassword(this.inventoryPassword);

        inventoryDbInitInfo.setSchema(this.inventorySchema);
        inventoryDbInitInfo.setServer(this.inventoryServer);
        inventoryDbInitInfo.setPort(this.inventoryPort);
        inventoryDbInitInfo.setHasRead(true);
        inventoryDbInitInfo.write();

        this.logUtil.putF("Created DB connection files", this, "set()");
    }
}
