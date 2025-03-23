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

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.commerce.inventory.item.BasicItemView;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.control.search.SearchRequest;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.StoreTransformer;
import org.allbinary.logic.visual.transform.data.TransformDocumentInterface;
import org.allbinary.logic.visual.transform.data.TransformStoreDocumentFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpSearch;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

/**
 *
 * @author user
 */
public class InventorySearchProductUtil {
	
    private static final InventorySearchProductUtil instance = new InventorySearchProductUtil();

    public static InventorySearchProductUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private final String ITEM_NOT_FOUND = "Item Not Found.";
    
    public String getProduct(final AbeClientInformationInterface abeClientInformation, SearchRequest searchRequest, String product) throws Exception
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

                String outputStr = new StoreTransformer(abeClientInformation,
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "getProduct", e));
            }
            throw new Exception("Failed to getProduct");
        }
    }

}
