import React, { useState, useEffect } from 'react';
import './Pagamentos.css';

const ListaPagamentos = () => {
  const [pagamentos, setPagamentos] = useState([]);

  useEffect(() => {
    const fetchPagamentos = async () => {
      try {
        const response = await fetch("http://localhost:8081/api/pagamentos");  
        const data = await response.json();
        console.log(data.message);

        setPagamentos(JSON.parse(data.message));

      } catch (err) {
        console.error(err.message);
      }
    };
    fetchPagamentos();
  }, []); 

  return (
    <div className="container">
      <h1>Lista de Pagamentos</h1>
      <table>
        <thead className="tabela">
          <tr>
            <th>ID pagamento</th>
            <th>Valor</th>
            <th>Data Pagamento</th>
            <th>Método</th>
          </tr>
        </thead>
        <tbody>
          {pagamentos.map(pagamento => (
            <tr key={pagamento.id}>
              <td>{pagamento.id}</td>
              <td>R$ {pagamento.valor.toFixed(2)}</td>
              <td>{pagamento.data}</td>
              <td>{pagamento.metodo}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <br />
    </div>
  );
};

export default ListaPagamentos;