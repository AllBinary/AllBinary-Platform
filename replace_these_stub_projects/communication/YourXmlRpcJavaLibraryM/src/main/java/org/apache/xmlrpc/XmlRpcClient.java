package org.apache.xmlrpc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import org.allbinary.init.crypt.jcehelper.CryptInterface;

public class XmlRpcClient implements XmlRpcHandler
{
    public XmlRpcClient(URL url)
    {
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
        return null;
    }

    public void setBasicAuthentication(String user, String password)
    {
    }

    public Object execute(String method, Vector params)
            throws XmlRpcException, IOException
    {
        return null;
    }

    public Object execute(String method, Vector params, CryptInterface cryptInterface)
            throws XmlRpcException, IOException
    {
        return null;
    }    
}
