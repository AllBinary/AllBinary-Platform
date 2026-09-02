package org.apache.xmlrpc;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public interface XmlRpcHandler
{
    Object execute (String method, BasicArrayList params)
            throws Exception;
}
