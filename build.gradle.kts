plugins {
    application
    id("java")
    id ("org.openjfx.javafxplugin") version "0.0.13"
}

group = "p4_group_8_repo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

application {
    mainClass = "p4_group_8_repo.MentorApp"
}

dependencies {
    implementation("org.apache.commons:commons-csv:1.12.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.vintage:junit-vintage-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.pholser:junit-quickcheck-core:1.0")
    testImplementation("com.pholser:junit-quickcheck-generators:1.0")
    testImplementation("org.mockito:mockito-core:5.14.+")
    mockitoAgent("org.mockito:mockito-core:5.14.+") { isTransitive = false }
}


java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

javafx{
    version = "22.0.1"
    modules = listOf("javafx.controls" , "javafx.fxml")
}

tasks.test {
    useJUnitPlatform()
}