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

import tags.PropertiesTag;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.http.request.AbResponseHandler;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.bool.BooleanUtil;
import abcs.logic.system.security.licensing.LicensingException;
import admin.taghelpers.TagHelperFactoryInterface;

public class TableTag extends PropertiesTag
//implements TableInterface
{
    private String enabled;
    private TagHelperFactoryInterface tagHelperFactoryInterface;
    private TagHelperFactoryInterface tagRequestHelperFactoryInterface;
    private Object requestObject;

    private Integer current;
    private Integer total;

    public TableTag()
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
        {
            LogUtil.put(LogFactory.getInstance("Tag Constructed", this, "TableTag()"));
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "drop()", e));
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "create()", e));
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "restore()", e));
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "backup()", e));
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "insert()", e));
            }
            return error;
        }
    }

    public String delete() throws LicensingException
    {
        try
        {
            Class helperClass = requestObject.getClass();
            Method method = helperClass.getMethod("delete", null);

            String result = (String) method.invoke(requestObject, null);
            return result;
        } catch (Exception e)
        {
            String error = "Failed to delete";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "delete()", e));
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

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
    {
    LogUtil.put(LogFactory.getInstance(error,this,"view()",e);
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

    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
    {
    LogUtil.put(LogFactory.getInstance(error,this,"editPricing()",e);
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "update()", e));
            }
            return error;
        }
    }

    public int doStartTag() throws JspTagException
    {
        try
        {        	
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
            {
                HttpServletRequest request =
                    (HttpServletRequest) this.pageContext.getRequest();

                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("TableTag Helper: ");
                stringBuffer.append(this.getTagHelperFactoryInterface().getClass().getName());
                stringBuffer.append(" Request URI: ");
                stringBuffer.append(request.getRequestURI());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "doStartTag()"));
            }

            if (this.getTagRequestHelperFactoryInterface() != null)
            {
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
                {
                    LogUtil.put(LogFactory.getInstance("TableTag RequestHelper: " + this.getTagRequestHelperFactoryInterface().getClass().getName(), this, "doStartTag()"));
                }
            } else
            {
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
                {
                LogUtil.put(LogFactory.getInstance("TableTag RequestHelper: null", this, "doStartTag()"));
                }
            }

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
            {
                LogUtil.put(LogFactory.getInstance("TableTag this.getCommand(): " + this.getCommand(), this, "doStartTag()"));
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

                    if (this.getCommand().compareTo(allbinary.globals.GLOBALS.INSERT) == 0
                        || this.getCommand().compareTo(allbinary.globals.GLOBALS.DELETE) == 0
                        || this.getCommand().compareTo(allbinary.globals.GLOBALS.UPDATE) == 0)
                    {
                        this.requestObject = getTagRequestHelperFactoryInterface().getInstance(
                            this.getPropertiesHashMap(), pageContext);
                    }

                    if (this.getCommand().compareTo(allbinary.globals.GLOBALS.BACKUP) == 0)
                    {
                        String output = this.backup();
                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                        return SKIP_BODY;
                    } else if (this.getCommand().compareTo(allbinary.globals.GLOBALS.RESTORE) == 0)
                    {
                        this.getPropertiesHashMap().put("current", this.getCurrent());
                        this.getPropertiesHashMap().put("total", this.getTotal());

                        String output = this.restore();
                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                        return SKIP_BODY;
                    } else if (this.getCommand().compareTo(allbinary.globals.GLOBALS.DROP) == 0)
                    {
                        String output = this.drop();
                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                        return SKIP_BODY;
                    } else if (this.getCommand().compareTo(allbinary.globals.GLOBALS.CREATE) == 0)
                    {
                        String output = this.create();
                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                        return SKIP_BODY;
                    } else if (this.getCommand().compareTo(allbinary.globals.GLOBALS.INSERT) == 0)
                    {
                        String output = this.insert();
                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                    } else if (this.getCommand().compareTo(allbinary.globals.GLOBALS.DELETE) == 0)
                    {
                        String output = this.delete();
                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                    } else if (this.getCommand().compareTo(allbinary.globals.GLOBALS.UPDATE) == 0)
                    {
                        String output = this.update();
                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                        {
                            this.pageContext.getOut().print(output + "<br />");
                        }
                    }
                    /*
                    else
                    if (this.getCommand().compareTo(allbinary.globals.GLOBALS.VIEW)==0)
                    {
                    String output = this.view();
                    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
                    {
                    this.pageContext.getOut().print(output);
                    }
                    }
                    else
                    if (this.getCommand().compareTo(allbinary.globals.GLOBALS.EDIT)==0)
                    {
                    String output = this.edit();
                    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
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
