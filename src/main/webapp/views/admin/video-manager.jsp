<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Video</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>QUẢN LÝ VIDEO</h2>
        
        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>

        <form action="" method="post" class="border p-3 mb-3">
            <div class="form-group">
                <label>Youtube ID:</label>
                <input type="text" name="id" value="${video.id}" class="form-control" placeholder="Ví dụ: v=dQw4w9WgXcQ">
            </div>
            <div class="form-group">
                <label>Tiêu đề:</label>
                <input type="text" name="title" value="${video.title}" class="form-control">
            </div>
            <div class="form-group">
                <label>Poster (Link ảnh):</label>
                <input type="text" name="poster" value="${video.poster}" class="form-control">
            </div>
            <div class="form-group">
                <label>Mô tả:</label>
                <textarea name="description" class="form-control">${video.description}</textarea>
            </div>
            <div class="form-check mb-3">
                <label class="form-check-label">
                    <input type="checkbox" name="active" value="true" class="form-check-input" ${video.active ? 'checked' : ''}> Hoạt động
                </label>
            </div>
            
            <button formaction="${pageContext.request.contextPath}/admin/video/create" class="btn btn-primary">Thêm mới</button>
            <button formaction="${pageContext.request.contextPath}/admin/video/update" class="btn btn-warning">Cập nhật</button>
            <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary">Làm mới</a>
        </form>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tiêu đề</th>
                    <th>Lượt xem</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.title}</td>
                        <td>${item.views}</td>
                        <td>${item.active ? 'Active' : 'Inactive'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/video/edit?id=${item.id}">Sửa</a> |
                            <a href="${pageContext.request.contextPath}/admin/video/delete?id=${item.id}" onclick="return confirm('Bạn chắc chắn xóa?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>