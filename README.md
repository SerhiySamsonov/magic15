<h1>
Launch instructions
</h1>
Have java 17 installed and run:

```
./mvnw clean package && java -jar target/magic15-1.0-SNAPSHOT.jar
```
<br>

**OR**
<br>
Have docker installed and run:
```
docker build -t magic15:ssamsonov . && docker run -i magic15:ssamsonov
```