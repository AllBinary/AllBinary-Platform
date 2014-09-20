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

public class PlayerInputIdFactory {

    private static final PlayerInputIdFactory instance = new PlayerInputIdFactory();

    public final int MAX_NUMBER_OF_PLAYERS = 6;
    private final int MAX_FAST_DEVICEID = 64;
    private final int[] deviceIdToplayerId = new int[MAX_FAST_DEVICEID];
    private final int[] playerIdToDeviceId = new int[MAX_NUMBER_OF_PLAYERS];
    private final boolean[] playersInPlay = new boolean[MAX_NUMBER_OF_PLAYERS];

    private int totalDevicesMapped = 0;

    private PlayerInputIdFactory() {

        for(int index = deviceIdToplayerId.length - 1; index >= 0; index--)
        {
            deviceIdToplayerId[index] = -1;
        }
    }

    public static PlayerInputIdFactory getInstance() {
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
                //LogUtil.put(LogFactory.getInstance("Fast Find DeviceId: " + deviceId + " at playerInputId: " + playerInputId, this, "getPlayerForDevice"));
                return playerInputId;
            }
        }
        else
        {
            for (index = totalDevicesMapped - 1; index >= 0; index--) {
                if (this.playerIdToDeviceId[index] == deviceId) {
                    //LogUtil.put(LogFactory.getInstance("Found DeviceId: " + deviceId + " at playerInputId: " + index, this, "getPlayerForDevice"));
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
            LogUtil.put(LogFactory.getInstance("Added DeviceId: " + deviceId + " beyond fast Id list", this, "getPlayerForDevice"));
        }

        this.playerIdToDeviceId[index] = deviceId;

        AnalogLocationInputFactory.getInstance().addPlayer(totalDevicesMapped);

        totalDevicesMapped++;

        LogUtil.put(LogFactory.getInstance("Added DeviceId: " + deviceId + " at: " + index + " Total: " + this.totalDevicesMapped, this, "getPlayerForDevice"));
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
        LogUtil.put(LogFactory.getInstance("Setting DeviceInPlay with DEVICE_ID_LABEL: " + deviceId + " playerInputId: " + playerInputId, this, "setDeviceInPlay"));
        playersInPlay[playerInputId] = true;
    }

    public void setDeviceOutOfPlay(int deviceId)
    {
        int playerInputId = this.getPlayerForDevice(deviceId);
        LogUtil.put(LogFactory.getInstance("Setting DeviceOutOfPlay with DEVICE_ID_LABEL: " + deviceId + " playerInputId: " + playerInputId, this, "setDeviceInPlay"));
        playersInPlay[playerInputId] = false;
    }
    */

    public boolean isPlayerInPlay(int playerInputId)
    {
        return playersInPlay[playerInputId];
    }    

    public void setPlayerInPlay(int playerInputId)
    {
        LogUtil.put(LogFactory.getInstance("Setting PlayerInPlay with playerInputId: " + playerInputId, this, "setPlayerInPlay"));
        playersInPlay[playerInputId] = true;
    }

    public void setPlayerOutOfPlay(int playerInputId)
    {
        LogUtil.put(LogFactory.getInstance("Setting PlayerOutOfPlay with playerInputId: " + playerInputId, this, "setPlayerOutOfPlay"));
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
