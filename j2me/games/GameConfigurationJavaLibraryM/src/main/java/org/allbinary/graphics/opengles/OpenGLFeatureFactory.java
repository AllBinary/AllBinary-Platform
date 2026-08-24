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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

@JsType
public class OpenGLFeatureFactory
{
    private static final OpenGLFeatureFactory instance = new OpenGLFeatureFactory();

    @JsMethod
    public static OpenGLFeatureFactory getInstance()
    {
        return OpenGLFeatureFactory.instance;
    }

    @JsConstructor
    private OpenGLFeatureFactory()
    {

    }

    @JsProperty
    public final OpenGLFeature OPENGL_OPTIONS = new OpenGLFeature("OpenGL Options");

    @JsProperty
    public final OpenGLFeature OPENGL = new OpenGLFeature("OpenGL");
    
    @JsProperty
    public final OpenGLFeature OPENGL_SURFACE_VIEW = new OpenGLFeature("GLSurfaceView");

    @JsProperty
    public final OpenGLFeature OPENGL_2D = new OpenGLFeature("OpenGL 2D");
    @JsProperty
    public final OpenGLFeature OPENGL_3D = new OpenGLFeature("OpenGL 3D");
    
    @JsProperty
    public final OpenGLFeature OPENGL_2D_AND_3D = new OpenGLFeature("OpenGL 2D/3D");

    // OpenGL Version
    @JsProperty
    public final OpenGLFeature OPENGL_AUTO_SELECT = new OpenGLFeature("Auto Select");
    @JsProperty
    public final OpenGLFeature OPENGL_MINIMUM = new OpenGLFeature("Minimum");

    // Threading
    @JsProperty
    public final OpenGLFeature OPENGL_AS_GAME_THREAD = new OpenGLFeature("As game thread");
    @JsProperty
    public final OpenGLFeature OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS = new OpenGLFeature("As different threads");

    // Image Color Depth
    @JsProperty
    public final OpenGLFeature IMAGE_COLOR_DEPTH_4444 = new OpenGLFeature("4444");
    @JsProperty
    public final OpenGLFeature IMAGE_COLOR_DEPTH_8888 = new OpenGLFeature("8888");
    @JsProperty
    public final OpenGLFeature IMAGE_COLOR_DEPTH_565 = new OpenGLFeature("565");

    // OpenGL Color Depth
    @JsProperty
    public final OpenGLFeature OPENGL_COLOR_DEPTH_4444 = new OpenGLFeature("4444");
    @JsProperty
    public final OpenGLFeature OPENGL_COLOR_DEPTH_8888 = new OpenGLFeature("8888");
    @JsProperty
    public final OpenGLFeature OPENGL_COLOR_DEPTH_565 = new OpenGLFeature("565");

    @JsProperty
    public final OpenGLFeature OPENGL_DRAW_TEXTURE = new OpenGLFeature("GL_OES_draw_texture");
    
    @JsProperty
    public final OpenGLFeature OPENGL_VERTEX_BUFFER_OBJECT = new OpenGLFeature("vertex_buffer_object");
    
    @JsProperty
    public final OpenGLFeature OPENGL_SIMPLE_OBJECT3D_PROCESSOR = new OpenGLFeature("Simple Object3d Processor");
    @JsProperty
    public final OpenGLFeature OPENGL_COMPLEX_OBJECT3D_PROCESSOR = new OpenGLFeature("Complex Object3d Processor");

    @JsProperty
    public final OpenGLFeature OPENGL_SIMPLE_TEXTURE_PROCESSOR = new OpenGLFeature("Simple Texture Processor");
    @JsProperty
    public final OpenGLFeature OPENGL_COMPLEX_TEXTURE_PROCESSOR = new OpenGLFeature("Complex Texture Processor");
}
