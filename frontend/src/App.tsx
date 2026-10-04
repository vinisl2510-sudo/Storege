import { useEffect, useState } from 'react'
import { produtosApi } from './api/produtos'
import { CadastroProduto } from './components/CadastroProduto'
import type { Produto } from './types/produto'
import './App.css'

function App() {
  const [produtos, setProdutos] = useState<Produto[]>([])
  const [erro, setErro] = useState<string | null>(null)

  // Roda uma vez quando a tela abre: busca a lista no backend
  useEffect(() => {
    produtosApi.listar()
      .then(setProdutos)
      .catch(err => setErro(err.message))
  }, [])

  return (
    <main>
      <h1>Storege</h1>

      <CadastroProduto aoCadastrar={p => setProdutos(atual => [...atual, p])} />

      {erro && <p className="erro">{erro}</p>}

      <ul>
        {produtos.map(p => (
          <li key={p.id}>
            <strong>{p.nome}</strong> ({p.categoria}): R$ {p.preco.toFixed(2)}, {p.estoque} em estoque
          </li>
        ))}
      </ul>
    </main>
  )
}

export default App