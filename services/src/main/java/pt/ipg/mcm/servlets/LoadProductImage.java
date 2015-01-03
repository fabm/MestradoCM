package pt.ipg.mcm.servlets;

import com.google.gson.Gson;
import pt.ipg.mcm.controller.ProdutoDao;
import pt.ipg.mcm.errors.MestradoException;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "LoadProductImage", urlPatterns = {"/loadProductImage/*"})
public class LoadProductImage extends HttpServlet {

  @EJB
  private ProdutoDao produtoDao;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    long id = Long.parseLong(req.getPathInfo().substring(1));

    try {
      resp.setContentType("image/png");
      BufferedImage bufferedImage = ImageIO.read(produtoDao.getFoto(id));
      ServletOutputStream out = resp.getOutputStream();
      ImageIO.write(bufferedImage, "png", out);
      out.close();
    } catch (MestradoException e) {
      resp.setContentType("application/json");
      Gson gson = new Gson();
      resp.getWriter().write(gson.toJson(e.toMap()));
    }
  }
}
