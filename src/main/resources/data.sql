-- Seed Employees
INSERT INTO employees (name, age, department, email) VALUES
('Alice Johnson', 29, 'Engineering', 'alice.johnson@example.com'),
('Bob Smith', 34, 'Product', 'bob.smith@example.com'),
('Catherine Davis', 41, 'Human Resources', 'catherine.davis@example.com'),
('David Miller', 26, 'Marketing', 'david.miller@example.com'),
('Elena Rostova', 38, 'Finance', 'elena.rostova@example.com');

-- Seed Users (password: password123)
INSERT INTO app_users (name, email, password, role) VALUES
('John Doe', 'john.doe@example.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'USER'),
('Jane Smith', 'jane.smith@example.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'USER'),
('Robert Brown', 'robert.brown@example.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN');

-- Seed Orders
INSERT INTO orders (product_name, quantity, total_amount, status, created_at, user_id) VALUES
('Mechanical Keyboard', 1, 129.99, 'DELIVERED', CURRENT_TIMESTAMP, 1),
('Wireless Mouse', 2, 59.98, 'SHIPPED', CURRENT_TIMESTAMP, 1),
('27-inch 4K Monitor', 1, 399.99, 'PLACED', CURRENT_TIMESTAMP, 2),
('USB-C Docking Station', 1, 89.50, 'DELIVERED', CURRENT_TIMESTAMP, 2),
('Noise-Cancelling Headphones', 1, 249.00, 'PLACED', CURRENT_TIMESTAMP, 3);
