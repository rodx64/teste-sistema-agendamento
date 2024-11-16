# Decisões de Código

## Backend

### [BigDecimal](https://docs.oracle.com/javase/8/docs/api/?java/math/BigDecimal.html)
Escolhido porque oferece precisão e controle sobre cálculos numéricos que outras representações, como float ou double, não conseguem fornecer.

### [RoundingMode.HALF_EVEN](https://docs.oracle.com/javase/10/docs/api/java/math/RoundingMode.html)
Escolhido por ser o tipo que busca o valor mais próximo, ou em caso de valores distantes o próximo par. O que acontece normalmente em desvios contábeis. 

### [LocalDate - dataTransferencia](/backend-sat/src/main/java/com/backend_sat/domain/model/Agendamento.java)
Data de transferência foi utilizado LocalDate, por entender não ser necessário saber o horário em que isso será processado.

### [LocalDateTime - dataAgendamento](/backend/src/main/java/com/backend_sat/domain/model/Agendamento.java)
Data de agendamento foi utilizado LocalDateTime com @CreationTimestamp para registrar o timestamp do pedido de agendamento no momento de solicitação (caso seja necessário validar log posteriormente).