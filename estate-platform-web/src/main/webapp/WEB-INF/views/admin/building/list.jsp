<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin/user/list"/>
<c:url var="formAjax" value="/ajax/user"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <spring:message code="label.user.list"/>
    </title>
</head>

<body>
<div class="main-content">
    <form:form commandName="model" action="${formUrl}" id="listForm" method="GET">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value="/admin/home"/>">
                            <spring:message code="label.home"/>
                        </a>
                    </li>
                    <li class="active">
                        <spring:message code="label.user.list"/>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${messageResponse!=null}">
                            <div class="alert alert-block alert-${alert}">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="widget-header">
                                        <h4 class="widget-title">
                                            <spring:message code="label.search"/>
                                        </h4>
                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-horizontal">
                                                <!-- các field cho phần search -->
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <!-- <spring:message code="label.search.value"/> -->
                                                        <spring:message text="Tên sản phẩm"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="Diện tích sàn"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- quận -->
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="Quận hiện có"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:select path="districtCode" id="districtCode">
                                                                <form:option value="NONE" label="--- Chọn quận ---"/>                            	                   	
                                                                <form:options items="${model.districts}" />
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="phường"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="đường"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <span class="col-sm-2 control-label">
                                                        <spring:message text="Diện tích"/>
                                                    </span>
                                                    <div class="col-sm-8">
                                                        <div class="form-group row">
                                                            <label for="inputKey" class="col-md-1 control-label">Từ</label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control" id="inputKey" placeholder="0">
                                                            </div>
                                                            <label for="inputValue" class="col-md-1 control-label">Đến</label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control" id="inputValue" placeholder="1">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="số tầng hầm"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="hướng"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="hạng"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <span class="col-sm-2 control-label">
                                                        <spring:message text="Giá thuê"/>
                                                    </span>
                                                    <div class="col-sm-8">
                                                        <div class="form-group row">
                                                            <label for="inputKey" class="col-md-1 control-label">Từ</label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control" id="inputKey" placeholder="0">
                                                            </div>
                                                            <label for="inputValue" class="col-md-1 control-label">Đến</label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control" id="inputValue" placeholder="1">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="tên quản lý"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="điên thoại"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- nhân viên -->
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                        <spring:message text="Nhân viên phụ trách"/>
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:select path="userName" id="userName">
                                                                <form:option value="NONE" label="--- Nhân viên ---"/>                            	                   	
                                                                <form:options items="${model.userDTOs}" />
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- Loại tòa nhà -->
                                                <div class="form-group">
                                                        <label class="col-sm-2 control-label">
                                                            <spring:message text="Loại tòa nhà"/>
                                                        </label>
                                                        <div class="col-sm-8">
                                                            <div class="fg-line">
                                                                <form:checkboxes items="${model.buildingTypes}"
                                                                path="buildingTypeName" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                <!-- nút tìm -->
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-8">
                                                        <button id="btnSearch" type="button"
                                                                class="btn btn-sm btn-success">
                                                            <spring:message code="label.search"/>
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                                                   requestURI="${formUrl}" partialList="true" sort="external"
                                                   size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                                                   id="tableList" pagesize="${model.maxPageItems}"
                                                   export="false"
                                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                   style="margin: 3em 0 1.5em;">
                                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                        headerClass="center select-cell">
                                            <fieldset>
                                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                                            </fieldset>
                                        </display:column>
                                        <display:column headerClass="text-left" property="" title="Ngày"/>
                                        <display:column headerClass="text-left" property="" title="tên sản phẩm"/>
                                        <display:column headerClass="text-left" property="" title="Địa chỉ"/>
                                        <display:column headerClass="text-left" property="" title="Tên quản lý"/>
                                        <display:column headerClass="text-left" property="" title="Số điện thoại"/>
                                        <display:column headerClass="text-left" property="" title="Diện tích sàn"/>
                                        <display:column headerClass="text-left" property="" title="Diện tích trống"/>
                                        <display:column headerClass="text-left" property="" title="Giá thuê"/>
                                        <display:column headerClass="text-left" property="" title="Phí mô giới"/>
                                        <display:column headerClass="col-actions" title="Chi tiết">
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Cập nhật người dùng"
                                               href='<c:url value="/admin/user/edit/${tableList.id}"/>'>
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </a>
                                        </display:column>
                                    </display:table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var someJsVar = "<c:out value='${addOrEditNews}'/>";
        $('#btnSearch').click(function () {
            $('#listForm').submit();
        });
    });

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteNews(dataArray);
        });
    }

    function deleteNews(data) {
        $.ajax({
            url: '${formAjax}/',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "${formUrl}";
            },
            error: function (res) {
                console.log(res);
            }
        });
    }
</script>
</body>

</html>