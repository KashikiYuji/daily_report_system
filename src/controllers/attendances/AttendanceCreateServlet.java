package controllers.attendances;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class AttendanceCreateServlet
 */
@WebServlet("/attendances/create")
public class AttendanceCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Attendance a = new Attendance();

            a.setEmployee((Employee) request.getSession().getAttribute("login_employee"));

            a.setAttendance_date(LocalDate.now());

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            sdf.format(currentTime);

            request.setAttribute("_token", request.getSession().getId());

            Employee login_employee = (Employee) request.getSession().getAttribute("login_employee");
            LocalDate attendance_date = LocalDate.now();
            List<Attendance> attendances = em.createNamedQuery("getTodayAttendances", Attendance.class)
                    .setParameter("employee", login_employee)
                    .setParameter("attendance_date", attendance_date)
                    .getResultList();
            if (attendances == null || attendances.isEmpty()) {
                Timestamp gtime_at = new Timestamp(System.currentTimeMillis());
                Timestamp gt = new Timestamp(gtime_at.getTime());
                a.setGtime_at(gt);

                em.getTransaction().begin();
                em.persist(a);
                em.getTransaction().commit();
                em.close();
                response.sendRedirect(request.getContextPath() + "/attendances/index");

            } else {
                request.getSession().setAttribute("flush", "出勤打刻は完了しています");

                response.sendRedirect(request.getContextPath() + "/attendances/index");
            }
        }
    }
}