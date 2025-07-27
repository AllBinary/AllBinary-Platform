package javax.microedition.lcdui;

import java.util.Stack;

public class NullCanvas extends Canvas 
{
    public static final Image NULL_IMAGE = new Image();
    public static final NullCanvas NULL_CANVAS = new NullCanvas();
    
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

    public synchronized boolean isCommand(Command command)
    {
        throw new RuntimeException();
    }

    @Override
    public synchronized void addCommand(Command command)
    {

    }

    @Override
    public synchronized void removeCommand(Command command)
    {
    }

    public synchronized void removeAllCommands()
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

    public synchronized boolean isPaused()
    {
        throw new RuntimeException();
    }

    public void removePauseCommand()
    {
        
    }

    public synchronized void pause()
    {
    }

    public synchronized void unPause()
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
