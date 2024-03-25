# DataJpaTest 

Ele utiliza o h2 para permitir a efetuação de testes. Por padrão ele foca em @Entity e @Repository. Por padrão os testes são transacionais e revertidos ao final de cada teste.


# Mockito Anotações

## @InjectMocks

Quando queremos injetar um objeto mcokado em outro objeto mockado, podemos usar essa anotação. Ele cria um mock da classe e injeta os mocks que já estão marcados com @Mock.

## @Mock

É uma alternativa a função **mock**.