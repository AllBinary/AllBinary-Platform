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
package org.allbinary.business.context.modules.storefront.statistics;

import org.allbinary.business.context.modules.storefront.statistics.advertisements.StoreFrontAdvertisementsStatisticsInterface;
import org.allbinary.business.context.modules.storefront.statistics.advertisements.StoreFrontAdvertisementsStatisticsView;
import org.allbinary.business.context.modules.storefront.statistics.inventory.StoreFrontInventoryStatisticsInterface;
import org.allbinary.business.context.modules.storefront.statistics.inventory.StoreFrontInventoryStatisticsView;
import org.allbinary.business.context.modules.storefront.statistics.orders.history.StoreFrontOrdersHistoryStatisticsInterface;
import org.allbinary.business.context.modules.storefront.statistics.orders.history.StoreFrontOrdersHistoryStatisticsView;
import org.allbinary.business.context.modules.storefront.statistics.users.StoreFrontUsersStatisticsInterface;
import org.allbinary.business.context.modules.storefront.statistics.users.StoreFrontUsersStatisticsView;
import org.allbinary.business.user.role.UserRole;
import org.allbinary.business.user.role.UserRoleFactory;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class RealTimeStoreFrontStatisticsView
    implements DomNodeInterface
{

    private final StoreFrontStatisticsInterface storeFrontStatisticsInterface;
    private final UserRole userRole;

    public RealTimeStoreFrontStatisticsView(
        StoreFrontStatisticsInterface storeFrontStatisticsInterface, UserRole userRole)
        throws Exception
    {
        this.storeFrontStatisticsInterface = storeFrontStatisticsInterface;

        this.userRole = userRole;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(StoreFrontStatisticsData.getInstance().NAME);
        //HashMap hashMap = this.storeFrontStatisticsInterface.toHashMap();

        StoreFrontAdvertisementsStatisticsInterface storeFrontAdvertisementsStatisticsInterface =
            this.storeFrontStatisticsInterface.getAdvertisements();

        DomNodeInterface storeFrontAdvertisementsStatisticsDomNodeInterface =
            new StoreFrontAdvertisementsStatisticsView(
            storeFrontAdvertisementsStatisticsInterface);

        node.appendChild(storeFrontAdvertisementsStatisticsDomNodeInterface.toXmlNode(document));

        StoreFrontInventoryStatisticsInterface storeFrontInventoryStatisticsInterface =
            this.storeFrontStatisticsInterface.getInventory();

        DomNodeInterface storeFrontInventoryStatisticsDomNodeInterface =
            new StoreFrontInventoryStatisticsView(
            storeFrontInventoryStatisticsInterface);

        node.appendChild(storeFrontInventoryStatisticsDomNodeInterface.toXmlNode(document));

        StoreFrontOrdersHistoryStatisticsInterface storeFrontOrdersHistoryStatisticsInterface =
            this.storeFrontStatisticsInterface.getOrders();

        DomNodeInterface storeFrontOrdersHistoryStatisticsDomNodeInterface =
            new StoreFrontOrdersHistoryStatisticsView(
            storeFrontOrdersHistoryStatisticsInterface);

        node.appendChild(storeFrontOrdersHistoryStatisticsDomNodeInterface.toXmlNode(document));

        if (this.userRole == UserRoleFactory.getInstance().ADMINISTRATOR)
        {
            StoreFrontUsersStatisticsInterface storeFrontUsersStatisticsInterface =
                this.storeFrontStatisticsInterface.getUsers();

            DomNodeInterface storeFrontUsersStatisticsDomNodeInterface =
                new StoreFrontUsersStatisticsView(
                storeFrontUsersStatisticsInterface);

            node.appendChild(storeFrontUsersStatisticsDomNodeInterface.toXmlNode(document));
        }

        return node;
    }
}
