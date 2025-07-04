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
    private String glShaderVersionString = stringUtil.EMPTY_STRING;
    public int shaderVersion = 0;
    private String glRenderer = stringUtil.EMPTY_STRING;
    private String glVendor = stringUtil.EMPTY_STRING;
    private String glExtensions = stringUtil.EMPTY_STRING;
    private boolean possiblyAccelerated;
    private String acceleratedString = stringUtil.EMPTY_STRING;
    public int maxTextureSize = 64;
    
    public final String VERSION_1_0 = "1.0";
    public final String VERSION_1_1 = "1.1";
    public final String VERSION_2_0 = "2.0";
    public final String VERSION_3_0 = "3.0";
    public final String VERSION_3_1 = "3.1";
    public final String VERSION_3_2 = "3.2";
    public final String VERSION_HIGHER_THAN_EXISTS = "999.999";

    public final String VERSION_UNK = "Unk";

    //OpenGLVersionValidator contains the version mapping for Android
    //Shader    to      OpenGL Version    
    //1.10              2.0
    //1.20              2.1
    //1.30              3.0
    //1.40              3.1
    //1.50              3.2
    //3.30              3.3  (From 3.3 on it is the same)
    //4.00              4.0
    //4.10              4.1
    //4.20              4.2
    //4.30              4.3
    //4.40              4.4
    //4.50              4.5
    public final String GL_EXT_GPU_SHADER4 = "GL_EXT_gpu_shader4";
    
    private String glVersion = this.glVersionString;
    public String glInstanceVersion = VERSION_UNK;
    private boolean glExtensionDrawTexture;
    
    private boolean glThreedDrawTexture;
    private boolean glExtensionGPUShader4;

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
            
            final int GL_SHADING_LANGUAGE_VERSION = 0x8b8c;
            glShaderVersionString = gl.glGetString(GL_SHADING_LANGUAGE_VERSION);
            //GL_NV_gpu_program4: SM 4.0 or better.
            //GL_NV_vertex_program3: SM 3.0 or better.
            //GL_ARB_fragment_program: SM 2.0 or better.            
            if(glShaderVersionString == null) {
                glShaderVersionString = stringUtil.EMPTY_STRING;
            }
            try {
                if(glShaderVersionString != null && glShaderVersionString.indexOf('.') >= 0) {

                    final int startIndex = glShaderVersionString.lastIndexOf(' ');
                    if (startIndex >= 0) {
                        glShaderVersionString = glShaderVersionString.substring(startIndex + 1);
                    }

                    shaderVersion = Integer.parseInt(glShaderVersionString.replace(CommonSeps.getInstance().PERIOD, StringUtil.getInstance().EMPTY_STRING));
                }
            } catch(Exception e) {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, METHOD_NAME, e));
            }

            glRenderer = gl.glGetString(GL10.GL_RENDERER);
            glVendor = gl.glGetString(GL10.GL_VENDOR);
            glExtensions = gl.glGetString(GL10.GL_EXTENSIONS);
            if(glExtensions.indexOf(this.GL_EXT_GPU_SHADER4) >= 0) {
                glExtensionGPUShader4 = true;
            } else {
                glExtensionGPUShader4 = false;
            }
            

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
                    if(AndroidUtil.isAndroid()) {
                        PreLogUtil.put("VBO implementation was detected, but disabled by default for Android", this, METHOD_NAME);
                    } else {
                        PreLogUtil.put("VBO implementation was detected, but disabled for now", this, METHOD_NAME);
                        //this.vertexBufferObjectSupport = true;
                    }
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
                    
                    PreLogUtil.put(stringBuffer.toString(), this, METHOD_NAME);
     
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
            
            //GL_MAX_RECTANGLE_TEXTURE_SIZE, and maybe GL_MAX_3D_TEXTURE_SIZE            
            final int[] maxTextureSizeArray = new int[1];
            gl.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, maxTextureSizeArray, 0);
            this.maxTextureSize = maxTextureSizeArray[0];
                
            //For now we will just use the poly version for opengl games
            //this.glThreedDrawTexture = true;
            //OpenGLImageSpecificFactory.getInstance().setImageFactory(new OpenGLESGL10RectangleImageFactory());
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, METHOD_NAME, e));
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
        stringBuffer.append(" GL_SHADING_LANGUAGE_VERSION: ");
        stringBuffer.append(this.glShaderVersionString);
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
        stringBuffer.append(" Max Texture Size: ");
        stringBuffer.append(this.maxTextureSize);
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
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.TOSTRING, e);
        }
        
        return stringBuffer.toString();
    }

    public boolean isGlExtensionDrawTexture()
    {
        return glExtensionDrawTexture;
    }

    public boolean isGlExtensionGPUShader4()
    {
        return glExtensionGPUShader4;
    }
    
    public String getGlVersion()
    {
        return glVersion;
    }

    public String getGlVersionString()
    {
        return glVersionString;
    }
    
    public String getGlShaderVersion()
    {
        return glShaderVersionString;
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

    public boolean isTextureSizeValid(final int widthAndHeight)
    {
        if(this.maxTextureSize >= widthAndHeight) {
            return true;
        }
        return false;
    }
    
}
