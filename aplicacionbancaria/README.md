### Los endpoint para probar son los siguientes:

### Para consultar el saldo de una cuenta:
localhost:8080/cuenta/saldo

{
"cuenta": "12"
}

### Para consultar el historial de transacciones de una cuenta:
localhost:8080/cuenta/historial

{
"cuenta": "12"
}

### Para realizar un deposito desde una sucursal:
localhost:8080/transaccion/deposito/sucursal

{
"cuenta": "12",
"tipoTransaccion": "Deposito desde sucursal",
"monto": 100
}

### Para realizar un deposito desde un cajero:
localhost:8080/transaccion/deposito/cajero

{
"cuenta": "12",
"tipoTransaccion": "Deposito desde cajero",
"monto": 100
}

### Para realizar un deposito desde otra cuenta:
localhost:8080/transaccion/deposito/otracuenta

{
"cuenta": "12",
"tipoTransaccion": "Deposito desde otra cuenta",
"monto": 100
}

### Para realizar una compra en un establecimiento fisico:
localhost:8080/transaccion/compra/fisico

{
"cuenta": "12",
"tipoTransaccion": "Compra establecimiento fisico",
"monto": 100
}

### Para realizar una compra en una pagina web:
localhost:8080/transaccion/compra/web

{
"cuenta": "12",
"tipoTransaccion": "Compra en pagina web",
"monto": 100
}

### Para realizar un retiro en un cajero:
localhost:8080/transaccion/retiro/cajero

{
"cuenta": "12",
"tipoTransaccion": "Retiro desde cajero",
"monto": 100
}

### Script de las tablas de la DB

CREATE TABLE cuenta (
id SERIAL PRIMARY KEY,       -- El campo id será la clave primaria y autoincremental.
tipo_cuenta VARCHAR(50) NOT NULL,  -- El campo tipo_cuenta es de tipo VARCHAR.
saldo NUMERIC(19, 2) NOT NULL,  -- BigDecimal en Java se mapea a NUMERIC en PostgreSQL.
titular VARCHAR(255) NOT NULL   -- El campo titular es de tipo VARCHAR.
);

ALTER TABLE IF EXISTS public.cuenta
ADD COLUMN numero_cuenta bigint;


CREATE TABLE IF NOT EXISTS transacciones (
id SERIAL PRIMARY KEY,                          -- ID autoincremental para la tabla transacciones.
tipo_transaccion VARCHAR(50) NOT NULL,          -- Tipo de transacción (e.g., 'Depósito', 'Retiro').
monto NUMERIC(19, 2) NOT NULL,                  -- Monto de la transacción.
fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,      -- Fecha de la transacción (automática con NOW()).
cuenta_id INT NOT NULL,                         -- Clave foránea que hace referencia a la tabla cuenta.
CONSTRAINT fk_cuenta
FOREIGN KEY (cuenta_id)
REFERENCES cuenta (id)                      -- Relación con la tabla cuenta.
);







    