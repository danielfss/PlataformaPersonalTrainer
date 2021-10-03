# PlataformaPersonalTrainer
Projeto Final do meu período de 3 meses no programa de bolsas da Compasso UOL. Esse projeto foi realizado em grupo de 5 pessoas.

Integrantes:
* Alex de Souza Santos;
* Daniel Ferreira Santos Souza;
* João Victor Junqueira;
* Juciélen Souza;
* Rafael Pinho.

## CENÁRIO
Um instrutor/personal trainer solicitou uma plataforma para que ele pudesse enviar treinos (vídeos, arquivos PDF e rotinas em formato de texto)
para os seus alunos de forma personalizada. Além disso, o aluno poderia ter a opção de marcar 1 treino presencial por semana.

### Especificações
1) Cadastro de alunos
  1.1) Nome, Email, Telefone, Senha (conformidade LGPD)
2) Login do alunos e do personal trainer
  2.1) Recuperação de senha
3) Dashboard do aluno
  3.1) Área com vídeos do treino da semana
  3.2) Área de calendário para agendar treino presencial com o Personal Trainer (max. 1 por semana).
  3.3) Área para envio de mensagem para o Personal Trainer
  3.4) Área para receber e baixar material em PDF
  3.5) Área para edição de informações do aluno
  3.6) Área de pagamento da mensalidade (integração com plataforma de pagamento ex.: Pagseguro)
4) Dashboard do personal trainer
  4.1) Listagens dos alunos cadastrados
  4.2) Exibição dos alunos pagantes e devedores
  4.3) Área para subir treino personalizado para cada aluno vídeos e material PDF.
  4.4) Área para leitura de mensagem de cada aluno
  4.5) Área de calendário, para visualizar os alunos agendados
  
  ### Tecnologias utilizadas
  No backend: Java, Spring Boot, Spring MVC + Thymeleaf, Spring Data JPA com Hibernate na implementação e Spring Security;
  No frontend: HTML5, CSS3 e Bootstrap.
