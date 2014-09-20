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
package org.allbinary.logic.visual.transform.data;

public class TransformDocumentFactory
{
   private TransformDocumentFactory()
   {
   }
   
   public static TransformDocumentInterface getInstance() 
      throws Exception
   {
      return (TransformDocumentInterface) new TransformDocument();
   }
}
