![alt text](https://github.com/matheusbrc/p2/blob/master/public/images/logo.png "Logo Bibliotec")

Inicialmente nosso projeto foi pensado sendo uma API REST implementada com a linguagem Scala juntamente com a framework Lagom, que consistia em um serviço para a gestão de uma biblioteca, podendo ser usada tanto numa aplicação para bibliotecas físicas quanto digitais, posibilitando o cadastro de livros até o controle dos empréstimos.

Devido a impecílios durante a tentativa inicial de implementação utilizando a framework Lagom a implementação foi alterada. Os impecílios consistiram em problemas com oas dependências e como o banco seria gerado automaticamente pela aplicação, pois a framework foi criada inicialmente para ser usada com o banco de dados Cassandra, um banco noSQL orientado a grafos.

Após ser elaborada uma solução para os impecílios a implementação final consistiu em uma aplicação web no modelo cliente servidor utilizando a framework Play!, que devido ao curto espaço de tempo para implementação teve suas funcionalidades parcialmente elaborada e um frontend minimalista.

Rotas:

>Rota: /clientes Método: GET
>  --> Esta rota exibe o conteúdo da tabela Cliente

>Rota: /autor Método: GET
> --> Esta rota exibe o conteúdo da tabela Autor.

>Rota: /livros Método: GET
> --> Esta rota exibe o conteúdo da tabela Livro.

>Rota: /movimento Método: GET
> --> Esta rota exibe o conteúdo da tabela Movimentação

>Rota: /emprestimo/:livroId/:clienteId Método: GET
> --> Esta rota, através do código do cliente e código do livro, o empréstimo do livro é feito ao cliente, depois que a ação é concluída, há o redirecionamento para a rota /movimentos.

>Rota: /devolucao/:livroId/:clienteId Método: GET
> --> Esta rota, através do código do cliente e código do livro, a devolução do livro é feito ao cliente, depois que a ação é concluída, há o redirecionamento para a rota /movimentos.

>Rota: /buscar/livros/autor/:autorId Método: GET
> --> Esta rota recebe o código de identificação do autor e procura o(s) livro(s) escrito pelo o autor.

Esquema do banco de dados:

![alt text](https://github.com/matheusbrc/p2/blob/master/public/images/banco.jpg "Esquema Banco")
