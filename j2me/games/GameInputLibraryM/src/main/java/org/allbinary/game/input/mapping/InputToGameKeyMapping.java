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
package org.allbinary.game.input.mapping;

import javax.microedition.lcdui.Canvas;

import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;

public class InputToGameKeyMapping extends InputMapping
{
    public static final InputToGameKeyMapping INPUT_TO_GAME_KEY_MAPPING = new InputToGameKeyMapping();

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
    
    private final GameKey[] mappedGameKeys = {
            this.gameKeyFactory.UP, 
            this.gameKeyFactory.DOWN, 
            this.gameKeyFactory.LEFT, 
            this.gameKeyFactory.RIGHT,
        //gameKeyFactory.FIRE,
        //gameKeyFactory.GAME_A, gameKeyFactory.GAME_B, gameKeyFactory.GAME_C, gameKeyFactory.GAME_D
    };

    protected GameKey[] negativePlatformToGameKeyMapping = new GameKey[0]; //6
    private final GameKey[] platformToGameKeyMapping = new GameKey[InputFactory.getInstance().MAX];

    public InputToGameKeyMapping()
    {
        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        this.clear();
    }
        
    private void clear()
    {
        for (int index = platformToGameKeyMapping.length - 1; index >= 0; index--)
        {
            platformToGameKeyMapping[index] = gameKeyFactory.NONE;
        }
    }

    @Override    
    protected void removeAll()
    {
        super.removeAll();
        this.clear();
    }
    
    @Override
    public void add(final InputToGameKeyMapping inputToGameKeyMapping)
    {
        //PreLogUtil.put(commonStrings.START, this, "InputToGameKeyMapping::add(InputToGameKeyMapping)");
        
        for(int index = inputToGameKeyMapping.getMaxMappable() - 1; index >= 0; index--)
        {
            final Input input = inputToGameKeyMapping.getInstance(index);

            if(input != gameKeyFactory.NONE)
            {
                //PreLogUtil.put("Input: " + input, this, "InputToGameKeyMapping::add(InputToGameKeyMapping)");
                this.addAll(input, inputToGameKeyMapping.getMappedInput(input));
            }
        }
    }

    private void addAll(Input input, BasicArrayList list)
    {
        //PreLogUtil.put(commonStrings.START_LABEL + input + " == " + list, this, "InputToGameKeyMapping::addAll");
        
        for (int index = list.size() - 1; index >= 0; index--)
        {
            Input mappedToInput = (Input) list.objectArray[index];
            
            //PreLogUtil.put("Input: " + input + " == " + mappedToInput, this, "InputToGameKeyMapping::addAll");
            this.add(input, mappedToInput);
        }
    }

    @Override    
    public void add(Input input, Input mappedToInput)
    {
        //PreLogUtil.put(commonStrings.START_LABEL + input + " == " + mappedToInput, this, "InputToGameKeyMapping::add");
        //logUtil.put(commonStrings.START_LABEL + input + " == " + mappedToInput, this, "InputToGameKeyMapping::add");
        super.add(input, mappedToInput);
        this.set(input, mappedToInput);
    }

