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
package views.generic.inventory;

import java.util.Vector;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.inventory.item.BasicItemView;
import allbinary.business.user.commerce.inventory.item.ItemInterface;
import allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import allbinary.logic.control.search.SearchRequest;
import allbinary.logic.visual.transform.StoreTransformer;
import allbinary.logic.visual.transform.data.TransformDocumentInterface;
import allbinary.logic.visual.transform.data.TransformStoreDocumentFactory;
import allbinary.logic.visual.transform.info.TransformInfoHttpSearch;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

/**
 *
 * @author user
 */
public class InventorySearchProductUtil {
	
    private static final InventorySearchProductUtil instance = new InventorySearchProductUtil();

	public static InventorySearchProductUtil getInstance() {
		return instance;
	}

    private final String ITEM_NOT_FOUND = "Item Not Found.";
    
    public String getProduct(SearchRequest searchRequest, String product) throws Exception
    {
        try
        {
            InventoryEntity inventoryEntityInterface =
                InventoryEntityFactory.getInstance().getInventoryEntityInstance();

            ItemInterface itemInterface = inventoryEntityInterface.getItem(product);

            if (itemInterface != null)
            {
                TransformDocumentInterface viewDocumentInterface =
                    TransformStoreDocumentFactory.getInstance(searchRequest);

                viewDocumentInterface.getBaseNode().appendChild(
                    new BasicItemView(itemInterface, new Vector()).toXmlNode(
                    viewDocumentInterface.getDoc()));

                String success = DomDocumentHelper.toString(viewDocumentInterface.getDoc());

                String outputStr = new StoreTransformer(
                    (TransformInfoInterface) new TransformInfoHttpSearch(
                    searchRequest)).translate(success);

                return outputStr;
            }
            else
            {
            	return ITEM_NOT_FOUND;
                //throw new Exception();
            }
        }
        catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.PRODUCTSEARCHLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "getProduct", e));
            }
            throw new Exception("Failed to getProduct");
        }
    }

}
