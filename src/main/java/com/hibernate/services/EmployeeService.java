package com.hibernate.services;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Update;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hibernate.model.Address;
import com.hibernate.model.Employee;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private SessionFactory sessionFactory;

	public Boolean createEmployeeRecord(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(employee);
		return Boolean.TRUE;
	}

	public Employee getEmployeeRecord(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee newEmployee = session.get(Employee.class, employee.getEmployeeId());
		return newEmployee;
	}

	public Employee getEmployeeAddressRecord(Address address) {
		Session session = this.sessionFactory.getCurrentSession();
		Address newArddres = session.get(Address.class, address.getAddressId());
		System.out.println(newArddres);
		if (address != null) {
			System.out.println(newArddres.getEmployee());
		}
		Employee employee = newArddres.getEmployee();
		return employee;
	}

	public Boolean deleteAddressByAddressId(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee delEmployee = (Employee) session.load(Employee.class, employee.getEmployeeId());
		Address delAddress = (Address) delEmployee.getAddress().iterator().next();
		Boolean result = delEmployee.getAddress().remove(delAddress);
		session.delete(delAddress);
		if (result) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public List<Employee> getAllEmployee() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> employees = criteria.list();
		return employees;
	}

	public List<Employee> getEmployeeByName(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("employeeName", employee.getEmployeeName()));
		List<Employee> result = criteria.list();
		return result;
	}

	public Boolean updateEmployeeById(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee employeeFromDb = (Employee) session.load(Employee.class, employee.getEmployeeId());
		try {
			JSONObject newUser = new JSONObject(new ObjectMapper().writeValueAsString(employee));
			Iterator<?> it = newUser.keys();

			while (it.hasNext()) {
				String key = (String) it.next();
				if (!newUser.get(key).equals(null)) {
					switch (key) {
					case "employeeName":
						employeeFromDb.setEmployeeName(employee.getEmployeeName());
						break;
					case "employeeNumber":
						employeeFromDb.setEmployeeNumber(employee.getEmployeeNumber());
						break;
					default:
						break;
					}
				}
			}
			session.save(employeeFromDb);
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}

	}

}
