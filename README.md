# 🔢 Serie de 3 en 3 – Java (IntelliJ) + Oracle 19c:.

<p align="center">
  <img width="1536" height="1024" alt="Logo Serie de 3 en 3"
       src="https://github.com/user-attachments/assets/7a991eda-337f-4563-92b2-263285c8d5cd" />
</p>

---

A continuación se presenta un **diseño académico y profesional**, con pasos **exactos y ordenados**, seguido del **programa completo en Java**, integrado con **Oracle 19c**, para **sumar una serie de 3 en 3** y **registrar el resultado en la base de datos**.  

---

## 📐 Pasos exactos y ordenados:  
### Suma de una serie de 3 en 3 (Java + Oracle 19c)

---

### 🔹 Paso 1. Definir el problema

Se desea:

- Solicitar un **límite entero** al usuario  
- Generar la serie:  


3 + 6 + 9 + … + n


- Calcular la **suma total**
- Guardar el **resultado en Oracle 19c**

---

### 🔹 Paso 2. Diseñar la base de datos

La base de datos debe almacenar:

- Límite ingresado  
- Resultado de la suma  
- Fecha de ejecución  

---

### 🔹 Paso 3. Crear la tabla en Oracle 19c

```sql
CREATE TABLE SERIE_TRES (
  ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  LIMITE NUMBER NOT NULL,
  RESULTADO NUMBER NOT NULL,
  FECHA DATE DEFAULT SYSDATE
);

🔹 Paso 4. Crear un Stored Procedure

El procedimiento almacenará la información calculada desde Java.

CREATE OR REPLACE PROCEDURE SP_GUARDAR_SERIE_TRES (
    P_LIMITE IN NUMBER,
    P_RESULTADO IN NUMBER
) AS
BEGIN
    INSERT INTO SERIE_TRES (LIMITE, RESULTADO)
    VALUES (P_LIMITE, P_RESULTADO);

    COMMIT;
END;
/

🔹 Paso 5. Configurar el proyecto en IntelliJ

Crear proyecto Java

Agregar el driver:

ojdbc8.jar o ojdbc11.jar

Usar la siguiente estructura:

src/
 ├── db/
 │   └── ConexionOracle.java
 ├── dao/
 │   └── SerieTresDAO.java
 ├── service/
 │   └── SerieTresService.java
 └── ui/
     └── SerieTresApp.java

🧱 Implementación en Java
🔌 Conexión a Oracle
package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {

    private static final String URL =
        "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USER = "TU_USUARIO";
    private static final String PASSWORD = "TU_PASSWORD";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

📦 DAO – Llamada al Stored Procedure
package dao;

import db.ConexionOracle;
import java.sql.CallableStatement;
import java.sql.Connection;

public class SerieTresDAO {

    public void guardarResultado(int limite, int resultado) throws Exception {
        String sql = "{CALL SP_GUARDAR_SERIE_TRES(?, ?)}";

        try (Connection con = ConexionOracle.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, limite);
            cs.setInt(2, resultado);
            cs.execute();
        }
    }
}

⚙️ Servicio – Lógica de negocio
package service;

import dao.SerieTresDAO;

public class SerieTresService {

    private final SerieTresDAO dao = new SerieTresDAO();

    public int calcularSuma(int limite) {
        int suma = 0;
        for (int i = 3; i <= limite; i += 3) {
            suma += i;
        }
        return suma;
    }

    public void procesarSerie(int limite) throws Exception {
        int resultado = calcularSuma(limite);
        dao.guardarResultado(limite, resultado);
    }
}

🖥️ UI – Programa principal (Consola)
package ui;

import service.SerieTresService;
import java.util.Scanner;

public class SerieTresApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SerieTresService service = new SerieTresService();

        System.out.print("Ingrese el número límite: ");
        int limite = scanner.nextInt();

        try {
            int suma = service.calcularSuma(limite);
            service.procesarSerie(limite);

            System.out.println("Serie generada de 3 en 3 hasta " + limite);
            System.out.println("Resultado de la suma: " + suma);
            System.out.println("Resultado guardado en Oracle 19c");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

✅ Ejemplo de ejecución
Ingrese el número límite: 15
Serie generada de 3 en 3 hasta 15
Resultado de la suma: 45
Resultado guardado en Oracle 19c .

📊 Consulta de resultados en la base de datos
SELECT * FROM SERIE_TRES;

🎓 Valor académico
✔ Algoritmo matemático claro
✔ Uso correcto de bucle incremental
✔ Arquitectura en capas
✔ Persistencia real en Oracle
✔ Uso de Stored Procedure

## 📐 Diagrama UML – Serie de 3 en 3 (Java + Oracle 19c)
```
<p align="center">
  <img
    src="https://github.com/user-attachments/assets/590ed67b-0abd-49fe-821c-5956a0319771"
    alt="Diagrama UML de la Serie de 3 en 3"
    width="893"
    height="635"
  />
</p>

```sql
**Figura:** Diagrama UML de clases que representa la arquitectura en capas  
(UI → Service → DAO → Base de Datos) del sistema *Serie de 3 en 3*.

🧱 Clases del sistema
1️⃣ ConexionOracle
Responsabilidad: Gestión de conexión a Oracle 19c
Métodos:
getConnection() : Connection

2️⃣ SerieTresDAO
Responsabilidad: Persistencia mediante Stored Procedure
Métodos:
guardarResultado(int limite, int resultado)

3️⃣ SerieTresService
Responsabilidad: Lógica de negocio
Métodos:
calcularSuma(int limite)
procesarSerie(int limite)

4️⃣ SerieTresApp
Responsabilidad: Interfaz de usuario
Métodos:
main(String[] args)

🔗 Relaciones UML
Relación	Tipo
SerieTresApp → SerieTresService	Dependencia
SerieTresService → SerieTresDAO	Asociación
SerieTresDAO → ConexionOracle	Dependencia
Arquitectura	En capas .

🧪 Código PlantUML (Recomendado)
@startuml
skinparam classAttributeIconSize 0

class SerieTresApp {
    + main(args : String[]) : void
}

class SerieTresService {
    - dao : SerieTresDAO
    + calcularSuma(limite : int) : int
    + procesarSerie(limite : int) : void
}

class SerieTresDAO {
    + guardarResultado(limite : int, resultado : int) : void
}

class ConexionOracle {
    + getConnection() : Connection
}

SerieTresApp --> SerieTresService : usa
SerieTresService --> SerieTresDAO : delega
SerieTresDAO --> ConexionOracle : obtiene conexión
@enduml

🏛️ Justificacion académica
✔ Separación clara de responsabilidades
✔ Cumple principios SOLID (SRP)
✔ Compatible con arquitectura empresarial Java
✔ Escalable a Swing / JavaFX / Web
✔ Ideal para exposición universitaria / .
