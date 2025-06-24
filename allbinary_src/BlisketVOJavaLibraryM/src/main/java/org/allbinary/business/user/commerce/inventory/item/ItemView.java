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
package org.allbinary.business.user.commerce.inventory.item;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import org.allbinary.business.user.address.StreetAddressData;

public class ItemView
{
    private final OrderItemInterface itemInterface;
    private final Vector vector;

    public ItemView(OrderItemInterface itemInterface, Vector vector)
    {
        this.itemInterface = itemInterface;
        this.vector = vector;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        HashMap hashMap = this.itemInterface.toHashMap();
        Set keySet = hashMap.keySet();

        Node node = document.createElement(ItemData.ITEM);

        BasicItemView basicItemView =
            new BasicItemView(this.itemInterface, vector);

        node.appendChild(basicItemView.toXmlNode(document));

        final StringUtil stringUtil = StringUtil.getInstance();
        
        final Object[] nameArray = keySet.toArray();
        final int size = nameArray.length;
        for (int index = 0; index < size; index++)
        {
            String name = (String) nameArray[index];
            String value = (String) hashMap.get(name);
            value = stringUtil.getInstance(value);
            node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));
        }

        Node shippingAddressNode = document.createElement(StreetAddressData.NAME);
        shippingAddressNode.appendChild(this.itemInterface.getShippingAddress().toXmlNode(document));

        return node;
    }
}
