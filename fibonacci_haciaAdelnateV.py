# Código 1: Fibonacci hacia adelante (sin generador)
def fibAdel(n: int) -> int:
    """Devuelve el n-ésimo número de Fibonacci calculado iterativamente."""
    if n < 0:
        raise ValueError("El índice n debe ser un número entero no negativo.")
    if n in (0, 1):
        return n
    penultimo, ultimo = 0, 1
    for _ in range(2, n + 1):
        penultimo, ultimo = ultimo, penultimo + ultimo
    return ultimo

if __name__ == "__main__":
    try:
        n = int(input("¿De qué posición quieres el valor de Fibonacci?: "))
        print(f"El {n}-ésimo valor de Fibonacci es: {fibAdel(n)}")
    except ValueError as e:
        print(f"Error: {e}")
