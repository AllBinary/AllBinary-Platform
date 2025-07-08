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
package org.allbinary.input.motion.button;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class TouchButtonFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TouchButtonFactory SINGLETON = new TouchButtonFactory();

    public static TouchButtonFactory getInstance()
    {
        return SINGLETON;
    }

    private BasicArrayList LIST = BasicArrayListUtil.getInstance().getImmutableInstance();
    private BasicArrayList list = LIST;
    private BasicArrayList savedList = LIST;

    private TouchButtonFactory()
    {
    }
        
    public void defaultList()
    {
        this.list = LIST;
        this.toggle(false, LIST);
    }

    private boolean saved = false;

    public synchronized void toggle(boolean save, BasicArrayList list)
    //throws Exception
    {
        //update savedlist and list if not in saved mode
        if (list != null)
        {
            //PreLogUtil.put("Save: " + save + " list: " + list.toString(), this, "toggle");
            
            this.savedList = list;
            if (!this.saved)
            {
                this.list = this.savedList;
            }
        }
        else
        {
            if(save)
            {
                if(!this.saved)
                {
                    //PreLogUtil.put("Saving", this, "toggle");
                    
                    this.saved = true;
                    
                    this.savedList = this.list;
                    this.list = LIST;
                }
                else
                {
                    //PreLogUtil.put("Already Saved", this, "toggle");
                    //already in saved mode
                }
            }
            else
            {
                //PreLogUtil.put("Restoring", this, "toggle");
                
                this.list = this.savedList;
                this.saved = false;
                
                /*
                if(saved)
                {
                    
                }
                else
                {
                    //already in restored mode
                }
                */
            }
        }    
    }

    public void setList(BasicArrayList list) throws Exception
    {
        //ForcedLogUtil.log("Touch Buttons: " + list, this);
        
        if(list == null)
        {
            throw new Exception("Null List");
        }
        
        this.list = list;
    }

    /**
     * @return the list
     */
    protected BasicArrayList getList()
    {
        return list;
    }
}
