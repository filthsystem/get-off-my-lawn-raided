package draylar.goml.mixin;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public abstract class BrittleMixin {
    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=ancient_debris"
                            },
                    ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/Block;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static Block brittleDebris(AbstractBlock.Settings settings) {
        return new Block (settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=anvil"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/AnvilBlock;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static AnvilBlock brittleAnvil(AbstractBlock.Settings settings) {
        return new AnvilBlock (settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=chipped_anvil"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/AnvilBlock;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static AnvilBlock brittleChippedAnvil(AbstractBlock.Settings settings) {
        return new AnvilBlock (settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=damaged_anvil"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/AnvilBlock;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static AnvilBlock brittleDamagedAnvil(AbstractBlock.Settings settings) {
        return new AnvilBlock (settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_block"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/Block;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static Block brittleNetheriteBlock(AbstractBlock.Settings settings) {
        return new Block (settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=crying_obsidian"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/CryingObsidianBlock;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static CryingObsidianBlock brittleCryingObsidian(AbstractBlock.Settings settings) {
        return new CryingObsidianBlock (settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=respawn_anchor"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/RespawnAnchorBlock;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static RespawnAnchorBlock brittleRespawnAnchor(AbstractBlock.Settings settings) {
        return new RespawnAnchorBlock (settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=enchanting_table"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/EnchantingTableBlock;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static EnchantingTableBlock brittleEnchantingTable(AbstractBlock.Settings settings) {
        return new EnchantingTableBlock(settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=obsidian"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/Block;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static Block brittleObsidian(AbstractBlock.Settings settings) {
        return new Block (settings.resistance(15.5f));
    }

    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=ender_chest"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/block/EnderChestBlock;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static EnderChestBlock brittleEnderChest(AbstractBlock.Settings settings) {
        return new EnderChestBlock (settings.resistance(15.5f));
    }
}
