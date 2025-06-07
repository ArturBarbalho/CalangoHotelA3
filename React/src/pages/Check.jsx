import React, { useState, useEffect } from 'react';
import './Check.css';

const CheckComponent = () => {
  const [reservas, setReservas] = useState([]);

  const carregar = async () => {
    try {
      const response = await fetch("/api/check/reservas");
      const data = await response.json();
      setReservas(JSON.parse(data.message));
      alert("✅ Reservas carregadas com sucesso!");
    } catch (err) {
      alert("❌ Erro ao carregar reservas: " + err.message);
    }
  };

  const fazerCheckIn = async (id) => {
    try {
      const response = await fetch("/api/check/checkin", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id })
      });
      const data = await response.json();
      if (response.ok) {
        alert("✅ " + data.message);
        carregar();
      } else {
        alert("❌ " + data.message);
      }
    } catch (err) {
      alert("❌ Erro no Check-in: " + err.message);
    }
  };

  const fazerCheckOut = async (id) => {
    try {
      const response = await fetch("/api/check/checkout", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id })
      });
      const data = await response.json();
      if (response.ok) {
        alert("✅ " + data.message);
        carregar();
      } else {
        alert("❌ " + data.message);
      }
    } catch (err) {
      alert("❌ Erro no Check-out: " + err.message);
    }
  };

  useEffect(() => {
    carregar();
  }, []);

  return (
    <div className="container">
      <h1>Gestão de Reservas</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Quarto</th>
            <th>Status</th>
            <th>Ação</th>
          </tr>
        </thead>
        <tbody>
          {reservas.map(reserva => (
            <tr key={reserva.id}>
              <td>{reserva.id}</td>
              <td>{reserva.cliente}</td>
              <td>{reserva.quarto}</td>
              <td>{reserva.status}</td>
              <td>
                {reserva.status === 'Aguardando Check-in' && (
                  <button onClick={() => fazerCheckIn(reserva.id)}>Check-in</button>
                )}
                {reserva.status === 'Ocupado' && (
                  <button onClick={() => fazerCheckOut(reserva.id)}>Check-out</button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <br />
      <button onClick={carregar}>Atualizar Lista</button>
    </div>
  );
};

export default CheckComponent;