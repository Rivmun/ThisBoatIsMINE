package com.rimo.tbim;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.VehicleEntity;

public class ModMain {

	public static final String MOD_ID = "tbim";

	public static void init() {
		InteractionEvent.INTERACT_ENTITY.register((player, entity, hand) -> {
			if (!player.isSpectator() && player.isShiftKeyDown()) {
				if (entity instanceof LivingEntity && entity.isPassenger()) {
					entity.stopRiding();
					player.swing(hand);
					return EventResult.interruptTrue();
				} else if (entity instanceof VehicleEntity && entity.isVehicle()) {
					entity.ejectPassengers();
					player.swing(hand);
					return EventResult.interruptTrue();
				}
			}
			return EventResult.pass();
		});
	}
}
