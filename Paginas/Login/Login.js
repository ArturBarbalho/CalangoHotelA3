export default {
  data() {
    return {
      usuario: '',
      senha: ''
    };
  },
  methods: {
    async fazerLogin() {
      try {
        const response = await fetch('http://localhost:8080/api/login', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            usuario: this.usuario,
            senha: this.senha
          })
        });

        if (response.ok) {
          const data = await response.json();
          console.log(data.message);

          // ✅ Salvar estado de login no localStorage
          localStorage.setItem('logado', 'true');

          // ✅ Redirecionar para Home.html
          window.location.href = 'Home.html';
        } else {
          const error = await response.json();
          alert(error.message);
        }
      } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Erro ao conectar com o servidor.');
      }
    }
  }
};
