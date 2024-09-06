package draylar.goml.mixin;

import draylar.goml.api.ClaimUtils;
import draylar.goml.other.OriginOwner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin extends Entity implements OriginOwner {
    @Shadow @Nullable private UUID ownerUuid;

    public ProjectileEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onCollision", at = @At("HEAD"), cancellable = true)
    private void preventEffects(HitResult hitResult, CallbackInfo ci) {
        if (!ClaimUtils.isNotAdminClaim(this.getWorld(), this.getBlockPos()) && !ClaimUtils.hasMatchingClaims(this.getWorld(), this.getBlockPos(), this.goml$getOriginSafe(), this.ownerUuid)) {
            ci.cancel();
        }
    }

    @Inject(method = "canModifyAt", at = @At("HEAD"), cancellable = true)
    private void preventModification(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!ClaimUtils.isNotAdminClaim(this.getWorld(), this.getBlockPos()) && !ClaimUtils.hasMatchingClaims(this.getWorld(), this.getBlockPos(), this.goml$getOriginSafe(), this.ownerUuid)) {
            cir.setReturnValue(false);
        }
    }
}
