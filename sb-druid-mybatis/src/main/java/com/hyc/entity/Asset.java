package com.hyc.entity;

public class Asset {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_asset.id
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_asset.name
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_asset.code
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_asset.emp_code
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    private String empCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_asset.id
     *
     * @return the value of t_asset.id
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_asset.id
     *
     * @param id the value for t_asset.id
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_asset.name
     *
     * @return the value of t_asset.name
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_asset.name
     *
     * @param name the value for t_asset.name
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_asset.code
     *
     * @return the value of t_asset.code
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_asset.code
     *
     * @param code the value for t_asset.code
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_asset.emp_code
     *
     * @return the value of t_asset.emp_code
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    public String getEmpCode() {
        return empCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_asset.emp_code
     *
     * @param empCode the value for t_asset.emp_code
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }
}