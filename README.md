# Madang-App
## Authentication
### `POST` Register User

* Description : Register to Application
* URL : api/v1/auth/user/register
* Auth Required : No

**Request Body** : 
````json
{
  "username" : "string",
  "email" : "string",
  "password" : "string",
  "confirmPassword" : "string"
}
````

**Response Body** :

* **✅ 200 OK**
````json
{
  "data" : {
    "username" : "string",
    "email" : "string",
    "userStatus" : "enum"
  },
  "message" : "Register success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "Password not matched"
}
````
### `POST` Login User
* Description : Register to Application
* URL : api/v1/auth/user/login
* Auth Required : No

**Request Body** :
````json
{
  "email" : "string",
  "password" : "string"
}
````

**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "accessToken" : "JWT Token",
    "refreshToken" : "JWT Token",
    "userId" : "UUID",
    "userStatus" : "ACTIVE"
  },
  "message" : "login success"
}
````

* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "email or password is invalid"
}
````
---
## Restaurant Service
### `POST` Create Restaurant

* Description : Create a new Restaurant
* URL : api/v1/restaurant
* Auth Required : Yes

**Request Body** :

````json
{
  "name": "string",
  "description": "string",
  "imageId": "UUID",
  "address": "string",
  "category": "enum"
}
````

**Response Body** :
* **✅ 200 OK**

````json
{
  "data": {
    "restaurantId": "UUID",
    "name": "string",
    "description": "string",
    "imageId": "UUID",
    "address": "string"
  },
  "message": "success"
}
````

* **❌ 404 Bad Request**
````json
{
  "data" : null,
  "message" : "image already used"
}
````

### `GET` All Restaurant

* Description : Get list of restaurant
* URL : api/v1/restaurant
* Auth Required : Yes

**Response Body : ✅ 200 OK**

````json
{
  "data": [
    {
      "restaurantId": "UUID",
      "name": "string",
      "description": "string",
      "imageLink": "string",
      "address": "string",
      "category": "enum",
      "rating": "float"
    },
    {
      "restaurantId": "UUID",
      "name": "string",
      "description": "string",
      "imageLink": "string",
      "address": "string",
      "category": "enum",
      "rating": "float"
    }
  ],
  "message": "success"
}
````
### `GET` Get Detail Restaurant by Id

* Description : Get Detail of Restaurant
* URL  : api/v1/restaurant/{restaurantId}
* Auth Required : Yes

**Response Body** :
* **✅ 200 OK**

````json
{
  "data": {
    "restaurantId" : "UUID",
    "name" : "string",
    "description" : "string",
    "imageLink" : "string",
    "address" : "string",
    "category" : "enum",
    "product" : {
      "name" : "string",
      "price" : "double",
      "type" : "enum"
    },
    "restaurantRating": "float",
    "customerReviews": [
      {
        "name" : "string",
        "review" : "string",
        "date" : "timestamp"
      },
      {
        "name" : "string",
        "review" : "string",
        "date" : "timestamp"
      }
    ]
  }
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "restaurantId not found"
}
````
---
## Product Service
### `POST` Create Product

* Description : Create a new product
* URL : api/v1/product
* Auth Required : Yes

**Request Body** :
````json
{
  "productName" : "string",
  "price" : "double",
  "category" : "enum",
  "productImageLink" : "string",
  "restaurantId" : "UUID"
}
````
**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "productId" : "UUID",
    "productName" : "string",
    "price" : "double",
    "category" : "enum",
    "productImageLink" : "string",
    "restaurantId" : "UUID"
  },
  "message" : "success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "restaurantId not found"
}
````

### `GET` Get All Product

* Description : Get list of product
* URL : api/v1/product
* Auth Required : Yes

**Response Body : ✅ 200 OK**
````json
{
  "data" : [
    {
      "productId" : "UUID",
      "productName" : "string",
      "price" : "double",
      "category" : "enum",
      "productImageLink" : "string",
      "restaurantId" : "UUID"
    },
    {
      "productId" : "UUID",
      "productName" : "string",
      "price" : "double",
      "category" : "enum",
      "productImageLink" : "string",
      "restaurantId" : "UUID"
    }
  ],
  "message" : "success"
}
````

