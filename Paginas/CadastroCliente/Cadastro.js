new Vue({
      el: '#app',
      data: {
        nome: '',
        cpf: '',
        telefone: '',
        email: ''
      },
      methods: {
        cadastrar() {
          // chama o método Java (exposto via WebView)
          if (typeof JavaConnectorCadastro !== 'undefined') {
            const resultado = JavaConnectorCadastro.cadastrarHospede(this.nome, this.email, this.telefone);
            alert(resultado ? 'Cadastro realizado com sucesso!' : 'Erro ao cadastrar!');
          } else {
            alert('javaConnector não está disponível.');
          }
        }
      }
    });