<%
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
%>
<%
class AdminPageData
{
   public static final String PREFIX = "admin";
   public static final String EXTENSION = ".jsp";

   public static final String ADMIN = PREFIX + EXTENSION;
   public static final String USERMANAGER = PREFIX + "UserManager" + EXTENSION;
   public static final String CONFIG = PREFIX + "Config" + EXTENSION;
   public static final String LOGGING = PREFIX + "Logging" + EXTENSION;
   public static final String REPORTS = PREFIX + "Reports" + EXTENSION;
   public static final String HELP = PREFIX + "Help" + EXTENSION;

   public static final String TABLES = "tables" + EXTENSION;
   public static final String STORES = "stores" + EXTENSION;
}
%>