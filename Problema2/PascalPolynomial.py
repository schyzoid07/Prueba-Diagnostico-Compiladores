import time

# a) Generar Coeficientes
def generar_coeficientes(n):
    if n < 0:
        raise ValueError("n debe ser no negativo")

    # Memoria Dinámica: Lista (array dinámico) de Python
    coeficientes = [0] * (n + 1)
    
    # n=0: (x+1)^0 = 1
    if n == 0:
        return [1]

    coeficientes[0] = 1 # Coeficiente de x^0 (que es 1)

    # Aplicación de la Programación Dinámica (Triángulo de Pascal)
    for i in range(1, n + 1):
        # Recorrer de derecha a izquierda para usar los valores de la fila anterior
        # antes de modificarlos (optimización de espacio)
        for j in range(i, 0, -1):
            if j == i:
                coeficientes[j] = 1
            else:
                # C(i, j) = C(i-1, j-1) + C(i-1, j)
                # Python maneja automáticamente enteros grandes, ideal para n=100
                coeficientes[j] = coeficientes[j] + coeficientes[j-1]
                
    return coeficientes

# Mostrar el Polinomio (x+1)^n
def mostrar_polinomio(coeficientes):
    n = len(coeficientes) - 1
    if n == 0:
        return "1"

    terminos = []
    # Los coeficientes están en orden a_0, a_1, ..., a_n. Iteramos de a_n a a_0.
    for i in range(n, -1, -1):
        c = coeficientes[i]
        if c == 0:
            continue

        signo = " + " if c > 0 and terminos else ""
        abs_c = abs(c)
        
        if i == 0: # Término constante
            termino = f"{abs_c}"
        elif i == 1: # Término lineal
            termino = f"{'x' if abs_c == 1 else abs_c}x"
        else: # Término de grado > 1
            termino = f"{'x^' + str(i) if abs_c == 1 else str(abs_c) + 'x^' + str(i)}"

        terminos.append(f"{signo}{termino}")
        
    return "".join(terminos).lstrip(' +')

# b) Evaluar el Polinomio por Pasos (Regla de Horner)
def evaluar_polinomio_pasos(coeficientes, x):
    n = len(coeficientes) - 1
    
    # Invertir los coeficientes para empezar con el de mayor grado a_n
    # Coeficientes: [a_n, a_{n-1}, ..., a_0]
    coeficientes_ordenados = coeficientes[::-1] 

    print(f"\n--- Cálculo f({x}) por Regla de Horner (Pasos) ---")
    resultado = 0

    for i, coeficiente in enumerate(coeficientes_ordenados):
        if i == 0:
            resultado = coeficiente  # Inicio con a_n
            print(f"Paso 0 (Inicial): {resultado}")
        else:
            valor_previo = resultado
            resultado = resultado * x + coeficiente # Nuevo resultado = (Resultado anterior * x) + a_k
            print(f"Paso {i}: ({valor_previo} * {x:.1f}) + {coeficiente} = {resultado}")
            
    return resultado

# --- Programa Principal ---
if __name__ == "__main__":
    n = 100 # Valor de n para la medición de tiempo
    x = 2.0 # Valor dado para x

    # --- Medición de Tiempo y Generación de Coeficientes (n=100) ---
    start_time = time.perf_counter_ns()
    coeficientes = generar_coeficientes(n)
    end_time = time.perf_counter_ns()
    duration = end_time - start_time # en nanosegundos

    # a) Mostrar el resultado del polinomio
    print(f"--- Polinomio (x+1)^{n} ---")
    print(f"Coeficientes (Primeros 10): {coeficientes[:10]}")
    print(f"Coeficientes (Últimos 10): {coeficientes[-10:]}")
    
    # b) Mostrar el cálculo paso a paso (usando un n pequeño para visualización)
    n_ejemplo = 4
    x_ejemplo = 2.0
    coeficientes_ejemplo = generar_coeficientes(n_ejemplo)
    print(f"\n--- Ejemplo: n={n_ejemplo} ---")
    print(f"Polinomio: {mostrar_polinomio(coeficientes_ejemplo)}")
    resultado_final = evaluar_polinomio_pasos(coeficientes_ejemplo, x_ejemplo)
    print(f"Resultado Final f({x_ejemplo}) = {resultado_final}")

    # --- Escribir el resultado de la medición en archivo TXT ---
    with open("tiempos_ejecucion.txt", "a") as f:
        f.write(f"N={n}\n")
        f.write(f"Lenguaje: Python\n")
        f.write(f"Tiempo (ns): {duration}\n")
        f.write(f"Tiempo (ms): {duration / 1_000_000.0}\n")
        f.write("---\n")
    print("\nResultado de Python (n=100) escrito en 'tiempos_ejecucion.txt'.")