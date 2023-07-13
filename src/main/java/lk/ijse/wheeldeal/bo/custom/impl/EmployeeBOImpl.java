package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.EmployeeBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.EmployeeDAO;
import lk.ijse.wheeldeal.dto.EmployeeDTO;
import lk.ijse.wheeldeal.entity.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    //with Factory Design Pattern
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<Employee> all = employeeDAO.getAll();
        List<EmployeeDTO> allEmployees = new ArrayList<>();
        for (Employee e : all){
            allEmployees.add(new EmployeeDTO(e.getEmpID(), e.getName(), e.getJobRole(), e.getTel()));
        }
        return allEmployees;
    }

    @Override
    public EmployeeDTO searchEmployee(String empID) throws SQLException, ClassNotFoundException {
        Employee e = employeeDAO.search(empID);
        return new EmployeeDTO(e.getEmpID(), e.getName(), e.getJobRole(), e.getTel());
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getEmpID(), dto.getName(), dto.getJobRole(), dto.getTel()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmpID(), dto.getName(), dto.getJobRole(), dto.getTel()));
    }

    @Override
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(empId);
    }

    @Override
    public boolean isAdmin(String empID) throws SQLException {
        return employeeDAO.isAdmin(empID);
    }

    @Override
    public boolean checkEmployee(String empId) throws SQLException {
        return employeeDAO.checkEmployee(empId);
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNextID();
    }

    @Override
    public String getEmployeeName(String empId) throws SQLException {
        return employeeDAO.getEmployeeName(empId);
    }

    @Override
    public String getEmployeeRole(String empId) throws SQLException {
        return employeeDAO.getEmployeeRole(empId);
    }
}