CREATE TABLE user (
first_name varchar(50) NOT NULL,
last_name  varchar(50) NOT NULL,
user_id varchar(50) NOT NULL,
PRIMARY KEY (user_id)
);

INSERT INTO user(first_name, last_name, user_id) values('First', 'Last', 'ExampleUser');