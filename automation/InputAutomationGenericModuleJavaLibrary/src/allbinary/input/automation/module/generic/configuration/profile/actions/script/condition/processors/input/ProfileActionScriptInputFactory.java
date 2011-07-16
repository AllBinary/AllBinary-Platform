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
package allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors.input;

import org.w3c.dom.Node;

import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

public class ProfileActionScriptInputFactory
{
    
    private ProfileActionScriptInputFactory()
    {
    }
    
    public static ProfileActionScriptInputInterface getInstance(
        Node node)
        throws Exception
    {
        ProfileActionScriptInputInterface profileActionScriptInputInterface = null;

        if(DomSearchHelper.getNodeNoThrow(KeyboardActionScriptInputData.NAME,
            node.getChildNodes()) != null)
        {
            profileActionScriptInputInterface = new KeyboardActionScriptInput(node);
        }
        else
        if(DomSearchHelper.getNodeNoThrow(MouseActionScriptInputData.NAME,
            node.getChildNodes()) != null)
        {
            profileActionScriptInputInterface = new MouseActionScriptInput(node);
        }
        else
        {
            throw new Exception("No Such ActionScript Input");
        }

        return profileActionScriptInputInterface;
    }
}
