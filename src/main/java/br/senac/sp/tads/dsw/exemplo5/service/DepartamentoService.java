package br.senac.sp.tads.dsw.exemplo5.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.senac.sp.tads.dsw.exemplo5.dto.DepartamentoRequestDTO;
import br.senac.sp.tads.dsw.exemplo5.dto.DepartamentoResponseDTO;
import br.senac.sp.tads.dsw.exemplo5.model.Departamento;
import br.senac.sp.tads.dsw.exemplo5.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public List<DepartamentoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(d -> new DepartamentoResponseDTO(
                        d.getId(), d.getNome(), d.getOrcamento()))
                .toList();
    }

    public DepartamentoResponseDTO buscarPorId(Long id) {
        Departamento d = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new DepartamentoResponseDTO(d.getId(), d.getNome(), d.getOrcamento());
    }

    public DepartamentoResponseDTO criar(DepartamentoRequestDTO dto) {
        Departamento d = new Departamento();
        d.setNome(dto.nome());
        d.setOrcamento(dto.orcamento());

        d = repository.save(d);

        return new DepartamentoResponseDTO(d.getId(), d.getNome(), d.getOrcamento());
    }

    public DepartamentoResponseDTO atualizar(Long id, DepartamentoRequestDTO dto) {
        Departamento d = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        d.setNome(dto.nome());
        d.setOrcamento(dto.orcamento());

        d = repository.save(d);

        return new DepartamentoResponseDTO(d.getId(), d.getNome(), d.getOrcamento());
    }

    public void apagar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
