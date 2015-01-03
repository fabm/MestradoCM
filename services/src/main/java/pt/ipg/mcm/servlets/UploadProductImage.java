package pt.ipg.mcm.servlets;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pt.ipg.mcm.controller.ProdutoDao;
import pt.ipg.mcm.errors.MestradoException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "UploadProductImage", urlPatterns = {"/uploadProductImage"})
@ServletSecurity(
    @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL,
        rolesAllowed = {"administrador"}))
public class UploadProductImage extends HttpServlet {

  @EJB
  private ProdutoDao produtoDao;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    boolean isMultipart = ServletFileUpload.isMultipartContent(req);

    resp.setContentType("application/json");

    Gson gson = new Gson();

    try {
      if (!isMultipart) {
        throw new MestradoException(-1, "Nenhum ficheiro para fazer upload");
      }

      DiskFileItemFactory factory = new DiskFileItemFactory();
      int maxFileSize = 50 * 1024;
      int maxMemSize = 4 * 1024;

      factory.setSizeThreshold(maxMemSize);

      ServletFileUpload upload = new ServletFileUpload(factory);
      upload.setSizeMax(maxFileSize);


      try {
        List<FileItem> fileItems = upload.parseRequest(req);
        if (fileItems.size() > 1) {
          throw new MestradoException(-2, "Apenas um ficheiro deve ser carragado para o servidor e não %d", fileItems.size());
        }
        FileItem fileItem = fileItems.get(0);
        InputStream is = fileItem.getInputStream();

        String idStr = req.getParameter("id-foto");

        if (idStr == null) {
          throw new MestradoException(-1, "Falta definir o id do produto");
        }

        long id;
        try {
          id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
          throw new MestradoException(-2, "O id %s é inválido", idStr);
        }

        produtoDao.saveFoto(id, is);

      } catch (FileUploadException e) {
        throw new MestradoException(-3, "Problema ao tentar fazer o upload para o servidor");
      }

    } catch (MestradoException e) {
      resp.getWriter().write(gson.toJson(e.toMap()));
    }

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.getWriter().write("não esperado");
  }
}
