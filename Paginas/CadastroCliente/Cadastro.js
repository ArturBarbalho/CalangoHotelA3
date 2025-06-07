new Vue({
  el: '#app',
  data: {
    nome: '',
    cpf: '',
    telefone: '',
    email: '',
    quarto: '',
    dataEntrada: '',
    dataSaida: '',
    valor: '',
    formaPagamento: ''
  },
  methods: {
    async cadastrar() {
      const payload = {
        nome: this.nome,
        cpf: this.cpf,
        telefone: this.telefone,
        email: this.email,
        quarto: this.quarto,
        dataEntrada: this.dataEntrada,
        dataSaida: this.dataSaida,
        valor: this.valor,
        formaPagamento: this.formaPagamento
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
    }
  }
});