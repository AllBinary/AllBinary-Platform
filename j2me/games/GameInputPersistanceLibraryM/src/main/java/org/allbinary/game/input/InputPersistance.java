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

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.HashtableUtil;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.game.configuration.persistance.BasicPersitance;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class InputPersistance extends BasicPersitance
{
    public InputPersistance(String name)
    {
        super(name);
    }

    public void loadAll(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        final String METHOD_NAME = "loadAll";
        
        RecordStore recordStore = null;
        try {

        recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        final RecordEnumeration recordEnum = recordStore.enumerateRecords(null, null, true);

        final String ERROR_LOADING = "Error Loading gameActionInput: ";
        final String LOADING_ID = "Loading id: ";
        
        //PreLogUtil.put(METHOD_NAME, this, METHOD_NAME);

        final String ERROR_LOADING_ID = "Error Loading id: ";
        final String ID = " id: ";

        final String GAME_ACTION_INPUT = " GameActionInput: ";
        
        long gameActionInputId;
        long inputId;
        Input gameActionInput;
        Input input;

        Hashtable hashtable;
        
        GameKeyMappingFactory gameKeyFactory = GameKeyMappingFactory.getInstance();            
        StringMaker stringBuffer = new StringMaker();
        
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        byte[] recordAsBytes;
        ByteArrayInputStream byteArrayInputStream;
        DataInputStream inputStream;
        while (recordEnum.hasNextElement())
        {
            final int id = recordEnum.nextRecordId();

            LogUtil.put(LogFactory.getInstance(new StringMaker().append(LOADING_ID).append(id).toString(), this, METHOD_NAME));

            recordAsBytes = recordStore.getRecord(id);
            if(recordAsBytes != null) {
                
                //PreLogUtil.put("bytes in: " + ArrayUtil.getInstance().toString(recordAsBytes), this, METHOD_NAME);

                byteArrayInputStream = new ByteArrayInputStream(recordAsBytes);
                inputStream = new DataInputStream(byteArrayInputStream);
                
                hashtable = new Hashtable();

                final InputFactory inputFactory = InputFactory.getInstance();

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
                            stringBuffer.append(ERROR_LOADING_ID);
                            stringBuffer.append(inputId);
                            stringBuffer.append(GAME_ACTION_INPUT);
                            stringBuffer.append(gameActionInputId);

                            //LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "loadAll"));
                            PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);
                        }
                        if (gameActionInput == null) {
                            stringBuffer.delete(0, stringBuffer.length());

                            stringBuffer.append(ERROR_LOADING);
                            stringBuffer.append(gameActionInputId);
                            stringBuffer.append(ID);
                            stringBuffer.append(inputId);

                            //LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "loadAll"));
                            PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);
                        }
                    } else {
                        //LogUtil.put(LogFactory.getInstance("Load Mapping from: "
                        //     ).append(input.toString()).append(" to: "
                        //   ).append(gameActionInput.toString(), this, "loadAll"));
                    }

                    hashtable.put(input, gameActionInput);
                }

                //LogUtil.put(LogFactory.getInstance("Add mapping for id", this, METHOD_NAME));
                this.getList().add(hashtable);
                this.getIds().add(smallIntegerSingletonFactory.getInstance(id));
            } else {
                //LogUtil.put(LogFactory.getInstance("No bytes for id", this, METHOD_NAME));
            }
        }
        
        } finally {
            if(recordStore != null) {
                PreLogUtil.put("Closing RecordStore", this, METHOD_NAME);
                recordStore.closeRecordStore();
            }
        }
    }

    public void save(final AbeClientInformationInterface abeClientInformation, Hashtable hashtable) throws Exception
    {
        RecordStore recordStore = null;

        try {

        PreLogUtil.put(new StringMaker().append("Saving: ").append(hashtable).toString(), this, "save");
        //LogUtil.put(LogFactory.getInstance("Saving: ").append(hashtable, this, "save"));

        recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);

        Input gameActionInput;
        BasicArrayList list;
        
        Input input;
        
        byte[] savedGameBytes;
        
        CommonSeps commonSeps = CommonSeps.getInstance();
        
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        Object[] inputObjectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        int size = inputObjectArray.length;
        for (int index = 0; index < size; index++)
        {
            gameActionInput = (Input) inputObjectArray[index];
            list = (BasicArrayList) hashtable.get(inputObjectArray[index]);

            for (int index2 = 0; index2 < list.size(); index2++)
            {
                //final StringMaker stringBuffer = new StringMaker();
                
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
                //PreLogUtil.put(string, this, "save");
                
                //stringBuffer.append("Save Mapping from: ");
                //stringBuffer.append(input.toString());
                //stringBuffer.append(" to: ");
                //stringBuffer.append(gameActionInput.toString());
                
                //LogUtil.put(LogFactory.getInstance("Save Mapping from: "
                //     ).append(input.toString()).append(" to: "
                  //   ).append(gameActionInput.toString(), this, "save"));
            }
        }

        //TWB - new String(byte[]).getBytes() is not working correctly for Avain
        //savedGameBytes = byteArrayOutputStream.toString().getBytes();
        savedGameBytes = byteArrayOutputStream.toByteArray();
        //PreLogUtil.put("bytes out: " + ArrayUtil.getInstance().toString(savedGameBytes), this, "save");

        recordStore.addRecord(savedGameBytes, 0, savedGameBytes.length);

        } finally {
            if(recordStore != null) {
                PreLogUtil.put("Closing RecordStore", this, "save");
                recordStore.closeRecordStore();
            }
        }
        
    }
        
}
