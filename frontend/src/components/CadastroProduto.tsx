import { useState, type FormEvent } from 'react'
import { produtosApi } from '../api/produtos'
import type { Produto, ProdutoRequest } from '../types/produto'

const formVazio: ProdutoRequest = { nome: '', descricao: '', preco: 0, categoria: '', estoque: 0 }

interface Props {
  aoCadastrar: (produto: Produto) => void
}

export function CadastroProduto({ aoCadastrar }: Props) {
  const [form, setForm] = useState<ProdutoRequest>(formVazio)
  const [erro, setErro] = useState<string | null>(null)

  async function enviar(e: FormEvent) {
    e.preventDefault() // impede o navegador de recarregar a página
    setErro(null)
    try {
      const criado = await produtosApi.criar(form)
      aoCadastrar(criado)
      setForm(formVazio)
    } catch (err) {
      setErro(err instanceof Error ? err.message : 'Erro inesperado')
    }
  }

  return (
    <form onSubmit={enviar}>
      <label>
        Nome
        <input value={form.nome}
          onChange={e => setForm({ ...form, nome: e.target.value })} />
      </label>
      <label>
        Descrição
        <input value={form.descricao}
          onChange={e => setForm({ ...form, descricao: e.target.value })} />
      </label>
      <label>
        Categoria
        <input value={form.categoria}
          onChange={e => setForm({ ...form, categoria: e.target.value })} />
      </label>
      <label>
        Preço
        <input type="number" step="0.01" value={form.preco}
          onChange={e => setForm({ ...form, preco: Number(e.target.value) })} />
      </label>
      <label>
        Estoque
        <input type="number" value={form.estoque}
          onChange={e => setForm({ ...form, estoque: Number(e.target.value) })} />
      </label>

      <button type="submit">Cadastrar</button>
      {erro && <p className="erro">{erro}</p>}
    </form>
  )
}