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
package org.allbinary.graphics.j2me.workarea;

import java.awt.*;
import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.tree.MutableTreeNode;

import org.allbinary.dom.DomHelper;
import org.allbinary.graphics.j2me.StatusFactory;
import org.allbinary.graphics.j2me.workarea.canvas.CanvasDom;
import org.allbinary.graphics.j2me.workarea.canvas.CanvasJPanel;
import org.allbinary.graphics.j2me.workarea.canvas.event.MyCanvasEvent;
import org.allbinary.graphics.j2me.workarea.canvas.event.MyCanvasEventListener;
import org.allbinary.graphics.j2me.workarea.canvas.event.MyCanvasEventService;
import org.allbinary.graphics.j2me.workarea.properties.PropertiesJPanel;
import org.allbinary.graphics.j2me.workarea.tools.ToolJPanel;
import org.allbinary.log.LOGGING;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class WorkAreaJPanel
        extends JPanel
        implements WorkAreaJPanelInterface, MyCanvasEventListener, EventListener, Runnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //linked list of CanvasJPanels

    private LinkedList canvasJPanelList;
    private JPanel canvasHolderJPanel;
    private String name;
    private int selectedFrame;
    private static org.w3c.dom.Document document;
    private PropertiesJPanel propertiesJPanel;
    private WorkAreaJTreeJPanel workAreaJTreeJPanel;
    //workarea animation
    private boolean isPlaying = false;

    public WorkAreaJPanel(String name, Dimension dimension, int x, int y) throws Exception
    {
        try
        {
            initComponents();

            this.name = name;

            initMyComponents(dimension);

            this.workAreaJTreeJPanel = new WorkAreaJTreeJPanel(this.name);

            CanvasJPanel canvasJPanel =
                    new CanvasJPanel(this.workAreaJTreeJPanel, this.getSize(), x, y);

            this.canvasJPanelList.add(canvasJPanel);

            this.selectedFrame = 0;

            this.propertiesJPanel.set(this.workAreaJTreeJPanel);
            this.canvasHolderJPanel.add((Component) canvasJPanel);

        } catch (Exception e)
        {
            throw e;
        }
    }

    public WorkAreaJPanel(WorkAreaDom workAreaDom, Dimension dimension) throws Exception
    {
        try
        {
            initComponents();


            this.name = workAreaDom.getName();
            initMyComponents(dimension);

            BasicArrayList canvasNodeList = workAreaDom.getCanvasNodes();
            int numberOfFrames = canvasNodeList.size();

            for (int index = 0; index < numberOfFrames; index++)
            {
                Node node = (Node) canvasNodeList.get(index);

                CanvasJPanel canvasJPanel =
                        new CanvasJPanel(this.workAreaJTreeJPanel,
                        this.getSize(),
                        new CanvasDom(node));

                this.canvasJPanelList.add(canvasJPanel);
            }

            this.selectedFrame = 0;

            this.canvasHolderJPanel.add(
                    (Component) this.getCurrentFrame());

            this.propertiesJPanel.set(this.workAreaJTreeJPanel);
        } catch (Exception e)
        {
            if (LOGGING.contains(LOGGING.LOADINGERROR))
            {
                logUtil.put("Constructor Error", this, "WorkAreaJPanel", e);
            }

            throw e;
        }
    }

    private void initMyComponents(Dimension dimension)
    {
        this.workAreaJTreeJPanel = new WorkAreaJTreeJPanel(this.name);

        this.canvasHolderJPanel = new JPanel();
        this.canvasHolderJPanel.setSize(dimension);
        this.canvasHolderJPanel.setLayout(new GridLayout(1, 1));

        this.setSize(dimension);

        this.canvasJPanelList = new LinkedList();

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.innerJPanel.setLayout(gridBagLayout);
        //this.setLayout(new GridLayout(1,1));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        //gridBagConstraints.gridheight =;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = .05;
        gridBagConstraints.weighty = .05;

        ToolJPanel toolJPanel = new ToolJPanel();
        gridBagLayout.setConstraints(toolJPanel, gridBagConstraints);
        this.innerJPanel.add(toolJPanel);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        //gridBagConstraints.gridheight =400;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;

        gridBagLayout.setConstraints(canvasHolderJPanel, gridBagConstraints);
        this.innerJPanel.add(canvasHolderJPanel);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        //gridBagConstraints.gridheight =400;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = .1;
        gridBagConstraints.weighty = .1;

        propertiesJPanel = new PropertiesJPanel();//this);
        gridBagLayout.setConstraints(propertiesJPanel, gridBagConstraints);
        this.innerJPanel.add(propertiesJPanel);

        MyCanvasEventService.addListener((MyCanvasEventListener) this);
    }

    //hashMap keeps track of what jtree node goes with a specific canvas
    private HashMap getCanvasHashMap()
    {
        final HashMap canvasJPanelHashMap = new HashMap();
        final int size = this.canvasJPanelList.size();
        for (int index = 0; index < size; index++)
        {
            final CanvasJPanel canvasJPanel = (CanvasJPanel) this.canvasJPanelList.get(index);
            canvasJPanelHashMap.put(canvasJPanel.getTreeNode(), new Integer(index));
        }
        return canvasJPanelHashMap;
    }

    public synchronized void play()
    {
        isPlaying = true;
    }

    public synchronized void stop()
    {
        isPlaying = false;
    }

    public synchronized boolean isPlaying()
    {
        return isPlaying;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String value)
    {
        this.name = value;
    }

    public void disableCanvas()
    {
        this.canvasHolderJPanel.removeAll();
    }

    public void enableCanvas()
    {
        this.canvasHolderJPanel.add((Component) this.getCurrentFrame());
    }

    public void deselect()
    {
        super.setVisible(false);
    }

    public void select()
    {
        super.setVisible(true);
        if (this.canvasHolderJPanel.getComponentCount() == 1)
        {
            final Component component = this.canvasHolderJPanel.getComponent(0);
            if (component != null)
            {
                component.repaint();
            }
        }
    }

    //zoom all canvas/frame
    public void changeZoom(int factor)
    {
        final int size = canvasJPanelList.size();
        for (int index = 0; index < size; index++)
        {
            CanvasJPanel canvasJPanel = (CanvasJPanel) canvasJPanelList.get(index);
            canvasJPanel.getGrid().setZoom(canvasJPanel.getGrid().getZoom() + factor);
        }
    }

    public CanvasJPanel getCurrentFrame()
    {
        return (CanvasJPanel) this.canvasJPanelList.get(this.selectedFrame);
    }

    private void initDuplicate(CanvasJPanel newCanvasJPanel)
    {
        if (newCanvasJPanel != null)
        {
            this.canvasJPanelList.add(newCanvasJPanel);
            this.select(this.canvasJPanelList.size() - 1);
        }
    }

    private void duplicate(MutableTreeNode treeNode)
            throws Exception
    {
        HashMap canvasJPanelHashMap = this.getCanvasHashMap();
        if (canvasJPanelHashMap.containsKey(treeNode))
        {
            Integer frameInteger = (Integer) canvasJPanelHashMap.get(treeNode);
            CanvasJPanel canvasJPanel = (CanvasJPanel) this.canvasJPanelList.get(frameInteger.intValue());
            initDuplicate(canvasJPanel.duplicate());
        }
    }

    public void autoRotate(int increments, int totalAngle)
            throws Exception
    {
        int incrementAngle = (int) (totalAngle / increments);
        for (int index = 1; index < increments; index++)
        {
            CanvasJPanel canvasJPanel = (CanvasJPanel) this.getCurrentFrame();
            CanvasJPanel newCanvasJPanel = canvasJPanel.duplicate();
            newCanvasJPanel.setAngle(incrementAngle * index);
            initDuplicate(newCanvasJPanel);
        }
    }

    public void explodeAll()
    {
        CanvasJPanel canvasJPanel = (CanvasJPanel) this.getCurrentFrame();
        //make sure all graphic items are reduced to the min number of points
        canvasJPanel.explodeAll();
    }

    public void autoExplode(int numberOfFrames, int explodeType)
            throws Exception
    {
        for (int index = 1; index < numberOfFrames; index++)
        {
            CanvasJPanel canvasJPanel = (CanvasJPanel) this.getCurrentFrame();
            CanvasJPanel newCanvasJPanel = canvasJPanel.duplicate();
            newCanvasJPanel.explode(index + 1, explodeType);
            initDuplicate(newCanvasJPanel);
        }
    }

    public void autoMirror()
            throws Exception
    {
        CanvasJPanel canvasJPanel = (CanvasJPanel) this.getCurrentFrame();
        CanvasJPanel newCanvasJPanel = canvasJPanel.duplicate();
        newCanvasJPanel.mirror();
        initDuplicate(newCanvasJPanel);
    }

    private synchronized void select(int index)
    {
        if (index < this.canvasJPanelList.size())
        {
            CanvasJPanel canvasJPanel = (CanvasJPanel) this.canvasJPanelList.get(index);
            if (canvasJPanel != null)
            {
                this.canvasHolderJPanel.removeAll();
                this.canvasHolderJPanel.add(canvasJPanel);
                this.selectedFrame = index;
                canvasJPanel.changed();
                this.canvasHolderJPanel.repaint();
                canvasJPanel.repaint();
            }
        }
    }

    private void select(MutableTreeNode treeNode)
    {
        HashMap canvasJPanelHashMap = this.getCanvasHashMap();
        Integer frameInteger = (Integer) canvasJPanelHashMap.get(treeNode);
        if (frameInteger != null)
        {
            this.select(frameInteger.intValue());
        }
    }

    //dom methods excluding constructor
    public static Document getDocument()
    {
        return document;
    }

    public Document toDocument() throws Exception
    {
        document = DomHelper.getInstance().createDocument();

        Node workAreaNode = document.createElement(WorkAreaDom.WORKAREA);

        Node nameNode = document.createElement(WorkAreaDom.NAME);
        Node nameTextNode = document.createTextNode(this.name);
        nameNode.appendChild(nameTextNode);

        Node framesNode = document.createElement(WorkAreaDom.FRAMES);

        final int size = canvasJPanelList.size();
        for (int index = 0; index < size; index++)
        {
            CanvasJPanel canvasJPanel = (CanvasJPanel) canvasJPanelList.get(index);
            framesNode.appendChild(canvasJPanel.toDom());
        }

        workAreaNode.appendChild(nameNode);
        workAreaNode.appendChild(framesNode);

        document.appendChild(workAreaNode);

        return document;
    }

   private void initComponents()//GEN-BEGIN:initComponents
   {
      innerJPanel = new javax.swing.JPanel();

      setLayout(new java.awt.GridLayout(1, 1));

      innerJPanel.addKeyListener(new java.awt.event.KeyAdapter()
      {
         public void keyTyped(java.awt.event.KeyEvent evt)
         {
            innerJPanelKeyTyped(evt);
         }
         public void keyPressed(java.awt.event.KeyEvent evt)
         {
            innerJPanelKeyPressed(evt);
         }
         public void keyReleased(java.awt.event.KeyEvent evt)
         {
            innerJPanelKeyReleased(evt);
         }
      });

      add(innerJPanel);

   }//GEN-END:initComponents

    //event methods
   private void innerJPanelKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_innerJPanelKeyReleased
   {//GEN-HEADEREND:event_innerJPanelKeyReleased
       // Add your handling code here:
       StatusFactory.getInstance().setStatus("Key Pressed1");
   }//GEN-LAST:event_innerJPanelKeyReleased

   private void innerJPanelKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_innerJPanelKeyPressed
   {//GEN-HEADEREND:event_innerJPanelKeyPressed
       // Add your handling code here:
       StatusFactory.getInstance().setStatus("Key Pressed2");
   }//GEN-LAST:event_innerJPanelKeyPressed

   private void innerJPanelKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_innerJPanelKeyTyped
   {//GEN-HEADEREND:event_innerJPanelKeyTyped
       // Add your handling code here:
       StatusFactory.getInstance().setStatus("Key Pressed3");
   }//GEN-LAST:event_innerJPanelKeyTyped

   public void keyPressed(java.awt.event.KeyEvent keyEvent)
   {
       this.getCurrentFrame().keyPressed(keyEvent);
   }

   public void keyReleased(java.awt.event.KeyEvent keyEvent)
   {
   }

   public void keyTyped(java.awt.event.KeyEvent keyEvent)
   {
   }

    public void delete(MyCanvasEvent evt)
    {
        HashMap canvasJPanelHashMap = this.getCanvasHashMap();

        if (canvasJPanelHashMap.containsKey(evt.getTreeNode()))
        {
            this.canvasHolderJPanel.removeAll();

            Integer frameInt = (Integer) canvasJPanelHashMap.get(evt.getTreeNode());
            this.canvasJPanelList.remove(frameInt.intValue());

            this.select(frameInt.intValue());
        }
    }

    public void duplicate(MyCanvasEvent evt)
            throws Exception
    {
        this.duplicate(evt.getTreeNode());
    }

    public void select(MyCanvasEvent evt)
    {
        this.select(evt.getTreeNode());
        StatusFactory.getInstance().setStatus("Frame Selected");
    }

    public void rotate(MyCanvasEvent evt)
    {
        HashMap canvasJPanelHashMap = this.getCanvasHashMap();
        MutableTreeNode treeNode = evt.getTreeNode();

        Integer frameInt = (Integer) canvasJPanelHashMap.get(evt.getTreeNode());

        CanvasJPanel canvasJPanel =
                (CanvasJPanel) this.canvasJPanelList.get(frameInt.intValue());

        if (canvasJPanel != null)
        {
            canvasJPanel.addAngle(evt.getAngle());
            this.select(frameInt.intValue());
        }
        StatusFactory.getInstance().setStatus("Canvas Rotated");
    }

    public void explode(MyCanvasEvent evt)
    {
        this.explodeAll();
    }

    public void autoExplode(MyCanvasEvent evt)
            throws Exception
    {
        this.autoExplode(10, 0);
    }

    public void center() throws Exception
    {
        CanvasJPanel canvasJPanel = (CanvasJPanel) this.getCurrentFrame();
        canvasJPanel.center();
    }

    public void center(MyCanvasEvent evt)
            throws Exception
    {
        this.center();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                if (this.isPlaying())
                {
                    final int size = this.canvasJPanelList.size();
                    for (int index = 0; index < size; index++)
                    {
                        if (!this.isPlaying())
                        {
                            break;
                        }
                        this.select(index);
                        Thread.sleep(100);
                    }
                } else
                {
                    Thread.sleep(500);
                }
                Thread.sleep(250);
            }
        } catch (Exception e)
        {
        }
    }
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JPanel innerJPanel;
   // End of variables declaration//GEN-END:variables
}
