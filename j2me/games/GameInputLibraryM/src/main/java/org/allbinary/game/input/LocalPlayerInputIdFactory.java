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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.analog.AnalogLocationInputFactory;
import org.allbinary.logic.string.StringMaker;

public class LocalPlayerInputIdFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final LocalPlayerInputIdFactory instance = new LocalPlayerInputIdFactory();

    public final int MAX_NUMBER_OF_PLAYERS = 6;
    private final int MAX_FAST_DEVICEID = 65;
    private final int[] deviceIdToplayerId = new int[MAX_FAST_DEVICEID];
    private final int[] playerIdToDeviceId = new int[MAX_NUMBER_OF_PLAYERS];
    private final boolean[] playersInPlay = new boolean[MAX_NUMBER_OF_PLAYERS];

    private int totalDevicesMapped = 0;

    private LocalPlayerInputIdFactory() {

        for(int index = deviceIdToplayerId.length - 1; index >= 0; index--)
        {
            deviceIdToplayerId[index] = -1;
        }
    }

    public static LocalPlayerInputIdFactory getInstance() {
        return instance;
    }

    public int getDeviceId(int playerInputId)
    {
        return this.playerIdToDeviceId[playerInputId];
    }

    public int getPlayerForDevice(int deviceId) {
        int index;
        
        if(deviceId < MAX_FAST_DEVICEID)
        {
            int playerInputId = deviceIdToplayerId[deviceId];
            if(playerInputId != -1)
            {
                //logUtil.put("Fast Find DeviceId: ").append(deviceId).append(" at playerInputId: ").append(playerInputId, this, "getPlayerForDevice");
                return playerInputId;
            }
        }
        else
        {
            for (index = totalDevicesMapped - 1; index >= 0; index--) {
                if (this.playerIdToDeviceId[index] == deviceId) {
                    //logUtil.put("Found DeviceId: ").append(deviceId).append(" at playerInputId: ").append(index, this, "getPlayerForDevice");
                    return index;
                }
            }
        }

        index = totalDevicesMapped;
        
        //Add New Player for device
        if(deviceId < MAX_FAST_DEVICEID)
        {        
            this.deviceIdToplayerId[deviceId] = index;
        }
        else
        {
            logUtil.put(new StringMaker().append("Added DeviceId: ").append(deviceId).append(" beyond fast Id list").toString(), this, "getPlayerForDevice");
        }

        this.playerIdToDeviceId[index] = deviceId;

        AnalogLocationInputFactory.getInstance().addPlayer(totalDevicesMapped);

        totalDevicesMapped++;

        logUtil.put(new StringMaker().append("Added DeviceId: ").append(deviceId).append(" at: ").append(index).append(" Total: ").append(this.totalDevicesMapped).toString(), this, "getPlayerForDevice");
        //At some point add Ouya and other special mapping here and return it instead of the internal mapping
        //int playerNum = OuyaController.getPlayerNumByDeviceId(deviceId);
        return index;
    }

    /**
     * @return the currentNumberOfPlayers
     */
    public int getTotalDevicesInPlay() {

        int total = 0;
        for (int index = totalDevicesMapped - 1; index >= 0; index--) {
            if (this.playersInPlay[index] == true) {
                total++;
            }
        }

        return total;
    }

/*
    public boolean isDeviceInPlay(int deviceId)
    {
        return playersInPlay[this.getPlayerForDevice(deviceId)];
    }

    public void setDeviceInPlay(int deviceId)
    {
        int playerInputId = this.getPlayerForDevice(deviceId);
        logUtil.put("Setting DeviceInPlay with DEVICE_ID_LABEL: ").append(deviceId).append(" playerInputId: ").append(playerInputId, this, "setDeviceInPlay");
        playersInPlay[playerInputId] = true;
    }

    public void setDeviceOutOfPlay(int deviceId)
    {
        int playerInputId = this.getPlayerForDevice(deviceId);
        logUtil.put("Setting DeviceOutOfPlay with DEVICE_ID_LABEL: ").append(deviceId).append(" playerInputId: ").append(playerInputId, this, "setDeviceInPlay");
        playersInPlay[playerInputId] = false;
    }
    */

    public boolean isPlayerInPlay(int playerInputId)
    {
        return playersInPlay[playerInputId];
    }    

    public void setPlayerInPlay(int playerInputId)
    {
        logUtil.put(new StringMaker().append("Setting PlayerInPlay with playerInputId: ").append(playerInputId).toString(), this, "setPlayerInPlay");
        playersInPlay[playerInputId] = true;
    }

    public void setPlayerOutOfPlay(int playerInputId)
    {
        logUtil.put(new StringMaker().append("Setting PlayerOutOfPlay with playerInputId: ").append(playerInputId).toString(), this, "setPlayerOutOfPlay");
        playersInPlay[playerInputId] = false;
    }   
    
    /**
     * @return the inPlayToDeviceId
     */
    public boolean[] getPlayersInPlay() {
        return playersInPlay;
    }

    /**
     * @return the totalDevicesMapped
     */
    /*
    public int getTotalDevicesMapped() {
        return totalDevicesMapped;
    }
    */
}
