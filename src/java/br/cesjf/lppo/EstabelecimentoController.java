package br.cesjf.lppo;

import Classes.Anuncio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igor
 */
@WebServlet(name = "EstabelecimentoController", 
        urlPatterns = {"/novo.html","/listar.html"})
public class EstabelecimentoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("listar.html")) {
            List<Anuncio> lista = new ArrayList<>();
            EstabelecimentoDAO dao = new EstabelecimentoDAO();
            lista = dao.listaTodos();

            request.setAttribute("anuncios", lista);
            request.getRequestDispatcher("/WEB-INF/listar.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("novo.html")) {
            request.getRequestDispatcher("/WEB-INF/novo.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getRequestURI().contains("novo.html")){
            Anuncio novoAnuncio = new Anuncio();
            novoAnuncio.setNome(request.getParameter("nome"));
            novoAnuncio.setDescricao(request.getParameter("descricao"));
            novoAnuncio.setPreco(Float.parseFloat(request.getParameter("preco")));
            
            EstabelecimentoDAO dao = new EstabelecimentoDAO();
            dao.criar(novoAnuncio);
            
            response.sendRedirect("listar.html");
        }
    }
    
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
