# Blind auction system

# Project Structure
```
root
├── user-service
│   ├── src
│   │   └── main
│   │       └── java/com/yourcompany/users
│   │           └── UserController.java
│   │           └── User.java
│   │           └── UserRepository.java
│   └── pom.xml
│
├── auction-service
│   ├── src
│   │   └── main
│   │       └── java/com/yourcompany/auctions
│   │           └── AuctionController.java
│   │           └── Auction.java
│   │           └── Product.java
│   │           └── Bid.java
│   │           └── AuctionRepository.java
│   │           └── BidRepository.java
│   └── pom.xml
│
└── pom.xml
```


API Details and Endpoints explanation:

# Auction Service 

1) createAuction:

    - Validates the seller's token by calling the User Service.
    - If validation passes, saves the auction details and sets the seller’s token.

2) placeBid:

    - Validates the buyer’s token.
    - Ensures that the bid amount meets the minimum bid price.
    - Associates the bid with the auction and saves it.

3) getAllAuctions:

    - Returns a list of all auctions available for bidding.

4) endAuction:

    - Ends the auction by finding the highest bid.
    - In case of a tie on the bid amount, the earliest bid (timestamp) is selected as the winner.

5) validateUserToken:

    - Calls the User Service to validate user tokens by role type (buyer or seller).
    - Throws an error if the token is invalid or the validation service fails.
 
# Auctions Endpoints 
    - /auctions/register: Creates a new auction with a seller’s token and minimum bid requirement.
    - /auctions/list: Lists all open auctions.
    - /auctions/{auctionId}/bid: Places a bid on an auction if the bid meets the minimum amount and the auction is open.
    - /auctions/{auctionId}/close: Closes the auction and returns the winning bid, if any. The first highest bid in descending order is considered the winner.

Notes
    - The RestTemplate in validateUserToken simulates a call to the UserService to verify tokens.
    - Error Handling: Error messages are thrown if auction or bid validation fails.
    - Comparators: endAuction selects the highest bid and resolves ties by timestamp.


# User Service

1) Token Map:

    - userTokens is a Map<String, String> that simulates a database of user tokens 
      mapped to user types (e.g., "seller" or "buyer").
    - For this example, tokens are pre-populated. In a real application, these would likely be stored 
      in a database or managed by an authentication service.

2) validateUserToken Endpoint:

    - URL: /users/validate
    - Parameters:
        - token: The token to validate.
        - type: Expected type of user ("seller" or "buyer").

3) Validation Logic:
    - Checks if the provided token exists in the userTokens map.
    - Verifies that the token corresponds to the correct user type (buyer or seller).

4) Return:
    - Returns "Valid User" if the token and type match.
    - Returns "Invalid Token" if the token doesn’t exist in the map.
    - Returns "Invalid User Type" if the token exists but is for the wrong user type.


# Sample Data Pre-population
To pre-populate the database, Add a CommandLineRunner bean to insert sample users when the application starts:

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) { 

    }
