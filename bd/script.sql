CREATE TABLE clients
(
    client_id SERIAL PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    email     VARCHAR(100) UNIQUE,
    phone     VARCHAR(20)
);

CREATE TABLE suppliers
(
    supplier_id SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    phone       VARCHAR(20),
    email       VARCHAR(100) UNIQUE
);

CREATE TABLE parts
(
    part_id           SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    supplier_id       INTEGER,
    price             INTEGER      NOT NULL,
    quantity_in_stock INTEGER      NOT NULL,
    description       VARCHAR(255),
    FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id) ON DELETE CASCADE
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
    client_id  INTEGER,
    order_date DATE,
    FOREIGN KEY (client_id) REFERENCES clients (client_id) ON DELETE CASCADE
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