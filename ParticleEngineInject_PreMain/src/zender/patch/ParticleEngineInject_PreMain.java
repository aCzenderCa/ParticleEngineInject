package zender.patch;

import javassist.ClassPool;
import javassist.CtClass;
import zender.patch.patchInterface.ICombatEnginePatcherInfo;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.function.Supplier;

public class ParticleEngineInject_PreMain {
    public static int JavaVersion() {
        String specificationVersion = System.getProperty("java.specification.version");
        String[] split = specificationVersion.split("\\.");
        try {

            if (specificationVersion.startsWith("1.")) {
                if (split.length > 1) {
                    return Integer.parseInt(split[1]);
                } else {
                    return 0;
                }
            } else {
                if (split.length > 0) {
                    return Integer.parseInt(split[0]);
                } else {
                    return 0;
                }
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static ICombatEnginePatcherInfo combatEnginePatcherInfo;

    public static void SuDefineClass(CtClass ctcls, Instrumentation inst, Supplier<Class<?>> cls) {
        try {
            ctcls.toClass();
        } catch (Exception e) {
            try {
                inst.redefineClasses(new ClassDefinition(cls.get(), ctcls.toBytecode()));
            } catch (Exception ee) {
                throw new RuntimeException(ee);
            }
        }
    }

    public static CtClass SuGetClass(ClassPool pool, String cls) {
        try {
            CtClass ctClass = pool.get(cls);
            if (ctClass.isFrozen()) {
                ctClass.defrost();
            }
            return ctClass;
        } catch (Exception e) {
            throw new RuntimeException("class:" + cls + " not find", e);
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        try {
            ClassPool pool = ClassPool.getDefault();

            CombatEnginePatcher.DoPatch(pool, inst);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
