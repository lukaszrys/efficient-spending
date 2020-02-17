db.createUser(
    {
      user: "authorization",
      pwd: "authorization",
      roles: [
        {
          role: "readWrite",
          db: "authorization-service-db"
        }
      ]
    }
);