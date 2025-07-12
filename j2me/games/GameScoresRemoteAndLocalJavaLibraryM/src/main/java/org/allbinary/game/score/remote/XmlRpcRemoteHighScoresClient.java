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
package org.allbinary.game.score.remote;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.allbinary.init.crypt.jcehelper.CryptInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.xmlrpc.XmlRpcAbeClient;
import org.allbinary.logic.java.exception.ExceptionUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class XmlRpcRemoteHighScoresClient extends XmlRpcAbeClient
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private String page;
    
    public XmlRpcRemoteHighScoresClient(AbeClientInformationInterface clientInfo, String page, String remoteMethod)
    {
        super(clientInfo, remoteMethod);
        
        this.page = page;
        
        //Start with the first server
        this.setServer(0);
    }

    public Object get(final Object object, final CryptInterface cryptInterface) throws Exception
    {
        try
        {            
            Vector param = new Vector();

            String serverUrl = getClientInfo().getLicenseServer(this.getServer());
            
            int index = serverUrl.lastIndexOf('/');

            serverUrl = serverUrl.substring(0, index + 1) + page;
            
            StringMaker stringBuffer = new StringMaker();
            stringBuffer.append(TRYING);
            stringBuffer.append(this.getServer());
            stringBuffer.append(SEP);
            stringBuffer.append(serverUrl);

            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println(stringBuffer.toString());
            logUtil.put(stringBuffer.toString(), this, commonStrings.GET);
            // }

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
             * client.toString(), this,commonStrings.GET); }
             */

            param.addElement(hashtable);

            // KeySpecFactory.DES,
            Object result = getClient().execute(this.getRemoteMethod(), param, cryptInterface);

            /*
             * this could return without trying all servers if(result==null) {
             * //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
             * //{ logUtil.put("Empty Result\n", this,commonStrings.GET); //}
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
            //logUtil.put("End Trying Server #" + this.getServer() + CommonLabels.getInstance().COLON_SEP + getClientInfo().getLicenseServer(this.getServer()), this, commonStrings.GET);
            // }

            //Hashtable resultHashtable = (Hashtable) result;

            isOnline = true;
            return result;

        } catch (IOException e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            //String message = LogFormatUtil.get(error, stringUtil.EMPTY_STRING, stringUtil.EMPTY_STRING, e);
            //System.out.println(message);
            //logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET, e);
            // }

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            //System.out.println("IOException Trying Other Servers");
            logUtil.put(TRYING_OTHER_SERVERS + ExceptionUtil.getInstance().getStackTrace(e), this, commonStrings.GET, e);
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
