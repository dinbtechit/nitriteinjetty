# Jetty REST API using Nitrite NoSql DB (in-memory vs File)

A sample project demonstrates Jetty (Jersey REST API) and Nitrite NoSQL DB integration.

To run project

```bash
mvn jetty:run
```

Access Rest in cURL. (Alternatively, you can also use a browser or a REST client.)

#### Inserting

<small> Note - Remember the last entry only. </small>

```
curl -v -X GET http://localhost:8080/api/db/memory/insert
```

<small> Note - Works as expectred. </small>

```bash
curl -v -X GET http://localhost:8080/api/db/file/insert
```


#### Finding

<small> Note - Returns empty results </small>

```
curl -v -X GET http://localhost:8080/api/db/memory/find/0/10000
```

<small> Note - Works as expectred. </small>

```bash
curl -v -X GET http://localhost:8080/api/db/file/find/0/10000
```