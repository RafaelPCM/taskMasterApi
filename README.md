# PROJETO TASKMASTERAPI
Repositório para teste do IMPA


No projeto tem um arquivo api.http com todas as requisições e jsons necessários para testar a aplicação. Você pode usar esse arquivo em um editor de texto que suporte requisições HTTP, como o Visual Studio Code com a extensão REST Client ou o IntelliJ IDEA.


#### TECNOLOGIAS UTILIZADAS

Java JDK 17

Spring Boot 3.1.5

Restful

PostgreSQL

#### INSTALAÇÕES

##### Instalar o Java JDK 17 (Recomendo o do RedHat x64 MSI)

https://developers.redhat.com/products/openjdk/download

Atentem para ativar TUDO na instalação, o PATH e etc.

##### Instalar o MAVEN

Escolher a versão Binary zip archive

https://maven.apache.org/download.cgi

Tutorial de como instalar o Maven e colocar as variáveis de ambiente

https://dicasdejava.com.br/como-instalar-o-maven-no-windows/

##### PostgreSQL e/ou DBeaver

https://www.postgresql.org/download/

https://dbeaver.io/download/

O DBeaver é um programa multiplataforma, que tem por objetivo conectar e manipular vários tipos de banco de dados. 


##### Postman ou Imnsonia

ps: O Postman facilita na hora de colocar autenticação.

https://www.postman.com/downloads/

https://insomnia.rest/download

##### IDE

Recomendo o VSCode

https://code.visualstudio.com/download

Recomendo instalar algumas extensões que irão ajudar:

Lombok Annotations, Git History e TODO Tree.

#### CONFIGURAÇÕES


##### PostgreSQL // DBeaver

Para adicionar novas conexões, clica em Database "New Database Connection", apertar em "PostgreSQL", Next, ir em "PostgreSQL" marcar a caixa "Show all databases", volta para "Main" e preenche os dados.
Em "Host" preencher o IP desejado (Local ou Remoto).

Segue os dados para cada conexão:

###### Conexão Local

`Host: localhost:5432`

###### Conexão Remota (Produção)

`Host: xx.y.zzz.kkk:5432`

`Database: postgres`

`Username: postgres`

`Password: @password`

###### Exportar e Importar dados do banco de dados

Export:

Conecta ao banco remoto `( Host: xx.y.zzz.kkk:5432 )` , escolhe a database que vai querer exportar e em seguida clica com o botão direito -> Tools -> Backup.
Ao abrir a tela de "Backup", em "Objects", seleciona a "public" e aperta em Next
Na tela de "Backup Settings", em "Format" selecionar "Tar", em "Enconding" selecionar "UTF-8", marcar a box "Add create database statement" e verificar em "Local Client" a versão do PostgreSQL requerida, ao final apertar em "Start", verifica se deu tudo certo e pode fechar a janela de Backup

Import:

Adiciona uma nova conexão, clica em Database "New Database Connection", apertar em "PostgreSQL", Next, ir em "PostgreSQL" marcar a caixa "Show all databases", volta para "Main" e preenche os dados (caso queira mudar algo). Em "Host" preencher o IP desejado, no caso, Local e em "Password" coloca a senha padrão (geralmente é 123).
Verificar em "Local Client" a versão do PostgreSQL requerida.

Em seguida, clica com o botão direito em "Databases", dentro da conexão localhost e cria uma nova database ("Create New Database"), coloca o nome e verifica se o "Encoding" tá em "UTF-8"

Em seguida, clica com o botão direito na nova database criada e em seguida clica com o botão direito -> Tools -> Restore.
Na janela "Restore Settings, verifica o "Format" se tá em "Tar" e marca a box "Create database" e escolhe o arquivo gerado pelo backup.
Verificar em "Local Client" a versão do PostgreSQL requerida.

#### PARA RODAR A APLICAÇÃO

Clonar o projeto escolhido, provavelmente será solicitado para que faça o login com a conta do GitLab, ao logar, escolher a branch (git checkout {nome da branch}), por exemplo, a develop, mudar o profile no arquivo application.properties (para dev ou dev-infra), rodar aplicação via VSCode clicando em "Run" e "Start Debugging" ou apertando F5.

##### PARA ACESSAR A DOCUMENTAÇÃO DA API (SWAGGER)

Swagger:

http://localhost:8080/swagger-ui/index.html#/

##### Para adicionar uma Task via POSTMAN

Criar uma pasta nova em uma Collection, adicionar um novo request POST e por o endereço:

###### Para criar uma Task no Projeto:

###### Local

POST http://localhost:8080/tasks

###### Remoto

POST http://xx.z.yy.kkk/tasks

No Body, selecionar opção "raw" e "JSON". Enviar o seguinte JSON (exemplo):

```JSON
{
    "title": "teste",
    "description": "teste",
    "statusEnum": "TODO"
}
```

#### CONTRIBUIÇÕES

Seguimos o padrão GitFlow, mas procure seu respectivo líder para saber mais detalhes de como as tarefas e contribuições são gerenciadas no Gitlab.

##### GitFlow

https://medium.com/trainingcenter/utilizando-o-fluxo-git-flow-e63d5e0d5e04

https://www.atlassian.com/br/git/tutorials/comparing-workflows/gitflow-workflow


#### Bibliotecas e Tecnologias

Com o progredir do desenvolvimento do projeto, novas bilbiotecas e tecnologias começaram a ser utilizadas para ajudar, facilitar e complementar os serviços. Com isso, o conhecimento e dominio delas acabaram tornando-se indispensáveis, assim, aqui encontram-se referências para a documentação das mesmas.


