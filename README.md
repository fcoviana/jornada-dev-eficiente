# Jornada DEV Eficiente

## Cognitive Driven Development (CDD)

### Métricas de Complexidade

#### Métrica Geral CDD
| Pontuação | Descrição |
|-----------|-----------|
| 1 ponto   | Acoplamento contextual (classes próprias) |
| 1 ponto   | Estruturas de controle (if, switch, loops, operador ternário) |
| 1 ponto   | Função com argumentos (arrow function) |

**Limites de Pontuação:**
- Máximo de 7 pontos para classes com atributos de dependência
- Máximo de 9 pontos para classes com atributos de estado

#### Métrica Padrão Indústria
| Pontuação | Descrição |
|-----------|-----------|
| 1 ponto   | Qualquer tipo de acoplamento |
| 1 ponto   | Estruturas de controle (if, switch, loops, operador ternário) |
| 1 ponto   | Função com argumentos (arrow function) |

**Limites de Pontuação:**
- Máximo de 7 pontos para classes com atributos de dependência
- Máximo de 9 pontos para classes com atributos de estado

### Projetos Legados
Para códigos existentes que excedem os limites de complexidade, recomenda-se:
- Estabelecer metas graduais de redução (exemplo: 20% a cada refatoração)
- Priorizar a redução em componentes mais críticos
- Documentar a complexidade atual como baseline
- Não permitir que novas alterações aumentem ainda mais a complexidade

---
> **Nota**: CDD é uma metodologia que visa medir e reduzir a complexidade cognitiva do código, tornando-o mais fácil de entender e manter.