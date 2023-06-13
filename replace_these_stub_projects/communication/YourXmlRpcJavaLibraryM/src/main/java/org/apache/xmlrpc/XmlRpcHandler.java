package org.apache.xmlrpc;

import java.util.Vector;

public interface XmlRpcHandler
{
    public Object execute (String method, Vector params)
            throws Exception;
}
