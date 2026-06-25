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
package org.allbinary.graphics.form;

import javax.microedition.lcdui.Graphics;
import org.allbinary.canvas.Processor;

import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.form.item.ABCustomItem;

public class ScrollCurrentSelectionForm 
extends ScrollSelectionForm implements UpdateMyFontInterface
{
    private final boolean moveForSmallScreen;

    class TempHorizontalFormProcessor extends Processor {

        private final ScrollCurrentSelectionForm scrollCurrentSelectionForm;
        
        TempHorizontalFormProcessor(final ScrollCurrentSelectionForm scrollCurrentSelectionForm) {
            this.scrollCurrentSelectionForm = scrollCurrentSelectionForm;
        }
        
        @Override
        public void process() throws Exception {
            this.scrollCurrentSelectionForm.processTempHorizontalForm();
            this.scrollCurrentSelectionForm.processor = Processor.getInstance();
        }
    };

    class HorizontalFormProcessor extends Processor {

        private final ScrollCurrentSelectionForm scrollCurrentSelectionForm;
        
        HorizontalFormProcessor(final ScrollCurrentSelectionForm scrollCurrentSelectionForm) {
            this.scrollCurrentSelectionForm = scrollCurrentSelectionForm;
        }
        
        @Override
        public void process() throws Exception {
            this.scrollCurrentSelectionForm.processHorizontalForm();
            this.scrollCurrentSelectionForm.processor = Processor.getInstance();
        }
    };

    class VerticalFormProcessor extends Processor {

        private final ScrollCurrentSelectionForm scrollCurrentSelectionForm;
        
        VerticalFormProcessor(final ScrollCurrentSelectionForm scrollCurrentSelectionForm) {
            this.scrollCurrentSelectionForm = scrollCurrentSelectionForm;
        }
        
        @Override
        public void process() throws Exception {
            this.scrollCurrentSelectionForm.processVerticalForm();
            this.scrollCurrentSelectionForm.processor = Processor.getInstance();
        }
    };
    
    class TempHorizontalItemIndexDx extends ItemIndexDx {
        
        private final ScrollCurrentSelectionForm scrollCurrentSelectionForm;
        
        TempHorizontalItemIndexDx(final ScrollCurrentSelectionForm scrollCurrentSelectionForm) {
            this.scrollCurrentSelectionForm = scrollCurrentSelectionForm;
        }

        @Override
        public int getDx(final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
            return this.scrollCurrentSelectionForm.getXTempHorizontalForm(index, item, dx, dy);
        }

    };
    
    class HorizontalItemIndexDx extends ItemIndexDx {
        
        private final ScrollCurrentSelectionForm scrollCurrentSelectionForm;
        
        HorizontalItemIndexDx(final ScrollCurrentSelectionForm scrollCurrentSelectionForm) {
            this.scrollCurrentSelectionForm = scrollCurrentSelectionForm;
        }

        @Override
        public int getDx(final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
            return this.scrollCurrentSelectionForm.getXHorizontalForm(index, item, dx, dy);
        }

    };
    
    class VerticalItemIndexDx extends ItemIndexDx {
        
        private final ScrollCurrentSelectionForm scrollCurrentSelectionForm;
        
        VerticalItemIndexDx(final ScrollCurrentSelectionForm scrollCurrentSelectionForm) {
            this.scrollCurrentSelectionForm = scrollCurrentSelectionForm;
        }

        @Override
        public int getDx(final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
            return this.scrollCurrentSelectionForm.getXVerticalForm(index, item, dx, dy);
        }

    };
    
    protected final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    protected MyFontProcessor myFontProcessor = this.updateMyFontProcessor;
    
    private Processor processor = Processor.getInstance();
    private ItemIndexDx preItemIndexDx = ItemIndexDx.getInstance();

    private int dx;
    private int dy;

    private int maxWidth = 0;

    public ScrollCurrentSelectionForm(final String title, final ABCustomItem[] items,
            final ItemPaintableFactory formPaintableFactory, final Rectangle rectangle,
            final FormType formType, final int border, final boolean moveForSmallScreen,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) 
        throws Exception
    {
        super(title, items, formPaintableFactory, rectangle, formType, border,
                backgroundBasicColor, foregroundBasicColor);

        this.moveForSmallScreen = moveForSmallScreen;
    
        this.init(rectangle, formType);
    }
    

    @Override
    public void init(final Rectangle rectangle, final FormType formType)
    throws Exception
    {
        super.init(rectangle, formType);
        
        final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();

        if (formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
        {
            this.processor = new TempHorizontalFormProcessor(this);
            this.preItemIndexDx = new TempHorizontalItemIndexDx(this);
        } else if (formType == formTypeFactory.HORIZONTAL_FORM)
        {
            this.processor = new HorizontalFormProcessor(this);
            this.preItemIndexDx = new HorizontalItemIndexDx(this);
        } else if (formType == formTypeFactory.VERTICAL_CENTER_FORM)
        {
            this.processor = new VerticalFormProcessor(this);
            this.preItemIndexDx = new VerticalItemIndexDx(this);
        } else if (formType == formTypeFactory.NULL_FORM_TYPE) {
        } else
        {
            this.logUtil.putF(formTypeFactory.UNK, this, this.commonStrings.INIT);
        }
        
        this.myFontProcessor = this.updateMyFontProcessor;
    }
    
    @Override
    public void updateMeasurement(final Graphics graphics) {
        
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    private void processTempHorizontalForm() {
        this.dx = this.x - 30 + (this.rectangle.getWidth() >> 1);
        this.dy = this.y;
    }

    private void processHorizontalForm() {
        final int size = this.size();

        int totalWidth = 0;

        ABCustomItem item;
        for (int index = 0; index < size; index++) {
            item = this.get(index);

            totalWidth += item.getMinimumWidth() + this.border;
        }

        this.dx = this.x + (this.rectangle.getWidth() >> 1) - (totalWidth >> 1);

        /*
            int maxHeight = 0;

            for (int index = 0; index < size; index++)
            {
                FormItemInterface item = (FormItemInterface) this.get(index);

                if(item.getMinimumHeight() > maxHeight)
                {
                    maxHeight = item.getMinimumHeight();
                }
            }
         */
        this.dy = this.y + (this.rectangle.getHeight() >> 1);

        // Special handling for small screens to keep menu out of title
        // animation
        if (this.moveForSmallScreen) {
            int maxTitleHeight = 175;
            if (this.dy < maxTitleHeight) {
                this.dy = maxTitleHeight;
            }
        }
    }
    
    private void processVerticalForm() {
        int totalHeight = 0;
        final int size = this.size();
        ABCustomItem item2;
        for (int index = 0; index < size; index++) {
            item2 = this.get(index);
            if (this.maxWidth < item2.getMinimumWidth()) {
                this.maxWidth = item2.getMinimumWidth();
            }

            totalHeight += item2.getMinimumHeight() + this.border;
        }

        this.dx = ((this.rectangle.getWidth() - this.maxWidth) / 2);
        // dx = this.rectangle.getWidth() - maxWidth;

        if (this.size() > 0) {
            // FormItemInterface item = (FormItemInterface) this.get(0);
            // dy = y - 30 + ((this.rectangle.getHeight() -
            // item.getMinimumHeight()) / 2);
            this.dy = this.y + ((this.rectangle.getHeight() - totalHeight) / 2);

            // Special handling for small screens to keep menu out of title
            // animation
            if (this.moveForSmallScreen) {
                int maxTitleHeight = 175;
                if (this.dy < maxTitleHeight) {
                    this.dy = maxTitleHeight;
                }
            }
        } else {
            // dy = y - 30 + ((this.rectangle.getHeight()) >> 1);
            this.dy = this.y;
        }
    }

    private int getXTempHorizontalForm(final int index, final ABCustomItem item, int dx, final int dy) {
        return this.getDiffX(item);
    }

    private int getXHorizontalForm(final int index, final ABCustomItem item, int dx, final int dy) {
        //this.halfBorder;
        return 0;
    }

    private int getXVerticalForm(final int index, final ABCustomItem item, int dx, final int dy) {
        return this.getDiffX(item) + this.halfBorder;
    }
    
    @Override
    protected int getDiffX(ABCustomItem item)
    {
        return ((this.maxWidth - item.getMinimumWidth()) >> 1);
    }

    /**
     * @return the dx
     */
    @Override
    public int getDx()
    {
        return this.dx;
    }

    /**
     * @return the dy
     */
    @Override
    public int getDy()
    {
        return this.dy;
    }
        
    @Override
    public void paint(final Graphics graphics)
    {
        try
        {
            this.processor.process();
            
            int delta = 0;
            int deltaX = this.getDx();
            int deltaY = this.getDy();
            final int size = this.size();
            
            final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();

            ABCustomItem item;
            for (int index = 0; index < size; index++)
            {
                item = this.get(index);

                int diffX = this.preItemIndexDx.getDx(index, item, this.dx, this.dy);

                if (index == this.getSelectedIndex())
                {
                    delta = this.paintItem(graphics, index, item, deltaX + diffX, deltaY);
                    this.paintable.paint(graphics, index, deltaX + diffX, deltaY);
                }
                else
                {
                    delta = this.paintUnselectedItem(graphics, index, item, deltaX + diffX, deltaY);
                    this.paintable.paint(graphics, index, deltaX + diffX, deltaY);
                }

                if (this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
                {
                }
                else
                if (this.formType == formTypeFactory.HORIZONTAL_FORM)
                {
                    deltaX = delta;
                }
                else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
                {
                    deltaY = delta;
                }
                else
                {
                    throw new Exception(formTypeFactory.UNK);
                }
                
                //graphics.drawRect(x, y - halfBorder, this.rectangle.getMaxX() + border, this.rectangle.getMaxY() + border);
            }
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.canvasStrings.PAINT, e);
        }
    }

}
