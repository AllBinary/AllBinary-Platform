/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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

package org.allbinary.game.layer.capital;

import org.allbinary.game.layer.hud.basic.NumberStringHud;

public class HudCapital extends Capital
{
    private NumberStringHud capitalGraphic;
    
    public HudCapital(NumberStringHud moneyGraphic)
    {
        this.capitalGraphic = moneyGraphic;
    }
    
    @Override
    public void removeMoney(int points)
    {
        super.removeMoney(points);
        this.capitalGraphic.reduce(points);
    }

    @Override
    public int getTotalMoney()
    {
        return this.capitalGraphic.get();
    }

    @Override
    public void addMoney(int points)
    {
        super.addMoney(points);
        this.capitalGraphic.add(points);
    }
}
