import { Component, StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import './components/cart.jsx'




createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App></App>
  </StrictMode>,
)
