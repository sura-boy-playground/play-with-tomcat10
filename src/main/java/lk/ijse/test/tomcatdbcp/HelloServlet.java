package lk.ijse.test.tomcatdbcp;

import java.io.*;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet(name = "helloServlet", value = "/hello", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {
    private String message;

    @Resource(name= "java:comp/env/bean/Something")
    private Something something;

    @PostConstruct
    public void doSomething(){
        System.out.println("Working....!");
    }

    public void init() {
        message = "Hello World!";
        try {
            InitialContext ctx = new InitialContext();
            Something lookup = (Something) ctx.lookup("java:comp/env/bean/Something");
            System.out.println(lookup);
            System.out.println(something);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}