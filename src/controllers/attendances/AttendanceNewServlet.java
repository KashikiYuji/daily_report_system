package controllers.attendances;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;

/**
 * Servlet implementation class AttendanceNewServlet
 */
@WebServlet("/attendances/new")
public class AttendanceNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Attendance a = new Attendance();
        a.setAttendance_date(LocalDate.now());
        request.setAttribute("attendance", a);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/new.jsp");
        rd.forward(request, response);
    }

}