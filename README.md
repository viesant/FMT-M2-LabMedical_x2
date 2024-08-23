# LABMedical

Projeto para validar conhecimentos do módulo 2 do curso FullStack - turma Health - LAB365 - SESI/SENAI-SC. LABMedical é um sistema em Java de gerenciamento de pacientes, consultas e exames de uma clínica fictícia.

## Índice

- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)

## Visão Geral

Projeto com objetivo de gerenciar pacientes, consultas e exames usando os conhecimentos obtidos no módulo 2 do curso FullStack[Health].

Possui controle de autenticação e segurança, comunicação com banco de dados, validação de dados, etc.

## Funcionalidades

Principais funcionalidades:

- Cadastro de usuários, com perfil de admin, médico ou paciente
- Cadastro e gerenciamento de pacientes
- Registro de consultas e exames
- Filtros avançados para busca de registros
- Exclusão em cascata de registros relacionados
- Autenticação e autorização de usuários

## Instalação

Siga as etapas abaixo para instalar o projeto localmente:

1. Clone o repositório:
    ```bash
    git clone https://github.com/viesant/FMT-M2-LabMedical_x2
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd FMT-M2-LabMedical_x2
    ```

3. Instale as dependências:
    ```bash
    mvn install
    ```

## Configuração

Detalhe as configurações necessárias antes de executar o projeto:

1. **Defina as variáveis de ambiente.**

2. **Configure o banco de dados no arquivo `application.properties`** ou use um perfil específico.

3. **(Opcional)** Insira qualquer configuração adicional que o projeto necessite.
