package draylar.goml.other;

import net.minecraft.util.math.BlockPos;

public interface OriginOwner {
    BlockPos goml$getOrigin();
    void goml$setOrigin(BlockPos pos);

    default BlockPos goml$getOriginSafe() {
        var origin =  this.goml$getOrigin();
        if (origin == null) {
            return BlockPos.ORIGIN;
        }
        return origin;
    }
}
