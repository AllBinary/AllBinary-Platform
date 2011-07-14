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

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import abcs.logic.basic.io.AbDataOutputStream;
import abcs.logic.basic.io.FileStreamFactory;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.FileFactory;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.configuration.event.ChangedGameFeatureListener;
import allbinary.game.configuration.feature.Feature;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.MainFeatureFactory;

public class OpenGLConfiguration
{
    private static final OpenGLConfiguration instance = 
        new OpenGLConfiguration();
    
    public static OpenGLConfiguration getInstance()
    {
        return instance;
    }

    private static final String FILE = "OpenGLConfiguration.dat";
    
    private boolean opengl = false;

    private OpenGLFeature type = 
        OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD;
    
    private OpenGLFeature imageColor = 
        OpenGLFeatureFactory.getInstance().IMAGE_COLOR_DEPTH_4444;
    private OpenGLFeature color = 
        OpenGLFeatureFactory.getInstance().IMAGE_COLOR_DEPTH_4444;

    private OpenGLFeature versionSelector = 
        OpenGLFeatureFactory.getInstance().OPENGL_AUTO_SELECT;
        
    private OpenGLConfiguration()
    {
        try
        {
            if (FileFactory.getInstance().isFile(FILE))
            {
                this.read();
            }
            else
            {
                this.write();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this,
                    CommonStrings.getInstance().CONSTRUCTOR, e));
        }
    }
    
    private void read() throws Exception
    {
        OpenGLFeatureFactory openGLFeatureFactory = 
            OpenGLFeatureFactory.getInstance();
        
        FileStreamFactory fileInputStreamFactory = 
            FileStreamFactory.getInstance();

        InputStream fileInputStream = 
            fileInputStreamFactory.getFileInputStreamInstance(
                    StringUtil.getInstance().EMPTY_STRING, FILE);

        DataInputStream dataInputStream = new DataInputStream(fileInputStream);

        int openGLValue = dataInputStream.readInt();
        if (openGLValue == 0)
        {
            this.setOpenGL(false);
        }
        else if (openGLValue == 1)
        {
            this.setOpenGL(true);
        }
        else
        {
            throw new Exception("Invalid OpenGL Setting");
        }

        String version = dataInputStream.readUTF();
        
        if(version.compareTo(openGLFeatureFactory.OPENGL_AUTO_SELECT.getName()) == 0)
        {
            this.setVersionSelector(openGLFeatureFactory.OPENGL_AUTO_SELECT);
        }
        else
            if(version.compareTo(openGLFeatureFactory.OPENGL_MINIMUM.getName()) == 0)
            {
                this.setVersionSelector(openGLFeatureFactory.OPENGL_MINIMUM);
            }
            else
        {
            throw new Exception("OpenGLConfiguration: Error reading version selector: " + version);
        }
        
        String type = dataInputStream.readUTF();
        
        if(type.compareTo(openGLFeatureFactory.OPENGL_AS_GAME_THREAD.getName()) == 0)
        {
            this.setType(openGLFeatureFactory.OPENGL_AS_GAME_THREAD);
        }
        else
            if(type.compareTo(openGLFeatureFactory.OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS.getName()) == 0)
            {
                this.setType(openGLFeatureFactory.OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS);
            }
                else
        {
            throw new Exception("OpenGLConfiguration: Error reading image color");
        }
        
        String imageColor = dataInputStream.readUTF();
        
        if(imageColor.compareTo(openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444.getName()) == 0)
        {
            this.setImageColor(openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444);
        }
        else
            if(imageColor.compareTo(openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444.getName()) == 0)
            {
                this.setImageColor(openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444);
            }
            else
                if(imageColor.compareTo(openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444.getName()) == 0)
                {
                    this.setImageColor(openGLFeatureFactory.IMAGE_COLOR_DEPTH_4444);
                }
                else
        {
            throw new Exception("OpenGLConfiguration: Error reading image color: " + imageColor);
        }

        String color = dataInputStream.readUTF();
        
        if(color.compareTo(openGLFeatureFactory.OPENGL_COLOR_DEPTH_4444.getName()) == 0)
        {
            this.setColor(openGLFeatureFactory.OPENGL_COLOR_DEPTH_4444);
        }
        else
            if(color.compareTo(openGLFeatureFactory.OPENGL_COLOR_DEPTH_565.getName()) == 0)
            {
                this.setColor(openGLFeatureFactory.OPENGL_COLOR_DEPTH_565);
            }
            else
                if(color.compareTo(openGLFeatureFactory.OPENGL_COLOR_DEPTH_8888.getName()) == 0)
                {
                    this.setColor(openGLFeatureFactory.OPENGL_COLOR_DEPTH_8888);
                }
                else
        {
            throw new Exception("OpenGLConfiguration: Error reading color: " + color);
        }
        
        PreLogUtil.put("Read Configuration: " + this.toString(), this, "read");
    }
    
    public void write() throws Exception
    {
        AbDataOutputStream dataOutputStream = null;
        try
        {
            //LogUtil.put(LogFactory.getInstance("Write Configuration: " + this.toString(), this, "write"));
            PreLogUtil.put("Write Configuration: " + this.toString(), this, "write");

            FileStreamFactory fileInputStreamFactory = FileStreamFactory.getInstance();

            OutputStream fileOutputStream = fileInputStreamFactory
                    .getFileOutputStreamInstance(
                            StringUtil.getInstance().EMPTY_STRING, FILE);

            dataOutputStream = new AbDataOutputStream(fileOutputStream);

            if (this.isOpenGL())
            {
                dataOutputStream.writeInt(1);
            }
            else
            {
                dataOutputStream.writeInt(0);
            }

            dataOutputStream.writeUTF(this.getVersionSelector().getName());

            dataOutputStream.writeUTF(this.getType().getName());

            dataOutputStream.writeUTF(this.getImageColor().getName());
            dataOutputStream.writeUTF(this.getColor().getName());

            dataOutputStream.flush();
        }
        finally
        {
            StreamUtil.getInstance().close(dataOutputStream);
        }
        
    }

    public void init() throws Exception
    {
        Features features = Features.getInstance();
        
        if (ChangedGameFeatureListener.getInstance().isChanged(
                MainFeatureFactory.getInstance().STATIC))
        {
            if (this.isOpenGL())
            {
                if (!features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
                {
                    LogUtil.put(LogFactory.getInstance("Turning on OpenGL",
                            this, CommonStrings.getInstance().INIT));
                    
                    features.addDefault(OpenGLFeatureFactory.getInstance().OPENGL);
                    
                    LogUtil.put(LogFactory.getInstance("Using OpenGL Type Feature: "
                            + this.getType(), this, CommonStrings.getInstance().INIT));
                    
                    features.addDefault(this.getType());
                    
                    LogUtil.put(LogFactory.getInstance("Using OpenGL ImageColor Feature: "
                            + this.getImageColor(), this, CommonStrings.getInstance().INIT));
                    features.addDefault(this.getImageColor());

                    LogUtil.put(LogFactory.getInstance("Using OpenGL Color Feature: "
                            + this.getColor(), this, CommonStrings.getInstance().INIT));
                    features.addDefault(this.getColor());

                    LogUtil.put(LogFactory.getInstance("Using OpenGL Version Selector Feature: "
                            + this.getVersionSelector(), this, CommonStrings.getInstance().INIT));
                    features.addDefault(this.getVersionSelector());
                }
            }
            else
            {
                LogUtil.put(LogFactory.getInstance("OpenGL is Off", this,
                        CommonStrings.getInstance().INIT));

                // Turning off OpenGL
                // if(features.isDefault(OpenGLFeature.OPENGL))
                // {
                // LogUtil.put(LogFactory.getInstance("Turning off OpenGL",
                // this, CommonStrings.getInstance().INIT));
                // features.removeDefault(OpenGLFeature.OPENGL);
                // }
            }
        }
        else
        {
            if(this.isOpenGL() && !features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
            {
                PreLogUtil.put("OpenGL is set but not Enabled since Statics where not cleared (Reboot or Complete exit is Required)", this, CommonStrings.getInstance().INIT);
            }
        }
        
        PreLogUtil.put(this.toString(), this, "init");
    }

    public void update(Feature gameFeature, boolean colorLocked)
    throws Exception
    {
        Features features = Features.getInstance();
        
        OpenGLFeatureFactory openGLFeatureFactory = 
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
        
        if(modified)
        {
            this.write();
        }
    }

    public void setOpenGL(boolean opengl)
    {
        this.opengl = opengl;
    }

    public boolean isOpenGL()
    {
        return opengl;
    }

    public void setImageColor(OpenGLFeature imageColor)
    {
        this.imageColor = imageColor;
    }

    public OpenGLFeature getImageColor()
    {
        return imageColor;
    }

    public void setColor(OpenGLFeature color)
    {
        this.color = color;
    }

    public OpenGLFeature getColor()
    {
        return color;
    }

    public void setVersionSelector(OpenGLFeature versionSelector)
    {
        this.versionSelector = versionSelector;
    }

    public OpenGLFeature getVersionSelector()
    {
        return versionSelector;
    }

    public void setType(OpenGLFeature type)
    {
        this.type = type;
    }

    public OpenGLFeature getType()
    {
        return type;
    }
    
    public String toString()
    {
        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append(" isOpenGL: ");
        stringBuffer.append(this.isOpenGL());
        stringBuffer.append(" VersionSelector: ");
        stringBuffer.append(this.getVersionSelector());
        stringBuffer.append(" Type: ");
        stringBuffer.append(this.getType());
        stringBuffer.append(" Image Color: ");
        stringBuffer.append(this.getImageColor());
        stringBuffer.append(" Color: ");
        stringBuffer.append(this.getColor());

        return stringBuffer.toString();
    }
}
