package com.management.HumanResources.dao;

import javax.annotation.PostConstruct;

import com.management.HumanResources.model.*;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class FirebaseDao extends Dao {

  @Value("${firebase.link}")
  public String firebaseLink;

  @PostConstruct
  private void init() {
    webClient =
        WebClient.builder()
            .baseUrl(firebaseLink)
            .build();
  }

  public String getAllEmployees() {
      return getSingleObject(String.class, "/employees.json");
  }

  public Employee getEmployee(long id) {
      return getSingleObject(Employee.class, "/employees/{employeeId}.json", id);
  }

  public String updateEmployee(Employee employee) {
      return patchSingleObject(String.class, "/employees/{employeeId}.json", employee, employee.getId());
  }

  public String addEmployee(Employee employee) {
      return putSingleObject(String.class, "/employees/{employeeId}.json", employee, employee.getId());
  }

    public String getAllEmployeeTimes() {
        return getSingleObject(String.class, "/time.json");
    }

    public String getEmployeeTime(long employeeId) {
        return getSingleObject(String.class, "/time/{employeeId}.json", employeeId);
    }
  
    public String updateEmployeeTime(EmployeeTime employeeTime) {
        return patchSingleObject(String.class, "/time/{employeeId}.json", employeeTime.toJson(), employeeTime.getEmployeeId());
    }

    public String updateTimeOff(String employeeTime, long id) {
        return putSingleObject(String.class, "/time/{employeeId}/timeOffs.json", "\"" + employeeTime + "\"", id);
    }
  
    public String addEmployeeTime(EmployeeTime employeeTime) {
        return putSingleObject(String.class, "/time/{employeeId}.json", employeeTime.toJson(), employeeTime.getEmployeeId());
    }

    public String addFeedback(Feedback feedback) {
        return putSingleObject(String.class, "/feedback/{feedbackId}.json", feedback, feedback.getFeedbackId());
    }

    public String getAllFeedback() {
        return getSingleObject(String.class, "/feedback.json");
    }

    public String eraseRecord(String recordPath) {
        return deleteObject(String.class, recordPath);
    }

    public String updateHoursRemaining(double hours, long id) {
        return putSingleObject(String.class, "/time/{employeeId}/hoursRemaining.json", hours, id);
    }

    public Feedback getSingleFeedback(long feedbackId) {
        return getSingleObject(Feedback.class, "/feedback/{feedbackId}.json", feedbackId);
    }
}