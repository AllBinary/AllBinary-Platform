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
package org.allbinary.logic.visual.transform.info;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.http.request.session.WeblisketSessionInterface;

public interface TransformInfoHttpInterface extends TransformInfoInterface
{
   public WeblisketSessionInterface getWeblisketSession();
   public PageContext getPageContext();
   public HashMap getPropertiesHashMap();
}
