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
package admin.tags;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;

import admin.taghelpers.TagHelperFactoryInterface;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.SqlStrings;
import org.allbinary.logic.java.bool.BooleanUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.PropertiesTag;

public class TableTag extends PropertiesTag
//extends Table
{
    protected final LogUtil logUtil = LogUtil.getInstance();
        
    private String enabled;
    private TagHelperFactoryInterface tagHelperFactoryInterface;
    private TagHelperFactoryInterface tagRequestHelperFactoryInterface;
    private Object requestObject;

    private Integer current;
    private Integer total;

    public TableTag()
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
        {
            logUtil.put(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
        }
    }

    public void setIsSelected(String enabled)
    {
        this.enabled = enabled;
    }

    /*
    public void setEnabled(String enabled)
    {
    this.enabled=enabled;
    }
     */
    public boolean isEnabled() throws Exception
    {
        if (!StringValidationUtil.getInstance().isEmpty(this.enabled))
        {
            if (BooleanUtil.getInstance().getFromString(this.enabled)
                || this.enabled.compareTo("on") == 0)
            {
                return true;
            }
        }
        return false;
    }

    protected void setTagHelperFactory(TagHelperFactoryInterface tagHelperFactoryInterface)
    {
        this.tagHelperFactoryInterface = tagHelperFactoryInterface;
    }

    protected TagHelperFactoryInterface getTagHelperFactoryInterface()
    {
        return tagHelperFactoryInterface;
    }

    protected void setTagRequestHelperFactory(TagHelperFactoryInterface tagHelperFactoryInterface)
    {
        this.tagRequestHelperFactoryInterface = tagHelperFactoryInterface;
    }

    protected TagHelperFactoryInterface getTagRequestHelperFactoryInterface()
    {
        return tagRequestHelperFactoryInterface;
    }

    public void setCurrent(Integer current)
    {
        this.current = current;
    }

    public Integer getCurrent()
    {
        return current;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }

    public Integer getTotal()
    {
        return total;
    }

    public String drop() throws LicensingException
    {
        try
        {
            final Object object = getTagHelperFactoryInterface().getInstance(
                this.getPropertiesHashMap(), pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("drop", null);

            String result = (String) method.invoke(object, null);
            return result;
        } catch (Exception e)
        {
            String error = "Failed to drop table";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.DROP, e);
            }

            return error;
        }
    }

    public String create() throws LicensingException
    {
        try
        {
            final Object object = getTagHelperFactoryInterface().getInstance(
                this.getPropertiesHashMap(), pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("create", null);

            String result = (String) method.invoke(object, null);
            return result;
        } catch (Exception e)
        {
            String error = "Failed to create table";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "create()", e);
            }

            return error;
        }
    }

    public String restore() throws LicensingException
    {
        try
        {
            final Object object = getTagHelperFactoryInterface().getInstance(
                this.getPropertiesHashMap(), pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("restore", null);

            String result = (String) method.invoke(object, null);
            return result;
        } catch (Exception e)
        {
            String error = "Failed to restore backup";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "restore()", e);
            }

            return error;
        }
    }

    public String backup() throws LicensingException
    {
        try
        {
            final Object object = getTagHelperFactoryInterface().getInstance(
                this.getPropertiesHashMap(), pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("backup", null);

            String result = (String) method.invoke(object, null);
            return result;
        } catch (Exception e)
        {
            String error = "Failed to make backup";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "backup()", e);
            }

            return error;
        }
    }

    public String insert() throws LicensingException
    {
        try
        {
            Class helperClass = requestObject.getClass();
            Method method = helperClass.getMethod("insert", null);

            String result = (String) method.invoke(requestObject, null);
            return result;
        } catch (Exception e)
        {
            String error = "Failed to insert";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "insert()", e);
            }
            return error;
        }
    }

    public String delete() throws LicensingException
    {
        try
        {
            Class helperClass = requestObject.getClass();
            Method method = helperClass.getMethod(commonStrings.delete, null);

            String result = (String) method.invoke(requestObject, null);
            return result;
        } catch (Exception e)
        {
            String error = "Failed to delete";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "delete()", e);
            }
            return error;
        }
    }

    /*
    public String view() throws LicensingException
    {
    try
    {
    Class helperClass = object.getClass();
    Method method = helperClass.getMethod("view",null);

    String result = (String) method.invoke(object,null);
    return result;
    }
    catch(LicensingException e)
    {
    throw e;
    }
    catch(Exception e)
    {
    String error = "Failed to view";

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
    {
    logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
    }
    return error;
    }
    }

    public String edit() throws LicensingException
    {
    try
    {
    Class helperClass = requestObject.getClass();
    Method method = helperClass.getMethod("edit",null);

    String result = (String) method.invoke(object,null);
    return result;
    }
    catch(LicensingException e)
    {
    throw e;
    }
    catch(Exception e)
    {
    String error = "Failed to edit";

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
    {
    logUtil.put(commonStrings.EXCEPTION,this,"editPricing()",e);
    }
    return error;
    }
    }
     */
    public String update() throws LicensingException
    {
        try
        {
            Class helperClass = requestObject.getClass();
            Method method = helperClass.getMethod("update", null);

            String result = (String) method.invoke(requestObject, null);
            return result;
        } catch (Exception e)
        {
            String error = "Failed to update";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "update()", e);
            }
            return error;
        }
    }

    public int doStartTag() throws JspTagException
    {
        try
        {        	
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
                HttpServletRequest request =
                    (HttpServletRequest) this.pageContext.getRequest();

                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append("TableTag Helper: ");
                stringBuffer.append(this.getTagHelperFactoryInterface().getClass().getName());
                stringBuffer.append(" Request URI: ");
                stringBuffer.append(request.getRequestURI());

                logUtil.put(stringBuffer.toString(), this, "doStartTag()");
            }

            if (this.getTagRequestHelperFactoryInterface() != null)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                {
                    logUtil.put("TableTag RequestHelper: " + this.getTagRequestHelperFactoryInterface().getClass().getName(), this, "doStartTag()");
                }
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                {
                logUtil.put("TableTag RequestHelper: null", this, "doStartTag()");
                }
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
                logUtil.put("TableTag this.getCommand(): " + this.getCommand(), this, "doStartTag()");
            }

            if (this.isEnabled())
            {
                if (this.getCommand() != null)
                {
                    //Initialize helpers for each request
                    //Note: doStartTag could be called multiple times and cause an error for multipart postings

                    //For Example:
                    //object Holds InventoryRequestHelper because the multipart processing makes
                    //the request object work only once for getInputStream()

                    if (this.getCommand().compareTo(commonStrings.INSERT) == 0 ||
                            this.getCommand().compareTo(SqlStrings.getInstance().INSERT_INTO)==0 || 
                            this.getCommand().compareTo(commonStrings.DELETE) == 0 || 
                            this.getCommand().compareTo(commonStrings.UPDATE) == 0 ||
                            this.getCommand().compareTo(SqlStrings.getInstance().UPDATE)==0)
                    {
                        this.requestObject = getTagRequestHelperFactoryInterface().getInstance(
                            this.getPropertiesHashMap(), pageContext);
                    }

                    if (this.getCommand().compareTo(commonStrings.INSERT) == 0 || 
                            this.getCommand().compareTo(SqlStrings.getInstance().INSERT_INTO)==0) {
                        this.insert();
                    } else if (this.getCommand().compareTo(commonStrings.DELETE) == 0) {
                        this.delete();
                    } else if (this.getCommand().compareTo(commonStrings.UPDATE) == 0 ||
                            this.getCommand().compareTo(SqlStrings.getInstance().UPDATE)==0)
                    {
                        this.update();
                    }
                    
                    if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.BACKUP) == 0)
                    {
                        String output = this.backup();
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                        return SKIP_BODY;
                    } else if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.RESTORE) == 0)
                    {
                        this.getPropertiesHashMap().put("current", this.getCurrent());
                        this.getPropertiesHashMap().put("total", this.getTotal());

                        String output = this.restore();
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                        return SKIP_BODY;
                    } else if (this.getCommand().compareTo(commonStrings.DROP) == 0)
                    {
                        String output = this.drop();
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                        return SKIP_BODY;
                    } else if (this.getCommand().compareTo(commonStrings.CREATE) == 0)
                    {
                        String output = this.create();
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                        return SKIP_BODY;
                    } else if (this.getCommand().compareTo(commonStrings.INSERT) == 0)
                    {
                        String output = this.insert();
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                    } else if (this.getCommand().compareTo(commonStrings.DELETE) == 0)
                    {
                        String output = this.delete();
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                    } else if (this.getCommand().compareTo(commonStrings.UPDATE) == 0)
                    {
                        String output = this.update();
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                    }
                    /*
                    else
                    if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.VIEW)==0)
                    {
                    String output = this.view();
                    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                    {
                    this.pageContext.getOut().print(output);
                    }
                    }
                    else
                    if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.EDIT)==0)
                    {
                    String output = this.edit();
                    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGEXTRAOUTPUT))
                    {
                    this.pageContext.getOut().print(output);
                    }
                    }
                     */
                }
            }
            return EVAL_BODY_INCLUDE;
            //         throw new Exception("Table doStartTag Should Never Be Used.");
        } catch (LicensingException e)
        {
            AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext, e);
            return SKIP_BODY;
        } catch (Exception e)
        {
            AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
            return SKIP_BODY;
        }
    }
}
