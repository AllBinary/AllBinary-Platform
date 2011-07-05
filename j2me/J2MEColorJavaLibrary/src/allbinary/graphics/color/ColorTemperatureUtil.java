/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/19/02
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.graphics.color;

public class ColorTemperatureUtil
{
    private static final ColorTemperatureUtil instance = new ColorTemperatureUtil();

    public static ColorTemperatureUtil getInstance()
    {
        return instance;
    }

    private final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
    
    public BasicColor getBasicColor(int thirdTemp)
    {
        BasicColor basicColor = basicColorFactory.RED;

        if (thirdTemp > 3700)
        {
            basicColor = basicColorFactory.PURPLE;
        } else if (thirdTemp > 3200)
        {
            basicColor = basicColorFactory.BLUE;
        } else if (thirdTemp > 2700)
        {
            basicColor = basicColorFactory.PUCE;
        } else if (thirdTemp > 2200)
        {
            basicColor = basicColorFactory.WHITE;
        } else if (thirdTemp > 1700)
        {
            basicColor = basicColorFactory.GREEN;
        } else if (thirdTemp > 1200)
        {
            basicColor = basicColorFactory.YELLOW;
        } else if (thirdTemp > 700)
        {
            basicColor = basicColorFactory.ORANGE;
        }

        return basicColor;
    }

}
