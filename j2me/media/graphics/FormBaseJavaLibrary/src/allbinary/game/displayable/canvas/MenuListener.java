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
package allbinary.game.displayable.canvas;

import allbinary.game.input.GameKeyEventSourceInterface;

public interface MenuListener 
extends GameKeyEventSourceInterface
{
    void open(); //throws Exception;
    void close() throws Exception;
}
