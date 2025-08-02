/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.apache.xmlrpc;

import java.util.Vector;
import org.allbinary.logic.NullUtil;

/**
 *
 * @author User
 */
public class NullXmlRpcHandler implements XmlRpcHandler {
    
    public static final NullXmlRpcHandler NULL_XML_RPC_HANDLER = new NullXmlRpcHandler();
    
    @Override
    public Object execute (String method, Vector<Object> params)
            throws Exception {
        return NullUtil.getInstance().NULL_OBJECT;
    }

}
