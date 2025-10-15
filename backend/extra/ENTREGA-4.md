<pre>
├── main
│   ├── java
│   │   └── ifsc
│   │       └── edu
│   │           └── tpj
│   │               ├── controller
│   │               ├── dto
│   │               ├── model
│   │               ├── repository
│   │               ├── service
│   │               └── util
│   └── resources
│       ├── static
│       └── templates
└── test
    └── java
        └── ifsc
            └── edu
                └── tpj
</pre>

```java
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.5.6'
    id 'io.spring.dependency-management' version '1.1.7'
}

group = 'ifsc.edu'
version = '0.0.1-SNAPSHOT'
description = 'tpj'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'org.postgresql:postgresql'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
    useJUnitPlatform()
}

```

