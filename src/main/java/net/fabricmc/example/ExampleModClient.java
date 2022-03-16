package net.fabricmc.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.example.client.renderer.block.CuttingBoardBlockEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ExampleMod.CUTTING_BOARD_ENTITY, CuttingBoardBlockEntityRenderer::new);
    }
}
