package br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal;

import br.com.vetorsistemas.ronalds_ws.cadastro.classe.ClasseService;
import br.com.vetorsistemas.ronalds_ws.cadastro.classe.dto.ClasseDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal.dto.ClassificacaoFiscalDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classificacao")
public class ClassificacaoFiscalController {

    private final ClassificacaoFiscalService service;

    public ClassificacaoFiscalController(ClassificacaoFiscalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ClassificacaoFiscalDto>> list(
            @RequestParam(value = "codigoNcm", required = false) String codigoNcm,
            @RequestParam(value = "nomeClassificacao", required = false) String nomeClassificacao,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(codigoNcm, nomeClassificacao, pagina, tamanhoPagina));
    }

    @PostMapping
    public ResponseEntity<ClassificacaoFiscalDto> create(@Valid @RequestBody ClassificacaoFiscalDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassificacaoFiscalDto> update(@PathVariable Integer id,
                                                         @Valid @RequestBody ClassificacaoFiscalDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
