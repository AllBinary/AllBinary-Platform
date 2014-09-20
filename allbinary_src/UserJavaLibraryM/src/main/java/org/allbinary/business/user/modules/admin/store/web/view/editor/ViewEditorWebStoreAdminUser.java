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
package org.allbinary.business.user.modules.admin.store.web.view.editor;

import org.allbinary.business.user.modules.User;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionInterface;

import java.util.HashMap;

public class ViewEditorWebStoreAdminUser extends User
{
   public ViewEditorWebStoreAdminUser() throws Exception
   {
      super();
   }

   public ViewEditorWebStoreAdminUser(HashMap userHashMap) throws Exception
   {
      super(userHashMap);
   }

   public void validateSession(WeblisketSessionInterface weblisketSession)
   {
      super.validateSession(weblisketSession);
      this.updateSession(weblisketSession);
   }

   public void updateSession(WeblisketSessionInterface weblisketSession)
   {
      weblisketSession.setStoreName(this.getPermissions());
   }

}
