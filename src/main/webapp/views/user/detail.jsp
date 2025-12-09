<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/views/common/head.jsp" %>
    <title>${video.title}</title>
</head>
<body>
    <%@include file="/views/common/menu.jsp" %>
    
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-8">
                <div class="embed-responsive embed-responsive-16by9 mb-3">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${video.id}" allowfullscreen></iframe>
                </div>
                <h3>${video.title}</h3>
                <hr>
                <p>${video.description}</p>
                <div class="mb-5">
                    <a href="${pageContext.request.contextPath}/like?id=${video.id}" class="btn btn-outline-success btn-lg">Like</a>
                    <button type="button" class="btn btn-outline-primary btn-lg" data-toggle="modal" data-target="#shareModal">Share</button>
                </div>
            </div>
            
            <div class="col-md-4">
                <h5>CÓ THỂ BẠN THÍCH</h5>
                <c:forEach var="item" items="${randomVideos}" begin="0" end="4">
                    <div class="row mb-3">
                        <div class="col-5">
                            <a href="detail?id=${item.id}">
                                <img src="${item.poster}" class="img-fluid">
                            </a>
                        </div>
                        <div class="col-7">
                            <a href="detail?id=${item.id}">${item.title}</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <div class="modal fade" id="shareModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Gửi video cho bạn bè</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form id="shareForm">
                        <input type="hidden" name="videoId" value="${video.id}">
                        <div class="form-group">
                            <label>Nhập Email bạn bè:</label>
                            <input type="email" name="email" class="form-control" placeholder="friend@gmail.com" required>
                        </div>
                        <button type="button" onclick="sendShare()" class="btn btn-primary">Gửi</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        function sendShare() {
            $.post('${pageContext.request.contextPath}/share', $('#shareForm').serialize(), function(data) {
                if(data === 'Success') alert('Đã gửi email thành công!');
                else alert('Lỗi gửi mail hoặc bạn chưa đăng nhập!');
                $('#shareModal').modal('hide');
            });
        }
    </script>
</body>
</html>