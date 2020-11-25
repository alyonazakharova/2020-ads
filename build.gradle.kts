// Apply the java plugin to add support for Java
plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    jcenter()
}

dependencies {
    // Annotation
    implementation ("org.jetbrains:annotations:16.0.3")

    // JUnit Jupiter test framework
    testCompile("org.junit.jupiter:junit-jupiter-api:5.4.0")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.4.0")

    testCompile("org.apache.commons:commons-lang3:3.11")
}

tasks {
    test {
        maxHeapSize = "128m"
        useJUnitPlatform()
    }
}
