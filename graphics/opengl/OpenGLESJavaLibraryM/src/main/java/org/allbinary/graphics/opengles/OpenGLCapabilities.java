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
import javax.microedition.khronos.opengles.GL11;
import org.allbinary.AndroidUtil;

import org.allbinary.image.opengles.OpenGLESGL10ImageFactory;
import org.allbinary.image.opengles.OpenGLESGL11VBOImageFactory;
import org.allbinary.image.opengles.OpenGLImageSpecificFactory;
import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.tokens.Tokenizer;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class OpenGLCapabilities
{
    private static final OpenGLCapabilities instance = new OpenGLCapabilities();
    
    public static OpenGLCapabilities getInstance()
    {
        return instance;
    }
    
    public final boolean CUSTOM_GL_SURFACE_VIEW = true;
    
    private final StringUtil stringUtil = StringUtil.getInstance();
    
    private String glVersionString = stringUtil.EMPTY_STRING;
    private String glRenderer = stringUtil.EMPTY_STRING;
    private String glVendor = stringUtil.EMPTY_STRING;
    private String glExtensions = stringUtil.EMPTY_STRING;
    private boolean possiblyAccelerated;
    private String acceleratedString = stringUtil.EMPTY_STRING;
    
    public final String VERSION_1_0 = "1.0";
    public final String VERSION_1_1 = "1.1";
    public final String VERSION_UNK = "Unk";
    
    private String glVersion = this.glVersionString;
    public String glInstanceVersion = VERSION_UNK;
    private boolean glExtensionDrawTexture;
    
    private boolean glThreedDrawTexture;

    private boolean vertexBufferObjectSupport;
    
    private OpenGLCapabilities()
    {
    }

    public void initCapabilities(GL10 gl)
    {
        final String METHOD_NAME = "initGLCapabilities";
        
        try {
            
            final Features features = Features.getInstance();
            final OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();

            final OpenGLImageSpecificFactory openGLImageSpecificFactory = OpenGLImageSpecificFactory.getInstance();

            final StringMaker stringBuffer = new StringMaker();

            glVersionString = gl.glGetString(GL10.GL_VERSION);
            glRenderer = gl.glGetString(GL10.GL_RENDERER);
            glVendor = gl.glGetString(GL10.GL_VENDOR);
            glExtensions = gl.glGetString(GL10.GL_EXTENSIONS);

            if(glRenderer == null) {
                glRenderer = stringUtil.EMPTY_STRING;
            }

            if (glRenderer.toLowerCase().indexOf("pixelflinger") >= 0)
            {
                acceleratedString = "Probably Not for " + glRenderer;
                possiblyAccelerated = false;
            } else
            {
                acceleratedString = "Probably for " + glRenderer;;
                possiblyAccelerated = true;
            }

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
            //The device often reports that it does have it when it does not
            /*
        if(this.isExtension(openGLFeatureFactory.OPENGL_DRAW_TEXTURE))
        {
            this.glExtensionDrawTexture = true;
        }
        else
        {
            this.glExtensionDrawTexture = false;
        }
             */
            this.glExtensionDrawTexture = false;

            if(this.glVersionString == null) {
                this.glVersionString = stringUtil.EMPTY_STRING;
                this.glVersion = this.VERSION_UNK;
            } else if (this.glVersionString.indexOf(" 1.0") >= 0)
            {
                this.glVersion = this.VERSION_1_0;
            } else if (this.glVersionString.indexOf(" 1.1") >= 0)
            {
                this.glVersion = this.VERSION_1_1;
            } else
            {
                this.glVersion = this.VERSION_UNK;
            }

            if(gl instanceof GL11) {
                this.glInstanceVersion = this.VERSION_1_1;
            } else if(gl instanceof GL10) {
                this.glInstanceVersion = this.VERSION_1_0;
            }

            if(glVendor == null) {
                glVendor = stringUtil.EMPTY_STRING;
            }
                        
            if(glExtensions == null) {
                glExtensions = stringUtil.EMPTY_STRING;
            }

            if (possiblyAccelerated)
            {
                PreLogUtil.put(new StringMaker().append("VBO:?").append((this.glInstanceVersion == this.VERSION_1_1)).append("||").append(this.isExtension(openGLFeatureFactory.OPENGL_VERTEX_BUFFER_OBJECT)).toString(), this, METHOD_NAME);
                if ((this.glInstanceVersion == this.VERSION_1_1 || this.isExtension(openGLFeatureFactory.OPENGL_VERTEX_BUFFER_OBJECT)))
                {
                      PreLogUtil.put("VBO implementation was detected, but disabled for now", this, METHOD_NAME);                    
//                    if(AndroidUtil.isAndroid()) {
//                        PreLogUtil.put("VBO implementation was detected, but disabled by default for Android", this, METHOD_NAME);
//                    } else {
//                        this.vertexBufferObjectSupport = true;
//                    }
                }
            }
            
            final StringUtil stringUtil = StringUtil.getInstance();

            if (features.isDefault(openGLFeatureFactory.OPENGL_AUTO_SELECT))
            {
                //if (GameFeatures.getInstance().isDefault(GameFeature.OPENGL_DRAW_TEXTURE))
                //{
                //StupidTimer.visit(new RendererSurfaceCreatedVisitor());

                if (this.isVertexBufferObjectSupport())
                {
                    openGLImageSpecificFactory.setImageFactory(new OpenGLESGL11VBOImageFactory());
                } else
                /*
                if(isGlExtensionDrawTexture())
                {
                    stringBuffer.append("Found: ");
                    stringBuffer.append(OpenGLFeatureFactory.getInstance().OPENGL_DRAW_TEXTURE);
                    
                    PreLogUtil.put(stringBuffer.toString(), this, "initGLCapabilities");
     
                    openGLImageSpecificFactory.setImageFactory(new OpenGLESGL11ExtImageFactory());
                }
                else
                 */
                {
                    stringBuffer.append("OpenGL is on but ");
                    stringBuffer.append(stringUtil.toString(openGLFeatureFactory.OPENGL_DRAW_TEXTURE));
                    stringBuffer.append(" was not available");

                    PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);

                    openGLImageSpecificFactory.setImageFactory(new OpenGLESGL10ImageFactory());
                }
                //}
            } else
            {
                stringBuffer.append(stringUtil.toString(openGLFeatureFactory.OPENGL_AUTO_SELECT));
                stringBuffer.append(" is not on");

                PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);

                openGLImageSpecificFactory.setImageFactory(new OpenGLESGL10ImageFactory());
            }

            //For now we will just use the poly version for opengl games
            //this.glThreedDrawTexture = true;
            //OpenGLImageSpecificFactory.getInstance().setImageFactory(new OpenGLESGL10RectangleImageFactory());
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, METHOD_NAME, e));
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
        final CommonSeps commonSeps = CommonSeps.getInstance();
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("GL_VERSION: ");
        stringBuffer.append(glVersionString);
        stringBuffer.append(" GL_RENDERER: ");
        stringBuffer.append(glRenderer);
        stringBuffer.append(" GL_VENDOR: ");
        stringBuffer.append(glVendor);
        stringBuffer.append(commonSeps.NEW_LINE);
        stringBuffer.append(" Is Accelerated: ");
        stringBuffer.append(acceleratedString);
        stringBuffer.append(commonSeps.NEW_LINE);
        stringBuffer.append(" VBO Support: ");
        stringBuffer.append(this.isVertexBufferObjectSupport());
        stringBuffer.append(commonSeps.NEW_LINE);
        stringBuffer.append(" GL_EXTENSIONS: ");
        
        try
        {
            Tokenizer tokenizer = new Tokenizer(commonSeps.SPACE);

            BasicArrayList list = tokenizer.getTokens(glExtensions, new BasicArrayList());

            for(int index = 0; index < list.size(); index++)
            {
                stringBuffer.append(commonSeps.NEW_LINE);
                stringBuffer.append(stringUtil.toString(list.objectArray[index]));
            }
        }
        catch(Exception e)
        {
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, "toString", e);
        }
        
        return stringBuffer.toString();
    }

    public boolean isGlExtensionDrawTexture()
    {
        return glExtensionDrawTexture;
    }

    public String getGlVersion()
    {
        return glVersion;
    }

    public boolean isGlThreedDrawTexture()
    {
        return glThreedDrawTexture;
    }

    public String getGlRenderer()
    {
        return glRenderer;
    }

    public boolean isVertexBufferObjectSupport()
    {
        return vertexBufferObjectSupport;
    }
}
