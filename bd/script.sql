CREATE TABLE client_types
(
    type_id SERIAL PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO client_types (type_name) VALUES ('customer'), ('supplier');

CREATE TABLE clients
(
    client_id SERIAL PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    email     VARCHAR(100) UNIQUE,
    phone     VARCHAR(20),
    type_id   INTEGER NOT NULL,
    FOREIGN KEY (type_id) REFERENCES client_types (type_id) ON DELETE CASCADE
);

CREATE TABLE parts
(
    part_id           SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    person_id         INTEGER,
    price             INTEGER NOT NULL,
    quantity_in_stock INTEGER NOT NULL,
    description       VARCHAR(255),
    FOREIGN KEY (person_id) REFERENCES clients (client_id) ON DELETE CASCADE
);

CREATE TABLE categories
(
    category_id SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE part_categories
(
    part_id     INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    PRIMARY KEY (part_id, category_id),
    FOREIGN KEY (part_id) REFERENCES parts (part_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    order_id   SERIAL PRIMARY KEY,
    person_id  INTEGER,
    order_date DATE,
    FOREIGN KEY (person_id) REFERENCES clients (client_id) ON DELETE CASCADE
);

CREATE TABLE order_items
(
    order_item_id SERIAL PRIMARY KEY,
    order_id      INTEGER,
    part_id       INTEGER,
    quantity      INTEGER NOT NULL,
    price         INTEGER NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    FOREIGN KEY (part_id) REFERENCES parts (part_id) ON DELETE CASCADE
);