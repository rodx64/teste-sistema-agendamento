<template>
  <div class="container mt-4">
    <h2 class="text-center">Agendar Transferência Bancária</h2>
    <Formulario/>
  </div>
</template>

<script>
import Formulario from '@/components/Formulario.vue';

export default {
  name: 'HomeView',
  components: {
    Formulario
  }
}
</script>