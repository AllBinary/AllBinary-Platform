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
package org.allbinary.business.context.modules.storefront;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;

import org.w3c.dom.Document;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.SpecialCharacterUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.logic.string.tokens.Tokenizer;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.configuration.ContextConfigurationDomDocumentMapping;
import org.allbinary.business.context.configuration.ContextConfigurationInterface;
import org.allbinary.business.context.configuration.ContextConfigurationInterfaceFactory;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.UserData;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.http.request.RequestParams;
import org.allbinary.util.BasicArrayList;

public class StoreFront implements StoreFrontInterface
{
    private final Directory directory = Directory.getInstance();
    
    private String name;
    private String basketName;
    private String homeHostName;
    private AbPath homeHostNamePath;
    private String hostName;
    private AbPath hostNamePath;
    private String testHomeHostName;
    private AbPath testHomeHostNamePath;
    private String testHostName;
    private AbPath testHostNamePath;
    private AbPath imagePath;
    private AbPath staticPath;
    private AbPath categoryPath;
    private String inventoryControl;
    private ContextConfigurationInterface contextConfigurationInterface;
    private String subStores;
    private String tagLocation;
    private String packageLocation;
    private String ftp;
    private AbPath ftpPath;
    private String ftpUserName;
    private String ftpPassword;
    private String testFtp;
    private AbPath testFtpPath;
    private String testFtpUserName;
    private String testFtpPassword;
    private String timeCreated;
    private String lastModified;
    private final int MINCHAR = 0;
    private final int MINSTORENAMELENGTH = 1;
    private final int MAXCHAR = 255;
    private static final String RESOURCES = " Resources" + AbPathData.getInstance().SEPARATOR;

    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
    /*
    public StoreFront()
    {

    }
     */
    public StoreFront(HttpServletRequest request) throws Exception
    {
        this.getFormData(new RequestParams(request).toHashMap());

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
        {
            LogUtil.put(LogFactory.getInstance("TWB- needs update for adding and updating context configuration - currently defaults to admin context config", this, "StoreFront"));
        }

        //TWB - Define Context From request HashMap - new Imp
        //this.setContextConfigurationInterface(
        //(ContextConfigurationInterface) new ContextConfiguration(storeHashMap));
        this.setContextConfigurationInterface(
            ContextConfigurationInterfaceFactory.getInstance("Admin"));
    }

    public StoreFront(HashMap hashMap) throws Exception
    {
        this.getFormData(hashMap);

        String domDocumentString = (String) 
            hashMap.get(StoreFrontData.getInstance().CONFIGURATION);

        Document document = DomDocumentHelper.create(domDocumentString);

        this.setContextConfigurationInterface(
            ContextConfigurationInterfaceFactory.getInstance(document));
    }

