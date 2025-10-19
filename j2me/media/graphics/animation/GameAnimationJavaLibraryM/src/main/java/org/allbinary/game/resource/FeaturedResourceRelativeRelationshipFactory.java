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
package org.allbinary.game.resource;

import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.RelativeRelationship;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.relationship.RelativeLayerRelationship;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class FeaturedResourceRelativeRelationshipFactory extends FeaturedResourceFactory
{

   private static FeaturedResourceRelativeRelationshipFactory INSTANCE =
      new FeaturedResourceRelativeRelationshipFactory();

   private FeaturedResourceRelativeRelationshipFactory()
   {
   }

   public static FeaturedResourceRelativeRelationshipFactory getInstance()
   {
      return INSTANCE;
   }

   private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
   
   public BasicArrayList getRelativeRelationshipList(String resource, AllBinaryLayer layer)
      throws Exception
   {
       final BasicArrayList featureReleaseList = this.getList();
      int size = getList().size();
      for (int index = 0; index < size; index++)
      {
         ResourceRelativeRelationshipFactoryInterface featureInterface = (ResourceRelativeRelationshipFactoryInterface) featureReleaseList.objectArray[index];
         if (featureInterface.isFeature())
         {
            BasicArrayList list = featureInterface.getResourceRelativeRelationshipList(resource);

            if (list != basicArrayListUtil.getImmutableInstance())
            {
               return this.duplicate(list, layer);
            }
         }
      }

      throw new Exception(
         new StringMaker().append("Not available for current feature selection or Resource: ").append(resource).toString());
   }

   private BasicArrayList duplicate(BasicArrayList list, AllBinaryLayer layer)
      throws Exception
   {
      BasicArrayList newList = new BasicArrayList();
      
      final int size = list.size();
      for (int index = 0; index < size; index++)
      {
         RelativeRelationship relativeRelationship = (RelativeRelationship) list.objectArray[index];
         newList.add(new RelativeLayerRelationship(
            layer, PointFactory.getInstance().getInstance(relativeRelationship.getX(), relativeRelationship.getY()), BasicArrayListUtil.getInstance().getImmutableInstance()));
      }
      return newList;
   }
}
