##########################Tables cleaning###########################
DELETE
FROM token;
DELETE
FROM user;
##########################Sequences resetting#######################
ALTER TABLE token
    AUTO_INCREMENT = 1;
ALTER TABLE user
    AUTO_INCREMENT = 1;
