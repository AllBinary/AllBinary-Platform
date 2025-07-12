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

import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class InitializerDatabase
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private HttpServletRequest request;

    private String adminDbUserName;
    private String adminDbPassword;
    private String adminJdbcDriver;
    private String adminSchema;
    private String adminServer;
    private String adminPort;
    
    private static final int MAXDB = 30;
    private static final int MAX = 16;
    private static final int MIN = 4;
    private static final int MINPASSWORD = 0;
    private DynamicInitDb initDb;

    public InitializerDatabase(final AbeClientInformationInterface abeClientInformation, final Map map)
    {
        final StringMaker stringBuffer = new StringMaker();
        
        //    this.request = request;
        //this.getFormData(request.getParameterMap());
        final HashMap hashMap = new HashMap();
        final Set keys = map.keySet();

        final Object[] keyArray = keys.toArray();
        final int size = keyArray.length;
        for(int index = 0; index < size; index++)
        {
            final String key = (String) keyArray[index];
            final String[] values = (String[]) map.get(key);
            hashMap.put(new String(key), new String(values[0]));
         
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append("key: ");
            stringBuffer.append(key);
            stringBuffer.append(" Value: ");
            stringBuffer.append(values[0]);
            
            logUtil.put(stringBuffer.toString(), this, "getFormData()");
        }
        this.getFormData(abeClientInformation, hashMap);
    }

    public InitializerDatabase(final AbeClientInformationInterface abeClientInformation, final HashMap initHashMap)
    {
        this.getFormData(abeClientInformation, initHashMap);
    }

    public void getFormData(final AbeClientInformationInterface abeClientInformation, final HashMap hashMap)
    {
        try
        {
            InitializerData initializerData = InitializerData.getInstance();
        	
            setAdminDbUserName((String) hashMap.get(initializerData.DBUSER));
            setAdminDbPassword((String) hashMap.get(initializerData.DBPASSWORD));

            setAdminJdbcDriver((String) hashMap.get(initializerData.ADMINJDBCDRIVER));
            setAdminSchema((String) hashMap.get(initializerData.ADMINSCHEMA));
            setAdminServer((String) hashMap.get(initializerData.ADMINSERVER));
            setAdminPort((String) hashMap.get(initializerData.ADMINPORT));

            DbConnectionInfo dbConnectionInfo = new DbConnectionInfo();

            String adminDbName = StringUtil.getInstance().EMPTY_STRING;
            dbConnectionInfo.setJdbcDriver(getAdminJdbcDriver());
            dbConnectionInfo.setName(adminDbName);
            dbConnectionInfo.setUserName(getAdminDbUserName());
            dbConnectionInfo.setPassword(getAdminDbPassword());

            dbConnectionInfo.setSchema(getAdminSchema());
            dbConnectionInfo.setServer(getAdminServer());
            dbConnectionInfo.setPort(getAdminPort());

            this.initDb = new DynamicInitDb(abeClientInformation, (DatabaseConnectionInfoInterface) dbConnectionInfo);

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

        if (!this.isJdbcDriverValid(this.adminJdbcDriver))
        {
            isValid = false;
        }

        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if(!stringValidationUtil.isValidRequired(adminDbUserName, MIN, MAX))
        {
            isValid = false;
        }

        if(!stringValidationUtil.isValidNotRequired(adminDbPassword, MINPASSWORD, MAX))
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

    public String getInvalidInfo()
    {
        boolean isValid = true;
        boolean isJdbcDriversValid = true;
        StringMaker stringBuffer = new StringMaker();

        if (!this.isJdbcDriverValid(this.adminJdbcDriver))
        {
            isJdbcDriversValid = false;
            stringBuffer.append(this.getJdbcDriverValidationInfo(this.getAdminJdbcDriver()));
        }

        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (!stringValidationUtil.isValidRequired(adminDbUserName, MIN, MAX))
        {
            stringBuffer.append("Admin username should be < " + MAX + " and > " + MIN + " characters in length.<br />");
        }

        if (!stringValidationUtil.isValidNotRequired(adminDbPassword, MINPASSWORD, MAX))
        {
            stringBuffer.append("Admin password should be < " + MAX + " and > " + MINPASSWORD + " characters in length.<br />");
        }

        if (!isJdbcDriversValid)
        {
            stringBuffer.append(InitializerData.getInstance().getJdbcDriverSolutionInfo());
        }

        return stringBuffer.toString();
    }

    public boolean createUsers()
    {
        try
        {
            initDb.addUsers();
            //initDb.addDatabases();
            return true;
        } catch (Exception e)
        {
            logUtil.put("Unable to Create Users", this, "createUsers()", e);
            return false;
        }
    }

    public boolean createDatabases()
    {
        try
        {
            initDb.addDatabases();
            return true;
        } catch (Exception e)
        {
            logUtil.put("Unable to Create Databases", this, "createDatabases()", e);
            return false;
        }
    }

    public boolean createTables()
    {
        try
        {
            initDb.addTables();
            return true;
        } catch (Exception e)
        {
            logUtil.put("Unable to Create Tables", this, "createTables()", e);
            return false;
        }
    }

    /**
     * @return the adminDbUserName
     */
    public String getAdminDbUserName()
    {
        return adminDbUserName;
    }

    /**
     * @param adminDbUserName the adminDbUserName to set
     */
    public void setAdminDbUserName(String adminDbUserName)
    {
        this.adminDbUserName = adminDbUserName;
    }

    /**
     * @return the adminDbPassword
     */
    public String getAdminDbPassword()
    {
        return adminDbPassword;
    }

    /**
     * @param adminDbPassword the adminDbPassword to set
     */
    public void setAdminDbPassword(String adminDbPassword)
    {
        this.adminDbPassword = adminDbPassword;
    }

    /**
     * @return the adminJdbcDriver
     */
    public String getAdminJdbcDriver()
    {
        return adminJdbcDriver;
    }

    /**
     * @param adminJdbcDriver the adminJdbcDriver to set
     */
    public void setAdminJdbcDriver(String adminJdbcDriver)
    {
        this.adminJdbcDriver = adminJdbcDriver;
    }

    /**
     * @return the adminSchema
     */
    public String getAdminSchema()
    {
        return adminSchema;
    }

    /**
     * @param adminSchema the adminSchema to set
     */
    public void setAdminSchema(String adminSchema)
    {
        this.adminSchema = adminSchema;
    }

    /**
     * @return the adminServer
     */
    public String getAdminServer()
    {
        return adminServer;
    }

    /**
     * @param adminServer the adminServer to set
     */
    public void setAdminServer(String adminServer)
    {
        this.adminServer = adminServer;
    }

    /**
     * @return the adminPort
     */
    public String getAdminPort()
    {
        return adminPort;
    }

    /**
     * @param adminPort the adminPort to set
     */
    public void setAdminPort(String adminPort)
    {
        this.adminPort = adminPort;
    }
}
