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
package org.allbinary.game.identification;

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class Group implements GroupInterface {

    private String name = StringUtil.getInstance().EMPTY_STRING;
    private final short teamId;

    private String string = StringUtil.getInstance().EMPTY_STRING;

    @JsConstructor
    public Group(String teamName, short teamId) {
        this.teamId = teamId;
        this.setName(teamName);
    }

    @Override
    @JsMethod
    public String getGroupName() {
        return this.name;
    }

    @JsMethod
    public void setName(final String name) {
        this.name = name;

        final GroupCommonFactory groupCommonFactory = GroupCommonFactory.getInstance();
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(groupCommonFactory.GROUP_NAME_LABEL);
        stringBuffer.append(this.name);
        stringBuffer.append(groupCommonFactory.ID_LABEL);
        stringBuffer.appendshort(this.teamId);

        this.string = stringBuffer.toString();
    }

    @Override
    @JsMethod
    public short getGroupId() {
        return this.teamId;
    }

    @JsMethod
    public String toString() {
        return this.string;
    }
}
