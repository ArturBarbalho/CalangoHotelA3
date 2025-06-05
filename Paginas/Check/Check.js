const app = new Vue({
        el: '#app',
        data: {
            reservas: []
        },
        methods: {
            carregar() {
                const json = window.javaConnectorCheck.listarReservas();
                this.reservas = JSON.parse(json);
            },
            fazerCheckIn(id) {
                const ok = window.javaConnectorCheck.realizarCheckIn(id);
                if (ok) {
                    alert("✅ Check-in realizado!");
                    this.carregar();
                } else {
                    alert("❌ Falha no Check-in.");
                }
            },
            fazerCheckOut(id) {
                const ok = window.javaConnectorCheck.realizarCheckOut(id);
                if (ok) {
                    alert("✅ Check-out realizado!");
                    this.carregar();
                } else {
                    alert("❌ Falha no Check-out.");
                }
            }
        },
        mounted() {
            this.carregar();
        }
    });