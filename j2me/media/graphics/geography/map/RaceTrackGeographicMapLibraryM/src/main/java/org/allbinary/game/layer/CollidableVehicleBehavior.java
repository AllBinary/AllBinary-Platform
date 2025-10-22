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
package org.allbinary.game.layer;

import org.allbinary.game.layer.pickup.PickedUpLayerInterface;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;
import org.allbinary.game.collision.CollisionType;
import org.allbinary.game.collision.CollisionTypeFactory;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableBehavior;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;

public class CollidableVehicleBehavior
extends CollidableDestroyableDamageableBehavior 
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected long totalImpactVelocity = 0;
    
    public CollidableVehicleBehavior(final CollidableCompositeLayer ownerLayer, final boolean collidable)
    {
        super(ownerLayer, collidable);
    }

    @Override
    public void collide(final CollidableCompositeLayer collidableInterfaceCompositeInterface)
    throws Exception
    {
        //Don't process non-weapon pickups if dead - Should even collisions occur if dead.
        //I would expectt just to set blank behaviors for both?
        //if (this.getHealthInterface().isAlive())

        final CollisionTypeFactory collisionTypeFactory = CollisionTypeFactory.getInstance();
        final CollisionType collisionType = collidableInterfaceCompositeInterface.getCollidableInferface().getCollisionTypeWith(this.ownerLayer);

        if (collisionType == collisionTypeFactory.PICKUP)
        {
            final CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer = ((CollidableDestroyableDamageableLayer) this.ownerLayer);
            collidableDestroyableDamageableLayer.getPickupBehavior().doPickup((PickedUpLayerInterface) collidableInterfaceCompositeInterface);
        }
        else if (collisionType == collisionTypeFactory.COLLISION)
        {
            super.collide(collidableInterfaceCompositeInterface);
        }
        else
        {
            this.collide((VehiclePropertiesCompositeInterface) collidableInterfaceCompositeInterface);
        }
    }

    @Override
    public void collide(final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        ForcedLogUtil.log("Don't Use Interface Version It Is Slower", this);
        //this.collide((VehicleLayer) collidableInterfaceCompositeInterface);
    }
    
    private long halfImpactVelocityX;
    private long halfImpactVelocityY;

    protected void collide(final VehiclePropertiesCompositeInterface vehiclePropertiesCompositeInterface)
    {
        final VehiclePropertiesCompositeInterface ownerVehicleLayerInterface = 
            ((VehiclePropertiesCompositeInterface) this.ownerLayer);
        
        final VehicleProperties ownerVehicleProperties = ownerVehicleLayerInterface.getVehicleProperties();
        final VehicleProperties vehicleProperties = vehiclePropertiesCompositeInterface.getVehicleProperties();

        // Reverse Velocity of both vehicles on the side of the hit
        final long impactVelocityX = ownerVehicleProperties.getVelocityProperties().getVelocityXBasicDecimalP().getUnscaled() 
                - vehicleProperties.getVelocityProperties().getVelocityXBasicDecimalP().getUnscaled();

        final long impactVelocityY = ownerVehicleProperties.getVelocityProperties().getVelocityYBasicDecimalP().getUnscaled()
                - vehicleProperties.getVelocityProperties().getVelocityYBasicDecimalP().getUnscaled();

        // long impactVelocityX =
        // this.getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimalP().getUnscaled();
        // long impactVelocityY =
        // this.getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimalP().getUnscaled();

        // Basic bounce off
        if (impactVelocityX != 0L)
        {
            // logUtil.put("Transfering X Velocity: " +
            // impactVelocityX, this, damageUtil.COLLIDE);
            halfImpactVelocityX = (impactVelocityX >> 1);
            ownerVehicleProperties.getVelocityProperties().getVelocityXBasicDecimalP().add(halfImpactVelocityX);
            vehicleProperties.getVelocityProperties().getVelocityXBasicDecimalP().add(halfImpactVelocityX);

            // vehicleLayer.move((int)
            // this.getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimalP().getScaled(),
            // 0);

            // this.getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimalP().subtract(impactVelocityX
            // * 2);
            // vehicleLayer.getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimalP().add(impactVelocityX);
        } else
        {
            // logUtil.put("No X Velocity Transfer",
            // this, damageUtil.COLLIDE);
        }

        // Basic bounce off
        if (impactVelocityY != 0L)
        {
            // logUtil.put("Transfering Y Velocity: " +
            // impactVelocityY, this, damageUtil.COLLIDE);
            halfImpactVelocityY = (impactVelocityY >> 1);
            ownerVehicleProperties.getVelocityProperties().getVelocityYBasicDecimalP().add(halfImpactVelocityY);
            vehicleProperties.getVelocityProperties().getVelocityXBasicDecimalP().add(halfImpactVelocityY);

            // vehicleLayer.move((int)
            // this.getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimalP().getScaled(),
            // 0);

            // this.getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimalP().subtract(impactVelocityY
            // * 2);
            // vehicleLayer.getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimalP().add(impactVelocityY);
        } else
        {
            // logUtil.put("No Y Velocity Transfer",
            // this, damageUtil.COLLIDE);
        }

        // Slow Down
        this.collideFriction(ownerVehicleLayerInterface);
        this.collideFriction(vehiclePropertiesCompositeInterface);

        totalImpactVelocity = impactVelocityX + impactVelocityY;
    }

    private void collideFriction(final VehiclePropertiesCompositeInterface vehiclePropertiesCompositeInterface)
    {
        final VehicleFrictionProperties vehicleFrictionProperties = 
            vehiclePropertiesCompositeInterface.getVehicleProperties().getVehicleFrictionProperties();

        vehiclePropertiesCompositeInterface.getVehicleProperties()
                .getVehicleFrictionProperties().friction(
                        vehiclePropertiesCompositeInterface.getVehicleProperties().getVelocityProperties(),
                        vehicleFrictionProperties.getCrashFrictionNominator());
    }

    // Reverse Velocity of both vehicles on the side of the hit
    /*
     * BasicDecimal xBasicDecimal =
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityXBasicDecimalP(); BasicDecimal otherXBasicDecimal =
     * vehicleLayer
     * .getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimal
     * (); BasicDecimal yBasicDecimal =
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityYBasicDecimalP(); BasicDecimal otherYBasicDecimal =
     * vehicleLayer
     * .getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimal
     * ();
     * 
     * long impactVelocityX = xBasicDecimal.getUnscaled() -
     * otherXBasicDecimal.getUnscaled(); long impactVelocityY =
     * yBasicDecimal.getUnscaled() - otherYBasicDecimal.getUnscaled();
     * 
     * // Basic bounce off if (impactVelocityX != 0) { //
     * logUtil.put("Transfering X Velocity: " +
     * impactVelocityX, // this, damageUtil.COLLIDE);
     * xBasicDecimal.add((impactVelocityX >> 1));
     * otherXBasicDecimal.add((impactVelocityX >> 1)); } else { //
     * logUtil.put("No X Velocity Transfer", this,
     * damageUtil.COLLIDE); }
     * 
     * // Basic bounce off if (impactVelocityY != 0) { //
     * logUtil.put("Transfering Y Velocity: " +
     * impactVelocityY, // this, damageUtil.COLLIDE);
     * yBasicDecimal.add((impactVelocityY >> 1));
     * otherYBasicDecimal.add((impactVelocityY >> 1)); } else { //
     * logUtil.put("No Y Velocity Transfer", this,
     * damageUtil.COLLIDE); }
     */

    /*
     * long impactVelocityX =
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityXBasicDecimalP().getUnscaled() -
     * vehicleLayer.getVehicleProperties
     * ().getVelocityProperties().getVelocityXBasicDecimalP().getUnscaled();
     * long impactVelocityY =
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityYBasicDecimalP().getUnscaled() -
     * vehicleLayer.getVehicleProperties
     * ().getVelocityProperties().getVelocityYBasicDecimalP().getUnscaled();
     * 
     * // Basic bounce off if (impactVelocityX != 0) { //
     * logUtil.put("Transfering X Velocity: " +
     * impactVelocityX, // this, damageUtil.COLLIDE);
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityXBasicDecimalP().add((impactVelocityX >> 1));
     * vehicleLayer.getVehicleProperties
     * ().getVelocityProperties().getVelocityXBasicDecimal
     * ().add((impactVelocityX >> 1)); } else { //
     * logUtil.put("No X Velocity Transfer", this,
     * damageUtil.COLLIDE); }
     * 
     * // Basic bounce off if (impactVelocityY != 0) { //
     * logUtil.put("Transfering Y Velocity: " +
     * impactVelocityY, // this, damageUtil.COLLIDE);
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityYBasicDecimalP().add((impactVelocityY >> 1));
     * vehicleLayer.getVehicleProperties
     * ().getVelocityProperties().getVelocityYBasicDecimal
     * ().add((impactVelocityY >> 1)); } else { //
     * logUtil.put("No Y Velocity Transfer", this,
     * damageUtil.COLLIDE); }
     */       
}