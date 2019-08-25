# Agenda Eletrônica
<h2>Imagens do projeto</h2>

<h3>Menu de opções do programa</h3>
<p>Quando iniciar o projeto, o usuário terá que selecionar a opção da funcionalidade que deseja, digitando
números  entre [1, 2, 3, 4, 5 ou 9].</p>
![MenuOpções](imagens/menuOpcoes.png)

<h3>Adicionar nova pessoa</h3>
<p>Para adicionar uma nova pessoa, basta inserir o nome e depois o telefone. Caso queira adicionar mais 
telefones, digite "s" quando aparecer na tela "Outro telefone? (s/n): ", caso contrário, digite "n".</p>
![Inserir Nova Pessoa](imagens/cadastroPessoa.png)

<h3>Listar Agenda</h3>
![Listar Agenda](imagens/listarAgenda.png)

<h3>Buscar Pessoas</h3>
<p>O usuário não vai precisar digitar o nome da pessoa por inteiro, basta  digitar uma única letra do nome, que o programa vai retornar todas as pessoas que tenham essa letra em seu nome.</p>
![Buscar Pessoa](imagens/buscarPessoas.png)

<h3>Editar Pessoa</h3>
<p>Para editar, o usuário tem que digitar o nome da pessoa. Não precisa obrigatoriamente digitar o nome desta pessoa por inteiro, basta informa uma única letra que o programa vai fazer a busca por essa pessoa. 
Após isso, o usuário deverá digitar o índice da linha que a pessoa está na agenda para o programa selecionar
esta pessoa.</p>
![Selecionar Pessoa](imagens/editarPessoa1.png)

<p>Depois que o usuário informou a linha, será exibido um menu. O usuário terá 3 opções: Atualizar nome, 
Atualizar telefone ou Voltar (que seria para cancelar a edição desta pessoa). Então, basta o usuário digitar
número entre [1, 2, 3].</p>
![Selecionar Pessoa](imagens/editarPessoa2.png)

<h3>Deletar Pessoas</h3>
<p>O usuário deve informar o nome da pessoa. Não precisa obrigatoriamente informar o nome inteiro, basta
uma letra. O programa retornará os nomes das pessoas, dependendo da entrada do usuário. Caso não encontre
nenhum nome, o programa retornará uma mensagem dizendo que não foi possível encontrar a pessoa com
essas letras.</p>
![Buscar Pessoa Para Excluir](imagens/excluir1.png)

<p>Depois, caso o programa retornar os nomes das pessoas, o usuário deverá digitar o índice da linha da pessoa que queira excluir.
Caso quiser excluir todos as pessoas que foram retornadas, basta digitar a opção "00". Se o usuário escolheu em excluir uma pessoa
específica, o programa pedirá a entrada do usuário novamente, para confirmar a exclusão da pessoa. A entrada do usuário vai ser "s"
que significa que pode confirmar a exclusão ou "n" que significa que vai cancelar a exclusão. Se o usuário escolheu para excluir todas
as pessoas retornadas, o programa também vai pedir a entrada do usuário, para confirmar se realmente quer excluir todos os nomes da
agenda.</p>
![Selecionar Pessoa Para Excluir](imagens/excluir2.png)

<h2>Diagrama de Caso de Uso</h2>
![Diagrama de Caso de Uso](diagramas/casoDeUso.png)

<h2>Diagrama de Sequência</h2>
![Diagrama de Sequência](diagramas/sequência.png)

<h2>Diagrama de Classe</h2>
![Diagrama de Classe](diagramas/classe.png)



