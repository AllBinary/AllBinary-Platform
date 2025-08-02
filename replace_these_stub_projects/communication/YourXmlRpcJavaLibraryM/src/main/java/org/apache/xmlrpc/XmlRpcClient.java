package org.apache.xmlrpc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.allbinary.init.crypt.jcehelper.CryptInterface;
import org.allbinary.logic.NullUtil;

public class XmlRpcClient implements XmlRpcHandler
{
    private final URL url;
    public XmlRpcClient(URL url)
    {
        this.url = url;
    }

    public XmlRpcClient(String url) throws MalformedURLException
    {
        this(new URL(url));
    }

    public XmlRpcClient(String hostname, int port) throws MalformedURLException
    {
        this(new URL("http://" + hostname + ':' + port + "/RPC2"));
    }

    public URL getURL()
    {
        return this.url;
    }

    public void setBasicAuthentication(String user, String password)
    {
    }

    @Override
    public Object execute(String method, Vector<Object> params)
            throws XmlRpcException, IOException
    {
        return NullUtil.getInstance().NULL_OBJECT;
    }

    public Object execute(String method, Vector<Object> params, CryptInterface cryptInterface)
            throws XmlRpcException, IOException
    {
        return NullUtil.getInstance().NULL_OBJECT;
    }    
}
