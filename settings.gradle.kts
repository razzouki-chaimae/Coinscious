pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Coinscious"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// App modules
include(":app")

// Core modules
include(":core:data")
include(":core:database")
include(":core:datastore")
include(":core:designsystem")
include(":core:model")
include(":core:ui")

// Feature modules
include(":feature:budgets")
include(":feature:dashboard")
include(":feature:quicklog")
include(":feature:settings")