### `GET` Get Product by Id
* Description : Get detail of product
* URL : api/v1/product/{productId}
* Auth Required : Yes

**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "productId" : "UUID",
    "productName" : "string",
    "price" : "double",
    "category" : "enum",
    "productImageLink" : "string",
    "productRating" : "float",
    "restaurantId" : "UUID"
  },
  "message" : "success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "productId not found"
}
````

### `PUT` Update Product Price
* Description : Update product price
* URL : api/v1/product/{productId}
* Auth Required : Yes

**Request Body** :
````json
{
  "price" : "double"
}
````
**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "productName" : "string",
    "price" : "double"
  },
  "message" : "success"
}
````
* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "productId not found"
}
````
---
## Image Service
### `POST` Create Image
* Description : Insert image to database
* URL : api/v1/images
* Auth Required : Yes

**Request Body** : 
````json
{
  "imageLink" : "string",
  "imageSize" : "enum",
  "category" : "enum"
}
````

**Response Body : ✅ 200 OK**
````json
{
  "data" : {
    "imageId" : "UUID",
    "imageLink" : "string",
    "imageSize" : "enum",
    "category" : "enum"
  },
  "message" : "success"
}
````

### `GET` Get Image by imageId
* Description : Get detail image
* URL : api/v1/restaurant/images/{imageId}
* Auth Required : Yes

**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : [
    {
      "imageId" : "UUID",
      "imageLink" : "string",
      "size" : "small",
      "category" : "product_image"
    },
    {
      "imageId" : "UUID",
      "imageLink" : "string",
      "size" : "medium",
      "category" : "restaurant_image"
    },
    {
      "imageId" : "UUID",
      "imageLink" : "string",
      "size" : "large",
      "category" : "restaurant_image"
    }
  ],
  "message" : "success"
}
````

* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "imageId not found"
}
````
---
## Seat Service
### `POST` Create Seat
* Description : Insert seat to database
* URL : api/v1/seat
* Auth Required : Yes

**Request Body** :
````json
{
  "seatName" : "string",
  "countTables" : "integer",
  "countChairs" : "integer",
  "countFlowers" : "integer",
  "price" : "double",
  "category" : "enum"
}
````

**Response Body : ✅ 200 OK**
````json
{
  "data" : {
    "seatId" : "UUID",
    "seatName" : "string"
    "countTables" : "integer",
    "countChairs" : "integer",
    "countFlowers" : "integer",
    "price" : "double",
    "category" : "enum"
  },
  "message" : "success"
}
````
### `GET` Get Seat
* Description : Get list of all seat
* URL : api/v1/seat
* Auth Required : Yes

**Response Body : ✅ 200 OK**
````json
{
  "data" : [
    {
      "seatName" : "chair",
      "countTables" : 0,
      "countChairs" : 1,
      "countFlowers" : 0,
      "price" : 0,
      "category" : "chair"
    },
    {
      "seatName" : "table",
      "countTables" : 1,
      "countChairs" : 0,
      "countFlowers" : 0,
      "price" : 0,
      "category" : "table"
    },
    {
      "seatName" : "flower",
      "countTables" : 0,
      "countChairs" : 0,
      "countFlowers" : 1,
      "price" : 25000,
      "category" : "flower"
    },
    {
      "seatName" : "work table",
      "countTables" : 1,
      "countChairs" : 1,
      "countFlowers" : 0,
      "price" : 10000,
      "category" : "work_table"
    }
  ],
  "message" : "success"
}
````

### Get Seat by Id
* Description : Get Seat by seatId
* URL : api/v1/seat/{seatId}
* Auth Required : Yes

**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "seatId" : "UUID",
    "seatName" : "flower",
    "countTables" : 0,
    "countChairs" : 0,
    "countFlowers" : 1,
    "price" : 25000,
    "category" : "flower"
  },
  "message" : "success"
}
````

* **❌ 400 Bad Request**
````json
{
  "data" : null,
  "message" : "seatId not found"
}
````
