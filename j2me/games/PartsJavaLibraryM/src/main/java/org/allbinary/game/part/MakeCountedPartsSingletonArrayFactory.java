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
package org.allbinary.game.part;

import org.allbinary.game.layer.pickup.CountedPickedUpLayerInterfaceFactory;
import org.allbinary.game.layer.pickup.CountedPickedUpLayerInterfaceFactoryPool;
import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class MakeCountedPartsSingletonArrayFactory
{
    private static final MakeCountedPartsSingletonArrayFactory instance = new MakeCountedPartsSingletonArrayFactory();

    private static BasicArrayList list;

    public static synchronized PartInterface[] getInstance(
            PartInterface[] partInterfaceArray) throws Exception
    {
        list = new BasicArrayList();

        for (int index = 0; index < partInterfaceArray.length; index++)
        {
            list.add(partInterfaceArray[index]);
        }

        BasicArrayList countedBasicArrayList = CountedPickedUpLayerInterfaceFactoryPool.getInstance().getList();

        int size = countedBasicArrayList.size();
        for (int index = 0; index < size; index++)
        {
            CountedPickedUpLayerInterfaceFactory layerInterfaceFactoryInterface = 
                    (CountedPickedUpLayerInterfaceFactory) countedBasicArrayList.objectArray[index];

            list.add(new CountedLayerInterfaceFactoryPart(0,
                    layerInterfaceFactoryInterface));
        }

        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("Total Parts: ");
        stringBuffer.append(list.size());
        stringBuffer.append(" Counted: ");
        stringBuffer.append(countedBasicArrayList.size());

        LogUtil.put(LogFactory.getInstance(
                stringBuffer.toString(), instance, CommonStrings.getInstance().GET_INSTANCE));

        PartInterface[] newPartInterfaceArray = new PartInterface[list.size()];
        return (PartInterface[]) list.toArray(newPartInterfaceArray);
    }
}
