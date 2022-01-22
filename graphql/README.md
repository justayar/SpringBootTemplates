# GraphQL Demo Application
This application is a simple example of GraphQL. For the demonstration of how it works, there is some mutations and query commands added to test section of this document.
## Requirements
For building and running the application you need:

- [JDK 1.11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 4](https://maven.apache.org)
- [Docker](https://docs.docker.com/get-docker/)

## How to test GraphQL endpoints

1. Firstly, you need to run a PostgreSQL server locally. To make it simple, we added related docker container which runs with Docker Compose.
To run Docker container, you must go to the source path, and call the following command:

```
docker compose up
```

2. Then, we need to run Spring application.

3. After run the application, you can open localhost:8010 on your browser.
It will open GraphiQL playground to test Graphql operations. You can use below
graphql commands to test your application.

### Adding new brand with mutation

```
mutation {
    addNewBrand(brandInput: {name: "Volvo", foundedBy: "Assar Gabrielsson, Gustav Larson"}) {
        id
        name
        foundedBy
        createdAt
    }
}
```
### Getting brand with id (You can use brand id which you created with mutation)

```
{
  getBrandById(id: "???") {
    id
    name
    foundedBy
    createdAt
  }
}
```
### Getting brand with name (You can use brand name which you created with mutation)

```
{
  getBrandByName(name: "Volvo") {
    id
    name
    foundedBy
    createdAt
  }
}
```
### Getting first n brands by skipping first m (n -> take, m-> skip)

```
{
  getAllBrands(skip: 0, take: 5) {
      id
      name
      foundedBy
      createdAt
  }
}
```
### Adding new vehicle with mutation

```
mutation {
    addNewVehicle(vehicleInput: {name: "S3", horsePower: 306, bodyMass: 1500 , 
    fuelType: PETROL, brandId: "???" }) {
        id
        name
        horsePower
        bodyMass
        fuelType
        createdAt
    }
}
```
### Getting vehicle with id (You can use vehicle id which you created with mutation)

```
{
  getVehicleById(id: "???") {
     id
     name
     horsePower
     bodyMass
     fuelType
     createdAt
  }
}
```
### Getting vehicle with name (You can use vehicle name which you created with mutation)

```
{
  getVehicleByName(name: "S3") {
     id
     name
     horsePower
     bodyMass
     fuelType
     createdAt
  }
}
```
### Getting vehicles with brand name (You can use brand name which you created with mutation)

```
{
  getVehiclesByBrand(brandId: "???" ) {
     id
     name
     horsePower
     bodyMass
     fuelType
     createdAt
  }
}
```
### Getting first n vehicles by skipping first m (n -> take, m-> skip)

```
{
  getAllVehicles(skip: 0, take: 5) {
     id
     name
     horsePower
     bodyMass
     fuelType
     createdAt
  }
}
```
