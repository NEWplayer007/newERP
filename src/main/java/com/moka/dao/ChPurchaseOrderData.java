package com.moka.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.StatementType;

import com.moka.dto.ChProductListDto;
import com.moka.dto.ChPurchaseDto;
import com.moka.dto.ChPurchaseSupplyDto;
import com.moka.model.ChPurchaseItem;
import com.moka.model.ChPurchaseOrder;
import com.moka.req.ChPurchaseAllReq;
import com.moka.req.ChPurchaseItemReq;
import com.moka.req.ChPurchaseSupplyReq;

@Mapper
public interface ChPurchaseOrderData {
	
	/**
	 * 插入操作
	 * @param entity
	 * @return
	 */
	@InsertProvider(type = ChPurchaseOrderProvider.class, method = "insertChPurchaseOrder")
	@SelectKey(before=false,keyProperty="id",resultType=Integer.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
	public int insertChPurchaseOrder(ChPurchaseOrder entity);
	/**
	 * 按条件查询总记录数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ChPurchaseOrderProvider.class, method = "selectChPurchaseOrderByCount")
	public int selectChPurchaseOrderByCount(ChPurchaseOrder entity);
	/**
	 * 按条件分页查询
	 * @param entity
	 * @param orderBy
	 * @param limit
	 * @param limitLen
	 * @return
	 */
	@SelectProvider(type = ChPurchaseOrderProvider.class, method = "selectChPurchaseOrderByLimt")
	public List<ChProductListDto> selectChPurchaseOrderByLimt(ChPurchaseOrder entity);
	/**
	 * 按条件查询记录
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ChPurchaseOrderProvider.class, method = "selectChPurchaseOrder")
	public List<ChProductListDto> selectChPurchaseOrder(ChPurchaseOrder entity);
	/**
	 * 根据主键id查询实体
	 * @param id
	 * @return
	 */
	@SelectProvider(type = ChPurchaseOrderProvider.class, method = "selectOne")
	public ChPurchaseOrder selectOne(@Param("id")int id);
	/**
	 * 更新实体
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type = ChPurchaseOrderProvider.class, method = "updateChPurchaseOrder")
	public int updateChPurchaseOrder(ChPurchaseOrder entity);
	/**
	 * 更新实体，过滤空值
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type = ChPurchaseOrderProvider.class, method = "updateChPurchaseOrderByNullChk")
	public int updateChPurchaseOrderByNullChk(ChPurchaseOrder entity);
	/**
	 * 逻辑删除实体
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type = ChPurchaseOrderProvider.class, method = "deleteByLogic")
	public int deleteByLogic(@Param("id")int id);
	
	
	/**
	 * 查询条件下的所有商品    lbq
	 * @param entity
	 * @param orderBy
	 * @param limit
	 * @param limitLen
	 * @return
	 */
	@SelectProvider(type = ChPurchaseOrderProvider.class, method = "selectChPurchaseAll")
	public List<ChPurchaseDto> selectChPurchaseAll(ChPurchaseAllReq req);
	
	/**
	 * 根据采购公司的品牌编码查询供应商信息 lbq
	 * @param req
	 * @return
	 */
	@SelectProvider(type = ChPurchaseOrderProvider.class, method = "findSupplyByCompany")
	public List<ChPurchaseSupplyDto> findSupplyByCompany(ChPurchaseSupplyReq req);
	/**
	 * 采购详情插入
	 * @param entity
	 * @return
	 */
	@InsertProvider(type = ChPurchaseOrderProvider.class, method = "insertChPurchaseOrderItem")
	@SelectKey(before=false,keyProperty="id",resultType=Integer.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
	public int insertChPurchaseOrderItem(ChPurchaseItem entity);
	
	
	
	/**
	 * 采购详情查看
	 * @param req
	 * @return
	 */
	@SelectProvider(type = ChPurchaseOrderProvider.class, method = "listItem")
	public List<ChPurchaseItem> listItem(ChPurchaseItemReq req);
	
	
	
	
}
