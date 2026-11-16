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

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.allbinary.graphics.j2me.workarea.canvas.CanvasTreeLabel;
import org.allbinary.graphics.j2me.workarea.canvas.event.MyCanvasEvent;
import org.allbinary.graphics.j2me.workarea.canvas.event.MyCanvasEventService;
import org.allbinary.graphics.j2me.workarea.canvas.event.MyCanvasEventSource;
import org.allbinary.graphics.j2me.workarea.tools.event.MyGraphicItemEvent;
import org.allbinary.graphics.j2me.workarea.tools.event.MyGraphicItemEventService;
import org.allbinary.graphics.j2me.workarea.tools.event.MyGraphicItemEventSource;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class WorkAreaJTreeJPanel extends JPanel
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
   private DefaultMutableTreeNode rootTreeNode;
   private JTree workAreaJTree;
   private BasicArrayList highlightedBasicArrayList;

   public WorkAreaJTreeJPanel(String workAreaName)
   {
      this.initComponents();

      this.rootTreeNode = new DefaultMutableTreeNode(workAreaName);
      this.workAreaPropertiesJPanel.removeAll();
      this.updateTree();

      this.highlightedBasicArrayList = new BasicArrayListD();
   }

   public void add(MutableTreeNode treeNode)
   {
      this.rootTreeNode.add(treeNode);
   }

   public void updateTree()
   {
      this.workAreaJTree = new JTree(this.rootTreeNode);

      this.workAreaJTree.addMouseListener(new MouseAdapter()
      {

         public void mousePressed(MouseEvent evt)
         {
            workAreaJTreeMousePressed(evt);
         }
      });
      this.workAreaPropertiesJPanel.removeAll();
      this.workAreaPropertiesJPanel.add(this.workAreaJTree);
   }

   public BasicArrayList getNode(TreePath[] treePathArray)
   {
      BasicArrayList basicArrayList = new BasicArrayListD();
      if (treePathArray != null)
      {
         for (int index = 0; index < treePathArray.length; index++)
         {
            basicArrayList.add(this.getNode(treePathArray[index]));
         }
      }
      return basicArrayList;
   }

   public DefaultMutableTreeNode getNode(TreePath treePath)
   {
      if (treePath != null)
      {
         Object[] obj = treePath.getPath();
         if (obj != null)
         {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj[obj.length - 1];
            return node;
         }
      }
      return null;
   }

   public void deselectAll() throws Exception
   {
      int size = this.highlightedBasicArrayList.size();
      this.logUtil.putF("size: " + size, this, "deselectAll");

      for (int index = 0; index < size; index++)
      {
         DefaultMutableTreeNode node =
            (DefaultMutableTreeNode) this.highlightedBasicArrayList.get(index);
         MyGraphicItemEventService.fire(new MyGraphicItemEvent((Object) new MyGraphicItemEventSource(MyGraphicItemEventService.DESELECT, node)));
      }

      this.highlightedBasicArrayList.clear();
   }

   public void selectGraphicItem(DefaultMutableTreeNode node)
      throws Exception
   {
      this.highlightedBasicArrayList.add(node);
      MyGraphicItemEventService.fire(new MyGraphicItemEvent((Object) new MyGraphicItemEventSource(
         MyGraphicItemEventService.SELECT, node)));
   }

   public void removeGraphicItem(DefaultMutableTreeNode node)
      throws Exception
   {
      MyGraphicItemEventService.fire(new MyGraphicItemEvent((Object) new MyGraphicItemEventSource(
         MyGraphicItemEventService.DELETE, node)));
   }

   public void removeCanvas(DefaultMutableTreeNode node) throws Exception
   {
      MyCanvasEventService.fire(new MyCanvasEvent((Object) new MyCanvasEventSource(
         MyCanvasEventService.DELETE, node)));
   }

   public void remove(TreePath treePath) throws Exception
   {
      DefaultMutableTreeNode node = this.getNode(treePath);

      if (node != null)
      {
         if (node.getUserObject() instanceof CanvasTreeLabel)
         {
            this.removeCanvas(node);
         }
         else
         {
            this.removeGraphicItem(node);
         }
      }

      DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();

      parent.remove(node);
   }

   private void copy() throws Exception
   {
      TreePath treePath = this.workAreaJTree.getSelectionPath();
      this.copy(treePath);
      this.updateTree();
      this.expand();
      this.repaint();
   }

   private void copy(TreePath treePath) throws Exception
   {
      DefaultMutableTreeNode node = this.getNode(treePath);
      if (node != null)
      {
         if (node.getUserObject() instanceof CanvasTreeLabel)
         {
            MyCanvasEventService.fire(new MyCanvasEvent((Object) new MyCanvasEventSource(
               MyCanvasEventService.DUPLICATE, node)));
         }
         else
         {
            MyGraphicItemEventService.fire(new MyGraphicItemEvent((Object) new MyGraphicItemEventSource(
               MyGraphicItemEventService.DUPLICATE, node)));
         }
      }
   }

   public void rotate(TreePath treePath, double angle) throws Exception
   {
      DefaultMutableTreeNode node = this.getNode(treePath);
      if (node != null)
      {
         if (node.getUserObject() instanceof CanvasTreeLabel)
         {
            MyCanvasEventService.fire(new MyCanvasEvent((Object) new MyCanvasEventSource(
               MyCanvasEventService.ROTATE, angle, node)));
         }
         else
         {
            MyGraphicItemEventService.fire(new MyGraphicItemEvent((Object) new MyGraphicItemEventSource(
               MyGraphicItemEventService.ROTATE, angle, node)));
         }
      }
   }

   public MutableTreeNode getRootTreeNode()
   {
      return this.rootTreeNode;
   }

   public void expand()
   {
      this.workAreaJTree.expandRow(0);
      this.workAreaJTree.expandRow(1);
   }

   public void expand(TreePath treePath)
   {
      this.workAreaJTree.expandPath(treePath);
   }

   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      itemJPopupMenu = new JPopupMenu();
      this.deleteJMenuItem = new JMenuItem();
      this.copyJMenuItem = new JMenuItem();
      this.rotateJMenuItem = new JMenuItem();
      this.itemJScrollPane1 = new JScrollPane();
      this.workAreaPropertiesJPanel = new JPanel();

      itemJPopupMenu.addPopupMenuListener(new PopupMenuListener() {
         public void popupMenuCanceled(PopupMenuEvent evt) {
         }
         public void popupMenuWillBecomeInvisible(PopupMenuEvent evt) {
            itemJPopupMenuPopupMenuWillBecomeInvisible(evt);
         }
         public void popupMenuWillBecomeVisible(PopupMenuEvent evt) {
            itemJPopupMenuPopupMenuWillBecomeVisible(evt);
         }
      });

      this.deleteJMenuItem.setText("Delete");
      this.deleteJMenuItem.addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent evt) {
            deleteJMenuItemMousePressed(evt);
         }
      });
      this.itemJPopupMenu.add(this.deleteJMenuItem);

      this.copyJMenuItem.setText("Copy");
      this.copyJMenuItem.addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent evt) {
            copyJMenuItemMousePressed(evt);
         }
      });
      this.itemJPopupMenu.add(this.copyJMenuItem);

      this.rotateJMenuItem.setText("Rotate 45*");
      this.rotateJMenuItem.addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent evt) {
            rotateJMenuItemMousePressed(evt);
         }
      });
      this.itemJPopupMenu.add(this.rotateJMenuItem);

      setLayout(new GridLayout(1, 1));

      this.workAreaPropertiesJPanel.addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent evt) {
            workAreaPropertiesJPanelMousePressed(evt);
         }
      });
      this.workAreaPropertiesJPanel.setLayout(new GridLayout(1, 0));
      this.itemJScrollPane1.setViewportView(this.workAreaPropertiesJPanel);

      add(this.itemJScrollPane1);
   }// </editor-fold>//GEN-END:initComponents

   private void rotateJMenuItemMousePressed(MouseEvent evt)//GEN-FIRST:event_rotateJMenuItemMousePressed
   {//GEN-HEADEREND:event_rotateJMenuItemMousePressed
      try
      {
         TreePath treePath = this.workAreaJTree.getSelectionPath();
         this.rotate(treePath, 45);
         this.updateTree();
         this.expand();
         this.repaint();
      }
      catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "rotateJMenuItemMousePressed", e);
      }
   }//GEN-LAST:event_rotateJMenuItemMousePressed

   private void workAreaJTreeMousePressed(MouseEvent evt)//GEN-FIRST:event_workAreaJTreeMousePressed
   {//GEN-HEADEREND:event_workAreaJTreeMousePressed
      try
      {
         TreePath[] selectedTreePathArray = this.workAreaJTree.getSelectionPaths();
         BasicArrayList list = this.getNode(selectedTreePathArray);

         if (list.size() > 0)
         {
            if ((evt.getModifiers() & evt.BUTTON3_MASK) == evt.BUTTON3_MASK)
            {
               for (int index = 0; index < selectedTreePathArray.length; index++)
               {
                  DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) list.get(index);
                  if (defaultMutableTreeNode.getUserObject() instanceof CanvasTreeLabel)
                  {
                     this.itemJPopupMenu.show(this, evt.getX(), evt.getY());
                  }
                  else
                  {
                     this.itemJPopupMenu.show(this, evt.getX(), evt.getY());
                  }
               }
            }

            if ((evt.getModifiers() & evt.BUTTON1_MASK) == evt.BUTTON1_MASK)
            {
               this.deselectAll();

               for (int index = 0; index < selectedTreePathArray.length; index++)
               {
                  DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) list.get(index);
                  if (defaultMutableTreeNode.getUserObject() instanceof CanvasTreeLabel)
                  {
                     MyCanvasEventService.fire(new MyCanvasEvent((Object) new MyCanvasEventSource(MyCanvasEventService.SELECT, defaultMutableTreeNode)));
                  }
                  else
                  {
                     this.selectGraphicItem(defaultMutableTreeNode);
                  }
               }
            }
         }
      }
      catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "workAreaJTreeMousePressed", e);
      }
   }//GEN-LAST:event_workAreaJTreeMousePressed

   private void itemJPopupMenuPopupMenuWillBecomeInvisible(PopupMenuEvent evt)//GEN-FIRST:event_itemJPopupMenuPopupMenuWillBecomeInvisible
   {//GEN-HEADEREND:event_itemJPopupMenuPopupMenuWillBecomeInvisible
   }//GEN-LAST:event_itemJPopupMenuPopupMenuWillBecomeInvisible

   private void itemJPopupMenuPopupMenuWillBecomeVisible(PopupMenuEvent evt)//GEN-FIRST:event_itemJPopupMenuPopupMenuWillBecomeVisible
   {//GEN-HEADEREND:event_itemJPopupMenuPopupMenuWillBecomeVisible
   }//GEN-LAST:event_itemJPopupMenuPopupMenuWillBecomeVisible

   private void copyJMenuItemMousePressed(MouseEvent evt)//GEN-FIRST:event_copyJMenuItemMousePressed
   {//GEN-HEADEREND:event_copyJMenuItemMousePressed
      try
      {
         this.copy();
      }
      catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "copyJMenuItemMousePressed", e);
      }
   }//GEN-LAST:event_copyJMenuItemMousePressed

   private void deleteJMenuItemMousePressed(MouseEvent evt)//GEN-FIRST:event_deleteJMenuItemMousePressed
   {//GEN-HEADEREND:event_deleteJMenuItemMousePressed
      try
      {
         TreePath[] selectedTreePathArray = this.workAreaJTree.getSelectionPaths();

         for (int index = 0; index < selectedTreePathArray.length; index++)
         {
            TreePath treePath = selectedTreePathArray[index];
            this.remove(treePath);
            this.updateTree();
            this.expand();
            this.repaint();
         }
      }
      catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "deleteJMenuItemMousePressed", e);
      }
   }//GEN-LAST:event_deleteJMenuItemMousePressed

   private void workAreaPropertiesJPanelMousePressed(MouseEvent evt) {//GEN-FIRST:event_workAreaPropertiesJPanelMousePressed
   }//GEN-LAST:event_workAreaPropertiesJPanelMousePressed
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private JMenuItem copyJMenuItem;
   private JMenuItem deleteJMenuItem;
   private JPopupMenu itemJPopupMenu;
   private JScrollPane itemJScrollPane1;
   private JMenuItem rotateJMenuItem;
   private JPanel workAreaPropertiesJPanel;
   // End of variables declaration//GEN-END:variables
}
