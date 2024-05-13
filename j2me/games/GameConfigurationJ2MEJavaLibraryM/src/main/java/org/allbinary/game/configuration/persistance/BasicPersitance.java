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
package org.allbinary.game.configuration.persistance;

import javax.microedition.rms.RecordStore;

import org.allbinary.logic.string.StringMaker;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class BasicPersitance
{
    private final String recordId;
    
    private BasicArrayList list = new BasicArrayList();
    private BasicArrayList nameBasicArrayList = new BasicArrayList();
    
    protected BasicPersitance(final String recordId)
    {
        this.recordId = recordId;
    }
    
    //Load all needs to be called already
    public void deleteAll(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        int size = nameBasicArrayList.size();
        for(int index = 0; index < size; index++)
        {
            Integer integer = (Integer) this.nameBasicArrayList.objectArray[index];
            this.delete(abeClientInformation, integer.intValue());
        }
        
        this.clear();
    }
    
    public void delete(final AbeClientInformationInterface abeClientInformation, final int deleteId) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Deleting: ").append(deleteId).toString(), this, "delete"));
        
        RecordStore recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        recordStore.deleteRecord(deleteId);

        recordStore.closeRecordStore();
    }
    
    public String getRecordId(final AbeClientInformationInterface abeClientInformation) {
        return new StringMaker().append(abeClientInformation.toShortString()).append(CommonSeps.getInstance().UNDERSCORE).append(this.recordId).toString();
    }
    
    public BasicArrayList getList()
    {
        return list;
    }

    public BasicArrayList getIds()
    {
        return this.nameBasicArrayList;
    }
    
    public void clear()
    {
        this.list.clear();
        this.nameBasicArrayList.clear();
    }
}
