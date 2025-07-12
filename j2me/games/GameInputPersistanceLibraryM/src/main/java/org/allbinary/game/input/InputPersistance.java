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
package org.allbinary.game.input;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Hashtable;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

import org.allbinary.game.configuration.persistance.BasicPersitance;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.HashtableUtil;

public class InputPersistance extends BasicPersitance
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final HashtableUtil hashtableUtil = HashtableUtil.getInstance();

    public InputPersistance(final String name)
    {
        super(name);
    }

    public void loadAll(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        RecordStore recordStore = null;
        try {

        recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        final RecordEnumeration recordEnum = recordStore.enumerateRecords(null, null, true);
        
        //PreLogUtil.put(METHOD_NAME, this, METHOD_NAME);
        
        long gameActionInputId;
        long inputId;
        Input gameActionInput;
        Input input;

        Hashtable hashtable;
        
        final GameKeyMappingFactory gameKeyFactory = GameKeyMappingFactory.getInstance();            
        final StringMaker stringBuffer = new StringMaker();

        final InputFactory inputFactory = InputFactory.getInstance();
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        byte[] recordAsBytes;
        ByteArrayInputStream byteArrayInputStream;
        DataInputStream inputStream;
        while (recordEnum.hasNextElement())
        {
            final int id = recordEnum.nextRecordId();

            stringBuffer.delete(0, stringBuffer.length());
            logUtil.put(stringBuffer.append(this.persistanceStrings.LOADING_ID).append(id).toString(), this, this.persistanceStrings.LOAD_ALL);

            recordAsBytes = recordStore.getRecord(id);
            if(recordAsBytes != null) {
                
                //PreLogUtil.put("bytes in: " + ArrayUtil.getInstance().toString(recordAsBytes), this, METHOD_NAME);

                byteArrayInputStream = new ByteArrayInputStream(recordAsBytes);
                inputStream = new DataInputStream(byteArrayInputStream);
                
                hashtable = new Hashtable();

                //PreLogUtil.put("inputStream.available(): " + inputStream.available(), this, METHOD_NAME);
                
                while (inputStream.available() > 0) {
                    final String gameActionInputIdAsString = inputStream.readUTF();
                    //PreLogUtil.put("gameActionInputIdAsString: " + gameActionInputIdAsString, this, METHOD_NAME);
                    gameActionInputId = Integer.parseInt(gameActionInputIdAsString);
                    //PreLogUtil.put("gameActionInputId: " + gameActionInputId, this, METHOD_NAME);
                    inputStream.readUTF();
                    inputId = Integer.parseInt(inputStream.readUTF());
                    //PreLogUtil.put("inputId: " + inputId, this, METHOD_NAME);
                    gameActionInput = gameKeyFactory.getInstance((int) gameActionInputId);
                    input = inputFactory.getInstance((int) inputId);

                    if (input == null || gameActionInput == null) {
                        stringBuffer.delete(0, stringBuffer.length());

                        if (input == null) {
                            stringBuffer.append(this.persistanceStrings.ERROR_LOADING_ID);
                            stringBuffer.append(inputId);
                            stringBuffer.append(this.persistanceStrings.GAME_ACTION_INPUT);
                            stringBuffer.append(gameActionInputId);

                            //logUtil.put(stringBuffer.toString(), this, persistanceStrings.LOAD_ALL);
                            PreLogUtil.put(stringBuffer.toString(), this, this.persistanceStrings.LOAD_ALL);
                        }
                        if (gameActionInput == null) {
                            stringBuffer.delete(0, stringBuffer.length());

                            stringBuffer.append(this.persistanceStrings.ERROR_LOADING);
                            stringBuffer.append(gameActionInputId);
                            stringBuffer.append(this.persistanceStrings.ID);
                            stringBuffer.append(inputId);

                            //logUtil.put(stringBuffer.toString(), this, persistanceStrings.LOAD_ALL);
                            PreLogUtil.put(stringBuffer.toString(), this, this.persistanceStrings.LOAD_ALL);
                        }
                    } else {
                        //logUtil.put("Load Mapping from: "
                        //     ).append(input.toString()).append(" to: "
                        //   ).append(gameActionInput.toString(), this, persistanceStrings.LOAD_ALL);
                    }

                    hashtable.put(input, gameActionInput);
                }

                //logUtil.put("Add mapping for id", this, METHOD_NAME);
                this.valueList.add(hashtable);
                this.idList.add(smallIntegerSingletonFactory.getInstance(id));
            } else {
                //logUtil.put("No bytes for id", this, METHOD_NAME);
            }
        }
        
        } finally {
            if(recordStore != null) {
                PreLogUtil.put(this.persistanceStrings.CLOSING_RECORDSTORE, this, this.persistanceStrings.LOAD_ALL);
                recordStore.closeRecordStore();
            }
        }
    }

    public void save(final AbeClientInformationInterface abeClientInformation, final Hashtable hashtable) throws Exception
    {
        RecordStore recordStore = null;

        try {

        final StringMaker stringBuffer = new StringMaker();
        PreLogUtil.put(stringBuffer.append(this.persistanceStrings.SAVING).append(StringUtil.getInstance().toString(hashtable)).toString(), this, this.commonStrings.SAVE);
        //logUtil.put("Saving: ").append(hashtable, this, commonStrings.SAVE);

        recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);

        Input gameActionInput;
        BasicArrayList list;
        
        Input input;
        
        byte[] savedGameBytes;
        
        final CommonSeps commonSeps = CommonSeps.getInstance();
        
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        final Object[] inputObjectArray = hashtableUtil.getKeysAsArray(hashtable);
        final int size = inputObjectArray.length;
        for (int index = 0; index < size; index++)
        {
            gameActionInput = (Input) inputObjectArray[index];
            list = (BasicArrayList) hashtable.get(inputObjectArray[index]);

            for (int index2 = 0; index2 < list.size(); index2++)
            {
                
                final String gameActionInputIdAsString = smallIntegerSingletonFactory.getInstance(gameActionInput.getId()).toString();
                //stringBuffer.append(gameActionInputIdAsString);
                outputStream.writeUTF(gameActionInputIdAsString);
                //stringBuffer.append(commonSeps.EQUALS);
                outputStream.writeUTF(commonSeps.EQUALS);
                input = (Input) list.objectArray[index2];
                final String inputIdAsString = smallIntegerSingletonFactory.getInstance(input.getId()).toString();
                //stringBuffer.append(inputIdAsString);
                outputStream.writeUTF(inputIdAsString);
                
                //final String string = stringBuffer.toString();
                //PreLogUtil.put(string, this, commonStrings.SAVE);
                
                //stringBuffer.append("Save Mapping from: ");
                //stringBuffer.append(input.toString());
                //stringBuffer.append(" to: ");
                //stringBuffer.append(gameActionInput.toString());
                
                //logUtil.put("Save Mapping from: "
                //     ).append(input.toString()).append(" to: "
                  //   ).append(gameActionInput.toString(), this, commonStrings.SAVE);
            }
        }

        //TWB - new String(byte[]).getBytes() is not working correctly for Avain
        //savedGameBytes = byteArrayOutputStream.toString().getBytes();
        savedGameBytes = byteArrayOutputStream.toByteArray();
        //PreLogUtil.put("bytes out: " + ArrayUtil.getInstance().toString(savedGameBytes), this, commonStrings.SAVE);

        recordStore.addRecord(savedGameBytes, 0, savedGameBytes.length);

        } finally {
            if(recordStore != null) {
                PreLogUtil.put(this.persistanceStrings.CLOSING_RECORDSTORE, this, this.commonStrings.SAVE);
                recordStore.closeRecordStore();
            }
        }
        
    }
        
}
