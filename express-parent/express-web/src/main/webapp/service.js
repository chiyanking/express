function Service(url) {
    this.rootUrl = url;
    this._error_handler = function (response) {
        if (mini) {
            mini.alert(response.msg);
        } else {
            alert(response.msg);
        }
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
                if (response.success) {
                    callback(response);
                } else {
                    mini.alert(response.msg);
                }
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
                if (response.success) {
                    callback(response);
                } else {
                    mini.alert(response.msg);
                }
            },
            error: this._error_handler
        });
    },

    //post
    postAll: function (param, callback, errorCallBack) {
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
                if (response.success) {
                    callback(response);
                } else {
                    mini.alert(response.msg);
                    btnOkEnabled(true);
                }
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
                if (response.success) {
                    callback(response);
                } else {
                    mini.alert(response.msg);
                }
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
                if (response.success) {
                    callback(response);
                } else {
                    mini.alert(response.msg);
                }
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
                if (response.success) {
                    callback(response);
                } else {
                    mini.alert(response.msg);
                }
            },
            error: this._error_handler
        });
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