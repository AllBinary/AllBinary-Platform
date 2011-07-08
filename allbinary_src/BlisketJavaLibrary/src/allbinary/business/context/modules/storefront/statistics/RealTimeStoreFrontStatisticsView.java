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
package allbinary.business.context.modules.storefront.statistics;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import allbinary.business.context.modules.storefront.statistics.advertisements.StoreFrontAdvertisementsStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.advertisements.StoreFrontAdvertisementsStatisticsView;
import allbinary.business.context.modules.storefront.statistics.inventory.StoreFrontInventoryStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.inventory.StoreFrontInventoryStatisticsView;
import allbinary.business.context.modules.storefront.statistics.orders.history.StoreFrontOrdersHistoryStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.orders.history.StoreFrontOrdersHistoryStatisticsView;
import allbinary.business.context.modules.storefront.statistics.users.StoreFrontUsersStatisticsInterface;
import allbinary.business.context.modules.storefront.statistics.users.StoreFrontUsersStatisticsView;
import allbinary.business.user.role.UserRole;
import allbinary.business.user.role.UserRoleFactory;
import allbinary.data.tree.dom.DomNodeInterface;

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
