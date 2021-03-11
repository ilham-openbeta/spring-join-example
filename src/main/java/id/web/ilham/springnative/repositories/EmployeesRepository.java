package id.web.ilham.springnative.repositories;

import id.web.ilham.springnative.models.entities.Employees;
import id.web.ilham.springnative.models.projections.DepartmentAndMinMaxSalaryView;
import id.web.ilham.springnative.models.projections.JobAndTotalEmployeeView;
import id.web.ilham.springnative.models.projections.NameAndDepartmentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    // urutan ke 79 Kimberely Grant tetap diambil walaupun department id nya null
    @Query(nativeQuery = true, value = "select e.first_name || ' ' || e.last_name fullName, d.department_name departmentName \n" +
            "from employees e \n" +
            "left join departments d on e.department_id = d.department_id")
    List<NameAndDepartmentView> leftJoinExample();

    // urutan ke 4 public accountant tetap dihitung total employeenya walaupun nol
    @Query(nativeQuery = true, value = "select j.job_title jobTitle,count(e.employee_id) totalEmployee\n" +
            "from employees e \n" +
            "right join jobs j on e.job_id = j.job_id \n" +
            "group by j.job_title")
    List<JobAndTotalEmployeeView> rightJoinExample();

    // mengambil semua data department yg digunakan pada employee
    @Query(nativeQuery = true, value = "select d.department_name departmentName, min(e.salary) minSalary,max(e.salary) maxSalary\n" +
            "from employees e \n" +
            "inner join departments d on d.department_id = e.department_id \n" +
            "group by d.department_name")
    List<DepartmentAndMinMaxSalaryView> innerJoinExample();

    // mengambil semua data yg null dari kedua tabel
    @Query(nativeQuery = true, value = "select e.first_name || ' ' || e.last_name fullName, d.department_name departmentName \n" +
            "from employees e \n" +
            "full outer join departments d on e.department_id = d.department_id\n" +
            "where e.department_id is null or d.department_id is null")
    List<NameAndDepartmentView> outerJoinExample();

// join tabel employees ke job_history berdasar id
// jika id ada maka update job_history kolom modified_date
// jika id tidak ada maka insert ke job_history
    @Modifying
    @Query(nativeQuery = true, value = "merge into job_history h\n" +
            "using (select * from employees where employee_id=102) e\n" +
            "on (h.employee_id = e.employee_id)\n" +
            "when matched then\n" +
            "update set modified_date = current_timestamp\n" +
            "when not matched then\n" +
            "insert (employee_id, start_date, end_date, job_id, department_id, modified_date)\n" +
            "values (e.employee_id, TO_DATE('02-02-2020'), sysdate, e.job_id, e.department_id, current_timestamp)")
    Integer mergeExample();
}
