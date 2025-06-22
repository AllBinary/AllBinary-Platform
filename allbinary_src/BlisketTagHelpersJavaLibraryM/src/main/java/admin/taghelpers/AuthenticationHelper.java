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
package admin.taghelpers;

import javax.servlet.jsp.PageContext;

import javax.servlet.jsp.tagext.TagSupport;

import org.allbinary.business.user.role.BasicUserRole;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.java.bool.BooleanUtil;

import org.allbinary.business.user.UserInterface;

import org.allbinary.business.user.role.UserRole;

import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.data.tables.user.UserEntityInterface;

import org.allbinary.logic.communication.http.request.session.WeblisketSession;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.role.UserRoleFactory;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.allbinary.globals.GLOBALS2;
import org.allbinary.string.CommonStrings;

public class AuthenticationHelper
    implements TagHelperInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private WeblisketSession weblisketSession;
    private String authenticated;
    private String sessionUserName;
    private UserRole role;
    private String timeout;
    private Integer attemptsInteger;

    //private final String TIMEOUT = "TIMEOUT";
    public AuthenticationHelper(HashMap hashMap, PageContext pageContext)
        throws Exception
    {
        this(hashMap, (HttpServletRequest) pageContext.getRequest());

        //this.pageContext = pageContext;
    }

    public AuthenticationHelper(HashMap hashMap, HttpServletRequest httpServletRequest)
        throws Exception
    {
        this.weblisketSession =
            new WeblisketSession(hashMap, httpServletRequest);

        this.getFormData();

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
        {
            LogUtil.put(LogFactory.getInstance(this.outputSessionInfo(), this, this.commonStrings.CONSTRUCTOR));
        }
    }

    private void getFormData() throws Exception
    {
        this.authenticated = this.weblisketSession.getAuthentication();
        this.sessionUserName = this.weblisketSession.getUserName();
        this.role = this.weblisketSession.getRole();
        this.attemptsInteger = this.weblisketSession.getAttempts();
        this.timeout = this.weblisketSession.getTimeout();
    }

    public Integer invalidateSession() throws Exception
    {
        try
        {
            this.weblisketSession.clear();
            //No need to really invalidate a session
            //this.weblisketSession.invalidate();
            return new Integer(TagSupport.EVAL_BODY_INCLUDE);
        } catch (Exception e)
        {
            String error = "Failed to invalidateSession";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATIONERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "invalidateSession()", e));
            }
            return null;
        }
    }

    public Boolean isSessionOld()
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
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("Session Is Old - Timeout: ");
                    stringBuffer.append(sessionTimout);
                    stringBuffer.append(" Eval: ");
                    stringBuffer.append(timeFirst);
                    stringBuffer.append(" > ");
                    stringBuffer.append(timeCreated);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isSessionOld()"));
                }

                return Boolean.TRUE;
            }
        } else
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
            {
                LogUtil.put(LogFactory.getInstance("Role Is Not In Session", this, "isSessionOld()"));
            }
        }

        return this.isRarelyUsedSession();
    }

    private Boolean isRarelyUsedSession()
    {
        if (this.role != null)
        {
            long timeCreated = this.weblisketSession.getCreationTime();
            long lastAccess = this.weblisketSession.getLastAccessedTime();

            long inactivityAllowed = role.getSessionInactivityTimeout();

            Calendar calendar = Calendar.getInstance();

            long timeFirst = calendar.getTimeInMillis() - lastAccess;

          //Temp log - remove when fixed
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Session Is Rarely Used - Timeout: ");
                stringBuffer.append(lastAccess);
                stringBuffer.append(" Eval: ");
                stringBuffer.append(timeFirst);
                stringBuffer.append(" > ");
                stringBuffer.append(inactivityAllowed);

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isRarelyUsedSession()"));
            }
            
            /*
            if (timeFirst > inactivityAllowed)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("Session Is Rarely Used - Timeout: ");
                    stringBuffer.append(lastAccess);
                    stringBuffer.append(" Eval: ");
                    stringBuffer.append(timeFirst);
                    stringBuffer.append(" > ");
                    stringBuffer.append(inactivityAllowed);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isRarelyUsedSession()"));
                }

                return Boolean.TRUE;
            }
            */
        } else
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
            {
                LogUtil.put(LogFactory.getInstance("Role Is Not In Session", this, "isRarelyUsedSession()"));
            }
        }

        return Boolean.FALSE;
    }

    public String validRole()
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();
            //role verified
            //remove username and password from future request
            //pageContext.getOut().print("Removing POST UserName and Password");

            stringBuffer.append("Trying New login<p/>");
            return stringBuffer.toString();
            //end of verified role
        } catch (Exception e)
        {
            String error = "Failed to set valid role";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATIONERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "validRole()", e));
            }
            return error;
        }
    }

    public String invalidRole()
    {
        try
        {
            this.weblisketSession.setAuthenticated(false);

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Sorry your username and password is invalid on this page. ");
            stringBuffer.append("Trying New login<p/>");
            return stringBuffer.toString();
        } catch (Exception e)
        {
            String error = "Failed to set role invalid";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATIONERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "invalidRole()", e));
            }
            return error;
        }
    }

    public boolean isAuthenticated() throws Exception
    {
        if (BooleanUtil.getInstance().getFromString(authenticated))
        {
            return true;
        } else
        {
            return false;
        }
    }

    public Boolean isAuthenticationSessionValid(Vector roles)
    {
        try
        {
            if (this.role != null)
            {
                if (this.isAuthenticated())
                {
                    //Returning with existing authenticated session
                	if(sessionUserName != null)
                	{
                    Iterator iter = roles.iterator();

                    BasicUserRole basicUserRole = role.getBasicUserRole();

                    while (iter.hasNext())
                    {
                        BasicUserRole mustBeOfRole = (BasicUserRole) iter.next();
                        if (basicUserRole.equals(mustBeOfRole))
                        {
                            if (basicUserRole.equals(UserRoleFactory.getInstance().CUSTOMER))
                            {
                                UserEntityInterface userEntityInterface = UserEntityFactory.getInstance();
                                UserInterface userInterface = userEntityInterface.getUser(sessionUserName);
                                if (!userInterface.isSessionValid().booleanValue())
                                {
                                    return Boolean.FALSE;
                                }
                            }
                            return Boolean.TRUE;
                        }
                    }
                	}
                    return Boolean.FALSE;
                }

            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATION))
                {
                    LogUtil.put(LogFactory.getInstance("Role Is Not In Session", this, "isAuthenticationSessionValid()"));
                }
            }

            return Boolean.FALSE;
        } catch (Exception e)
        {
            String error = "Failed to validate previously authenticated Session";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATIONERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "isAuthenticatedSessionValid()", e));
            }
            return Boolean.FALSE;
        }
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

    public Boolean processIfNewLogin(String userName, String password)
    {
        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATIONERROR))
            {
                LogUtil.put(LogFactory.getInstance("Starting: " + userName, this, "processIfNewLogin()"));
            }

            if (userName != null && userName.compareTo(StringUtil.getInstance().EMPTY_STRING) != 0
                && password != null && password.compareTo(StringUtil.getInstance().EMPTY_STRING) != 0)
            {
                String login;

                /*
                if(this.role.compareTo(UserRoleData.INSTALLER)!=0)
                {
                 */
                UserEntityInterface userEntityInterface = UserEntityFactory.getInstance();
                login = userEntityInterface.login(userName, password);
                /*
                }
                else
                {
                //If client user is claiming to be an installer
                //check file instead of db
                InstallerInfo installerInfo = new InstallerInfo();
                if(installerInfo.isValid(userName, password))
                {
                login = allbinary.globals.GLOBALS2.LOGINSUCCESS;
                }
                else
                {
                login = allbinary.globals.GLOBALS2.LOGINFAILED;
                }
                }
                 */

                if (login.compareTo(GLOBALS2.LOGINSUCCESS) == 0)
                {
                    UserInterface userInterface = userEntityInterface.getUser(userName);
                    if (userInterface.isSessionValid().booleanValue())
                    {
                        this.weblisketSession.setAttempts(new Integer(0));
                        return Boolean.TRUE;
                    }
                }

                this.nextAttempt();
                return Boolean.FALSE;
            }

            this.nextAttempt();
            return Boolean.FALSE;
        } catch (Exception e)
        {
            String error = "Failed to validate new login";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATIONERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "processIfNewLogin()", e));
            }
            return Boolean.FALSE;
        }
    }

    public Boolean processInvalidation()
    {
        try
        {
            if (BooleanUtil.getInstance().getFromString(timeout))
            {
                this.invalidateSession();
                return Boolean.TRUE;
            }
            {
                this.weblisketSession.setTimeout(BooleanFactory.getInstance().TRUE_STRING);
                return Boolean.FALSE;
            }
        } catch (Exception e)
        {
            String error = "Failed check if already set to invalidate";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().AUTHENTICATIONERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "processInvalidation()", e));
            }
            return Boolean.FALSE;
        }
    }

    //used for debugging
    public String outputSessionInfo()
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            long timeCreated = this.weblisketSession.getCreationTime();
            StringBuffer stringBuffer = new StringBuffer();

            long lastAccess = this.weblisketSession.getLastAccessedTime();
            stringBuffer.append("Time Since Last Access: " + (calendar.getTimeInMillis() - lastAccess) + "<br/>\n");

            if (this.role != null)
            {
                stringBuffer.append("Auth: ");
                stringBuffer.append(this.authenticated);
                stringBuffer.append(" UserName: ");
                stringBuffer.append(this.sessionUserName);
                stringBuffer.append(" Role: ");
                stringBuffer.append(this.role.toString());

                //Remarked code shows user time left in session and last use
                stringBuffer.append("<br/>\n");
                stringBuffer.append("Time Left: ");
                stringBuffer.append((role.getSessionTimeout() - (calendar.getTimeInMillis() - timeCreated)));
                stringBuffer.append("<br/>\n");
                stringBuffer.append("Inactivity Time Allowed: ");
                stringBuffer.append(role.getSessionTimeout());
                stringBuffer.append("<br/>\n");
                stringBuffer.append("Inactivity Time Allowed: ");
                stringBuffer.append(role.getSessionInactivityTimeout());
                stringBuffer.append("<br/>\n");
            } else
            {
                stringBuffer.append("Role Is Not Set<br/>\n");
            }

            return stringBuffer.toString();
        } catch (Exception e)
        {
            return "Error";
        }
    }

    /**
     * @return the role
     */
    public UserRole getRole()
    {
        return role;
    }
}
