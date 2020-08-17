package controllers.attendances;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import utils.DBUtil;

/**
 * Servlet implementation class AttendanceIndexServlet
 */
@WebServlet("/attendances/index")
public class AttendanceIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }
        List<Attendance> attendance = em.createNamedQuery("getAllAttendances", Attendance.class)
                                     .setFirstResult(15 * (page - 1))
                                     .setMaxResults(15)
                                     .getResultList();

        long attendances_count = (long)em.createNamedQuery("getAttendancesCount", Long.class)
                                       .getSingleResult();

        em.close();

        request.setAttribute("attendances", attendance);
        request.setAttribute("attendances_count", attendances_count);
        request.setAttribute("page", page);
        request.setAttribute("_token", request.getSession().getId());
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/index.jsp");
        rd.forward(request, response);
    }
}