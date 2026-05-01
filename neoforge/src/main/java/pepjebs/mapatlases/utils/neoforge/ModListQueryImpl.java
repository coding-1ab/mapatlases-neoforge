package pepjebs.mapatlases.utils.neoforge;

import net.neoforged.fml.loading.FMLLoader;

public class ModListQueryImpl {
    public static boolean isLoaded(String modId) {
        return FMLLoader
                .getLoadingModList()
                .getMods()
                .stream()
                .anyMatch(m -> m.getModId().equals(modId));
    }
}
