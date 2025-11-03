/*CREATE database ecommerce;
USE ecommerce;

CREATE TABLE users (
id_user INT AUTO_INCREMENT PRIMARY KEY,
nome_user VARCHAR (100) NOT NULL,
email_user VARCHAR (200) NOT NULL,
senha_user VARCHAR (100) NOT NULL,
ROLE ENUM ('admin', 'customer') NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE products (
id_product INT AUTO_INCREMENT PRIMARY KEY,
nome_product VARCHAR (300) NOT NULL,
slug_product VARCHAR (200) UNIQUE NOT NULL,
descricao_product TEXT NOT NULL,
imagemurl_product VARCHAR (600) NOT NULL,
categoria_product VARCHAR (100) NOT NULL,
ativo BOOLEAN DEFAULT TRUE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP) ENGINE=InnoDB;

CREATE TABLE prices (
id_price INT AUTO_INCREMENT PRIMARY KEY,
product_id INT NOT NULL,
stripe_price_id VARCHAR(50) UNIQUE NOT NULL,
amount_price INT NOT NULL,                
currency_price VARCHAR(10) NOT NULL DEFAULT 'BRL',
vigente_price BOOLEAN DEFAULT TRUE,        
FOREIGN KEY (product_id) REFERENCES products(id_product) ON DELETE CASCADE);*/

/*CREATE TABLE inventory (
id_inventory INT AUTO_INCREMENT PRIMARY KEY,
product_id INT NOT NULL,
qty_on_hand INT NOT NULL DEFAULT 0,
FOREIGN KEY (product_id) REFERENCES products(id_product) ON DELETE CASCADE) ENGINE=InnoDB;*/
    
/*CREATE TABLE orders (
id_order INT AUTO_INCREMENT PRIMARY KEY,
user_id INT NOT NULL,
status_order ENUM ('pending', 'paid', 'canceled', 'fulfilled', 'picked_up') DEFAULT 'pending', 
total_orders INT NOT NULL,
currency_order VARCHAR(10) NOT NULL DEFAULT 'BRL',
stripe_checkout_id VARCHAR(100) UNIQUE NOT NULL,
stripe_payment_intent VARCHAR(100) UNIQUE NOT NULL,
pickup_qr_token VARCHAR(300),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES users(id_user) ON DELETE CASCADE) ENGINE=InnoDB;*/

/*CREATE TABLE order_items (
id_items INT AUTO_INCREMENT PRIMARY KEY,
order_id INT NOT NULL,
product_id INT NOT NULL,
qty_items INT NOT NULL CHECK (qty_items > 0),
unit_amount INT NOT NULL,            
subtotal_amount INT NOT NULL,    
FOREIGN KEY (order_id) REFERENCES orders(id_order) ON DELETE CASCADE,
FOREIGN KEY (product_id) REFERENCES products(id_product)) ENGINE=InnoDB;*/

/*CREATE TABLE audit_logs (
id_logs INT AUTO_INCREMENT PRIMARY KEY,
actor_user_id INT NOT NULL,
action_logs VARCHAR(50) NOT NULL,
entity_logs VARCHAR(50) NOT NULL,
entity_id INT NOT NULL,
payload_json JSON,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (actor_user_id) REFERENCES users(id_user)) ENGINE=InnoDB;*/

CREATE TABLE webhook_events (
id_webhook_events INT AUTO_INCREMENT PRIMARY KEY,
stripe_event_id VARCHAR(50) UNIQUE NOT NULL,
type_webhook VARCHAR(100) NOT NULL,
raw_json JSON NOT NULL,
processed BOOLEAN DEFAULT FALSE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP) ENGINE=InnoDB;
