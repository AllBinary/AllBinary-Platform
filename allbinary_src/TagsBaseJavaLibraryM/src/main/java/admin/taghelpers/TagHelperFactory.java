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
package admin.taghelpers;

import java.util.HashMap;
import javax.servlet.jsp.PageContext;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class TagHelperFactory implements TagHelperFactoryInterface {
 
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public Object getInstance(
      final HashMap hashMap, final PageContext pageContext) 
      throws Exception, LicensingException {
        throw new RuntimeException();
    }

}
