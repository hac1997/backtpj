plugins {
    id 'java'
    id 'org.springframework.boot' version '3.3.4'
    id 'io.spring.dependency-management' version '1.1.6'
    id 'checkstyle'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

checkstyle {
    toolVersion = '10.17.0' // Versão do Checkstyle
    configFile = file('checkstyle.xml') // Arquivo de regras
    maxWarnings = 0 // Falha o build se houver warnings
}

tasks.named('checkstyleMain') {
    // Configurações adicionais, se necessário
}
