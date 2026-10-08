# ☕ MYTOS — Café, sabor e lendas

O **MYTOS** é um aplicativo de café inspirado em diferentes mitologias do mundo, desenvolvido como projeto acadêmico utilizando **Kotlin e Jetpack Compose**.

A proposta é unir café, mitologia e tecnologia em uma experiência digital simples, interativa e acessível para toda a família.

---

## 📱 Sobre o projeto

O MYTOS começou como um projeto voltado para uma experiência de café com uma identidade visual inspirada em mitologias.

Ao longo do desenvolvimento, o projeto evoluiu para uma proposta mais amigável, colorida e acessível, com personagens próprios e uma experiência de navegação mais completa.

O aplicativo permite que o usuário:

- realizar cadastro e login;
- acessar a tela de acessibilidade;
- visualizar o cardápio;
- adicionar produtos;
- editar produtos;
- excluir produtos;
- visualizar detalhes dos produtos;
- adicionar produtos à sacola;
- alterar quantidades;
- visualizar o valor total da compra;
- finalizar pedidos;
- consultar pedidos realizados;
- visualizar os detalhes de cada pedido;
- acessar e editar informações do perfil.

---

## 🎨 Identidade do MYTOS

A identidade visual do aplicativo utiliza principalmente tons de:

- 💜 Roxo
- 🤍 Branco
- 💛 Amarelo claro
- 💚 Verde como cor de apoio

O projeto também possui três personagens que representam diferentes culturas e mitologias:

- 🐺 **Iago** — lobo-guará brasileiro
- 🐈 **Bast** — gata inspirada na cultura egípcia
- 🦊 **Mika** — kitsune japonesa

Os personagens fazem parte da identidade do MYTOS e ajudam a tornar a experiência mais amigável e reconhecível.

---

## 📲 Telas do aplicativo

O aplicativo possui diferentes telas conectadas por navegação:

1. Splash
2. Login
3. Cadastro
4. Acessibilidade
5. Home
6. Cardápio
7. Detalhes do Produto
8. Sacola
9. Perfil
10. Meus Pedidos
11. Detalhes do Pedido

A navegação principal é realizada por uma **Bottom Navigation**, contendo:

- 🏠 Home
- ☕ Cardápio
- 🛍 Sacola
- 👤 Perfil

---

## 🛒 Funcionamento do aplicativo

### Cardápio

O usuário pode visualizar os produtos disponíveis e filtrar os itens por categoria.

As categorias utilizadas são:

- Bebidas
- Doces
- Salgados

Também é possível adicionar novos produtos.

Cada produto pode ser:

- adicionado;
- editado;
- excluído;
- aberto para visualização de detalhes.

---

### 🛍 Sacola

Os produtos selecionados são armazenados na sacola.

O usuário pode:

- aumentar a quantidade;
- diminuir a quantidade;
- remover produtos;
- visualizar o subtotal;
- visualizar o valor total;
- finalizar o pedido.

---

### 📦 Pedidos

Após finalizar uma compra, um novo pedido é criado e armazenado na lista de pedidos.

O usuário pode acessar **Meus Pedidos** e visualizar:

- número do pedido;
- data e horário;
- quantidade de itens;
- valor total.

Ao selecionar um pedido, é aberta uma tela de detalhes contendo os produtos daquele pedido, suas quantidades, preços unitários, subtotais e o valor total.

---

## 🧩 Estrutura do projeto

O código foi organizado em diferentes pacotes para facilitar a manutenção e separar as responsabilidades.

```text
com.example.mytos
│
├── data
│   ├── MythosData.kt
│   └── SacolaData.kt
│
├── model
│   └── Produto.kt
│
├── navigation
│   └── Rotas.kt
│
├── screens
│   ├── splash
│   ├── login
│   ├── cadastro
│   ├── acessibilidade
│   ├── home
│   ├── cardapio
│   ├── produto
│   ├── sacola
│   ├── perfil
│   └── pedidos
│
└── ui
    ├── components
    └── theme