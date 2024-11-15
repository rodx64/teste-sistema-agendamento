# Decisões DB

### [Inicialização via Hibernate](https://docs.spring.io/spring-boot/docs/1.1.0.M1/reference/html/howto-database-initialization.html#howto-initialize-a-database-using-hibernate)
Por se tratar de um projeto de teste com banco em memória, foi utilizada a estratégia default `create-drop` do Hibernate, garantindo um banco efêmero para cada alteração e teste.

obs. Em um projeto real, existem diversas outras opções a considerar antes de tomar qualquer ação, como a validação de dados, schema, versionamento, etc.

### [Cache em memória](https://docs.spring.io/spring-boot/reference/io/caching.html)
Com a necessidade de acesso aos dados de regra das taxas constante, será utilizado uma estratégia de Cache tipo Key-Value, que é gerenciada pelo próprio framework. 
Não é uma estratégia escalável, por se tratar de dados em memória, entretanto cabe a este projeto de testes. 
Para outras situações, deve ser analisada necessidade de uso do cache e poderiam ser utilizadas outras tecnologias e estratégias de Cache, como `Cache Aside`, `Write Through`, `CDN`, entre outras. 
