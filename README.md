# ☕ MYTOS — Café, sabor e lendas

O **MYTOS** é um aplicativo de café inspirado em mitologias de diferentes culturas, desenvolvido em **Kotlin** com **Jetpack Compose**.

O projeto foi desenvolvido como parte de um trabalho acadêmico, com foco em navegação, interação com o usuário, organização de dados e acessibilidade.

---

## 🌟 Sobre o projeto

O MYTOS busca transformar a experiência de um café tradicional em uma experiência temática, utilizando produtos inspirados em personagens e elementos de diferentes mitologias.

A identidade visual do aplicativo utiliza principalmente tons de **roxo, branco e amarelo claro**, combinando uma proposta moderna, acolhedora e divertida.

---

## 🐺 Mascotes

O aplicativo possui três mascotes que representam diferentes culturas:

- **Iago** — lobo-guará brasileiro 🇧🇷
- **Bast** — gata egípcia 🇪🇬
- **Mika** — kitsune japonesa 🇯🇵

Os mascotes fazem parte da identidade visual do MYTOS e ajudam a tornar a experiência mais amigável e acessível.

---

## 📱 Telas do aplicativo

O MYTOS possui diversas telas navegáveis:

- Splash
- Login
- Cadastro
- Acessibilidade
- Home
- Cardápio
- Detalhes do produto
- Sacola
- Perfil
- Meus Pedidos
- Detalhes do pedido

---

## 🍰 Cardápio

O cardápio apresenta produtos inspirados em diferentes mitologias.

Atualmente, alguns exemplos são:

- **Café de Apolo** — R$ 14,90
- **Néctar de Poseidon** — R$ 16,90
- **Torta de Hera** — R$ 18,90
- **Chá de Kitsune** — R$ 13,90

O usuário pode:

- visualizar os produtos;
- filtrar por categoria;
- adicionar novos produtos;
- editar produtos;
- excluir produtos;
- acessar os detalhes de cada produto.

---

## 🛍️ Sacola

A sacola permite adicionar produtos e controlar suas quantidades.

O usuário pode:

- adicionar produtos;
- aumentar ou diminuir quantidades;
- visualizar o subtotal de cada produto;
- visualizar o valor total;
- finalizar o pedido.

---

## 📦 Meus Pedidos

A tela de **Meus Pedidos** permite gerenciar os pedidos durante a execução do aplicativo.

O usuário pode:

- adicionar novos pedidos;
- editar pedidos;
- excluir pedidos;
- visualizar os detalhes de cada pedido;
- consultar quantidade de itens;
- visualizar os valores;
- visualizar o total calculado.

---

## 🧩 Modelagem de dados

O projeto utiliza diferentes `data class` para representar os dados principais do aplicativo:

### Produto

Representa os produtos disponíveis no cardápio.

### ItemSacola

Representa um produto dentro da sacola e sua quantidade, calculando automaticamente o subtotal.

### Pedido

Representa um pedido realizado, contendo seus itens, valor total e data.

---

## 📋 Listas e gerenciamento de dados

As informações são armazenadas em memória utilizando `mutableStateListOf`.

O projeto possui listas para:

- produtos;
- itens da sacola;
- pedidos.

As listas são exibidas utilizando componentes como `LazyColumn` e `Card`.

---

## 🧭 Navegação

A navegação do aplicativo é realizada utilizando:

- `NavController`
- `NavHost`
- rotas com argumentos
- `navigate()`
- `popBackStack()`

O aplicativo também possui uma **BottomNavigation** para facilitar o acesso às principais áreas:

- Home
- Cardápio
- Sacola
- Perfil

Além disso, as telas de detalhes recebem o identificador do item selecionado para apresentar os dados correspondentes.

---

## ♿ Acessibilidade

O projeto possui uma área dedicada à acessibilidade, incluindo suporte e orientação para utilização do aplicativo com **TalkBack**.

A acessibilidade foi considerada como parte da experiência do usuário e da organização das telas.

---

## ✅ Validações

Foram implementadas validações nos formulários para evitar o cadastro de informações inválidas ou vazias.

Entre os exemplos estão:

- campos obrigatórios;
- quantidade válida;
- valores não vazios;
- número do cartão somente com números;
- nome do titular utilizando letras e espaços;
- validade do cartão no formato `MM/AA`.

O teclado também foi configurado para melhorar a interação com os campos de entrada.

---

## 🛠️ Tecnologias utilizadas

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **Android Studio**
- **Git e GitHub**

---

## 📂 Organização do projeto

O projeto foi organizado em diferentes pacotes para facilitar a manutenção e compreensão do código:

```text
com.example.mytos
├── data
├── model
├── navigation
├── screens
└── ui
    ├── components
    └── theme
