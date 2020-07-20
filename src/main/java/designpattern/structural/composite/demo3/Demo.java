package designpattern.structural.composite.demo3;

import java.util.List;

/**
 * 组合模式的应用场景举例：
 *
 * 假设开发一个 OA 系统，公司的组织结构包含 [部门] 和 [员工] 两种数据类型。其中，[部门] 又可以包含 [子部门] 和 [员工]。
 * 部门表 (Department) 如下所示：
 * | 部门id  |    隶属上级部门id     | ... | ... | ... |
 * |   id   | parent_department_id | ... | ... | ... |
 *
 * 员工表 (Employee) 如下所示：
 * | 员工id  |  隶属上级部门id  | 员工薪资 | ... | ... |
 * |   id   |  department_id  | salary  | ... | ... |
 *
 * 希望在内存中构建整个公司的人员架构图（部门、子部门、员工的隶属关系），并且提供接口计算出部门的薪资成本（隶属于这个部门的所有员工的薪资和）。
 *
 * 部门包含子部门和员工，这是一种嵌套结构，可以表示成树这种数据结构。计算每个部门的薪资开支这样一个需求，也可以通过在树上的遍历算法来实现。
 * 所以，从这个角度来看，这个应用场景可以使用组合模式来设计和实现。
 *
 * 这个例子的代码结构跟上一个例子的很相似，代码实现见 demo3，可以对比 demo2 看一下。
 * 其中，HumanResource 是部门类（Department）和员工类（Employee）抽象出来的父类，为的是能统一薪资的处理逻辑。
 *
 * 组合模式的定义跟这个例子对照一下：
 * “将一组对象（员工和部门）组织成树形结构，以表示一种‘部分 - 整体’的层次结构（部门与子部门的嵌套结构）。
 * 组合模式让客户端可以统一单个对象（员工）和组合对象（部门）的处理逻辑（递归遍历）。”
 *
 */
public class Demo {

    private static final long ORGANIZATION_ROOT_ID = 1001;

    private DepartmentRepo departmentRepo;

    private EmployeeRepo employeeRepo;

    public void buildOrganization() {
        Department rootDepartment = new Department(ORGANIZATION_ROOT_ID);
        buildOrganization(rootDepartment);
    }

    public void buildOrganization(Department department) {
        List<Long> subDepartmentIds = departmentRepo.getSubDepartmentIds(department.getId());
        for (Long subDepartmentId : subDepartmentIds) {
            Department subDepartment = new Department(subDepartmentId);
            department.addSubNode(subDepartment);
            buildOrganization(subDepartment);
        }
        List<Long> employeeIds = employeeRepo.getDepartmentEmployeeIds(department.getId());
        for (Long employeeId : employeeIds) {
            double salary = employeeRepo.getEmployeeSalary(employeeId);
            department.addSubNode(new Employee(employeeId, salary));
        }
    }


}
