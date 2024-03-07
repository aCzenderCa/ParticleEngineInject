package zender.patch;

import javassist.*;

import static zender.patch.ParticleEngineInject_PreMain.*;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class CombatEnginePatcher {

    public static long AllSize = 0;

    public static void ClearBuffer(ByteBuffer buffer) {
        try {
            AllSize += buffer.capacity();
            if (AllSize > (1 << 27)) {
                AllSize = 0;
                System.gc();
            }
        } catch (Exception ignored) {
        }
    }

    public static void DoPatch(ClassPool pool, Instrumentation inst) {
        try {
            if (JavaVersion() > 8) {
                CtClass ctModule = SuGetClass(pool, "java.lang.Module");
                CtMethod[] isOpens = ctModule.getDeclaredMethods("isOpen");
                for (CtMethod isOpen : isOpens) {
                    isOpen.setBody("return true;");
                }
                CtMethod[] isExporteds = ctModule.getDeclaredMethods("isExported");
                for (CtMethod isExported : isExporteds) {
                    isExported.setBody("return true;");
                }
                inst.redefineClasses(new ClassDefinition(ClassLoader.getSystemClassLoader()
                        .loadClass("java.lang.Module"), ctModule.toBytecode()));
            }

            CtClass CombatEngine = pool.get("com.fs.starfarer.combat.CombatEngine");
            if (CombatEngine.isFrozen()) {
                CombatEngine.defrost();
            }
            try {
                CtMethod addHitParticle1 = CombatEngine.getMethod("addHitParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFLjava/awt/Color;)V");
                CtMethod addHitParticle2 = CombatEngine.getMethod("addHitParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFFLjava/awt/Color;)V");
                addHitParticle1.insertBefore(
                        "if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)" +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addHitParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6);return;}");
                addHitParticle2.insertBefore(
                        "if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)" +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addHitParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6,$7);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addSmoothParticle1 = CombatEngine.getMethod("addSmoothParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFLjava/awt/Color;)V");
                CtMethod addSmoothParticle2 = CombatEngine.getMethod("addSmoothParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFFLjava/awt/Color;)V");
                addSmoothParticle1
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addSmoothParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6);return;}");
                addSmoothParticle2
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addSmoothParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6,$7);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addNegativeParticle = CombatEngine.getMethod("addNegativeParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFLjava/awt/Color;)V");
                addNegativeParticle
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addNegativeParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addNebulaParticle = CombatEngine.getMethod("addNebulaParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFFFLjava/awt/Color;Z)V");
                addNebulaParticle
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addNebulaParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6,$7,$8,$9);return;}");

            } catch (NotFoundException ignored) {
            }
            try {
                CtMethod addNebulaSmoothParticle = CombatEngine.getMethod("addNebulaSmoothParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFFFLjava/awt/Color;Z)V");
                addNebulaSmoothParticle
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addNebulaSmoothParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6,$7,$8,$9);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addSwirlyNebulaParticle = CombatEngine.getMethod("addSwirlyNebulaParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFFFLjava/awt/Color;Z)V");
                addSwirlyNebulaParticle
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addSwirlyNebulaParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6,$7,$8,$9);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addNebulaSmokeParticle = CombatEngine.getMethod("addNebulaSmokeParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFFFLjava/awt/Color;)V");
                addNebulaSmokeParticle
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addNebulaSmokeParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6,$7,$8);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addNegativeNebulaParticle = CombatEngine.getMethod("addNegativeNebulaParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFFFLjava/awt/Color;)V");
                addNegativeNebulaParticle
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addNegativeNebulaParticle("
                                + "$0,$1,$2,$3,$4,$5,$6,$7,$8);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addNegativeSwirlyNebulaParticle = CombatEngine.getMethod(
                        "addNegativeSwirlyNebulaParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFFFLjava/awt/Color;)V");
                addNegativeSwirlyNebulaParticle
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addNegativeSwirlyNebulaParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6,$7,$8);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addSmokeParticle = CombatEngine.getMethod("addSmokeParticle",
                        "(Lorg/lwjgl/util/vector/Vector2f;Lorg/lwjgl/util/vector/Vector2f;FFFLjava/awt/Color;)V");
                addSmokeParticle
                        .insertBefore("if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)"
                                +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addSmokeParticle("
                                +
                                "$0,$1,$2,$3,$4,$5,$6);return;}");
            } catch (NotFoundException ignored) {
            }

            try {
                CtMethod addSparks = CombatEngine.getMethod("addSparks",
                        "(FFFFLorg/lwjgl/util/vector/Vector2f;FFFFILjava/awt/Color;)V");
                addSparks.insertBefore(
                        "if(zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo!=null)" +
                                "{zender.patch.ParticleEngineInject_PreMain.combatEnginePatcherInfo.addSparks("
                                +
                                "$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11);return;}");
            } catch (NotFoundException ignored) {
            }

            SuDefineClass(CombatEngine, inst, () -> com.fs.starfarer.combat.CombatEngine.class);

            if (JavaVersion() > 8) {
                try {
                    Class<?> builtinClassLoader = ClassLoader.getSystemClassLoader().loadClass("jdk.internal.loader.BuiltinClassLoader");
                    CtClass Package = SuGetClass(pool, "java.lang.Package");
                    CtMethod isSealed = Package.getMethod("isSealed", "(Ljava/net/URL;)Z");
                    isSealed.insertBefore("return true;");
                    SuDefineClass(Package, inst, () -> java.lang.Package.class);
                    CtClass BuiltinClassLoader = SuGetClass(pool, "jdk.internal.loader.BuiltinClassLoader");
                    CtMethod getAndVerifyPackage = BuiltinClassLoader.getDeclaredMethod("getAndVerifyPackage");
                    getAndVerifyPackage.insertBefore("return getDefinedPackage($1);");
                    inst.redefineClasses(new ClassDefinition(builtinClassLoader, BuiltinClassLoader.toBytecode()));
                } catch (ClassNotFoundException ignored) {

                }
            }

            if (JavaVersion() > 11) {
                CtClass Thread = SuGetClass(pool, "java.lang.Thread");
                try {
                    CtMethod stop = Thread.getMethod("stop", "()V");
                    stop.addCatch("return;", pool.get("java.lang.Exception"));
                } catch (NotFoundException e) {
                    CtMethod stop = new CtMethod(pool.get("void"), "stop", new CtClass[0], Thread);
                    stop.setModifiers(Modifier.PUBLIC | Modifier.FINAL);
                    stop.setBody("return;");
                    Thread.addMethod(stop);
                }
                inst.redefineClasses(new ClassDefinition(java.lang.Thread.class, Thread.toBytecode()));
            }

            if (JavaVersion() > 8) {
                CtClass ctAccessibleObject = SuGetClass(pool, "java.lang.reflect.AccessibleObject");
                CtMethod[] checkCanSetAccessibles = ctAccessibleObject.getDeclaredMethods("checkCanSetAccessible");
                for (CtMethod checkCanSetAccessible : checkCanSetAccessibles) {
                    if (checkCanSetAccessible.getReturnType() == pool.get("boolean"))
                        checkCanSetAccessible.setBody("return true;");
                }
                SuDefineClass(ctAccessibleObject, inst, () -> java.lang.reflect.AccessibleObject.class);
            }

            try {
                Class<?> Arrays = ClassLoader.getSystemClassLoader().loadClass("java.util.Arrays");
                CtClass CtArrays = SuGetClass(pool, "java.util.Arrays");
                CtMethod sort = CtArrays.getMethod("sort", "([Ljava/lang/Object;Ljava/util/Comparator;)V");
                CtMethod sort1 = CtArrays.getMethod("sort", "([Ljava/lang/Object;IILjava/util/Comparator;)V");
                sort.setBody("{if($2==null)sort($1);else{legacyMergeSort($1, $2);}}");
                sort1.setBody("{if($4==null)sort($1,$2,$3);else{rangeCheck($1.length,$2,$3);legacyMergeSort($1,$2,$3,$4);}}");
                inst.redefineClasses(new ClassDefinition(Arrays, CtArrays.toBytecode()));
            } catch (Exception ignored) {
            }

            if (JavaVersion() > 8) {
                CtClass TextureLoader = SuGetClass(pool, "com.fs.graphics.TextureLoader");
                CtMethod[] TLMs = TextureLoader.getMethods();
                CtMethod clearBuffer = null;
                for (CtMethod ctMethod : TLMs) {
                    CtClass[] parameterTypes = ctMethod.getParameterTypes();
                    if (parameterTypes.length > 0
                            && parameterTypes[0].equals(pool.get("java.nio.ByteBuffer"))) {
                        clearBuffer = ctMethod;
                        break;
                    }
                }

                clearBuffer.setBody("zender.patch.CombatEnginePatcher.ClearBuffer($1);");
                SuDefineClass(TextureLoader, inst, () -> com.fs.graphics.TextureLoader.class);
            }

            CtClass SortablePlanetList = SuGetClass(pool, "com.fs.starfarer.campaign.ui.intel.SortablePlanetList");
            CtMethod recreateList = SortablePlanetList.getDeclaredMethod("recreateList");
            recreateList.addCatch("return;", pool.get("java.lang.Exception"));
            SuDefineClass(SortablePlanetList, inst, () -> com.fs.starfarer.campaign.ui.intel.SortablePlanetList.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
