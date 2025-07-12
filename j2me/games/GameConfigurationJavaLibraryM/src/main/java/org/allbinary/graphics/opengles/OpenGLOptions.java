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
package org.allbinary.graphics.opengles;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureChoiceGroups;
import org.allbinary.util.BasicArrayList;

public class OpenGLOptions
{
    public void init()
    {
        OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();
        
        if (Features.getInstance().isFeature(openGLFeatureFactory.OPENGL_OPTIONS))
        {
            BasicArrayList openGLMultipleList = new BasicArrayList();

            openGLMultipleList.add(openGLFeatureFactory.OPENGL);

            GameFeatureChoiceGroups.getMultipleInstance().add(
                    "OpenGL (Complete Restart)", openGLMultipleList);

            BasicArrayList openGLVersionSelectorMultipleList = new BasicArrayList();

            openGLVersionSelectorMultipleList.add(openGLFeatureFactory.OPENGL_AUTO_SELECT);
            openGLVersionSelectorMultipleList.add(openGLFeatureFactory.OPENGL_MINIMUM);

            GameFeatureChoiceGroups.getExclusiveInstance().add(
                    "OpenGL Version Selector",
                    openGLVersionSelectorMultipleList);

            /*
             * BasicArrayList openGLTypeMultipleList = new BasicArrayList();
             * 
             * openGLTypeMultipleList.add(OpenGLFeature.OPENGL_AS_GAME_THREAD);
             * openGLMultipleList
             * .add(OpenGLFeature.OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS);
             * 
             * GameFeatureChoiceGroups.getExclusiveInstance().add( "Type",
             * openGLTypeMultipleList);
             */

            BasicArrayList openGLImageColorMultipleList = new BasicArrayList();

            openGLImageColorMultipleList.add(openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444);
            // openGLImageColorMultipleList.add(OpenGLFeature.IMAGE_COLOR_DEPTH_565);
            openGLImageColorMultipleList.add(openGLFeatureFactory.IMAGE_COLOR_DEPTH_8888);

            GameFeatureChoiceGroups.getExclusiveInstance().add(
                    "OpenGL Image Color RGBA", openGLImageColorMultipleList);

            /*
             * BasicArrayList openGLColorMultipleList = new BasicArrayList();
             * 
             * 
             * openGLColorMultipleList.add(OpenGLFeature.OPENGL_COLOR_DEPTH_4444)
             * ;
             * //openGLColorMultipleList.add(OpenGLFeature.OPENGL_COLOR_DEPTH_565
             * );
             * openGLColorMultipleList.add(OpenGLFeature.OPENGL_COLOR_DEPTH_8888
             * );
             * 
             * GameFeatureChoiceGroups.getExclusiveInstance().add( "Image RGBA",
             * openGLColorMultipleList);
             */
        }
    }
}
