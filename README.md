## Desafio técnico Back-End ilegra

*Análise de dados*

``` 
A aplicação deve ler arquivos .dat, analisar os dados e gerar um relatório.
Existem 3 tipos de dados dentro desses arquivos. Para cada tipo de dado, existe um layout diferente.

Os dados do vendedor têm o formato id 001 e a linha terá o seguinte formato:
• 001çCPFçNomeçSalário

Os dados do cliente têm o formato id 002 e a linha terá o seguinte formato:
• 002çCNPJçNameçBusinessArea

Os dados de vendas têm o formato id 003. 
Dentro da linha de vendas, há a lista de itens, que é envolto por colchetes []. 
• 003çIDdaVendaç[ID do item-Quantidade do item-Preço do item]çNomedoVendedor

A aplicação fica em execução o tempo todo e sempre que arquivos são criados, modificados ou deletados ele é atualizado.
O relatório do arquivo de saída possui os seguintes dados:
• Quantidade de clientes no arquivo de entrada
• Quantidade de vendedor no arquivo de entrada
• ID da venda mais cara
• O pior vendedor de todos os tempos

``` 

*Para rodar a aplicação insira o comando abaixo na pasta do projeto:*
``` 
$ ./app.sh run
```
Pronto, aplicação rodando!

Após rodar o script serão criadas duas pastas e o diretório /data/:
- /data/in

Recebe os arquivos com os dados de entrada, a aplicação lê *apenas arquivos .dat*

É possível ler arquivos com vários dados, exemplo:

arquivo1.dat:
``` 
001ç1234567891234çIzaç50000
001ç1224567891234çMariaç50000
001ç1214567891234çJoaoç50000
```

- /data/out

Após a execução irá gerar o relatório no arquivo de saída denominado *report.done.dat*



*Referências de estudo e desenvolvimento:*
- https://www.caelum.com.br/apostila-java-orientacao-objetos/pacote-java-io
- http://tutorials.jenkov.com/java-io/index.html
- https://stackoverflow.com/questions/12175401/how-to-create-a-dat-file-in-java
- https://www.alura.com.br/artigos/como-separar-palavras-de-string-em-java
- https://www.codeflow.site/pt/article/java-nio2-watchservice
- https://pt.stackoverflow.com/questions/103286
- https://stackoverflow.com/questions/40041109
- https://stackoverflow.com/questions/13924754
- https://stackoverflow.com/questions/30083161/sort-mapstring-long-by-value-reversed
- https://www.oracle.com/br/technical-resources/articles/java-stream-api.html
- https://docs.spring.io/spring-framework/docs/5.2.9.RELEASE/spring-framework-reference/core.html#spring-core




