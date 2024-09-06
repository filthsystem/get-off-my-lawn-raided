package draylar.goml.mixin;

import draylar.goml.api.ClaimUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {
    @Shadow @Final public static DirectionProperty FACING;

    @Inject(method = "scheduledTick", at = @At("HEAD"), cancellable = true)
    private void safeSetBlock(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (ClaimUtils.isNotAdminClaim(world, pos)) {
            return;
        }

        var nextPos = pos.offset(state.get(FACING));

        if (!ClaimUtils.hasMatchingClaims(world, nextPos, pos)) {
            ci.cancel();
        }
    }
}
