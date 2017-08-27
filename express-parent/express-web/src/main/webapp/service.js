function Service(url) {
    this.rootUrl = url;
    this._error_handler = function (response) {
        alert(response.msg);
    }
}

Service.prototype = {
    constructor: Service,
    //get异步
    get: function (param, callback) {
        $.ajax({
            cache: false,
            type: 'GET',
            url: this.rootUrl + '?' + $.param(param),
            dataType: "json",
            success: function (response) {
                callback(response);
            },
            error: this._error_handler
        });
    },
    //get同步
    getAllAsync: function (param, callback) {
        $.ajax({
            cache: false,
            type: 'GET',
            url: this.rootUrl + '?' + $.param(param),
            dataType: "json",
            async: false,
            success: function (response) {
                callback(response);
            },
            error: this._error_handler
        });
    },

    //post
    post: function (param, callback, errorCallBack) {
        var error = this._error_handler;
        $.ajax({
            cache: false,
            async: false,
            type: 'POST',
            contentType: 'application/json',
            url: this.rootUrl,
            dataType: "json",
            data: JSON.stringify(param),
            success: function (response) {
                callback(response);
            },
            error: function (jqXHR) {
                error(jqXHR, param, errorCallBack);
            }
        });
    },

    //put
    update: function (param, callback) {
        $.ajax({
            cache: false,
            type: 'POST',
            contentType: 'application/json',
            url: this.rootUrl,
            dataType: "json",
            data: JSON.stringify(param),
            success: function (response) {
                callback(response);
            },
            error: this._error_handler
        });
    },
    //update
    updateEx: function (urlParam, param, callback) {
        $.ajax({
            cache: false,
            type: 'Post',
            contentType: 'application/json',
            url: this.rootUrl + urlParam,
            dataType: "json",
            data: JSON.stringify(param),
            success: function (response) {
                callback(response);
            },
            error: this._error_handler
        });
    },

    //delete
    remove: function (urlParam, callback) {
        $.ajax({
            cache: false,
            type: 'DELETE',
            url: this.rootUrl + urlParam,
            success: function (response) {
                callback(response);
            },
            error: this._error_handler
        });
    },
    getUrlParams:function(){
        var args=new Object();
        var query=location.search.substring(1);//获取查询串
        var pairs=query.split("&");//在逗号处断开
        for(var   i=0;i<pairs.length;i++) {
            var pos=pairs[i].indexOf('=');//查找name=value
            if(pos==-1)   continue;//如果没有找到就跳过
            var argname=pairs[i].substring(0,pos);//提取name
            var value=pairs[i].substring(pos+1);//提取value
            args[argname]=unescape(value);//存为属性
        }
        return args;
    }
};

Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};