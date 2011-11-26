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
package allbinary.game.health;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Graphics;

import min3d.core.ColorBufferList;
import min3d.core.Number3dBufferList;
import min3d.objectPrimitives.Rectangle;
import min3d.vos.Number3d;

import org.allbinary.graphics.threed.min3d.renderer.Object3dProcessorUtil;
import org.allbinary.android.device.OpenGLESGraphics;

import allbinary.graphics.color.BasicColorFactory;
import allbinary.layer.AllBinaryLayer;
import allbinary.view.ViewPosition;

public class HealthBarThreedAnimation
extends HealthBarAnimation
{
	private Rectangle rectangle;
	
    private final BasicColorFactory basicColorFactory = 
            BasicColorFactory.getInstance();

    public HealthBarThreedAnimation(AllBinaryLayer layerInterface, int location)
    throws Exception
    {
        super(layerInterface, location);
        
        this.basicColor = this.basicColorFactory.GREEN;
        this.color = this.basicColor.intValue();
        
        rectangle = new Rectangle(
        		0, 0, 
        		1, 1, this.basicColor);
        rectangle.setDoubleSidedEnabled(true);
        rectangle.setNormalsEnabled(false);
        rectangle.setTexturesEnabled(false);
    }

    private void updateColor()
    {
    	ColorBufferList colorBufferList =
    			rectangle.getVertices().getColor4BufferList();
    	
    	int size = colorBufferList.size();
    	
    	for(int index = size; --index >= 0;)
    	{
    		colorBufferList.set(index, this.basicColor);
    	}
    }

    private void updateSize()
    {
    	Number3dBufferList number3dBufferList = rectangle.getVertices().getPointsNumber3dBufferList();

        int width = x2;
        int height = this.thickness * 2;
    	
    	int segsH = 1;
    	int segsW = 1;
    	
    	int row;
    	int col;
    	
		float w = width / segsW;
		float h = height / segsH;

		float width5 = width/2f;
		float height5 = height/2f;
    	
		int index = 0;
		for (row = 0; row <= segsH; row++)
		{
			for (col = 0; col <= segsW; col++)
			{
				number3dBufferList.set(index++, (float)col * w - width5, (float)row * h - height5, 0f);	
			}
		}
    }

    public void onHealthChange(int newX2)
    {
    	super.onHealthChange(newX2);

    	this.updateColor();

    	this.updateSize();
    }

    private final Object3dProcessorUtil object3dUtil = Object3dProcessorUtil.getInstance();

    public void paintThreed(Graphics graphics, int x, int y, int z)
    {
    	OpenGLESGraphics openGLESGraphics = (OpenGLESGraphics) graphics;
    	GL10 gl = openGLESGraphics.getGl();

    	ViewPosition viewPosition = this.allbinaryLayer.getViewPosition();

        Number3d positionNumber3d = this.rectangle.getPosition();

        positionNumber3d.x = -((float) viewPosition.getX() - this.allbinaryLayer.getHalfWidth() + (x2 >> 1) );
        // 
        positionNumber3d.z = -((float) viewPosition.getY() + (this.allbinaryLayer.getHalfHeight() + 1));
        //+ this.allbinaryLayer.getHalfHeight() + 1
        positionNumber3d.y = viewPosition.getZ() + 12;

        object3dUtil.drawObject(gl, this.rectangle);
    }

    /*
        private static int test = -60;
        private static final TimeDelayHelper timer = new TimeDelayHelper(1000);
        
        if(timer.isTime())
        {
        	if(test <= 60)
        	{
        		test++;
        	}
        	else
        	{
        		test = -60;
        	}
        	PreLogUtil.put(Integer.toString(test), this, this.toString());
        } 
     */

    //private final float[] fillRectangleFloatArray = new float[12];
    //private final FloatBuffer fillRectangleVertexFloatBuffer =
      //  FloatBuffer.wrap(fillRectangleFloatArray);
	
    //int ax = viewPosition.getX();
    //int y = this.getY() - 1;
    
    //graphics.fillRect(ax, this.getY() - 1, x2, this.thickness);
    
    /*
    for (int index = 0; index < this.thickness; index++)
    {
        graphics.drawLine(x, y - index, x + x2, y - index);
    }
    */
    
    /*
    int width = x2;
    int height = this.thickness;
    
    x = -(viewPosition.getX());
    z = -(viewPosition.getY());
    y = viewPosition.getZ();
    
    this.fillRectangleFloatArray[2] = z;
    this.fillRectangleFloatArray[5] = z;
    this.fillRectangleFloatArray[8] = z;
    this.fillRectangleFloatArray[11] = z;
    
    this.fillRectangleFloatArray[0] = x;
    //this.fillRectangleFloatArray[7] = DisplayInfoSingleton.getInstance().getLastHeight() - y;
    this.fillRectangleFloatArray[7] = y;
    this.fillRectangleFloatArray[1] = this.fillRectangleFloatArray[7] - height;
    
    this.fillRectangleFloatArray[3] = x + width;
    this.fillRectangleFloatArray[4] = this.fillRectangleFloatArray[1];
     
    this.fillRectangleFloatArray[6] = x;
     
    this.fillRectangleFloatArray[9] = this.fillRectangleFloatArray[3];
    this.fillRectangleFloatArray[10] = this.fillRectangleFloatArray[7];

    gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
    gl.glDisable(GL10.GL_TEXTURE_2D);
    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    
    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fillRectangleVertexFloatBuffer);

    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    */
    
}