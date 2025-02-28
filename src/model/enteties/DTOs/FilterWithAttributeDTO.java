package model.enteties.DTOs;

import model.enteties.employee.EmployeeAttributes;

public class FilterWithAttributeDTO {
    private String Filter;
    private EmployeeAttributes employeeAttribute;

    public String getFilter() {
        return Filter;
    }

    public void setFilter(String filter) {
        Filter = filter;
    }

    public EmployeeAttributes getEmployeeAttribute() {
        return employeeAttribute;
    }

    public void setEmployeeAttribute(EmployeeAttributes employeeAttribute) {
        this.employeeAttribute = employeeAttribute;
    }
}
