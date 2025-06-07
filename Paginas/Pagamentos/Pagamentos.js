const app = Vue.createApp({
    data(){
        return {
            pagamento : ''
        }
    },
    methods: {
        async ListarPagamento() {
            try {
                const response = await fetch("/api/pagamentos")
                if (!response.ok) {
                    throw new Error('Erro na requisição')
                }
                return await response.text()
            } catch (err) {
                return err.message
            }
        }
    },
    async mounted() {
        this.pagamento = await this.ListarPagamento()
    }
})
app.mount('#app')
