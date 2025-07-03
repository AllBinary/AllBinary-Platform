/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.logic.control.validate;

import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author User
 */
public class Validation implements ValidationInterface {

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    public Boolean isValid() throws Exception {
        throw new RuntimeException();
    }

    public Node toValidationInfoNode(Document document) throws Exception {
        throw new RuntimeException();
    }

    public Document toValidationInfoDoc() throws Exception {
        throw new RuntimeException();
    }

    public String validationInfo() throws Exception {
        throw new RuntimeException();
    }

}
