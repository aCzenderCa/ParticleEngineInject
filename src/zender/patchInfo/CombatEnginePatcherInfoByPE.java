package zender.patchInfo;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.combat.CombatEngine;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.vector.Vector2f;
import particleengine.Emitter;
import particleengine.Particles;
import zender.patch.patchInterface.ICombatEnginePatcherInfo;

import java.awt.*;

public class CombatEnginePatcherInfoByPE implements ICombatEnginePatcherInfo {
    public SpriteAPI fusion_lamp_glow;
    public SpriteAPI particlealpha32sq;
    public SpriteAPI particlealpha64linear;
    public SpriteAPI nebulaParticles;
    public SpriteAPI nebulaParticles2;
    public SpriteAPI fx_particles2;
    public SpriteAPI smoke32;

    public CombatEnginePatcherInfoByPE() {
        try {
            fusion_lamp_glow = Global.getSettings().getSprite("campaignEntities", "fusion_lamp_glow");
        } catch (Exception e) {
        }
        try {
            particlealpha32sq = Global.getSettings().getSprite("game_raw_fx", "particlealpha32sq");
        } catch (Exception e) {
        }
        try {
            particlealpha64linear = Global.getSettings().getSprite("graphics/fx/particlealpha64linear.png");
        } catch (Exception e) {
        }
        try {
            nebulaParticles = Global.getSettings().getSprite("misc", "nebula_particles");
        } catch (Exception e) {
        }
        try {
            nebulaParticles2 = Global.getSettings().getSprite("misc", "nebula_particles2");
        } catch (Exception e) {
        }
        try {
            fx_particles2 = Global.getSettings().getSprite("misc", "fx_particles2");
        } catch (Exception e) {
        }
        try {
            smoke32 = Global.getSettings().getSprite("graphics/fx/smoke32.png");
        } catch (Exception e) {
        }
    }


