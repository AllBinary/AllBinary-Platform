package org.allbinary.graphics.opengles;

import jsinterop.annotations.JsType;

import javax.microedition.khronos.opengles.GL10;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class OpenGLProcessor
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    @JsMethod
    public void process(GL10 gl)
    {
    }
}
