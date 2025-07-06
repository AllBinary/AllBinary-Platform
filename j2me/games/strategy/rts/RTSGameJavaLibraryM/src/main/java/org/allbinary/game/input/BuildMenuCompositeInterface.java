/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

package org.allbinary.game.input;

import org.allbinary.graphics.form.ScrollSelectionForm;

/**
 *
 * @author user
 */
public interface BuildMenuCompositeInterface {

    ScrollSelectionForm getCurrentScrollSelectionForm();
    void setCurrentScrollSelectionForm(ScrollSelectionForm currentScrollSelectionForm);
    ScrollSelectionForm getBuildingScrollSelectionForm();
    //void setBuildingScrollSelectionForm(ScrollSelectionForm buildingScrollSelectionForm);
}
