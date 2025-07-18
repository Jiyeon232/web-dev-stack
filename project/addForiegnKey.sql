-- hrm 외래키 생성
ALTER TABLE employee_info ADD
    FOREIGN KEY (job_no) REFERENCES job_position(job_no); -- 직급 번호 외래 키
ALTER TABLE employee_info ADD
    FOREIGN KEY (dept_no) REFERENCES department(dept_no); -- 부서 번호 외래 키
ALTER TABLE attendance_log ADD
    FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no); -- 직원 번호 외래 키 
ALTER TABLE leave_request ADD
    FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no); -- 직원 번호 외래 키
ALTER TABLE leave_remain ADD
    FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no); -- 직원 번호 외래 키 
ALTER TABLE performance_review ADD
    FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no); -- 평가 대상 직원 번호 외래 키
ALTER TABLE performance_review ADD
    FOREIGN KEY (evaluator_emp_no) REFERENCES employee_info(emp_no); -- 평가자 직원 번호 외래 키 
ALTER TABLE salary_history ADD
    FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no); -- 직원 번호 외래 키
ALTER TABLE bonus_payment ADD
    FOREIGN KEY (bonus_no) REFERENCES bonus(bonus_no); -- 수당 번호 외래 키 
ALTER TABLE bonus_payment ADD
    FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no); -- 직원 번호 외래 키 
ALTER TABLE quitter ADD
    FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no); -- 직원 번호 외래 키
    
-- qam 외래키 생성
ALTER TABLE product ADD
FOREIGN KEY (product_code) REFERENCES product_name(product_code);
ALTER TABLE qc ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE qc ADD
FOREIGN KEY (product_no) REFERENCES product(product_no);
ALTER TABLE sale ADD
FOREIGN KEY (product_no) REFERENCES product(product_no);
ALTER TABLE defective ADD
FOREIGN KEY (product_no) REFERENCES product(product_no);

-- fm 외래키 생성
-- salary → employee_info(emp_no)
ALTER TABLE salary
ADD FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
-- salary -> bonus_payment(bonus_payment_no)
ALTER TABLE salary
ADD FOREIGN KEY (bonus_payment_no) REFERENCES bonus_payment(bonus_payment_no);
-- budget → department(dept_no)
ALTER TABLE budget
ADD FOREIGN KEY (dept_no) REFERENCES department(dept_no);
-- transaction → employee_info(emp_no) --> 직웝 번호 외래키 안걸기로!
-- ALTER TABLE transaction
-- ADD FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
-- transaction → department(dept_no)
ALTER TABLE transaction
ADD FOREIGN KEY (dept_no) REFERENCES department(dept_no);
-- purchase → product_name(product_code)
ALTER TABLE purchase
ADD FOREIGN KEY (product_code) REFERENCES product_name(product_code);
ALTER TABLE sale_manage
ADD FOREIGN KEY (product_code) REFERENCES product_name(product_code);