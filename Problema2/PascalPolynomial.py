import time

def generar_coeficientes(n):
    if n < 0:
        raise ValueError("n debe ser no negativo")

    coeficientes = [0] * (n + 1)
    
    if n == 0:
        return [1]

    coeficientes[0] = 1

    for i in range(1, n + 1):
        for j in range(i, 0, -1):
            if j == i:
                coeficientes[j] = 1
            else:
                coeficientes[j] = coeficientes[j] + coeficientes[j-1]
                
    return coeficientes

def mostrar_polinomio(coeficientes):
    n = len(coeficientes) - 1
    if n == 0:
        return "1"

    terminos = []
    for i in range(n, -1, -1):
        c = coeficientes[i]
        if c == 0:
            continue

        signo = " + " if c > 0 and terminos else ""
        abs_c = abs(c)
        
        if i == 0:
            termino = f"{abs_c}"
        elif i == 1:
            termino = f"{'x' if abs_c == 1 else abs_c}x"
        else:
            termino = f"{'x^' + str(i) if abs_c == 1 else str(abs_c) + 'x^' + str(i)}"

        terminos.append(f"{signo}{termino}")
        
    return "".join(terminos).lstrip(' +')

def evaluar_polinomio_pasos(coeficientes, x):
    n = len(coeficientes) - 1
    
    coeficientes_ordenados = coeficientes[::-1] 

    print(f"\n--- Cálculo f({x}) por Regla de Horner (Pasos) ---")
    resultado = 0

    for i, coeficiente in enumerate(coeficientes_ordenados):
        if i == 0:
            resultado = coeficiente
            print(f"Paso 0 (Inicial): {resultado}")
        else:
            valor_previo = resultado
            resultado = resultado * x + coeficiente
            print(f"Paso {i}: ({valor_previo} * {x:.1f}) + {coeficiente} = {resultado}")
            
    return resultado

if __name__ == "__main__":
    n = 100
    x = 2.0

    start_time = time.perf_counter_ns()
    coeficientes = generar_coeficientes(n)
    end_time = time.perf_counter_ns()
    duration = end_time - start_time

    print(f"--- Polinomio (x+1)^{n} ---")
    print(f"Coeficientes (Primeros 10): {coeficientes[:10]}")
    print(f"Coeficientes (Últimos 10): {coeficientes[-10:]}")
    
    n_ejemplo = 4
    x_ejemplo = 2.0
    coeficientes_ejemplo = generar_coeficientes(n_ejemplo)
    print(f"\n--- Ejemplo: n={n_ejemplo} ---")
    print(f"Polinomio: {mostrar_polinomio(coeficientes_ejemplo)}")
    resultado_final = evaluar_polinomio_pasos(coeficientes_ejemplo, x_ejemplo)
    print(f"Resultado Final f({x_ejemplo}) = {resultado_final}")

    with open("tiempos_ejecucion.txt", "a") as f:
        f.write(f"N={n}\n")
        f.write(f"Lenguaje: Python\n")
        f.write(f"Tiempo (ns): {duration}\n")
        f.write(f"Tiempo (ms): {duration / 1_000_000.0}\n")
        f.write("---\n")
    print("\nResultado de Python (n=100) escrito en 'tiempos_ejecucion.txt'.")
