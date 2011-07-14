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
package allbinary.graphics.pipeline;

import org.allbinary.util.BasicArrayList;

import allbinary.game.rand.MyRandomFactory;

public class RandomTranslation
{
    public static BasicArrayList getInstance(BasicArrayList vector, int howMuch)
        throws Exception
    {
        final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();

        int x = -myRandomFactory.getNextInt(howMuch);
        int y = myRandomFactory.getNextInt(howMuch);

        BasicGraphicsPipeline graphicsPipe = new BasicGraphicsPipeline(vector);

        graphicsPipe.createMatrix();
        graphicsPipe.translate(x, y);

        return graphicsPipe.getMatrix();
    }
}
