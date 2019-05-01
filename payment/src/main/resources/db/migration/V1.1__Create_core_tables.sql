CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `auth_id` bigint(20) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    `access_token` varchar(64),
    `created_at` timestamp NULL,
    `updated_at` timestamp NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `account` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `balance` int NOT NULL,
   `owner_id` bigint(20),
   `created_at` timestamp NULL,
   `updated_at` timestamp NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE `transaction` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `from_account_id` bigint(20) NOT NULL,
    `to_account_id` bigint(20) NOT NULL,
    `amount` int NOT NULL,
    `created_at` timestamp NULL,
    `updated_at` timestamp NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `bill` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `to_account_id` bigint(20) NOT NULL,
    `transaction_id` bigint(20),
    `amount` int NOT NULL,
    `created_at` timestamp NULL,
    `updated_at` timestamp NULL,
    PRIMARY KEY (`id`)
);