    //in the future could use vector from table data
    private boolean isColumn(String columnName)
    {
        if (columnName.compareTo(StoreFrontData.getInstance().NAME) == 0
            || columnName.compareTo(UserData.MAINEMAIL) == 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    //should be in the pathUtil
    private AbPath createPath(String key, HashMap hashMap) throws Exception
    {
        return new AbPath(this.create(EMPTY_STRING, key, hashMap));
    }

    private AbPath createPath(String append, String key, HashMap hashMap) throws Exception
    {
        return new AbPath(this.create(append, key, hashMap));
    }

    private String get(String key, HashMap hashMap)
    {
        return this.create(EMPTY_STRING, key, hashMap);
    }

    private String create(String append, String key, HashMap hashMap)
    {
        String path = (String) hashMap.get(key);

        if (StringValidationUtil.getInstance().isEmpty(path))
        {
            return EMPTY_STRING;
        } else
        {
            if (this.isColumn(path))
            {
                return (String) hashMap.get(path) + append;
            } else
            {
                return path;
            }
        }
    }

    private void getFormData(HashMap storeHashMap) throws Exception
    {
    	StoreFrontData storeFrontData = StoreFrontData.getInstance();
    	
        this.name = (String) storeHashMap.get(storeFrontData.NAME);
        this.basketName = (String) storeHashMap.get(storeFrontData.NAME);
        this.homeHostName = (String) storeHashMap.get(storeFrontData.HOMEHOSTNAME);

        this.homeHostNamePath =
            this.createPath(storeFrontData.HOMEHOSTNAMEPATH, storeHashMap);

        this.hostName = (String) storeHashMap.get(storeFrontData.HOSTNAME);

        this.hostNamePath =
            this.createPath(storeFrontData.HOSTNAMEPATH, storeHashMap);

        this.testHomeHostName = (String) storeHashMap.get(storeFrontData.TESTHOMEHOSTNAME);

        this.testHomeHostNamePath =
            this.createPath(storeFrontData.TESTHOMEHOSTNAMEPATH, storeHashMap);

        this.testHostName = (String) storeHashMap.get(storeFrontData.TESTHOSTNAME);

        this.testHostNamePath =
            this.createPath(storeFrontData.TESTHOSTNAMEPATH, storeHashMap);

        this.imagePath = this.createPath(
            RESOURCES, storeFrontData.IMAGEPATH, storeHashMap);

        this.staticPath = this.createPath(
            " Products" + AbPathData.getInstance().SEPARATOR,
            storeFrontData.STATICPATH, storeHashMap);

        this.categoryPath = this.createPath(
            RESOURCES, storeFrontData.CATEGORYPATH, storeHashMap);

        this.inventoryControl = (String) storeHashMap.get(storeFrontData.INVENTORYCONTROL);

        //All replaced with event driven user email system
        //should any notification occur
        //this.notification = (String) storeHashMap.get(storeFrontData.NOTIFICATION);
        //notification information
        //Uses main email as alternate
        //this.order = this.get(storeFrontData.ORDERNOTIFICATION, storeHashMap);
        //this.orderCancel = this.get(storeFrontData.ORDERCANCELNOTIFICATION, storeHashMap);
        //this.out = this.get(storeFrontData.OUT, storeHashMap);
        //this.age = this.get(storeFrontData.AGE, storeHashMap);
        //this.sale = this.get(storeFrontData.SALE, storeHashMap);

        this.subStores = (String) storeHashMap.get(storeFrontData.SUBSTORES);
        this.tagLocation = (String) storeHashMap.get(storeFrontData.TAGLOCATION);
        this.packageLocation = (String) storeHashMap.get(storeFrontData.PACKAGELOCATION);

        this.ftp = (String) storeHashMap.get(storeFrontData.FTP);
        this.ftpPath = this.createPath(storeFrontData.FTPPATH, storeHashMap);
        this.ftpUserName = (String) storeHashMap.get(storeFrontData.FTPUSERNAME);
        this.ftpPassword = (String) storeHashMap.get(storeFrontData.FTPPASSWORD);

        this.testFtp = (String) storeHashMap.get(storeFrontData.TESTFTP);
        this.testFtpPath = this.createPath(storeFrontData.TESTFTPPATH, storeHashMap);
        this.testFtpUserName = (String) storeHashMap.get(storeFrontData.TESTFTPUSERNAME);
        this.testFtpPassword = (String) storeHashMap.get(storeFrontData.TESTFTPPASSWORD);

        this.timeCreated = (String) storeHashMap.get(EntryData.getInstance().TIMECREATED);
        this.lastModified = (String) storeHashMap.get(EntryData.getInstance().LASTMODIFIED);
    }

    public Boolean isNameValid()
    {
        if (!StringValidationUtil.getInstance().isValidRequired(this.name, MINSTORENAMELENGTH, MAXCHAR))
        {
            return Boolean.FALSE;
        } else
        {
            //Since StoreName is used as the store directory
            boolean isEscapedCharactersContained = false;
            String storeNameEscaped = StringEscapeUtils.escapeHtml(this.name);
            if (this.name.compareTo(storeNameEscaped) != 0)
            {
                isEscapedCharactersContained = true;
            }

            StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
                    
            boolean isSpacesContained = false;
            if (stringValidationUtil.containsSpaces(this.name))
            {
                isSpacesContained = true;
            }

            if (isSpacesContained || isEscapedCharactersContained)
            {
                HashMap hashMap = SpecialCharacterUtil.getHashMap();
                hashMap.put(CommonSeps.getInstance().SPACE, EMPTY_STRING);
                this.name = new Replace(hashMap).all(this.name);
            }

        }
        return Boolean.TRUE;
    }

    public Boolean isValid()
    {
        Boolean valid = Boolean.TRUE;

        valid = this.isNameValid();

        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (!stringValidationUtil.isValidRequired(this.basketName, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidRequired(this.homeHostName, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.homeHostNamePath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidRequired(this.hostName, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidRequired(this.hostNamePath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.testHomeHostName, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.testHomeHostNamePath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.testHostName, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.testHostNamePath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidRequired(this.imagePath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidRequired(this.staticPath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidRequired(this.categoryPath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidRequired(this.inventoryControl, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        /*
        if(!stringValidationUtil.isValidNotRequired(this.out, MINCHAR, MAXCHAR))
        {
        valid = Boolean.FALSE;
        }

        if(!stringValidationUtil.isValidNotRequired(this.age, MINCHAR, MAXCHAR))
        {
        valid = Boolean.FALSE;
        }

        if(!stringValidationUtil.isValidNotRequired(this.sale, MINCHAR, MAXCHAR))
        {
        valid = Boolean.FALSE;
        }

        if(!stringValidationUtil.isValidNotRequired(this.notification, MINCHAR, MAXCHAR))
        {
        valid = Boolean.FALSE;
        }

        if(!stringValidationUtil.isValidNotRequired(this.order, MINCHAR, MAXCHAR))
        {
        valid = Boolean.FALSE;
        }

        if(!stringValidationUtil.isValidNotRequired(this.orderCancel, MINCHAR, MAXCHAR))
        {
        valid = Boolean.FALSE;
        }
         */

        if (!stringValidationUtil.isValidNotRequired(this.subStores, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.tagLocation, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidRequired(this.packageLocation, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.ftp, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.ftpPath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.ftpUserName, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.ftpPassword, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.testFtp, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.testFtpPath.toString(), MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.testFtpUserName, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (!stringValidationUtil.isValidNotRequired(this.testFtpPassword, MINCHAR, MAXCHAR))
        {
            valid = Boolean.FALSE;
        }

        if (valid.booleanValue() == true)
        {
            //if(InventoryEntityFactory.getInstance().getItem(id)==null)
            return Boolean.TRUE;
            /*else
            {
            return Boolean.FALSE;
            }*/
        } else
        {
            return Boolean.FALSE;
        }
    }

    public String nameValidationInfo()
    {
        StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if (!stringValidationUtil.isValidRequired(this.name, MINCHAR, MAXCHAR))
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Name is invalid. Must be < ");
            stringBuffer.append(MAXCHAR);
            stringBuffer.append(" and > 0 characters.<br>");

            return stringBuffer.toString();
        }
        return EMPTY_STRING;
    }

    public String validationInfo()
    {
        try
        {
            StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
            
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(this.nameValidationInfo());

            if (!stringValidationUtil.isValidRequired(this.basketName, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Basket name is invalid. Must be < ");
                stringBuffer.append(MAXCHAR);
                stringBuffer.append(" and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidRequired(this.homeHostName, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Home host name is invalid. Must be < ");
                stringBuffer.append(MAXCHAR);
                stringBuffer.append(" and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.homeHostNamePath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Home host name path is invalid. Must be < ");
                stringBuffer.append(MAXCHAR);
                stringBuffer.append(" and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidRequired(this.hostName, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Host name is invalid. Must be < ");
                stringBuffer.append(MAXCHAR);
                stringBuffer.append(" and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidRequired(this.hostNamePath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Host name path is invalid. Must be < ");
                stringBuffer.append(MAXCHAR);
                stringBuffer.append(" and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.testHomeHostName, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Test home host name is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.testHomeHostNamePath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Test home host name path is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.testHostName, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Test host name is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.testHostNamePath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Test host name path is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidRequired(this.imagePath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Image path is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidRequired(this.staticPath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Static path is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidRequired(this.categoryPath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Category path is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidRequired(this.inventoryControl, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Inventory control is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            /*
            if(!stringValidationUtil.isValidNotRequired(this.out, MINCHAR, MAXCHAR))
            {
            stringBuffer.append("Out is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if(!stringValidationUtil.isValidNotRequired(this.age, MINCHAR, MAXCHAR))
            {
            stringBuffer.append("Age is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if(!stringValidationUtil.isValidNotRequired(this.sale, MINCHAR, MAXCHAR))
            {
            stringBuffer.append("Sale is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if(!stringValidationUtil.isValidNotRequired(this.notification, MINCHAR, MAXCHAR))
            {
            stringBuffer.append("Notification is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if(!stringValidationUtil.isValidNotRequired(this.order, MINCHAR, MAXCHAR))
            {
            stringBuffer.append("Order is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if(!stringValidationUtil.isValidNotRequired(this.orderCancel, MINCHAR, MAXCHAR))
            {
            stringBuffer.append("Order cancel is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }
             */

            if (!stringValidationUtil.isValidNotRequired(this.subStores, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Sub stores is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.tagLocation, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Tag location is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidRequired(this.packageLocation, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Package location is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.ftp, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Ftp is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.ftpPath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Ftp path is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.ftpUserName, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Ftp username is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.ftpPassword, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Ftp password is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.testFtp, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Test ftp is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.testFtpPath.toString(), MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Test ftp Path is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.testFtpUserName, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Test ftp username is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            if (!stringValidationUtil.isValidNotRequired(this.testFtpPassword, MINCHAR, MAXCHAR))
            {
                stringBuffer.append("Test ftp password is invalid. Must be < " + MAXCHAR + " and > 0 characters.<br>");
            }

            return stringBuffer.toString();
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed to generate validation error info", this, "validationInfo()", e));
            }
            return "Error Validating Form";
        }
    }

    //This path is used by the product listings class to save newly generated static pages
    //these files should be copied to the real server + staticpages path probably with ftp
    public String getTestHtmlPath()
    {
        //in testing mode return the test location else return the working location
        return org.allbinary.globals.URLGLOBALS.getTestHtmlPath() + this.getCurrentHostNamePath();
    }

    public String getCurrentHostName()
    {
        String location = EMPTY_STRING;
        //in testing mode return the test location else return the working location
        if (org.allbinary.globals.URLGLOBALS.isTestingMode())
        {
            location = this.getTestHostName();
        } else
        {
            location = this.getHostName();
        }
        return location;
    }

    //host name is used by Blisket urls
    public String getCurrentHostNamePath()
    {
        String location = EMPTY_STRING;

        //in testing mode return the test location else return the working location
        if (org.allbinary.globals.URLGLOBALS.isTestingMode())
        {
            location = this.getTestHostNamePath();
        } else
        {
            location = this.getHostNamePath();
        }
        return location;
    }

    //Home host name is used by remote non blisket urls
    public String getCurrentHomeHostName()
    {
        String location = EMPTY_STRING;
        
        //in testing mode return the test location else return the working location
        if (org.allbinary.globals.URLGLOBALS.isTestingMode())
        {
            location = this.getTestHomeHostName();
        } else
        {
            location = this.getHomeHostName();
        }
        return location;
    }

    public String getCurrentHomeHostNamePath()
    {
        String location = EMPTY_STRING;
        
        //in testing mode return the test location else return the working location
        if (org.allbinary.globals.URLGLOBALS.isTestingMode())
        {
            location = this.getTestHomeHostNamePath();
        } else
        {
            location = this.getHomeHostNamePath();
        }
        return location;
    }

    public String getName()
    {
        return this.name;
    }

    public String getBasketName()
    {
        return this.basketName;
    }

    public String getHomeHostName()
    {
        return this.homeHostName;
    }

    public String getHomeHostNamePath()
    {
        return this.homeHostNamePath.toString();
    }

    public String getHostName()
    {
        return this.hostName;
    }

    public String getHostNamePath()
    {
        return this.hostNamePath.toString();
    }

    public String getTestHomeHostName()
    {
        return this.testHomeHostName;
    }

    public String getTestHomeHostNamePath()
    {
        return this.testHomeHostNamePath.toString();
    }

    public String getTestHostName()
    {
        return this.testHostName;
    }

    public String getTestHostNamePath()
    {
        return this.testHostNamePath.toString();
    }

    /*
    public String getImagePath()
    {
    return this.imagePath;
    }
     */
    public String getStaticPath()
    {
        return this.staticPath.toString();
    }

    public String getCategoryPath()
    {
        return this.categoryPath.toString();
    }

    public String getInventoryControl()
    {
        return this.inventoryControl;
    }

    public BasicArrayList getSubStores() throws Exception
    {
        try
        {
            Tokenizer tokenizer = new Tokenizer(CommonSeps.getInstance().SEMICOLON);
            BasicArrayList subStoreVector = tokenizer.getTokens(this.subStores, new BasicArrayList());
            return subStoreVector;
        } catch (Exception e)
        {
            throw e;
        }
    }

    public String getTagLocation()
    {
        return this.tagLocation;
    }

    public String getPackageLocation()
    {
        return this.packageLocation;
    }

    public String getFtp()
    {
        return this.ftp;
    }

    public String getFtpUserName()
    {
        return this.ftpUserName;
    }

    public String getFtpPassword()
    {
        return this.ftpPassword;
    }

    public String getTestFtp()
    {
        return this.testFtp;
    }

    public String getTestFtpUserName()
    {
        return this.testFtpUserName;
    }

    public String getTestFtpPassword()
    {
        return this.testFtpPassword;
    }

    public String getFtpPath()
    {
        return this.ftpPath.toString();
    }

    public String getTestFtpPath()
    {
        return this.testFtpPath.toString();
    }

    public String getTimeCreated()
    {
        return this.timeCreated;
    }

    public String getLastModified()
    {
        return this.lastModified;
    }

    public void setName(String value)
    {
        this.name = value;
    }

    public void setBasketName(String value)
    {
        this.basketName = value;
    }

    public void setHomeHostName(String value)
    {
        this.homeHostName = value;
    }

    public void setHomeHostNamePath(String value) throws Exception
    {
        this.homeHostNamePath = new AbPath(value);
    }

    public void setHostName(String value)
    {
        this.hostName = value;
    }

    public void setHostNamePath(String value) throws Exception
    {
        this.hostNamePath = new AbPath(value);
    }

    public void setTestHomeHostName(String value)
    {
        this.testHomeHostName = value;
    }

    public void setTestHomeHostNamePath(String value) throws Exception
    {
        this.testHomeHostNamePath = new AbPath(value);
    }

    public void setTestHostName(String value)
    {
        this.testHostName = value;
    }

    public void setTestHostNamePath(String value) throws Exception
    {
        this.testHostNamePath = new AbPath(value);
    }

    public void setImagePath(String value) throws Exception
    {
        this.imagePath = new AbPath(value);
    }

    public void setStaticPath(String value) throws Exception
    {
        this.staticPath = new AbPath(value);
    }

    public void setCategoryPath(String value) throws Exception
    {
        this.categoryPath = new AbPath(value);
    }

    public void setSubStores(String value)
    {
        this.subStores = value;
    }

    public void setTagLocation(String value)
    {
        this.tagLocation = value;
    }

    public void setPackageLocation(String value)
    {
        this.packageLocation = value;
    }

    public void setInventoryControl(String value)
    {
        this.inventoryControl = value;
    }

    public void getPackageLocation(String value)
    {
        this.packageLocation = value;
    }

    public void setFtp(String value)
    {
        this.ftp = value;
    }

    public void setFtpUserName(String value)
    {
        this.ftpUserName = value;
    }

    public void setFtpPassword(String value)
    {
        this.ftpPassword = value;
    }

    public void setTestFtp(String value)
    {
        this.testFtp = value;
    }

    public void setTestFtpUserName(String value)
    {
        this.testFtpUserName = value;
    }

    public void setTestFtpPassword(String value)
    {
        this.testFtpPassword = value;
    }

    public void setTimeCreated(String value)
    {
        this.timeCreated = value;
    }

    public void setLastModified(String value)
    {
        this.lastModified = value;
    }

    public void setFtpPath(final String value) throws Exception
    {
        this.ftpPath = new AbPath(value);
    }

    public void setTestFtpPath(final String value) throws Exception
    {
        this.testFtpPath = new AbPath(value);
    }

    private boolean createDirectories() throws Exception
    {
        //use storename as man directory
        AbPath storeAbPath = new AbPath(
            URLGLOBALS.getWebappPath() + this.getCurrentHomeHostNamePath());

        if (!this.directory.create(storeAbPath))
        {
            return false;
        }

        if (!this.directory.create(new AbPath(storeAbPath + this.getCategoryPath())))
        {
            return false;
        }

        /*
        if(!Directory.create(storeAbPath + this.getImagePath()))
        return false;
         */

        if (!this.directory.create(new AbPath(storeAbPath + this.getStaticPath())))
        {
            return false;
        }

        return true;
    }

    //Create directories and copy files for store installation
    public void install(final int current, final int total) throws Exception
    {
        try
        {
            //create new store directories if they do not already exist
            if (!this.createDirectories())
            {
                throw new Exception("Unable to create store directories");
            }

            final StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append(URLGLOBALS.getMainPath());
            stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);

            String viewPath = stringBuffer.toString();

            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append(viewPath);
            stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().INSTALLPATH);

            AbPath fromDirectoryAbPath = new AbPath(stringBuffer.toString());

            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append(viewPath);
            stringBuffer.append("special");
            stringBuffer.append(AbPathData.getInstance().SEPARATOR);

            AbPath fromSpecialDirectoryAbPath = new AbPath(stringBuffer.toString());

            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append(viewPath);
            stringBuffer.append(this.getName());
            stringBuffer.append(AbPathData.getInstance().SEPARATOR);

            AbPath toDirectoryAbPath = new AbPath(stringBuffer.toString());

            if (current == 0)
            {
                if (!this.directory.create(toDirectoryAbPath))
                {
                    throw new Exception("Unable to create store view directory: " + toDirectoryAbPath.toString());
                }
            }

            final int viewTotal = (total * 85) / 100;
            final int installTotal = (total * 93) / 100;
            if (current < viewTotal)
            {
                final AbFile file = new AbFile(fromSpecialDirectoryAbPath);

                if (file.isDirectory())
                {
                    int halfViewTotal = viewTotal / 2;
                    if (current < halfViewTotal)
                    {
                        this.installViews(fromSpecialDirectoryAbPath, toDirectoryAbPath, current, halfViewTotal);
                    } else if (current < viewTotal)
                    {
                        this.installViews(fromDirectoryAbPath, toDirectoryAbPath, current - halfViewTotal, viewTotal - halfViewTotal);
                    }
                } else
                {
                    this.installViews(fromDirectoryAbPath, toDirectoryAbPath, current, viewTotal);
                }
            } else if (current < installTotal)
            {
                this.installResources(fromDirectoryAbPath, current - viewTotal, installTotal - viewTotal);
            } else
            {
                this.installMedia(fromDirectoryAbPath, current - installTotal, total - installTotal);
            }

        } catch (Exception e)
        {
            String error = "Failed to install storefront";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "install()", e));
            }
            throw e;
        }
    }

    private void installViews(final AbPath fromDirectoryAbPath, final AbPath toDirectoryAbPath, final int current, final int total)
        throws Exception
    {
        //copy install template/view files for new store
        FileUtil.getInstance().copyDirectoryPortion(fromDirectoryAbPath, toDirectoryAbPath, true, false, current, total);
    }

    private void installResources(AbPath fromDirectoryAbPath, int current, int total)
        throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();
        /*
        AbFile rootCategoryAbFile =
        new AbFile(new AbPath(fromDirectoryAbPath.toString(),
        CategoryData.ROOTCATEGORY + AbPathData.EXTENSION_SEP +
        CategoryData.UNCRYPTED_EXTENSION);
         */

        //Copy in default Resources
        stringBuffer.append(fromDirectoryAbPath.toString());
        stringBuffer.append(AbPathData.getInstance().SEPARATOR);
        stringBuffer.append("Resources");
        stringBuffer.append(AbPathData.getInstance().SEPARATOR);

        AbPath installCategoryAbPath = new AbPath(stringBuffer.toString());

        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(URLGLOBALS.getMainPath());
        stringBuffer.append(this.getCurrentHomeHostNamePath());
        stringBuffer.append(AbPathData.getInstance().SEPARATORCHAR);
        stringBuffer.append(this.getCategoryPath());

        AbPath categoryAbPath = new AbPath(stringBuffer.toString());

        FileUtil.getInstance().copyDirectoryPortion(installCategoryAbPath, categoryAbPath, true, false, current, total);
        //FileUtil.copy(installCategoryAbPath, categoryAbPath);
    }

    private void installMedia(AbPath fromDirectoryAbPath, int current, int total)
        throws Exception
    {
        //TWB -Could add
        //FileUtil.encCopy(rootCategoryAbFile, categoryAbPath,
        // CategoryData.ENCRYPTED_EXTENSION);

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(fromDirectoryAbPath.toString());
        stringBuffer.append(AbPathData.getInstance().SEPARATOR);
        stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().TEMPLATEPATH);
        stringBuffer.append("images");
        stringBuffer.append(AbPathData.getInstance().SEPARATOR);

        AbPath viewStoreImagesDirectoryAbPath = new AbPath(stringBuffer.toString());

        AbPath storeAbPath = new AbPath(URLGLOBALS.getWebappPath() + this.getCurrentHomeHostNamePath());

        FileUtil.getInstance().copyDirectoryPortion(viewStoreImagesDirectoryAbPath, storeAbPath, true, false, current, total);
        //FileUtil.copy(viewStoreImagesDirectoryAbPath, storeAbPath);
    }

    public Vector toVector() throws Exception
    {
        Vector dataVector = new Vector();
        dataVector.add(this.name);
        dataVector.add(this.homeHostName);
        dataVector.add(this.homeHostNamePath.toString());
        dataVector.add(this.hostName);
        dataVector.add(this.hostNamePath.toString());
        dataVector.add(this.testHomeHostName);
        dataVector.add(this.testHomeHostNamePath.toString());
        dataVector.add(this.testHostName);
        dataVector.add(this.testHostNamePath.toString());
        dataVector.add(this.imagePath.toString());
        dataVector.add(this.staticPath.toString());
        dataVector.add(this.categoryPath.toString());
        dataVector.add(this.inventoryControl);

        ContextConfigurationDomDocumentMapping contextConfigurationDomDocumentMapping =
            new ContextConfigurationDomDocumentMapping(this.getContextConfigurationInterface());
        dataVector.add(contextConfigurationDomDocumentMapping.toDomDocumentString());

        dataVector.add(this.subStores);
        dataVector.add(this.tagLocation);
        dataVector.add(this.packageLocation);
        dataVector.add(this.ftp);
        dataVector.add(this.ftpPath.toString());
        dataVector.add(this.ftpUserName);
        dataVector.add(this.ftpPassword);
        dataVector.add(this.testFtp);
        dataVector.add(this.testFtpPath.toString());
        dataVector.add(this.testFtpUserName);
        dataVector.add(this.testFtpPassword);

        Calendar calendar = Calendar.getInstance();
        String time = new String(new Long(calendar.getTimeInMillis()).toString());
        dataVector.add(time);
        dataVector.add(time);

        return dataVector;
    }

    public HashMap toHashMap() throws Exception
    {
    	StoreFrontData storeFrontData = StoreFrontData.getInstance();
    	
        HashMap dataHashMap = new HashMap();

        dataHashMap.put(storeFrontData.NAME, this.name);
        dataHashMap.put(storeFrontData.HOMEHOSTNAME, this.homeHostName);
        dataHashMap.put(storeFrontData.HOMEHOSTNAMEPATH, this.homeHostNamePath.toString());
        dataHashMap.put(storeFrontData.HOSTNAME, this.hostName);
        dataHashMap.put(storeFrontData.HOSTNAMEPATH, this.hostNamePath.toString());
        dataHashMap.put(storeFrontData.TESTHOMEHOSTNAME, this.testHomeHostName);
        dataHashMap.put(storeFrontData.TESTHOMEHOSTNAMEPATH, this.testHomeHostNamePath.toString());
        dataHashMap.put(storeFrontData.TESTHOSTNAME, this.testHostName);
        dataHashMap.put(storeFrontData.TESTHOSTNAMEPATH, this.testHostNamePath.toString());
        dataHashMap.put(storeFrontData.IMAGEPATH, this.imagePath.toString());
        dataHashMap.put(storeFrontData.STATICPATH, this.staticPath.toString());
        dataHashMap.put(storeFrontData.CATEGORYPATH, this.categoryPath.toString());
        dataHashMap.put(storeFrontData.INVENTORYCONTROL, this.inventoryControl);

        ContextConfigurationDomDocumentMapping contextConfigurationDomDocumentMapping =
            new ContextConfigurationDomDocumentMapping(this.getContextConfigurationInterface());
        dataHashMap.put(storeFrontData.CONFIGURATION,
            contextConfigurationDomDocumentMapping.toDomDocumentString());

        dataHashMap.put(storeFrontData.SUBSTORES, this.subStores);
        dataHashMap.put(storeFrontData.TAGLOCATION, this.tagLocation);
        dataHashMap.put(storeFrontData.PACKAGELOCATION, this.packageLocation);
        dataHashMap.put(storeFrontData.FTP, this.ftp);
        dataHashMap.put(storeFrontData.FTPPATH, this.ftpPath.toString());
        dataHashMap.put(storeFrontData.FTPUSERNAME, this.ftpUserName);
        dataHashMap.put(storeFrontData.FTPPASSWORD, this.ftpPassword);
        dataHashMap.put(storeFrontData.TESTFTP, this.testFtp);
        dataHashMap.put(storeFrontData.TESTFTPPATH, this.testFtpPath.toString());
        dataHashMap.put(storeFrontData.TESTFTPUSERNAME, this.testFtpUserName);
        dataHashMap.put(storeFrontData.TESTFTPPASSWORD, this.testFtpPassword);

        Calendar calendar = Calendar.getInstance();
        String time = new String(new Long(calendar.getTimeInMillis()).toString());
        dataHashMap.put(EntryData.getInstance().LASTMODIFIED, time);

        return dataHashMap;
    }

    public Object getKey()
    {
        return (Object) this.getName();
    }

    public ContextConfigurationInterface getContextConfigurationInterface()
    {
        return contextConfigurationInterface;
    }

    public void setContextConfigurationInterface(ContextConfigurationInterface contextConfigurationInterface)
    {
        this.contextConfigurationInterface = contextConfigurationInterface;
    }
}
