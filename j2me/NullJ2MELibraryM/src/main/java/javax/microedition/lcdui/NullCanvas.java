package javax.microedition.lcdui;

import java.util.Stack;
import org.allbinary.logic.string.StringUtil;

public class NullCanvas extends Canvas 
{
    public static final Image[] NULL_IMAGE_ARRAY = new Image[0];
    public static final Image NULL_IMAGE = new Image();
    public static final NullCanvas NULL_CANVAS = new NullCanvas();
    public static final Form NULL_SCREEN = new Form(StringUtil.getInstance().EMPTY_STRING);
    
    public NullCanvas()
    {
    }

    @Override
    public void setFullScreenMode(boolean mode)
    {
    }

    @Override
    protected void sizeChanged(int w, int h)
    {
    }
    
    public Stack<Object> getCommandStack()
    {
        throw new RuntimeException();
    }

    public boolean isCommand(Command command)
    {
        throw new RuntimeException();
    }

    @Override
    public void addCommand(Command command)
    {

    }

    @Override
    public void removeCommand(Command command)
    {
    }

    public void removeAllCommands()
    {
    }
    
    @Override
    public void setCommandListener(CommandListener l)
    {
    }

    public CommandListener getCustomCommandListener()
    {
        throw new RuntimeException();
    }

    public boolean isPaused()
    {
        throw new RuntimeException();
    }

    public void removePauseCommand()
    {
        
    }

    public void pause()
    {
    }

    public void unPause()
    {
    }

    protected void process() throws Exception
    {
    }
    
    @Override
    protected void paint(Graphics graphics)
    {
    }
    
    public void destroy()
    {
        
    }

    protected void setPaused(boolean isPaused)
    {
        throw new RuntimeException();
    }

    @Override
    protected void pointerDragged(int x, int y)
    {
    }

    @Override
    protected void pointerPressed(int x, int y)
    {
    }

    @Override
    protected void pointerReleased(int x, int y)
    {
        
    }
    
}
