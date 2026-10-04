import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5180,        // porta fixa do Storege
    strictPort: true,  // se a 5180 estiver ocupada, dá erro em vez de pular pra outra
    proxy: {
      // Tudo que começar com /api é repassado pro Spring Boot
      '/api': 'http://localhost:8080',
    },
  },
})