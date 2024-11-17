<template>
  <div class="container mt-5 text-center">
    <form @submit.prevent="enviarFormulario" class="shadow-lg p-5 rounded bg-light">
      <div class="mb-3">
        <label for="contaOrigem" class="form-label">Conta Origem</label>
        <input
          type="text"
          id="contaOrigem"
          v-model="formData.contaOrigem"
          class="form-control"
          required
          @invalid="editarMensagemCustomizada"
          @input="limparMensagemCustomizada"
          placeholder="Digite o número da conta de origem"
          maxlength="10"
        />
      </div>

      <div class="mb-3">
        <label for="contaDestino" class="form-label">Conta Destino</label>
        <input
          type="text"
          id="contaDestino"
          v-model="formData.contaDestino"
          class="form-control"
          required
          @invalid="editarMensagemCustomizada"
          @input="limparMensagemCustomizada"
          placeholder="Digite o número da conta de destino"
          maxlength="10"
        />
      </div>

      <div class="mb-3">
        <label for="valor" class="form-label">Valor</label>
        <input
          type="number"
          id="valor"
          v-model="formData.valor"
          class="form-control"
          required
          min="0.01"
          step="0.01"
          @invalid="editarMensagemCustomizada"
          @input="limparMensagemCustomizada"
          placeholder="Exemplo: 100.00"
        />
      </div>

      <div class="mb-3">
        <label for="dataTransferencia" class="form-label">Data de Transferência</label>
        <input
          type="date"
          id="dataTransferencia"
          v-model="formData.dataTransferencia"
          class="form-control"
          required
          @invalid="editarMensagemCustomizada"
          @input="limparMensagemCustomizada"
          :min="dataMinima"
          :max="dataMaxima"
        />
      </div>

      <button type="submit" class="btn btn-primary w-100 py-2 mt-3">Agendar</button>
    </form>

    <div v-if="mensagem" :class="['alert', 'mt-3', 'alert-' + tipoAlerta]" role="alert">
      {{ mensagem }}
    </div>
  </div>
</template>
  
<script>
  import axios from "axios";
  
  export default {
    name: "Formulario",
    data() {
      return {
        formData: {
          contaOrigem: "",
          contaDestino: "",
          valor: null,
          dataTransferencia: "",
        },
        mensagem: null,
      };
    },
    computed: {
    dataMinima() {
      const hoje = new Date();
      return hoje.toISOString().split('T')[0];
    },
    dataMaxima() {
      const hoje = new Date();
      hoje.setDate(hoje.getDate() + 50); 
      return hoje.toISOString().split('T')[0];
    },
  },
    methods: {
        editarMensagemCustomizada(event) {
            event.target.setCustomValidity("Campo obrigatório, por favor preencha.");
        },
        limparMensagemCustomizada(event) {
            event.target.setCustomValidity(""); 
        },
        async enviarFormulario() {
            try {
                const response = await axios.post("http://localhost:8080/api/agendamentos/agendar", this.formData);
                this.mensagem = "Agendamento realizado com sucesso!";
                this.tipoAlerta = "success";
                console.log("Resposta da API:", response.data);
        
                this.formData = {
                    contaOrigem: "",
                    contaDestino: "",
                    valor: null,
                    dataTransferencia: "",
                };

            } catch (error) {
                this.tipoAlerta = "danger"; 
                this.mensagem = error.response.data;
            }
        },
    },
  };
</script>
  
<style scoped>
.container {
  max-width: 600px;
}

form {
  background: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h2 {
  font-family: 'Montserrat', sans-serif;
  font-size: 1.5rem;
  color: #4a4a4a;
}

input.form-control {
  border-radius: 8px;
  transition: border 0.3s;
}

input.form-control:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 10px rgba(0, 86, 179, 0.2);
}

button {
  background-color: #0d6efd;
  border-radius: 10px;
  border-color: #f8f9fa;
  font-weight: bold;
  font-size: 1rem;
}

button:hover {
  background-color: #0650be;
  border-color: #f8f9fa;
}

.alert {
  font-size: 1.1rem;
  padding: 10px;
  font-family: 'Montserrat', sans-serif;
  border-radius: 8px;
}
</style>
  