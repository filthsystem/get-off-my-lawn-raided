package draylar.goml.mixin;

import com.jamieswhiteshirt.rtree3i.Entry;
import com.jamieswhiteshirt.rtree3i.Selection;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import draylar.goml.api.Claim;
import draylar.goml.api.ClaimBox;
import draylar.goml.api.ClaimUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.piston.PistonHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Mixin(PistonHandler.class)
public class PistonHandlerMixin {
    @Shadow @Final private List<BlockPos> movedBlocks;
    @Shadow @Final private List<BlockPos> brokenBlocks;
    @Shadow @Final private World world;
    @Unique
    private boolean claimsEmpty;
    private HashSet<UUID> trusted;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void storeClaimInfo(World world, BlockPos pos, Direction dir, boolean retracted, CallbackInfo ci) {
        var claims = ClaimUtils.getClaimsAt(world, pos);
        this.claimsEmpty = claims.isEmpty();
        this.trusted = new HashSet<>();
        claims.forEach(x -> {
            this.trusted.addAll(x.getValue().getOwners());
            this.trusted.addAll(x.getValue().getTrusted());
        });
    }

    @ModifyReturnValue(method = "tryMove", at = @At("RETURN"))
    private boolean preventMovement(boolean value) {
        if (value) {
            if (!checkClaims(this.movedBlocks) || !checkClaims(this.brokenBlocks)) {
                this.movedBlocks.clear();
                this.brokenBlocks.clear();
                return false;
            }
            return true;
        }

        return false;
    }

    private boolean checkClaims(List<BlockPos> blocks) {
        for (var pos : blocks) {
            var claims = ClaimUtils.getClaimsAt(this.world, pos);
            if (claims.isEmpty() && this.claimsEmpty) {
                continue;
            }

            if (claims.noneMatch(x -> x.getValue().hasPermission(this.trusted))) {
                return false;
            }
        }
        return true;
    }
}
