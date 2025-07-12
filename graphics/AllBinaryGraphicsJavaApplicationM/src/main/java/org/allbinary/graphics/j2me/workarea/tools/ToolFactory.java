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

import org.allbinary.graphics.j2me.StatusFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author user
 */
public class ToolFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
            logUtil.put(commonStrings.EXCEPTION, this, "setSelectionTool", e);
        }
    }

    public GraphicsItemInterfaceFactoryInterface getSelectedToolFactory()
    {
        StatusFactory.getInstance().setStatus("Tool In Use: " + tool);

        if (tool != null)
        {
            this.selectedTool = GraphicItemFactory.getInstance().getInstance(tool);

            logUtil.put("New Tool: " + this.selectedTool, this, "getSelectedToolFactory");

            return this.selectedTool;
        }
        else
        {
            return null;
        }
    }
}
