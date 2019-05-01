CREATE TABLE `access_token` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `access_token` longblob,
    `expires` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `application` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `client_id` varchar(255) DEFAULT NULL,
    `client_secret` longblob,
    `name` varchar(255) DEFAULT NULL,
    `redirect_uri` varchar(255) DEFAULT NULL,
    `owner_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`owner_id`),
    UNIQUE KEY (`client_id`),
    UNIQUE KEY (`name`)
);

CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `pw_hash` longblob,
    `salt` longblob,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`name`)
);


CREATE TABLE `authorisation` (
    `application_id` bigint(20) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    `auth_code` longblob,
    `access_token_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`application_id`,`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
    FOREIGN KEY (`access_token_id`)  REFERENCES `access_token`(`id`)
);