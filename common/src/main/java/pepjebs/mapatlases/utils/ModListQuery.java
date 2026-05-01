package pepjebs.mapatlases.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class ModListQuery {
    @ExpectPlatform
    public static boolean isLoaded(String modId) {
        throw new AssertionError();
    }
}
