<template>
  <nav>
    <ul class="pagination justify-content-end">
      <li 
        class="page-item"
        :class="{ disabled: paginaAtual === 1 }"
        @click="trocarPagina(paginaAtual - 1)"
      >
        <a class="page-link" href="#">Anterior</a>
      </li>

      <li 
        v-for="pagina in totalDePaginas"
        :key="pagina"
        class="page-item"
        :class="{ active: pagina === paginaAtual }"
        @click="trocarPagina(pagina)"
      >
        <a class="page-link" href="#">{{ pagina }}</a>
      </li>

      <li 
        class="page-item"
        :class="{ disabled: paginaAtual === totalDePaginas }"
        @click="trocarPagina(paginaAtual + 1)"
      >
        <a class="page-link" href="#">Próxima</a>
      </li>
    </ul>
  </nav>
</template>
  
<script>
export default {
  props: {
    totalDePaginas: {
      type: Number,
      required: true,
    },
    paginaAtual: {
      type: Number,
      required: true,
    },
  },
  emits: ["trocar-pagina"],
  methods: {
    trocarPagina(pagina) {
      if (pagina >= 1 && pagina <= this.totalDePaginas) {
        this.$emit("trocar-pagina", pagina); 
      }
    },
  },
};
</script>