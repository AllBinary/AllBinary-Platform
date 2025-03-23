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
package org.allbinary.logic.system.security.licensing.client;

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
import org.allbinary.logic.system.security.licensing.AbeClientLicense;
import org.allbinary.logic.system.security.licensing.AbeLicenseInterface;

public class XmlRpcAbeLicenseRetrievalClient extends XmlRpcAbeClient
{
    public XmlRpcAbeLicenseRetrievalClient(AbeClientInformationInterface clientInfo)
    {
        super(clientInfo, "LicServ.getLicense");
    }
    
    public Object get(final Object object, final CryptInterface cryptInterface) throws Exception
    {
        try
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            
            final String server = getClientInfo().getLicenseServer(this.getServer());
            
            final StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append(TRYING);
            stringBuffer.append(this.getServer());
            stringBuffer.append(SEP);
            stringBuffer.append(server);
            
            LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START_LABEL + stringBuffer.toString(), this, CommonStrings.getInstance().GET));
            // }

            final Vector param = new Vector();

            this.setClient(new XmlRpcClient(server));
            this.getClient().setBasicAuthentication(null, null);

            final Hashtable hashtable = this.getClientInfo().toHashtable();
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            LogUtil.put(LogFactory.getInstance(CLIENT_INFO + hashtable.toString(), this, CommonStrings.getInstance().GET));
            // }

            /*
             * if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING)) {
             * LogUtil.put(LogFactory.getInstance("Xml-Rpc Client Liscense Request: \n" +
             * client.toString(), this,GET)); }
             */

            param.add(hashtable);
            // KeySpecFactory.DES,
            final Object result = getClient().execute(this.getRemoteMethod(), param, cryptInterface);

            /*
             * this could return without trying all servers if(result==null) {
             * //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
             * //{ LogUtil.put(LogFactory.getInstance("Empty Result\n", this,GET)); //}
             * return null; }
             */

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            LogUtil.put(LogFactory.getInstance(RESULT + result.toString(), this, CommonStrings.getInstance().GET));
            // }

            final Hashtable resultHashtable = (Hashtable) result;

            if (!AbeClientLicense.hasRequiredKeys(resultHashtable))
            {
                // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
                // {
                LogUtil.put(LogFactory.getInstance(INVALID, this, CommonStrings.getInstance().GET));
                // }

                return this.tryAnother(object);
            }

            final AbeLicenseInterface abeLicenseInterface = new AbeClientLicense(resultHashtable);

            // throw new LicensingException("Keys Failed To Validate");

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END + stringBuffer.toString(), this, CommonStrings.getInstance().GET));
            // }

            isOnline = true;
            return abeLicenseInterface;
        } catch (IOException e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            LogUtil.put(LogFactory.getInstance(EXCEPTION_IN_CLIENT, this, CommonStrings.getInstance().GET, e));
            // }

            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            LogUtil.put(LogFactory.getInstance(TRYING_OTHER_SERVERS + ExceptionUtil.getInstance().getStackTrace(e), this, CommonStrings.getInstance().GET));
            // }

            if(!e.getMessage().startsWith(HOST_NOT_RESOLVED))
            {
                return this.tryAnother(object);
            }
            else
            {
                isOnline = false;
                throw new Exception(HOST_NOT_RESOLVED_MSG);
            }

        // return this.tryOtherServers();
        } catch (XmlRpcException e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            LogUtil.put(LogFactory.getInstance(SERVER_REPORTED_ERROR, this, CommonStrings.getInstance().GET, e));
            // }
            return this.tryAnother(object);
        } catch (Exception e)
        {
            // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            LogUtil.put(LogFactory.getInstance(UNKNOWN_ERROR, this, CommonStrings.getInstance().GET, e));
            // }
            return this.tryAnother(object);
        }
    }
}
