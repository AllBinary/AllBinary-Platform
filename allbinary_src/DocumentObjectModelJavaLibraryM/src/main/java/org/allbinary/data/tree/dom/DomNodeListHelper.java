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
package org.allbinary.data.tree.dom;

import java.util.Vector;

import org.w3c.dom.NodeList;

/**
 *
 * @author user
 */
public class DomNodeListHelper {

    public static Vector getVector(NodeList nodeList)
    {
        Vector vector = new Vector();
        int size = nodeList.getLength();
        for(int index = 0; index < size; index++)
        {
            vector.add(DomNodeHelper.getTextNodeValue(nodeList.item(index)));
        }
        return vector;
    }
}
