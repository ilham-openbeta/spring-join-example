package id.web.ilham.springnative.services;

import id.web.ilham.springnative.models.projections.DepartmentAndMinMaxSalaryView;
import id.web.ilham.springnative.models.projections.JobAndTotalEmployeeView;
import id.web.ilham.springnative.models.projections.NameAndDepartmentView;
import id.web.ilham.springnative.repositories.EmployeesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<NameAndDepartmentView> leftJoinExample() {
        return employeesRepository.leftJoinExample();
    }

    public List<JobAndTotalEmployeeView> rightJoinExample() {
        return employeesRepository.rightJoinExample();
    }

    public List<DepartmentAndMinMaxSalaryView> innerJoinExample() {
        return employeesRepository.innerJoinExample();
    }

    public List<NameAndDepartmentView> outerJoinExample() {
        return employeesRepository.outerJoinExample();
    }

    @Transactional
    public Integer mergeExample() {
        return employeesRepository.mergeExample();
    }
}
