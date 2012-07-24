<%
/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
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