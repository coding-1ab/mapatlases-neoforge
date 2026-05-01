package pepjebs.mapatlases.mixin;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.service.IClassBytecodeProvider;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.Annotations;
import pepjebs.mapatlases.utils.ModListQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AtlasesMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String modMixin = Type.getDescriptor(ModMixin.class);
        IClassBytecodeProvider bytecodeProvider = MixinService.getService().getBytecodeProvider();

        ClassNode mixinClass;
        try {
            mixinClass = bytecodeProvider.getClassNode(mixinClassName);
        } catch (ClassNotFoundException | IOException ignored) {
            return true; // Trigger mixin load error intentionally as this is clearly error.
        }

        List<AnnotationNode> annotations = new ArrayList<>();
        {
            if (mixinClass.invisibleAnnotations != null) {
                annotations.addAll(mixinClass.invisibleAnnotations);
            }
            if (mixinClass.visibleAnnotations != null) {
                annotations.addAll(mixinClass.visibleAnnotations);
            }
        }

        for (AnnotationNode node : annotations) {
            if (node.desc.equals(modMixin)) {
                String modId = Annotations.getValue(node, "modId", "");
                if (!modId.isEmpty()) {
                    return ModListQuery.isLoaded(modId);
                }
            }
        }
        return true;
    }


    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @interface ModMixin {
        String modId();
    }
}
