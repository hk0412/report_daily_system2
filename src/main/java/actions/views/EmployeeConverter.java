package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Employee;

/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class EmployeeConverter {


    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param ev EmployeeViewのインスタンス
     * @return Employeeのインスタンス
     */
    public static Employee toModel(EmployeeView ev) {

        Integer adm_flg = null;
        if(ev.getAdminFlag() == AttributeConst.ROLE_ADMIN.getIntegerValue()) {
            adm_flg = JpaConst.ROLE_ADMIN;
        }else if (ev.getAdminFlag() == AttributeConst.ROLE_DIRECTOR.getIntegerValue()){
            adm_flg = JpaConst.ROLE_DIRECTOR;
        }else if (ev.getAdminFlag() == AttributeConst.ROLE_GENERAL.getIntegerValue()){
            adm_flg = JpaConst.ROLE_GENERAL;
        }

        return new Employee(
                ev.getId(),
                ev.getCode(),
                ev.getName(),
                ev.getPassword(),
                adm_flg,
                ev.getCreatedAt(),
                ev.getUpdatedAt(),
                ev.getDeleteFlag() == null
                        ? null
                        : ev.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ? JpaConst.EMP_DEL_TRUE
                                : JpaConst.EMP_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Employeeのインスタンス
     * @return EmployeeViewのインスタンス
     */
    public static EmployeeView toView(Employee e) {

        if(e == null) {
            return null;
        }

        Integer adm_flg = null;
        if (e.getAdminFlag() == JpaConst.ROLE_ADMIN) {
            adm_flg = AttributeConst.ROLE_ADMIN.getIntegerValue();
        }else if (e.getAdminFlag() == JpaConst.ROLE_DIRECTOR) {
            adm_flg = AttributeConst.ROLE_DIRECTOR.getIntegerValue();
        }else if (e.getAdminFlag() == JpaConst.ROLE_GENERAL) {
            adm_flg = AttributeConst.ROLE_GENERAL.getIntegerValue();
        }

        return new EmployeeView(
                e.getId(),
                e.getCode(),
                e.getName(),
                e.getPassword(),
                adm_flg,
                e.getCreatedAt(),
                e.getUpdatedAt(),
                e.getDeleteFlag() == null
                        ? null
                        : e.getDeleteFlag() == JpaConst.EMP_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<EmployeeView> toViewList(List<Employee> list) {
        List<EmployeeView> evs = new ArrayList<>();

        for (Employee e : list) {
            evs.add(toView(e));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(Employee e, EmployeeView ev) {
        e.setId(ev.getId());
        e.setCode(ev.getCode());
        e.setName(ev.getName());
        e.setPassword(ev.getPassword());
        e.setAdminFlag(ev.getAdminFlag());
        e.setCreatedAt(ev.getCreatedAt());
        e.setUpdatedAt(ev.getUpdatedAt());
        e.setDeleteFlag(ev.getDeleteFlag());

    }

}