package org.allbinary.graphics.opengles.shader;

import jsinterop.annotations.JsType;

import javax.microedition.khronos.opengles.GL10;

import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;



@JsType
public class OpenGLObject3dProcessor
{
    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    @JsMethod
    public void process(GL10 gl, Object object3d)
    {
    }
    
    @JsMethod
    public void process(GL10 gl, Object object3d, int index)
    {
    }
}
