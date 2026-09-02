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

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.logic.StdUtil;

import org.w3c.dom.NodeList;

/**
 *
 * @author user
 */
public class DomNodeListHelper {

    public static BasicArrayList getVector(NodeList nodeList)
    {
        BasicArrayList vector = new BasicArrayListD();
        int size = nodeList.getLength();
        for(int index = 0; index < size; index++)
        {
            vector.add(DomNodeHelper.getTextNodeValue(nodeList.item(index)));
        }
        return vector;
    }
}
