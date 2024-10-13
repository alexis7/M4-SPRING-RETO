### Los endpoint para probar son los siguientes:

### Para consultar el saldo de una cuenta:
localhost:8080/cuenta/saldo

{
"cuenta": "1"
}

### Para realizar un retiro:
localhost:8080/cuenta/retiro

{
"cuenta": "1",
"tipoTransaccion": "Retiro",
"monto": 100
}

### Para realizar un deposito:
localhost:8080/cuenta/deposito


{
"cuenta": "1",
"tipoTransaccion": "Deposito",
"monto": 100
}

### Script de las tablas de la DB

CREATE TABLE cuenta (
id SERIAL PRIMARY KEY,       -- El campo id será la clave primaria y autoincremental.
saldo NUMERIC(19, 2) NOT NULL,  -- BigDecimal en Java se mapea a NUMERIC en PostgreSQL.
titular VARCHAR(255) NOT NULL   -- El campo titular es de tipo VARCHAR.
);


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







    