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
/*
 *  MicroEmulator
 *  Copyright (C) 2001 Bartek Teodorczyk <barteo@barteo.net>
 *  Copyright (C) 2005 Andres Navarro
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Contributor(s):
 *    Shane Harper
 */

package allbinary.graphics.form.item;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

import org.allbinary.graphics.opengles.OpenGLCapabilities;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.feature.Features;
import allbinary.graphics.color.BasicColor;

//import abcs.logic.communication.log.LogFactory;
//import abcs.logic.communication.log.LogUtil;

public class ChoiceGroupItem extends CustomItem implements ChoiceItemInterface
{
    int choiceType;

    private ChoiceItem items[] = new ChoiceItem[4];

    private int numOfItems = 0;

    private int fitPolicy;

    private int highlightedItemIndex = -1;

    private List popupList;

    public ChoiceGroupItem(String label, int choiceType, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        this(label, choiceType, true, backgroundBasicColor, foregroundBasicColor);
    }

    public ChoiceGroupItem(String label, int choiceType, String[] stringElements,
            Image[] imageElements, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        this(label, choiceType, stringElements, imageElements, true, backgroundBasicColor,
                foregroundBasicColor);
    }

    ChoiceGroupItem(String label, int choiceType, boolean validateChoiceType,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, backgroundBasicColor, foregroundBasicColor);

