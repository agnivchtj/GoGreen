# API Docs v1.4

## ENDPOINTS


### /register

> Used to register a new user into the system

* Request:
```
{
	"username" : "your_username",
	"password" : "hashed_password", // Hashing happens on client side
	"email" : "your_email"
}
```
* Response:
```
if (successful) {
	"status" : "Success",
	"id" : "identification_number"
} else {
	"status" : "Error",
	"details" : "reason" // ex: Missing param username
}
```

### /auth

> Used to authenticate a user into the system

* Request:
```
{
    // Accepted methods: id_only, username_only,email_only
	"method" : "authorization_method",
	"identity" : "id/username/email",  // Based on method attribute
	"password" : "hashed_password"
}
```
* Response:
```
if (successful) {
	"status" : "Granted"
} else {
	"status" : "Error",
	"details" : "reason" // ex: Incorrect Details
}
```

### /activity

> Add a new activity to the system

* Request:
```
{
	"username" : "your_username",
	"type" : "activity_type",  // ex: Eat a vegetarian meal
	"comment" : "additional_comment" // Can be blank
}
```
* Response:
```
if (successful) {
	"status" : "Added"
} else {
	"status" : "Error",
	"details" : "reason" // ex: Missing type of activity
}
```

### /friend

> Add a new friend to the system

* Request:
```
{
	"username" : "your_username",
	"friend" : "friend_username"
}
```
* Response:
```
if (successful) {
	"status" : "Added"
} else {
	"status" : "Error",
	"details" : "reason" // ex: Missing type of activity
}
```
### /getactivity

> Get the activities of a certain user

* Request:
```
{
	"username" : "your_username"
}
```
* Response:
```
if (successful) {
	"status" : "Success"
	"activities": [
	        {
	            "type" : "activity_type",
	            "comment" : "your_comment",
	            "timestamp" : "timestamp",
            	"points" : "points_obtained"
	        },
	        {
	            ...
	        }
	    ]
} else {
	"status" : "Error",
	"details" : "reason" // ex: Missing type of activity
}
```
#### Allowed activity types:
```
[
    "Vegetarian meal",
    "Vegan meal",
    "Local produce",
    "Bike",
    "Public transport",
    "Temperature",
    "Solar panel"
]
```
### /getfriends

> Get the friends of a certain user

* Request:
```
{
	"username" : "your_username"
}
```
* Response:
```
if (successful) {
	"status" : "Success"
	"friends": [
	        {
	            "username" : "friend_username"
	        },
	        {
	            ...
	        }
	    ]
} else {
	"status" : "Error",
	"details" : "reason" // ex: Missing type of activity
}
```
### /stat

> Get the statistics of a certain user

* Request:
```
{
	"username" : "your_username"
}
```
* Response:
```
if (successful) {
    "status" : "Success",
	"username" : "your_username",
	"points" : "current_points",
	"saved" : "kg_of_co2_saved",
	"recent": [
	    {
	        "type" : "activity_type",
	        "comment" : "your_comment",
	        "timestamp" : "timestamp",
            "points" : "points_obtained"
	    },
	    {
	        ...
	    }
	]
} else {
	"status" : "Error",
	"details" : "reason" // ex: Missing type of activity
}
```
### /stats

> Get the statistics of a certain user and all his friends

* Request:
```
{
	"username" : "your_username"
}
```
* Response:
```
if (successful) {
    "status" : "Success",
	"stats" : [
	    {
        	"username" : "your_username",
        	"points" : "current_points",
        	"saved" : "kg_of_co2_saved",
        	"recent": [
        	    {
        	        "type" : "activity_type",
        	        "comment" : "your_comment",
        	        "timestamp" : "timestamp",
                    "points" : "points_obtained"
                },
                {
                    ...
                }
            ]
	    }, 
	    {
	        // The array contains all the friends with same format.
	    },
	    {
	        ...
	    }
	]
} else {
	"status" : "Error",
	"details" : "reason" // ex: Missing type of activity
}
```