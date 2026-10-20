package br.senac.sp.tads.dsw.exemplo5.dto;

import java.time.LocalDate;

public record FuncionarioResponseDTO(
    Long id,
    String nome,
    LocalDate dataContratacao,
    Boolean trabalhoRemoto,
    String nomeDepartamento // Exibe apenas o nome para leitura
) {
    
}
