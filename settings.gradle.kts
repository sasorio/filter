pluginManagement {
  repositories {
    gradlePluginPortal()
  }

  includeBuild("build-logic")
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "filter-parent"

sequenceOf(
  "filter-api"
).forEach {
  include(it)
}
