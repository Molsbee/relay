## Requires MongoDB

### Current setup for running locally
#### Mongo Docker Documentation - https://hub.docker.com/_/mongo/

```
docker run --name relay -p 27017:27017 -d mongo
```

Run command exposes docker over port 27017. Currently not setup to persist data to storage.