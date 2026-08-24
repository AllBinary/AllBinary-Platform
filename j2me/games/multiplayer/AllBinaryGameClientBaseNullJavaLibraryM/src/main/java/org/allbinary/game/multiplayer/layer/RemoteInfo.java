/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.game.multiplayer.layer;

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author user
 */

@JsType
public class RemoteInfo
{
    @JsProperty
    public static final int NEW_PLAYER_ID = 0;
    @JsProperty
    public static final RemoteInfo REMOTE_INFO = new RemoteInfo(StringUtil.getInstance().EMPTY_STRING, -1, -1, RemoteInfo.NEW_PLAYER_ID);

    @JsConstructor
    public RemoteInfo(String username, int actorSessionId, int playerInputId, int id)
    {
    }
}
