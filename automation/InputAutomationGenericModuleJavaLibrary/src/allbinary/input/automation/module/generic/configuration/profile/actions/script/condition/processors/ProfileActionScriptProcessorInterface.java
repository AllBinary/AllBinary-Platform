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
package allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors;

import javax.swing.tree.MutableTreeNode;

import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.input.automation.module.generic.configuration.profile.actions.script.ProfileActionScriptItemInterface;
import allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.CustomTreeNodeInterface;

public interface ProfileActionScriptProcessorInterface
    extends CustomTreeNodeInterface, DomNodeInterface, MutableTreeNode, 
    ProfileActionScriptItemInterface
{
}