    @Override
    public void remove(Input input, Input mappedToInput)
    {
        logUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(StringUtil.getInstance().toString(input)).append(" == ").append(StringUtil.getInstance().toString(mappedToInput)).toString(), this, "InputToGameKeyMapping::remove");
        super.remove(input, mappedToInput);
        this.set(input, gameKeyFactory.NONE);
    }

    private void set(Input input, Input mappedToInput)
    {
        int id = mappedToInput.getId();
        if(id >= 0 && id < platformToGameKeyMapping.length)
        {
            this.platformToGameKeyMapping[id] = (GameKey) input;
        }
    }

    public int getMaxMappable()
    {
        return platformToGameKeyMapping.length;
    }

    //This is slow but that is okay for options screen
    //This may not work at some point for a new platform since I have special mappings at the high end like touch and motion inputs
    public BasicArrayList getReverseInstance(int id)
    //throws Exception
    {
        BasicArrayList list = new BasicArrayList();

        final InputFactory inputFactory = InputFactory.getInstance();
        
        for(int index = 0; index < platformToGameKeyMapping.length; index++)
        {
            if(platformToGameKeyMapping[index].getId() == id)
            {
                Input input = inputFactory.getInstance(index);
                if(input != null)
                {
                    list.add(input);
                }
            }
        }

        return list;
        
        //throw new Exception("Could not reverse map to platform key");
    }

    /*
    public GameKey getInstance(Canvas canvas, int id)
    {
        //Using getKeyCode and updating the mapping would probably be better
        //canvas.getKeyCode(id);
        if(id < 0)
        {
            //This is lower than using the generated table
            return this.getInstance(canvas.getGameAction(id));
        }
        else
        {
            return this.getInstance(id);
        }
    }
    */

    private void init(Canvas canvas, GameKey gameKey) throws Exception
    {
        int key = canvas.getKeyCode(gameKey.getId());

        if(key < 0 && -key < negativePlatformToGameKeyMapping.length)
        {
            negativePlatformToGameKeyMapping[-key] = gameKey;
        }
        else if(key > 0)
        {
            if(platformToGameKeyMapping[key] == gameKeyFactory.NONE)
            {
                platformToGameKeyMapping[key] = gameKey;
            }
        } else {
            PreLogUtil.put(new StringMaker().append(gameKey.toString()).append(CommonSeps.getInstance().COLON_SEP).append(key).toString(), this, commonStrings.INIT, new Exception());
        }
        //Still could have a negative key that is out of range

        //PreLogUtil.put(gameKey.toString() + CommonSeps.getInstance().COLON_SEP + key, this, commonStrings.INIT);
    }

    private int getSmallestCanvasGameKeyCode(Canvas canvas) throws Exception
    {
        int nextKey = 0;
        int smallestKey = 0;
        for(int index = mappedGameKeys.length; --index >= 0;)
        {
            nextKey = canvas.getKeyCode(mappedGameKeys[index].getId());

            if(nextKey < smallestKey)
            {
                smallestKey = nextKey;
            }
        }

        return smallestKey;
    }

    public void init(Canvas canvas)
    {
        try
        {
            GameKey[] negativePlatformToGameKeyMapping = new GameKey[0];

            int smallestKey = this.getSmallestCanvasGameKeyCode(canvas);

            if (smallestKey < 0)
            {
                smallestKey--;
                
                if (smallestKey < -256)
                {
                    smallestKey = -256;
                }

                negativePlatformToGameKeyMapping = new GameKey[-smallestKey];

                for (int index = negativePlatformToGameKeyMapping.length; --index >= 0;)
                {
                    negativePlatformToGameKeyMapping[index] = gameKeyFactory.NONE;
                }

                this.negativePlatformToGameKeyMapping = negativePlatformToGameKeyMapping;
            }

            for (int index = mappedGameKeys.length; --index >= 0;)
            {
                this.init(canvas, mappedGameKeys[index]);
            }

            //PreLogUtil.put("Smallest GameKey: " + smallestKey, this, commonStrings.INIT);
        } catch (Throwable t)
        {
            //catch everything here little dangerous but I don't ever want to fail just because of failed mapping for j2me game keys
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, t);
        }
    }

    public GameKey getInstance(Canvas canvas, int id)
    {
        //Reduce method call by one for often used method
        //return this.getInstance(id);

        if(id >= 0 && id < platformToGameKeyMapping.length)
        {
            return platformToGameKeyMapping[id];
        }
        else
        if (id < 0 && -id < negativePlatformToGameKeyMapping.length)
        {
            return this.negativePlatformToGameKeyMapping[-id];
        }
        else
        {
            return gameKeyFactory.NONE;
        }
    }
    
    public GameKey getInstance(int id)
    {
        if(id >= 0 && id < platformToGameKeyMapping.length)
        {
            return platformToGameKeyMapping[id];
        }
        else
        if (id < 0 && -id < negativePlatformToGameKeyMapping.length)
        {
            return this.negativePlatformToGameKeyMapping[-id];
        }
        else
        {
            return gameKeyFactory.NONE;
        }
    }
}
