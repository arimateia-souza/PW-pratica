package atividade.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class ControllerAtividade {


    @RequestMapping(method = RequestMethod.POST,value = "/doSomething")
    public void doSomething(HttpServletResponse response, HttpServletRequest request)throws IOException{
        var writer = response.getWriter();

        var nome = request.getParameter("nome");
        var email =request.getParameter("email");

        response.setContentType("text/HTML");

        writer.println("<html>" +
                "<body>"+
                "<h1>" + nome + "</h1>"+
                "<p> Email: " + email + "</p>"
        );
        writer.println("</body>"+
                "</html>"
        );


    }


    @RequestMapping(value = "/sessao")
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
            HttpSession session = request.getSession();
            Integer acessoCount = (Integer) session.getAttribute("contador");
            if (acessoCount == null){
                acessoCount = 0;
            }else{
                acessoCount++;
            }
            session.setAttribute("contador", acessoCount);
            String id = session.getId();
            long creationTime = session.getCreationTime();
            long lastAccessedTime = session.getLastAccessedTime();
            var out = response.getWriter();
            out.println("id: "+id+" creationTime: " + creationTime + " lastAccessedTime: " + lastAccessedTime + " count: "+ acessoCount);
        }

}

