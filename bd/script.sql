CREATE TABLE client_type
(
    id SERIAL PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL UNIQUE
);

-- Insert types into clientType table
INSERT INTO client_type (type_name) VALUES ('customer'), ('supplier');

-- Create person table
CREATE TABLE person
(
    id SERIAL PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    email     VARCHAR(100) UNIQUE,
    phone     VARCHAR(20),
    type_id   INTEGER NOT NULL,
    FOREIGN KEY (type_id) REFERENCES client_type (id) ON DELETE CASCADE
);

-- Create parts table
CREATE TABLE part
(
    id           SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    person_id         INTEGER,
    price             INTEGER NOT NULL,
    quantity_in_stock INTEGER NOT NULL,
    description       VARCHAR(255),
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE
);

-- Create categories table
CREATE TABLE category
(
    id SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE
);

-- Create part_categories table
CREATE TABLE part_category
(
    part_id     INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    PRIMARY KEY (part_id, category_id),
    FOREIGN KEY (part_id) REFERENCES part(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);

-- Create orders table
CREATE TABLE "order"
(
    id   SERIAL PRIMARY KEY,
    person_id  INTEGER,
    order_date DATE,
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE
);

-- Create order_items table
CREATE TABLE order_item
(
    id SERIAL PRIMARY KEY,
    order_id      INTEGER,
    part_id       INTEGER,
    quantity      INTEGER NOT NULL,
    price         INTEGER NOT NULL,
    FOREIGN KEY (order_id) REFERENCES "order" (id) ON DELETE CASCADE,
    FOREIGN KEY (part_id) REFERENCES part (id) ON DELETE CASCADE
);