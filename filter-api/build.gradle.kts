plugins {
  id("event.conventions")
}

dependencies {
  compileOnlyApi("org.jetbrains:annotations:26.1.0")
  compileOnlyApi("org.jspecify:jspecify:1.0.1")
  testImplementation("com.google.guava:guava-testlib:33.8.0-jre")
}
