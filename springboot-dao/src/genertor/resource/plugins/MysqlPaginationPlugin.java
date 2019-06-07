package plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import genertor.VelocityMain;


public class MysqlPaginationPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		// 声明selectpagelist分页方法
		Method method = new Method("selectPageList");
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Map"));

		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
		Parameter parameter = new Parameter(new FullyQualifiedJavaType(
				"java.util.Map"), "map");
		method.addParameter(parameter);
		method.setReturnType(new FullyQualifiedJavaType("java.util.List<"
				+ introspectedTable.getFullyQualifiedTable()
						.getDomainObjectName() + ">"));

		// 声明selectlist方法
		Method listMethod = new Method("selectList");
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Map"));
		Parameter listParameter = new Parameter(new FullyQualifiedJavaType(
				"java.util.Map"), "map");
		listMethod.addParameter(listParameter);
		listMethod.setReturnType(new FullyQualifiedJavaType("java.util.List<"
				+ introspectedTable.getFullyQualifiedTable()
						.getDomainObjectName() + ">"));

		// selectcount
		Method countMethod = new Method("selectCount");
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Map"));

		Parameter countParameter = new Parameter(new FullyQualifiedJavaType(
				"java.util.Map"), "map");
		countMethod.addParameter(countParameter);
		countMethod.setReturnType(new FullyQualifiedJavaType("int"));

		// 声明selectOne方法
		Method oneMethod = new Method("selectOne");
		Parameter oneParameter = new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "t");
		oneMethod.addParameter(oneParameter);
		oneMethod.setReturnType(new FullyQualifiedJavaType(introspectedTable.getFullyQualifiedTable()
						.getDomainObjectName()));

		interfaze.addMethod(method);
		interfaze.addMethod(listMethod);
		interfaze.addMethod(countMethod);
		interfaze.addMethod(oneMethod);

		return super.clientGenerated(interfaze, topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {
		XmlElement parentElement = document.getRootElement();

		// 添加元素
		parentElement.addElement(getSelectListElement(introspectedTable));
		parentElement.addElement(getSelectPageListElement(introspectedTable));
		parentElement.addElement(getSelectCountElement(introspectedTable));
		parentElement.addElement(getSelectOneElement(introspectedTable));
		final String domainObjectName = introspectedTable
				.getFullyQualifiedTable().getDomainObjectName();

		String parameterType;
		if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			parameterType = introspectedTable.getPrimaryKeyType();
		} else {
			// PK fields are in the base class. If more than on PK
			// field, then they are coming in a map.
			if (introspectedTable.getPrimaryKeyColumns().size() > 1) {
				parameterType = "map"; //$NON-NLS-1$
			} else {
				parameterType = introspectedTable.getPrimaryKeyColumns().get(0)
						.getFullyQualifiedJavaType().toString();
			}
		}

		final String PK = parameterType;
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new VelocityMain().genertor(domainObjectName, PK);

			}
		}).start();

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	private Element getSelectListElement(IntrospectedTable introspectedTable) {
		// 产生selectList语句
		XmlElement selectListElement = new XmlElement("select");
		selectListElement.addAttribute(new Attribute("id", "selectList"));
		selectListElement.addAttribute(new Attribute("resultMap",
				"BaseResultMap"));
		// paginationPrefixElement.addAttribute(new Attribute("parameterType",
		// introspectedTable.getBaseRecordType()));
		// selectListElement.addAttribute(new Attribute("parameterType", this
		// .getContext().getJavaModelGeneratorConfiguration()
		// .getTargetPackage()
		// + "."
		// + introspectedTable.getFullyQualifiedTable()
		// .getDomainObjectName()));

		selectListElement.addAttribute(new Attribute("parameterType",
				"java.util.Map"));

		// <include refid="Base_Column_List" />

		TextElement basePrefixSelect = new TextElement("select ");
		XmlElement Base_Column_List = new XmlElement("include");
		Base_Column_List
				.addAttribute(new Attribute("refid", "Base_Column_List"));

		TextElement basePrefixfrom = new TextElement(" from ");

		selectListElement.addElement(basePrefixSelect);
		selectListElement.addElement(Base_Column_List);
		selectListElement.addElement(basePrefixfrom);

		selectListElement.addElement(getQualifiedTableName(introspectedTable));

		selectListElement
				.addElement(getListIntrospectedColumn(introspectedTable));

		XmlElement order = new XmlElement("if");
		order.addAttribute(new Attribute("test", "order != null"));
		order.addElement(new TextElement("<![CDATA[  order by  ${order} ]]>"));
		selectListElement.addElement(order);
		return selectListElement;
	}

	private Element getSelectPageListElement(IntrospectedTable introspectedTable) {
		XmlElement selectPageListElement = new XmlElement("select");

		selectPageListElement
				.addAttribute(new Attribute("id", "selectPageList"));
		selectPageListElement.addAttribute(new Attribute("resultMap",
				"BaseResultMap"));
		selectPageListElement.addAttribute(new Attribute("parameterType",
				"java.util.Map"));

		selectPageListElement.addElement(new TextElement("select "));
		XmlElement Base_Column_List = new XmlElement("include");
		Base_Column_List
				.addAttribute(new Attribute("refid", "Base_Column_List"));
		selectPageListElement.addElement(Base_Column_List);

		selectPageListElement.addElement(new TextElement(" from "));
		selectPageListElement
				.addElement(getQualifiedTableName(introspectedTable));

		selectPageListElement
				.addElement(getListIntrospectedColumn(introspectedTable));

		XmlElement pageListSuffixEnd = new XmlElement("if");
		pageListSuffixEnd.addAttribute(new Attribute("test", "page != null"));
		pageListSuffixEnd.addElement(new TextElement(
				"<![CDATA[  limit #{page.begin}, #{page.length} ]]>"));

		XmlElement order = new XmlElement("if");
		order.addAttribute(new Attribute("test", "order != null"));
		order.addElement(new TextElement("<![CDATA[  order by  ${order} ]]>"));

		selectPageListElement.addElement(order);
		selectPageListElement.addElement(pageListSuffixEnd);

		return selectPageListElement;
	}

	private Element getSelectCountElement(IntrospectedTable introspectedTable) {
		// 产生selectcount语句
		XmlElement selectCountElement = new XmlElement("select");
		selectCountElement.addElement(new TextElement(" select"));
		selectCountElement.addAttribute(new Attribute("id", "selectCount"));

		selectCountElement.addAttribute(new Attribute("resultType", "int"));
		selectCountElement.addAttribute(new Attribute("parameterType",
				"java.util.Map"));
		selectCountElement.addElement(new TextElement(" count(1)"));
		selectCountElement.addElement(new TextElement(" from "));
		selectCountElement.addElement(getQualifiedTableName(introspectedTable));

		selectCountElement
				.addElement(getListIntrospectedColumn(introspectedTable));
		return selectCountElement;
	}

	private XmlElement getSelectOneElement(IntrospectedTable introspectedTable) {
		// 产生selectList语句
		XmlElement selectOneElement = new XmlElement("select");
		selectOneElement.addAttribute(new Attribute("id", "selectOne"));
		selectOneElement.addAttribute(new Attribute("resultMap",
				"BaseResultMap"));

		selectOneElement.addAttribute(new Attribute("parameterType",
				introspectedTable.getBaseRecordType()));

		// <include refid="Base_Column_List" />

		TextElement basePrefixSelect = new TextElement("select ");
		XmlElement Base_Column_List = new XmlElement("include");
		Base_Column_List
				.addAttribute(new Attribute("refid", "Base_Column_List"));

		TextElement basePrefixfrom = new TextElement(" from ");

		selectOneElement.addElement(basePrefixSelect);
		selectOneElement.addElement(Base_Column_List);
		selectOneElement.addElement(basePrefixfrom);

		selectOneElement.addElement(getQualifiedTableName(introspectedTable));

		selectOneElement
				.addElement(getListIntrospectedColumn(introspectedTable));
		return selectOneElement;
	}

	private TextElement getQualifiedTableName(
			IntrospectedTable introspectedTable) {
		TextElement qualifiedTableName = new TextElement(" "
//				+ introspectedTable.getTableConfiguration().getSchema() + "."
				+ introspectedTable.getTableConfiguration().getTableName());
		return qualifiedTableName;
	}

	public XmlElement getListIntrospectedColumn(
			IntrospectedTable introspectedTable) {
		XmlElement whereFilterElement = new XmlElement("where");
		List<IntrospectedColumn> columnsList = introspectedTable
				.getAllColumns();
		for (IntrospectedColumn column : columnsList) {
			
			XmlElement columsFilterElement = new XmlElement("if");
			columsFilterElement.addAttribute(new Attribute("test", column
					.getJavaProperty() + " != null"));
			// #{businessid,jdbcType=VARCHAR},
			columsFilterElement.addElement(new TextElement(" and "
					+ column.getActualColumnName() + "= #{"
					+ column.getJavaProperty() + ",jdbcType="
					+ column.getJdbcTypeName() + "}"));
			// ,jdbcType="+ column.getJdbcTypeName() +
			whereFilterElement.addElement(columsFilterElement);
		}
		return whereFilterElement;

	}

}
