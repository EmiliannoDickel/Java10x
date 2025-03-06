package dev.java10x.cadastrobasico.Controller;

import dev.java10x.cadastrobasico.Entity.UsuarioDTO;
import dev.java10x.cadastrobasico.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/usuario/ui")

public class UsuarioControllerUi { //criamos essa class mais para o uso experimental para o thymeleaf, se fosse react seria uso das API

    private final UsuarioService usuarioService;

    public UsuarioControllerUi(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public String pegarUsuarios (Model model) {
        List<UsuarioDTO> usuariosLista = usuarioService.pegarUsuarios();
        model.addAttribute("usuarios", usuariosLista);
        return "UsuarioLista"; //tem que retornar o nome da pagina html que renderiza
    }

    @GetMapping("/{id}") //como é redirecionamento, nao pode ser delete, tem que ser get, para pegar essa requisicao e enviar pro delete
    public String excluirUsuario (@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return "redirect:/api/usuario/ui/all";
    }

    @GetMapping("/listar/{id}")
    public String listarUsuarioPorId(@PathVariable Long id, Model model) {
        UsuarioDTO usuario =  usuarioService.pegarUsuarioId(id);
        if (usuario !=null) {
            model.addAttribute("usuario", usuario);
            return "detalhesusuario";
        } else {
            model.addAttribute("mensagem", "Usuário não encontrado");
            return "UsuarioLista";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "adicionarUsuario";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute UsuarioDTO usuario, RedirectAttributes redirectAttributes) {
        usuarioService.criarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/api/usuario/ui/all";
    }

}
