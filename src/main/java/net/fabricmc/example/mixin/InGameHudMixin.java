package net.fabricmc.example.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow private int scaledWidth;
    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @ModifyArg(method = "renderHotbar", index = 1, at = @At(value = "INVOKE", ordinal = 4,target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
    private int moveIndicator(int p) {
        int offhandShift = getCameraPlayer().getOffHandStack().isEmpty() ? 0 : 29;

        if (getCameraPlayer().getMainArm() == Arm.LEFT)
            return scaledWidth/2 + 91 + 6 + offhandShift;
        return scaledWidth/2 - 91 - 22 - offhandShift;

    }

    @ModifyArg(method = "renderHotbar", index = 1, at = @At(value = "INVOKE", ordinal = 5,target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
    private int moveIndicator2(int p) {
        int offhandShift = getCameraPlayer().getOffHandStack().isEmpty() ? 0 : 29;

        if (getCameraPlayer().getMainArm() == Arm.LEFT)
            return scaledWidth/2 + 91 + 6 + offhandShift;
        return scaledWidth/2 - 91 - 22 - offhandShift;

    }

}
