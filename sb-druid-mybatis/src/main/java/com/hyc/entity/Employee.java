package com.hyc.entity;

public class Employee {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_employee.id
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_employee.name
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_employee.code
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_employee.age
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_employee.gender
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    private Integer gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_employee.dept_code
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    private String deptCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_employee.id
     *
     * @return the value of t_employee.id
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_employee.id
     *
     * @param id the value for t_employee.id
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_employee.name
     *
     * @return the value of t_employee.name
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_employee.name
     *
     * @param name the value for t_employee.name
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_employee.code
     *
     * @return the value of t_employee.code
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_employee.code
     *
     * @param code the value for t_employee.code
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_employee.age
     *
     * @return the value of t_employee.age
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_employee.age
     *
     * @param age the value for t_employee.age
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_employee.gender
     *
     * @return the value of t_employee.gender
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_employee.gender
     *
     * @param gender the value for t_employee.gender
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_employee.dept_code
     *
     * @return the value of t_employee.dept_code
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_employee.dept_code
     *
     * @param deptCode the value for t_employee.dept_code
     *
     * @mbg.generated Sat Jun 15 21:58:00 CST 2019
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

}