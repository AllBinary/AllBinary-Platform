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
package allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors.output;

import org.w3c.dom.Node;

import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

public class ProfileActionScriptOutputFactory
{
    
    private ProfileActionScriptOutputFactory()
    {
    }
    
    public static ProfileActionScriptOutputInterface getInstance(
        Node node)
        throws Exception
    {
        ProfileActionScriptOutputInterface profileActionScriptOutputInterface = null;

        if(DomSearchHelper.getNodeNoThrow(ImageActionScriptOutputData.NAME,
            node.getChildNodes()) != null)
        {
            profileActionScriptOutputInterface = new ImageActionScriptOutput(node);
        }
        else
        {
            throw new Exception("No Such ActionScript Output");
        }

        return profileActionScriptOutputInterface;
    }
}
