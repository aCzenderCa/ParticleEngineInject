package zender.patch.patchInterface;

import com.fs.starfarer.combat.CombatEngine;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;

public interface ICombatEnginePatcherInfo {
    void addHitParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float brightness,
                        float duration, Color color);

    void addHitParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float brightness,
                        float durationIn, float totalDuration, Color color);

    void addSmoothParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size,
                           float brightness, float duration, Color color);

    void addSmoothParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size,
                           float brightness, float rampUpFraction, float totalDuration, Color color);

    void addNegativeParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size,
                             float rampUpFraction, float totalDuration, Color color);

    void addNebulaParticle(CombatEngine combatEngine, Vector2f loc, Vector2f vel, float size,
                           float endSizeMult, float rampUpFraction, float fullBrightnessFraction,
                           float totalDuration, Color color, boolean expandAsSqrt);

    void addNebulaSmoothParticle(CombatEngine combatEngine, Vector2f loc, Vector2f vel, float size,
                                 float endSizeMult, float rampUpFraction, float fullBrightnessFraction,
                                 float totalDuration, Color color, boolean expandAsSqrt);

    void addSwirlyNebulaParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float endSizeMult,
                                 float rampUpFraction, float fullBrightnessFraction,
                                 float totalDuration, Color color, boolean expandAsSqrt);

    void addNebulaSmokeParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float endSizeMult,
                                float rampUpFraction, float fullBrightnessFraction, float totalDuration, Color color);

    void addNegativeNebulaParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float endSizeMult,
                                   float rampUpFraction, float fullBrightnessFraction, float totalDuration, Color color);

    void addNegativeSwirlyNebulaParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float endSizeMult,
                                         float rampUpFraction, float fullBrightnessFraction, float totalDuration, Color color);

    void addSmokeParticle(CombatEngine engine, Vector2f loc, Vector2f vel, float size, float opacity,
                          float duration, Color color);

    void addSparks(float var1, float var2, float var3, float var4, Vector2f var5, float var6, float var7, float var8,
                   float var9, int var10, Color var11);

}
