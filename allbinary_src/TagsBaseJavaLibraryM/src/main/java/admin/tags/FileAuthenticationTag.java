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

import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.allbinary.business.init.InstallerInfo;
import org.allbinary.business.user.role.BasicUserRole;
import org.allbinary.business.user.role.BasicUserRoleFactory;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.http.request.session.BasicWeblisketSession;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.java.bool.BooleanUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.CustomTagSupport;

public class FileAuthenticationTag extends CustomTagSupport
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private Integer attemptsInteger;
    //private final String TIMEOUT = "TIMEOUT";
    //60000 = 1 min
    //600000 = 10 min
    //900000 = 15 min
    //3600000 = 60 min
    //private final int INACTIVITYTIMEOUT = 36000000;
    //private final int CUSTOMERTIMEOUT = 36000000;
    //private final int CUSTOMERMAXSESSIONTIME = 360000000;
    //private final int MAXSESSIONTIME = 360000000;
    private BasicWeblisketSession weblisketSession;
    private HttpServletRequest request;
    //for managers only
    //private String storeName;
    private String command;
    private String userName;
    private String password;
    private String newPassword;
    private String authenticated;
    private String sessionUserName;
    private BasicUserRole role;
    private String timeout;
    private Vector roles;

    public FileAuthenticationTag()
    {
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

    private void makeSessionValid(BasicUserRole storedRole, String userName)
    {
        this.weblisketSession.setAuthenticated();
        this.weblisketSession.setRole(storedRole);
        this.weblisketSession.setUserName(userName);

        this.request.removeAttribute(WeblisketSessionData.REMOVABLEUSERNAME);
        this.request.removeAttribute(WeblisketSessionData.REMOVABLEPASSWORD);
    }

    private void getFormData()
    {
        this.authenticated = this.weblisketSession.getAuthentication();
        this.sessionUserName = this.weblisketSession.getUserName();
        this.role = this.weblisketSession.getRole();
        this.attemptsInteger = this.weblisketSession.getAttempts();
        this.timeout = this.weblisketSession.getTimeout();
        //this.storeName = this.request.getParameter(allbinary.business.context.modules.storefront.StoreFrontData.NAME);
    }

    public void nextAttempt()
    {
        if (attemptsInteger != null && attemptsInteger.intValue() > 0)
        {
            this.weblisketSession.setAttempts(new Integer(attemptsInteger.intValue() + 1));
        } else
        {
            this.weblisketSession.setAttempts(new Integer(1));
        }
    }

    private boolean changePassword() throws LicensingException
    {
        try
        {
            //BasicUserRole role = this.weblisketSession.getRole();
            String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
            String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
            String newPassword = request.getParameter(WeblisketSessionData.REMOVABLENEWPASSWORD);
            //String sessionPassword = this.weblisketSession.getPassword();

            String newReenteredPassword = request.getParameter(WeblisketSessionData.REMOVABLEREENTERNEWPASSWORD);

            if (newPassword.compareTo(newReenteredPassword) != 0)
            {
                return false;
            }

            InstallerInfo installerInfo = new InstallerInfo();
            if (installerInfo.isValid(userName, password))
            {
                installerInfo.setPassword(newPassword);
                installerInfo.write();

                this.weblisketSession.setUserName(userName);
                this.weblisketSession.setPassword(newPassword);

                return true;
            }

            return false;
        } catch (LicensingException e)
        {
            throw e;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "invalidateSession()", e);
            }
            return false;
        }
    }

    private int invalidateSession()
    {
        try
        {
            this.weblisketSession.clear();
            return new Integer(TagSupport.EVAL_BODY_INCLUDE).intValue();
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
            if (this.role != null)
            {
                long timeCreated = this.weblisketSession.getCreationTime();
                long sessionTimout = this.role.getSessionTimeout();

                Calendar calendar = Calendar.getInstance();

                long timeFirst = calendar.getTimeInMillis() - sessionTimout;

                if (timeFirst > timeCreated)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
                    {
                        StringMaker stringBuffer = new StringMaker();

                        stringBuffer.append("Session Is Old - Timeout: ");
                        stringBuffer.append(sessionTimout);
                        stringBuffer.append(" Eval: ");
                        stringBuffer.append(timeFirst);
                        stringBuffer.append(" > ");
                        stringBuffer.append(timeCreated);

                        logUtil.put(stringBuffer.toString(), this, "isSessionOld()");
                    }

                    return Boolean.TRUE.booleanValue();
                }
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
                {
                    logUtil.put("Role Is Not In Session", this, "isSessionOld()");
                }
            }
            return this.isRarelyUsedSession().booleanValue();

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAGERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "isSessionOld()", e);
            }
            return false;
        }
    }

    private Boolean isRarelyUsedSession()
    {    	
        if (this.role != null)
        {
            long timeCreated = this.weblisketSession.getCreationTime();
            long lastAccess = this.weblisketSession.getLastAccessedTime();

            long timePassed = role.getSessionInactivityTimeout();

            Calendar calendar = Calendar.getInstance();

            long timeFirst = calendar.getTimeInMillis() - lastAccess;

          //Temp log - remove when fixed
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
            {
                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append("Session Is Rarely Used - Timeout: ");
                stringBuffer.append(lastAccess);
                stringBuffer.append(" Eval: ");
                stringBuffer.append(timeFirst);
                stringBuffer.append(" > ");
                stringBuffer.append(timePassed);

                logUtil.put(stringBuffer.toString(), this, "isRarelyUsedSession()");
            }
            
            /*
            if (timeFirst > timePassed)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
                {
                    StringMaker stringBuffer = new StringMaker();

                    stringBuffer.append("Session Is Rarely Used - Timeout: ");
                    stringBuffer.append(lastAccess);
                    stringBuffer.append(" Eval: ");
                    stringBuffer.append(timeFirst);
                    stringBuffer.append(" > ");
                    stringBuffer.append(timePassed);

                    logUtil.put(stringBuffer.toString(), this, "isRarelyUsedSession()");
                }

                return Boolean.TRUE;
            }
            */
        } else
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
            {
                logUtil.put("Role Is Not In Session", this, "isRarelyUsedSession()");
            }
        }

        return Boolean.FALSE;
    }

    private boolean isRoleValid() throws LicensingException
    {
        try
        {
            this.makeSessionValid(BasicUserRoleFactory.getInstance().INSTALLER, userName);
            return Boolean.TRUE.booleanValue();
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
            StringMaker stringBuffer = new StringMaker();
            //role verified
            //remove username and password from future request
            //pageContext.getOut().print("Removing POST UserName and Password");

            stringBuffer.append("Trying New login<p>");
            return stringBuffer.toString();
            //end of verified role
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
            this.weblisketSession.setAuthenticated(false);

            StringMaker stringBuffer = new StringMaker();
            stringBuffer.append("Sorry your username and password is invalid on this page.");
            stringBuffer.append("Trying New login<p>");
            return stringBuffer.toString();
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
            if (BooleanUtil.getInstance().getFromString(authenticated))
            {
                //Returning with existing authenticated session
                final int size = roles.size();
                for(int index = 0; index < size; index++)
                {
                    BasicUserRole mustBeOfRole = (BasicUserRole) roles.get(index);
                    if (sessionUserName != null
                        && role.equals(mustBeOfRole))
                    {
                        return Boolean.TRUE.booleanValue();
                    }
                }
                return Boolean.FALSE.booleanValue();
            }
            return Boolean.FALSE.booleanValue();
        } catch (Exception e)
        {
            //String error = "Failed to validate previously authenticated Session";
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
            {
                logUtil.put("Starting for User: " + this.userName + " Attempt: " + this.weblisketSession.getAttempts(), this, "processIfNewLogin()");
            }

            if (userName != null && userName.compareTo(StringUtil.getInstance().EMPTY_STRING) != 0 && password != null && password.compareTo(StringUtil.getInstance().EMPTY_STRING) != 0)
            {
                String login;

                //If client user is claiming to be an installer
                //check file instead of db
                InstallerInfo installerInfo = new InstallerInfo();
                if (installerInfo.isValid(userName, password))
                {
                    login = org.allbinary.globals.GLOBALS2.LOGINSUCCESS;
                } else
                {
                    login = org.allbinary.globals.GLOBALS2.LOGINFAILED;
                }

                if (login.compareTo(org.allbinary.globals.GLOBALS2.LOGINSUCCESS) == 0)
                {
                    this.weblisketSession.setAttempts(new Integer(0));
                    return Boolean.TRUE.booleanValue();
                }

                this.nextAttempt();
                return Boolean.FALSE.booleanValue();
            }

            this.nextAttempt();
            return Boolean.FALSE.booleanValue();
        } catch (Exception e)
        {
            //String error = "Failed to validate new login";
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
            if (BooleanUtil.getInstance().getFromString(timeout))
            {
                this.invalidateSession();
                return Boolean.TRUE.booleanValue();
            }
            {
                this.weblisketSession.setTimeout(BooleanFactory.getInstance().TRUE_STRING);
                return Boolean.FALSE.booleanValue();
            }
        } catch (Exception e)
        {
            //String error = "Failed check if already set to invalidate";
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
            this.request = (HttpServletRequest) pageContext.getRequest();
            this.weblisketSession = new BasicWeblisketSession(pageContext);

            this.getFormData();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
            {
                logUtil.put("Started", this, "doStartTag()");
            }

            if (command != null)
            {
                //Test Error Pages
                //if(command!=null) throw new JspTagException(e2);
                //if(command!=null) throw new LicensingException();

                if (command.compareTo(org.allbinary.globals.GLOBALS2.CHANGEPASSWORD) == 0)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                    {
                        logUtil.put("Change Password", this, "doStartTag()");
                    }

                    if (!this.changePassword())
                    {
                        pageContext.getOut().print("Password Change Attempt Failed.<p>");
                        return EVAL_BODY_INCLUDE;
                    } else
                    {
                        pageContext.getOut().print("Password Changed Successfully.<p>");
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
                        // logUtil.put("Session is old", this, "doStartTag()");
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

                        if (userName != null && userName.compareTo(StringUtil.getInstance().EMPTY_STRING) != 0
                            && password != null && password.compareTo(StringUtil.getInstance().EMPTY_STRING) != 0)
                        {
                            pageContext.getOut().print("Sorry your username and/or password is invalid.<p>");
                        }
                    }

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
                    {
                        StringMaker stringBuffer = new StringMaker();

                        stringBuffer.append("Major authentication error - userName: ");
                        stringBuffer.append(this.userName);
                        stringBuffer.append(" Password: ");
                        stringBuffer.append(this.password);
                        stringBuffer.append(" Command: ");
                        stringBuffer.append(this.command);

                        logUtil.put(stringBuffer.toString(), this, "doStartTag()");
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
