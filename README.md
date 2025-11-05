# Prueba Diagnóstica 2025-II

**Hecho por:** Miguel Nuñez
**C.I.:** V30932227
**Profesor:** Msc. Félix Márquez
**Período:** 2025-II

---

## Mapa del Repositorio

Estos problemas fueron resueltos utilizando el lenguaje de programacion Java.
Todo esta organizado de manera que haya una carpeta por cada problema:

- **`Problema1/`**: Validando el Ajedrez (FEN).
- **`Problema2/`**: La Magia del Triángulo de Pascal y la Regla de Horner.
- **`Problema3/`**: Detectives de Cadenas (IP, Correo, Científica).
- **`Problema4/`**: Traduciendo el Código C al Castellano.

---

## Problema 1: El Validador FEN

**Propósito** La clase (`ValidadorFEN`) Se encarga de revisar que la posición de un tablero (dada en notación FEN) sea estructuralmente válida.

**Lo más interesante:**

- **Verificación Rígida:** Revisa que haya **exactamente 6 campos** (posición, turno, enroque, al paso, medio-movimiento y movimiento completo).
- **Análisis Fila por Fila:** La parte más compleja es verificar que las 8 filas sumen siempre **8 casillas** entre piezas y espacios vacíos (números 1-8), ¡y que no haya dos números seguidos!

---

## 2. Problema 2: Polinomios, Pascal y Horner (Java & Python)

### a) La Generación de Coeficientes

- **Lógica:** Utilizamos la **Programación Dinámica** _in situ_, replicando la lógica de suma del Triángulo de Pascal

[Image of Pascal's Triangle]
para generar los coeficientes de $(x+1)^n$.

- **El Reto de n=100:** Python es ideal aquí, ya que maneja **enteros grandes** automáticamente, evitando el desbordamiento que podría afectar a otros tipos de datos.

### b) Evaluación Eficiente con Horner

- Para calcular $f(x)=(x+1)^n$, usamos la **Regla de Horner**. Este método es mucho más eficiente que calcular cada término por separado, reduciendo el número de multiplicaciones. La función lo muestra ¡paso a paso!

### Resultado de la Medición (n=100)

Los tiempos de ejecución de la generación de coeficientes para $n=100$ en ambos lenguajes se encuentran en: `tiempos_ejecucion.txt`.

---

## 3. Problema 3: reconocimiento de cadenas, expresiones notación científica, ip y correo electrónico (Java)

**Propósito** La clase `ValidadorDeCadenas` usa las **Expresiones Regulares (Regex)** para validar tres formatos comunes y cruciales en la informática.

| Formato                 | ¿Qué valida?                                                    | La Clave Regex                                                     |
| :---------------------- | :-------------------------------------------------------------- | :----------------------------------------------------------------- |
| **Notación Científica** | Que el número tenga su mantisa, signo y exponente (`1.23e+10`). | Manejar la parte decimal opcional y la parte `e/E`.                |
| **Dirección IPv4**      | Que cada octeto esté en el rango **0-255** (¡el desafío!).      | La expresión regular es súper precisa para el rango `0-255`.       |
| **Correo Electrónico**  | Estructura básica `local@dominio.tld`.                          | Un simple y efectivo patrón para la estructura mínima de un email. |

---

## 4. Problema 4: Traducción de Código C (Java)

**Propósito** Este es un simulador de **Análisis Léxico**. El código identifica las palabras reservadas de C y las sustituye por sus equivalentes en español.

- Usamos un `HashMap` con 32 palabras reservadas y sus traducciones.
- Al reemplazar, usamos el ancla `\b` (**límite de palabra**). Esto garantiza que `int` se reemplace por `entero`, pero **no** afecte a la palabra `printf` o `pintar`. Solo se traducen las palabras completas

---

## Entrega y Defensa

**Link de YouTube (Explicación y Demostración):**
[(https://youtu.be/BZIaAcsK2uk)]
