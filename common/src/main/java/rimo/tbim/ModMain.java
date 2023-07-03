package rimo.tbim;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.entity.LivingEntity;

public class ModMain {
	public static void init() {
		InteractionEvent.INTERACT_ENTITY.register((player, entity, hand) -> {
			if (!player.isSpectator() && player.isSneaking() && entity instanceof LivingEntity && entity.hasVehicle()) {
				entity.stopRiding();
				player.swingHand(hand);
				return EventResult.interruptTrue();
            }
			return EventResult.pass();
		});
	}
}
