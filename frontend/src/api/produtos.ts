import { api } from './client'
import type { Produto, ProdutoRequest } from '../types/produto'

export const produtosApi = {
  listar: () => api<Produto[]>('/produtos'),

  buscar: (id: number) => api<Produto>(`/produtos/${id}`),

  criar: (dados: ProdutoRequest) =>
    api<Produto>('/produtos', { method: 'POST', body: JSON.stringify(dados) }),

  atualizar: (id: number, dados: ProdutoRequest) =>
    api<Produto>(`/produtos/${id}`, { method: 'PUT', body: JSON.stringify(dados) }),

  deletar: (id: number) => api<void>(`/produtos/${id}`, { method: 'DELETE' }),
}