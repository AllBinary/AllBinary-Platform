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
package abcs.logic.visual.media;

public class MediaDataFactory {

	private static final MediaDataFactory instance = new MediaDataFactory();
	
	public static MediaDataFactory getInstance() {
		return instance;
	}

	   public final MediaData AVI = new MediaData("avi");
	   public final MediaData BMP = new MediaData("bmp");
	   public final MediaData CEL = new MediaData("cel");
	   public final MediaData FITS = new MediaData("fits");
	   public final MediaData FLI = new MediaData("fli");
	   public final MediaData GBR = new MediaData("gbr");
	   public final MediaData GIF = new MediaData("gif");
	   public final MediaData GIH = new MediaData("gih");
	   public final MediaData JPG = new MediaData("jpg");
	   public final MediaData PIX = new MediaData("pix");
	   public final MediaData PNG = new MediaData("png");
	   public final MediaData PNM = new MediaData("pnm");
	   public final MediaData PSD = new MediaData("psd");
	   public final MediaData PSP = new MediaData("psp");
	   public final MediaData PS = new MediaData("ps");
	   public final MediaData SGI = new MediaData("SGI");
	   public final MediaData SWF = new MediaData("swf");
	   public final MediaData TGA = new MediaData("tga");
	   public final MediaData TIFF = new MediaData("tiff");
	   public final MediaData WMF = new MediaData("wmf");
}
