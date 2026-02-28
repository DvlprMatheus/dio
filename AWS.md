# RELATÓRIO DE IMPLEMENTAÇÃO DE SERVIÇOS AWS

**Data:** 28 de fevereiro de 2026
**Empresa:** Matheus Cruz

---

## Introdução

Este relatório apresenta o processo de implementação de serviços na empresa **Abstergo Industries**, realizado por **Matheus Cruz**.

O objetivo do projeto foi selecionar três serviços da AWS com a finalidade de promover a diminuição imediata de custos operacionais em infraestrutura de TI, além de aumentar a eficiência, escalabilidade e controle financeiro do ambiente em nuvem.

---

## Descrição do Projeto

O projeto foi dividido em três etapas estratégicas, cada uma com foco direto na redução de desperdícios e otimização de recursos.

---

### Etapa 1

- **Nome da ferramenta:** Amazon EC2 Auto Scaling  
- **Foco da ferramenta:** Otimização automática de recursos computacionais  
- **Descrição do caso de uso:**

Foi identificado que os servidores EC2 estavam superdimensionados e ativos 24 horas por dia, mesmo em horários de baixa demanda.

Com a implementação do EC2 Auto Scaling, foram criados grupos de escalabilidade automática baseados em métricas de utilização de CPU. Dessa forma:

- O número de instâncias aumenta automaticamente em horários de pico.
- O número de instâncias reduz em períodos de baixa utilização.
- Instâncias ociosas foram eliminadas.

**Resultado esperado:** Redução média estimada de 30% nos custos mensais com computação.

---

### Etapa 2

- **Nome da ferramenta:** Amazon RDS  
- **Foco da ferramenta:** Redução de custos operacionais com banco de dados  
- **Descrição do caso de uso:**

A empresa utilizava banco de dados autogerenciado em instâncias EC2, o que gerava custos elevados com manutenção, backups manuais e maior risco operacional.

Foi realizada a migração para o Amazon RDS, utilizando:

- Instância sob demanda adequada ao perfil de uso
- Backups automatizados
- Desativação de recursos avançados em ambientes não críticos
- Adoção de instâncias reservadas para produção

**Resultado esperado:**

- Redução de custos com administração manual
- Aumento de disponibilidade
- Economia estimada de 20% em comparação ao modelo anterior

---

### Etapa 3

- **Nome da ferramenta:** AWS Cost Explorer  
- **Foco da ferramenta:** Monitoramento e governança financeira  
- **Descrição do caso de uso:**

Foi identificado que a empresa não possuía visibilidade detalhada dos custos por serviço, projeto ou ambiente (desenvolvimento, homologação e produção).

Com a implementação do AWS Cost Explorer:

- Foram criados relatórios personalizados por centro de custo.
- Foram configurados alertas de orçamento.
- Identificaram-se recursos subutilizados.
- Foram desativados volumes e snapshots órfãos.

**Resultado esperado:**

- Maior previsibilidade financeira
- Eliminação de desperdícios
- Economia adicional estimada de 10% ao mês

---

## Conclusão

A implementação dos serviços Amazon EC2 Auto Scaling, Amazon RDS e AWS Cost Explorer na empresa **Abstergo Industries** proporcionou uma reestruturação estratégica da infraestrutura em nuvem.

Os principais benefícios esperados são:

- Redução total estimada de até 40% nos custos mensais de infraestrutura
- Melhor aproveitamento de recursos computacionais
- Aumento da eficiência operacional
- Maior governança financeira e previsibilidade de gastos
- Infraestrutura com alta disponibilidade

Recomenda-se a continuidade do monitoramento mensal de custos, a adoção futura de Savings Plans ou instâncias reservadas adicionais e a implementação de políticas de desligamento automático para ambientes não produtivos.

---

**Assinatura do Responsável pelo Projeto:**

_________________________________________
Matheus Cruz
