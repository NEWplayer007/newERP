package com.moka.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.StatementType;

import com.moka.model.ChCompany;

@Mapper
public interface ChCompanyData {
	
	/**
	 * 插入操作
	 * @param entity
	 * @return
	 */
	@InsertProvider(type = ChCompanyProvider.class, method = "insertChCompany")
	@SelectKey(before=false,keyProperty="id",resultType=Integer.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
	public int insertChCompany(ChCompany entity);
	/**
	 * 按条件查询总记录数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ChCompanyProvider.class, method = "selectChCompanyByCount")
	public int selectChCompanyByCount(ChCompany entity);
	/**
	 * 按条件分页查询
	 * @param entity
	 * @param orderBy
	 * @param limit
	 * @param limitLen
	 * @return
	 */
	@SelectProvider(type = ChCompanyProvider.class, method = "selectChCompanyByLimt")
	public List<ChCompany> selectChCompanyByLimt(ChCompany entity);
	/**
	 * 根据主键id查询实体
	 * @param id
	 * @return
	 */
	@SelectProvider(type = ChCompanyProvider.class, method = "selectOne")
	public ChCompany selectOne(@Param("id")int id);
	/**
	 * 更新实体，过滤空值
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type = ChCompanyProvider.class, method = "updateChCompanyByNullChk")
	public int updateChCompanyByNullChk(ChCompany entity);
	/**
	 * 逻辑删除实体
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type = ChCompanyProvider.class, method = "deleteByLogic")
	public int deleteByLogic(@Param("id") int id);
	
	/**
	 * 按条件查询记录
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ChCompanyProvider.class, method = "selectChCompanyAll")
	public List<ChCompany> selectChCompanyAll();
	/**
	 * 根据公司id查询公司名称
	 * @param id
	 * @return
	 */
	@SelectProvider(type = ChCompanyProvider.class,method = "findNameById")
 	public String findNameById(Integer id);
}








