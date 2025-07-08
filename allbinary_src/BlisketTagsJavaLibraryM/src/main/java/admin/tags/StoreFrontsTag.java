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

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.communication.log.LogUtil;

import admin.taghelpers.StoreFrontsHelperFactory;
import admin.taghelpers.StoreFrontsRequestHelperFactory;

import org.allbinary.business.context.modules.storefront.StoreFrontData;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class StoreFrontsTag extends TableTag
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public StoreFrontsTag()
    {
        this.setTagHelperFactory(new StoreFrontsHelperFactory());
        this.setTagRequestHelperFactory(new StoreFrontsRequestHelperFactory());
    }

    private String generateSelect() throws LicensingException
    {
        try
        {
            Object object =
                this.getTagHelperFactoryInterface().getInstance(this.getPropertiesHashMap(), pageContext);

            Method method = object.getClass().getMethod("generateSelect", null);
            String result = (String) method.invoke(object, null);
            return result;
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            String error = "Failed to generate storefronts select";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "generateSelect()", e);
            }
            return error;
        }
    }

    private String install() throws LicensingException
    {
        try
        {
            Object object =
                this.getTagRequestHelperFactoryInterface().getInstance(this.getPropertiesHashMap(), pageContext);

            Method method = object.getClass().getMethod("install", null);
            String result = (String) method.invoke(object, null);
            return result;
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            String error = "Failed to generate storefronts select";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "generateSelect()", e);
            }
            return error;
        }
    }

    private String sendStoreCreatedEmails() throws LicensingException
    {
        try
        {
            Object object =
                this.getTagRequestHelperFactoryInterface().getInstance(this.getPropertiesHashMap(), pageContext);

            Method method = object.getClass().getMethod("sendStoreCreatedEmails", null);
            String result = (String) method.invoke(object, null);
            return result;
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            String error = "Failed to send email";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "sendStoreCreatedEmails()", e);
            }
            return error;
        }
    }

    public int doStartTag() throws JspTagException
    {
        try
        {
            if (this.isEnabled())
            {
                if (this.getCommand() != null)
                {
                	StoreFrontData storeFrontData = StoreFrontData.getInstance();
                	
                    if (this.getCommand().compareTo(storeFrontData.SELECT) == 0)
                    {
                        this.pageContext.getOut().print(this.generateSelect());
                    } else if (this.getCommand().compareTo(storeFrontData.INSTALL) == 0)
                    {
                        this.getPropertiesHashMap().put("current", this.getCurrent());
                        this.getPropertiesHashMap().put("total", this.getTotal());

                        //If install is complete process body
                        if (this.getCurrent().intValue() == this.getTotal().intValue())
                        {
                            return TagSupport.EVAL_BODY_INCLUDE;
                        }

                        this.install();
                        //this.pageContext.getOut().print();
                    } else if (this.getCommand().compareTo(storeFrontData.INSTALL_COMPLETE) == 0)
                    {
                        this.sendStoreCreatedEmails();
                        //this.pageContext.getOut().print();
                    } else
                    {
                        return super.doStartTag();
                    }
                }
            }
            return SKIP_BODY;
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
