package atividade.web;

import jakarta.servlet.http.Cookie;
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
    @RequestMapping(value = "/cookie",method = RequestMethod.GET)
        public void doCookie(HttpServletResponse response, HttpServletRequest resquest) throws IOException{
        Cookie cookie1 = new Cookie("1", "um");
        Cookie cookie2 = new Cookie("2", "dois");
        Cookie cookie3 = new Cookie("3", "tres");
        Cookie cookie4 = new Cookie("4", "quatro");
        Cookie cookie5 = new Cookie("5", "cinco");

        cookie4.setMaxAge(30); //tempo do cookie
        cookie5.setMaxAge(30); //tempo do cookie

        response.addCookie(cookie1); //adiciona o cookie na resposta
        response.addCookie(cookie2);
        response.addCookie(cookie3);
        response.addCookie(cookie4);
        response.addCookie(cookie5);
        response.sendRedirect("/cookieVer");

    }
    @RequestMapping(value = "/cookieVer", method = RequestMethod.GET)
        public void doCookieVer(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var write = response.getWriter();
        Cookie cookies[] = request.getCookies();
        String nome, valor;

        if(cookies !=null){
            for (var c: cookies){
                nome = c.getName();
                valor = c.getValue();
                write.println("Cookie: " + nome + " Valor: " + valor);

            }
        }
    }

}

