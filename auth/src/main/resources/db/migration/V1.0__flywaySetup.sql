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
    `db_username` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `redirect_uri` varchar(255) DEFAULT NULL,
    `schema_name` varchar(255) DEFAULT NULL,
    `owner_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_hmxyq9oplk2w6au8uo9vs6qtw` (`owner_id`),
    UNIQUE KEY `UK_6e90jvkn0xacoatqyje680hyn` (`client_id`),
    UNIQUE KEY `UK_17b4ry3b0kmnndt01fcy6b5rc` (`db_username`),
    UNIQUE KEY `UK_lspnba25gpku3nx3oecprrx8c` (`name`),
    UNIQUE KEY `UK_il26ce3j3lgn147e4oav6xuch` (`schema_name`)
);

CREATE TABLE `authorisation` (
    `application_id` bigint(20) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    `auth_code` longblob,
    `access_token_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`application_id`,`user_id`),
    KEY `FKfcopwx2u8ejwnyqxunw96fyfe` (`user_id`),
    KEY `FK8n49ffbino2bh1ycpjt3to41o` (`access_token_id`)
);

CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `pw_hash` longblob,
    `salt` longblob,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`)
);
