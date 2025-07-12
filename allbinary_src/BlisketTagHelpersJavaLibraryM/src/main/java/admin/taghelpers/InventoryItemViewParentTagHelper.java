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
package admin.taghelpers;

import org.allbinary.logic.communication.http.request.RequestMapInterface;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.transform.TransformInterface;
import taghelpers.ValidationViewHelper;
import tags.HelperTag;
import views.admin.inventory.InventoryItemView;
import views.admin.inventory.download.DownloadableInventoryItemView;

public class InventoryItemViewParentTagHelper
{
    private static final InventoryItemViewParentTagHelper instance = new InventoryItemViewParentTagHelper();

    public static InventoryItemViewParentTagHelper getInstance()
    {
        return instance;
    }

    public InventoryItemView getInventoryItemView(HelperTag inventoryTag) throws Exception
    {
        if (inventoryTag != null)
        {
            //File in request
            this.checkForValidationViewHelper(
                inventoryTag.getHelper());

            ValidationViewHelper validationViewHelper =
                (ValidationViewHelper) inventoryTag.getHelper();
            TransformInterface viewObject = validationViewHelper.getViewObject();

            if (viewObject.getTypeId() != InventoryItemView.TYPE_ID)
            {
                StringMaker stringBuffer = new StringMaker();
                //stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Must have ");
                stringBuffer.append("InventoryItemView and not: ");
                stringBuffer.append(inventoryTag.getHelper().getClass().getName());

                throw new Exception(stringBuffer.toString());
            }

            return (InventoryItemView) viewObject;

            //views.admin.inventory.AddFileValidationView
            //views.admin.inventory.UpdateFileValidationView
            //views.admin.inventory.InventoryItemView
        } else
        {
            throw new Exception("Must have parent tag.");
            //File not in request
            //this.request = (HttpServletRequest) pageContext.getRequest();
            //this.dataMappingInterface = ItemFactory.getInstance(request);
        }
    }

    public DownloadableInventoryItemView getDownloadableInventoryItemView(
        HelperTag inventoryTag) throws Exception
    {
        if (inventoryTag != null)
        {
            //File in request
            this.checkForValidationViewHelper(
                inventoryTag.getHelper());

            ValidationViewHelper validationViewHelper =
                (ValidationViewHelper) inventoryTag.getHelper();
            TransformInterface viewObject = validationViewHelper.getViewObject();

            if (viewObject.getTypeId() != DownloadableInventoryItemView.TYPE_ID)
            {
                StringMaker stringBuffer = new StringMaker();
                //stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Must have ");
                stringBuffer.append("DownloadableInventoryItemView and not: ");
                stringBuffer.append(inventoryTag.getHelper().getClass().getName());

                throw new Exception(stringBuffer.toString());
            }

            return (DownloadableInventoryItemView) viewObject;

            //views.admin.inventory.AddFileValidationView
            //views.admin.inventory.UpdateFileValidationView
            //views.admin.inventory.InventoryItemView
        } else
        {
            throw new Exception("Must have parent tag.");
            //File not in request
            //this.request = (HttpServletRequest) pageContext.getRequest();
            //this.dataMappingInterface = ItemFactory.getInstance(request);
        }
    }

    public RequestMapInterface getRequestMapInterface(HelperTag inventoryTag) throws Exception
    {
        if (inventoryTag != null)
        {
            //File in request
            this.checkForValidationViewHelper(
                inventoryTag.getHelper());

            ValidationViewHelper validationViewHelper =
                (ValidationViewHelper) inventoryTag.getHelper();
            TransformInterface viewObject = validationViewHelper.getViewObject();

            if (viewObject.getTypeId() != InventoryItemView.TYPE_ID &&
            	viewObject.getTypeId() != DownloadableInventoryItemView.TYPE_ID)
            {
                StringMaker stringBuffer = new StringMaker();
                //stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Must have ");
                stringBuffer.append("specific TYPE_ID and not: ");
                stringBuffer.append(inventoryTag.getHelper().getClass().getName());

                throw new Exception(stringBuffer.toString());
            }

            return (RequestMapInterface) viewObject;

        } else
        {
            throw new Exception("Must have parent tag.");
        }
    }
    
    public void checkForValidationViewHelper(Object helperObject)
        throws Exception
    {
        if (!(helperObject instanceof ValidationViewHelper))
        {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Must have ");
            stringBuffer.append("ValidationViewHelper");
            stringBuffer.append("and not: ");
            stringBuffer.append(helperObject.getClass().getName());

            throw new Exception(stringBuffer.toString());
        }
    }
}
