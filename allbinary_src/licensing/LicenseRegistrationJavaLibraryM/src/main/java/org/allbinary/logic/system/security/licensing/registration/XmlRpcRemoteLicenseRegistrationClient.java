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
package org.allbinary.logic.system.security.licensing.registration;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.allbinary.init.crypt.jcehelper.CryptInterface;
import org.allbinary.logic.string.CommonLabels;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.xmlrpc.XmlRpcAbeClient;
import org.allbinary.logic.java.exception.ExceptionUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class XmlRpcRemoteLicenseRegistrationClient extends XmlRpcAbeClient
{
    private final String PAGE = "licenseregistrationserver.php";
    
    public XmlRpcRemoteLicenseRegistrationClient(AbeClientInformationInterface clientInfo)
    {
        super(clientInfo, "LicenseRegistrationServer.process");
        
        //Start with the first server
        this.setServer(0);
    }

    public Object get(final Object object, final CryptInterface cryptInterface) throws Exception
    {
        try
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("Start Trying Server #" + this.getServer() + ": " + getClientInfo().getLicenseServer(this.getServer()));

            String server = getClientInfo().getLicenseServer(this.getServer());
            
            StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append(TRYING);
            stringBuffer.append(this.getServer());
            stringBuffer.append(SEP);
            stringBuffer.append(server);
            
            LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START_LABEL + stringBuffer.toString(), this, CommonStrings.getInstance().GET));
            
            // }

            Vector param = new Vector();

            String serverUrl = getClientInfo().getLicenseServer(this.getServer());

            int index = serverUrl.lastIndexOf("/");
            
            serverUrl = serverUrl.substring(0, index + 1) + PAGE;
            //System.out.println("Renamed Server: " + serverUrl);

            this.setClient(new XmlRpcClient(serverUrl));
            this.getClient().setBasicAuthentication(null, null);

            Hashtable hashtable = (Hashtable) object;
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("Client Info: \n" + hashtable.toString());
            LogUtil.put(LogFactory.getInstance(CLIENT_INFO + hashtable.toString(), this, CommonStrings.getInstance().GET));
            // }

            /*
             * if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING)) {
             * LogUtil.put(LogFactory.getInstance("Xml-Rpc Client Liscense Request: \n" +
             * client.toString(), this,GET)); }
             */

            param.add(hashtable);
            // KeySpecFactory.DES,
            Object result = getClient().execute(this.getRemoteMethod(), param, cryptInterface);

            /*
             * this could return without trying all servers if(result==null) {
             * //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
             * //{ LogUtil.put(LogFactory.getInstance("Empty Result\n", this,GET)); //}
             * return null; }
             */

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("Result: \n" + result.toString());
            LogUtil.put(LogFactory.getInstance(RESULT + result.toString(), this, CommonStrings.getInstance().GET));
            // }

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("End Trying Server #" + this.getServer() + ": " + getClientInfo().getLicenseServer(this.getServer()));
            //LogUtil.put(LogFactory.getInstance("End Trying Server #" + this.getServer() + ": " + getClientInfo().getLicenseServer(this.getServer()), this, GET));
            // }

            isOnline = true;
            return result;

        } catch (IOException e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //String message = LogFormatUtil.get(error, "", "", e);
            //System.out.println(message);
            //LogUtil.put(LogFactory.getInstance(error, this, GET, e));
            // }

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("IOException Trying Other Servers");
            LogUtil.put(LogFactory.getInstance(TRYING_OTHER_SERVERS + ExceptionUtil.getInstance().getStackTrace(e), this, CommonStrings.getInstance().GET));
            // }

            if(!e.getMessage().startsWith(HOST_NOT_RESOLVED))
            {
                return this.tryAnother(object);
            }
            else
            {
                throw new Exception(HOST_NOT_RESOLVED_MSG);
            }

        // return this.tryOtherServers();
        } catch (XmlRpcException e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //Can't log this since that is what the log is using
            //String message = LogFormatUtil.get(error, "", "", e);
            //System.out.println(message);
            LogUtil.put(LogFactory.getInstance(SERVER_REPORTED_ERROR, this, CommonStrings.getInstance().GET, e));
            // }
            return this.tryAnother(object);
        } catch (Exception e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //String message = LogFormatUtil.get(error, "", "", e);
            //System.out.println(message);
            LogUtil.put(LogFactory.getInstance(UNKNOWN_ERROR, this, CommonStrings.getInstance().GET, e));
            // }
            return this.tryAnother(object);
        }
    }
}
