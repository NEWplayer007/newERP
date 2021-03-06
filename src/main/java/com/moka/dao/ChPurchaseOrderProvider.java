package com.moka.dao;

import java.util.Objects;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.google.common.base.Strings;
import com.moka.model.ChPurchaseItem;
import com.moka.model.ChPurchaseOrder;
import com.moka.req.ChPurchaseAllReq;
import com.moka.req.ChPurchaseItemReq;
import com.moka.req.ChPurchaseSupplyReq;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * provider
 */
@Slf4j
public class ChPurchaseOrderProvider {
	
	/**
	 * 插入操作
	 * @param entity
	 * @return
	 */
	public String insertChPurchaseOrder(ChPurchaseOrder entity) {
		SQL sql = new SQL().INSERT_INTO("ch_purchase_order");
		sql.VALUES("company_id,supply_id,createtime,depot_id,id,memo,picture,predict_time,price,pur_bills_date,pur_bills_id,pur_bills_type,pur_order_type,reality_time,state,updatetime,user_id", "#{companyId},#{supplyId},now(),#{depotId},#{id},#{memo},#{picture},#{predictTime},#{price},#{purBillsDate},#{purBillsId},#{purBillsType},#{purOrderType},#{realityTime},#{state},now(),#{userId}");
		return sql.toString();
	}
	/**
	 * 按条件查询总记录数
	 * @param entity
	 * @return
	 */
	public String selectChPurchaseOrderByCount(ChPurchaseOrder entity) {
		SQL sql = new SQL().SELECT("count(*)").FROM("ch_purchase_order");
					if(!Objects.isNull(entity.getApproverId())) {sql.WHERE("approver_id = #{approverId}");}
			if(!Objects.isNull(entity.getCompanyId())) {sql.WHERE("company_id = #{companyId}");}
			if(!Strings.isNullOrEmpty(entity.getCreatetime())) {sql.WHERE("createtime = #{createtime}");}
			if(!Objects.isNull(entity.getDepotId())) {sql.WHERE("depot_id = #{depotId}");}
			if(!Objects.isNull(entity.getId())) {sql.WHERE("id = #{id}");}
			if(!Strings.isNullOrEmpty(entity.getMemo())) {sql.WHERE("memo = #{memo}");}
			if(!Strings.isNullOrEmpty(entity.getPicture())) {sql.WHERE("picture = #{picture}");}
			if(!Strings.isNullOrEmpty(entity.getPredictTime())) {sql.WHERE("predict_time = #{predictTime}");}
			if(!Objects.isNull(entity.getPrice())) {sql.WHERE("price = #{price}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsDate())) {sql.WHERE("pur_bills_date = #{purBillsDate}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsId())) {sql.WHERE("pur_bills_id = #{purBillsId}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsType())) {sql.WHERE("pur_bills_type = #{purBillsType}");}
			if(!Strings.isNullOrEmpty(entity.getPurOrderType())) {sql.WHERE("pur_order_type = #{purOrderType}");}
			if(!Strings.isNullOrEmpty(entity.getRealityTime())) {sql.WHERE("reality_time = #{realityTime}");}
			if(!Strings.isNullOrEmpty(entity.getState())) {sql.WHERE("state = #{state}");}
			if(!Strings.isNullOrEmpty(entity.getUpdatetime())) {sql.WHERE("updatetime = #{updatetime}");}
			if(!Objects.isNull(entity.getUserId())) {sql.WHERE("user_id = #{userId}");}
			sql.WHERE(" state='1'");
		return sql.toString();
	}
	/**
	 * 按条件分页查询
	 * @param entity
	 * @param orderBy
	 * @param limit
	 * @param limitLen
	 * @return
	 */
	public String selectChPurchaseOrderByLimt(ChPurchaseOrder entity) {
		SQL sql = new SQL().SELECT("a.id,a.pur_bills_id AS purBillsId,a.pur_order_type AS purOrderType,"
				+ "a.predict_time AS predictTime,a.reality_time AS realityTime,a.company_id AS companyId,"
				+ "a.supply_id AS supplyId,b.supply_name AS supplyName,a.memo,a.picture,a.user_id AS userId,"
				+ "a.approver_id AS approverId,a.price,a.pur_bills_type as purBillsType,a.pur_bills_date as purBillsDate,"
				+ "a.depot_id as depotId,b.supply_address as  supplyAddress,b.supply_contact AS supplyContact,"
				+ "b.supply_account AS supplyAccount,b.supply_account_name AS supplyAccountName,c.depot_name AS depotName,d.name as name ")
				.FROM("ch_purchase_order a")
				.INNER_JOIN(" ch_supply b ON (a.supply_id=b.id)")
				.INNER_JOIN(" ch_depot c ON (a.depot_id=c.id)")
				.INNER_JOIN(" sys_user d ON (a.user_id=d.id)");
			if(!Objects.isNull(entity.getCompanyId())) {sql.WHERE("a.company_id = #{companyId}");}
			if(!Objects.isNull(entity.getSupplyId())) {sql.WHERE("a.supply_id = #{supplyId}");}
			if(!Objects.isNull(entity.getId())) {sql.WHERE("a.id = #{id}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsDate())) {sql.WHERE("a.pur_bills_date like CONCAT ('%',#{purBillsDate},'%') ");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsId())) {sql.WHERE("a.pur_bills_id = #{purBillsId}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsType())) {sql.WHERE("a.pur_bills_type = #{purBillsType}");}
			if(!Strings.isNullOrEmpty(entity.getPurOrderType())) {sql.WHERE("a.pur_order_type = #{purOrderType}");}
			if(!Strings.isNullOrEmpty(entity.getRealityTime())) {sql.WHERE("a.reality_time = #{realityTime}");}
			if(!Strings.isNullOrEmpty(entity.getState())) {sql.WHERE("a.state = #{state}");}
			if(!Objects.isNull(entity.getUserId())) {sql.WHERE("a.user_id = #{userId}");}
			sql.WHERE(" a.state='1'");
			sql.WHERE(" b.state='1'");
			System.out.println(sql);
		return sql.toString() + " order by " + entity.getOrderBy() + " desc limit " + entity.getLimit() + "," + entity.getLimitLen();
	}
	/**
	 * 按条件查询记录
	 * @param entity
	 * @return
	 */
	public String selectChPurchaseOrder(ChPurchaseOrder entity) {
		SQL sql = new SQL().SELECT(" id,pur_bills_id AS purBillsId,pur_order_type AS purOrderType,predict_time AS predictTime,"
				+ "reality_time AS realityTime,company_id AS companyId,supply_id AS supplyId,memo,"
				+ "picture,user_id AS userId,approver_id AS approverId,state").FROM("ch_purchase_order");
					if(!Objects.isNull(entity.getApproverId())) {sql.WHERE("approver_id = #{approverId}");}
			if(!Objects.isNull(entity.getCompanyId())) {sql.WHERE("company_id = #{companyId}");}
			if(!Strings.isNullOrEmpty(entity.getCreatetime())) {sql.WHERE("createtime = #{createtime}");}
			if(!Objects.isNull(entity.getDepotId())) {sql.WHERE("depot_id = #{depotId}");}
			if(!Objects.isNull(entity.getId())) {sql.WHERE("id = #{id}");}
			if(!Strings.isNullOrEmpty(entity.getMemo())) {sql.WHERE("memo = #{memo}");}
			if(!Strings.isNullOrEmpty(entity.getPicture())) {sql.WHERE("picture = #{picture}");}
			if(!Strings.isNullOrEmpty(entity.getPredictTime())) {sql.WHERE("predict_time = #{predictTime}");}
			if(!Objects.isNull(entity.getPrice())) {sql.WHERE("price = #{price}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsDate())) {sql.WHERE("pur_bills_date = #{purBillsDate}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsId())) {sql.WHERE("pur_bills_id = #{purBillsId}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsType())) {sql.WHERE("pur_bills_type = #{purBillsType}");}
			if(!Strings.isNullOrEmpty(entity.getPurOrderType())) {sql.WHERE("pur_order_type = #{purOrderType}");}
			if(!Strings.isNullOrEmpty(entity.getRealityTime())) {sql.WHERE("reality_time = #{realityTime}");}
			if(!Strings.isNullOrEmpty(entity.getState())) {sql.WHERE("state = #{state}");}
			if(!Strings.isNullOrEmpty(entity.getUpdatetime())) {sql.WHERE("updatetime = #{updatetime}");}
			if(!Objects.isNull(entity.getUserId())) {sql.WHERE("user_id = #{userId}");}
			sql.WHERE(" state='1'");
		return sql.toString();
	}
	/**
	 * 根据主键id查询实体
	 * @param id
	 * @return
	 */
	public String selectOne(@Param("id")int id) {
		SQL sql = new SQL().SELECT("*").FROM("ch_purchase_order");
		sql.WHERE("id=#{id}");
		return sql.toString();
	}
	/**
	 * 更新实体
	 * @param entity
	 * @return
	 */
	public String updateChPurchaseOrder(ChPurchaseOrder entity) {
		SQL sql = new SQL().UPDATE("ch_purchase_order");
				sql.SET("approver_id = #{approverId}");
		sql.SET("company_id = #{companyId}");
		sql.SET("depot_id = #{depotId}");
		sql.SET("memo = #{memo}");
		sql.SET("picture = #{picture}");
		sql.SET("predict_time = #{predictTime}");
		sql.SET("price = #{price}");
		sql.SET("pur_bills_date = #{purBillsDate}");
		sql.SET("pur_bills_id = #{purBillsId}");
		sql.SET("pur_bills_type = #{purBillsType}");
		sql.SET("pur_order_type = #{purOrderType}");
		sql.SET("reality_time = #{realityTime}");
		sql.SET("state = #{state}");
		sql.SET("user_id = #{userId}");
		sql.SET("updatetime = now()");

		sql.WHERE("id = #{id}");
		return sql.toString();
	}
	/**
	 * 更新实体，过滤空值
	 * @param entity
	 * @return
	 */
	public String updateChPurchaseOrderByNullChk(ChPurchaseOrder entity) {
		SQL sql = new SQL().UPDATE("ch_purchase_order");
					if(!Objects.isNull(entity.getApproverId())) {sql.SET("approver_id = #{approverId}");}
			if(!Objects.isNull(entity.getCompanyId())) {sql.SET("company_id = #{companyId}");}
			if(!Objects.isNull(entity.getDepotId())) {sql.SET("depot_id = #{depotId}");}
			if(!Strings.isNullOrEmpty(entity.getMemo())) {sql.SET("memo = #{memo}");}
			if(!Strings.isNullOrEmpty(entity.getPicture())) {sql.SET("picture = #{picture}");}
			if(!Strings.isNullOrEmpty(entity.getPredictTime())) {sql.SET("predict_time = #{predictTime}");}
			if(!Objects.isNull(entity.getPrice())) {sql.SET("price = #{price}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsDate())) {sql.SET("pur_bills_date = #{purBillsDate}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsId())) {sql.SET("pur_bills_id = #{purBillsId}");}
			if(!Strings.isNullOrEmpty(entity.getPurBillsType())) {sql.SET("pur_bills_type = #{purBillsType}");}
			if(!Strings.isNullOrEmpty(entity.getPurOrderType())) {sql.SET("pur_order_type = #{purOrderType}");}
			if(!Strings.isNullOrEmpty(entity.getRealityTime())) {sql.SET("reality_time = #{realityTime}");}
			if(!Strings.isNullOrEmpty(entity.getState())) {sql.SET("state = #{state}");}
			if(!Objects.isNull(entity.getUserId())) {sql.SET("user_id = #{userId}");}
		sql.SET("updatetime = now()");

		sql.WHERE("id = #{id}");
		return sql.toString();
	}
	/**
	 * 逻辑删除实体
	 * @param entity
	 * @return
	 */
	public String deleteByLogic(@Param("id")int id) {
		SQL sql = new SQL().UPDATE("ch_purchase_order");
		sql.SET("state=2");
		sql.WHERE("id = #{id}");
		return sql.toString();
	}
	/**
	 * 采购订单查询列表
	 * @return
	 */
	public String  selectChPurchaseAll(ChPurchaseAllReq req){
		SQL sql = new SQL().SELECT("a.id as productId,"
				+ "a.product_code AS productCode,"
				+ "a.product_name AS productName,"
				+ "a.product_size AS productSize,"
				+ "a.product_type AS productType,"
				+ "a.product_unit AS productUnit,"
				+ "a.product_weight AS productWeight,"
				+ "b.id AS productItemId,"
				+ "b.brand_code AS brandCode,"
				+ "b.supply_id AS supplyId,"
				+ "b.purchase_price AS purchasePrice,"
				+ "b.supply_product_no AS supplyProductNo,"
				+ "e.id AS companyId,"
				+ "c.supply_code AS supplyCode,"
				+ "c.supply_name AS supplyName,"
				+ "e.company_name AS companyName").
				FROM("ch_product a").
				INNER_JOIN(" ch_product_item b ON ( a.id = b.product_id )").
				INNER_JOIN(" ch_supply c ON ( b.supply_id = c.id )").
				INNER_JOIN(" ch_brand d ON ( d.brand_name= a.brand_code )").
				INNER_JOIN(" ch_company e ON ( d.company_id = e.id )");
		sql.WHERE(" a.state='1'");
		sql.WHERE(" b.state='1'");
		if(!Objects.isNull(req.getCompanyId())) {sql.WHERE("e.id = #{companyId}");}
		if(!Objects.isNull(req.getSupplyId())) {sql.WHERE("c.id = #{supplyId}");}
		if(!Objects.isNull(req.getProductId())){sql.WHERE("a.id = #{productId}");}
		if(!Objects.isNull(req.getProductItemId())){sql.WHERE("b.id = #{productItemId}");}
		//log.info("查询商品的sql语句"+sql);
		return sql.toString();
	}
	
	/**
	 * 根据采购公司的品牌编码查询供应商信息
	 * @return
	 */
	public String  findSupplyByCompany(ChPurchaseSupplyReq req){
		SQL sql=new SQL();
		sql.SELECT(" a.supply_name AS supplyName,a.id AS supplyId,a.supply_code AS supplyCode ").
		FROM("ch_supply a").
		INNER_JOIN("ch_brand b ON (a.accredit_brand=b.brand_name)");
		sql.WHERE(" a.state='1' AND b.state='1'");
		if(!Objects.isNull(req.getCompanyId())) {sql.WHERE("b.company_id = #{companyId}");}
		log.info("根据公司查供应商接口"+req);
		return sql.toString();
	}
	
	/**
	 * 详情插入操作
	 * @param entity
	 * @return
	 */
	public String insertChPurchaseOrderItem(ChPurchaseItem chPurchaseItem) {
		SQL sql = new SQL().INSERT_INTO("ch_purchase_order_item");
		sql.VALUES(" pur_bills_id,product_item_id,product_name,product_size,purchase_price,product_price,pur_number,money,else_price,memo,product_weight,product_unit"
				, "#{purBillsId},#{productItemId},#{productName},#{productSize},#{purchasePrice},#{productPrice},#{purNumber},#{money},#{elsePrice},#{memo},#{productWeight},#{productUnit}");
		return sql.toString();
	}
	/**
	 * 采购订单详情查询
	 * @param req
	 * @return
	 */
	public String listItem(ChPurchaseItemReq req){
		SQL sql = new SQL().SELECT(" a.id,a.pur_bills_id AS purBillsId, a.product_item_id AS productItemId,"
				+ "a.product_name AS productName,a.product_size	AS productSize,a.product_price AS productPrice,"
				+ "a.pur_number AS purNumber,a.money,a.else_price AS elsePrice,a.memo,a.purchase_price as purchasePrice,"
				+ "a.product_weight as productWeight,a.product_unit as productUnit").FROM(" ch_purchase_order_item a");
		if(!Strings.isNullOrEmpty(req.getPurBillsId())) {sql.WHERE(" a.pur_bills_id = #{purBillsId}");}
		return sql.toString();
	}
}





