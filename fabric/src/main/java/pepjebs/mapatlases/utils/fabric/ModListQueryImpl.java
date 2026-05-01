package pepjebs.mapatlases.utils.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class ModListQueryImpl {
    public static boolean isLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}
