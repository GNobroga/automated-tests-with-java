
## Tecnologias

- Mockito 

- JUnit

- Hamcrest - Desenvolvida para facilitar a escrita de testes unitários mais legíveis e expressivos

- Mockito inline - Permite mockar métodos estáticos etc


# DataJpaTest 

Ele utiliza o h2 para permitir a efetuação de testes. Por padrão ele foca em @Entity e @Repository. Por padrão os testes são transacionais e revertidos ao final de cada teste.


# Mockito Anotações

## @InjectMocks

Quando queremos injetar um objeto mcokado em outro objeto mockado, podemos usar essa anotação. Ele cria um mock da classe e injeta os mocks que já estão marcados com @Mock.

## @Mock

É uma alternativa a função **mock**.



## JsonPath Library

É uma DSL Java para leitura de documentos JSON

- JsonPath - Referencem a estrutura JSON

- XPath - Referencem a XML

## @WebMvcTest (Teste Unidade) (Utiliza mocks)

@WebMvcTest é uma anotação do Spring Boot para testes de integração que se concentram apenas nos controladores da camada de apresentação da sua aplicação web. Esses testes carregam apenas o contexto necessário para testar os controladores e fornecem um ambiente simulado para fazer solicitações HTTP e verificar o comportamento do controlador. Isso ajuda a manter os testes mais rápidos e focados, excluindo as outras camadas da aplicação que não são relevantes para os testes de controladores.


Essa anotação sobe os beans apenas relacionados aos controladores.

## SpringBootTest (Teste Integração) (Não utiliza mocks)

Ele é focado em testes de integração e sobe o application context completo (como se estivesse subindo a aplicação normalmente), ele sobe toda a aplicação como se fosse o fluxo normal.

Essa anotação sobe todos os beans, além disso não há uso de mocks.

**Por padrão, @SpringBootTest não inicia um servidor. Precisamos adicionar o atributo WebEnvironment para refinar ainda mais com nossos testes serão executados**

- Mock (Padrão) - Carrega um WebServerApplicationContext e fornece um web environment mockado.

- RANDOM_PORT - Carrega um WebServerApplicationContext e fornece u mweb environment real. O servidor embarcado é iniciado e exposto em uma porta aleatórioa. Essa opção deve ser usada para testes de integração.

- DEFINED_PORT - Carrega um WebServerApplicationContext e fornce um web environment real;

- NONE - Carrega um ApplicationContext usando SpringApplication, mas não fornece nenhum web environment.