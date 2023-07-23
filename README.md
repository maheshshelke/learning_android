# learning_android

## Initial setup 
1. created app using wizard with empty activity
2. added 2 buttons and handled their click events 

## REST call
Sending GET request and getting data from server using retrofit library 

sending request to ```http://13.233.230.234:5000/api/v1```
and getting response 
```
{
    "status": true,
    "statusCode": 10200,
    "message": "Request Processed Successfully",
    "data": "Hellow, World!, from env:dev"
}
```
Note: This response has data as string. In other responses it could be different objects

