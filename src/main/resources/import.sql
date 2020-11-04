/* Populate tables */
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(1,'Mariana','Diaz','mary@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(2,'Matias','medinaz','maty@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(3,'Malisa','basurto','mely@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(4,'Mirella','quintero','mary@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(5,'lucila','laureano','luci@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(6,'Hugo','boss','hugo@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(7,'roberto','carlos','rober@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(8,'kiara','quesadilla','dilla@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(9,'Cenon','tostadas','toty@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(10,'neftali','ramos','mary@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(11,'Mariana','Diaz','mary@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(12,'flavio','rodriguez','flavelo@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(13,'Karla','reynosa','cary@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(15,'Mariana','Diaz','mary@gmail.com','2020-10-13','');
INSERT INTO CLIENTES (id, nombre, apellido, email, create_at,foto) values(19,'Miguel Angel','Lopez Ramos','endejelin19@gmail.com','2017-08-04','');

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 700, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 379, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 6000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 800, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Celular', 6000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mochila', 800, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('reloj digital', 2000, NOW());

/* Populate tabla productos con campo cantidad */
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Panasonic Pantalla LCD', 700,50, NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Sony Camara digital DSC-W320B', 490,50 NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Apple iPod shuffle', 990,50, NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Sony Notebook Z110', 379,50, NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Hewlett Packard Multifuncional F2280', 6000,50, NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Bianchi Bicicleta Aro 26', 800,50 NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Mica Comoda 5 Cajones', 299990,50 NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Celular', 6000,50 NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('Mochila', 800,50 NOW());
-- INSERT INTO productos (nombre, precio,cantidad_articulos, create_at) VALUES('reloj digital', 2000,50 NOW());



/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

/*insertar datos a cliente endejelin*/
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Compras Tiwui', 'Alguna nota importante!', 19, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 3, 8);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 3, 9);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 3, 10);




