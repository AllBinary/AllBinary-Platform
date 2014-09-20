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
package org.allbinary.logic.visual.transform.info.objectConfig;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;

public class OverrideStoreTransformInfoObjectConfig extends GenericStoreTransformInfoObjectConfig
{

   public OverrideStoreTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      //this.setDocument(this.generate(this.getDocument()));
   }

   public OverrideStoreTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface, Document document) throws Exception
   {
      super(transformInfoInterface, document);
      this.setDocument(this.generate(this.toXmlDoc()));
   }

   public OverrideStoreTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface, String name, String type) throws Exception
   {
      super(transformInfoInterface, name, type);
      this.setDocument(this.generate(this.toXmlDoc()));
   }

   protected Document generate(Document objectConfigDocument) throws Exception
   {
      Document newObjectConfigDocument = super.generate(objectConfigDocument);
      return newObjectConfigDocument;
   }
}