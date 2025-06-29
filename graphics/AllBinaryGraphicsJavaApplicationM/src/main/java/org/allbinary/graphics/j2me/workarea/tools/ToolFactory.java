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
package org.allbinary.graphics.j2me.workarea.tools;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.j2me.StatusFactory;

/**
 *
 * @author user
 */
public class ToolFactory
{
    private static final ToolFactory instance = new ToolFactory();

    /**
     * @return the instance
     */
    public static ToolFactory getInstance()
    {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private GraphicsItemInterfaceFactoryInterface selectedTool = null;
    private String tool = null;

    private final String SELECTED_LABEL = "Selected: ";

    public void setSelectedTool(String tool)
    {
        try
        {
            StatusFactory.getInstance().setStatus(SELECTED_LABEL + tool);
            this.tool = tool;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "setSelectionTool", e));
        }
    }

    public GraphicsItemInterfaceFactoryInterface getSelectedToolFactory()
    {
        StatusFactory.getInstance().setStatus("Tool In Use: " + tool);

        if (tool != null)
        {
            this.selectedTool = GraphicItemFactory.getInstance().getInstance(tool);

            LogUtil.put(LogFactory.getInstance("New Tool: " + this.selectedTool, this, "getSelectedToolFactory"));

            return this.selectedTool;
        }
        else
        {
            return null;
        }
    }
}