    @Nullable
    public static Emitter createSimpleParticle(@Nullable SpriteAPI spriteAPI, Vector2f loc, Vector2f vel, float size,
                                               float brightness, float duration, Color color, boolean neg) {
        if (spriteAPI == null) return null;
        Emitter particle = Particles.initialize(loc, spriteAPI);
        particle.velocity(vel, vel);
        particle.size(size / spriteAPI.getWidth(), size / spriteAPI.getWidth(),
                size / spriteAPI.getHeight(), size / spriteAPI.getHeight());
        if (neg) {
            particle.setBlendMode(770, 1, 32779);
        }
        if (brightness > 0) {
            if (brightness > 1) brightness = 1;
            particle.color(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (brightness * 255f)));
        } else {
            particle.color(color);
        }
        if (duration <= 0) duration = 0.5f;
        particle.life(duration, duration);
        return particle;
    }

    public static void burst(@Nullable Emitter emitter) {
        if (emitter == null) return;
        Particles.burst(emitter, 1);
    }

    @Override
    public void addHitParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float brightness,
                               float duration, Color color) {
        Emitter particle = createSimpleParticle(fusion_lamp_glow, loc, vel, size, brightness, duration, color, false);
        burst(particle);
    }

    @Override
    public void addHitParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float brightness,
                               float durationIn, float totalDuration, Color color) {
        Emitter particle = createSimpleParticle(fusion_lamp_glow, loc, vel, size, brightness, totalDuration, color, false);
        if (particle != null) {
            particle.fadeTime(durationIn * totalDuration, durationIn * totalDuration, 0, 0);
        }
        burst(particle);
    }

    @Override
    public void addSmoothParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size,
                                  float brightness, float duration, Color color) {
        Emitter particle = createSimpleParticle(particlealpha32sq, loc, vel, size, brightness, duration, color, false);
        burst(particle);
    }

    @Override
    public void addSmoothParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size,
                                  float brightness, float rampUpFraction, float totalDuration, Color color) {
        Emitter particle = createSimpleParticle(particlealpha32sq, loc, vel, size, brightness, totalDuration, color, false);
        if (particle != null) {
            particle.fadeTime(rampUpFraction * totalDuration, rampUpFraction * totalDuration, 0, 0);
        }
        burst(particle);
    }

    @Override
    public void addNegativeParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size,
                                    float rampUpFraction, float totalDuration, Color color) {
        Emitter particle = createSimpleParticle(particlealpha64linear, loc, vel, size,
                -1, totalDuration, color, true);
        if (particle != null) {
            particle.fadeTime(rampUpFraction * totalDuration, rampUpFraction * totalDuration, 0, 0);
        }
        burst(particle);
    }

    @Override
    public void addNebulaParticle(CombatEngine combatEngine, Vector2f loc, Vector2f vel, float size,
                                  float endSizeMult, float rampUpFraction, float fullBrightnessFraction,
                                  float totalDuration, Color color, boolean expandAsSqrt) {
        Emitter particle = createSimpleParticle(nebulaParticles, loc, vel, size,
                fullBrightnessFraction, totalDuration, color, false);
        if (particle != null) {
            particle.fadeTime(rampUpFraction * totalDuration, rampUpFraction * totalDuration, 0, 0);
        }
        CalcVA(size, endSizeMult, totalDuration, expandAsSqrt, particle);
        burst(particle);
    }

    @Override
    public void addNebulaSmoothParticle(CombatEngine combatEngine, Vector2f loc, Vector2f vel, float size,
                                        float endSizeMult, float rampUpFraction, float fullBrightnessFraction,
                                        float totalDuration, Color color, boolean expandAsSqrt) {
        Emitter particle = createSimpleParticle(nebulaParticles2, loc, vel, size,
                fullBrightnessFraction, totalDuration, color, false);
        if (particle != null) {
            particle.fadeTime(rampUpFraction * totalDuration, rampUpFraction * totalDuration, 0, 0);
        }
        CalcVA(size, endSizeMult, totalDuration, expandAsSqrt, particle);
        burst(particle);
    }

    @Override
    public void addSwirlyNebulaParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float endSizeMult,
                                        float rampUpFraction, float fullBrightnessFraction,
                                        float totalDuration, Color color, boolean expandAsSqrt) {
        Emitter particle = createSimpleParticle(fx_particles2, loc, vel, size,
                fullBrightnessFraction, totalDuration, color, false);
        if (particle != null) {
            particle.fadeTime(rampUpFraction * totalDuration, rampUpFraction * totalDuration, 0, 0);
        }
        CalcVA(size, endSizeMult, totalDuration, expandAsSqrt, particle);
        burst(particle);
    }

    @Override
    public void addNebulaSmokeParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float endSizeMult,
                                       float rampUpFraction, float fullBrightnessFraction, float totalDuration, Color color) {
        Emitter particle = createSimpleParticle(nebulaParticles, loc, vel, size,
                fullBrightnessFraction, totalDuration, color, false);
        if (particle != null) {
            particle.fadeTime(rampUpFraction * totalDuration, rampUpFraction * totalDuration, 0, 0);
        }
        CalcVA(size, endSizeMult, totalDuration, false, particle);
        burst(particle);
    }

    @Override
    public void addNegativeNebulaParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float endSizeMult,
                                          float rampUpFraction, float fullBrightnessFraction, float totalDuration, Color color) {
        Emitter particle = createSimpleParticle(nebulaParticles, loc, vel, size,
                fullBrightnessFraction, totalDuration, color, true);
        if (particle != null) {
            particle.fadeTime(rampUpFraction * totalDuration, rampUpFraction * totalDuration, 0, 0);
        }
        CalcVA(size, endSizeMult, totalDuration, false, particle);
        burst(particle);
    }

    @Override
    public void addNegativeSwirlyNebulaParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float endSizeMult,
                                                float rampUpFraction, float fullBrightnessFraction, float totalDuration, Color color) {
        Emitter particle = createSimpleParticle(fx_particles2, loc, vel, size, fullBrightnessFraction, totalDuration, color, true);
        if (particle != null) {
            particle.fadeTime(rampUpFraction * totalDuration, rampUpFraction * totalDuration, 0, 0);
        }
        CalcVA(size, endSizeMult, totalDuration, false, particle);
        burst(particle);

    }

    @Override
    public void addSmokeParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float opacity,
                                 float duration, Color color) {
        Emitter particle = createSimpleParticle(smoke32, loc, vel, size, opacity, duration, color, false);
        burst(particle);
    }

    @Override
    public void addSparks(float var1, float var2, float var3, float var4, Vector2f var5, float var6,
                          float var7, float var8, float var9, int var10, Color var11) {
        var4 = (float) Math.toRadians(var4);
        var3 = (float) Math.toRadians(var3);
        for (int var12 = 0; var12 < var10; ++var12) {
            float var14 = var3 + (float) Math.random() * var4 - var4 / 2.0F;
            float var15 = (float) (Math.random() * (double) (var1 + var2));
            float var16 = (float) Math.sin(var14);
            float var17 = (float) Math.cos(var14);
            float var18 = var8 + var17 * var15;
            float var19 = var9 + var16 * var15;
            float var20 = var6 + var7 * (float) Math.random();
            Emitter particle = createSimpleParticle(particlealpha32sq, new Vector2f(var18, var19),
                    new Vector2f(var5.x + var17 * var20, var5.y + var16 * var20),
                    (float) ((int) ((float) Math.random() * var1 + var2)), -1, 1, var11, false);
            burst(particle);
        }
    }

    private static void CalcVA(float size, float endSizeMult, float totalDuration, boolean expandAsSqrt, @Nullable Emitter particle) {
        if (particle == null) return;
        SpriteAPI sprite = particle.getSprite();
        size /= (sprite.getWidth() + sprite.getHeight()) / 2f;
        if (!expandAsSqrt) {
            float v = (endSizeMult - 1) * size / totalDuration;
            particle.growthRate(v, v);
        } else {
            float s = (endSizeMult - 1) * size;
            float v = s * 7 / totalDuration / 3;
            float a = -s * 8 / totalDuration / totalDuration / 3;
            particle.growthRate(v, v);
            particle.growthAcceleration(a, a);
        }
    }
}
