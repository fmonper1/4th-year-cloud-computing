CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `access_token` varchar(255) NOT NULL,
    `auth_id` bigint(20) NOT NULL,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`auth_id`)
);

CREATE TABLE `application` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `description` varchar(255) DEFAULT NULL,
   `name` varchar(255) DEFAULT NULL,
   `owner_id` bigint(20) NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`owner_id`) REFERENCES `user`(`id`)
);

CREATE TABLE `db_application` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `db_username` varchar(255) DEFAULT NULL,
    `schema_name` varchar(255) DEFAULT NULL,
    `application_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`application_id`) REFERENCES `application`(`id`),
    UNIQUE KEY (`db_username`),
    UNIQUE KEY (`schema_name`)
);

CREATE TABLE `deployment` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `image_path` varchar(255) DEFAULT NULL,
    `url` varchar(255) DEFAULT NULL,
    `application_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`application_id`) REFERENCES `application`(`id`)
);

