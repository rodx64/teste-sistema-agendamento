<template>
   <div class="container mt-4">
    <h2 class="text-center mb-4">Extrato de agendamentos</h2>
    
    <div class="row">
      <div class="col-3 mb-3">
        <div class="border p-3">
          <h5 class="mb-3">Filtrar por</h5>
          <div class="row">
            <div class="col-md-6">
              <label for="filtroContaOrigem" class="form-label">Conta Origem</label>
              <input v-model="filtroContaOrigem" id="filtroContaOrigem" class="form-control" @keydown.enter="buscarAgendamentos(1)" />
            </div>
            <div class="col-md-6">
              <label for="filtroContaDestino" class="form-label">Conta Destino</label>
              <input v-model="filtroContaDestino" id="filtroContaDestino" class="form-control" @keydown.enter="buscarAgendamentos(1)" />
            </div>
          </div>
        </div>
      </div>

      <div class="col-3">
      <div class="border p-3">
          <h5 class="mb-3">Buscar por</h5>
          <div class="row">
            <div class="col-md-12">
              <label for="itens-requisitados" class="form-label">Quantidade de itens</label>
              <select v-model="paginacao.quantidade" id="itens-requisitados" class="form-select" @change="buscarAgendamentos(1)">
                <option value="0">todos</option>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
              </select>
            </div>
            
          </div>
        </div>
      </div>

      <div class="col-6">
        <div class="border p-3">
          <h5 class="mb-3">Organizar por</h5>
          <div class="row">
            <div class="col-md-4">
              <label for="itens-apresentados" class="form-label">Itens na tela</label>
              <select v-model="itensApresentados" id="itens-apresentados" class="form-select" @change="atualizarPaginacao">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
              </select>
            </div>
            <div class="col-md-4">
              <label for="ordenarPor" class="form-label">Data</label>
              <select v-model="paginacao.ordenarPor" id="ordenarPor" class="form-select" @change="buscarAgendamentos(1)">
                <option value="dataTransferencia">Transferência</option>
                <option value="dataAgendamento">Agendamento</option>
              </select>
            </div>
            <div class="col-md-4">
              <label for="direcao" class="form-label">Direção</label>
              <select v-model="paginacao.direcao" id="direcao" class="form-select" @change="buscarAgendamentos(1)">
                <option value="ASC">Ascendente</option>
                <option value="DESC">Descendente</option>
              </select>
            </div>
          </div>
        </div>
      </div>
    </div>

    <Paginacao class="mt-4"
      :totalDePaginas="paginasCalculadas"
      :paginaAtual="paginaAtual"
      @trocar-pagina="proximaPagina"
    />

    <div class="mt-4">
      <div class="row row-cols-1 row-cols-md-2 row-cols-lg-5 g-3">
        <div class="col" v-for="agendamento in agendamentosFiltrados" :key="agendamento.id">
          <div class="card h-100 shadow-sm">
            <div class="card-body">
              <h4 class="card-title">Agendamento</h4>
              <div class="mt-4">
                <p class="card-text">
                  <strong>Conta Destino</strong> <br /> {{ agendamento.contaDestino }} <br />
                  <strong>Valor</strong> <br /> R$ {{ agendamento.valor.toFixed(2) }} <br />
                  <strong>Valor das Taxas</strong> <br /> R$ {{ agendamento.valorTaxa.toFixed(2) }} <br />
                  <strong>Data Agendamento</strong> <br /> 
                  <span v-format-date="agendamento.dataAgendamento"></span> <br />
                  <strong>Data Transferência</strong> <br /> 
                  <span v-format-date="agendamento.dataTransferencia"></span>
                </p>
              </div>
            </div>
            <div class="card-footer text-muted">
              <strong>Conta Origem</strong> <br /> {{ agendamento.contaOrigem }} <br />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Paginacao from "@/components/Paginacao.vue"; 
import formatDate from '@/directives/formatDate'
import axios from "axios";

export default {
  name: 'ExtratoView',
  components: {
    Paginacao
  },
  data() {
    return {
      agendamentos: [],
      paginacao: {
        totalDePaginas: 0,
        numero: 0,
        quantidade: 0,
        ordenarPor: "dataTransferencia",
        direcao: "ASC",
      },
      filtroContaOrigem: "",
      filtroContaDestino: "",
      itensApresentados: 5, 
      paginaAtual: 1,
    };
  },
  computed: {
    paginasCalculadas() {
      return Math.ceil(this.agendamentos.length / this.itensApresentados);
    },
    agendamentosFiltrados() {
      const inicio = (this.paginaAtual - 1) * this.itensApresentados;
      const fim = Number(inicio) + Number(this.itensApresentados);
      return this.agendamentos.slice(inicio, fim);
    },
  },
  methods: {
    async buscarAgendamentos(page) {
      try {
        this.paginacao.numero = page - 1; 

        const response = await axios.get("http://localhost:8080/api/agendamentos/gerar-extrato", {
          params: {
            numero: this.paginacao.numero,
            quantidade: this.paginacao.quantidade,
            ordenarPor: this.paginacao.ordenarPor,
            direcao: this.paginacao.direcao,
            contaOrigem: this.filtroContaOrigem,
            contaDestino: this.filtroContaDestino
          },
        });

        const data = response.data;

        this.agendamentos = data.content || [];
        this.paginacao.totalDePaginas = Math.ceil(data.totalElements / this.paginacao.quantidade);
        this.paginaAtual = 1;
      } catch (error) {
        console.error("Erro ao buscar dados:", error);
      }
    },
    proximaPagina(novaPagina) {
      this.paginaAtual = novaPagina;
    },
    atualizarPaginacao() {
      this.paginaAtual = 1;
    },
  },
  mounted() {
    this.buscarAgendamentos(1); 
  },
  directives: {
    formatDate,
  },
}
</script>