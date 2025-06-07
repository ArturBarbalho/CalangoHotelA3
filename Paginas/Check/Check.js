const app = new Vue({
  el: '#app',
  data: {
    reservas: []
  },
  methods: {
    async carregar() {
      try {
        const response = await fetch("/api/check/reservas");
        const data = await response.json();
        // Supondo que a API retorne um JSON com { message: '[{"id":1,"nome":"João"}]' }
        this.reservas = JSON.parse(data.message);
      } catch (err) {
        alert("❌ Erro ao carregar reservas: " + err.message);
      }
    },
    async fazerCheckIn(id) {
      try {
        const response = await fetch("/api/check/checkin", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ id: id })
        });
        const data = await response.json();
        if (response.ok) {
          alert("✅ " + data.message);
          this.carregar();
        } else {
          alert("❌ " + data.message);
        }
      } catch (err) {
        alert("❌ Erro no Check-in: " + err.message);
      }
    },
    async fazerCheckOut(id) {
      try {
        const response = await fetch("/api/check/checkout", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ id: id })
        });
        const data = await response.json();
        if (response.ok) {
          alert("✅ " + data.message);
          this.carregar();
        } else {
          alert("❌ " + data.message);
        }
      } catch (err) {
        alert("❌ Erro no Check-out: " + err.message);
      }
    }
  },
  mounted() {
    this.carregar();
  }
});