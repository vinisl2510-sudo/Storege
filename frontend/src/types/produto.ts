// Igual ao ProdutoRequest.java: o que o front ENVIA
export interface ProdutoRequest {
  nome: string
  descricao: string
  preco: number
  categoria: string
  estoque: number
}

// Igual ao ProdutoResponse.java: o que o back DEVOLVE
export interface Produto extends ProdutoRequest {
  id: number
  imagemUrl: string | null
}