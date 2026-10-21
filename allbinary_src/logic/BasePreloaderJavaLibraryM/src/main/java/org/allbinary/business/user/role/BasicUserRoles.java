/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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
package org.allbinary.business.user.role;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

/**
 *
 * @author User
 */
public class BasicUserRoles {

    private static final BasicUserRoles instance = new BasicUserRoles();

    /**
     * @return the instance
     */
    public static BasicUserRoles getInstance() {
        return BasicUserRoles.instance;
    }

    public final BasicArrayList roleVector = new BasicArrayListD();

    public BasicUserRole getRole(String role) throws Exception {
        final BasicArrayList roleVector = this.getAll();
        final int size = roleVector.size();
        for (int index = 0; index < size; index++) {
            BasicUserRole userRole = (BasicUserRole) roleVector.get(index);

            if (role.compareTo(userRole.toString()) == 0) {
                return userRole;
            }
        }
        throw new Exception("Unable to get role - Unknown Role");
    }

    public BasicArrayList getAll() {
        return this.roleVector;
    }

}
