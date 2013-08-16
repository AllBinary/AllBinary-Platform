package allbinary.game.layer.special;

import allbinary.game.input.event.GameKeyEvent;
import allbinary.layer.AllBinaryLayerManager;


public interface SpecialGameInputInterface {

    void up() throws Exception;
    
    void down() throws Exception;

    void right() throws Exception;

    void left() throws Exception;

    void strafeLeft() throws Exception;

    void strafeRight() throws Exception;
    
    void fire(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    void special1(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    void special2(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    void special3(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    void special4(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;

    void special5(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent) throws Exception;
    
}
