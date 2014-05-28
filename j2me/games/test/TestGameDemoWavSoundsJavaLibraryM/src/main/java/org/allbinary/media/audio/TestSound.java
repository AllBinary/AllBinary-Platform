/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
 *
 */
package org.allbinary.media.audio;

import allbinary.media.audio.CompositeSound;
import allbinary.media.audio.Sound;

public class TestSound extends CompositeSound
{
   private static Sound soundInterface = new TestSound();
   
   private TestSound()
   {
       super("resource:/wav/test.wav");
   }
   
   public static Sound getInstance()
   {
      return soundInterface;
   }   
}
