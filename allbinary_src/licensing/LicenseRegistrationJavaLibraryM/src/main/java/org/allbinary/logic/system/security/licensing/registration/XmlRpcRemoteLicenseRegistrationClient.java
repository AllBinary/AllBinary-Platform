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
import org.allbinary.string.CommonLabels;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.xmlrpc.XmlRpcAbeClient;
import org.allbinary.logic.java.exception.ExceptionUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class XmlRpcRemoteLicenseRegistrationClient extends XmlRpcAbeClient
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final String PAGE = "licenseregistrationserverssl.php";
    
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
            //System.out.println("Start Trying Server #" + this.getServer() + CommonLabels.getInstance().COLON_SEP + getClientInfo().getLicenseServer(this.getServer()));

            String server = getClientInfo().getLicenseServer(this.getServer());
            
            StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append(TRYING);
            stringBuffer.append(this.getServer());
            stringBuffer.append(SEP);
            stringBuffer.append(server);
            
            logUtil.put(CommonLabels.getInstance().START_LABEL + stringBuffer.toString(), this, commonStrings.GET);
            
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
            logUtil.put(CLIENT_INFO + hashtable.toString(), this, commonStrings.GET);
            // }

            /*
             * if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING)) {
             * logUtil.put("Xml-Rpc Client Liscense Request: \n" +
             * client.toString(), this,GET); }
             */

            param.add(hashtable);
            // KeySpecFactory.DES,
            Object result = getClient().execute(this.getRemoteMethod(), param, cryptInterface);

            /*
             * this could return without trying all servers if(result==null) {
             * //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
             * //{ logUtil.put("Empty Result\n", this,GET); //}
             * return null; }
             */

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("Result: \n" + result.toString());
            logUtil.put(RESULT + result.toString(), this, commonStrings.GET);
            // }

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("End Trying Server #" + this.getServer() + CommonLabels.getInstance().COLON_SEP + getClientInfo().getLicenseServer(this.getServer()));
            //logUtil.put("End Trying Server #" + this.getServer() + CommonLabels.getInstance().COLON_SEP + getClientInfo().getLicenseServer(this.getServer()), this, GET);
            // }

            isOnline = true;
            return result;

        } catch (IOException e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //String message = LogFormatUtil.get(error, stringUtil.EMPTY_STRING, stringUtil.EMPTY_STRING, e);
            //System.out.println(message);
            //logUtil.put(commonStrings.EXCEPTION, this, GET, e);
            // }

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("IOException Trying Other Servers");
            logUtil.put(TRYING_OTHER_SERVERS + ExceptionUtil.getInstance().getStackTrace(e), this, commonStrings.GET);
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
            //String message = LogFormatUtil.get(error, stringUtil.EMPTY_STRING, stringUtil.EMPTY_STRING, e);
            //System.out.println(message);
            logUtil.put(SERVER_REPORTED_ERROR, this, commonStrings.GET, e);
            // }
            return this.tryAnother(object);
        } catch (Exception e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //String message = LogFormatUtil.get(error, stringUtil.EMPTY_STRING, stringUtil.EMPTY_STRING, e);
            //System.out.println(message);
            logUtil.put(UNKNOWN_ERROR, this, commonStrings.GET, e);
            // }
            return this.tryAnother(object);
        }
    }
}
