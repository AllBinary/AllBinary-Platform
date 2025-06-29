/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.media.audio;

import javax.microedition.media.Player;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class PlayerStateUtil {
    
    protected static final PlayerStateUtil instance = new PlayerStateUtil();

    /**
     * @return the instance
     */
    public static PlayerStateUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public final String UNREALIZED = "UNREALIZED";

    public final String REALIZED = "REALIZED";

    public final String PREFETCHED = "PREFETCHED";

    public final String STARTED = "STARTED";

    public final String CLOSED = "CLOSED";
    
    public String convert(final int state) {
        
        if (state == Player.UNREALIZED) {
            return this.UNREALIZED;
        } else if (state == Player.REALIZED) {
            return this.REALIZED;
        } else if (state == Player.PREFETCHED) {
            return this.PREFETCHED;
        } else if (state == Player.STARTED) {
            return this.STARTED;
        } else if (state == Player.CLOSED) {
            return this.CLOSED;
        }
        
        return commonStrings.UNKNOWN;
    }
    
}
