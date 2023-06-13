package org.apache.xmlrpc;

public class XmlRpcException extends Exception
{
    public XmlRpcException(int code, String message)
    {
        super(message);
    }
}
