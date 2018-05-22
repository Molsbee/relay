# Relay
Populate with information

## Features
* Create User
* Disable User
* Create Channel
* Archive/Disable Channel
* Create Message
* Delete Message
* Edit/Update Message

## Models/Relationship

User
```json
{
    "firstName": "john",
    "lastName": "smith",
    "userName": "john",
    "password": "possibly-encrypt",
    "subscribeChannels": ["test", "test2"]
}
```
Channel
```json
{
    "name": "test",
    "messages": [{},{}]
}
```
Message
```json
{
  "user": "userName",
  "createdAt": "timestamp",
  "updatedAt": "timestamp",
  "value": "some amount of test"
}
```


## Requires MongoDB

### Current setup for running locally
#### Mongo Docker Documentation - https://hub.docker.com/_/mongo/

```
docker run --name relay -p 27017:27017 -d mongo
```

Run command exposes docker over port 27017. Currently not setup to persist data to storage.

```
docker run -it --link relay:mongo --rm mongo sh -c 'exec mongo "$MONGO_PORT_27017_TCP_ADDR:$MONGO_PORT_27017_TCP_PORT/test"'
```


# Notes
```java
public class Thing {

    @GetMapping("/user")
    public Mono<User> current(@AuthenticationPrincipal Mono<User> principal) {
        return principal;
    }    
}
```
