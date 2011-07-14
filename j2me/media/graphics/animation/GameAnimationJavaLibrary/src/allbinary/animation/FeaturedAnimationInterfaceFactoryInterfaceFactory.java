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
package allbinary.animation;

import abcs.logic.basic.string.CommonSeps;
import allbinary.animation.resource.FeatureResourceAnimationInterfaceFactoryInterface;
import allbinary.game.configuration.GameConfigurationCentral;
import allbinary.game.resource.FeaturedResourceFactory;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;

public class FeaturedAnimationInterfaceFactoryInterfaceFactory 
   extends FeaturedResourceFactory
{
   private static FeaturedAnimationInterfaceFactoryInterfaceFactory INSTANCE = 
      new FeaturedAnimationInterfaceFactoryInterfaceFactory();
   
   private FeaturedAnimationInterfaceFactoryInterfaceFactory()
   {
   }
   
   public static FeaturedAnimationInterfaceFactoryInterfaceFactory getInstance()
   {
      return INSTANCE;
   }
   
   public Rectangle getRectangle(String resource)
      throws Exception
   {
      return getRectangle(resource, 0, 0);
   }
   
   public Rectangle getRectangle(String resource, int x, int y)
      throws Exception
   {
      int scale = GameConfigurationCentral.getInstance().SCALE.getValue().intValue();
      int size = getList().size();
      for (int index = 0; index < size; index++)
      {
         FeatureResourceAnimationInterfaceFactoryInterface featureInterface =
             (FeatureResourceAnimationInterfaceFactoryInterface) getList().get(index);

         if (featureInterface.isFeature())
         {
            Rectangle rectangle = featureInterface.getRectangle(resource);

            if (rectangle != null)
            {
                return new Rectangle(PointFactory.getInstance().getInstance(x, y),
                        ((rectangle.getWidth() * scale) >> 1), ((rectangle.getHeight() * scale) >> 1));
            }
         }
      }

      /*
       * LogUtil.put(LogFactory.getInstance( "No animation available for current feature
       * selection or Resource: " + resource, "FeaturedAnimationInterfaceFactoryInterfaceFactory",
       * "getAnimationInterfaceInstance"));
       */

      throw new Exception(
         "No rectangle available for current feature selection or Resource: " + resource);
   }

   public ProceduralAnimationInterfaceFactoryInterface getProcedural(
      String resource) throws Exception
   {

      return (ProceduralAnimationInterfaceFactoryInterface) getBasicAnimationInterfaceFactoryInstance(resource);

   }

   public AnimationInterfaceFactoryInterface get(String resource)
      throws Exception
   {
      return (AnimationInterfaceFactoryInterface) getBasicAnimationInterfaceFactoryInstance(resource);
   }

   private BasicAnimationInterfaceFactoryInterface getBasicAnimationInterfaceFactoryInstance(
      String resource) throws Exception
   {

      boolean resourceTypeAvailable = false;
      int size = getList().size();
      for (int index = 0; index < size; index++)
      {
         FeatureResourceAnimationInterfaceFactoryInterface featureInterface = 
             (FeatureResourceAnimationInterfaceFactoryInterface) getList().get(index);
         if (featureInterface.isFeature())
         {
            resourceTypeAvailable = true;

            BasicAnimationInterfaceFactoryInterface animationInterfaceFactoryInterface =  
                featureInterface.getBasicAnimationInterfaceFactoryInstance(resource);

            if (animationInterfaceFactoryInterface != null)
            {
               return animationInterfaceFactoryInterface;
            }
         }
      }

      /*
       * LogUtil.put(LogFactory.getInstance( "No animation available for current feature
       * selection or Resource: " + resource, "FeaturedAnimationInterfaceFactoryInterfaceFactory",
       * "getAnimationInterfaceInstance"));
       */

      if (resourceTypeAvailable)
      {
          StringBuilder stringBuffer = new StringBuilder();
          
          stringBuffer.append("No animation available from: ");
          stringBuffer.append(size);
          stringBuffer.append(" factories for Resource: ");
          stringBuffer.append(resource);
                  
          for (int index = 0; index < size; index++)
          {
              stringBuffer.append(CommonSeps.getInstance().NEW_LINE);
              
             FeatureResourceAnimationInterfaceFactoryInterface featureInterface = (FeatureResourceAnimationInterfaceFactoryInterface) getList().get(index);
             if (featureInterface.isFeature())
             {
                stringBuffer.append(featureInterface);
                stringBuffer.append(" has: ");
                stringBuffer.append(featureInterface.getHashtable().size());
                stringBuffer.append(" resources ");
                //To large
                //stringBuffer.append(" resources: ");
                //stringBuffer.append(featureInterface.getHashtable().toString());
             }
          }
          
         throw new Exception(stringBuffer.toString());
      }
      else
      {
         StringBuilder stringBuffer = new StringBuilder();
         for (int index = 0; index < size; index++)
         {
            FeatureResourceAnimationInterfaceFactoryInterface featureInterface = 
                (FeatureResourceAnimationInterfaceFactoryInterface) getList().get(index);
            stringBuffer.append(featureInterface.toString());
            stringBuffer.append(CommonSeps.getInstance().SPACE);
         }
                     
         throw new Exception(
            "No feature resource type available for Resource: " + resource + " Resource Factories Available: " + stringBuffer.toString());
      }
   }
}
