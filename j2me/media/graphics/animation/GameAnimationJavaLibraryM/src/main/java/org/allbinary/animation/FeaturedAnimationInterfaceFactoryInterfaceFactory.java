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
package org.allbinary.animation;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.animation.resource.FeatureResourceAnimationInterfaceFactoryInterface;
import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.game.resource.FeaturedResourceFactory;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class FeaturedAnimationInterfaceFactoryInterfaceFactory
        extends FeaturedResourceFactory
{

    private static FeaturedAnimationInterfaceFactoryInterfaceFactory INSTANCE
            = new FeaturedAnimationInterfaceFactoryInterfaceFactory();

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
            FeatureResourceAnimationInterfaceFactoryInterface featureInterface
                    = (FeatureResourceAnimationInterfaceFactoryInterface) getList().objectArray[index];

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
         * selection or Resource: ").append(resource, "FeaturedAnimationInterfaceFactoryInterfaceFactory",
         * "getAnimationInterfaceInstance"));
         */
        throw new Exception(
                new StringMaker().append("No rectangle available for current feature selection or Resource: ").append(resource).toString());
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

        final BasicArrayList resourceTypeAvailableList = new BasicArrayList();
        int size = getList().size();
        for (int index = 0; index < size; index++)
        {
            FeatureResourceAnimationInterfaceFactoryInterface featureInterface
                    = (FeatureResourceAnimationInterfaceFactoryInterface) getList().objectArray[index];
            if (featureInterface.isFeature())
            {
                resourceTypeAvailableList.add(featureInterface);

                BasicAnimationInterfaceFactoryInterface animationInterfaceFactoryInterface
                        = featureInterface.getBasicAnimationInterfaceFactoryInstance(resource);

                if (animationInterfaceFactoryInterface != null)
                {
                    return animationInterfaceFactoryInterface;
                }
            }
        }

        /*
         * LogUtil.put(LogFactory.getInstance( "No animation available for current feature
         * selection or Resource: ").append(resource, "FeaturedAnimationInterfaceFactoryInterfaceFactory",
         * "getAnimationInterfaceInstance"));
         */
        if (resourceTypeAvailableList.size() > 0)
        {
            final String NO_ANIMATION_AVAILABLE_FROM = "No animation available from: ";
            final String FOR_FACTORIES = " factories: ";
            final String FOR_RESOURCE = " for Resource: ";
            final String HAS_KEY = " has: ";
            final String RESOURCES_LABEL = " resources ";

            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(NO_ANIMATION_AVAILABLE_FROM);
            stringBuffer.append(resourceTypeAvailableList.size());
            stringBuffer.append(CommonSeps.getInstance().FORWARD_SLASH);
            stringBuffer.append(size);
            stringBuffer.append(FOR_FACTORIES);
            
            for(int index = 0; index < resourceTypeAvailableList.size(); index++)
            {
                stringBuffer.append(resourceTypeAvailableList.get(index).toString());
                stringBuffer.append(CommonSeps.getInstance().COMMA_SEP);
            }
             
            stringBuffer.append(FOR_RESOURCE);
            stringBuffer.append(resource);

            for (int index = 0; index < size; index++)
            {
                stringBuffer.append(CommonSeps.getInstance().NEW_LINE);

                FeatureResourceAnimationInterfaceFactoryInterface featureInterface = (FeatureResourceAnimationInterfaceFactoryInterface) getList().objectArray[index];
                if (featureInterface.isFeature())
                {
                    stringBuffer.append(StringUtil.getInstance().toString(featureInterface));
                    stringBuffer.append(HAS_KEY);
                    stringBuffer.append(featureInterface.getHashtable().size());
                    stringBuffer.append(RESOURCES_LABEL);
                    //To large
                    //stringBuffer.append(" resources: ");
                    //stringBuffer.append(featureInterface.getHashtable().toString());
                }
            }

            throw new Exception(stringBuffer.toString());
        } else
        {
            final StringMaker stringBuffer = new StringMaker();
            for (int index = 0; index < size; index++)
            {
                FeatureResourceAnimationInterfaceFactoryInterface featureInterface
                        = (FeatureResourceAnimationInterfaceFactoryInterface) getList().objectArray[index];
                stringBuffer.append(featureInterface.toString());
                stringBuffer.append(CommonSeps.getInstance().SPACE);
            }

            final String result = stringBuffer.toString();
            stringBuffer.delete(0, stringBuffer.length());
            throw new Exception(
                    stringBuffer.append("No feature resource type available for Resource: ").append(resource).append(" Resource Factories Available: ").append(result).toString());
        }
    }
}
