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
package org.allbinary.logic.communication.log;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import org.allbinary.logic.communication.xmlrpc.XmlRpcAbeClient;
import org.allbinary.logic.system.security.crypt.jcehelper.NoCrypt;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class XmlRpcRemoteLogClient extends XmlRpcAbeClient
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final NoCrypt noCrypt = new NoCrypt();
    
    public XmlRpcRemoteLogClient(final AbeClientInformationInterface clientInfo)
    {
        super(clientInfo, "SSLLogServ.logUtil");
    }

    public Object get(final Object object) throws Exception
    {
        try
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("Start Trying Server #" + this.getServer() + CommonLabels.getInstance().COLON_SEP + getClientInfo().getLicenseServer(this.getServer()));
            //logUtil.put("Start Trying Server #" + this.getServer() + CommonLabels.getInstance().COLON_SEP + getClientInfo().getLicenseServer(this.getServer()), this, commonStrings.GET);
            // }

            Vector param = new Vector();

            String serverUrl = getClientInfo().getLicenseServer(this.getServer());
            
            int index = serverUrl.lastIndexOf("/");
            
            serverUrl = serverUrl.substring(0, index + 1) + "logservssl.php";
            //System.out.println("Renamed Server: " + serverUrl);

            final XmlRpcClient xmlRpcClient = new XmlRpcClient(serverUrl);
            this.setClient(xmlRpcClient);
            xmlRpcClient.setBasicAuthentication(null, null);

            Hashtable hashtable = (Hashtable) object;
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("Client Info: \n" + hashtable.toString());
            //logUtil.put("Client Info: \n" + hashtable.toString(), this, commonStrings.GET);
            // }

            /*
             * if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING)) {
             logUtil.put("Xml-Rpc Client Liscense Request: \n" +
             * client.toString(), this,commonStrings.GET); }
             */

            param.add(hashtable);
            // KeySpecFactory.DES,
            Object result = xmlRpcClient.execute(this.getRemoteMethod(), param, noCrypt);

            /*
             * this could return without trying all servers if(result==null) {
             * //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
             * //{ logUtil.put("Empty Result\n", this,commonStrings.GET); //}
             * return null; }
             */

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            System.out.println("Result: \n" + result.toString());
            //logUtil.put("Result: \n" + result.toString(), this, commonStrings.GET);
            // }

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("End Trying Server #" + this.getServer() + CommonLabels.getInstance().COLON_SEP + getClientInfo().getLicenseServer(this.getServer()));
            //logUtil.put("End Trying Server #" + this.getServer() + CommonLabels.getInstance().COLON_SEP + getClientInfo().getLicenseServer(this.getServer()), this, commonStrings.GET);
            // }

            return result;

        } catch (IOException e)
        {
            //String error = "Exception in client: ";

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //String message = LogFormatUtil.getInstance().get(error, stringUtil.EMPTY_STRING, stringUtil.EMPTY_STRING, e);
            //System.out.println(message);
            //logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET, e);
            // }

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            System.out.println("IOException Trying Other Servers");
            //logUtil.put("IOException Trying Other Servers", this, commonStrings.GET);
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
            //String error = SERVER_REPORTED_ERROR;

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //Can't log this since that is what the log is using
            //String message = LogFormatUtil.getInstance().get(error, StringUtil.getInstance().EMPTY_STRING, StringUtil.getInstance().EMPTY_STRING, e);
            //System.out.println(message);
            //logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET, e);
            // }
            return this.tryAnother(object);
        } catch (Exception e)
        {
            //String error = UNKNOWN_ERROR;

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //String message = LogFormatUtil.getInstance().get(error, StringUtil.getInstance().EMPTY_STRING, StringUtil.getInstance().EMPTY_STRING, e);
            //System.out.println(message);
            //logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET, e);
            // }
            return this.tryAnother(object);
        }
    }
}
