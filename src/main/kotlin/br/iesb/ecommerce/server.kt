import br.iesb.ecommerce.services.ofertas.Oferta
import br.iesb.ecommerce.services.ofertas.OfertaDescontoPorcentagem
import br.iesb.ecommerce.entities.produtos.Produto
import br.iesb.ecommerce.entities.produtos.ProdutoVendedor
import br.iesb.ecommerce.entities.usuario.Usuario
import br.iesb.ecommerce.entities.usuario.UsuarioComum
import br.iesb.ecommerce.entities.vendedores.Vendedor
import br.iesb.ecommerce.entities.vendedores.VendedorPadrao
import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.management.GerenciamentoAlteracao
import br.iesb.ecommerce.management.GerenciamentoCadastro
import br.iesb.ecommerce.management.GerenciamentoRemocao
import br.iesb.ecommerce.services.categorias.Categoria
import br.iesb.ecommerce.services.categorias.CategoriaPadrao
import br.iesb.ecommerce.services.favoritos.FavoritoPadrao
import br.iesb.ecommerce.storage.SysArmazenamentoEmMemoriaCategorias
import br.iesb.ecommerce.storage.SysArmazenamentoEmMemoriaOferta
import br.iesb.ecommerce.storage.SysArmazenamentoEmMemoriaUsuario
import br.iesb.ecommerce.storage.SysArmazenamentoEmMemoriaVendedor
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

val sysArmazenamentoCategorias = SysArmazenamentoEmMemoriaCategorias
val sysArmazenamentoVendedor = SysArmazenamentoEmMemoriaVendedor
val sysArmazenamentoOferta = SysArmazenamentoEmMemoriaOferta
val sysArmazenamentoUsuario = SysArmazenamentoEmMemoriaUsuario


fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080

    embeddedServer(Netty, port) {
        routing {
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }

            get("/usuario/listar") {
                call.respond(sysArmazenamentoUsuario.obterUsuarios())
            }
            get("/usuario/encontrar"){
                val usuario = call.receive<UsuarioComum>()

                try{
                    call.respond(sysArmazenamentoUsuario.obterUsuario(usuario.obterId()!!))
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            post("/usuario/cadastrar/usuario_comum"){
                val novoUsuario = call.receive<UsuarioComum>()
                try{
                    GerenciamentoCadastro().cadastrarUsuarioComum(novoUsuario, sysArmazenamentoUsuario)
                    call.respond("Usuario comum cadastrado com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e:Exception){
                    call.respond("Erro insesperado: ${e.message}")
                }
            }
            delete("/usuario/deletar"){
                val usuario = call.receive<Usuario>()
                try{
                    GerenciamentoRemocao().excluirUsuario(usuario, sysArmazenamentoUsuario)
                    call.respond("Usuario deletado com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            put("/usuario/atualizar"){
                val novoUsuario = call.receive<Usuario>()

                try{
                    GerenciamentoAlteracao().atualizarUsuario(novoUsuario, sysArmazenamentoUsuario)
                    call.respond("Usuario atualizado com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            get("/usuario/favoritos/listar"){
                val usuario = call.receive<UsuarioComum>()

                call.respond(sysArmazenamentoUsuario.obterUsuario(usuario.obterId()!!).obterFavoritos())
            }
                post("/usuario/favoritos/adicionar"){
                val favorito = call.receive<FavoritoPadrao>()

                try{
                    GerenciamentoCadastro().cadastrarFavoritoPadraoEmUsuario(favorito,
                            sysArmazenamentoUsuario)
                    call.respond("Favorito adicionado com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }

            }
            delete("/usuario/favoritos/deletar"){
                val favorito = call.receive<FavoritoPadrao>()

                try{
                    GerenciamentoRemocao().excluirFavoritoEmUsuario(favorito,
                            sysArmazenamentoUsuario)
                }
                catch (e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            get("/vendedor/listar"){
                call.respond(sysArmazenamentoVendedor.obterVendedores())
            }
            get("/vendedor/encontrar"){
                val vendedor = call.receive<Vendedor>()

                try{
                    call.respond(sysArmazenamentoVendedor.obterVendedor(vendedor.obterId()!!))
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            post("/vendedor/cadastrar/vendedor_padrao"){
                val novoVendedor = call.receive<VendedorPadrao>()

                try{
                    GerenciamentoCadastro().cadastrarVendedorPadrao(novoVendedor, sysArmazenamentoVendedor)
                    call.respond("Vendedor padrão cadastrado com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            put("/vendedor/atualizar"){
                val novoVendedor = call.receive<VendedorPadrao>()

                try{
                    GerenciamentoAlteracao().atualizarVendedor(novoVendedor, sysArmazenamentoVendedor)
                    call.respond("Vendedor atualizado com sucesso")
                }
                catch(e: ExistsException){
                    call.respond(" ${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            delete("/vendedor/deletar"){
                val vendedor = call.receive<Vendedor>()

                try{
                    GerenciamentoRemocao().excluirVendedor(vendedor, sysArmazenamentoVendedor)
                    call.respond("Vendedor removido com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            get("/produtos/listar"){
                call.respond(sysArmazenamentoVendedor.obterProdutos())
            }
            post("/produto/cadatrar/produtoVendedor"){
                val novoProduto = call.receive<ProdutoVendedor>()

                try{
                    GerenciamentoCadastro().cadastrarProdutoVendedorFretePadrao(novoProduto,
                            sysArmazenamentoVendedor)
                    call.respond("Produto cadastrado com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            delete("/produto/deletar"){
                val produto = call.receive<Produto>()

                try{
                    GerenciamentoRemocao().excluirProduto(produto, sysArmazenamentoVendedor)
                    call.respond("Produto removido com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            put("/produto/atualizar"){
                val novoProduto = call.receive<Produto>()

                try{
                    GerenciamentoAlteracao().atualizarProduto(novoProduto, sysArmazenamentoVendedor)
                    call.respond("Produto atualizado com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            get("/categoria/lista"){
                call.respond(sysArmazenamentoCategorias.obterCategorias())
            }
            post("/categoria/cadastrar/categoria_padrao"){
                val novaCategoria = call.receive<CategoriaPadrao>()

                try{
                    GerenciamentoCadastro().cadastrarCategoriaPadrao(novaCategoria, sysArmazenamentoCategorias)
                    call.respond("Categoria cadastrada com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("Error: ${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: $e.message")
                }
            }
            post("/categoria/adicionar_produto"){
                val categoriaComProdutos = call.receive<Categoria>()

                try{
                    GerenciamentoAlteracao().addProdutoEmCategoria(categoriaComProdutos,
                                                                sysArmazenamentoCategorias)
                    call.respond("Produtos adicionado em Categoria com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("Error: ${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: $e.message")
                }
            }
            delete("/categoria/deletar_produtos"){
                val categoriaComProdutos = call.receive<Categoria>()

                try{
                    GerenciamentoAlteracao().removerProdutoEmCategoria(categoriaComProdutos,
                                                sysArmazenamentoCategorias)
                    call.respond("Produtos removidos de Categoria com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("Error: ${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: $e.message")
                }
            }
            delete("/categoria/deletar"){
                val categoria = call.receive<Categoria>()

                try{
                    GerenciamentoRemocao().excluirCategoria(categoria, sysArmazenamentoCategorias)
                    call.respond("Categoria deletada com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            put("/categoria/atualizar"){
                val categoria = call.receive<CategoriaPadrao>()

                try{
                    GerenciamentoAlteracao().atualizarCategoria(categoria, sysArmazenamentoCategorias)
                    call.respond("Categoria atualizada com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception) {
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            get("/ofertas/listar"){
                call.respond(sysArmazenamentoOferta.obterOfertas())
            }
            post("/ofertas/cadastrar/ofertas_desconto_porcentagem"){
                val novaOferta = call.receive<OfertaDescontoPorcentagem>()

                try{
                    GerenciamentoCadastro().cadastrarOfertaDescontoPorcentagem(novaOferta,
                            sysArmazenamentoOferta, sysArmazenamentoVendedor)
                    call.respond("Oferta cadastrada com sucesso")
                }
                catch(e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            put("/ofertas/atualizar"){
                val ofertaAtualizada = call.receive<Oferta>()

                try{
                    GerenciamentoAlteracao().atualizarOferta(ofertaAtualizada,
                    sysArmazenamentoOferta)
                }
                catch (e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
            delete("/oferta/deletar"){
                val oferta = call.receive<Oferta>()

                try{
                    GerenciamentoRemocao().excluirOferta(oferta, sysArmazenamentoOferta)
                }
                catch (e: ExistsException){
                    call.respond("${e.message}")
                }
                catch(e: Exception){
                    call.respond("Erro inesperado: ${e.message}")
                }
            }
        }
    }.start(wait = true)
}