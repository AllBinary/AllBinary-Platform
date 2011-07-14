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

import javax.microedition.khronos.opengles.GL10;

import org.allbinary.image.opengles.OpenGLESGL10ImageFactory;
import org.allbinary.image.opengles.OpenGLESGL11ExtImageFactory;
import org.allbinary.image.opengles.OpenGLImageSpecificFactory;

import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.configuration.feature.Features;

public class OpenGLCapabilities
{
    private static final OpenGLCapabilities instance = new OpenGLCapabilities();
    
    public static OpenGLCapabilities getInstance()
    {
        return instance;
    }
    
    private String glVersionString = StringUtil.getInstance().EMPTY_STRING;
    private String glExtensions = this.glVersionString;
    
    public final String VERSION_1_0 = "1.0";
    public final String VERSION_1_1 = "1.1";
    public final String VERSION_UNK = "Unk";
    
    private String glVersion = this.glVersionString;
    private boolean glExtensionDrawTexture;

    private OpenGLCapabilities()
    {
    }

    public void initCapabilities(GL10 gl)
    throws Exception
    {
        glVersionString = gl.glGetString(GL10.GL_VERSION);
        glExtensions = gl.glGetString(GL10.GL_EXTENSIONS);
        
        /*
        class RendererSurfaceCreatedVisitor 
        implements VisitorInterface
        {

            public Object visit(Object object)
            {
                    BooleanFactory booleanFactory = BooleanFactory.getInstance();
                    if (!AllBinaryRenderer.isSurfaceCreated())
                    {
                        return booleanFactory.TRUE;
                    }
                    else
                    {
                        return booleanFactory.FALSE;
                    }
            }
        }
        */

        if(this.isExtension(OpenGLFeatureFactory.getInstance().OPENGL_DRAW_TEXTURE))
        {
            this.setGlExtensionDrawTexture(true);
        }
        else
        {
            this.setGlExtensionDrawTexture(false);
        }

        if(this.glVersionString.indexOf(" 1.0") >= 0)
        {
            this.setGlVersion(this.VERSION_1_0);
        }
        else
            if(this.glVersionString.indexOf(" 1.1") >= 0)
        {
                this.setGlVersion(this.VERSION_1_1);
        }
            else
            {
                this.setGlVersion(this.VERSION_UNK);
            }

        if (Features.getInstance().isDefault(
                OpenGLFeatureFactory.getInstance().OPENGL_AUTO_SELECT))
        {
            //if (GameFeatures.getInstance().isDefault(GameFeature.OPENGL_DRAW_TEXTURE))
            //{                   
                //StupidTimer.visit(new RendererSurfaceCreatedVisitor());
                                
                if(isGlExtensionDrawTexture())
                {
                    //ReloadConfiguration reloadConfiguration = 
                        //ReloadConfiguration.getInstance();
                    //reloadConfiguration.setOpenGL(true);
                    //reloadConfiguration.write();
                    
                    PreLogUtil.put("Found: " + OpenGLFeatureFactory.getInstance().OPENGL_DRAW_TEXTURE, 
                            this, "initGLCapabilities");
     
                    OpenGLImageSpecificFactory.getInstance().setImageFactory(
                            new OpenGLESGL11ExtImageFactory());
                }
                else
                {
                    PreLogUtil.put(
                            "OpenGL is on but " + OpenGLFeatureFactory.getInstance().OPENGL_DRAW_TEXTURE + " was not available", 
                            this, "initGLCapabilities");

                    OpenGLImageSpecificFactory.getInstance().setImageFactory(
                            new OpenGLESGL10ImageFactory());
                }
            //}
        } else
        {
            PreLogUtil.put(OpenGLFeatureFactory.getInstance().OPENGL_AUTO_SELECT + 
                    " is not on", this, "initGLCapabilities");
            
            OpenGLImageSpecificFactory.getInstance().setImageFactory(
                    new OpenGLESGL10ImageFactory());
        }
    }
    
    private boolean isExtension(OpenGLFeature gameFeature)
    {
        int index = glExtensions.indexOf(gameFeature.getName());
        if(index >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String toString()
    {
        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append("GL_VERSION: ");
        stringBuffer.append(glVersionString);
        stringBuffer.append(" GL_EXTENSIONS: ");
        stringBuffer.append(glExtensions);
        return stringBuffer.toString();
    }

    private void setGlExtensionDrawTexture(boolean glExtensionDrawTexture)
    {
        this.glExtensionDrawTexture = glExtensionDrawTexture;
    }

    public boolean isGlExtensionDrawTexture()
    {
        return glExtensionDrawTexture;
    }

    private void setGlVersion(String glVersion)
    {
        this.glVersion = glVersion;
    }

    public String getGlVersion()
    {
        return glVersion;
    }

}
