<%--
  Created by IntelliJ IDEA.
  User: darxan
  Date: 2017/2/21
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>hostel's custom check-in</title>
    <jsp:include page="../common/header.jsp"/>
    <script>
        $(document).ready(function () {
            var currentPage = 1;
            var visiblePages = 10;
            var totalPage = 1;
            totalPage = ${paginationResult.totalPages};
            currentPage = ${reservationRestrict.page};

            $('#myPagination').jqPaginator({
                totalPages: totalPage,
                visiblePages: visiblePages,
                currentPage: currentPage,
                onPageChange: function (num, type) {
                    if (num!=currentPage) {
                        $("#pageNum").attr("value", num);
                        $("#searchButton").click();
                    }
                }
            });
        });
    </script>
</head>
<body>

<jsp:include page="../common/nav.jsp"/>

<div class="myContent">


    <div class="container-fluid">
        <div class="row-fluid">

            <div class="col-md-2"></div>
            <div class="col-md-7">
                <div class="listItems">
                    <c:forEach items="${paginationResult.items}" var="reservation">

                        <div class="" style="display:block;">
                            <div class="col-xs-12 col-sm-12 col-md-4 ">
                                <a  class=" "  href="../../public/user/${reservation.memberId}" title="${reservation.name}" >
                                    <img class="img-responsive" alt="${reservation.contact}" src="${reservation.avatar}" style="height:160px;width:150px;border-width:0px;">
                                </a>
                            </div>
                            <diV class="col-xs-12 col-sm-12 col-md-8">
                                <h3 class="">
                                    <a class="" title="" href="../../public/user/${reservation.memberId}">${reservation.contact}</a>
                                </h3>
                                <a class="" title="" href="../../public/user/${reservation.memberId}">
                                    <div class="">
                                        <span class="phone">${reservation.phone}</span>
                                    </div>
                                </a>

                                <c:if test="${reservation.checkOut!=true}">
                                    <a class="">
                                        <div class="">
                                            <span onclick="alert('${reservation.memberId}')" class="checkout">点击checkout</span>
                                        </div>
                                    </a>
                                </c:if>
                                <c:if test="${reservation.checkOut==true}">
                                    <br/>
                                </c:if>
                                <br/>
                                <a class="">
                                    <div class="">
                                        <span class="price">
                                            <dfn>¥</dfn>
                                            <b>${reservation.price}*${reservation.amount}</b>
                                        </span>
                                    </div>
                                </a>
                                <a class="">
                                    <div class="">
                                        <span class="time">${reservation.reserveTime}</span>
                                    </div>
                                </a>
                            </diV>

                        </div>
                        <div class="clearfix"></div>
                    </c:forEach>
                </div>

                <div class="myPager">
                    <ul id="myPagination" class="pagination" ></ul >
                </div>
            </div>

            <div class="col-md-3">
                <form action="../../hostel/list/checkIn" method="post">
                    <fieldset>
                        <legend>入住单搜索</legend>


                        <input type="hidden" class="page" id="pageNum" name="page"  value="${reservationRestrict.page}"/>

                        <label>搜索关键字</label>
                        <input type="text" class="keyword" name="keyword" value="${reservationRestrict.keyword}"/>
                        <span class="help-block"></span>

                        <%--<label>排序方式</label>--%>
                        <input type="hidden" class="order" name="order"  value="${reservationRestrict.order}"/>

                        <label>从旧到新</label>
                        <input type="radio" class="order" name="asc"  value="${reservationRestrict.asc}"/>
                        <span class="help-block"></span>

                        <label>开始日期</label>
                        <input type="date" class="dateLower" name="dateLower" value="${reservationRestrict.dateLowerString}">
                        <span class="help-block"></span>

                        <label>截至日期</label>
                        <input type="date" class="dateUpper" name="dateUpper" value="${reservationRestrict.dateUpperString}">
                        <span class="help-block"></span>

                        <button id="searchButton" type="submit" class="btn">提交</button>

                    </fieldset>

                </form>

            </div>
        </div>
    </div>


</div>

</body>
</html>
