package pt.ipg.mcm.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UploadProductImage", urlPatterns = {"/uploadProductImage/*"})
@ServletSecurity(
    @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL,
        rolesAllowed = {"administrador"}))
public class UploadProductImage extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
