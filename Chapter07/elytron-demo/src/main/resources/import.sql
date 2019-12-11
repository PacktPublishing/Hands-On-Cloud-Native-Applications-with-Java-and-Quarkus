CREATE TABLE quarkus_user (
    id INT,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);
INSERT INTO quarkus_user (id, username, password, role) VALUES (1, 'admin', 'password123', 'admin');
INSERT INTO quarkus_user (id, username, password, role) VALUES (2, 'frank','password123', 'user');

INSERT INTO customer (id, name, surname) VALUES ( nextval('customerId_seq'), 'John','Doe');
INSERT INTO customer (id, name, surname) VALUES ( nextval('customerId_seq'), 'Fred','Smith');

