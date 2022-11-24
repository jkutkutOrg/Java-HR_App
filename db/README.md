# DB:

PostgreSQL with pgAdmin.

## Use:
### First time:
#### Secrets:
All the secrets are in the .env file. The following script will help you to create it:
```
./scripts/setSecrets.sh
```

#### Create containers:
```
./scripts/createDB.sh
./scripts/createWebController.sh
```

### Stop containers:
```
./scripts/stopAll.sh
```

### Start containers:
```
./scripts/startAll.sh
```
