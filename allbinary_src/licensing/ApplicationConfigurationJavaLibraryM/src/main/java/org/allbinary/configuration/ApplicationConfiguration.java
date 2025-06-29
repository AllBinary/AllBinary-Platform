package org.allbinary.configuration;

import java.io.InputStream;
import java.io.OutputStream;

import org.allbinary.logic.io.AbDataInputStream;
import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.FileStreamFactory;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.FileFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.feature.Feature;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.MainFeatureFactory;

public class ApplicationConfiguration
{
    private static final ApplicationConfiguration instance = 
        new ApplicationConfiguration();

    /*
     * private boolean fullscreen = true; private boolean showTitleBar = false;
     * protected boolean progressBarView = true;
     */
    
    // Title progress bar works but SetProgress is now a portion of progress
    // instead of being a total value
    /*
     * private boolean fullscreen = true; private boolean showTitleBar = true;
     * protected boolean progressBarView = true;
     */

    private boolean fullscreen = true;
    private boolean showTitleBar = false;
    private boolean progressBarView = false;

    private static final String FILE = "ReloadConfiguration.dat";

    private ApplicationConfiguration()
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
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
        }
    }

    public static ApplicationConfiguration getInstance()
    {
        return instance;
    }

    private void read() throws Exception
    {
        FileStreamFactory fileInputStreamFactory = 
            FileStreamFactory.getInstance();

        InputStream fileInputStream =
            fileInputStreamFactory.getFileInputStreamInstance(
                    StringUtil.getInstance().EMPTY_STRING, FILE);

        AbDataInputStream dataInputStream =
            new AbDataInputStream(fileInputStream);

        int fullScreen = dataInputStream.readInt();
        if (fullScreen == 0)
        {
            this.setFullscreen(false);
        }
        else if (fullScreen == 1)
        {
            this.setFullscreen(true);
        }
        else
        {
            throw new Exception("Invalid FullScreen ActivityConfiguration");
        }

        int progressBarView = dataInputStream.readInt();
        if (progressBarView == 0)
        {
            this.setProgressBarView(false);
        }
        else if (progressBarView == 1)
        {
            this.setProgressBarView(true);
        }
        else
        {
            throw new Exception("Invalid ProgressBarView ActivityConfiguration");
        }

        int showTitleBar = dataInputStream.readInt();
        if (showTitleBar == 0)
        {
            this.setShowTitleBar(false);
        }
        else if (showTitleBar == 1)
        {
            this.setShowTitleBar(true);
        }
        else
        {
            throw new Exception("Invalid ShowTitleBar ActivityConfiguration");
        }

        //PreLogUtil.put("Read Configuration: " + this.toString(), this, "read");
        LogUtil.put(LogFactory.getInstance(
                "Read Configuration: " + this.toString(), this, "read"));
    }

    public void write() throws Exception
    {
        AbDataOutputStream dataOutputStream = null;
        try
        {
        //PreLogUtil.put("Write Configuration: " + this.toString(), this, "write");
        LogUtil.put(LogFactory.getInstance(
                "Write Configuration: " + this.toString(), this, "write"));
        
        FileStreamFactory fileInputStreamFactory = 
            FileStreamFactory.getInstance();

        OutputStream fileOutputStream = 
            fileInputStreamFactory.getFileOutputStreamInstance(
                    StringUtil.getInstance().EMPTY_STRING, FILE);

        dataOutputStream =
            new AbDataOutputStream(fileOutputStream);

        if (this.isFullscreen())
        {
            dataOutputStream.writeInt(1);
        }
        else
        {
            dataOutputStream.writeInt(0);
        }

        if (this.isProgressBarView())
        {
            dataOutputStream.writeInt(1);
        }
        else
        {
            dataOutputStream.writeInt(0);
        }

        if (this.isShowTitleBar())
        {
            dataOutputStream.writeInt(1);
        }
        else
        {
            dataOutputStream.writeInt(0);
        }
        
        dataOutputStream.flush();
        }
        finally
        {
            StreamUtil.getInstance().close(dataOutputStream);
        }
    }

    public void update(Feature gameFeature)
    throws Exception
    {
        if (gameFeature == MainFeatureFactory.getInstance().FULL_SCREEN)
        {
            Features features = Features.getInstance();
            
            if (features.isFeature(gameFeature))
            {
                if(!this.isFullscreen())
                {
                    this.setFullscreen(true);
                    this.write();
                }
            }
            else
            {
                if(this.isFullscreen())
                {
                    this.setFullscreen(false);
                    this.write();
                }
            }
        }
    }
    
    public void setFullscreen(boolean fullscreen)
    {
        this.fullscreen = fullscreen;
    }

    public boolean isFullscreen()
    {
        return fullscreen;
    }

    public void setShowTitleBar(boolean showTitleBar)
    {
        this.showTitleBar = showTitleBar;
    }

    public boolean isShowTitleBar()
    {
        return showTitleBar;
    }

    public void setProgressBarView(boolean progressBarView)
    {
        this.progressBarView = progressBarView;
    }

    public boolean isProgressBarView()
    {
        return progressBarView;
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("isFullscreen: ");
        stringBuffer.append(this.isFullscreen());

        stringBuffer.append(" isProgressBarView: ");
        stringBuffer.append(this.isProgressBarView());

        stringBuffer.append(" isShowTitleBar: ");
        stringBuffer.append(this.isShowTitleBar());

        return stringBuffer.toString();
    }
}
