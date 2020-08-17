package models;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "attendances")
@NamedQueries({
    @NamedQuery(
            name = "getAllAttendances",
            query = "SELECT e FROM Attendance AS e ORDER BY e.id DESC"
            ),
    @NamedQuery(
            name = "getAttendancesCount",
            query = "SELECT COUNT(e) FROM Attendance AS e"
            ),
    @NamedQuery(
            name = "getMyAllAttendances",
            query = "SELECT r FROM Report AS r WHERE r.employee = :employee ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getMyAttendancesCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :employee"
            ),
    @NamedQuery(
            name = "getTodayAttendances",
            query = "SELECT a  FROM Attendance AS a WHERE a.attendance_date = :attendance_date and a.employee = :employee"
            ),
    @NamedQuery(
            name = "getGtimeAttendances",
            query = "SELECT a  FROM Attendance AS a WHERE a.attendance_date = :attendance_date and a.employee = :employee and a.gtime_at = :gtime_at"
            )

})

@Entity
public class Attendance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "gtime_at", nullable = true)
    private Timestamp gtime_at;

    @Column(name = "ftime_at", nullable = true)
    private Timestamp ftime_at;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendance_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(LocalDate attendance_date) {
        this.attendance_date = attendance_date;
    }
    public Timestamp getGtime_at() {
        return gtime_at;
    }

    public void setGtime_at(Timestamp gtime_at) {
        this.gtime_at = gtime_at;
    }

    public Timestamp getFtime_at() {
        return ftime_at;
    }
    public void setFtime_at(Timestamp ftime_at){
        this.ftime_at = ftime_at;

    }

    public boolean isEmpty() {
        if(employee == null || attendance_date == null || gtime_at == null && id !=null){
        return true;
        }else{
        return false;
    }
    }
    }
