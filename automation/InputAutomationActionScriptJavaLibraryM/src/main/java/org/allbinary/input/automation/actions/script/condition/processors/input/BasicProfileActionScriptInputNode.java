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
package org.allbinary.input.automation.actions.script.condition.processors.input;

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class BasicProfileActionScriptInputNode
{
    public BasicProfileActionScriptInputNode(Node node)
    throws Exception
    {
        Node actionNode = DomSearchHelper.getNode(
            GenericProfileActionScriptInputData.NAME,
            node.getChildNodes());
    }

    public BasicProfileActionScriptInputNode()
    {
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(
            GenericProfileActionScriptInputData.NAME);

        return node;
    }
}
