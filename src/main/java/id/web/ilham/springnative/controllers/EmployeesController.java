package id.web.ilham.springnative.controllers;

import id.web.ilham.springnative.models.projections.DepartmentAndMinMaxSalaryView;
import id.web.ilham.springnative.models.projections.JobAndTotalEmployeeView;
import id.web.ilham.springnative.models.projections.NameAndDepartmentView;
import id.web.ilham.springnative.services.EmployeesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    // Example URL : http://localhost:8080/api/v1/employee/leftjoin
    @GetMapping("/leftjoin")
    public List<NameAndDepartmentView> leftJoin() {
        return employeesService.leftJoinExample();
    }

    // Example URL : http://localhost:8080/api/v1/employee/rightjoin
    @GetMapping("/rightjoin")
    public List<JobAndTotalEmployeeView> rightJoin() {
        return employeesService.rightJoinExample();
    }

    // Example URL : http://localhost:8080/api/v1/employee/innerjoin
    @GetMapping("/innerjoin")
    public List<DepartmentAndMinMaxSalaryView> innerJoin() {
        return employeesService.innerJoinExample();
    }

    // Example URL : http://localhost:8080/api/v1/employee/outerjoin
    @GetMapping("/outerjoin")
    public List<NameAndDepartmentView> outerJoin() {
        return employeesService.outerJoinExample();
    }

    // Example URL : http://localhost:8080/api/v1/employee/merge
    @GetMapping("/merge")
    public Integer merge() {
        return employeesService.mergeExample();
    }

}
