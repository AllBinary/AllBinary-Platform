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
package org.allbinary.game.resource;

import org.allbinary.game.configuration.feature.GameFeatureControlledInterface;
import org.allbinary.graphics.RelativeRelationship;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public interface ResourceRelativeRelationshipFactoryInterface 
extends GameFeatureControlledInterface
{
    BasicArrayList getResourceRelativeRelationshipList(String resource) throws Exception;
    void addResourceRelativeRelationship(String resource, RelativeRelationship relativeRelationship) throws Exception;
}
