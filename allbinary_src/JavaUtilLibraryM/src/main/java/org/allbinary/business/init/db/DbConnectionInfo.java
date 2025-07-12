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

/*
User Database
Set Name: securedc_user
Set UserName: securedc_cutome
Set Password: user835

History Database
Set Name: securedc_history
Set UserName: securedc_history
Set Password: history835

Log Database
Set Name: securedc_securelog
Set UserName: securedc_securel
Set Password: securesite835

StaticPages Database
securedc_static
securedc_static
securesite835

Inventory Database
securedc_inventory
securedc_invento
inventory835
 */
package org.allbinary.business.init.db;

import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;

public class DbConnectionInfo implements DatabaseConnectionInfoInterface
{
    private String jdbcDriver;
    private String name;
    private String userName;
    private String password;
    private String schema;
    private String server;
    private String port;

    private String url;
    private String host;

    private final String SCHEMA_SEP = "://";
    private final String USER_NAME_KEY = "?user=";
    private final String PASSWORD_KEY = "&password=";
    
    public DbConnectionInfo()
    {
    }

    private void updateUrl()
    {
        this.updateHost();
        
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.getHost());
        stringBuffer.append(this.getName());
        stringBuffer.append(this.getUserNameKey());
        stringBuffer.append(this.getUserName());
        stringBuffer.append(this.getPasswordKey());
        stringBuffer.append(this.getPassword());

        this.url = stringBuffer.toString();
    }

    public String getUrl()
    {
        return url;
    }

    public String getJdbcDriver()
    {
        return this.jdbcDriver;
    }

    public String getName()
    {
        return this.name;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public String getPassword()
    {
        return this.password;
    }

    private void updateHost()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.getSchema());
        stringBuffer.append(this.SCHEMA_SEP);
        stringBuffer.append(this.getServer());
        
        if (this.getPort() != null && this.getPort().length() > 1)
        {
            stringBuffer.append(CommonSeps.getInstance().COLON);
            stringBuffer.append(this.getPort());
        }

        stringBuffer.append(AbPathData.getInstance().SEPARATOR);

        this.host = stringBuffer.toString();
    }

    public String getHost()
    {
        return this.host;
    }

    public String getSchema()
    {
        return this.schema;
    }

    public String getServer()
    {
        return this.server;
    }

    public String getPort()
    {
        return this.port;
    }

    public void setJdbcDriver(String value)
    {
        this.jdbcDriver = value;
    }

    public void setName(String value)
    {
        this.name = value;
        this.updateUrl();
    }

    public void setUserName(String value)
    {
        this.userName = value;
        this.updateUrl();
    }

    public void setPassword(String value)
    {
        this.password = value;
        this.updateUrl();
    }

    public void setSchema(String value)
    {
        this.schema = value;
        this.updateUrl();
    }

    public void setServer(String value)
    {
        this.server = value;
        this.updateUrl();
    }

    public void setPort(String value)
    {
        this.port = value;
        this.updateUrl();
    }

    public final String getUserNameKey()
    {
        return USER_NAME_KEY;
    }

    public final String getPasswordKey()
    {
        return PASSWORD_KEY;
    }
}
