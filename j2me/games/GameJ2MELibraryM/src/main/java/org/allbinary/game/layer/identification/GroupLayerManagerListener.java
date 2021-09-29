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
package org.allbinary.game.layer.identification;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.identification.Group;
import org.allbinary.game.identification.GroupInterfaceCompositeInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.event.LayerManagerEvent;
import org.allbinary.layer.event.LayerManagerEventHandler;
import org.allbinary.layer.event.LayerManagerEventListener;
import org.allbinary.logic.basic.util.event.AllBinaryEventObject;
import org.allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GroupLayerManagerListener
extends LayerManagerEventListener
{
    private static GroupLayerManagerListener SINGLETON = 
        new GroupLayerManagerListener();

    private final BasicArrayList list = new BasicArrayList();

    public static GroupLayerManagerListener getInstance()
    {
        return SINGLETON;
    }

    private GroupLayerManagerListener()
    {
        LayerManagerEventHandler.getInstance().addListener(this);
    }

    public void clear()
    {
        int size = list.size();
        for (int index = size - 1; index >= 0; index--)
        {
            BasicArrayList groupList = (BasicArrayList) this.list.objectArray[index];
            groupList.clear();
        }
    }

    public int getGroupSize(GroupInterfaceCompositeInterface groupInterfaceCompositeInterface)
    {
        final Group[] groupInterfaceArray = groupInterfaceCompositeInterface.getGroupInterface();
        return this.getGroupSize(groupInterfaceArray[0]);
    }

    public int getGroupSize(Group groupInterface)
    {
        int id = groupInterface.getGroupId();
        return this.getGroupSize(id);
    }

    public BasicArrayList getList(Group groupInterface)
    {
        int id = groupInterface.getGroupId();
        return this.getList(id);
    }

    private BasicArrayList getList(int groupId)
    {
        BasicArrayList groupList = (BasicArrayList) this.list.objectArray[groupId];
        return groupList;
    }

    private int getGroupSize(int groupId)
    {
        BasicArrayList groupList = (BasicArrayList) this.list.objectArray[groupId];
        //if(groupList == null) {
            //LogUtil.put(LogFactory.getInstance("groupId: " + groupId, this, "getGroupSize"));
        //}
        int size = groupList.size();
        return size;
    }

    public boolean areAllOtherGroupsEmpty(Group groupInterface)
    {
        final int id = groupInterface.getGroupId();

        final int size = list.size();
        for (int index = size - 1; index >= 0; index--)
        {
            if (id != index)
            {
                int groupSize = this.getGroupSize(index);

                if (groupSize != 0)
                {
                    LogUtil.put(LogFactory.getInstance(
                        "Group Size: " + groupSize, this, "areAllOtherGroupsEmpty"));
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isIdInList(int id, BasicArrayList excludeGroupList)
    {
        final int size = excludeGroupList.size();
        Group groupInterface;
        for (int index = size - 1; index >= 0; index--)
        {
            groupInterface = (Group) excludeGroupList.objectArray[index];
            if(groupInterface.getGroupId() == id)
            {
                return true;
            }
        }
        return false;
    }

  //Note: The PlayerLayer could be in the group list so 1 might be the minimum
    public boolean areAllOtherGroupsLessThan(
            BasicArrayList excludeGroupList, int maxSize)
    {
        //final String GROUP_SIZE = "Group Size: ";
        //final String MORE_THAN = " >= ";
        
        //StringMaker stringBuffer = new StringMaker();
        
        final int size = list.size();
        for (int index = size - 1; index >= 0; index--)
        {
            if (!this.isIdInList(index, excludeGroupList))
            {
                int groupSize = this.getGroupSize(index);

                if (groupSize >= maxSize)
                {
                    //stringBuffer.delete(0, stringBuffer.length());

                    //stringBuffer.append(GROUP_SIZE);
                    //stringBuffer.append(groupSize);
                    //stringBuffer.append(MORE_THAN);
                    //stringBuffer.append(maxSize);

                    //BasicArrayList groupList = (BasicArrayList) this.list.get(index);
                    //stringBuffer.append(" -> ");
                    //stringBuffer.append(groupList);

                    //LogUtil.put(LogFactory.getInstance(
                      //      stringBuffer.toString(), this, "areAllOtherGroupsLessThan"));

                    return false;
                }
            }
        }
        return true;
    }

    public void init(int total)
    {
        while (this.list.size() <= total + 1)
        {
            this.list.add(new BasicArrayList());
        }
        
        //LogUtil.put(LogFactory.getInstance("size: " + total, this, "init"));
        
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    public void onCreateLayerManagerEvent(LayerManagerEvent layerManagerEvent)
        throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onLayerManagerEvent"));

        final AllBinaryLayer layerInterface = layerManagerEvent.getLayerInterface();

        //Ignore weapons
        /*
        if(!this.countWeapons && (layerInterface.getType() == WeaponLayer.getStaticType()))
        {
            return;
        }
        */
        
        final Group[] groupInterfaceArray =
            ((GroupInterfaceCompositeInterface) layerInterface).getGroupInterface();
        final int size = groupInterfaceArray.length;

        int id;
        BasicArrayList groupList;
        for(int index = 0; index < size; index++) {
            id = groupInterfaceArray[index].getGroupId();

            groupList = (BasicArrayList) this.list.objectArray[id];

            if(groupList == null) {
                LogUtil.put(LogFactory.getInstance("id: " + id, this, "onLayerManagerEvent"));
            }
            
            //if(Group.ENEMY.getGroupId() == id)

            if (!groupList.contains(layerInterface))
            {
                //if(layerInterface.getType() != WeaponLayer.getStaticType())
                    groupList.add(layerInterface);
                
                //Ignore removing weapon for logging -- to much logging
                /*
                if(layerInterface.getType() != WeaponLayer.getStaticType())
                {
                    StringMaker stringBuffer = new StringMaker();

                    stringBuffer.append("Adding: ");
                    stringBuffer.append(layerInterface.getName());
                    stringBuffer.append(" To Team: ");
                    stringBuffer.append(id);
                    stringBuffer.append(" with: ");
                    stringBuffer.append(groupList.size());
                    stringBuffer.append(" --> ");
                    stringBuffer.append(groupList);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "onLayerManagerEvent"));
                }
                */
            }
            else
            {
                //throw new Exception("layerInterface: " + layerInterface + " is already in group");
            }            
        }
    }

    public void onDeleteLayerManagerEvent(LayerManagerEvent layerManagerEvent)
            throws Exception
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START,
        // this, "onLayerManagerEvent"));

        AllBinaryLayer layerInterface = layerManagerEvent.getLayerInterface();

        // Ignore weapons
        // if(!this.countWeapons && (layerInterface.getType() ==
        // WeaponLayer.getStaticType()))
        // {
        // return;
        // }

        final Group[] groupInterfaceArray = 
                ((GroupInterfaceCompositeInterface) layerInterface).getGroupInterface();

        final int size = groupInterfaceArray.length;
        int id;
        BasicArrayList groupList;
        for(int index = 0; index < size; index++) {
            
            id = groupInterfaceArray[index].getGroupId();

            groupList = (BasicArrayList) this.list.objectArray[id];

            // if(Group.ENEMY.getGroupId() == id)
            // if(layerInterface.getType() != WeaponLayer.getStaticType())
            groupList.remove(layerInterface);

        // Ignore removing weapon for logging -- to much logging

        /*
        if(layerInterface.getType() != WeaponLayer.getStaticType())
        {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Removing: ");
            stringBuffer.append(layerInterface.getName());
            stringBuffer.append(" From Team: ");
            stringBuffer.append(id);
            stringBuffer.append(" with: ");
            stringBuffer.append(groupList.size());
            stringBuffer.append(" --> ");
            stringBuffer.append(groupList);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "onLayerManagerEvent"));
        }
        */        
            
        }        
    }

    public void log()
    {
        StringMaker stringBuffer = new StringMaker();
        
        final String GROUP = "Group: ";
        
        final String SPACE = CommonSeps.getInstance().SPACE;
        final String TOTAL_LABEL = CommonStrings.getInstance().TOTAL_LABEL;

        int size = list.size();
        for (int index = size - 1; index >= 0; index--)
        {
            BasicArrayList groupList = (BasicArrayList) this.list.objectArray[index];
            
            //stringBuffer.delete(0, stringBuffer.length());

            stringBuffer.append(GROUP);
            stringBuffer.append(index);
            stringBuffer.append(SPACE);
            stringBuffer.append(TOTAL_LABEL);
            stringBuffer.append(groupList.size());
            stringBuffer.append(SPACE);
            
            //LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "log"));
        }
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "log"));
    }
}
