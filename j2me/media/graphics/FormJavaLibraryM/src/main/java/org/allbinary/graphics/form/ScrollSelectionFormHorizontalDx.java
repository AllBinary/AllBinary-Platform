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

import org.allbinary.graphics.form.item.ABCustomItem;

public class ScrollSelectionFormHorizontalDx extends ItemIndexDx {

    private final ScrollSelectionForm scrollSelectionForm;

    public ScrollSelectionFormHorizontalDx(final ScrollSelectionForm scrollSelectionForm) {
        this.scrollSelectionForm = scrollSelectionForm;
    }

    @Override
    public int getDx(final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
        return this.scrollSelectionForm.getSelectedIndexForPointHorizontalDx(index, item, dx, dy);
    }

}
