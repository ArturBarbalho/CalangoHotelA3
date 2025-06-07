import React, { useState } from 'react';
import './Login.css';

const Login = () => {
  const [usuario, setUsuario] = useState('');
  const [senha, setSenha] = useState('');

  const test = () => {
    localStorage.setItem('logado', 'true') ;
    window.location.reload();
  }

  const fazerLogin = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          usuario,
          senha
        })
      });

      if (response.ok) {
        const data = await response.json();
        console.log(data.message);
        localStorage.setItem('logado', 'true');
        window.location.reload(); 
      } else {
        const error = await response.json();
        alert(error.message);
      }
    } catch (error) {
      console.error('Erro na requisição:', error);
      alert('Erro ao conectar com o servidor.');
    }
  };

  return (
    
    <div id="body-login">
      <h1 id='title-login'>LOGIN</h1>
      <div id="container-login">
        <img
          id="logo-login"
          src="logoLogin.png"
          alt=""
        />
        <p id='p-login'>Usuário</p>
        <input id='input-login'
          type="text"
          value={usuario}
          onChange={(e) => setUsuario(e.target.value)}
        />
        <p id='p-login'>Senha</p>
        <input id='input-login'
          type="password"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
        />
        <br />
        <button id='button-login' onClick={fazerLogin}>Login</button>
        <button onClick={test}>go</button>
      </div>
    </div>
   
  );
};

export default Login;