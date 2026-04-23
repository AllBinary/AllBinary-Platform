/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.game.multiplayer.layer;

import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author user
 */
public class RemoteInfo
{
    public static final RemoteInfo REMOTE_INFO = new RemoteInfo(StringUtil.getInstance().EMPTY_STRING, -1, -1, RemoteInfo.NEW_PLAYER_ID);
    public static final int NEW_PLAYER_ID = 0;

    public RemoteInfo(String username, int actorSessionId, int playerInputId, int id)
    {
    }
}
