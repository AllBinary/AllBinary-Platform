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
package org.allbinary.input.automation.actions.script.condition.processors;

import java.awt.event.ActionEvent;

import org.allbinary.input.automation.actions.script.JTreeInterfaceFactory;
import org.allbinary.input.automation.actions.script.ProfileActionScriptItem;
import org.allbinary.input.automation.actions.script.condition.ProfileActionScriptConditionInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class BasicProfileActionScriptProcessor
    extends ProfileActionScriptItem
    implements ProfileActionScriptProcessorInterface
{    
    public BasicProfileActionScriptProcessor(
        String label, Node node) 
        throws Exception
    {
        super(label, node);
    }
    
    public BasicProfileActionScriptProcessor(String label)
        throws Exception
    {
        super(label);
    }

    public void actionPerformed(ActionEvent actionEvent)
    {
        super.actionPerformed(actionEvent);

        if(actionEvent.getActionCommand().compareTo(DELETE) == 0)
        {
            if(this.getParent() instanceof 
                ProfileActionScriptConditionInterface)
            {
                ProfileActionScriptConditionInterface
                    profileActionScriptConditionInterface = 
                    (ProfileActionScriptConditionInterface) 
                    this.getParent();

                profileActionScriptConditionInterface.removeProcessor(
                    (ProfileActionScriptProcessorInterface) this);

                JTreeInterfaceFactory.getInstance().getJTreeInterface().updateJTree();
            }
        }
    }
    
    //TWB - Fix to use Node Structure instead of nodes for each
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(
            GenericProfileActionScriptProcessorData.NAME);
        return node;
    }
}
