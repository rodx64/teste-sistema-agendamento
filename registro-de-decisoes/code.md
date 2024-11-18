# Decisões de Código

## Backend

### [BigDecimal](https://docs.oracle.com/javase/8/docs/api/?java/math/BigDecimal.html)
Escolhido porque oferece precisão e controle sobre cálculos numéricos que outras representações, como float ou double, não conseguem fornecer.

### [RoundingMode.HALF_EVEN](https://docs.oracle.com/javase/10/docs/api/java/math/RoundingMode.html)
Escolhido por ser o tipo que busca o valor mais próximo, ou em caso de valores distantes o próximo par. O que acontece normalmente em desvios contábeis. 

### [LocalDate - dataTransferencia](/backend-sat/src/main/java/com/backend_sat/domain/model/Agendamento.java)
Data de transferência foi utilizado `LocalDate`, por entender não ser necessário saber o horário em que isso será processado.

### [LocalDateTime - dataAgendamento](/backend/src/main/java/com/backend_sat/domain/model/Agendamento.java)
Data de agendamento foi utilizado `LocalDateTime` com `@CreationTimestamp` para registrar o timestamp do pedido de agendamento no momento de solicitação (caso seja necessário validar log posteriormente).

### [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)
[Cors config](/backend-sat/src/main/java/com/backend_sat/config/CorsConfig.java) foi utilizada para liberar o `cross-origin` localmente no lado do servidor. Pelo modelo do projeto entendi não ser um problema, mas em um projeto real deveriam ser consideradas outras questões como: segurança, `profile` da aplicação, etc.

## Frontend

### [VueJS](https://vuejs.org/) + [Bootstrap](https://getbootstrap.com/docs/5.0/)
Apesar de ter mais experiência em Angular  com Material Design, preferi utilizar o VueJS com Bootstrap como um desafio pessoal.

### [Diretiva personalizada - formatDate](https://pt.vuejs.org/guide/reusability/custom-directives)
Por questões de reuso, foi utilizada [diretiva personalizada](/frontend-sat/src/directives/formatDate.js) para formatar data.

### [Lock date component](/frontend-sat/src/components/Formulario.vue#60)
Apesar da [Api](/backend-sat/src/main/java/com/backend_sat/api/AgendamentoController.java#77) ter tratamento de exceção não haja taxa aplicável, o componente de seleção de dada no Vue foi limitado para evitar erros do usuário  