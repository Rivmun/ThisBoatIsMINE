package com.rimo.tbim;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.BoatEntity;

public class ModMain {
	public static void init() {
		InteractionEvent.INTERACT_ENTITY.register((player, entity, hand) -> {
			if (!player.isSpectator() && player.isSneaking()) {
				if (entity instanceof LivingEntity && entity.hasVehicle()) {
					entity.stopRiding();
					player.swingHand(hand);
					return EventResult.interruptTrue();
				} else if ((entity instanceof AbstractMinecartEntity || entity instanceof BoatEntity) && entity.hasPassengers()) {
					entity.removeAllPassengers();
					player.swingHand(hand);
					return EventResult.interruptTrue();
				}
            }
			return EventResult.pass();
		});
	}
}
