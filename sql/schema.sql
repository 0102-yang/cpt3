drop table if exists user;
drop table if exists `yaan`;

create table user
(
    id       int primary key auto_increment,
    username varchar(255) not null unique,
    password varchar(64)  not null,
    age      int default null,
    gender   boolean comment 'male for 1 and female for 0'
) auto_increment = 10000
  engine = InnoDB
  default charset = utf8;

CREATE TABLE doctor
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `name`            varchar(30)   DEFAULT NULL,
    `title`           varchar(50)   DEFAULT NULL,
    `hospital_name`   varchar(50)   DEFAULT NULL,
    `department_name` varchar(50)   DEFAULT NULL,
    `clinic_name`     varchar(50)   DEFAULT NULL,
    `score`           float(11, 1)  DEFAULT NULL,
    `hits`            int(11)       DEFAULT NULL,
    `labels`          varchar(100)  DEFAULT NULL,
    `goodat`          varchar(2500) DEFAULT NULL,
    `about`           text          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;