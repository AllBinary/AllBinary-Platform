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
package org.allbinary.input.automation.module.actions.script.condition.processors.output;

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class BasicProfileActionScriptOutputNode
{
    public BasicProfileActionScriptOutputNode(Node node)
    throws Exception
    {
        Node actionNode = DomSearchHelper.getNode(
            GenericProfileActionScriptOutputData.NAME,
            node.getChildNodes());
    }

    public BasicProfileActionScriptOutputNode()
    {
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(
            GenericProfileActionScriptOutputData.NAME);

        return node;
    }
}
