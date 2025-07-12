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
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.HashtableUtil;

public class InputPersistance extends BasicPersitance
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final HashtableUtil hashtableUtil = HashtableUtil.getInstance();

    public InputPersistance(String name)
    {
        super(name);
    }

    public void loadAll(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        final RecordStore recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        final RecordEnumeration recordEnum = recordStore.enumerateRecords(null, null, true);

        //final String ERROR_LOADING = "Error Loading gameActionInput: ";
        //PreLogUtil.put(METHOD_NAME, this, METHOD_NAME);
        
        //final String ERROR_LOADING_ID = "Error Loading id: ";
        //final String ID = " id: ";

        //final String GAME_ACTION_INPUT = " GameActionInput: ";
        
        //long gameActionInputId;
        //long inputId;
        //Input gameActionInput;
        //Input input;

        //ByteArrayInputStream byteArrayInputStream;

        //DataInputStream inputStream;

        Hashtable hashtable;
        //GameKeyMappingFactory gameKeyFactory = GameKeyMappingFactory.getInstance();            
        final StringMaker stringBuffer = new StringMaker();
        
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        int id;
        while (recordEnum.hasNextElement())
        {
            id = recordEnum.nextRecordId();

            stringBuffer.delete(0, stringBuffer.length());
            logUtil.put(stringBuffer.append(this.persistanceStrings.LOADING_ID).append(id).toString(), this, this.persistanceStrings.LOAD_ALL);

            //byteArrayInputStream = 
              //  new ByteArrayInputStream(recordStore.getRecord(id));

            //inputStream = new DataInputStream(byteArrayInputStream);

            hashtable = new Hashtable();
            
            //final InputFactory inputFactory = InputFactory.getInstance();
            
            /*
            while (inputStream.available() > 0)
            {
                gameActionInputId = Integer.parseInt(inputStream.readUTF());
                inputStream.readUTF();
                inputId = Integer.parseInt(inputStream.readUTF());
                gameActionInput = gameKeyFactory.getInstance((int) gameActionInputId);
                input = inputFactory.getInstance((int) inputId);

                if (input == null || gameActionInput == null)
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    if (input == null)
                    {
                        stringBuffer.append(ERROR_LOADING_ID);
                        stringBuffer.append(inputId);
                        stringBuffer.append(GAME_ACTION_INPUT);
                        stringBuffer.append(gameActionInputId);
                        
                        //logUtil.put(stringBuffer.toString(), this, persistanceStrings.LOAD_ALL);
                        PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);
                    }
                    if (gameActionInput == null)
                    {
                        stringBuffer.delete(0, stringBuffer.length());
                        
                        stringBuffer.append(ERROR_LOADING);
                        stringBuffer.append(gameActionInputId);
                        stringBuffer.append(ID);
                        stringBuffer.append(inputId);
                        
                        //logUtil.put(stringBuffer.toString(), this, persistanceStrings.LOAD_ALL);
                        PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);
                    }
                }
                else
                {
                    //logUtil.put("Load Mapping from: "
                      //     ).append(input.toString()).append(" to: "
                        //   ).append(gameActionInput.toString(), this, persistanceStrings.LOAD_ALL);
                }

                hashtable.put(input, gameActionInput);
            }
            */

            this.valueList.add(hashtable);
            this.idList.add(smallIntegerSingletonFactory.getInstance(id));
        }

        recordStore.closeRecordStore();
    }

    public void save(final AbeClientInformationInterface abeClientInformation, Hashtable hashtable) throws Exception
    {
        PreLogUtil.put(new StringMaker().append(this.persistanceStrings.NOT_SAVING).append(StringUtil.getInstance().toString(hashtable)).toString(), this, this.commonStrings.SAVE);
        //logUtil.put("Saving: ").append(hashtable, this, commonStrings.SAVE);
        
        final RecordStore recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //DataOutputStream outputStream = new DataOutputStream(
          //      byteArrayOutputStream);

        Input gameActionInput;
        BasicArrayList list;
        
        Input input;
        
        //byte[] savedGameBytes;
        
        //CommonSeps commonSeps = CommonSeps.getInstance();
        
        //final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        final Object[] inputObjectArray = this.hashtableUtil.getKeysAsArray(hashtable);
        final int size = inputObjectArray.length;
        for (int index = 0; index < size; index++)
        {
            gameActionInput = (Input) inputObjectArray[index];
            list = (BasicArrayList) hashtable.get(inputObjectArray[index]);

            for (int index2 = 0; index2 < list.size(); index2++)
            {
                //outputStream.writeUTF(smallIntegerSingletonFactory.getInstance(gameActionInput.getId()).toString());
                //outputStream.writeUTF(commonSeps.EQUALS);
                input = (Input) list.get(index2);
                //outputStream.writeUTF(smallIntegerSingletonFactory.getInstance(input.getId()).toString());

                //StringMaker stringBuffer = new StringMaker();
                
                //stringBuffer.append("Save Mapping from: ");
                //stringBuffer.append(input.toString());
                //stringBuffer.append(" to: ");
                //stringBuffer.append(gameActionInput.toString());

                //PreLogUtil.put(stringBuffer.toString(), this, commonStrings.SAVE);
                
                //logUtil.put("Save Mapping from: "
                //     ).append(input.toString()).append(" to: "
                  //   ).append(gameActionInput.toString(), this, commonStrings.SAVE);
            }
        }

        //savedGameBytes = byteArrayOutputStream.toString().getBytes();

        //recordStore.addRecord(savedGameBytes, 0, savedGameBytes.length);

        recordStore.closeRecordStore();
    }
}
