package net.zestywings.ttgmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.TTGMod;

import java.awt.*;


public class BrewKegScreen extends HandledScreen<BrewKegScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(TTGMod.MOD_ID, "textures/gui/brew_keg_gui.png");

    public BrewKegScreen(BrewKegScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX =  ( textRenderer.getWidth(title) / 2);
        playerInventoryTitleY = 1000;

    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE,x,y,0,0, backgroundWidth, backgroundHeight);


        //renderProgressBar(context,x,y);
        //renderFullnessBar(context,x,y);

        /*
        if(handler.getScaledProgress()!=0){
            context.drawText(textRenderer, convertTickToTime(handler.getTicksLeft()),x+100,y+20, new Color(64,64,64,255).getRGB(), true);
        }
        if(handler.getScaledFullness()!=0){
            context.drawText(textRenderer, "FINISHED",x+100,y+20, new Color(64,64,64,255).getRGB(), true);
        }
         */
    }


    private void renderProgressBar(DrawContext context, int x, int y){
        if(handler.isCrafting()){
            context.drawTexture(TEXTURE,x+9, y+74-handler.getScaledProgress(), 180, 74-handler.getScaledProgress(),4, handler.getScaledProgress());
        }
    }

    private void renderFullnessBar(DrawContext context, int x, int y){
        if(!handler.isEmpty()){
            context.drawTexture(TEXTURE,x+9,y+74-handler.getScaledFullness(), 188,74-handler.getScaledFullness(), 4, handler.getScaledFullness());
        }
    }

    public String convertTickToTime(int tick){
        int time = tick/20;
        int hour = tick / 3600;
        int min = (time % 3600) / 60;
        int sec = time % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }



    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta){
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

    }







}
