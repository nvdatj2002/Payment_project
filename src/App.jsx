import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter as Router, Route, Routes } from "react-router-dom"
import Cart from './components/cart'
import Payment from './components/payment'
import Login from './components/login'
import Order from './components/order'


function App() {
 

  return (
    <>
      <Router>
        <Routes>
          <Route path={'/Cart'} element={<Cart/>} />
          <Route path={'/order'} element={<Order/>} />
          <Route path={'/login'} element={<Login/>} />
          <Route path={'/payment'} element={<Payment/>} />
        </Routes>
      </Router>
    </>
  )
}

export default App
