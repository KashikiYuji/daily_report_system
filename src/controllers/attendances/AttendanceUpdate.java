package controllers.attendances;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class AttendanceUpdate
 */
@WebServlet("/attendances/update")
public class AttendanceUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceUpdate() {
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

            try {
                Employee login_employee = (Employee) request.getSession().getAttribute("login_employee");
                LocalDate attendance_date = LocalDate.now();
                Attendance a = em.createNamedQuery("getTodayAttendances", Attendance.class)
                        .setParameter("employee", login_employee)
                        .setParameter("attendance_date", attendance_date)
                        .getSingleResult();

                if (a != null && a.getFtime_at() == null) {
                    Timestamp ftime_at = new Timestamp(System.currentTimeMillis());
                    Timestamp ft = new Timestamp(ftime_at.getTime());
                    a.setFtime_at(ft);

                    request.getSession().setAttribute("flush", "退勤打刻をしました");

                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    em.close();


                    response.sendRedirect(request.getContextPath() + "/attendances/index");

                } else {
                    request.getSession().setAttribute("flush", "退勤打刻は完了しています");

                    response.sendRedirect(request.getContextPath() + "/attendances/index");
                }
            } catch (NoResultException e) {
                request.getSession().setAttribute("flush", "出勤打刻をしてください");

                response.sendRedirect(request.getContextPath() + "/attendances/index");
            }
        }
    }
}