        if (validateChoiceType)
        {
            if (choiceType != ChoiceItemInterface.POPUP
                    && choiceType != ChoiceItemInterface.MULTIPLE
                    && choiceType != ChoiceItemInterface.EXCLUSIVE)
            {
                throw new IllegalArgumentException("Illegal choice type");
            }
        }
        this.choiceType = choiceType;
        if (choiceType == ChoiceItemInterface.POPUP)
        {
            // POPUP has a hidden List to implement it's
            // behaviour
            popupList = new List(label, ChoiceItemInterface.IMPLICIT);
            popupList.setCommandListener(new ImplicitListener());
        }
    }

    // XXX imageElements is ignored.
    ChoiceGroupItem(String label, int choiceType, String[] stringElements, Image[] imageElements,
            boolean validateChoiceType, BasicColor backgroundBasicColor,
            BasicColor foregroundBasicColor)
    {
        this(label, choiceType, validateChoiceType, backgroundBasicColor, foregroundBasicColor);

        for (int i = 0; i < stringElements.length; i++)
        {
            if (imageElements == null)
            {
                append(stringElements[i], null);
            } else
            {
                append(stringElements[i], imageElements[i]);
            }
        }
    }

    public int append(String stringPart, Image imagePart)
    {
        insert(numOfItems, stringPart, imagePart);

        return (numOfItems - 1);
    }

    public void delete(int itemNum)
    {
        if (itemNum < 0 || itemNum >= numOfItems)
        {
            throw new IndexOutOfBoundsException();
        }

        // Ensure that an item of an EXCLUSIVE list remains selected.
        if ((ChoiceItemInterface.EXCLUSIVE == choiceType || ChoiceItemInterface.POPUP == choiceType)
                && items[itemNum].isSelected())
        {
            if (numOfItems > 1)
            {
                items[itemNum != 0 ? 0 : 1].setSelectedState(true);
            }
        }

        // Delete item.
        if (itemNum != numOfItems - 1)
        {
            System.arraycopy(items, itemNum + 1, items, itemNum, numOfItems - itemNum - 1);
        }
        numOfItems--;
        // clear the slot to allow garbage collection
        items[numOfItems] = null;

        // Ensure highlighted item remains highlighted (if it wasn't just
        // deleted).
        if (highlightedItemIndex > itemNum)
        {
            --highlightedItemIndex;
        }

        // Ensure that an item remains highlighted.
        if (highlightedItemIndex >= numOfItems)
        {
            highlightedItemIndex = numOfItems - 1;
        }

        if (choiceType == ChoiceItemInterface.POPUP)
            popupList.delete(itemNum);
        repaint();
    }

    public void deleteAll()
    {
        // clear the array to allow garbage collection
        for (int i = 0; i < numOfItems; i++)
            items[i] = null;
        numOfItems = 0;
        highlightedItemIndex = -1;
        if (choiceType == ChoiceItemInterface.POPUP)
            popupList.deleteAll();
        repaint();
    }

    public int getFitPolicy()
    {
        return fitPolicy;
    }

    public Font getFont(int itemNum)
    {
        if (itemNum < 0 || itemNum >= numOfItems)
        {
            throw new IndexOutOfBoundsException();
        }
        return items[itemNum].getFont();
    }

    /*
    public Image getImage(int elementNum)
    {
    	if (elementNum < 0 || elementNum >= numOfItems) {
    		throw new IndexOutOfBoundsException();
    	}

      return items[elementNum].getImage();
    }
    */

    /**
     * Queries the state of a ChoiceGroup and returns the state of all elements
     * in the boolean array selectedArray_return. NOTE: this is a result
     * parameter. It must be at least as long as the size of the ChoiceGroup as
     * returned by size(). If the array is longer, the extra elements are set to
     * false.
     * 
     * For ChoiceGroup objects of type MULTIPLE, any number of elements may be
     * selected and set to true in the result array. For ChoiceGroup objects of
     * type EXCLUSIVE, exactly one element will be selected, unless there are
     * zero elements in the ChoiceGroup.
     */
    public int getSelectedFlags(boolean[] selectedArray_return)
    {
        if (selectedArray_return == null)
        {
            throw new NullPointerException();
        }
        if (selectedArray_return.length < numOfItems)
        {
            throw new IllegalArgumentException();
        }

        // set selectedArray_return elements and count number of selected items
        int selectedItemsCount = 0;

        for (int i = 0; i < selectedArray_return.length; ++i)
        {
            selectedArray_return[i] = (i < numOfItems) ? items[i].isSelected() : false;
            if (selectedArray_return[i])
            {
                ++selectedItemsCount;
            }
        }

        return selectedItemsCount;
    }

    /**
     * Returns the index number of an element in the ChoiceGroup that is
     * selected. For ChoiceGroup objects of type EXCLUSIVE there is at most one
     * element selected, so this method is useful for determining the user's
     * choice. Returns -1 if there are no elements in the ChoiceGroup.
     * 
     * For ChoiceGroup objects of type MULTIPLE, this always returns -1 because
     * no single value can in general represent the state of such a ChoiceGroup.
     * To get the complete state of a MULTIPLE Choice, see getSelectedFlags.
     */
    public int getSelectedIndex()
    {
        switch (choiceType)
        {
        case ChoiceItemInterface.EXCLUSIVE:
        case ChoiceItemInterface.POPUP:
            // XXX It'd be nice if the selected item index was stored, so it
            // isn't
            // necessary to search for it.
            for (int i = 0; i < numOfItems; ++i)
            {
                if (items[i].isSelected())
                    return i;
            }
            break;
        case ChoiceItemInterface.IMPLICIT:
            return highlightedItemIndex;
        }
        return -1;
    }

    public String getString(int elementNum)
    {
        if (elementNum < 0 || elementNum >= numOfItems)
        {
            throw new IndexOutOfBoundsException();
        }

        return items[elementNum].getText();
    }

    public void insert(int elementNum, String stringPart, Image imagePart)
    {
        if (elementNum < 0 || elementNum > numOfItems)
        {
            throw new IndexOutOfBoundsException();
        }
        if (stringPart == null)
        {
            throw new NullPointerException();
        }

        if (choiceType == ChoiceItemInterface.POPUP)
        {
            popupList.insert(elementNum, stringPart, imagePart);
        }

        if (numOfItems == items.length /*no space left in item array*/)
        {
            ChoiceItem newItems[] = new ChoiceItem[numOfItems + 4];
            System.arraycopy(items, 0, newItems, 0, numOfItems);
            items = newItems;
        }

        System.arraycopy(items, elementNum, items, elementNum + 1, numOfItems - elementNum);

        items[elementNum] = new ChoiceItem(null, imagePart, stringPart, this
                .getLabelStringComponent().getBackgroundBasicColor(), this
                .getLabelStringComponent().getForegroundBasicColor());

        ++numOfItems;

        if (numOfItems == 1)
        {
            highlightedItemIndex = 0;
            if (ChoiceItemInterface.EXCLUSIVE == choiceType
                    || ChoiceItemInterface.POPUP == choiceType)
            {
                setSelectedIndex(0, true);
            }
        }

        repaint();
    }

    public boolean isSelected(int elementNum)
    {
        if (elementNum < 0 || elementNum >= numOfItems)
        {
            throw new IndexOutOfBoundsException();
        }

        return items[elementNum].isSelected();
    }

    public void set(int elementNum, String stringPart, Image imagePart)
    {
        if (elementNum < 0 || elementNum >= numOfItems)
        {
            throw new IndexOutOfBoundsException();
        }
        if (imagePart != null && imagePart.isMutable())
        {
            throw new IllegalArgumentException();
        }
        if (stringPart == null)
        {
            throw new NullPointerException();
        }

        items[elementNum].setText(stringPart);
        items[elementNum].setImage(imagePart);

        if (choiceType == ChoiceItemInterface.POPUP)
        {
            popupList.set(elementNum, stringPart, imagePart);
        }

        repaint();
    }

    public void setFitPolicy(int policy)
    {
        if (policy != ChoiceItemInterface.TEXT_WRAP_DEFAULT
                && policy != ChoiceItemInterface.TEXT_WRAP_ON
                && policy != ChoiceItemInterface.TEXT_WRAP_OFF)
            throw new IllegalArgumentException("Bad Policy");
        fitPolicy = policy;
        if (choiceType == ChoiceItemInterface.POPUP)
        {
            popupList.setFitPolicy(policy);
        }
    }

    public void setFont(int itemNum, Font font)
    {
        if (itemNum < 0 || itemNum >= numOfItems)
        {
            throw new IndexOutOfBoundsException();
        }
        items[itemNum].setFont(font);
        if (choiceType == ChoiceItemInterface.POPUP)
        {
            popupList.setFont(itemNum, font);
        }
    }

    public void setSelectedFlags(boolean[] selectedArray)
    {
        if (selectedArray == null)
        {
            throw new NullPointerException();
        }
        if (selectedArray.length < numOfItems)
        {
            throw new NullPointerException();
        }

        if (numOfItems == 0)
            return;

        if (choiceType == ChoiceItemInterface.MULTIPLE)
        {
            for (int i = 0; i < numOfItems; i++)
            {
                setSelectedIndex(i, selectedArray[i]);
            }
        } else
        {
            int selectedItem = -1;
            for (int i = 0; i < numOfItems; i++)
            {
                if (selectedArray[i])
                {
                    setSelectedIndex(i, true);
                    selectedItem = i;
                    break;
                }
            }
            if (selectedItem == -1)
            {
                setSelectedIndex(0, true);
            }

            if (choiceType == ChoiceItemInterface.POPUP)
            {
                popupList.setSelectedFlags(selectedArray);
            }
        }

    }

    public void setSelectedIndex(int elementNum, boolean selected)
    {
        if (elementNum < 0 || elementNum >= numOfItems)
        {
            throw new IndexOutOfBoundsException();
        }

        highlightedItemIndex = elementNum;
        if ((choiceType == ChoiceItemInterface.EXCLUSIVE || choiceType == ChoiceItemInterface.POPUP)
                && selected)
        {
            for (int i = 0; i < numOfItems; i++)
            {
                items[i].setSelectedState(elementNum == i);
            }
            if (choiceType == ChoiceItemInterface.POPUP)
            {
                popupList.setSelectedIndex(elementNum, true);
            }
            repaint();
        } else if (choiceType == ChoiceItemInterface.MULTIPLE)
        {
            items[elementNum].setSelectedState(selected);
            repaint();
        } else if (choiceType == ChoiceItemInterface.IMPLICIT)
        {
            if (selected)
            {
                items[elementNum].setSelectedState(selected);
                repaint();
            }
        }
    }

    public int size()
    {
        return numOfItems;
    }

    public boolean isFocusable()
    {
        return true;
    }

    public int getHeight()
    {
        int height = 0;
        if (choiceType == ChoiceItemInterface.POPUP)
        {
            if (highlightedItemIndex != -1)
            {
                height += items[highlightedItemIndex].getHeight();
            }
        } else
        {
            for (int i = 0; i < numOfItems; i++)
            {
                height += items[i].getHeight();
            }
        }

        return super.getHeight() + height;
    }

    /*
     * Get item index from coordinates
     */
    int getItemIndexAt(int x, int y)
    {
        x -= super.getHeight();
        int testY = 0;
        for (int i = 0; i < numOfItems; i++)
        {
            testY += items[i].getHeight();
            if (y < testY)
            {
                return i;
            }
        }

        return -1;
    }

    int getHeightToItem(int itemIndex)
    {
        int height = 0;

        for (int i = 0; i < itemIndex; i++)
        {
            height += items[i].getHeight();
        }

        return height;
    }

    int getItemHeight(int itemIndex)
    {
        return items[itemIndex].getHeight();
    }

    public int paint(Graphics g)
    {
        // super.paintContent(g);

        g.translate(0, super.getHeight());
        int translatedY = 0;

        if (choiceType == ChoiceItemInterface.POPUP)
        {
            int index = getSelectedIndex();
            if (index != -1)
            {
                items[index].invertPaint(hasFocus());
                items[index].paint(g);
            }
        } else
        {
            for (int i = 0; i < numOfItems; i++)
            {
                items[i].invertPaint(i == highlightedItemIndex && hasFocus());
                items[i].paint(g);
                // LogUtil.put(LogFactory.getInstance("Painting: " +
                // items[i].getLabel(), this, "paint"));
                g.translate(0, items[i].getHeight());
                translatedY += items[i].getHeight();
            }
            g.translate(0, -translatedY);
        }

        g.translate(0, -super.getHeight());

        return getHeight();
    }

    public boolean select()
    {
        if (numOfItems == 0)
        {
            return false;
        }

        if (choiceType == ChoiceItemInterface.POPUP)
        {
            // getOwner().currentDisplay.setCurrent(popupList);
        } else
        {
            // XXX What does the following statement do?

            // It is correct, in the case of multiple inverts the selected
            // state, in exclusive selects the highligthed
            // and in implicit it does nothing
            // Andres Navarro
            setSelectedIndex(highlightedItemIndex, !items[highlightedItemIndex].isSelected());
        }

        return true;
    }

    public int traverse(int gameKeyCode, int top, int bottom, boolean action)
    {
        int OUTOFITEM = Integer.MAX_VALUE;
        //int OUTOFITEM = Item.OUTOFITEM;

        if (this.choiceType == ChoiceItemInterface.POPUP)
        {
            // POPUP has a totally different behaviour
            if (gameKeyCode == Canvas.UP)
            {
                if (top > 0)
                {
                    return -top;
                } else
                {
                    return OUTOFITEM;
                }
            } else if (gameKeyCode == Canvas.DOWN)
            {
                if (!action)
                {
                    int height = super.getHeight();
                    int index = getSelectedIndex();
                    if (index != -1)
                    {
                        height += items[index].getHeight();
                    }

                    if (height > bottom)
                    {
                        return height - bottom;
                    } else
                    {
                        repaint();
                    }
                } else
                {
                    return OUTOFITEM;
                }
            }
        } else
        {
            if (gameKeyCode == Canvas.UP)
            {
                if (highlightedItemIndex > 0)
                {
                    if (action)
                    {
                        highlightedItemIndex--;
                    }
                    int height = super.getHeight();
                    for (int i = 0; i < highlightedItemIndex; i++)
                    {
                        height += items[i].getHeight();
                    }
                    if (height < top)
                    {
                        return height - top;
                    } else
                    {
                        repaint();
                    }
                } else
                {
                    if (top > 0)
                    {
                        return -top;
                    } else
                    {
                        return OUTOFITEM;
                    }
                }
            }
            if (gameKeyCode == Canvas.DOWN)
            {
                if ((!action && highlightedItemIndex < numOfItems)
                        || (action && highlightedItemIndex < (numOfItems - 1)))
                {
                    if (action)
                    {
                        highlightedItemIndex++;
                    }
                    int height = super.getHeight();
                    for (int i = 0; i <= highlightedItemIndex; i++)
                    {
                        height += items[i].getHeight();
                    }
                    if (height > bottom)
                    {
                        return height - bottom;
                    } else
                    {
                        repaint();
                    }
                } else
                {
                    return OUTOFITEM;
                }
            }
        }

        return 0;
    }

    /*
    void repaint() {
      // the popup list should be repainted
      // in the case it is being shown
      if (choiceType == Choice.POPUP)
    	  popupList.repaint();
      super.repaint();
    }*/

    class ChoiceItem extends ImageStringItem
    {
        private boolean selected;
        private Font font;
        Image box;

        ChoiceItem(String label, Image image, String text, BasicColor backgroundBasicColor,
                BasicColor foregroundBasicColor)
        {
            super(label, image, text, backgroundBasicColor, foregroundBasicColor);
            setSelectedState(false);
            font = Font.getDefaultFont();
        }

        Font getFont()
        {
            return font;
        }

        public void setImage(Image img)
        {
            super.setImage(img);

            int width = 0;
            if (box != null)
                width += box.getWidth();
            if (this.getImage() != null)
                width += img.getWidth();
            if (width > 0)
                width += 2;
            this.getStringComponent().setWidthDecreaser(width);
        }

        public int getHeight()
        {
            int height = 0;
            if (box != null)
            {
                height = box.getHeight();
            }
                
            if (this.getImage() != null && this.getImage().getHeight() > height)
            {
                height = this.getImage().getHeight();
            }
            
            if (this.getStringComponent().getHeight() > height)
            {
                height = this.getStringComponent().getHeight();
            }
            return height;
        }

        public int paint(Graphics g)
        {
            // OpenGL ES Hack
            OpenGLCapabilities openGLCapabilities = OpenGLCapabilities.getInstance();

            if (this.getStringComponent() == null)
            {
                return 0;
            }

            int widthAddition = 0;
            if (box != null)
            {

                if (Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL)
                        && openGLCapabilities.isGlExtensionDrawTexture())
                {
                    g.drawImage(box, g.getTranslateX(), g.getTranslateY(), Graphics.LEFT | Graphics.TOP);
                } else
                {
                    g.drawImage(box, 0, 0, Graphics.LEFT | Graphics.TOP);
                }

                if (this.getImage() != null)
                {
                    widthAddition = box.getWidth();
                    g.translate(box.getWidth(), 0);
                } else
                {
                    widthAddition = box.getWidth() + 2;
                    g.translate(box.getWidth() + 2, 0);
                }
            }

            Image image = this.getImage();
            
            if (image != null)
            {

                widthAddition += image.getWidth() + 2;

                // OpenGL ES Hack
                if (Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL)
                        && openGLCapabilities.isGlExtensionDrawTexture())
                {
                    g.drawImage(image, g.getTranslateX(), g.getTranslateY(), Graphics.LEFT | Graphics.TOP);
                } 
                else
                {
                    g.drawImage(image, 0, 0, Graphics.LEFT | Graphics.TOP);
                }

                g.translate(image.getWidth() + 2, 0);
            }

            int y = this.getStringComponent().paint(g);

            if (widthAddition != 0)
            {
                g.translate(-widthAddition, 0);
            }

            return y;
        }

        boolean isSelected()
        {
            return selected;
        }

        void setFont(Font f)
        {
            if (f == null)
            {
                throw new NullPointerException();
            }
                
            // only allow fonts of the same height
            // for now (to simplify the layout)
            if (f.getHeight() == font.getHeight())
                font = f;
        }

        void setSelectedState(boolean state)
        {
            selected = state;

            Image[] imageArray = ChoiceGroupImageFactory.getInstance().getImageArray();
            if (choiceType != ChoiceItemInterface.IMPLICIT
                    && choiceType != ChoiceItemInterface.POPUP)
            {
                box = (ChoiceItemInterface.EXCLUSIVE == choiceType ? (state ? imageArray[3]
                        : imageArray[2]) : (state ? imageArray[1] : imageArray[0]));
            }
        }
    }

    class ImplicitListener implements CommandListener
    {
        public void commandAction(Command c, Displayable d)
        {
            List list = (List) d;
            setSelectedIndex(list.getSelectedIndex(), true);
            try
            {
                // getOwner().currentDisplay.setCurrent(getOwner());
                repaint();
            } catch (NullPointerException n)
            {
                // this happens if the item becomes an orphan
                // (ie not owned by a Form, shouldn't happen
                // if correct programming practices are used!!)
            }
        }
    }

    public void setFocus(boolean state)
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START,
        // this, "setFocus"));

        if (state)
        {
            // TWB - Toggle selected choice hack
            LogUtil.put(LogFactory.getInstance("Resetting Focus", this, "setFocus"));

            int index = 0;
            this.setSelectedIndex(index, !this.isSelected(index));
        }

        super.setFocus(state);
    }
}