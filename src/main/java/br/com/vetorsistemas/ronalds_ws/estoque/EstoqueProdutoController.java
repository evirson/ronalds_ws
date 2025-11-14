package br.com.vetorsistemas.ronalds_ws.estoque;

import br.com.vetorsistemas.ronalds_ws.estoque.dto.EstoqueProdutoDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173","http://localhost:4200"}, allowCredentials = "false")
@RestController
@RequestMapping("/api/estoque/produtos")
public class EstoqueProdutoController {

    private final EstoqueProdutoService service;

    public EstoqueProdutoController(EstoqueProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<EstoqueProdutoDto>> list(
            @RequestParam(value = "termo", required = false) String termo,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(value = "ordenar-por", defaultValue = "nome") String ordenarPor,
            @RequestParam(value = "direcao", defaultValue = "asc") String direcao
    ) {
        return ResponseEntity.ok(service.list(termo, pagina, tamanhoPagina, ordenarPor, direcao));
    }
}
