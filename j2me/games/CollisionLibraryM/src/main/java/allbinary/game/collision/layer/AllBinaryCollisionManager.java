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
package allbinary.game.collision.layer;

import allbinary.game.collision.CollidableBaseBehavior;
import allbinary.game.collision.CollidableInterfaceCompositeInterface;
import allbinary.layer.LayerInterfaceManager;

public class AllBinaryCollisionManager
{
   private static AllBinaryCollisionManager allBinaryCollisionManager =
      new AllBinaryCollisionManager();
   
   private AllBinaryCollisionManager()
   {
   }
   
   public static AllBinaryCollisionManager getInstance()
   {
      return AllBinaryCollisionManager.allBinaryCollisionManager;
   }
   
   /* //If I need multiple LayerManagers
   public void process(AllBinaryLayerManager myLayerManagers[])//,LayerManager layerManagers[])
   {
      for(int myLayerManagerIndex = 0; myLayerManagerIndex < myLayerManagers.length; myLayerManagerIndex++)
      {
         AllBinaryLayerManager myLayerManager = myLayerManagers[myLayerManagerIndex];
         this.process(myLayerManager);
      }
   }
    */
   
   /*
   public void process(AllBinaryLayerManager myLayerManager)
           throws Exception
   {
      int size = myLayerManager.getSize();
      for(int index = 0; index < size; index++)
      {
         LayerInterface myLayerInterface = myLayerManager.getLayerAt(index);
         if(myLayerInterface instance of CollidableInterface)
         {
            CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface = 
               (CollidableInterfaceCompositeInterface) myLayerInterface;
            if(collidableInterfaceCompositeInterface.isCollidable())
            {
               this.process(myLayerManager, collidableInterface, index);
            }
         }
      }
   }
   */
   
   public void process(
		   LayerInterfaceManager layerInterfaceManager,
		   CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface,
      int startIndex)
      throws Exception
   {
       int size = layerInterfaceManager.getSize();

       for (int index = startIndex + 1; index < size; index++)
       {
           CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface2 = 
                   (CollidableInterfaceCompositeInterface) layerInterfaceManager.getLayerAt(index);

           // if(collidableInterfaceCompositeInterface !=
           // collidableInterfaceCompositeInterface2)
           // {
           this.collide(layerInterfaceManager,
                   collidableInterfaceCompositeInterface,
                   collidableInterfaceCompositeInterface2);
           // }
       }
   }

   private void collide(
	  LayerInterfaceManager layerInterfaceManager,
	  CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface,
	  CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface2)
      throws Exception
   {
       CollidableBaseBehavior collidableBase2 = collidableInterfaceCompositeInterface2.getCollidableInferface();
       CollidableBaseBehavior collidableBase = collidableInterfaceCompositeInterface.getCollidableInferface();
       
      if(collidableBase2.isCollidable())
      {
         if(collidableBase.isCollision(collidableInterfaceCompositeInterface2))
         {
             collidableBase.collide(collidableInterfaceCompositeInterface2);
             collidableBase2.collide(collidableInterfaceCompositeInterface);            
         }
      }
   }
}