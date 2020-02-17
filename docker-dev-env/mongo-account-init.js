db.createUser(
    {
      user: "account",
      pwd: "account",
      roles: [
        {
          role: "readWrite",
          db: "account-service-db"
        }
      ]
    }
);