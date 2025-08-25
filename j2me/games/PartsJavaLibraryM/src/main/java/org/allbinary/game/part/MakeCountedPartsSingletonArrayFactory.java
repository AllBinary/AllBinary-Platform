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
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class MakeCountedPartsSingletonArrayFactory
{

    private static final MakeCountedPartsSingletonArrayFactory instance = new MakeCountedPartsSingletonArrayFactory();

    public static MakeCountedPartsSingletonArrayFactory getInstance() {
        return instance;
    }

    protected final LogUtil logUtil = LogUtil.getInstance();
    
    private BasicArrayList list = BasicArrayListUtil.getInstance().getImmutableInstance();

    public PartInterface[] getInstance(final PartInterface[] partInterfaceArray) throws Exception
    {
        list = new BasicArrayList();

        for (int index = 0; index < partInterfaceArray.length; index++)
        {
            list.add(partInterfaceArray[index]);
        }

        final BasicArrayList countedBasicArrayList = CountedPickedUpLayerInterfaceFactoryPool.getInstance().getList();

        CountedPickedUpLayerInterfaceFactory layerInterfaceFactoryInterface;
        int size = countedBasicArrayList.size();
        for (int index = 0; index < size; index++)
        {
            layerInterfaceFactoryInterface = 
                    (CountedPickedUpLayerInterfaceFactory) countedBasicArrayList.objectArray[index];

            list.add(new CountedLayerInterfaceFactoryPart(0,
                    layerInterfaceFactoryInterface));
        }

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("Total Parts: ");
        stringBuffer.append(list.size());
        stringBuffer.append(" Counted: ");
        stringBuffer.append(countedBasicArrayList.size());

        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(stringBuffer.toString(), this, commonStrings.GET_INSTANCE);

        //final PartInterface[] newPartInterfaceArray = (PartInterface[]) list.toArray(new PartInterface[list.size()]);
        final PartInterface[] newPartInterfaceArray = new PartInterface[list.size()];
        final int newSize = newPartInterfaceArray.length;
        for(int index = 0; index < newSize; index++) {
            newPartInterfaceArray[index] = (PartInterface) list.get(index);
        }

        return newPartInterfaceArray;
    }
}
