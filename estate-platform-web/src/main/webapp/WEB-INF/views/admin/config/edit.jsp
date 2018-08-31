<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/ajax/config"/>
<html>
<head>
    <title><spring:message code="label.configuration.edit"/></title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin/home"/>">
                        <spring:message code="label.home"/>
                    </a>
                </li>
                <li class="active"><spring:message code="label.configuration.edit"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form:form id="formEdit" commandName="model">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại cấu hình</label>
                            <div class="col-sm-9">
                                <form:select path="type" id="type">
                                    <form:option value="NONE" label="--- Chọn vai trò ---"/>
                                    <form:options items="${model.configTypes}" />
                                </form:select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><spring:message code="label.configuration.key"/></label>
                            <div class="col-sm-9">
                                <form:input path="key" id="key" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><spring:message code="label.configuration.name"/></label>
                            <div class="col-sm-9">
                                <form:input path="name" id="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><spring:message code="label.configuration.value"/></label>
                            <div class="col-sm-9">
                                <form:textarea path="value" cols="80" rows="10" id="value"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật cấu hình" id="btnAddOrUpdateConfig"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới cấu hình" id="btnAddOrUpdateConfig"/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="configId"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var editor = '';
    $(document).ready(function () {
//        editor = CKEDITOR.replace( 'value' );
    });
    $("#btnAddOrUpdateConfig").click(function (event) {
        event.preventDefault();
        var dataArray = {};
        dataArray["type"] = $('#type').val();
        dataArray["key"] = $('#key').val();
        dataArray["value"] = $('#value').val();
        dataArray["name"] = $('#name').val();
        if ($('#configId').val() != "") {
            updateConfig(dataArray, $('#configId').val());
        }
        else{
            addConfig(dataArray);
        }
    });
    function addConfig(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/configuration/list'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    }
    function updateConfig(data, id) {
        $.ajax({
            url: '${formUrl}/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/configuration/edit/"+res.id+"'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    }
</script>
</body>
</html>
