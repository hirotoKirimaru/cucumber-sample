package kirimaru.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/default")
public class DefultServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        throw new RuntimeException("きりん");


//        response.setContentType("text/html; charset=Shift_JIS");
//        PrintWriter out = response.getWriter();
//
//        out.println(createHTML("GET"));
//
//        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();

        out.println(createHTML("POST"));

        out.close();
    }

    protected String createHTML(String methodType){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>サンプル</title>");
        sb.append("</head>");
        sb.append("<body>");

        sb.append("<p>");
        sb.append(methodType);
        sb.append("メソッドで呼び出されました</p>");

        sb.append("</form>");

        sb.append("</body>");
        sb.append("</html>");

        return (new String(sb));
    }
}
