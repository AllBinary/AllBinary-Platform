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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.game.configuration.event.ChangedGameFeatureListener;
import org.allbinary.game.configuration.feature.Feature;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import org.allbinary.logic.string.StringUtil;

public class OpenGLConfiguration
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final OpenGLConfiguration instance = 
        new OpenGLConfiguration();
    
    public static OpenGLConfiguration getInstance()
    {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private boolean opengl = false;

    private OpenGLFeature type =
    		//OpenGLFeatureFactory.getInstance().OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS;
    		OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD;
    
    private OpenGLFeature imageColor = 
        OpenGLFeatureFactory.getInstance().IMAGE_COLOR_DEPTH_4444;
    private OpenGLFeature color = 
        OpenGLFeatureFactory.getInstance().IMAGE_COLOR_DEPTH_4444;

    private OpenGLFeature versionSelector = 
        OpenGLFeatureFactory.getInstance().OPENGL_AUTO_SELECT;
        
    private OpenGLConfiguration()
    {
        //PreLogUtil.put(imageColor.getName(), this, "Constructor - imageColor - depth");
        //PreLogUtil.put(color.getName(), this, "Constructor - color - depth");
        
    }

    public void write() {
        
    }
    
    public void init() throws Exception
    {
        final Features features = Features.getInstance();
        
        if (ChangedGameFeatureListener.getInstance().isChanged(
                MainFeatureFactory.getInstance().STATIC))
        {
            if (this.isOpenGL())
            {
                if (!features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
                {
                    logUtil.put("Turning on OpenGL",
                            this, commonStrings.INIT);
                    
                    features.addDefault(OpenGLFeatureFactory.getInstance().OPENGL);
                    
                    logUtil.put("Using OpenGL Type Feature: "
                            + this.getType(), this, commonStrings.INIT);
                    
                    features.addDefault(this.getType());
                    
                    logUtil.put("Using OpenGL ImageColor Feature: "
                            + this.getImageColor(), this, commonStrings.INIT);
                    features.addDefault(this.getImageColor());

                    logUtil.put("Using OpenGL Color Feature: "
                            + this.getColor(), this, commonStrings.INIT);
                    features.addDefault(this.getColor());

                    logUtil.put("Using OpenGL Version Selector Feature: "
                            + this.getVersionSelector(), this, commonStrings.INIT);
                    features.addDefault(this.getVersionSelector());
                }
            }
            else
            {
                logUtil.put("OpenGL is Off", this,
                        commonStrings.INIT);

                // Turning off OpenGL
                // if(features.isDefault(OpenGLFeature.OPENGL))
                // {
                // logUtil.put("Turning off OpenGL",
                // this, commonStrings.INIT);
                // features.removeDefault(OpenGLFeature.OPENGL);
                // }
            }
        }
        else
        {
            if(this.isOpenGL() && !features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
            {
                PreLogUtil.put("OpenGL is set but not Enabled since Statics where not cleared (Reboot or Complete exit is Required)", this, commonStrings.INIT);
            }
        }
        
        PreLogUtil.put(this.toString(), this, this.commonStrings.INIT);
    }

    public void update(final Feature gameFeature, final boolean colorLocked)
    throws Exception
    {
        final Features features = Features.getInstance();
        
        final OpenGLFeatureFactory openGLFeatureFactory = 
            OpenGLFeatureFactory.getInstance();
        
        boolean modified = false;

        if (gameFeature == openGLFeatureFactory.OPENGL)
        {
            if (features.isFeature(gameFeature))
            {
                if(!this.isOpenGL())
                {
                    this.setOpenGL(true);
                    modified = true;
                }
                
            } else
            {
                if(this.isOpenGL())
                {
                    this.setOpenGL(false);
                    modified = true;
                }
            }            
        }

        if (gameFeature == openGLFeatureFactory.OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS ||
                gameFeature == openGLFeatureFactory.OPENGL_AS_GAME_THREAD)
        {
            if (features.isFeature(gameFeature))
            {
                if(gameFeature != this.getType())
                {
                    this.setType((OpenGLFeature) gameFeature);
                    modified = true;
                }
            }
        }

        if (gameFeature == openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444 ||
                gameFeature == openGLFeatureFactory.IMAGE_COLOR_DEPTH_565 ||
                gameFeature == openGLFeatureFactory.IMAGE_COLOR_DEPTH_8888)
        {
            if (features.isFeature(gameFeature))
            {
                if(this.getImageColor() != gameFeature)
                {
                    this.setImageColor((OpenGLFeature) gameFeature);
                    
                    if(colorLocked)
                    {
                        if (gameFeature == openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444)
                        {
                            this.setColor(openGLFeatureFactory.OPENGL_COLOR_DEPTH_4444);
                        }
                        else
                            if (gameFeature == openGLFeatureFactory.IMAGE_COLOR_DEPTH_565)
                            {
                                this.setColor(openGLFeatureFactory.OPENGL_COLOR_DEPTH_565);
                            }
                            else
                                if (gameFeature == openGLFeatureFactory.IMAGE_COLOR_DEPTH_8888)
                                {
                                    this.setColor(openGLFeatureFactory.OPENGL_COLOR_DEPTH_8888);
                                }
                    }
                    modified = true;
                }
            }
        }
        
        if (gameFeature == openGLFeatureFactory.OPENGL_COLOR_DEPTH_4444 ||
                gameFeature == openGLFeatureFactory.OPENGL_COLOR_DEPTH_565 ||
                gameFeature == openGLFeatureFactory.OPENGL_COLOR_DEPTH_8888)
        {
            if (features.isFeature(gameFeature))
            {
                if(this.getColor() != gameFeature)
                {
                    this.setColor((OpenGLFeature) gameFeature);
                    modified = true;
                }
            }
        }

        if (gameFeature == openGLFeatureFactory.OPENGL_AUTO_SELECT ||
                gameFeature == openGLFeatureFactory.OPENGL_MINIMUM)
        {
            if (features.isFeature(gameFeature))
            {
                this.setVersionSelector((OpenGLFeature) gameFeature);
            }

            modified = true;
        }
        
    }
    
    public void setOpenGL(final boolean opengl)
    {
        this.opengl = opengl;
    }

    public boolean isOpenGL()
    {
        return opengl;
    }

    public void setImageColor(final OpenGLFeature imageColor)
    {
        //PreLogUtil.put(imageColor.getName(), this, "setImageColor - depth");
        this.imageColor = imageColor;
    }

    public OpenGLFeature getImageColor()
    {
        return imageColor;
    }

    public void setColor(final OpenGLFeature color)
    {
        //PreLogUtil.put(imageColor.getName(), this, "setColor - depth");
        this.color = color;
    }

    public OpenGLFeature getColor()
    {
        return color;
    }

    public void setVersionSelector(final OpenGLFeature versionSelector)
    {
        this.versionSelector = versionSelector;
    }

    public OpenGLFeature getVersionSelector()
    {
        return versionSelector;
    }

    public void setType(final OpenGLFeature type)
    {
        this.type = type;
    }

    public OpenGLFeature getType()
    {
        return type;
    }
    
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();
        final StringUtil stringUtil = StringUtil.getInstance();

        stringBuffer.append(" isOpenGL: ");
        stringBuffer.append(this.isOpenGL());
        stringBuffer.append(" VersionSelector: ");
        stringBuffer.append(stringUtil.toString(this.getVersionSelector()));
        stringBuffer.append(" Type: ");
        stringBuffer.append(stringUtil.toString(this.getType()));
        stringBuffer.append(" Image Color: ");
        stringBuffer.append(stringUtil.toString(this.getImageColor()));
        stringBuffer.append(" Color: ");
        stringBuffer.append(stringUtil.toString(this.getColor()));

        return stringBuffer.toString();
    }
}
