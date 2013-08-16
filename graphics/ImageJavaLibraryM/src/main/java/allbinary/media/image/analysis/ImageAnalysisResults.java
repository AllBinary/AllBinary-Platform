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
package allbinary.media.image.analysis;

public class ImageAnalysisResults
{
    private ImageColorRangeResults imageColorRangeResults;
    private ImageColorResults imageColorResults;
    private float averageAmbientLevel;
    
    public ImageAnalysisResults()
    {
        this.imageColorRangeResults = new ImageColorRangeResults();
        this.imageColorResults = new ImageColorResults();
    }

    public ImageColorRangeResults getImageColorRangeResults()
    {
        return imageColorRangeResults;
    }

    public void setImageColorRangeResults(ImageColorRangeResults imageColorRangeResults)
    {
        this.imageColorRangeResults = imageColorRangeResults;
    }

    public float getAverageAmbientLevel()
    {
        return averageAmbientLevel;
    }

    public void setAverageAmbientLevel(float averageAmbientLevel)
    {
        this.averageAmbientLevel = averageAmbientLevel;
    }

    public ImageColorResults getImageColorResults()
    {
        return imageColorResults;
    }

    public void setImageColorResults(ImageColorResults imageColorResults)
    {
        this.imageColorResults = imageColorResults;
    }
    
}
