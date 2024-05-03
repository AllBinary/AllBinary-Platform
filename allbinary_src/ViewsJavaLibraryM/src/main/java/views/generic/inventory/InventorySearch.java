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


import java.util.HashMap;
import java.util.Vector;

import views.admin.inventory.listings.InventoryViewSearchInterface;
import org.allbinary.logic.io.InputOutputTypeData;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.data.tables.staticpages.StaticPagesEntity;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.control.search.SearchParams;
import org.allbinary.logic.control.search.SearchRequest;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;

/*
 *This class updates the static page db table and generates/updates the html
 *associated with each keyword filename pair listed in the static page db table
 *when a client request a search this class returns a static page
 */
public class InventorySearch implements InventoryViewSearchInterface
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private final SearchRequest searchRequest;

    public InventorySearch(SearchRequest searchRequest)
    {
        super();
        this.searchRequest = searchRequest;
    }

    /*This function searches the static pages db for a match*/
    public String searchSingleStaticPage() throws Exception
    {
        try
        {
            StoreFrontInterface storeFront = this.searchRequest.getStoreFront();

            SearchParams searchParams = this.searchRequest.getParams();
            HashMap columnValueHashMap = searchParams.get();

            String file = new StaticPagesEntity().getFile(
                storeFront.getName(), new Replace("-", CommonSeps.getInstance().SPACE).all(
                (String) columnValueHashMap.get(BasicItemData.KEYWORDS)));

            if (StringValidationUtil.getInstance().isEmpty(file))
            {
                return null;
            }
            else
            {
            	StringBuffer stringBuffer = new StringBuffer();
            	
            	stringBuffer.append(storeFront.getCurrentHostName());
                stringBuffer.append(storeFront.getCurrentHostNamePath());
                stringBuffer.append(storeFront.getStaticPath());
                stringBuffer.append(file);
                stringBuffer.append(this.searchRequest.getParams().getEndPage());
                stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
                stringBuffer.append(InputOutputTypeData.getInstance().DEFAULT);

                String filePath = new Replace(CommonSeps.getInstance().SPACE, "%20").all(stringBuffer.toString());

                return filePath;
            }
        }
        catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "searchSingleStaticPage", e));
            }
            throw new Exception("Failed retrieve Single Product Page Static");
        }
    }

    public String searchSingleDynamicPage() throws Exception
    {
        try
        {
            String[] str = search();
            return str[this.searchRequest.getParams().getStartPageInt().intValue()];
        }
        catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "searchSingleDynamicPage", e));
            }
            throw new Exception("Failed retrieve Single Product Page Dynamically");
        }
    }

    //Node contentTypeNode = document.createElement(this.searchRequest.getContentType());
    public String[] search() throws Exception
    {
        final AbeClientInformationInterface abeClientInformation = 
            ServiceClientInformationInterfaceFactory.getInstance();

        final InventorySearchUtil inventorySearchUtil =
            InventorySearchUtil.getInstance();

        final Vector vector = inventorySearchUtil.getBasicItemIdColumn(searchRequest);

        return inventorySearchUtil.search(abeClientInformation, searchRequest, vector);
    }

    public String getProduct(String product) throws Exception
    {
        final AbeClientInformationInterface abeClientInformation = 
            ServiceClientInformationInterfaceFactory.getInstance();

        return InventorySearchProductUtil.getInstance().getProduct(abeClientInformation, searchRequest, product);
    }

    /*
    public String remove(String keywords)
    {
    //remove static file and db entry if no match left
    return StringUtil.getInstance();
    }
    
    public String update(String keywords)
    {
    //generate a save path
    //generate static pages from the listgenerator
    //if keywords not in static pages db add to static pages db
    //otherwise update db
    return StringUtil.getInstance();
    }
     */
    /*
    public String upgradeAll(String storeName)
    {
    }
     */
}
