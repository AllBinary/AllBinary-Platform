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
package org.allbinary.logic.communication.xmlrpc;

import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.apache.xmlrpc.NullXmlRpcHandler;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcHandler;

public class XmlRpcAbeClient
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final String remoteMethod;
    private final AbeClientInformationInterface clientInfo;
    private XmlRpcHandler client = NullXmlRpcHandler.NULL_XML_RPC_HANDLER;
    private int server;
    private int start;
    private int maxServers;
    private boolean isDone;

    private final String START_SERVER = "Start With Server #";
    protected final String TRYING = "Trying Server #";
    protected final String SEP = CommonLabels.getInstance().COLON_SEP;
    
    protected final String CLIENT_INFO = "Client Info: \n";
    protected final String RESULT = "Result: \n";
    protected final String INVALID = "License data is Invalid Trying Other Servers";
    
    protected final String EXCEPTION_IN_CLIENT = "Exception in client";
    protected final String SERVER_REPORTED_ERROR = "Server reported error";
    protected final String UNKNOWN_ERROR = "Unknown License Retrieval Failure";

    protected final String TRYING_OTHER_SERVERS = "IOException Trying Other Servers";

    protected final String HOST_NOT_RESOLVED_MSG = "Not Trying Again Since Host Unresolved";
    protected final String HOST_NOT_RESOLVED = "Host is unresolved";
    
    public static boolean isOnline = true;
    
    private final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();
    
    public XmlRpcAbeClient(AbeClientInformationInterface clientInfo,
            String remoteMethod)
    {
        this.remoteMethod = remoteMethod;

        this.clientInfo = clientInfo;

        if (clientInfo.getNumberOfLicenseServers() > 1)
        {
            // Now the first server is a backup license server
            this.maxServers = clientInfo.getNumberOfLicenseServers() - 2;
            this.start = this.myRandomFactory.getAbsoluteNextInt(this.maxServers) + 1;

            // Originally the first server in the list was a normal license
            // server
            // this.maxServers = clientInfo.getNumberOfLicenseServers() - 1;
            // this.start = new Random().nextInt(maxServers);
        }
        else if (clientInfo.getNumberOfLicenseServers() == 1)
        {
            this.maxServers = 0;
            this.start = 0;
        }

        this.isDone = false;

        this.server = this.start;

        // if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
        // {
        
        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(this.START_SERVER);
        stringBuffer.appendint(this.getServer());
        stringBuffer.append(this.SEP);
        stringBuffer.append(clientInfo.getLicenseServer(this.getServer()));
        
        this.logUtil.putF(stringBuffer.toString(), this, commonStrings.CONSTRUCTOR);
        // }
    }

    public Object get(Object object) throws Exception
    {
        ForcedLogUtil.log(this.commonStrings.NOT_IMPLEMENTED, this);
        return NullUtil.getInstance().NULL_OBJECT;
    }

    protected Object tryAnother(Object object) throws Exception
    {
        if (getServer() < getMaxServers())
        {
            this.setServer(getServer() + 1);
        }
        else
        {
            this.setServer(0);
        }

        if (getServer() != getStart() && !isIsDone())
        {
            return this.get(object);
        }
        else
        {
            this.setIsDone(true);
        }
        throw new Exception("Tried All Servers But Still Failed");

    }

    /**
     * @return the clientInfo
     */
    protected AbeClientInformationInterface getClientInfo()
    {
        return this.clientInfo;
    }

    /**
     * @return the client
     */
    protected XmlRpcHandler getClient()
    {
        return this.client;
    }

    /**
     * @param client
     *            the client to set
     */
    protected void setClient(XmlRpcClient client)
    {
        this.client = client;
    }

    /**
     * @return the server
     */
    protected int getServer()
    {
        return this.server;
    }

    /**
     * @param server
     *            the server to set
     */
    protected void setServer(int server)
    {
        this.server = server;
    }

    /**
     * @return the start
     */
    protected int getStart()
    {
        return this.start;
    }

    /**
     * @param start
     *            the start to set
     */
    protected void setStart(int start)
    {
        this.start = start;
    }

    /**
     * @return the maxServers
     */
    protected int getMaxServers()
    {
        return this.maxServers;
    }

    /**
     * @param maxServers
     *            the maxServers to set
     */
    protected void setMaxServers(int maxServers)
    {
        this.maxServers = maxServers;
    }

    /**
     * @return the isDone
     */
    protected boolean isIsDone()
    {
        return this.isDone;
    }

    /**
     * @param isDone
     *            the isDone to set
     */
    protected void setIsDone(boolean isDone)
    {
        this.isDone = isDone;
    }

    protected String getRemoteMethod()
    {
        return this.remoteMethod;
    }
}
