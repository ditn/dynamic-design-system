rootProject.name = "design-system-frontend"
include(":app")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "symbol-processing" ->
                    useModule("com.google.devtools.ksp:symbol-processing:${requested.version}")
            }
        }
    }

    repositories {
        gradlePluginPortal()
        google()
    }
}
