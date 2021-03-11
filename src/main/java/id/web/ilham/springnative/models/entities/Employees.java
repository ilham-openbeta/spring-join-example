package id.web.ilham.springnative.models.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Table
@Entity
@Data
public class Employees {

    @Id
    private Integer employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date hireDate;

    private String jobId;

    private Integer salary;

    private Integer commissionPct;

    private Integer managerId;

    private Integer departmentId;
}
