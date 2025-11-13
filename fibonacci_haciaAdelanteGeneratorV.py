# Código 2: Fibonacci con función Generator (yield)
from typing import Generator

def fibGen(n: int) -> Generator[int, None, None]:
    """Genera los valores de la secuencia de Fibonacci hasta la posición n (inclusive)."""
    if n < 0:
        raise ValueError("El índice n debe ser un número entero no negativo.")
    a, b = 0, 1
    for i in range(n + 1):
        yield a
        a, b = b, a + b

if __name__ == "__main__":
    try:
        n = int(input("¿Fibonacci hasta la posición?: "))
        for valor in fibGen(n):
            print(valor)
    except ValueError as e:
        print(f"Error: {e}")
