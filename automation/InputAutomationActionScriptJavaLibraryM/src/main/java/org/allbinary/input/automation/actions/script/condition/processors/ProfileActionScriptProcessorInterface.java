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

import javax.swing.tree.MutableTreeNode;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.input.automation.actions.script.ProfileActionScriptItemInterface;
import org.allbinary.input.automation.actions.script.condition.CustomTreeNodeInterface;

public interface ProfileActionScriptProcessorInterface
    extends CustomTreeNodeInterface, DomNodeInterface, MutableTreeNode, 
    ProfileActionScriptItemInterface
{
}
