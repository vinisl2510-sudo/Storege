// Erro que carrega o status HTTP e a mensagem que veio do backend
export class ApiError extends Error {
  status: number

  constructor(status: number, mensagem: string) {
    super(mensagem)
    this.status = status
  }
}

// Função única que fala com o backend. Todas as chamadas passam por aqui.
export async function api<T>(caminho: string, opcoes: RequestInit = {}): Promise<T> {
  const resposta = await fetch(`/api${caminho}`, {
    ...opcoes,
    headers: { 'Content-Type': 'application/json', ...opcoes.headers },
  })

  if (!resposta.ok) {
    // O GlobalExceptionHandler devolve {"mensagem": "..."}
    const corpo = await resposta.json().catch(() => null)
    throw new ApiError(resposta.status, corpo?.mensagem ?? `Erro ${resposta.status}`)
  }

  // 204 No Content (DELETE) não tem corpo
  if (resposta.status === 204) return undefined as T

  return resposta.json() as Promise<T>
}