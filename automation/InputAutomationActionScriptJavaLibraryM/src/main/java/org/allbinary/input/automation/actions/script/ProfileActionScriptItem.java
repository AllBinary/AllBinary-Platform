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
package org.allbinary.input.automation.actions.script;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

import org.w3c.dom.Node;

public class ProfileActionScriptItem extends DefaultMutableTreeNode
    implements ProfileActionScriptItemInterface, ActionListener
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private JPopupMenu jPopupMenu;

    private final static String EDIT = "Edit";
    protected final static String DELETE = "Delete";

    protected final static String NEW_CONDITION = "New Condition";
    protected final static String NEW_INPUT = "New Input";
    protected final static String NEW_OUTPUT = "New Output";
    
    protected final static String NEW_COLOR_AT = "Color At";
    protected final static String NEW_TIME_INTERVAL = "Time Interval";
    protected final static String NEW_ALWAYS = "Always On/Off";

    protected final static String NEW_KEY_INPUT = "Key";
    protected final static String NEW_MOUSE_INPUT = "Mouse";
    protected final static String NEW_JOYSTICK_INPUT = "Joystick";
    
    protected final static String NEW_IMAGE_OUTPUT = "Image";
    
    public ProfileActionScriptItem(
        String label, Node node)
    {
        super(label);
        this.init();
        
        LogUtil.put(LogFactory.getInstance("Label: " + label, this, this.commonStrings.CONSTRUCTOR));
    }
    
    public ProfileActionScriptItem(
        String label)
    {
        super(label);
        this.init();
        
        LogUtil.put(LogFactory.getInstance("Label: " + label, this, this.commonStrings.CONSTRUCTOR));
    }

    private void init()
    {
        setJPopupMenu(new JPopupMenu());

        JMenuItem jMenuItemEdit = new JMenuItem(EDIT);
        JMenuItem jMenuItemDelete = new JMenuItem(DELETE);
        
        //Action customAction = new CustomAction(this);
        //jMenuItem.setAction(customAction);

        jMenuItemEdit.addActionListener(this);
        jMenuItemDelete.addActionListener(this);

        getJPopupMenu().add(jMenuItemEdit);
        getJPopupMenu().add(jMenuItemDelete);
    }

    public void process(Long frame) throws Exception {
        throw new RuntimeException();
    }
    
    public JPopupMenu getJPopupMenu()
    {
        return jPopupMenu;
    }
    
    public void showDialog() {
        throw new RuntimeException();
    }
    
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getActionCommand().compareTo(EDIT) == 0)
        {
            this.showDialog();
        }
    }

    public void setJPopupMenu(JPopupMenu jPopupMenu)
    {
        this.jPopupMenu = jPopupMenu;
    }
}
