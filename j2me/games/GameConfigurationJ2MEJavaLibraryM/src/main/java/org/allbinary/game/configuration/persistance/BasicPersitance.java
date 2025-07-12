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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.persistance.PlatformRecordIdUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class BasicPersitance
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final PersistanceStrings persistanceStrings = PersistanceStrings.getInstance();
    private final PlatformRecordIdUtil platformRecordIdUtil = PlatformRecordIdUtil.getInstance();
    
    private final String recordId;
    
    protected final BasicArrayList valueList = new BasicArrayList();
    protected final BasicArrayList idList = new BasicArrayList();
    
    protected BasicPersitance(final String recordId)
    {
        this.recordId = recordId;
    }

    public void deleteRecoreStore(final AbeClientInformationInterface abeClientInformation) throws Exception 
    {
        RecordStore.deleteRecordStore(this.getRecordId(abeClientInformation));
    }
    
    //Load all needs to be called already
    public void deleteAll(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        int size = idList.size();
        for(int index = 0; index < size; index++)
        {
            Integer integer = (Integer) this.idList.objectArray[index];
            this.delete(abeClientInformation, integer.intValue());
        }
        
        this.clear();
    }
    
    public void delete(final AbeClientInformationInterface abeClientInformation, final int deleteId) throws Exception
    {
        RecordStore recordStore = null;
        
        try {
            
        logUtil.put(new StringMaker().append(this.persistanceStrings.DELETING_WITH_ID).append(deleteId).toString(), this, this.commonStrings.delete);
        
        recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        recordStore.deleteRecord(deleteId);

        } finally {
            if(recordStore != null) {
                PreLogUtil.put(this.persistanceStrings.CLOSING_RECORDSTORE, this, this.commonStrings.delete);
                recordStore.closeRecordStore();
            }
        }

    }
    
    public String getRecordId(final AbeClientInformationInterface abeClientInformation) {
        return platformRecordIdUtil.getRecordId(abeClientInformation, recordId);
    }

    public BasicArrayList getList()
    {
        return this.valueList;
    }

    public BasicArrayList getIds()
    {
        return this.idList;
    }

    public void clear()
    {
        this.valueList.clear();
        this.idList.clear();
    }
}
