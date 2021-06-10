plugins {
    java
    idea
    id("io.quarkus")
}

repositories {
    mavenLocal()
    mavenCentral()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    implementation("io.quarkus:quarkus-rest-client-reactive")

    implementation("io.quarkus:quarkus-vertx-web")
    implementation("io.quarkus:quarkus-rest-client-reactive-jackson")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive")

    // Logging
    implementation("org.slf4j:slf4j-api:1.7.30")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")

    // Tests
    testImplementation("org.codehaus.groovy:groovy-all:3.0.8")
    testImplementation("org.spockframework:spock-core:2.0-M5-groovy-3.0")
    // optional dependencies for using Spock
    // allows mocking of classes (in addition to interfaces)
    testImplementation("cglib:cglib-nodep:3.3.0")
    // allows mocking of classes without default constructor (together with CGLIB)
    testImplementation("org.objenesis:objenesis:3.2")
    testImplementation("com.athaydes:spock-reports:2.0.1-RC3")
}

group = "io.qaware"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
