<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form method="get" action="${pageContext.request.contextPath }/jsp/user.do">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>用户名：</span>
					 <input name="queryName" class="input-text"	type="text" value="${queryUserName}">

					<span>用户角色：</span>
					 <select name="queryUserRole">
						 <c:if test="${roleList!=null}">
							 <option value="0">--请选择--</option>
							 <c:forEach items="${roleList}" var="role">
								 <option <c:if test="${role.id}==${queryRoleName}">selected="selected"</c:if>
										 value="${role.id}"> ${role.roleName}</option>
							 </c:forEach>
						 </c:if>
	        		</select>

					<input type="hidden" name="pageIndex" value="1"/>
					<input	value="查 询" type="submit" id="searchbutton">
					<a href="${pageContext.request.contextPath }/user/useradd">添加用户</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">用户角色</th>
                    <th width="10%">性别</th>
                    <th width="10%">生日</th>
                    <th width="10%">电话</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach items="${userList}" var="ur" varStatus="status">
					<tr>
						<td>
							<span>${ur.userCode}</span>
						</td>
						<td>
							<span>${ur.userName }</span>
						</td>
						<td>
							<span>${ur.userRole }</span>
						</td>
						<td>
							<span>${ur.gender==1?"男":"女" }</span>
						</td>
						<td>
						    <span><f:formatDate value="${ur.birthday}" pattern="yyyy-MM-dd"/></span>
						</td>
						<td>
							<span>${ur.phone}</span>
						</td>
						
						<td>
							<span><a class="viewUser" href="${pageContext.request.contextPath }/user/userview?id=${ur.id}"><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
							<span><a class="modifyUser" href="${pageContext.request.contextPath }/user/usermodify?id=${ur.id}&flag=update"><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
							<span><a class="deleteUser" href="${pageContext.request.contextPath }/user/userdel?id=${ur.id}"><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="page-bar">
				<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
				<c:import url="rollpage.jsp">
					<c:param name="totalCount" value="${totalCount}"/>
					<c:param name="currentPageNo" value="${currentPageNo}"/>
					<c:param name="totalPageCount" value="${totalPageCount}"/>
				</c:import>
			</div>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="common/foot.jsp" %>
