# 🥗 Vitatrack API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

API REST desenvolvida em Java + Spring Boot para controle nutricional, com cálculo metabólico, consumo diário e acompanhamento alimentar baseado na tabela TACO.

---

## 🚀 Funcionalidades

✔ Cadastro de usuários  
✔ Cálculo de IMC e classificação  
✔ Cálculo de TMB (Mifflin-St Jeor)  
✔ Cálculo de GET (Gasto Energético Total)  
✔ Distribuição de macronutrientes por objetivo  
✔ Integração com base alimentar (TACO)  
✔ Busca de alimentos  
✔ Registro diário de refeições  
✔ Cálculo automático de calorias e macros  
✔ Comparação entre meta e consumo

---

## 🧠 Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Lombok

---

## 📊 Fluxo da aplicação

👉 Cadastro do usuário

👉 Cálculo metabólico  
(IMC, TMB, GET e macronutrientes)

👉 Registro alimentar diário

👉 Cálculo automático de consumo

👉 Comparação com meta nutricional

---

## 📌 Endpoints principais

### 👤 Usuários

POST   /users  
GET    /users  
GET    /users/{id}  
PUT    /users/{id}  
DELETE /users/{id}

---

### 🍎 Alimentos

GET /foods  
GET /foods/search?nome=banana

---

### 🍽️ Diário alimentar

POST /daily-food-logs/users/{userId}/date/{data}/items  
GET  /daily-food-logs/users/{userId}/date/{data}

---

### 📊 Resumo nutricional

GET /nutrition-summary/users/{userId}/date/{data}

---

## 📥 Exemplos

### Criar usuário

{
"nome": "Carlos",
"email": "carlos@email.com",
"senha": "123456",
"idade": 23,
"gender": "MASCULINO",
"peso": 85.0,
"altura": 1.73,
"objetivo": "PERDER_PESO",
"nivelAtividade": "MODERADA"
}

---

### Adicionar alimento

{
"foodId": 182,
"quantidadeGramas": 150,
"refeicao": "CAFE_DA_MANHA"
}

---

### Resumo nutricional

{
"metaCalorias": 2822.94,
"caloriasConsumidas": 147.38,
"caloriasRestantes": 2675.56,
"excedeuCalorias": false
}

---

## 🧠 Regras de negócio

- TMB calculado com Mifflin-St Jeor
- GET baseado no nível de atividade
- Macronutrientes baseados em peso + objetivo
- Alimentos baseados na Tabela TACO
- Cálculo proporcional por gramas consumidas

---

## 🎯 Objetivo

Projeto desenvolvido para demonstrar:

✔ Construção de APIs REST  
✔ Aplicação de regras de negócio reais  
✔ Modelagem de domínio  
✔ Integração com banco de dados  
✔ Organização em arquitetura de camadas

---

## 👨‍💻 Autor

Carlos Lopes

GitHub: https://github.com/CarlosEduLopes  
LinkedIn: https://www.linkedin.com/in/carlosedulopescadu

---

## 📌 Status do projeto

🚧 Em desenvolvimento — próximas melhorias:

- Dashboard nutricional
- Histórico semanal
- Sugestões inteligentes de dieta  