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
package org.allbinary.business.user.commerce.money.payment.types;

public class AuthorizationTypeFactory {

	private static final AuthorizationTypeFactory instance = new AuthorizationTypeFactory();

	public static AuthorizationTypeFactory getInstance() {
		return instance;
	}
	
	   public final AuthorizationType NONE = new AuthorizationType("None");   
	   public final AuthorizationType VOICE = new AuthorizationType("Voice"); 
	   public final AuthorizationType RETINAL = new AuthorizationType("Retinal"); 
	   public final AuthorizationType IMAGE = new AuthorizationType("Image");
	   public final AuthorizationType DNA = new AuthorizationType("DNA");
	   public final AuthorizationType PRINT = new AuthorizationType("Print");
	   public final AuthorizationType CHIP = new AuthorizationType("Chip");
}
