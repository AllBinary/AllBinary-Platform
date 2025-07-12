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

import org.allbinary.logic.string.StringMaker;

public class InitializerData {

	private static final InitializerData instance = new InitializerData();

	public static InitializerData getInstance() {
		return instance;
	}

    public final String DBUSER = "DBUSER";
    public final String DBPASSWORD = "DBPASSWORD";
    public final String ADMINJDBCDRIVER = "ADMIN_JDBCDRIVER";
    public final String ADMINSCHEMA = "ADMINSCHEMA";
    public final String ADMINSERVER = "ADMINSERVER";
    public final String ADMINPORT = "ADMINPORT";
    public final String CUSTOMERJDBCDRIVER = "CUSTOMER_JDBCDRIVER";
    public final String CUSTOMERNAME = "CUSTOMERNAME";
    public final String CUSTOMERUSERNAME = "CUSTOMERUSERNAME";
    public final String CUSTOMERPASSWORD = "CUSTOMERPASSWORD";
    public final String CUSTOMERSCHEMA = "CUSTOMERSCHEMA";
    public final String CUSTOMERSERVER = "CUSTOMERSERVER";
    public final String CUSTOMERPORT = "CUSTOMERPORT";
    public final String HISTORYJDBCDRIVER = "HISTORY_JDBCDRIVER";
    public final String HISTORYNAME = "HISTORYNAME";
    public final String HISTORYUSERNAME = "HISTORYUSERNAME";
    public final String HISTORYPASSWORD = "HISTORYPASSWORD";
    public final String HISTORYSCHEMA = "HISTORYSCHEMA";
    public final String HISTORYSERVER = "HISTORYSERVER";
    public final String HISTORYPORT = "HISTORYPORT";
    public final String LOGJDBCDRIVER = "LOG_JDBCDRIVER";
    public final String LOGNAME = "LOGNAME";
    public final String LOGUSERNAME = "LOGUSERNAME";
    public final String LOGPASSWORD = "LOGPASSWORD";
    public final String LOGSCHEMA = "LOGSCHEMA";
    public final String LOGSERVER = "LOGSERVER";
    public final String LOGPORT = "LOGPORT";
    public final String INVENTORYJDBCDRIVER = "INVENTORY_JDBCDRIVER";
    public final String INVENTORYNAME = "INVENTORYNAME";
    public final String INVENTORYUSERNAME = "INVENTORYUSERNAME";
    public final String INVENTORYPASSWORD = "INVENTORYPASSWORD";
    public final String INVENTORYSCHEMA = "INVENTORYSCHEMA";
    public final String INVENTORYSERVER = "INVENTORYSERVER";
    public final String INVENTORYPORT = "INVENTORYPORT";
    public final String STATICPAGESJDBCDRIVER = "STATICPAGES_JDBCDRIVER";
    public final String STATICPAGESNAME = "STATICPAGESNAME";
    public final String STATICPAGESUSERNAME = "STATICPAGESUSERNAME";
    public final String STATICPAGESPASSWORD = "STATICPAGESPASSWORD";
    public final String STATICPAGESSCHEMA = "STATICPAGESSCHEMA";
    public final String STATICPAGESSERVER = "STATICPAGESSERVER";
    public final String STATICPAGESPORT = "STATICPAGESPORT";

    public String getJdbcDriverSolutionInfo()
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

}
