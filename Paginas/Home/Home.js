const app = Vue.createApp({
            data() {
                return {
                    currentPage: '../Check/Check',
                    content: ''
                }
            },
            methods: {
                loadPage(page) {
                    this.currentPage = page;
                    fetch(`${page}.html`)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Página não encontrada');
                            }
                            return response.text();
                        })
                        .then(html => {
                            this.content = html;
                        })
                        .catch(err => {
                            this.content = `<h1>Erro 404</h1><p>${err.message}</p>`;
                        });
                }
            },
            mounted() {
                this.loadPage(this.currentPage);
            }
        });

        app.mount('#app');