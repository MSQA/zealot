package com.blinkfox.zealot.core;

import com.blinkfox.zealot.bean.BuildSource;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.config.AbstractZealotConfig;
import com.blinkfox.zealot.consts.ZealotConst;
import com.blinkfox.zealot.helpers.ParseHelper;
import com.blinkfox.zealot.helpers.StringHelper;
import java.util.List;
import org.dom4j.Node;

/**
 * Zealot的核心解析和生成调用类.
 * Created by blinkfox on 2016/10/30.
 */
public final class Zealot {

    /**
     * 私有构造方法.
     */
    private Zealot() {
        super();
    }

    /**
     * 获取sqlInfo信息.
     * @param nameSpace xml命名空间
     * @param zealotId xml中的zealot id
     * @param paramObj 参数对象
     * @return 返回SqlInfo对象
     */
    public static SqlInfo getSqlInfo(String nameSpace, String zealotId, Object paramObj) {
        // 获取nameSpace文档中的指定sql的zealotId的节点对应的Node节点
        Node zealotNode = AbstractZealotConfig.getZealots()
                .get(StringHelper.concat(nameSpace, ZealotConst.SP_AT, zealotId));
        return buildSqlInfo(zealotNode, paramObj);
    }

    /**
     * 构建完整的SqlInfo对象.
     * @param node dom4j对象节点
     * @param paramObj 参数对象
     * @return 返回SqlInfo对象
     */
    @SuppressWarnings("unchecked")
    private static SqlInfo buildSqlInfo(Node node, Object paramObj) {
        SqlInfo sqlInfo = SqlInfo.newInstance();

        // 获取所有子节点，并分别将其使用StringBuilder拼接起来
        List<Node> nodes = node.selectNodes(ZealotConst.ATTR_CHILD);
        for (Node n: nodes) {
            if (ZealotConst.NODETYPE_TEXT.equals(n.getNodeTypeName())) {
                // 如果子节点node 是文本节点，则直接获取其文本
                sqlInfo.getJoin().append(n.getText());
            } else if (ZealotConst.NODETYPE_ELEMENT.equals(n.getNodeTypeName())) {
                BuildSource source = new BuildSource(sqlInfo, n, paramObj);
                // 如果子节点node 是元素节点，则再判断其是什么元素，动态判断条件和参数
                sqlInfo = ConditContext.buildSqlInfo(source, n.getName());
            }
        }

        return buildFinalSql(sqlInfo, paramObj);
    }

    /**
     * 根据标签拼接的SQL信息来生成最终的SQL.
     * @param sqlInfo sql及参数信息
     * @param paramObj 参数对象信息
     * @return 返回SqlInfo对象
     */
    private static SqlInfo buildFinalSql(SqlInfo sqlInfo, Object paramObj) {
        // 得到生成的SQL，如果有MVEL的模板表达式，则执行计算出该表达式来生成最终的SQL
        String sql = sqlInfo.getJoin().toString();
        sql = ParseHelper.parseTemplate(sql, paramObj);
        return sqlInfo.setSql(StringHelper.replaceBlank(sql));
    }

}