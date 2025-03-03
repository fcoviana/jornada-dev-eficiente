package br.com.fcoviana.apilojalivrosvirtual;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Home", description = "rota inicial da API")
@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "Bem-vindo à API da Loja de Livros Virtual!";
    }
}
