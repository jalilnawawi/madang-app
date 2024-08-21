# Madang-App
## Authentication
### `POST` Register User

* Description : Register to Application
* URL : api/v1/auth/user/register
* Auth Required : No

**Request Body** : 
````json
{
  "name" : "string",
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
    "name" : "string",
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
  "name" : "string",
  "description" : "string",
  "pictureId" : "UUID",
  "address" : "string",
  "category" : "enum"
}
````

**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "restaurantId" : "UUID",
    "name" : "string",
    "description" : "string",
    "pictureId" : "UUID",
    "address" : "string"
  },
  "message" : "success"
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
  "data" : [
    {
      "restaurantId" : "UUID",
      "name" : "string",
      "description" : "string",
      "pictureLink" : "string",
      "address" : "string",
      "category" : "enum",
      "rating" : "float"
    },
    {
      "restaurantId" : "UUID",
      "name" : "string",
      "description" : "string",
      "pictureLink" : "string",
      "address" : "string",
      "category" : "enum",
      "rating" : "float"
    }
  ],
  "message" : "success"
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
  "data" : {
    "restaurantId" : "UUID",
    "name" : "string",
    "description" : "string",
    "pictureLink" : "string",
    "address" : "string",
    "category" : "enum",
    "product" : {
      "name" : "string",
      "price" : "double",
      "type" : "enum"
    },
    "restaurantRating" : "float",
    "customerReviews" : [
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

### `POST` Create Restaurant Image
* Description : Insert restaurant image to database
* URL : api/v1/restaurant/images
* Auth Required : Yes

**Request Body** :
````json
{
  "restaurantId" : "UUID",
  "imageLink" : "string",
  "imageSize" : "enum"
}
````

**Response Body** :
* **✅ 200 OK**
````json
{
  "data" : {
    "restaurantId" : "UUID"
    "imageId" : "UUID",
    "imageLink" : "string",
    "imageSize" : "enum"
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

### `GET` Get Restaurant Image
* Description : Get Restaurant image by imageId
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
      "size" : "small"
    },
    {
      "imageId" : "UUID",
      "imageLink" : "string",
      "size" : "medium"
    },
    {
      "imageId" : "UUID",
      "imageLink" : "string",
      "size" : "large"
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
  }
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
