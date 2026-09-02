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
package org.allbinary.input.automation.module;


import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import javax.swing.*;
import org.allbinary.logic.StdUtil;

public class DefaultListModelHelper
{
    private DefaultListModel defaultListModel;
    
    private BasicArrayList vector;
    
    public DefaultListModelHelper() throws Exception
    {
        this.vector = new BasicArrayListD();
        this.defaultListModel = new DefaultListModel();
    }

    public void initDefaultModelList()
    {
        this.defaultListModel = new DefaultListModel();

        final int size = this.vector.size();
        for (int index = 0; index < size; index++)
        {
            String moduleName = (String) this.vector.get(index);
            this.defaultListModel.addElement(moduleName);
        }
    }

    public ListModel getListModel()
    {
        return (ListModel) this.defaultListModel;
    }
    
    public void add(String string)
    {
        this.vector.add(string);
    }

    public void remove(String string)
    {
        this.vector.remove(string);
    }
}
