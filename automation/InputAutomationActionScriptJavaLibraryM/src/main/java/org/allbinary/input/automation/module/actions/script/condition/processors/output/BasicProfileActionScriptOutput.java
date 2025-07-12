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

import org.allbinary.input.automation.actions.script.condition.processors.BasicProfileActionScriptProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class BasicProfileActionScriptOutput
    extends BasicProfileActionScriptProcessor
    implements ProfileActionScriptOutputInterface
{
    public BasicProfileActionScriptOutput(String label, Node node) 
        throws Exception
    {        
        super(label, node);
    }
    
    public BasicProfileActionScriptOutput(String label)
        throws Exception
    {
        super(label);
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(
            GenericProfileActionScriptOutputData.NAME);
        
        return node;
    }
    
}
