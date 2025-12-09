<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">ONLINE ENTERTAINMENT</a>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a>
            </li>
            
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li class="nav-item"><a class="nav-link" href="#">Yêu thích</a></li>
                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Xin chào, ${sessionScope.user.fullname}
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Đổi mật khẩu</a>
                            <a class="dropdown-item" href="#">Cập nhật hồ sơ</a>
                            <c:if test="${sessionScope.user.admin}">
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/videos">Quản trị Admin</a>
                            </c:if>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/register">Đăng ký</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>