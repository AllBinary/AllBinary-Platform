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
package allbinary.business.category;

import allbinary.data.file.DataFileData;

public class CategoryData
{
	private static final CategoryData instance = new CategoryData();
	
	public static CategoryData getInstance() {
		return instance;
	}

	private CategoryData()
	{
		
	}
	
   public final String PARENT = "CATEGORY_PARENT";
   
   public final String NAME = "CATEGORY_NAME";
   public final String PATH = "CATEGORY_PATH";

   public final String LABEL = "CATEGORY_LABEL";

   public final String VIEW = "CATEGORY_VIEW_CATEGORY";

   public final String LEVEL = "CATEGORY_LEVEL";

   public final String REQUEST = "request";

   public final String ROOTCATEGORY = "Category";

   //public final String VIEW = "CATEGORY_VIEW";
   //private final String CATEGORIES = "categories";
   //public final String NAME = "CATEGORY";
   //public final String STORENAMEELEMENT = "STORE";

   public final String UNCRYPTED_EXTENSION = DataFileData.UNCRYPTED_EXTENSION;
   public final String ENCRYPTED_EXTENSION = DataFileData.ENCRYPTED_EXTENSION;
}
