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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class BasicPersitance
{
    private final String recordStoreName;
    
    private BasicArrayList list = new BasicArrayList();
    private BasicArrayList nameBasicArrayList = new BasicArrayList();
    
    protected BasicPersitance(String recordId)
    {
        this.recordStoreName = recordId;
    }

    //Load all needs to be called already
    public void deleteAll() throws Exception
    {
        int size = nameBasicArrayList.size();
        for(int index = 0; index < size; index++)
        {
            Integer integer = (Integer) this.nameBasicArrayList.objectArray[index];
            this.delete(integer.intValue());
        }
        
        this.clear();
    }
    
    public void delete(int deleteId) throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Deleting: " + deleteId, this, "delete"));
        
        RecordStore recordStore = RecordStore.openRecordStore(this.getRecordStoreName(), true);

        recordStore.deleteRecord(deleteId);

        recordStore.closeRecordStore();
    }
    
    public String getRecordStoreName()
    {
        return recordStoreName;
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
