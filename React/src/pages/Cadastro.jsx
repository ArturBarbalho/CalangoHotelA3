import React, { useState } from 'react';
import './Cadastro.css'; // Assumindo que o CSS está disponível

const CadastroCliente = () => {
  const [nome, setNome] = useState('');
  const [cpf, setCpf] = useState('');
  const [telefone, setTelefone] = useState('');
  const [email, setEmail] = useState('');
  const [quarto, setQuarto] = useState('');
  const [dataEntrada, setDataEntrada] = useState('');
  const [dataSaida, setDataSaida] = useState('');
  const [valorReserva, setValorReserva] = useState('');
  const [metodoPagamento, setMetodoPagamento] = useState('');

  const cadastrar = async () => {
    const payload = {
      nome,
      cpf,
      telefone,
      email,
      quarto,
      dataEntrada,
      dataSaida,
      valor: valorReserva,
      formaPagamento: metodoPagamento
    };

    try {
      const response = await fetch("/api/cadastro", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      const data = await response.json();

      if (response.ok) {
        alert(data.message || '✅ Cadastro realizado com sucesso!');
      } else {
        alert(data.message || '❌ Erro ao cadastrar.');
      }
    } catch (err) {
      alert("❌ Erro na comunicação com o servidor: " + err.message);
    }
  };

  return (
    <div id="container">
      <h1>CADASTRAR CLIENTE</h1>
      <div id="linha">
        <div id="formulario">
          <p>Nome completo</p>
          <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} />

          <p>CPF</p>
          <input type="text" value={cpf} onChange={(e) => setCpf(e.target.value)} />

          <p>Número de telefone</p>
          <input type="text" value={telefone} onChange={(e) => setTelefone(e.target.value)} />

          <p>Email</p>
          <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>

        <div id="formulario">
          <p>Quarto</p>
          <select id="select" value={quarto} onChange={(e) => setQuarto(e.target.value)}>
            <option value="">Selecione um quarto</option>
            <option value="1">Quarto 1</option>
            <option value="2">Quarto 2</option>
            <option value="3">Quarto 3</option>
            <option value="4">Quarto 4</option>
          </select>

          <p>Data de entrada</p>
          <input type="date" value={dataEntrada} onChange={(e) => setDataEntrada(e.target.value)} />

          <p>Data de saída</p>
          <input type="date" value={dataSaida} onChange={(e) => setDataSaida(e.target.value)} />

          <p>Valor da reserva</p>
          <input type="number" value={valorReserva} onChange={(e) => setValorReserva(e.target.value)} />

          <p>Método de pagamento</p>
          <select id="select" value={metodoPagamento} onChange={(e) => setMetodoPagamento(e.target.value)}>
            <option value="">Selecione um método</option>
            <option value="cartao">Cartão de Crédito</option>
            <option value="pix">Pix</option>
            <option value="boleto">Boleto</option>
          </select>
        </div>
      </div>
      <button onClick={cadastrar}>Cadastrar</button>
    </div>
  );
};

export default CadastroCliente;