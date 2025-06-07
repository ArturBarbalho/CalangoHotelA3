import React, { useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route, Navigate, NavLink } from 'react-router-dom';
import Check from './pages/Check';
import Cadastro from './pages/Cadastro';
import Pagamentos from './pages/Pagamentos';
import Login from './pages/Login';
import './App.css';
const App = () => {

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const storage = localStorage.getItem('logado');
    if (storage === 'true') {
      setIsLoggedIn(true);
    }
    else {
      setIsLoggedIn(false);
    }
  }, []);  
   
  
  return (
    
    <BrowserRouter>
    {isLoggedIn ? (
      
    
      <div className="App">
        <header>
          <div>
            <img id="home" src="Logo.png" alt="Logo do Hotel Calango" />
          </div>
          <nav>
            <NavLink to="check" >
              <img id="icons" src="reservas.png" alt="Reservas" />
              <span>Reservas</span>
            </NavLink>
            <NavLink to="cadastro" >
              <img id="icons" src="cadastrar.png" alt="Cadastrar" />
              <span>Cadastrar</span>
            </NavLink>
            <NavLink to="pagamentos">
              <img id="icons" src="pagamentos.png" alt="Pagamentos" />
              <span>Pagamentos</span>
            </NavLink>
          </nav>
        </header>
        <main>
          <Routes>
            <Route index element={<Navigate to="check" replace />} />
            <Route path="check" element={<Check />} />
            <Route path="cadastro" element={<Cadastro />} />
            <Route path="pagamentos" element={<Pagamentos />} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </Routes>
        </main>
      </div>
      ) : (
      <Login />
    )}
    </BrowserRouter>



  );
};

export default App;