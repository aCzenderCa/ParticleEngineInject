package zender;

import com.fs.starfarer.api.BaseModPlugin;
import zender.patch.ParticleEngineInject_PreMain;
import zender.patchInfo.CombatEnginePatcherInfoByPE;

public class ParticleEngineInjectMainPlugin extends BaseModPlugin {
    @Override
    public void onApplicationLoad() {
        ParticleEngineInject_PreMain.combatEnginePatcherInfo = new CombatEnginePatcherInfoByPE();
    }
}
