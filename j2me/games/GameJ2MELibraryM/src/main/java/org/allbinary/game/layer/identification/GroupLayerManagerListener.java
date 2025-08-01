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

import org.allbinary.game.identification.Group;
import org.allbinary.game.identification.GroupInterfaceCompositeInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.event.LayerManagerEvent;
import org.allbinary.layer.event.LayerManagerEventHandler;
import org.allbinary.layer.event.LayerManagerEventListener;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;

public class GroupLayerManagerListener
extends LayerManagerEventListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
        BasicArrayList groupList;
        int size = list.size();
        for (int index = size - 1; index >= 0; index--)
        {
            groupList = (BasicArrayList) this.list.objectArray[index];
            groupList.clear();
        }
    }

    public int getGroupSize(final GroupInterfaceCompositeInterface groupInterfaceCompositeInterface)
    {
        final Group[] groupInterfaceArray = groupInterfaceCompositeInterface.getGroupInterface();
        return this.getGroupSize(groupInterfaceArray[0]);
    }

    public int getGroupSize(final Group groupInterface)
    {
        final int id = (int) groupInterface.getGroupId();
        return this.getGroupSize(id);
    }

    public BasicArrayList getList(final Group groupInterface)
    {
        final int id = (int) groupInterface.getGroupId();
        return this.getList(id);
    }

    private BasicArrayList getList(final int groupId)
    {
        final BasicArrayList groupList = (BasicArrayList) this.list.objectArray[groupId];
        return groupList;
    }

    private int getGroupSize(final int groupId)
    {
        final BasicArrayList groupList = (BasicArrayList) this.list.objectArray[groupId];
        //if(groupList == null) {
            //logUtil.put("groupId: ").append(groupId, this, "getGroupSize");
        //}
        final int size = groupList.size();
        return size;
    }

    public boolean areAllOtherGroupsEmpty(final Group groupInterface)
    {
        final int id = (int) groupInterface.getGroupId();

        final int size = list.size();
        for (int index = size - 1; index >= 0; index--)
        {
            if (id != index)
            {
                int groupSize = this.getGroupSize(index);

                if (groupSize != 0)
                {
                    logUtil.put(
                        new StringMaker().append("Group Size: ").append(groupSize).toString(), this, "areAllOtherGroupsEmpty");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isIdInList(final int id, final BasicArrayList excludeGroupList)
    {
        final int size = excludeGroupList.size();
        Group[] groupInterfaceArray;
        Group groupInterface;
        int groupId;
        for (int index = size - 1; index >= 0; index--)
        {
            groupInterfaceArray = (Group[]) excludeGroupList.objectArray[index];
            final int size2 = groupInterfaceArray.length;
            for(int index2 = 0; index2 < size2; index2++) {
                groupInterface = groupInterfaceArray[index2];
                groupId = (int) groupInterface.getGroupId();
                if (groupId == id) {
                    return true;
                }
            }
        }
        return false;
    }

  //Note: The PlayerLayer could be in the group list so 1 might be the minimum
    public boolean areAllOtherGroupsLessThan(
            final BasicArrayList excludeGroupList, final int maxSize)
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

                    //logUtil.put(
                      //      stringBuffer.toString(), this, "areAllOtherGroupsLessThan");

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
        
        //logUtil.put("size: ").append(total, this, this.commonStrings.INIT);
        
    }

    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    @Override
    public void onCreateLayerManagerEvent(final LayerManagerEvent layerManagerEvent)
        throws Exception
    {

        final AllBinaryLayer layerInterface = layerManagerEvent.getLayerInterface();

        //logUtil.put(commonStrings.START, this, "onCreateLayerManagerEvent: ").append(layerInterface.toString());
        
        //Ignore weapons
        /*
        if(!this.countWeapons && (layerInterface.getType() == WeaponLayer.getStaticType()))
        {
            return;
        }
        */
        
        final Group[] groupInterfaceArray = layerInterface.getGroupInterface();
        final int size = groupInterfaceArray.length;

        int id;
        BasicArrayList groupList;
        for(int index = 0; index < size; index++) {
            id = (int) groupInterfaceArray[index].getGroupId();

            groupList = (BasicArrayList) this.list.objectArray[id];

            if(groupList == null) {
                logUtil.put(new StringMaker().append("id: ").append(id).toString(), this, "onCreateLayerManagerEvent");
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

                    logUtil.put(stringBuffer.toString(), this, "onCreateLayerManagerEvent");
                }
                */
            }
            else
            {
                //throw new Exception("layerInterface: ").append(layerInterface).append(" is already in group");
            }            
        }
    }

    @Override
    public void onDeleteLayerManagerEvent(final LayerManagerEvent layerManagerEvent)
            throws Exception
    {
        //logUtil.put(commonStrings.START, this, "onDeleteLayerManagerEvent");

        final AllBinaryLayer layerInterface = layerManagerEvent.getLayerInterface();

        // Ignore weapons
        // if(!this.countWeapons && (layerInterface.getType() ==
        // WeaponLayer.getStaticType()))
        // {
        // return;
        // }

        final Group[] groupInterfaceArray = layerInterface.getGroupInterface();

        final int size = groupInterfaceArray.length;
        int id;
        BasicArrayList groupList;
        for(int index = 0; index < size; index++) {
            
            id = (int) groupInterfaceArray[index].getGroupId();

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

            logUtil.put(stringBuffer.toString(), this, "onDeleteLayerManagerEvent");
        }
        */        
            
        }        
    }

    public void log()
    {
        final StringMaker stringBuffer = new StringMaker();
        
        final String GROUP = "Group: ";
        
        final String SPACE = CommonSeps.getInstance().SPACE;
        final String TOTAL_LABEL = CommonLabels.getInstance().TOTAL_LABEL;

        int size = list.size();
        for (int index = size - 1; index >= 0; index--)
        {
            final BasicArrayList groupList = (BasicArrayList) this.list.objectArray[index];
            
            //stringBuffer.delete(0, stringBuffer.length());

            stringBuffer.append(GROUP);
            stringBuffer.append(index);
            stringBuffer.append(SPACE);
            stringBuffer.append(TOTAL_LABEL);
            stringBuffer.append(groupList.size());
            stringBuffer.append(SPACE);
            
            //logUtil.put(stringBuffer.toString(), this, "log");
        }
        logUtil.put(stringBuffer.toString(), this, "log");
    }
}
