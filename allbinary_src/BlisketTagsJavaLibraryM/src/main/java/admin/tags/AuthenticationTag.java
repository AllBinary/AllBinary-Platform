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
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import admin.taghelpers.AuthenticationHelperFactory;
import admin.taghelpers.AuthenticationRequestHelperFactory;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.CustomTagSupport;

public class AuthenticationTag extends CustomTagSupport
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private String command;
    private String userName;
    private String password;
    private String newPassword;
    private Vector roles;
    private HashMap propertiesHashMap;

    public AuthenticationTag()
    {
        final StringUtil stringUtil = StringUtil.getInstance();
        this.userName = stringUtil.EMPTY_STRING;
        this.password = stringUtil.EMPTY_STRING;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String value)
    {
        this.password = value;
    }

    public void setNewPassword(String value)
    {
        this.newPassword = value;
    }

    public void setRoles(Vector values)
    {
        this.roles = values;
    }

    private boolean changePassword() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationRequestHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("changePassword", null);

            Boolean result = (Boolean) method.invoke(object, null);
            return result.booleanValue();
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "changePassword()", e);
            }
            return false;
        }
    }

    private boolean newPassword() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationRequestHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("newPassword", null);

            Boolean result = (Boolean) method.invoke(object, null);
            return result.booleanValue();
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "newPassword()", e);
            }
            return false;
        }
    }

    private int invalidateSession() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("invalidateSession", null);

            Integer resultInteger = (Integer) method.invoke(object, null);
            return resultInteger.intValue();
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "invalidateSession()", e);
            }
            return TagSupport.SKIP_BODY;
        }
    }

    private boolean isSessionOld() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class helperClass = object.getClass();
            Method method = helperClass.getMethod("isSessionOld", null);

            Boolean resultBoolean = (Boolean) method.invoke(object, null);
            return resultBoolean.booleanValue();
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "isSessionOld()", e);
            }
            return false;
        }
    }

    private boolean isRoleValid() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationRequestHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class addressHelperClass = object.getClass();

            Class[] methodParams =
            {
                this.userName.getClass(),
                this.password.getClass(),
                this.roles.getClass()
            };
            Method method = addressHelperClass.getMethod("isRoleValid", methodParams);
            Object[] methodArgs =
            {
                this.userName, this.password, this.roles
            };

            Boolean resultBoolean = (Boolean) method.invoke(object, methodArgs);
            return resultBoolean.booleanValue();
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "isRoleValid()", e);
            }
            return false;
        }
    }

    private String validRole() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("validRole", null);

            String result = (String) method.invoke(object, null);
            return result;
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            String error = "Failed to set valid role";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "validRole()", e);
            }
            return error;
        }
    }

    private String invalidRole() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("invalidRole", null);

            String result = (String) method.invoke(object, null);
            return result;
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            String error = "Failed to set role invalid";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "invalidRole()", e);
            }
            return error;
        }
    }

    private boolean isAuthenticationSessionValid() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class addressHelperClass = object.getClass();

            Class[] methodParams =
            {
                this.roles.getClass()
            };
            Method method = addressHelperClass.getMethod("isAuthenticationSessionValid", methodParams);
            Object[] methodArgs =
            {
                this.roles
            };

            Boolean resultBoolean = (Boolean) method.invoke(object, methodArgs);
            return resultBoolean.booleanValue();
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "isAuthenticationSessionValid()", e);
            }
            return false;
        }
    }

    private boolean processIfNewLogin() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class helperClass = object.getClass();

            Class[] methodParams =
            {
                this.userName.getClass(), this.password.getClass()
            };

            Method method = helperClass.getMethod("processIfNewLogin", methodParams);

            Object[] methodArgs =
            {
                this.userName, this.password
            };

            Boolean resultBoolean = (Boolean) method.invoke(object, methodArgs);
            return resultBoolean.booleanValue();
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "processIfNewLogin()", e);
            }
            return false;
        }
    }

    private boolean processInvalidation() throws LicensingException
    {
        try
        {
            Object object = new AuthenticationHelperFactory().getInstance(
                this.propertiesHashMap, this.pageContext);

            Class addressHelperClass = object.getClass();
            Method method = addressHelperClass.getMethod("processInvalidation", null);

            Boolean resultBoolean = (Boolean) method.invoke(object, null);
            return resultBoolean.booleanValue();
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "isSetToInvalidate()", e);
            }
            return false;
        }
    }

    public int doStartTag() throws JspTagException
    {
        try
        {
            final StringUtil stringUtil = StringUtil.getInstance();
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
                HttpServletRequest request =
                    (HttpServletRequest) this.pageContext.getRequest();

                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append(" Request URI: ");
                stringBuffer.append(request.getRequestURI());

                logUtil.put(stringBuffer.toString(), this, "doStartTag()");
            }

            if (command != null)
            {
                //Test Error Pages
                //if(command!=null) throw new JspTagException(e2);
                //if(command!=null) throw new LicensingException();

                this.propertiesHashMap = new HashMap();

                if (command.compareTo(org.allbinary.globals.GLOBALS2.NEWPASSWORD) == 0)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                    {
                        logUtil.put("New Password", this, "doStartTag()");
                    }

                    if (!this.newPassword())
                    {
                        pageContext.getOut().print("New Password Request Failed.<p/>");
                        return EVAL_BODY_INCLUDE;
                    } else
                    {
                        pageContext.getOut().print("New Password Was Sent.<p/>");
                        return SKIP_BODY;
                    }
                } else if (command.compareTo(org.allbinary.globals.GLOBALS2.CHANGEPASSWORD) == 0)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                    {
                        logUtil.put("Change Password", this, "doStartTag()");
                    }

                    if (!this.changePassword())
                    {
                        pageContext.getOut().print("Password Change Attempt Failed.<p/>");
                        return EVAL_BODY_INCLUDE;
                    } else
                    {
                        pageContext.getOut().print("Password Changed Successfully.<p/>");
                        return SKIP_BODY;
                    }
                } else if (command.compareTo(WeblisketSessionData.INVALIDATESESSION) == 0)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                    {
                        logUtil.put("INVALIDATESESSION", this, "doStartTag()");
                    }
                    return this.invalidateSession();
                } else
                {
                    /*
                    pageContext.getOut().print("UserName: " + userName + "<br>");
                    pageContext.getOut().print("Password: " + password + "<br>");
                    pageContext.getOut().print("UserName: " + sessionUserName + "<br>");
                    pageContext.getOut().print("Role: " + role + "<br>");
                    pageContext.getOut().print("Auth: " + authenticated + "<p>");
                    this.outputSessionInfo(session);
                     */

                    if (this.isSessionOld())
                    {
                        //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                        //{
                        // logUtil.put("Session is old",this,"doStartTag()");
                        //}

                        if (this.processInvalidation())
                        {
                            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                            {
                                logUtil.put("Processing Invalidation", this, "doStartTag()");
                            }

                            //invalidates session based on a timeout
                            pageContext.getOut().print("Please login again.<p>");
                            if (command.compareTo(org.allbinary.globals.GLOBALS2.PROCESSBODYIFAUTHENTICATED) == 0)
                            {
                                return TagSupport.SKIP_BODY;
                            } else
                            {
                                return TagSupport.EVAL_BODY_INCLUDE;
                            }
                        } else
                        {
                            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                            {
                                logUtil.put("Preparing session for invalidation", this, "doStartTag()");
                            }

                            //prepares session for timeout
                            if (command.compareTo(org.allbinary.globals.GLOBALS2.PROCESSBODYIFAUTHENTICATED) == 0)
                            {
                                return TagSupport.SKIP_BODY;
                            } else
                            {
                                return TagSupport.EVAL_BODY_INCLUDE;
                            }
                        }
                    }

                    /*
                    if(userName==null || userName.compareTo(stringUtil.EMPTY_STRING)==0)
                    pageContext.getOut().print("UserName Empty<br>");
                    if(password==null  || password.compareTo(stringUtil.EMPTY_STRING)==0)
                    pageContext.getOut().print("Password Empty<br>");
                    if(sessionUserName==null || sessionUserName.compareTo(stringUtil.EMPTY_STRING)==0)
                    pageContext.getOut().print("Session UserName Null or Empty<br>");
                    if(authenticated!=null)
                    {
                    pageContext.getOut().print("Auth Not Empty<br>");
                    if(authenticated.compareTo("true")==0)
                    pageContext.getOut().print("Auth==true<br>");
                    }
                     */

                    if ((userName == null || userName.compareTo(stringUtil.EMPTY_STRING) == 0)
                        && (password == null || password.compareTo(stringUtil.EMPTY_STRING) == 0)
                        && this.roles != null && this.isAuthenticationSessionValid())
                    {
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                        {
                            logUtil.put("Authenticated session is valid", this, "doStartTag()");
                        }

                        if (command.compareTo(org.allbinary.globals.GLOBALS2.PROCESSBODYIFAUTHENTICATED) == 0)
                        {
                            return TagSupport.EVAL_BODY_INCLUDE;
                        } else
                        {
                            return TagSupport.SKIP_BODY;
                        }
                    }

                    if (this.userName != null && this.password != null && this.processIfNewLogin())
                    {
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                        {
                            logUtil.put("Processing new login.", this, "doStartTag()");
                        }

                        if (this.isRoleValid())
                        {
                            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                            {
                                logUtil.put("Role is valid - Processing new login", this, "doStartTag()");
                            }

                            pageContext.getOut().print(validRole());
                            if (command.compareTo(org.allbinary.globals.GLOBALS2.PROCESSBODYIFAUTHENTICATED) == 0)
                            {
                                return EVAL_BODY_INCLUDE;
                            } else
                            {
                                return SKIP_BODY;
                            }
                        } else
                        {
                            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                            {
                                logUtil.put("Role is not valid - Processing new login", this, "doStartTag()");
                            }

                            pageContext.getOut().print(invalidRole());
                            if (command.compareTo(org.allbinary.globals.GLOBALS2.PROCESSBODYIFAUTHENTICATED) == 0)
                            {
                                return SKIP_BODY;
                            } else
                            {
                                return EVAL_BODY_INCLUDE;
                            }
                        }
                    } else
                    {
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                        {
                            logUtil.put("Invalid Login", this, "doStartTag()");
                        }

                        if (userName != null && userName.compareTo(stringUtil.EMPTY_STRING) != 0 && password != null && password.compareTo(stringUtil.EMPTY_STRING) != 0)
                        {
                            pageContext.getOut().print("Sorry your username and/or password is invalid.<p/>");
                        }
                    }

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                    {
                        logUtil.put("Major authentication error - userName: " + this.userName
                            + " Password: " + this.password + " Command: " + this.command,
                            this, "doStartTag()");
                    }

                    //pageContext.getOut().print("Not a new attempt or previously authorized<p>");
                    //I hope this never occurs - I may add an exception for this case in the future
                    if (command.compareTo(org.allbinary.globals.GLOBALS2.PROCESSBODYIFAUTHENTICATED) == 0)
                    {
                        return TagSupport.SKIP_BODY;
                    } else
                    {
                        return TagSupport.EVAL_BODY_INCLUDE;
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
