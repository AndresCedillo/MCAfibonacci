def fibAdel(n: int) -> int:
    if n == 0: return n
    penultimo: int = 0
    ultimo:    int = 1
    for _ in range(1, n):
        penultimo, ultimo = ultimo, penultimo + ultimo
    return ultimo

if __name__ == "__main__":
    n = int(input("¿De qué posición quieres el valor de Fibonacci?: "))
    print(f"El  {n}-ésimo valor de Fibonacci es: {fibAdel(n)}") 
