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


#### **Docker**

##### Instalações

###### WSL

Caso seu sistema operacional (SO) seja Windows, será necessário instalar o WSL 2.

Para instalar o WSL, basta seguir o tutorial abaixo:

https://docs.microsoft.com/pt-br/windows/wsl/setup/environment

Outras informações:

https://docs.microsoft.com/pt-br/windows/wsl/install

ps: caso esteja demorando mais que o normal, clique no terminal e aperte enter para ele continuar instalando normalmente, feito isso, basta aguardar.

Por padrão o windows instala automaticamente o WSL 2, mas se, você estiver executando um build mais antigo do Windows, talvez não tenha suporte para o comando, do tutorial acima, então basta seguir o tutorial abaixo:

https://docs.microsoft.com/pt-br/windows/wsl/install-manual#step-4---download-the-linux-kernel-update-package

###### Docker

Enfim, para instalar o docker, basta seguir o tutorial disponivel no link abaixo:

https://docs.docker.com/desktop/install/windows-install/

Outras informações (em PT BR):
https://docs.microsoft.com/pt-br/windows/wsl/tutorials/wsl-containers


##### Imagem dos projetos

ps: Usuários de Linux não precisam modificar nada, podem seguir para o próximo passo.

Para gerar a imagem do projeto, é necessário ir ao arquivo Dockerfile e comentar a linha que tem escrito:

```
Run bash mvnw clean package -Dmaven.test.skip
```

Ir ao application.properties ou application.yml e alterar o profile de prod para devcloud ou trocar o host da conexão com os bancos de dados:

De:		`protocolo://localhost:porta`

Para:	`protocolo://host.docker.internal:porta`

E em seguida rodar os comandos "`maven clean`" e "`maven package`" no terminal

Em seus terminais e naveguem até um serviço desejado
Em seguida, digitem o seguinte comando:

```
docker build . --tag user/nomeprojeto
```

Esse comando é responsável por buildar o projeto, da mesma maneira de um CI/CD de projetos.

**OPCIONAL:**  Subir projeto no Docker Hub

[Criar uma conta no Docker Hub](https://hub.docker.com/)

**IMPORTANTE**: O nome do usuário no passo anterior deve ser o mesmo que seu usuário no Docker Hub.

Digitem: `dockerlogin`

Por fim, digitem: `docker push nomeusuario/nomeprojeto`

Para mais informações:
https://youtu.be/iqqDU2crIEQ?t=1000

##### **Rodando Container**

###### Linux

Basta abrir o terminal, navegar até o serviço desejado e rodar o seguinte comando:

```
docker run --network="host"-d nomeusuario/nomeprojeto
```

###### **Windows**

Utilizar o DockerDesktop (ou dockerhub) ou abrir o terminal desejado, navegar até o serviço desejado e rodar o seguinte comando:

```
docker run -d -p 8080:8080 --name nomedocontainer nomeusuario/nomeprojeto
```

ps1: Dentro do serviço, caso ele precise se conectar com algo do host do seu S.O, usar `host.docker.internal` ao invés de
`localhost` na conexão do serviço

ps2: onde tem: " -p xxxx:yyyy "

xxxx - Porta que será conetada no host do seu S.O

yyyy - Porta que o seu serviço está rodando no container

Para mais informações: "[Explore networking features | Docker Documentation](https://docs.docker.com/desktop/networking/)"

Obs: Para recuperar uma imagem do repositório remoto basta digitar no terminal `docker pull nomeusuario/nomeprojeto`

##### Comandos úteis (CLI)

Listar contâineres: `docker container ls`

Iniciar um contâiner já existente: `docker start <id_container>`

Parar um contâiner: `docker stop <id_container>`

Parar todos os contâineres: `docker stop $(docker ps -a -q)`

Remover um contâiner: `docker rm <id_container>`

Remover uma imagem: `docker rmi <id_imagem>`


#### Bibliotecas e Tecnologias

Com o progredir do desenvolvimento do projeto, novas bilbiotecas e tecnologias começaram a ser utilizadas para ajudar, facilitar e complementar os serviços. Com isso, o conhecimento e dominio delas acabaram tornando-se indispensáveis, assim, aqui encontram-se referências para a documentação das mesmas.


