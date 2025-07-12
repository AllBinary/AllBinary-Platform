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
package org.allbinary.gui.swing.workers;

import javax.swing.*;

public class JListSwingWorker extends SwingWorker
{
    private JList jList;
    private ListModel listModel;
    
    public JListSwingWorker(JList jList, ListModel listModel)
    {
        this.setJList(jList);
        this.setListModel(listModel);
    }
    
    public Object doInBackground()
    {
        return null;
    }
    
    protected void done()
    {
        this.getJList().setModel(this.getListModel());
    }

    private JList getJList()
    {
        return jList;
    }

    private void setJList(JList jList)
    {
        this.jList = jList;
    }

    private ListModel getListModel()
    {
        return listModel;
    }

    private void setListModel(ListModel listModel)
    {
        this.listModel = listModel;
    }
}
