package draylar.goml.mixin;

import draylar.goml.other.VanillaTeamGroups;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerScoreboard.class)
public class ServerScoreboardMixin {
    @Inject(method = "updateRemovedTeam", at = @At("TAIL"))
    private void goml$removeTeamFromClaims(Team team, CallbackInfo ci) {
        VanillaTeamGroups.onRemove(team);
    }
}
