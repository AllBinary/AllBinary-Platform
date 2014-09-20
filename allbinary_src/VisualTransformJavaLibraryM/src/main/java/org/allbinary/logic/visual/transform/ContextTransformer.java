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
package org.allbinary.logic.visual.transform;

import org.allbinary.data.tree.dom.BasicUriResolver;
import org.allbinary.data.tree.dom.ContextUriResolver;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import javax.xml.transform.URIResolver;

public class ContextTransformer extends BasicTransformer
{
   public ContextTransformer(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      this.setURIResolver(
         (URIResolver) new ContextUriResolver(
            (BasicUriResolver) this.getURIResolver()));
   }
}