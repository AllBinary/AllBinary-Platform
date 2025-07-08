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

import org.allbinary.game.layer.CollidableCompositeLayer;
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
    
    public CollidableVehicleBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);
    }

    // @Override
    public void collide(CollidableCompositeLayer collidableInterfaceCompositeInterface)
    throws Exception
    {
        //Don't process non-weapon pickups if dead - Should even collisions occur if dead.
        //I would expect just to set blank behaviors for both?
        //if (this.getHealthInterface().isAlive())

        CollisionTypeFactory collisionTypeFactory = CollisionTypeFactory.getInstance();
        CollisionType collisionType = collidableInterfaceCompositeInterface.getCollidableInferface().getCollisionTypeWith(this.ownerLayer);

        if (collisionType == collisionTypeFactory.PICKUP)
        {
            ((CollidableDestroyableDamageableLayer) this.ownerLayer).getPickupBehavior().doPickup(
                    (PickedUpLayerInterface) collidableInterfaceCompositeInterface);
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

    // @Override
    public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        ForcedLogUtil.log("Don't Use Interface Version It Is Slower", this);
        //this.collide((VehicleLayer) collidableInterfaceCompositeInterface);
    }
    
    private long halfImpactVelocityX;
    private long halfImpactVelocityY;

    protected void collide(VehiclePropertiesCompositeInterface vehiclePropertiesCompositeInterface)
    {
        VehiclePropertiesCompositeInterface ownerVehicleLayerInterface = 
            ((VehiclePropertiesCompositeInterface) this.ownerLayer);
        
        VehicleProperties ownerVehicleProperties = ownerVehicleLayerInterface.getVehicleProperties();
        VehicleProperties vehicleProperties = vehiclePropertiesCompositeInterface.getVehicleProperties();

        // Reverse Velocity of both vehicles on the side of the hit
        long impactVelocityX = ownerVehicleProperties.getVelocityProperties().getVelocityXBasicDecimal().getUnscaled() 
                - vehicleProperties.getVelocityProperties().getVelocityXBasicDecimal().getUnscaled();

        long impactVelocityY = ownerVehicleProperties.getVelocityProperties().getVelocityYBasicDecimal().getUnscaled()
                - vehicleProperties.getVelocityProperties().getVelocityYBasicDecimal().getUnscaled();

        // long impactVelocityX =
        // this.getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimal().getUnscaled();
        // long impactVelocityY =
        // this.getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimal().getUnscaled();

        // Basic bounce off
        if (impactVelocityX != 0)
        {
            // logUtil.put("Transfering X Velocity: " +
            // impactVelocityX, this, damageUtil.COLLIDE);
            halfImpactVelocityX = (impactVelocityX >> 1);
            ownerVehicleProperties.getVelocityProperties().getVelocityXBasicDecimal().add(halfImpactVelocityX);
            vehicleProperties.getVelocityProperties().getVelocityXBasicDecimal().add(halfImpactVelocityX);

            // vehicleLayer.move((int)
            // this.getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimal().getScaled(),
            // 0);

            // this.getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimal().subtract(impactVelocityX
            // * 2);
            // vehicleLayer.getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimal().add(impactVelocityX);
        } else
        {
            // logUtil.put("No X Velocity Transfer",
            // this, damageUtil.COLLIDE);
        }

        // Basic bounce off
        if (impactVelocityY != 0)
        {
            // logUtil.put("Transfering Y Velocity: " +
            // impactVelocityY, this, damageUtil.COLLIDE);
            halfImpactVelocityY = (impactVelocityY >> 1);
            ownerVehicleProperties.getVelocityProperties().getVelocityYBasicDecimal().add(halfImpactVelocityY);
            vehicleProperties.getVelocityProperties().getVelocityXBasicDecimal().add(halfImpactVelocityY);

            // vehicleLayer.move((int)
            // this.getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimal().getScaled(),
            // 0);

            // this.getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimal().subtract(impactVelocityY
            // * 2);
            // vehicleLayer.getVehicleProperties().getVelocityProperties().getVelocityYBasicDecimal().add(impactVelocityY);
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

    private void collideFriction(VehiclePropertiesCompositeInterface vehiclePropertiesCompositeInterface)
    {
        VehicleFrictionProperties vehicleFrictionProperties = 
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
     * ().getVelocityXBasicDecimal(); BasicDecimal otherXBasicDecimal =
     * vehicleLayer
     * .getVehicleProperties().getVelocityProperties().getVelocityXBasicDecimal
     * (); BasicDecimal yBasicDecimal =
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityYBasicDecimal(); BasicDecimal otherYBasicDecimal =
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
     * ().getVelocityXBasicDecimal().getUnscaled() -
     * vehicleLayer.getVehicleProperties
     * ().getVelocityProperties().getVelocityXBasicDecimal().getUnscaled();
     * long impactVelocityY =
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityYBasicDecimal().getUnscaled() -
     * vehicleLayer.getVehicleProperties
     * ().getVelocityProperties().getVelocityYBasicDecimal().getUnscaled();
     * 
     * // Basic bounce off if (impactVelocityX != 0) { //
     * logUtil.put("Transfering X Velocity: " +
     * impactVelocityX, // this, damageUtil.COLLIDE);
     * this.getVehicleProperties().getVelocityProperties
     * ().getVelocityXBasicDecimal().add((impactVelocityX >> 1));
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
     * ().getVelocityYBasicDecimal().add((impactVelocityY >> 1));
     * vehicleLayer.getVehicleProperties
     * ().getVelocityProperties().getVelocityYBasicDecimal
     * ().add((impactVelocityY >> 1)); } else { //
     * logUtil.put("No Y Velocity Transfer", this,
     * damageUtil.COLLIDE); }
     */       
}