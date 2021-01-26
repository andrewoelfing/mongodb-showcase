# MongoDB Atlas Showcase

MongoDB ist ein dokumentenorientiertes NoSQL-Datenbankmanagementsystem, das in der Programmiersprache C++ geschrieben ist. Da die Datenbank dokumentenorientiert ist, kann sie Sammlungen von JSON-ähnlichen Dokumenten verwalten. So können viele Anwendungen Daten auf natürlichere Weise modellieren, da die Daten zwar in komplexen Hierarchien verschachtelt werden können, dabei aber immer abfragbar und indizierbar bleiben. [[1](https://de.wikipedia.org/wiki/MongoDB)]

1) [Get Started](#getstarted)
2) [Java](#java)
3) [Go](#go)

<a name="getstarted"></a>
## Get Started

Java[[2](https://openjdk.java.net/)] installiert?

Go[[3](https://golang.org/)] installiert?

Favorisierter Editor installiert (VS Code, IntelliJ, ...)?

```
git clone https://github.com/andrewoelfing/mongodb-showcase
```


<a name="java"></a>
## Java

First things first - die `application.properties` anpassen. Diese liegt in folgendem Pfad (relativ zum Projekt-Root) `java/src/main/resources/application.properties`.
Hier bitte den Connection-Sting ersetzen.

Danach kann die Anwendung gestartet werden. Bitte beachtet, dass MongoDB Atlas TLS1.2 für eine Connection benötigt. Am Einfachsten geht das über eine Environmentvariable `jdk.tls.client.protocols=TLSv1.2`

```
cd java
JAVA_TOOL_OPTIONS=-Djdk.tls.client.protocols=TLSv1.2 ./gradlew build bootRun
```

<a name="go"></a>
## Go

First things first - die `application.properties` anpassen. Diese liegt in folgendem Pfad (relativ zum Projekt-Root) `go/application.properties`.
Hier bitte den Connection-Sting ersetzen.

```
cd go
go get -u go.mongodb.org/mongo-driver/mongo		
go get -u github.com/magiconair/properties
go run main.go
```

