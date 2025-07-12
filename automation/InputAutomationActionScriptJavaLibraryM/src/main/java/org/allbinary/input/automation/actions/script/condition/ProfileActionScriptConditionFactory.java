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
package org.allbinary.input.automation.actions.script.condition;

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.w3c.dom.Node;

public class ProfileActionScriptConditionFactory
{
    
    private ProfileActionScriptConditionFactory()
    {
    }
    
    public static ProfileActionScriptConditionInterface getInstance(
        Node node)
        throws Exception
    {
        ProfileActionScriptConditionInterface profileActionScriptConditionInterface = null;

        if(DomSearchHelper.getNodeNoThrow(
            ColorAtActionScriptConditionData.NAME,
            node.getChildNodes()) != null)
        {
            profileActionScriptConditionInterface = 
                new ColorAtActionScriptCondition(node);
        }
        else
        if(DomSearchHelper.getNodeNoThrow(
            TimeIntervalActionScriptConditionData.NAME,
            node.getChildNodes()) != null)
        {
            profileActionScriptConditionInterface = 
                new TimeIntervalActionScriptCondition(node);
        }
        else
        if(DomSearchHelper.getNodeNoThrow(
            AlwaysActionScriptConditionData.NAME,
            node.getChildNodes()) != null)
        {
            profileActionScriptConditionInterface = 
                new AlwaysActionScriptCondition(node);
        }

        return profileActionScriptConditionInterface;
    }
}
