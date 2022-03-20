# Shell Application Using Spring Shell
    
    <dependency>
        <groupId>org.springframework.shell</groupId>
        <artifactId>spring-shell-starter-jna</artifactId>
        <version>2.1.0-M2</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.experimental</groupId>
        <artifactId>spring-native</artifactId>
        <version>${spring-native.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.experimental</groupId>
        <artifactId>spring-aot</artifactId>
        <version>${spring-native.version}</version>
    </dependency>


# MAVEN 3.8 + (settings.xml)
Using mirror maven-default-http-blocker (http://0.0.0.0/) for repository.spring.milestone
Blocked mirror for repositories: [repository.spring.milestone (http://repo.spring.io/milestone, default, releases+snapshots)]

    <settings>
        <mirrors>
            <mirror>
                <id>repository.spring.milestone</id>
                <mirrorOf>repository.spring.milestone</mirrorOf>
                <name>Spring Milestore</name>
                <url>http://repo.spring.io/milestone</url>
                <blocked>false</blocked>
            </mirror>
        </mirrors>
    </settings>